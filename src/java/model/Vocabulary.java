/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author nhanv
 */
public class Vocabulary {
    private int id;
    private String word;
    private String nghiaTV;
    private String phatAmTV;
    private String ghiChu;
    private Course course;

    public Vocabulary() {
    }

    public Vocabulary(String word, String nghiaTV, String phatAmTV, String ghiChu, Course course) {
        this.word = word;
        this.nghiaTV = nghiaTV;
        this.phatAmTV = phatAmTV;
        this.ghiChu = ghiChu;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getNghiaTV() {
        return nghiaTV;
    }

    public void setNghiaTV(String nghiaTV) {
        this.nghiaTV = nghiaTV;
    }

    public String getPhatAmTV() {
        return phatAmTV;
    }

    public void setPhatAmTV(String phatAmTV) {
        this.phatAmTV = phatAmTV;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
