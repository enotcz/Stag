/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objekty;

import javafx.scene.image.Image;

/**
 *
 * @author maksim
 */
public class Student {
    private String netID;
    private String jmeno;
    private String prijmeni;
    private String rocnik;
    private Obor obor;
    private Image foto;

    public String getNetID() {
        return netID;
    }

    public void setNetID(String netID) {
        this.netID = netID;
    }

    public Student(String netID, String jmeno, String prijmeni, String rocnik,Obor obor) {
        this.netID = netID;
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.rocnik = rocnik;
        this.obor = obor;
    }
    
    public Student(String netID, String jmeno, String prijmeni, String rocnik,Obor obor, Image foto) {
        this.netID = netID;
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.rocnik = rocnik;
        this.obor = obor;
        this.foto = foto;
    }

    

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    public String getRocnik() {
        return rocnik;
    }

    public void setRocnik(String rocnik) {
        this.rocnik = rocnik;
    }

    public Image getFoto() {
        return foto;
    }

    public void setFoto(Image foto) {
        this.foto = foto;
    }

    public Obor getObor() {
        return obor;
    }

    public void setObor(Obor obor) {
        this.obor = obor;
    }
    
    
    

    @Override
    public String toString() {
        return "ID:" + netID + " "+ jmeno+ " " + prijmeni + ", rocnik:" + rocnik;
    }
    
    
    
}
