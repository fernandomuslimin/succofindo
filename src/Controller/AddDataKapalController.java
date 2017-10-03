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
        float trim;
        float volInterpolated;
        String notank;
        String id;
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
    
    @FXML
    private void btnSave(ActionEvent event) {      
        insertData(); 
        calcInterpolation();
        insertCalculatedGov(event);            
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
                 System.out.println("Your data has been successfully aded!");                
             }
             
             pst.close();
                                      
        } catch (SQLException e) {
            System.out.println(e);            
        }
    }
    
    public void calcInterpolation(){
        VesselController.getInstance().loadDataKapal();
        
        float vsl;
        int db = VesselController.getInstance().trim;
        notank = fNoTank.getText();
        id = VesselController.getInstance().cellID;
        
        String allExist = "SELECT volume FROM trim"+db+" WHERE sounding = (SELECT sounding FROM tank WHERE id = ? AND notank = ?)";
        String checkTrim = "SELECT volume FROM trim"+db+"";
        
        System.out.println("Nilai trim = "+db);
        con = DBConnect.getKoneksi();
        
        try {
            st = con.createStatement();
            if(st.execute(checkTrim) == true){
                System.out.println("Trim Found!");
                pst = con.prepareStatement(allExist);
                pst.setString(1, id);
                pst.setString(2, notank);
                rs = pst.executeQuery();
                if(rs.next()){
                    vsl = rs.getFloat("volume");
                    System.out.println("Volume = "+vsl);
                } else {
                    System.out.println("Trim exist but sounding is not");
                    int soundTop = getTopSound();
                    int soundBot = getBotSound();
                    int volTop = 0;
                    int volBot = 0;
                    String queryVolTop = "SELECT volume FROM trim"+db+" WHERE sounding = ?";
                    String queryVolBot = "SELECT volume FROM trim"+db+" WHERE sounding = ?";
                    
                    //get top volume
                    try {
                        pst = con.prepareStatement(queryVolTop);
                        pst.setInt(1, soundTop);
                        rs = pst.executeQuery();
                        if(rs.next()){
                            volTop = rs.getInt("volume");
                            System.out.println("Vol Top = "+volTop);
                        }                        
                    } catch (SQLException e) {
                        System.out.println("Fail to obtain top volume "+e);
                    }
                    
                    //get bot volume
                    try {
                        pst = con.prepareStatement(queryVolBot);
                        pst.setInt(1, soundBot);
                        rs = pst.executeQuery();
                        if(rs.next()){
                            volBot = rs.getInt("volume");
                            System.out.println("vol Bot = "+volBot);
                        }                       
                    } catch (SQLException e) {
                        System.out.println("fail to obtain bot volume "+e);
                    }
                    
                    System.out.println("Sound Top = "+soundTop);
                    System.out.println("Sound Bot = "+soundBot);
                    
                    //calculate interpolation
                    int inpSounding = getInpSounding();
                    int a = soundBot - inpSounding;
                    int b = soundBot - soundTop;
                    int c = volBot - volTop;                                    
                    volInterpolated = volBot - (a*c/b);
                    System.out.println("Volume Interpolateed = "+volInterpolated);
                    /*float res;
                    volInterpolated = (volBot - ((soundBot - inpSounding)*(volBot - volTop))/(soundBot - soundTop));
                    res = volBot - (a*c/b);                    
                    System.out.println("Vol res = "+res);*/
                }
            }else{
                System.out.println("Trim not found!");
            }
            
            pst.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void insertCalculatedGov(ActionEvent event){
        String insGov = "UPDATE tank SET gov = ? WHERE id = ? AND notank = ?";
        
        try {
            pst = con.prepareStatement(insGov);
            pst.setFloat(1, volInterpolated);
            pst.setString(2, id);
            pst.setString(3, notank);
            int res = pst.executeUpdate();
            if (res>0){
                Node b = (Node) event.getSource();
                Stage a = (Stage) b.getScene().getWindow();
                a.close();                
                FXDialogs.showInformation("Data Information", "Data Successfully Added");
                VesselController.getInstance().loadDataKapal();
            }
        } catch (SQLException e) {
            FXDialogs.showInformation("Data Information", "Failed to add data");
            System.out.println(e);
        }
        System.out.println("vol calculated = "+volInterpolated);
        System.out.println("id = "+id);
        System.out.println("notank = "+notank);
    }
    
    public int getInpSounding(){
        int sounding = Integer.parseInt(fSounding.getText());
        
        return sounding;
    }
    
    public int getTopSound(){
        String inpSound = fSounding.getText(0, 1);
        int topSound = Integer.parseInt(inpSound+"0");
        
        return topSound;
    }
    
    public int getBotSound(){
        int downSound = getTopSound() + 10;
        
        return downSound;
    }
}    