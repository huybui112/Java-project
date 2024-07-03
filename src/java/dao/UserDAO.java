/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.User;

/**
 *
 * @author nhanv
 */
public class UserDAO extends DBConnect{

    public UserDAO() {
    }
    
    // tìm user với username, pass
    public User timUser(String username,String pass){
        String sql="Select * from users where username=? AND pass=? ";
        User user = null;
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setNString(1, username);
            st.setNString(2, pass);
            ResultSet rs =st.executeQuery();
            while(rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getNString("username"));
                user.setPass(rs.getNString("pass"));
                user.setEmail(rs.getNString("email")); 
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return user;// Nếu không tìm thấy sẽ trả về user có  id = 0;
    }
    
    // tìm id của user bằng username
    public int timUserId(String username){
        String sql="Select id from users where username=?";
        int id=0;
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setNString(1, username);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                id=rs.getInt("id");
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return id;
    }
    
    // Cập nhật lại mật khẩu,email cho user co id=n
    public boolean capNhatUser(int id,String newPass,String email){
        String sql = "Update users "
                +" set pass=? ,email=? "
                +" where id=?";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setNString(1,newPass);
            st.setNString(2, email);
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
    
    // tìm user bằng Id
    public User timUserBangId(int id){
        String sql="Select * from users where id=? ";
        User user = null;
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs =st.executeQuery();
            while(rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getNString("username"));
                user.setPass(rs.getNString("pass"));
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return user;// Nếu không tìm thấy sẽ trả về user có  id = 0;
    }
    // tìm user bằng email
    public User timUserBangEmail(String email){
        String sql="Select * from users where email=? ";
        User user = null;
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setNString(1, email);
            ResultSet rs =st.executeQuery();
            while(rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getNString("username"));
                user.setPass(rs.getNString("pass"));
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return user;// Nếu không tìm thấy sẽ trả về user có  id = 0;
    }
    
    
    // tạo mới một user
    public boolean taoUser(String username,String pass,String email){
        String sql="insert into users values(?,?,?)";
        
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setNString(1, username);
            st.setNString(2,pass);
            st.setNString(3,email);
            int rs =st.executeUpdate();
            if(rs>0){
                return true;
            }else{
                return false;
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return false;// Nếu không tạo được user thì trả về false, tạo được trả về true
    }
    
    public boolean kiemTraTonTai1(String username, String email)
    {
        String sql = "select count(username) as NumOfUser, count(email) as NumOfEmail from users where username = ? or email = ?";
        try
        {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setNString(1, username);
            st.setNString(2, email);
            ResultSet rs = st.executeQuery();
            int NumOfUser = 0, NumOfEmail = 0;
            while(rs.next())
            {
                NumOfUser = rs.getInt("NumOfUser");
                NumOfEmail = rs.getInt("NumOfEmail");
            }
            return !(NumOfEmail > 0 || NumOfUser > 0);
        }
        catch(SQLException e)
        {
            return false;
        }
    }
    public boolean kiemTraTonTai2(String username, String email)
    {
        String sql = "Select count(username) as NumOfAccount from users where username = ? and email = ?";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setNString(1, username);
            st.setNString(2, email);
            ResultSet rs = st.executeQuery();
            int NumOfAccount = 0;
            while(rs.next())
            {
                NumOfAccount = rs.getInt("NumOfAccount");
            }
            return NumOfAccount == 1;
        }
        catch(Exception e)
        {
            return false;
        }
    }
}
