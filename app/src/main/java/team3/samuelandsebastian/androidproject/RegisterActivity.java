package team3.samuelandsebastian.androidproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import team3.samuelandsebastian.androidproject.models.User;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextFirstName, editTextLastName, editTextEMail, editTextPassword, editTextPasswordConfirmation;
    private Button buttonRegister, buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initialise();
    }

    private void initialise(){
        editTextFirstName = findViewById(R.id.editTextRegisterFirstName);
        editTextLastName = findViewById(R.id.editTextRegisterLastName);
        editTextEMail = findViewById(R.id.editTextRegisterEmail);
        editTextPassword = findViewById(R.id.editTextRegisterPassword);
        editTextPasswordConfirmation = findViewById(R.id.editTextRegisterConfirmPassword);

        buttonRegister = findViewById(R.id.buttonRegisterRegister);
        buttonCancel = findViewById(R.id.buttonRegisterCancel);

        buttonRegister.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonRegisterRegister:
                buttonRegisterAction();
                break;
            case R.id.buttonRegisterCancel:
                buttonCancelAction();
                break;
            default:
                Toast.makeText(getApplicationContext(), "This button was not registered yet, please warn the responsible about it", Toast.LENGTH_LONG).show();
                break;
        }
    }

    private void displayOkDialog(AlertDialog.Builder dialog){
        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //disappear
            }
        });
        dialog.create();
        dialog.show();
    }

    private void buttonRegisterAction(){
        ///validate data
        //Show error message
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Invalid Registration");

        if(editTextFirstName.getText() == null && editTextLastName.getText() == null && editTextEMail.getText() == null && editTextPassword == null && editTextPasswordConfirmation != null){
            dialog.setMessage("Please fill all fields.");
            displayOkDialog(dialog);
            return;
        }
        else if(!(editTextFirstName.getText().toString().matches("^[A-Z]\\w+") && editTextLastName.getText().toString().matches("^[A-Z]\\w+"))){
            dialog.setMessage("Proper names start wit capitals and have at least an other letter!");
            displayOkDialog(dialog);
            return;
        }
        else if(!editTextEMail.getText().toString().matches("^[A-Za-z0-9]+@[A-Za-z0-9]+\\.[A-Za-z0-9.]+$")){
            dialog.setMessage("Invalid Email.");
            displayOkDialog(dialog);
            return;
        }
        else if(User.getUserByEmail(editTextEMail.getText().toString()) != null){
            dialog.setMessage("Don't you already have an account?");
            displayOkDialog(dialog);
            return;
        }
        else if(editTextPassword.getText().toString().length() < 8){
            dialog.setMessage("Password too short!");
            displayOkDialog(dialog);
            return;
        }
        else if(!editTextPassword.getText().toString().equals(editTextPasswordConfirmation.getText().toString())){
            dialog.setMessage("Both passwords didn't match!");
            displayOkDialog(dialog);
            return;
        }
        else{
            String firstName = editTextFirstName.getText().toString();
            String lastName = editTextLastName.getText().toString();
            String email = editTextEMail.getText().toString();
            String password = "" + editTextPassword.getText().toString().hashCode() * editTextEMail.getText().toString().hashCode();

            new User(firstName, lastName, email, password).insert().addOnFailureListener(error -> {
                Log.i("Insert Fail", error.getMessage());
            });

            Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void buttonCancelAction(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}