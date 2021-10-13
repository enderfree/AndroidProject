package team3.samuelandsebastian.androidproject.models;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;

import team3.samuelandsebastian.androidproject.service.FirebaseDAO;

public class User {
    private static String collectionName = "Users";

    private String id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;

    public User(String firstName, String lastName, String emailAddress, String password) {
        this.id = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public User() {} //actually needed for some thing we called

    //Getters and setters
    @Exclude
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static String getCollectionName() {
        return collectionName;
    }

    public static void setCollectionName(String collectionName) {
        User.collectionName = collectionName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //Query
    public Task<Void> insert(){
        DatabaseReference firebase = FirebaseDAO.getDatabaseReference();

        return firebase.child(collectionName).child(id).setValue(this);
    }

    public static Query findByEmail(String emailAddress) { //don't expend here... async!
        DatabaseReference firebase = FirebaseDAO.getDatabaseReference();
        return firebase.child(collectionName).orderByChild("emailAddress").equalTo(emailAddress);
    }
}
