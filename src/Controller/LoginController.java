/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
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
 * @author Fernando M
 */
public class LoginController implements Initializable {
    Stage prevStage;
    @FXML
    private TextField fUsername;
    @FXML
    private PasswordField fPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnLogin(ActionEvent event) {        
       Methods MT = new Methods();
       String username = fUsername.getText();
       String password = fPassword.getText();
       MT.checkUser(username,password);
       Node node = (Node)event.getSource();
       Stage stage = (Stage) node.getScene().getWindow();
       stage.close();
    }

    @FXML
    private void btnCancel(ActionEvent event) {
       System.exit(0);
    }
    
    public void setPrevStage(Stage prevStage){
        this.prevStage = prevStage;
    }
}
