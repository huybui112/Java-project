/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Blog;
import model.Course;

/**
 *
 * @author ASUS
 */
public class BlogDAO extends DBConnect{
    public BlogDAO()
    {
        super();
    }
    public boolean taoBlog(String content, int idCourse)
    {
        String sql = "insert into blog values (?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setNString(1, content);
            st.setInt(2, idCourse);
            int rs = st.executeUpdate();
            return rs > 0;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    
    public Blog findBlogByIdCourse(int idc)
    {
        String sql = "Select * from blog where idc = ?";
        Blog blog = null;
        CourseDAO courseDao = new CourseDAO();
        Course course = courseDao.timKhoaHocBangId(idc);
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, idc);
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                blog = new Blog(rs.getInt("id"), rs.getNString("content"), course);
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return blog;
    }
    
    public boolean updateBlog(String content, int idc)
    {
        String sql = "Update blog set content = ? where idc = ?";
        try
        {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setNString(1, content);
            st.setInt(2, idc);
            int rs = st.executeUpdate();
            return rs > 0;
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return false;
    }
    
    public Blog findBlogById(int id)
    {
        String sql = "Select * from blog where id = ?";
        Blog blog = null;
        CourseDAO courseDao = new CourseDAO();
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                blog = new Blog(rs.getInt("id"), rs.getNString("content"), courseDao.timKhoaHocBangId(rs.getInt("idc")));
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return blog;        
    }
}
