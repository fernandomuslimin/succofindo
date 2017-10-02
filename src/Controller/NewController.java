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
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tesa
 */
public class NewController implements Initializable {
    
    @FXML
    private TextField fNamaKapal;
    private TextField fIdKapal;
    @FXML
    private TextField fSeaCondition;
    @FXML
    private TextField fBL;
    private Button clicked;
    private GridPane gridPane;
    @FXML
    private TextField fFoward;
    @FXML
    private TextField fAfter;
    @FXML
    private TextField fList;
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
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
    
    private void btnReset(ActionEvent event) {
        fNamaKapal.setText("");
        fIdKapal.setText("");
        fSeaCondition.setText("");
    }

    @FXML
    private void btnCancel(ActionEvent event) {
        Stage x = (Stage) ((Node) event.getSource()).getScene().getWindow();
        x.close();
    }
    
    @FXML
    private void btnAdd(ActionEvent event) {
        float trim = calculateTrim();
        String add = "INSERT INTO kapal(namakapal, seacondition, bl, forward, after, list, trim) VALUES (?,?,?,?,?,?,?)";
        con = DBConnect.getKoneksi();
        
        try {
             pst = con.prepareStatement(add);
             pst.setString(1, fNamaKapal.getText());
             pst.setString(2, fSeaCondition.getText());
             pst.setString(3, fBL.getText());
             pst.setString(4, fFoward.getText());
             pst.setString(5, fAfter.getText());
             pst.setString(6, fList.getText());
             pst.setFloat(7, trim);
             int r = pst.executeUpdate();
             if(r>0){                
                Node b = (Node) event.getSource();
                Stage a = (Stage) b.getScene().getWindow();
                a.close();
                VesselController.getInstance().loadTableNamaKapal();
             }
             
             pst.close();
             
        } catch (SQLException e) {
            System.out.println(e);           
            FXDialogs.showInformation("Data Information", "Data insert was failed!");
        }
    }
    
    public float calculateTrim() {       
        float trim;
        float forward = Float.parseFloat(fFoward.getText());
        float after = Float.parseFloat(fAfter.getText());
        
        trim = forward - after;
        
        return trim;
    }
    
    public void addNewData(TankResultsModel tankResults){
        ObservableList<TankResultsModel> data = FXCollections.observableArrayList();
        data.add(tankResults);
    }
}
