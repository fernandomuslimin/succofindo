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
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author NILAM
 */
public class VesselController implements Initializable {

      
    @FXML
    private Label lbNamaKapal;   
    @FXML
    private TextField fForward;
    @FXML
    private TextField fAfter;
    @FXML
    private TextField fList;
    @FXML
    private TextField fBl;
    @FXML
    private TextField fSeacond;
    @FXML
    private TextField fTrim;
    @FXML
    private TextField fRho;
    @FXML
    private TableColumn<TankResultsModel, Number> temp;
    @FXML
    private TableColumn<TankResultsModel, Number> density;
    @FXML
    private TableColumn<TankResultsModel, Number> vcf;
    @FXML
    private TableColumn<TankResultsModel, Number> wcf;
    @FXML
    private TableColumn<TankResultsModel, Number> sounding;
    @FXML
    private TableColumn<TankResultsModel, Number> gov;
    @FXML
    private TableColumn<TankResultsModel, Number> gsv;
    @FXML
    private TableColumn<TankResultsModel, Number> gsw;
    @FXML
    private TableView<TankResultsModel> tableTank;
    @FXML
    private TableColumn<TankResultsModel, String> noTank;
    @FXML
    public TableView<TankResultsModel> tblNamaKapal;
    @FXML
    private TableColumn<TankResultsModel, Number> cNo;
    @FXML
    private TableColumn<TankResultsModel, String> cNamaKapal;
    @FXML
    private TableColumn<TankResultsModel, String> cSeaCondition;
    @FXML
    private TableColumn<TankResultsModel, Number> cBl;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTableNamaKapal();        
    }    

    /*===========Declaration==========*/
    ObservableList<TankResultsModel> detailData = FXCollections.observableArrayList();
    ObservableList<TankResultsModel> identitasKapal = FXCollections.observableArrayList();
    UIController uic = new UIController();
    Connection con = DBConnect.getKoneksi();
    PreparedStatement pst;
    ResultSet rs;        
    int tr;   
    String namaKapal;
    String cellID;
    /*============================*/
    
    public ArrayList refreshDataTable(){
        return (ArrayList) detailData;
    }
    
    @FXML
    private void btnNewKapal(ActionEvent event) {
        uic.CallUI("/Form/NewKapal.fxml");
    }

    @FXML
    private void btnRefresh(ActionEvent event) {            
        tblDataKapalClicked();    
        loadTableNamaKapal();            
        tblNamaKapal.getSelectionModel().select(tr);
    }

    @FXML
    private void btnAddData(ActionEvent event) {
        uic.CallUI("/Form/AddDataKapal.fxml");
    }
    
    public void loadTableNamaKapal(){       
        cNo.setCellValueFactory(new PropertyValueFactory<>("id"));
        cNamaKapal.setCellValueFactory(new PropertyValueFactory<>("namakapal"));
        cSeaCondition.setCellValueFactory(new PropertyValueFactory<>("seacondition"));
        cBl.setCellValueFactory(new PropertyValueFactory<>("bl"));
        
        identitasKapal.clear();
                        
        String sql = "SELECT * FROM kapal";
       
        float bl = 0;
                              
        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while(rs.next()){
                identitasKapal.add(new TankResultsModel(
                        rs.getInt("id"),
                        rs.getString("namakapal"),
                        rs.getString("seacondition"),
                        bl = rs.getFloat("bl")
                ));
                
                tblNamaKapal.setItems(identitasKapal);
            }
                                  
            //tr = tblNamaKapal.getSelectionModel().getFocusedIndex();
            
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(VesselController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    
    public void getSelectedID(int id){
        
    }
    
    public void tblDataKapalClicked(){
        noTank.setCellValueFactory(new PropertyValueFactory<>("notank"));
        sounding.setCellValueFactory(new PropertyValueFactory<>("sounding"));
        gov.setCellValueFactory(new PropertyValueFactory<>("gov"));
        temp.setCellValueFactory(new PropertyValueFactory<>("temp"));
        density.setCellValueFactory(new PropertyValueFactory<>("density"));
        vcf.setCellValueFactory(new PropertyValueFactory<>("vcf"));
        gsv.setCellValueFactory(new PropertyValueFactory<>("gsv"));
        wcf.setCellValueFactory(new PropertyValueFactory<>("wcf"));
        gsw.setCellValueFactory(new PropertyValueFactory<>("gsw"));
        
        detailData.clear();        
        tr = tblNamaKapal.getSelectionModel().getFocusedIndex();
        TankResultsModel trm = tblNamaKapal.getSelectionModel().getSelectedItem();
        String cellValue = trm.getNamakapal();       
                                           
        String sql = "SELECT kapal.id,kapal.namakapal,kapal.seacondition,kapal.bl,notank,sounding,gov,temp,density,vcf,gsv,wcf,gsw,forward,after,list FROM tank INNER JOIN kapal ON tank.id=kapal.id WHERE kapal.namakapal='"+cellValue+"'";
              
        String seacondition = null;       
        String notank = null;        
        float bl = 0;
        float dens = 0;
        float forward = 0;
        float after = 0;
        float list = 0;
        
        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while(rs.next()){
                detailData.add(new TankResultsModel(rs.getInt("id"),
                                                                        this.namaKapal =rs.getString("namakapal"),
                                                                        seacondition = rs.getString("seacondition"),
                                                                        bl = rs.getFloat("bl"),
                                                                        notank = rs.getString("notank"), 
                                                                        rs.getFloat("sounding"), 
                                                                        rs.getFloat("gov"), 
                                                                        rs.getFloat("temp"), 
                                                                        dens = rs.getFloat("density"), 
                                                                        rs.getFloat("vcf"), 
                                                                        rs.getFloat("gsv"), 
                                                                        rs.getFloat("wcf"), 
                                                                        rs.getFloat("gsw"),
                                                                        forward = rs.getFloat("forward"),
                                                                        after = rs.getFloat("after"),
                                                                        list = rs.getFloat("list")));
                
                tableTank.setItems(detailData);
            }
                        
            lbNamaKapal.setText(cellValue);
            fForward.setText(Float.toString(forward));
            fAfter.setText(Float.toString(after));
            fList.setText(Float.toString(list));
            fBl.setText(Float.toString(bl));
            fRho.setText(Float.toString(bl));
            fSeacond.setText(seacondition);
            System.out.println(cellValue);

            pst.close();
            rs.close();           
            
        } catch (SQLException ex) {
            Logger.getLogger(VesselController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    @FXML
    private void tblNamaKapalClicked(MouseEvent event) {
        tblDataKapalClicked();
    }
    
   
    
}
