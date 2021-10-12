package team3.samuelandsebastian.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import team3.samuelandsebastian.androidproject.api.IWordAPI;
import team3.samuelandsebastian.androidproject.models.Word;
import team3.samuelandsebastian.androidproject.models.WordResult;

public class APIActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonSrch;
    private EditText editTextWord;
    private CheckBox chBoxSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apiactivity);
        init();
    }

    private void init() {
        buttonSrch = findViewById(R.id.buttonSrch);
        editTextWord = findViewById(R.id.txtWord);
        chBoxSave = findViewById(R.id.chBoxSave);
        buttonSrch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSrch:
                callWordApi();
                break;
        }
    }

    private void callWordApi() {
        String wordStr = editTextWord.getText().toString();

        if(wordStr.isEmpty()) {
            Toast.makeText(getBaseContext(), "You need to enter a wordStr", Toast.LENGTH_SHORT).show();
            return;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://wordsapiv1.p.rapidapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IWordAPI iWordAPI = retrofit.create(IWordAPI.class);

        Map<String, String> headers = new HashMap<>();
        headers.put("x-rapidapi-host", "wordsapiv1.p.rapidapi.com");
        headers.put("x-rapidapi-key", "758a77b168msh4606f6bf6b1870dp170691jsne214277ce7f6");

        Call<JsonObject> call = iWordAPI.getData(wordStr, headers);

        call.enqueue(new Callback<JsonObject>() {

            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.i("Response Code", String.valueOf(response.code()));

                if (response.code() != 200) {   // Http code 200 = success
                    Toast.makeText(getBaseContext(), "No response/error from server!", Toast.LENGTH_LONG).show();
                    return;
                }
                Gson gson = new Gson();
                String jsonString = gson.toJson(response.body());

                JsonParser parser = new JsonParser();
                JsonObject json = (JsonObject) parser.parse(jsonString);
                Word word;
                try {
                    JsonArray results = json.getAsJsonArray("results");
                    List<WordResult> wordResultList = new ArrayList<>();
                    for(int i = 0; i < results.size(); i++) {
                        JsonObject result = results.get(i).getAsJsonObject();
                        String[] synonyms = gson.fromJson(result.getAsJsonArray("synonyms"), String[].class);
                        String[] examples = gson.fromJson(result.getAsJsonArray("examples"), String[].class);

                        WordResult wordResultObj = new WordResult(
                                result.getAsJsonPrimitive("partOfSpeech").getAsString(),
                                result.getAsJsonPrimitive("definition").getAsString(),
                                synonyms != null ? Arrays.asList(synonyms) : null,
                                examples != null ? Arrays.asList(examples) : null
                        );
                        wordResultList.add(wordResultObj);
                    }
                    word = new Word(wordStr, wordResultList);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getBaseContext(), "Error fetching data! \n" + e.toString(), Toast.LENGTH_LONG).show();
                    return;
                }

                // Added this for testing purposes can delete later...
                for(int i = 0; i < word.getResults().size(); i++) {
                    Log.i("Definition #" + (i + 1), word.getResults().get(i).getDefinition());
                }

                if(chBoxSave.isChecked()) {
                    word.insert().addOnSuccessListener(success -> {
                        Toast.makeText(getBaseContext(), "Word Saved!\n" + word.getId(), Toast.LENGTH_LONG).show();
                    }).addOnFailureListener(error -> {
                        Toast.makeText(getBaseContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                        Log.i("Error DB", error.getMessage());
                    });
                }

                Intent intent = new Intent(getBaseContext(), WordViewActivity.class);
                intent.putExtra("data", word);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Something is wrong! \n" + t.toString(), Toast.LENGTH_LONG).show();
            }

        });

    }
}