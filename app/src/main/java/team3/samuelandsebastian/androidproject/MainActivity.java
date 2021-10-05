package team3.samuelandsebastian.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextEmail, editTextPassword;
    private Button buttonLogin, buttonRegister ,bypassBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialise();
    }

    private void initialise(){
        editTextEmail = findViewById(R.id.editTextEMail);
        editTextPassword = findViewById(R.id.editTextPassword);

        buttonLogin = findViewById(R.id.buttonLogin);
        buttonRegister = findViewById(R.id.buttonRegister);
        bypassBtn = findViewById(R.id.bypassBtn);

        buttonLogin.setOnClickListener(this);
        buttonRegister.setOnClickListener(this);
        bypassBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonLogin:
                loginAction();
                break;
            case R.id.buttonRegister:
                registerAction();
                break;
            case R.id.bypassBtn:
                openAPIActivity();
                break;
            default:
                Toast.makeText(getApplicationContext(), "This button was not registered yet, please warn the responsible about it", Toast.LENGTH_LONG).show();
                break;
        }
    }

    private void loginAction(){

    }

    private void registerAction(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private void openAPIActivity() {
        Intent intent = new Intent(this, APIActivity.class);
        startActivity(intent);
    }
}