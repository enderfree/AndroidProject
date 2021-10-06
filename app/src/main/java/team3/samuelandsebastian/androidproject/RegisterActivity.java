package team3.samuelandsebastian.androidproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
            dialog.setMessage("message");
            displayOkDialog(dialog);
            return;
        }

        if(editTextFirstName.getText().toString().matches("^[A-Z]\\w+") && editTextLastName.getText().toString().matches("^[A-Z]\\w+")){
            dialog.setMessage("message");
            displayOkDialog(dialog);
            return;
        }

        //I don't check email further since firebase will do it for us

        //need access to db in order to check uniqueness of password
    }

    private void buttonCancelAction(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}