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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
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
    }
    
    @FXML
    private void btnAdd(ActionEvent event) {
        String add = "INSERT INTO kapal(namakapal, seacondition, bl) VALUES (?,?,?)";
        con = DBConnect.getKoneksi();
        try {
             pst = con.prepareStatement(add);
             pst.setString(1, fNamaKapal.getText());
             pst.setString(2, fSeaCondition.getText());
             pst.setString(3, fBL.getText());
             int r = pst.executeUpdate();
             if(r>0){                
                Node b = (Node) event.getSource();
                Stage a = (Stage) b.getScene().getWindow();
                a.close();
                VesselController.getInstance().loadTableNamaKapal();
             }
             
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            e.getCause();
        }
    }
    
    public void addNewData(TankResultsModel tankResults){
        ObservableList<TankResultsModel> data = FXCollections.observableArrayList();
        data.add(tankResults);
    }
}
