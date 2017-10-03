/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Tesa
 */
public class TankResultsModel {
    private final SimpleIntegerProperty id;
    private SimpleStringProperty seacondition;
    private SimpleStringProperty namaKapal;
    private SimpleFloatProperty bl;
    private SimpleStringProperty notank;
    private SimpleFloatProperty sounding;
    private SimpleFloatProperty gov;
    private SimpleFloatProperty temp;
    private SimpleFloatProperty density;
    private SimpleFloatProperty vcf;
    private SimpleFloatProperty gsv;
    private SimpleFloatProperty wcf;
    private SimpleFloatProperty gsw;
    private SimpleFloatProperty forward;
    private SimpleFloatProperty after;
    private SimpleFloatProperty list;
    private SimpleIntegerProperty trim;
    
    public TankResultsModel(int id, String namaKapal, String seacondition, float bl, float forward, float after, float list, int trim){        
        this.id = new SimpleIntegerProperty(id);
        this.namaKapal = new SimpleStringProperty(namaKapal);
        this.seacondition = new SimpleStringProperty(seacondition);
        this.bl = new SimpleFloatProperty(bl);       
        this.forward = new SimpleFloatProperty(forward);
        this.after = new SimpleFloatProperty(after);
        this.list = new SimpleFloatProperty(list);
        this.trim = new SimpleIntegerProperty(trim);
    }
            
    public TankResultsModel(int id, String notank, float sounding, float gov, float temp, float density, float vcf, float gsv, float wcf, float gsw){        
        this.id = new SimpleIntegerProperty(id);
        this.notank = new SimpleStringProperty(notank);        
        this.sounding = new SimpleFloatProperty(sounding);
        this.gov = new SimpleFloatProperty(gov);
        this.temp = new SimpleFloatProperty(temp);
        this.density = new SimpleFloatProperty(density);
        this.vcf = new SimpleFloatProperty(vcf);
        this.gsv = new SimpleFloatProperty(gsv);
        this.wcf = new SimpleFloatProperty(wcf);
        this.gsw = new SimpleFloatProperty(gsw);        
    }    
    
    public int getId(){
        return id.get();
    }
    
    public String getNamakapal(){
        return namaKapal.get();
    }
    
    public String getSeacondition(){
        return seacondition.get();
    }
    
    public float getBl(){
        return bl.get();
    }
    
    public String getNotank(){
        return notank.get();
    }
    
    public float getSounding(){
        return sounding.get();
    }
    
    public float getGov(){
        return gov.get();
    }
    
    public float getTemp(){
        return temp.get();
    }
    
    public float getDensity(){
        return density.get();
    }
    
    public float getVcf(){
        return vcf.get();
    }
            
    public float getGsv(){
        return gsv.get();
    }
    
    public float getWcf(){
        return wcf.get();
    }
    
    public float getGsw(){
        return gsw.get();
    }
    
    public float getForward(){
        return forward.get();
    }
    
    public float getAfter(){
        return after.get();
    }
    
    public float getList(){
        return list.get();
    }
    
    public int getTrim(){
        return trim.get();
    }
    
    //setter for data model from database   
    
    public void setId(int id){
        this.id.set(id);
    }
    
    public void setNamaKapal(String namakapal){
        this.namaKapal.set(namakapal);
    }
    
    public void setSeacondition(String seacondition){
        this.seacondition.set(seacondition);
    }
    
    public void setBl(float bl){
        this.bl.set(bl);
    }
    
    public void setNotank(String notank){
        this.notank.set(notank);
    }
    
    public void setSounding(float sounding){
       this.sounding.set(sounding);
    }
    
    public void setGov(float gov){
       this.gov.set(gov);
    }
    
    public void setTemp(float temp){
        this.temp.set(temp);
    }
    
    public void setDensity(float density){
       this.density.set(density);
    }
    
    public void setVcf(float vcf){
        this.vcf.set(vcf);
    }
            
    public void setGsv(float gsv){
        this.gsv.set(gsv);
    }
    
    public void setWcf(float wcf){
        this.wcf.set(wcf);
    }
    
    public void setGsw(float gsw){
        this.gsw.set(gsw);
    }
    
    public void setForward(float forward){
        this.forward.set(forward);
    }
    
    public void setAfter(float after){
        this.after.set(after);
    }
    
    public void setList(float list){
        this.list.set(list);
    }
    
    public void setTrim(int trim){
        this.trim.set(trim);
    }
}
