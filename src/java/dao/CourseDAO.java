/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import model.Course;
import model.Vocabulary;

/**
 *
 * @author nhanv
 */
public class CourseDAO extends DBConnect {

    public CourseDAO() {
    }
    
    //Thêm khóa học mới cho người dùng co id=n; thêm thành công trả về true , không thành công trả về false
    public boolean taoKhoaHoc(String name,String describe,Date time_create){
        String sql="Insert into courses values(?,?,?)";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setNString(1, name);
            st.setNString(2, describe);
            java.sql.Date sqlDate = java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(time_create));
            st.setDate(3, sqlDate);
            int rs = st.executeUpdate();
            
            if(rs>0){
                //sau khi thêm khóa học thành công, trigger sẽ tự động tạo 1 role mới cho học và người dùng.
                return true;
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return false;
    }
    
    
    // Xóa khóa học với id = n , xóa thành công trả về true, không thành công trả về false
    public boolean xoaKhoaHoc(int id){
        VocabularyDAO vocabularyDAO= new VocabularyDAO();
//        boolean kt = vocabularyDAO.xoaTuMoiBangIdc(id);
        String sql="DELETE FROM courses WHERE id =?";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            int rs = st.executeUpdate();
            if(rs>0){
                return true;
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return false;
    }
    
    // cập nhật thông tin name và miêu tả của khóa học có id=n , trả về true khi cập nhật thành công, false khi thất bại
    public boolean capNhatKhoaHoc(int id, String name,String describe){
        String sql="Update courses "+
                "Set course_name=? ,describe= ? "+
                "where id=?";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            
            st.setNString(1, name);
            st.setNString(2, describe);
            st.setInt(3, id);
            int rs = st.executeUpdate();
            if(rs>0){
                return true;
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return false;
    }
    
    public boolean capNhatTitle(int id, String name){
        String sql="Update courses set course_name = ? where id = ?";
                
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setNString(1, name);
            st.setInt(2, id);
            int rs = st.executeUpdate();
            if(rs>0){
                System.out.println("ok");
                return true;
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return false;
    }

    // tìm khóa học có id = n
    public Course timKhoaHocBangId(int id) {
        String sql = "Select * from courses where id=?";
        Course course = new Course();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                course.setId(rs.getInt("id"));
                course.setTenKH(rs.getNString("course_name"));
                course.setMieuTaKh(rs.getNString("describe"));
                java.sql.Date sqlDate = rs.getDate("time_create");
                java.util.Date utilDate = new java.util.Date(sqlDate.getTime());
                course.setThoiGianTaoKH(utilDate);

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return course;
    }

   
    

//    // cập nhật lại tiến trình của của khóa học có id=n sau mỗi lần ôn tập
//    public boolean resetTienTrinh(int id) {
//        
//        try {
//            VocabularyDAO vocabularyDAO = new VocabularyDAO();
//            ArrayList<Vocabulary> listVoca = vocabularyDAO.timTatCaTuMoiBangCourse(id);
//            for(Vocabulary v:listVoca){
//                vocabularyDAO.capNhatTienTrinhTuMoi(v.getId(), 0);
//            }
//            
//            return true;
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }
//        return false;
//    }

    
//    public boolean capNhatTienTrinh(int id){
//        String sql="Update courses "+
//                "Set process=? where id=? ";
//        try{
//            VocabularyDAO vocabularyDAO= new VocabularyDAO();
//            ArrayList<Vocabulary> list= vocabularyDAO.timTatCaTuMoiBangCourse(id);
//            int sum = 0 ;
//            PreparedStatement st = connection.prepareStatement(sql);
//            st.setInt(1, sum);
//            st.setInt(2, id);
//            boolean kt = st.execute();
//            return true;
//        }catch(SQLException ex){
//            System.out.println(ex);
//        }
//        return false;
//    }
//    public void updateProcess(int res,int id){
//        String sql="Update courses "+
//                "Set process=? where id=? ";
//        try{
//            PreparedStatement st = connection.prepareStatement(sql);
//            st.setInt(1, res);
//            st.setInt(2, id);
//            st.executeUpdate();
//        }catch(SQLException ex){
//            System.out.println(ex);
//        }
//    }
    public Course findCourseByName(String name)
    {
        String sql = "Select * from courses where course_name = ?";
        Course course = null;
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setNString(1, name);
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                course = new Course(rs.getNString("course_name"), rs.getNString("describe"), rs.getDate("time_create"));
                course.setId(rs.getInt("id"));
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return course;
    }
    
    public ArrayList<Course> getAllCourse()
    {
        String sql = "select * from courses";
        ArrayList<Course> arr = new ArrayList<>();
        try
        {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                Course course = new Course(rs.getNString("course_name"), rs.getNString("describe"), rs.getDate("time_create"));
                course.setId(rs.getInt("id"));
                arr.add(course);
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return arr;
    }
    
    public int getAllNumCourse()
    {
        String sql = "select count(*) as res from courses";
        int res = 0;
        try
        {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                res = rs.getInt("res");
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return (res % 5 == 0 && res > 0 ? res/5-1 : res/5);       
    }
    
    public ArrayList<Course> getFirstFiveCourse(int page, String filter)
    {
        String sql;
        if(filter.equals("all"))
            sql = "select * from courses order by course_name offset ? rows fetch next 5 rows only";
        else if(filter.equals("newest"))
            sql = "select * from courses order by time_create desc, id desc offset ? rows fetch next 5 rows only";
        else
            sql = "select * from courses order by time_create, id offset ? rows fetch next 5 rows only";
        ArrayList<Course> arr = new ArrayList<>();
        try
        {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, page*5);
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setTenKH(rs.getNString("course_name"));
                course.setMieuTaKh(rs.getNString("describe"));
                course.setThoiGianTaoKH(rs.getDate("time_create"));
                arr.add(course);
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return arr;
    }
}
