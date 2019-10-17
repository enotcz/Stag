/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objekty;

/**
 *
 * @author maksim
 */
public class Fakulta {
    String zkratka;
    String nazev;
    String dekan;
    Kontakt kontakt;

    public Fakulta(String zkratka, String nazev, String dekan) {
        this.zkratka = zkratka;
        this.nazev = nazev;
        this.dekan = dekan;
    }

    public String getZkratka() {
        return zkratka;
    }

    public void setZkratka(String zkratka) {
        this.zkratka = zkratka;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public String getDekan() {
        return dekan;
    }

    public void setDekan(String dekan) {
        this.dekan = dekan;
    }

    public Kontakt getKontakt() {
        return kontakt;
    }

    public void setKontakt(Kontakt kontakt) {
        this.kontakt = kontakt;
    }

    @Override
    public String toString() {
        return zkratka + " " + nazev;
    }
    
    
}
