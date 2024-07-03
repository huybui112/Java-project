/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author nhanv
 */
public class DBConnect {
    protected Connection connection;
    public DBConnect()
    {
        try {
            
            String url = "jdbc:sqlserver://DESKTOP-DSHHDU8\\SQLEXPRESS:1433;databaseName= WEBHOCTAP_1";
            // Thay đổi theo tài khoản mật khẩu trong trình quản trị csdl của mọi người
            String username = "sa";
            String password = "sa";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
    }
    
}
