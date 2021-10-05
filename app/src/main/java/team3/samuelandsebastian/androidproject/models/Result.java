package team3.samuelandsebastian.androidproject.models;

import java.util.ArrayList;

public class Result {
    private String partOfSpeech; //noun, adj, adv
    private String definition;
    private ArrayList<String> synonyms;
    private ArrayList<String> examples;

    public Result(String partOfSpeech, String definition, ArrayList<String> synonyms, ArrayList<String> examples) {
        this.partOfSpeech = partOfSpeech;
        this.definition = definition;
        this.synonyms = synonyms;
        this.examples = examples;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public ArrayList<String> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(ArrayList<String> synonyms) {
        this.synonyms = synonyms;
    }

    public ArrayList<String> getExamples() {
        return examples;
    }

    public void setExamples(ArrayList<String> examples) {
        this.examples = examples;
    }
}
