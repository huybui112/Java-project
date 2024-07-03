/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Blog;
import model.blogQuestion;

/**
 *
 * @author ADMIN
 */
public class blogQuestionDAO extends DBConnect{
    public blogQuestionDAO(){
    }
    public void taoQuestionBlog(String question, String a,String b, String c, String d, String answer, int idb){
        String sql="Insert into blogQuestions values(?,?,?,?,?,?,?)";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setNString(1, question);
            st.setNString(2, a);
            st.setNString(3, b);
            st.setNString(4, c);
            st.setNString(5, d);
            st.setNString(6, answer);
            st.setInt(7, idb);
            st.executeUpdate();
        }catch(SQLException ex){
            
        }
    }
    public ArrayList<blogQuestion> layBlogQuestionBangIdb(int idb){
        String sql="select * from blogQuestions where idb = ?";
        ArrayList<blogQuestion> a = new ArrayList<>();
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, idb);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                blogQuestion t = new blogQuestion(rs.getInt("id"),rs.getNString("quesion"), rs.getNString("a"), rs.getNString("b"),rs.getNString("c"),rs.getNString("d"),rs.getNString("answer"),rs.getInt("idb"));
                a.add(t);
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return a;
    }
    
    public void xoaBlogQuestion(int id){
        
        String sql="DELETE FROM blogQuestions WHERE idb =?";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            
        }catch(SQLException ex){
            
        }
    }
    
    public void editBlogQuestion(int id, String question, String a, String b, String c, String d, String answer) {
        String sql = "UPDATE blogQuestions " +
                     "SET quesion = ?, a = ?, b = ?, c = ?, d = ?, answer = ? " +
                     "WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, question);
            st.setString(2, a);
            st.setString(3, b);
            st.setString(4, c);
            st.setString(5, d);
            st.setString(6, answer);
            st.setInt(7, id);
            st.executeUpdate();
            System.out.println("ok");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void deleteBlogQuestion(int id){
        String sql="DELETE FROM blogQuestions WHERE id =?";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            
        }catch(SQLException ex){
            
        }
    }
}
