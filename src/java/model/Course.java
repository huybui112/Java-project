/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dao.VocabularyDAO;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author nhanv
 */
public class Course {
    private int id;
    private String tenKH;
    private String mieuTaKh;
    private Date thoiGianTaoKH;

    public Course() {
    }

    public Course(String tenKH, String mieuTaKh, Date thoiGianTaoKH) {
        this.tenKH = tenKH;
        this.mieuTaKh = mieuTaKh;
        this.thoiGianTaoKH = thoiGianTaoKH;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getMieuTaKh() {
        return mieuTaKh;
    }

    public void setMieuTaKh(String mieuTaKh) {
        this.mieuTaKh = mieuTaKh;
    }

    public Date getThoiGianTaoKH() {
        return thoiGianTaoKH;
    }

    public void setThoiGianTaoKH(Date thoiGianTaoKH) {
        this.thoiGianTaoKH = thoiGianTaoKH;
    }

    public int getTongSoTu(){
        VocabularyDAO vocabularyDAO = new VocabularyDAO();
        ArrayList<Vocabulary> list = vocabularyDAO.timTatCaTuMoiBangCourse(this.id);
        return list.size();
    }
    
}
