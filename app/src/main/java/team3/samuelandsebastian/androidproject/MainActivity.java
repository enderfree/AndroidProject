package team3.samuelandsebastian.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import team3.samuelandsebastian.androidproject.models.User;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextEmail, editTextPassword;
    private Button buttonLogin, buttonRegister ,bypassBtn;

    private static boolean DEV_MODE = false;

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
            case R.id.bypassBtn: //for testing
                DEV_MODE = true;
                openAPIActivity();
                break;
            default:
                Toast.makeText(getApplicationContext(), "This button was not registered yet, please warn the responsible about it", Toast.LENGTH_LONG).show();
                break;
        }
    }

    private void invalidCredentials(){ //That's a lot of code for one box! I need it twice...
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Invalid Credentials");
        dialog.setMessage("The email and password you used doesn't match!");

        // Can set listener to null since it doesn't do anything.
        dialog.setPositiveButton("Ok", null);
        dialog.create();
        dialog.show();
    }

    private void loginAction(){
        User.findByEmail(editTextEmail.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int i = 0;
                for (DataSnapshot snap: snapshot.getChildren()) {
                    String currentPassword = "" + editTextPassword.getText().toString().hashCode() * editTextEmail.getText().toString().hashCode();
                    User user = snap.getValue(User.class);
                    if(user.getPassword().equals(currentPassword)) {

                        // Store the current user in the sharedpreferences.
                        SharedPreferences sp = getSharedPreferences("account", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.clear();
                        Gson gson = new Gson();
                        String userJson = gson.toJson(user);
                        editor.putString("user", userJson);
                        editor.commit();
                        DEV_MODE = false;
                        openAPIActivity();
                    }
                    else { //The fact that this may get called more than once isn't a problem since email should be unique... just don't use 123@123.com
                        invalidCredentials();
                    }

                    ++i;
                }

                if(i == 0){
                    invalidCredentials();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //don't care but obligatory override
            }
        });
    }

    private void registerAction(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private void openAPIActivity() {
        Intent intent = new Intent(this, MainApiActivity.class);
        startActivity(intent);
    }

    public static boolean isDevMode() {
        return DEV_MODE;
    }
}