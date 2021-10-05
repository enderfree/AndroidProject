package team3.samuelandsebastian.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bypassBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bypassBtn = findViewById(R.id.bypassBtn);
        bypassBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bypassBtn:
                openAPIActivity();
                break;
        }
    }

    private void openAPIActivity() {
        Intent intent = new Intent(this, APIActivity.class);
        startActivity(intent);
    }
}