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
    private List<WordResult> wordResults;
    private long time;
    private String id;

    public Word(String word, List<WordResult> wordResults) {
        this.word = word;
        this.wordResults = wordResults;
        this.time = System.currentTimeMillis();
    }

    public String getId() {
        return id;
    }

    @Exclude
    public String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CANADA);
        Date date = new Date(this.time);
        return sdf.format(date);
    }

    public long getTimeInMillis() {
        return this.time;
    }

    public String getWord() {
        return word;
    }

    public List<WordResult> getResults() {
        return wordResults;
    }

    public Task<Void> insert() {
        DatabaseReference firebase = FirebaseDAO.getDatabaseReference();
        this.id = UUID.randomUUID().toString();
        return firebase.child(collectionName)
                .child(this.id)
                .setValue(this);
    }

    public Task<Void> delete() {
        DatabaseReference firebase = FirebaseDAO.getDatabaseReference();
        return firebase.child(collectionName)
                .child(this.id)
                .removeValue();
    }

    public static Query findAll() {
        DatabaseReference firebase = FirebaseDAO.getDatabaseReference();
        return firebase.child(collectionName).orderByKey();
    }
}
