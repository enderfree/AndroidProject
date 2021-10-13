package team3.samuelandsebastian.androidproject.models;

import java.io.Serializable;
import java.util.List;

public class WordResult implements Serializable {
    private String partOfSpeech; //noun, adj, adv
    private String definition;
    private List<String> synonyms;
    private List<String> examples;

    public WordResult(String partOfSpeech, String definition, List<String> synonyms, List<String> examples) {
        this.partOfSpeech = partOfSpeech;
        this.definition = definition;
        this.synonyms = synonyms;
        this.examples = examples;
    }

    public WordResult() {} //needed for some things we called

    //getters (the composition here makes the getters facultative so we took them off since we don't use them)
    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public String getDefinition() {
        return definition;
    }

    public List<String> getSynonyms() {
        return synonyms;
    }

    public List<String> getExamples() {
        return examples;
    }
}
