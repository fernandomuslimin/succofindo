/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import Controller.LoginController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author NILAM
 */
public class MainClass extends Application {
    
    public Stage stage;
    Scene scene;
    public static MainClass instances;
    //public static EntityManagerFactory emf;
    
    public MainClass(){
        instances = this;
    }
    
    public static MainClass Instances(){
        return instances;
    }
    
    @Override
    public void start(Stage primaryStage) {
        
        showLogin();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    private void showLogin(){               
        FXMLLoader loader = new FXMLLoader();
        String pageURL = "/Form/Login.fxml";
        loader.setLocation(getClass().getResource(pageURL));
        try {
            scene = new Scene(loader.load());
            stage = new Stage();
            
            stage.setScene(scene);
            
            stage.show();
            stage.setOnCloseRequest(e->{
                //emf.close();
                Platform.exit();
                System.exit(0);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        LoginController controller = loader.getController();
        controller.setPrevStage(stage);
        
    }
}
