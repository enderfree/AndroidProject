package team3.samuelandsebastian.androidproject.models;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import team3.samuelandsebastian.androidproject.service.FirebaseDAO;

public class User {
    private static String collectionName = "Users";

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;

    public User(String firstName, String lastName, String emailAddress, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
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

    @Exclude
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

    public static User getUserByEmail(String emailAddress){
        DatabaseReference firebase = FirebaseDAO.getDatabaseReference();

        try{
            String firstName = firebase.child(collectionName).child(emailAddress).child("firstName").toString();
            String lastName = firebase.child(collectionName).child(emailAddress).child("lastName").toString();
            String password = firebase.child(collectionName).child(emailAddress).child("password").toString();

            return new User(firstName, lastName, emailAddress, password);
        }
        catch (Exception e){
            return null;
        }
    }
}
