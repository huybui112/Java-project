/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */
public class Dictionary {
    private int id;
    private String word, mean, pronunciation, partOfSpeech, synonym, antonym;
    public Dictionary()
    {
        
    }
    public Dictionary(int id, String w, String m, String p, String pos, String s, String a)
    {
        this.id = id;
        this.word = w;
        this.mean = m;
        this.pronunciation = p;
        this.partOfSpeech = pos;
        this.antonym = a;
        this.synonym = s;
    }

    public int getId() {
        return id;
    }

    public String getWord() {
        return word;
    }

    public String getMean() {
        return mean;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public String getSynonym() {
        return synonym;
    }

    public String getAntonym() {
        return antonym;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public void setSynonym(String synonym) {
        this.synonym = synonym;
    }

    public void setAntonym(String antonym) {
        this.antonym = antonym;
    }

    @Override
    public String toString() {
        return this.word;
    }
}
