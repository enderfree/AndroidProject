package team3.samuelandsebastian.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

import team3.samuelandsebastian.androidproject.adapter.RecyclerAdapter;
import team3.samuelandsebastian.androidproject.listener.RecyclerItemClickListener;
import team3.samuelandsebastian.androidproject.models.Word;

public class MainApiActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton fabAdd;
    private RecyclerView recyclerView;
    private ArrayList<Word> words = new ArrayList<>();
    private RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_api);
        init();
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        loadWords();
    }

    private void init() {
        fabAdd = findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(this);
        recyclerView = findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new RecyclerAdapter(words);
        recyclerView.setAdapter(recyclerAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getBaseContext(), WordViewActivity.class);
                        intent.putExtra("data", words.get(position));
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        words.get(position).delete();
                        words.remove(position);
                        recyclerAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                }));
    }

    private void loadWords() {
        Word.findAll().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                words.clear();
                for(DataSnapshot snap : snapshot.getChildren()) {
                    words.add(snap.getValue(Word.class));
                }
                Collections.reverse(words);
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.fabAdd) {
            startActivity(new Intent(getBaseContext(), APIActivity.class));
        }
    }
}