package team3.samuelandsebastian.androidproject.models;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import team3.samuelandsebastian.androidproject.service.FirebaseDAO;

public class Word implements Serializable {
    private static String collectionName = "Words";

    private String word;
    private List<WordResult> wordResults;
    private String id;

    public Word(String word, List<WordResult> wordResults) {
        this.word = word;
        this.wordResults = wordResults;
    }

    public String getId() {
        return id;
    }

    public String getWord() {
        return word;
    }

    public List<WordResult> getResults() {
        return wordResults;
    }

    public Task<Void> upsert() {
        DatabaseReference firebase = FirebaseDAO.getDatabaseReference();

        if(this.id == null) {
            this.id = UUID.randomUUID().toString();
        }

        return firebase.child(collectionName)
                .child(this.id)
                .setValue(this);
    }

}
