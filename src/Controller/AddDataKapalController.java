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
        float gov;
        //String id;
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
        
    public void findVolume(){
        
        int db = (int)VesselController.getInstance().trim;
        System.out.println("Nilai trim = "+db);
        String sql = "SELECT volume FROM trim"+db+" WHERE sounding = ?";
        con = DBConnect.getKoneksi();
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, fSounding.getText());
            rs = pst.executeQuery();
            if(rs.next()){
                gov = rs.getFloat("volume");
                System.out.println("Nilai GOV = "+gov);
            }else{
                System.out.println("Data Not Found!");
            }
        } catch (Exception e) {
        }
    }
    
    public void insertData(ActionEvent event){
        con = DBConnect.getKoneksi();
        try {
            
            String add = "INSERT INTO tank(id, notank, sounding, gov, temp, density, forward, after, list) VALUES ((SELECT id FROM kapal WHERE id = ?),?,?,?,?,?,?,?,?) ";
            
            pst = con.prepareStatement(add);
            pst.setString(1, VesselController.instance.cellID);
            pst.setString(2, fNoTank.getText());
            pst.setString(3, fSounding.getText());
            pst.setFloat(4, gov);
            pst.setString(5, fTemp.getText());
            pst.setString(6, fDensity.getText());
            pst.setString(7,fFoward.getText());
            pst.setString(8, fAfter.getText());
            pst.setString(9, fList.getText());             
            int r = pst.executeUpdate();
            
             if(r>0){                             
                //FXDialogs.showInformation("Query Information", "Your data has been successfully aded!");
                Node b = (Node) event.getSource();
                Stage a = (Stage) b.getScene().getWindow();
                a.close();
                VesselController.getInstance().loadDataKapal();
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
        findVolume();
        insertData(event);        
    }
}    

