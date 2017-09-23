/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author NILAM
 */
public class Methods {
    
    public void checkUser(String username, String password){
        UIController UIC = new UIController();
        try {
            String loginpage = "/Form/Dashboard.fxml";
            String sql = "SELECT username,password FROM userid WHERE username = '"+username+"' AND password = '"+password+"'";
            Connection con = DBConnect.getKoneksi();
            Statement st = con.createStatement();
            try (ResultSet rs = st.executeQuery(sql)) {
                if(rs.next()){
                    UIC.CallUI(loginpage);
                } else{
                    JOptionPane.showMessageDialog(null, "Login Failed");
                }
            }
            
        } catch (HeadlessException | SQLException e) {
           
            e.printStackTrace();
        }       
    }
    
    
}
