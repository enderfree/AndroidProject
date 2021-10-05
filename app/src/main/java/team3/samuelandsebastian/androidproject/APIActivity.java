package team3.samuelandsebastian.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import team3.samuelandsebastian.androidproject.api.IWordAPI;

public class APIActivity extends AppCompatActivity implements View.OnClickListener {

    private Button searchBtn;
    private EditText txtWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apiactivity);
        init();
    }

    private void init() {
        searchBtn = findViewById(R.id.searchBtn);
        txtWord = findViewById(R.id.txtWord);

        searchBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.searchBtn:
                callWordApi();
                break;
        }
    }

    private void callWordApi() {
        String word = txtWord.getText().toString();

        if(word.isEmpty()) {
            Toast.makeText(getBaseContext(), "You need to enter a word", Toast.LENGTH_SHORT).show();
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

        Call<JsonObject> call = iWordAPI.getData("car", headers);

        call.enqueue(new Callback<JsonObject>() {

            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.i("Response Code", String.valueOf(response.code()));

                if (response.code() != 200) {   // Http code 200 = success
                    Toast.makeText(getBaseContext(), "No response/error from server!", Toast.LENGTH_LONG).show();
                    return;
                }

                String jsonString = new Gson().toJson(response.body());

                JsonParser parser = new JsonParser();
                JsonObject json = (JsonObject) parser.parse(jsonString);

                try {

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Something is wrong! \n" + t.toString(), Toast.LENGTH_LONG).show();
            }

        });

    }
}