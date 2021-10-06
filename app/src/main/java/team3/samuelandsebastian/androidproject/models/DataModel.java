package team3.samuelandsebastian.androidproject.models;

import java.io.Serializable;
import java.util.List;

public class DataModel implements Serializable {
    private String word;
    private List<Result> results;

    public DataModel(String word, List<Result> results) {
        this.word = word;
        this.results = results;
    }

    public String getWord() {
        return word;
    }

    public List<Result> getResults() {
        return results;
    }
}
