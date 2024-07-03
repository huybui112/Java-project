/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import model.Course;
import model.Vocabulary;

/**
 *
 * @author nhanv
 */
public class VocabularyDAO extends DBConnect {

    public VocabularyDAO() {
    }

    //Tạo một từ mới 
    public boolean taoTuMoi(String word, String nghiaTV, String phatAmTV, String ghiChu, int courseId) {
        String sql = "insert into vocabularys values(?,?,?,?,?) ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setNString(1, word);
            st.setNString(2, nghiaTV);
            st.setNString(3, phatAmTV);
            st.setNString(4, ghiChu);
            st.setInt(5, courseId);
            int rs = st.executeUpdate();
            return rs > 0;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false; //// Nếu không tạo được vocabulary thì trả về false, tạo được trả về true
    }

    // cập nhật lại các thay đổi cho từ mới có id= ?
    public boolean capNhatTuMoi(int id,String word, String nghiaTV, String phatAmTV, String ghiChu) {
        String sql = "UPDATE vocabularys "
                + "SET word = ?, mean = ?, pronunciation = ?, note = ?"
                + " WHERE id = ?";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setNString(1, word);
            st.setNString(2, nghiaTV);
            st.setNString(3, phatAmTV);
            st.setNString(4, ghiChu);
            st.setInt(5, id);
            int rs = st.executeUpdate();
            return true;
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return false;
    }
    
    // cập nhật lại tiến trình của từ mới sau mỗi lần trả lời câu hỏi 
    public boolean capNhatTienTrinhTuMoi(int id,float tienTrinhMoi) {
        String sql = "UPDATE vocabularys "
                + " SET process = ?"
                + " WHERE id = ?;";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            if(tienTrinhMoi>=1){
                tienTrinhMoi=1;
            }
            st.setFloat(1, tienTrinhMoi);
            st.setInt(2, id);
            int rs = st.executeUpdate();
            return true;
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return false;
    }
    
    // xóa từ mới bằng Id
    public boolean xoaTuMoiBangId(int id){
        String sql = "DELETE FROM vocabularys WHERE id = ?";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            int rs = st.executeUpdate();
            return true;
        }catch(SQLException ex){
            System.out.println("ex");
        }
        return false;
    }
    
    // lấy từ mới của khóa học có id=n
    public boolean xoaTuMoiBangIdc(int id){
        String sql = "DELETE FROM vocabularys WHERE idc = ?";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            int rs = st.executeUpdate();
            return true;
        }catch(SQLException ex){
            System.out.println("ex");
        }
        return false;
    }
    
    // 
    public Vocabulary timTuMoiBangId(int id){
        String sql= "Select * from vocabularys where id=?";
        Vocabulary tumoi = new Vocabulary();
        try{
            PreparedStatement st =connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                tumoi.setId(rs.getInt("id"));
                tumoi.setWord(rs.getNString("word"));
                tumoi.setNghiaTV(rs.getNString("mean"));
                tumoi.setPhatAmTV(rs.getNString("pronunciation"));
                tumoi.setGhiChu(rs.getNString("note"));
                CourseDAO courseDAO= new CourseDAO();
                Course course = courseDAO.timKhoaHocBangId(rs.getInt("idc"));
                tumoi.setCourse(course);
                
            }
        }catch(SQLException ex){
            
        }
        return tumoi; 
    }
    
    public Vocabulary timTuMoiBangWord(String word, int idc){
        String sql= "Select * from vocabularys where word=? and idc = ?";
        Vocabulary tumoi = new Vocabulary();
        try{
            PreparedStatement st =connection.prepareStatement(sql);
            st.setNString(1, word);
            st.setInt(2, idc);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                tumoi.setId(rs.getInt("id"));
                tumoi.setWord(rs.getNString("word"));
                tumoi.setNghiaTV(rs.getNString("mean"));
                tumoi.setPhatAmTV(rs.getNString("pronunciation"));
                tumoi.setGhiChu(rs.getNString("note"));
                CourseDAO courseDAO= new CourseDAO();
                Course course = courseDAO.timKhoaHocBangId(rs.getInt("idc"));
                tumoi.setCourse(course);
                
            }
        }catch(SQLException ex){
            
        }
        return tumoi; 
    }
    
    //lấy tất cả từ mới có idc=n ; tức là lấy tất cả các từ mới của một khóa học có id=n
    public ArrayList<Vocabulary> timTatCaTuMoiBangCourse(int idc){
        String sql= "Select * from vocabularys where idc=?";
        
        ArrayList<Vocabulary> list =new ArrayList<>();
        try{
            PreparedStatement st =connection.prepareStatement(sql);
            st.setInt(1, idc);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                Vocabulary tumoi = new Vocabulary();
                tumoi.setId(rs.getInt("id"));
                tumoi.setWord(rs.getNString("word"));
                tumoi.setNghiaTV(rs.getNString("mean"));
                tumoi.setPhatAmTV(rs.getNString("pronunciation"));
                tumoi.setGhiChu(rs.getNString("note"));
                CourseDAO courseDAO= new CourseDAO();
                Course course = courseDAO.timKhoaHocBangId(rs.getInt("idc"));
                tumoi.setCourse(course);
                list.add(tumoi);
                
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return list; 
    }
    public void capNhatTienTrinhTuMoi(String tu,float tienTrinhMoi) {
        String sql = "UPDATE vocabularys "
                + " SET process = ?"
                + " WHERE word = ? or mean = ?;";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            if(tienTrinhMoi>=1){
                tienTrinhMoi=1;
            }
            st.setFloat(1, tienTrinhMoi);
            st.setString(2, tu);
            st.setString(3, tu);
            st.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }
    
    public ArrayList<Vocabulary> top5000Words()
    {
        String sql = "select top 5000 from vocabularys";
        ArrayList<Vocabulary> arr = new ArrayList<>();
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                Vocabulary tumoi = new Vocabulary();
                tumoi.setId(rs.getInt("id"));
                tumoi.setWord(rs.getNString("word"));
                tumoi.setNghiaTV(rs.getNString("mean"));
                tumoi.setPhatAmTV(rs.getNString("pronunciation"));
                tumoi.setGhiChu(rs.getNString("note"));
                CourseDAO courseDAO= new CourseDAO();
                Course course = courseDAO.timKhoaHocBangId(rs.getInt("idc"));
                tumoi.setCourse(course);
                arr.add(tumoi);
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return arr;
    }
}
