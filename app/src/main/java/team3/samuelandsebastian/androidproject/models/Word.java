package team3.samuelandsebastian.androidproject.models;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.Query;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import team3.samuelandsebastian.androidproject.service.FirebaseDAO;

public class Word implements Serializable {
    private static String collectionName = "Words";

    private String word;
    private List<WordResult> results;
    private long timeInMillis;
    private String id;

    public Word(String word, List<WordResult> results) {
        this.word = word;
        this.results = results;
        this.timeInMillis = System.currentTimeMillis();
    }

    public Word() {}

    public String getId() {
        return id;
    }

    @Exclude
    public String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CANADA);
        Date date = new Date(this.timeInMillis);
        return sdf.format(date);
    }

    public long getTimeInMillis() {
        return this.timeInMillis;
    }

    public String getWord() {
        return word;
    }

    public List<WordResult> getResults() {
        return results;
    }

    public Task<Void> insert() {
        DatabaseReference firebase = FirebaseDAO.getDatabaseReference();
        this.id = UUID.randomUUID().toString();
        return firebase.child(collectionName)
                .child(this.id)
                .setValue(this);
    }

    public void delete() {
        DatabaseReference firebase = FirebaseDAO.getDatabaseReference();
        firebase.child(collectionName)
                .child(this.id)
                .removeValue();
    }

    public static Query findAll() {
        DatabaseReference firebase = FirebaseDAO.getDatabaseReference();
        return firebase.child(collectionName).orderByChild("timeInMillis");
    }
}
