/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objekty;

import Daty.EnumJazyk;
import java.sql.Date;

/**
 *
 * @author maksim
 */
public class Obor {
    private String id;
    private String nazev;
    private String zkratka;
    private Date akrOd;
    private Date akrDo;
    private String formaStudia;
    private String jazyk;
    private String typ;
    

    public Obor() {
    }

    public Obor(String id, String nazev, String zkratka, Date akrOd, Date akrDo, String formaStudia, String jazyk, String typ) {
        this.id = id;
        this.nazev = nazev;
        this.zkratka = zkratka;
        this.akrOd = akrOd;
        this.akrDo = akrDo;
        this.formaStudia = formaStudia;
        this.jazyk = jazyk;
        this.typ = typ;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public String getZkratka() {
        return zkratka;
    }

    public void setZkratka(String zkratka) {
        this.zkratka = zkratka;
    }

    public Date getAkrOd() {
        return akrOd;
    }

    public void setAkrOd(Date akrOd) {
        this.akrOd = akrOd;
    }

    public Date getAkrDo() {
        return akrDo;
    }

    public void setAkrDo(Date akrDo) {
        this.akrDo = akrDo;
    }

    public String getFormaStudia() {
        return formaStudia;
    }

    public void setFormaStudia(String formaStudia) {
        this.formaStudia = formaStudia;
    }

    public String getJazyk() {
        return jazyk;
    }

    public void setJazyk(String jazyk) {
        this.jazyk = jazyk;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    

    

    @Override
    public String toString() {
        return "ID:" + id + ", " + nazev + ", " + zkratka + ", " + typ +", jazyk:" + jazyk;
    }
    
    
    
}
