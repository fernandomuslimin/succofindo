/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author NILAM
 */
public class AddDataKapalController implements Initializable {

    @FXML
    private GridPane gridPane;
    @FXML
    private TextField fFoward;
    @FXML
    private TextField fAfter;
    @FXML
    private TextField fNoTank;
    @FXML
    private TextField fSounding;
    @FXML
    private TextField fTemp;
    @FXML
    private TextField fDensity;
    @FXML
    private TextField fList;
      
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     /*===========Declaration============*/
        Connection con;
        Statement st;
        PreparedStatement pst;
        ResultSet rs;   
        float trim;
      /*==============================*/
        
    public static AddDataKapalController instance;
    
    public AddDataKapalController(){
        instance = this;
    }
    
    public static AddDataKapalController getInstance(){
        return instance;
    }    
        
    @FXML
    private void btnReset(ActionEvent event) {
    }
    
    public void findVolume(ActionEvent event){        
        VesselController.getInstance().loadDataKapal();
        
        float gov = 0;
        int db = (int) VesselController.getInstance().trim;
        String notank = fNoTank.getText();
        String id = VesselController.getInstance().cellID;
        
        String sql = "SELECT volume FROM trim"+db+" WHERE sounding = (SELECT sounding FROM tank WHERE id = ? AND notank = ?)";
        String insGov = "UPDATE tank SET gov = ? WHERE id = ? AND notank = ?";
       
        con = DBConnect.getKoneksi();
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, notank);
            rs = pst.executeQuery();
            if(rs.next()){
                gov = rs.getFloat("volume");
            } else {
                System.out.println("data not found");
            }
            
            pst.close();
            rs.close();
            
        } catch (SQLException e) {
            System.out.println(e);
            e.getCause();
            e.printStackTrace();
        }
        
        try {
            pst = con.prepareStatement(insGov);
            pst.setFloat(1, gov);
            pst.setString(2, id);
            pst.setString(3, notank);
            int res = pst.executeUpdate();
            if (res>0){
                Node b = (Node) event.getSource();
                Stage a = (Stage) b.getScene().getWindow();
                a.close();                
                VesselController.getInstance().loadDataKapal();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void insertData(){
        
        con = DBConnect.getKoneksi();
        try {
            
            String add = "INSERT INTO tank(id, notank, sounding, temp, density) VALUES ((SELECT id FROM kapal WHERE id = ?),?,?,?,?) ";
            
            pst = con.prepareStatement(add);
            pst.setString(1, VesselController.instance.cellID);
            pst.setString(2, fNoTank.getText());
            pst.setString(3, fSounding.getText());
            pst.setString(4, fTemp.getText());
            pst.setString(5, fDensity.getText());
              
            int r = pst.executeUpdate();
            
             if(r>0){                             
                FXDialogs.showInformation("Query Information", "Your data has been successfully aded!");                
             }
             
             pst.close();
                                      
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            e.getCause();
        }
    }
    
    @FXML
    private void btnSave(ActionEvent event) {      
       insertData(); 
       findVolume(event);
    }
}    

