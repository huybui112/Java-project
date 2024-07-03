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
import model.Dictionary;

/**
 *
 * @author ASUS
 */
public class DictionaryDAO extends DBConnect{
    public DictionaryDAO()
    {
        super();
    }
    public ArrayList<Dictionary> topTenWord(int start, String search)
    {
        String sql = "Select * from dictionary where word like ? order by id offset ? rows fetch next 10 rows only";
        ArrayList<Dictionary> arr = new ArrayList<>();
        try 
        {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setNString(1, "%" + search + "%");
            st.setInt(2, start);
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                int id = rs.getInt("id");
                String word = rs.getNString("word");
                String mean = rs.getNString("mean");
                String pronun = rs.getNString("pronunciation");
                String pos = rs.getNString("partOfSpeech");
                String syn = rs.getNString("syn");
                String anton = rs.getNString("anton");
                arr.add(new Dictionary(id, word, mean, pronun, pos, syn, anton));
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e);
        }
        return arr;
    }
    public int getMaxPage(String search)
    {
        String sql = "Select count(word) as maxPage from dictionary where word like ?";
        int maxPage = 0;
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setNString(1, "%" + search + "%");
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                maxPage = rs.getInt("maxPage");
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return (maxPage % 10 == 0 && maxPage > 0? maxPage/10 - 1 : maxPage/10);
    }
        public ArrayList<Dictionary> top100Words(String loaiTu)
    {
        String sql = "select top 100 * from dictionary where partOfSpeech = ?";
        ArrayList<Dictionary> arr = new ArrayList<>();
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setNString(1, loaiTu);
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                Dictionary tumoi = new Dictionary();
                tumoi.setId(rs.getInt("id"));
                tumoi.setWord(rs.getNString("word"));
                tumoi.setMean(rs.getNString("mean"));
                tumoi.setPronunciation(rs.getNString("pronunciation"));
                tumoi.setPartOfSpeech(rs.getNString("partOfSpeech"));
                tumoi.setSynonym("");
                tumoi.setAntonym("");
                arr.add(tumoi);
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return arr;
    }
    public Dictionary layDictionaryBangWord(String word){
        String sql="select * from dictionary where word = ?";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setNString(1, word);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Dictionary t = new Dictionary(rs.getInt("id"),rs.getNString("word"), rs.getNString("mean"), rs.getNString("pronunciation"),rs.getNString("partOfSpeech"),rs.getNString("syn"),rs.getNString("anton"));
                return t;
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return null;
    }
    
    public boolean taoTuMoi(String word, String mean, String pronunciation, String partOfSpeech)
    {
        String sql = "insert into dictionary(word, mean, pronunciation, partOfSpeech) values(?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setNString(1, word);
            st.setNString(2, mean);
            st.setNString(3, pronunciation);
            st.setNString(4, partOfSpeech);
            int rs = st.executeUpdate();
            return rs > 0;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    
    public void xoaTuBangId(int id)
    {
        String sql = "Delete from dictionary where id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public Dictionary timTuBangId(int id)
    {
        String sql = "Select * from dictionary where id = ?";
        Dictionary dictionary = null;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                dictionary = new Dictionary();
                dictionary.setWord(rs.getNString("word"));
                dictionary.setMean(rs.getNString("mean"));
                dictionary.setPronunciation(rs.getNString("pronunciation"));
                dictionary.setPartOfSpeech(rs.getNString("partOfSpeech"));
                dictionary.setId(rs.getInt("id"));
                dictionary.setSynonym(rs.getNString("syn"));
                dictionary.setAntonym(rs.getNString("anton"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return dictionary;
    }
    
    public boolean taoTuMoi(String word, String mean, String pronunciation, String partOfSpeech, String syn, String anton)
    {
        String sql = "insert into dictionary(word, mean, pronunciation, partOfSpeech, syn, anton) values(?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setNString(1, word);
            st.setNString(2, mean);
            st.setNString(3, pronunciation);
            st.setNString(4, partOfSpeech);
            st.setNString(5, syn);
            st.setNString(6, anton);
            int rs = st.executeUpdate();
            return rs > 0;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    
    public boolean suaTuVungBangId(int id, String word, String mean, String pronunciation, String partOfSpeech, String syn, String anton)
    {
        String sql = "update dictionary set word = ?, mean = ?, pronunciation = ?, partOfSpeech = ?, syn = ?, anton = ? where id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setNString(1, word);
            st.setNString(2, mean);
            st.setNString(3, pronunciation);
            st.setNString(4, partOfSpeech);
            st.setNString(5, syn);
            st.setNString(6, anton);
            st.setInt(7, id);
            int rs = st.executeUpdate();
            return rs > 0;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
}
