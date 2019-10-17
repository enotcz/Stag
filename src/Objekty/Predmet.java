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
public class Predmet {
    private String zkratka;
    private String nazev;
    private String popis;
    private String pocetKreditu;
    private String semestrZimni;
    private String semestrLetni;
    private String zkratkaKatedry;
    private String zpusobZakonceni;
    private String doporucenyRok;
    private int prednaskaHod;
    private int cviceniHod;
    private int seminarHod;
    
    

    public Predmet(String zkratka, String nazev, String popis,String zkratkaKatedry, String pocetKreditu, String semestrZimni, String semestrLetni, String zpusobZakonceni, String doporucenyRok, int prednaskaHod, int cviceniHod, int seminarHod) {
        this.zkratka = zkratka;
        this.nazev = nazev;
        this.popis = popis;
        this.pocetKreditu = pocetKreditu;
        this.semestrZimni = semestrZimni;
        this.semestrLetni = semestrLetni;
        this.zpusobZakonceni = zpusobZakonceni;
        this.doporucenyRok = doporucenyRok;
        this.prednaskaHod = prednaskaHod;
        this.cviceniHod = cviceniHod;
        this.seminarHod = seminarHod;
        this.zkratkaKatedry = zkratkaKatedry;
    }

    public String getZkratkaKatedry() {
        return zkratkaKatedry;
    }

    public void setZkratkaKatedry(String zkratkaKatedry) {
        this.zkratkaKatedry = zkratkaKatedry;
    }

    
    
    

    public Predmet(String zkratka, String nazev) {
        this.zkratka = zkratka;
        this.nazev = nazev;
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

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    public String getPocetKreditu() {
        return pocetKreditu;
    }

    public void setPocetKreditu(String pocetKreditu) {
        this.pocetKreditu = pocetKreditu;
    }

    public String getSemestrZimni() {
        return semestrZimni;
    }

    public void setSemestrZimni(String semestrZimni) {
        this.semestrZimni = semestrZimni;
    }

    public String getSemestrLetni() {
        return semestrLetni;
    }

    public void setSemestrLetni(String semestrLetni) {
        this.semestrLetni = semestrLetni;
    }

    

    public String getZpusobZakonceni() {
        return zpusobZakonceni;
    }

    public void setZpusobZakonceni(String zpusobZakonceni) {
        this.zpusobZakonceni = zpusobZakonceni;
    }

    @Override
    public String toString() {
        return zkratka + ", " + nazev;
    }

    public String getDoporucenyRok() {
        return doporucenyRok;
    }

    public void setDoporucenyRok(String doporucenyRok) {
        this.doporucenyRok = doporucenyRok;
    }

    public int getPrednaskaHod() {
        return prednaskaHod;
    }

    public void setPrednaskaHod(int prednaskaHod) {
        this.prednaskaHod = prednaskaHod;
    }

    public int getCviceniHod() {
        return cviceniHod;
    }

    public void setCviceniHod(int cviceniHod) {
        this.cviceniHod = cviceniHod;
    }

    public int getSeminarHod() {
        return seminarHod;
    }

    public void setSeminarHod(int seminarHod) {
        this.seminarHod = seminarHod;
    }
    
    
    
    
    
    
    
}
