/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.TankResultsModel;
import java.io.IOException;
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
    public TableView<TankResultsModel> tableTank;
    @FXML
    private TableColumn<TankResultsModel, String> noTank;
    public TableView<TankResultsModel> tblNamaKapal;
    @FXML
    private TableColumn<TankResultsModel, Number> cForward;
    @FXML
    private TableColumn<TankResultsModel, Number> cAfter;
    @FXML
    private TableColumn<TankResultsModel, Number> cList;
    @FXML
    private TableColumn<TankResultsModel, Number> cTrim;
    @FXML
    private TableColumn<TankResultsModel, Number> cNo;
    @FXML
    private TableColumn<TankResultsModel, Number> cBl;
    @FXML
    private TableColumn<TankResultsModel, String> cNamaKapal;
    @FXML
    private TableColumn<TankResultsModel, String> cSeaCondition;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTableNamaKapal();        
    }    
    
    public static VesselController instance;
    
    public VesselController(){
        instance = this;
    }
    
    public static VesselController getInstance(){
        return instance;
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
    int trim;
    /*============================*/
    
    public ArrayList refreshDataTable(){
        return (ArrayList) detailData;
    }
    
    @FXML
    private void btnNewKapal(ActionEvent event) {
        uic.popUpUI("/Form/NewKapal.fxml", event);
    }

    @FXML
    private void btnRefresh(ActionEvent event) {            
        loadDataKapal();    
        loadTableNamaKapal();            
        tblNamaKapal.getSelectionModel().select(tr);        
    }
    
    @FXML
    private void btnAddData(ActionEvent event) throws IOException {
        tr = tblNamaKapal.getSelectionModel().getFocusedIndex();
        cellID = tblNamaKapal.getColumns().get(0).getCellObservableValue(tr).getValue().toString();   
        trim = Integer.parseInt(tblNamaKapal.getColumns().get(7).getCellObservableValue(tr).getValue().toString());
        System.out.println("trim add "+trim);
        uic.popUpUI("/Form/AddDataKapal.fxml", event);
       
    }
    
    public void loadTableNamaKapal(){       
        cNo.setCellValueFactory(new PropertyValueFactory<>("id"));
        cNamaKapal.setCellValueFactory(new PropertyValueFactory<>("namakapal"));
        cSeaCondition.setCellValueFactory(new PropertyValueFactory<>("seacondition"));
        cBl.setCellValueFactory(new PropertyValueFactory<>("bl"));
        cForward.setCellValueFactory(new PropertyValueFactory<>("forward"));
        cAfter.setCellValueFactory(new PropertyValueFactory<>("after"));
        cList.setCellValueFactory(new PropertyValueFactory<>("list"));
        cTrim.setCellValueFactory(new PropertyValueFactory<>("trim"));
        
        identitasKapal.clear();
                        
        String sql = "SELECT * FROM kapal";
       
        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while(rs.next()){
                identitasKapal.add(new TankResultsModel(
                        rs.getInt("id"),
                        namaKapal = rs.getString("namakapal"),
                        rs.getString("seacondition"),
                        rs.getFloat("bl"),
                        rs.getFloat("forward"),
                        rs.getFloat("after"),
                        rs.getFloat("list"),
                        rs.getInt("trim")                        
                ));
                
                tblNamaKapal.setItems(identitasKapal);
            }
          
            pst.close();
            rs.close();
           
        } catch (SQLException ex) {
            Logger.getLogger(VesselController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    public void loadDataKapal(){
       
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
        
        try {
            tr = tblNamaKapal.getSelectionModel().getFocusedIndex();
            String id = tblNamaKapal.getColumns().get(0).getCellObservableValue(tr).getValue().toString();   
                      
            String sql = "SELECT * FROM tank INNER JOIN kapal ON tank.id=kapal.id WHERE kapal.id='"+id+"'";
           
            try {
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();

                while(rs.next()){
                    detailData.add(new TankResultsModel(rs.getInt("id"),
                                                                            rs.getString("notank"), 
                                                                            rs.getFloat("sounding"), 
                                                                            rs.getFloat("gov"), 
                                                                            rs.getFloat("temp"), 
                                                                            rs.getFloat("density"), 
                                                                            rs.getFloat("vcf"), 
                                                                            rs.getFloat("gsv"), 
                                                                            rs.getFloat("wcf"), 
                                                                            rs.getFloat("gsw")
                                                                           ));

                    tableTank.setItems(detailData);
                    //get namakapal value
                    TankResultsModel trm = tblNamaKapal.getSelectionModel().getSelectedItem();
                    String cellValue = trm.getNamakapal();     
                    cellID = tblNamaKapal.getColumns().get(0).getCellObservableValue(tr).getValue().toString();
                    lbNamaKapal.setText(cellValue);           
                }

                pst.close();
                rs.close();           
                
            } catch (SQLException ex) {
                Logger.getLogger(VesselController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }    
       

    @FXML
    private void tblNamaKapalClicked(MouseEvent event) {
        loadDataKapal();        
    }

    @FXML
    private void btnUpdate(ActionEvent event) {
        uic.popUpUI("/Form/Update.fxml", event);
    }
    @FXML
    private void btnDelete(ActionEvent event) {        
        
        TankResultsModel tankRow = tableTank.getSelectionModel().getSelectedItem();
        TankResultsModel IDKapal = tblNamaKapal.getSelectionModel().getSelectedItem();
       
        if(tankRow != null && IDKapal != null){
            con = DBConnect.getKoneksi();
            String sql = "DELETE FROM tank WHERE id = ? AND notank = ?";
            try {
                pst = con.prepareStatement(sql);
                pst.setInt(1, IDKapal.getId());
                pst.setString(2, tankRow.getNotank());
                pst.execute();
                loadDataKapal();
                System.out.println("Deleted");
            } catch (SQLException e) {
                System.out.println(e);
            }
            
        } else{
            System.out.println("Table Empty");
        }
    }

    @FXML
    private void btnBtn(ActionEvent event) {
    }
    
    
}
