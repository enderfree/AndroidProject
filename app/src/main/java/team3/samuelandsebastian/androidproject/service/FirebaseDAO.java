package team3.samuelandsebastian.androidproject.service;

import com.google.firebase.auth.FirebaseAuth;

public class FirebaseDAO {
    private static FirebaseAuth auth;

    public static FirebaseAuth getAuth(){
        if(auth == null){
            auth = FirebaseAuth.getInstance();
        }
        return auth;
    }
}
