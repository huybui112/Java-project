///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package model;
//
//import java.util.ArrayList;
//
///**
// *
// * @author ASUS
// */
//public class QuestionVoca {
//    private String word;
//    private ArrayList<String> dapan;
//
//    public QuestionVoca(String word, ArrayList<String> dapan) {
//        this.word = word;
//        this.dapan = new ArrayList<>();
//        this.dapan = dapan;
//    }
//
//    public String getWord() {
//        return word;
//    }
//
//    public ArrayList<String> getDapan() {
//        return dapan;
//    }
//}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author ASUS
 */
public class QuestionVoca {
    private int type;
    private Vocabulary vocabulary;
    private ArrayList<String> option;
    

    public QuestionVoca() {
    }

    public QuestionVoca(int type,Vocabulary vocabulary) {
        this.type=type;
        this.vocabulary = vocabulary;
        
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    
    public Vocabulary getVocabulary() {
        return vocabulary;
    }

    public void setVocabulary(Vocabulary vocabulary) {
        this.vocabulary = vocabulary;
    }

    public ArrayList<String> getOption() {
        return option;
    }

    public void setOptionV(ArrayList<String> option) {
        ArrayList<String> res= new ArrayList<>();
        Collections.shuffle(option);
        int c=0;
        for(String s:option){
            
            if(!s.trim().equalsIgnoreCase(this.vocabulary.getNghiaTV().trim())){
                res.add(s);
                c++;
            }
            if(c==3){
                break;
            }
        }
        res.add(this.vocabulary.getNghiaTV());
        ArrayList<String> lib = new ArrayList<>(Arrays.asList("Trường đại học","phương pháp","Xe máy","sử dụng"));
        while(res.size()<4){
            res.add(lib.get(res.size()));
        }
        this.option=res;
    }
    public void setOptionE(ArrayList<String> option) {
        ArrayList<String> res= new ArrayList<>();
        Collections.shuffle(option);
        int c=0;
        for(String s:option){
            
            if(!s.trim().equalsIgnoreCase(this.vocabulary.getWord().trim())){
                res.add(s);
                c++;
            }
            if(c==3){
                break;
            }
        }
        res.add(this.vocabulary.getWord());
        ArrayList<String> lib = new ArrayList<>(Arrays.asList("University","method","Motorbike","use"));
        while(res.size()<4){
            res.add(lib.get(res.size()));
        }
        this.option=res;
    }
    public boolean kiemtra1(String selection){
        if(this.vocabulary.getNghiaTV().equalsIgnoreCase(selection.trim())){
            return true;
        }
        return false;
    }
    public boolean kiemtra2(String selection){
        if(this.vocabulary.getWord().equalsIgnoreCase(selection.trim())){
            return true;
        }
        return false;
    }
}
