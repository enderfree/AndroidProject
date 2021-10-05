package team3.samuelandsebastian.androidproject.models;

import java.util.ArrayList;

public class DataModel {
    private String word;
    private ArrayList<Result> results;

    public DataModel(String word, ArrayList<Result> results) {
        this.word = word;
        this.results = results;
    }

    public String getWord() {
        return word;
    }

    public ArrayList<Result> getResults() {
        return results;
    }
}
