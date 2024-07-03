/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */
public class Blog {
    private String content;
    private int id;
    private Course course;
    public Blog()
    {}
    public Blog(int i, String content, Course c)
    {
        this.id = i;
        this.content = content;
        this.course = c;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
    @Override
    public String toString()
    {
        return this.course.getTenKH();
    }
}
