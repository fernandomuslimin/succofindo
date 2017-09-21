/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author NILAM
 */
public class Methods {
    Stage prevStage;    
    //String username;
    
    public void checkUser(String username, String password){
        UIController UIC = new UIController();
        try {
            String loginpage = "/Form/Dashboard.fxml";
            String sql = "SELECT username,password FROM userid WHERE username = '"+username+"' AND password = '"+password+"'";
            Connection con = DBConnect.getKoneksi();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            if(rs.next()){
                UIC.CallUI(loginpage);
            } else{
                JOptionPane.showMessageDialog(null, "Login Failed");
            }
            
        } catch (Exception e) {
           
            e.printStackTrace();
        }       
    }
    
    
}
