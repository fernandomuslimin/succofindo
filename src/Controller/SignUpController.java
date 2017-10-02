/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author NILAM
 */
public class SignUpController implements Initializable {

    @FXML
    private TextField jNama;
    @FXML
    private TextField jNpp;
    @FXML
    private TextField jUsername;
    @FXML
    private PasswordField jPassword;

    //=============Define Variable============
    Connection con;
    PreparedStatement pst;
    UIController uic = new UIController();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnSignUp(ActionEvent event) {
        con = DBConnect.getKoneksi();
        String sql = "INSERT INTO userid (npp,name,username,password) VALUES(?,?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, jNpp.getText());
            pst.setString(2, jNama.getText());
            pst.setString(3, jUsername.getText());
            pst.setString(4, jPassword.getText());
            pst.execute();
            
            FXDialogs.showInformation("Sign Up Information", "User Berhasil Didaftarkan");

            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();          
            stage.close();                
            uic.CallUI("/Form/Login.fxml");
          
        } catch (SQLException e) {
            FXDialogs.showError("Sign Up Infomation", "User Gagal Didaftarkan!");
            System.out.println(e);
        }
    }

    @FXML
    private void btnCancel(ActionEvent event) {
        
        uic.CallUI("/Form/Login.fxml");
    }
    
}
