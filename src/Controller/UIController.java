/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author NILAM
 */
public class UIController {
    public void CallUI(String fxml){
        try {                
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxml));
                Scene scene = new Scene(loader.load());
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
                System.out.println(fxml);                
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Failed to Open Scene");
                ex.printStackTrace();
                ex.getCause();
            }
    }
}
