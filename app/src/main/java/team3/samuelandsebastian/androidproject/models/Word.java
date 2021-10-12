package team3.samuelandsebastian.androidproject.models;

import java.io.Serializable;
import java.util.List;

public class Word implements Serializable {
    private static String collectionName = "History";

    private String word;
    private List<WordResult> wordResults;

    public Word(String word, List<WordResult> wordResults) {
        this.word = word;
        this.wordResults = wordResults;
    }

    public String getWord() {
        return word;
    }

    public List<WordResult> getResults() {
        return wordResults;
    }
}
