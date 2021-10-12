package team3.samuelandsebastian.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainApiActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_api);
        init();
    }

    private void init() {
        fabAdd = findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.fabAdd) {
            startActivity(new Intent(getBaseContext(), APIActivity.class));
        }
    }
}