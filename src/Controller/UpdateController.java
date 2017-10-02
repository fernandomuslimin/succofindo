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
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author NILAM
 */
public class UpdateController implements Initializable {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    @FXML
    private GridPane gridPane;
    @FXML
    private TextField fNoTank;
    @FXML
    private TextField fSounding;
    @FXML
    private TextField fTemp;
    @FXML
    private TextField fDensity;
    
    
    //============Define Variable============
    TankResultsModel tank = VesselController.getInstance().tableTank.getSelectionModel().getSelectedItem();
    TankResultsModel namakapal = VesselController.getInstance().tblNamaKapal.getSelectionModel().getSelectedItem();
    String notank;
    String sounding;
    String temp;
    String density;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getData();
    }    
    
    public void getData(){
        if(tank !=null && namakapal != null){
            con = DBConnect.getKoneksi();
            String sql = "Select notank, sounding, temp, density From tank where Id=? and notank = ?";
            try {
               pst = con.prepareStatement(sql);
               pst.setInt(1, namakapal.getId());
               pst.setString(2, tank.getNotank());
               rs = pst.executeQuery();
               if(rs.next()){
                   notank = rs.getString("notank");
                   sounding = rs.getString("sounding");
                   temp = rs.getString("temp");
                   density = rs.getString("density");

                   fNoTank.setText(notank);
                   fSounding.setText(sounding);
                   fTemp.setText(temp);
                   fDensity.setText(density);
               }
            } catch (SQLException e) {
                 System.out.println(e);
            }
        } else{
            System.out.println("Table is not selected");
        }
        
    }

    @FXML
    private void btnUpdate(ActionEvent event) {
                
        
        int id = tank.getId();
        String update = "UPDATE tank SET notank = ?, sounding = ?, temp = ?, density = ? WHERE id = ? AND notank = ?";
       
        con = DBConnect.getKoneksi();
        try {
            pst = con.prepareStatement(update);
            pst.setString(1, fNoTank.getText());
            pst.setString(2, fSounding.getText());
            pst.setString(3, fTemp.getText());
            pst.setString(4, fDensity.getText());
            pst.setInt(5, id);
            pst.setString(6, notank);
            int res = pst.executeUpdate();
            System.out.println(update);
            if(res > 0){
                System.out.println(notank);
                System.out.println(id);
                FXDialogs.showInformation("Update Information", "Update Successfull");
                Node b = (Node) event.getSource();
                Stage a = (Stage) b.getScene().getWindow();
                a.close();                
               
                VesselController.getInstance().loadDataKapal();
                
            } 
            
            pst.close();
            rs.close();
            
            
        } catch (SQLException e) {
            FXDialogs.showInformation("Update Information", "Update Failed");
            System.out.println(e);
        }
    }

    @FXML
    private void btnCancel(ActionEvent event) {
      Stage x = (Stage) ((Node) event.getSource()).getScene().getWindow();
      x.close();
    }
}
