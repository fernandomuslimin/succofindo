/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.TankResultsModel;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
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
      /*==============================*/
    @FXML
    private void btnReset(ActionEvent event) {
    }

    @FXML
    private void btnSave(ActionEvent event) {
        
        con = DBConnect.getKoneksi();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Form/Vessel.fxml"));
            Parent root = loader.load();
            VesselController vc = (VesselController) loader.getController(); 
            TankResultsModel trm = (TankResultsModel) vc.tblNamaKapal.getSelectionModel().getSelectedItem();
            //int id = trm.getId();
            //String update = "UPDATE tank SET forward=?, after=?, list=?, notank=?, sounding=?, temp=?, density=? WHERE notank='"+id+"'";
            String add = "INSERT INTO tank(id, notank, sounding, temp, density, forward, after, list) VALUES ((SELECT id FROM kapal WHERE id = ?),?,?,?,?,?,?,?) ";
            
            pst = con.prepareStatement(add);
            pst.setString(1, vc.cellID);
            pst.setString(2, fNoTank.getText());
            pst.setString(3, fSounding.getText());
            pst.setString(4, fTemp.getText());
            pst.setString(5, fDensity.getText());
            pst.setString(6,fFoward.getText());
            pst.setString(7, fAfter.getText());
            pst.setString(8, fList.getText());             
            int r = pst.executeUpdate();
             
             if(r>0){                             
                //FXDialogs.showInformation("Query Information", "Your data has been successfully aded!");
                Node b = (Node) event.getSource();
                Stage a = (Stage) b.getScene().getWindow();
                a.close();
             }
             
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            e.getCause();
        }
        
        
        
    }
    
    public void addData(ActionEvent event){       
        
    }
    
}    

