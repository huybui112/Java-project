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
import model.Course;
import model.Process;

/**
 *
 * @author ASUS
 */
public class ProcessDAO extends DBConnect{
    public ProcessDAO(){}
    
    public boolean taoProcess(int idu, int idv)
    {
        String sql = "insert into process values (?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, idu);
            st.setInt(2, idv);
            st.setFloat(3, 0);
            int rs = st.executeUpdate();
            return rs > 0;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    
    public boolean updateProcess(int idu, int idv) 
    {
        Process p = this.findProcess(idu, idv);
        String sql = "Update process set res = ? where idv = ? and idu = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(3, idu);
            st.setInt(2, idv);
            float res = (float)(p.getProcess()+0.25);
            if(res>1){
                st.setFloat(1, 1);
            }
            else{
                st.setFloat(1, res);
            }
            int rs = st.executeUpdate();
            return rs > 0;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    
    public void resetProcess(int idu, int idc) 
    {
        String sql = "select idu, idv, idc from process inner join vocabularys on vocabularys.id = process.idv where idu = ? and idc = ?";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, idu);
            st.setInt(2, idc);
            ArrayList<Integer> arr = new ArrayList<>();
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                arr.add(rs.getInt("idv"));
            }
            String update = "Update process set res = 0 where idv = ? and idu = ?";
            for(Integer x : arr)
            {
                st = connection.prepareStatement(update);
                st.setInt(1, x);
                st.setInt(2, idu);
                st.execute();
            }
        }
        catch(SQLException ex)
        {
            System.out.println(ex);
        }
    }    
    
    public Process findProcess(int idu, int idv)
    {
        String sql = "Select * from process where idv = ? and idu = ?";
        Process p = null;
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, idv);
            st.setInt(2, idu);
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                p = new Process(rs.getInt("id"), rs.getInt("idu"),rs.getInt("idv"),rs.getFloat("res"));
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return p;
    }
}
