/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author NILAM
 */
public class DBConnect {
    
    private static Connection koneksi;
            
    public static Connection getKoneksi(){               
        if(koneksi == null){
            try {
                String url = "jdbc:mysql://localhost:3306/dipping?zeroDateTimeBehavior=convertToNull";
                String username = "root";
                String password = "";
                
                Class.forName("com.mysql.jdbc.Driver");
                koneksi = DriverManager.getConnection(url,username,password);
            } catch (ClassNotFoundException | SQLException e) {
                JOptionPane.showMessageDialog(null, "Koneksi Ke Server Database Gagal!");
            }
        }
        return koneksi;
    }
}
