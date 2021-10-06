package team3.samuelandsebastian.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import team3.samuelandsebastian.androidproject.adapter.ListWordAdapter;
import team3.samuelandsebastian.androidproject.models.DataModel;

public class WordViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView wordListView;
    private ListWordAdapter wordAdapter;
    private DataModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_view);
        init();
    }

    private void init() {
        wordListView = findViewById(R.id.wordListView);

        model = (DataModel) getIntent().getSerializableExtra("data");
        wordAdapter = new ListWordAdapter(this, model, -1);
        wordListView.setAdapter(wordAdapter);
        wordListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(wordAdapter.getCurrentSelectedCell() == position) {
            wordAdapter = new ListWordAdapter(this, model, -1);
        } else {
            wordAdapter = new ListWordAdapter(this, model, position);
        }
        wordListView.setAdapter(wordAdapter);
    }
}