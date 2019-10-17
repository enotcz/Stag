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
public class Ucitel implements Comparable<Ucitel> {

    private String id;
    private String jmeno;
    private String prijmeni;
    private String titulPred;
    private String titulZa;
    private Kontakt kontakt;
    private String katedra;
    private Image foto;
    private int prava;

    public Ucitel(String id, String jmeno, String prijmeni, String titulPred, String titulZa, Kontakt kontakt, String katedra, Image foto) {
        this.id = id;
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
         if (titulPred == null || titulPred.equalsIgnoreCase("null")) {
            this.titulPred = "";
        } else {
            this.titulPred = titulPred;
        }
        if (titulZa == null || titulZa.equalsIgnoreCase("null")) {
            this.titulZa = "";
        } else {
            this.titulZa = titulZa;
        }
        this.kontakt = kontakt;
        this.katedra = katedra;
        this.foto = foto;
    }

    public Ucitel(String id, String jmeno, String prijmeni, String titulPred, String titulZa, String katedra, Image foto) {
        this.id = id;
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        if (titulPred == null || titulPred.equalsIgnoreCase("null")) {
            this.titulPred = "";
        } else {
            this.titulPred = titulPred;
        }
        if (titulZa == null || titulZa.equalsIgnoreCase("null")) {
            this.titulZa = "";
        } else {
            this.titulZa = titulZa;
        }
        this.katedra = katedra;
        if (foto == null) {
            this.foto = null;
        } else {
            this.foto = foto;
        }
    }

    public Ucitel(String id, String jmeno, String prijmeni, String titulPred, String titulZa, String katedra) {
        this.id = id;
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
         if (titulPred == null || titulPred.equalsIgnoreCase("null")) {
            this.titulPred = "";
        } else {
            this.titulPred = titulPred;
        }
        if (titulZa == null || titulZa.equalsIgnoreCase("null")) {
            this.titulZa = "";
        } else {
            this.titulZa = titulZa;
        }
        this.katedra = katedra;

    }

    public Ucitel(String id, String jmeno, String prijmeni, String titulPred, String titulZa) {
        this.id = id;
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
         if (titulPred == null || titulPred.equalsIgnoreCase("null")) {
            this.titulPred = "";
        } else {
            this.titulPred = titulPred;
        }
        if (titulZa == null || titulZa.equalsIgnoreCase("null")) {
            this.titulZa = "";
        } else {
            this.titulZa = titulZa;
        }
    }

    public Ucitel(String id, String jmeno, String prijmeni, String titulPred, String titulZa, String katedra, Image foto, int prava) {
        this.id = id;
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        if (titulPred == null || titulPred.equalsIgnoreCase("null")) {
            this.titulPred = "";
        } else {
            this.titulPred = titulPred;
        }
        if (titulZa == null || titulZa.equalsIgnoreCase("null")) {
            this.titulZa = "";
        } else {
            this.titulZa = titulZa;
        }
        this.katedra = katedra;
        this.foto = foto;
        this.prava = prava;
    }

    public String getKatedra() {
        return katedra;
    }

    public void setKatedra(String katedra) {
        this.katedra = katedra;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getTitulPred() {
        return titulPred;
    }

    public void setTitulPred(String titulPred) {
        this.titulPred = titulPred;
    }

    public String getTitulZa() {
        return titulZa;
    }

    public void setTitulZa(String titulZa) {
        this.titulZa = titulZa;
    }

    public Kontakt getKontakt() {
        if (kontakt == null){
            kontakt = new Kontakt();
        }
       return kontakt;
    }

    public void setKontakt(Kontakt kontakt) {
        if (kontakt == null) {
            this.kontakt = new Kontakt("", "", "");
        } else {
            this.kontakt = kontakt;
        }

    }

    @Override
    public String toString() {
        return "ID:" + id + " " + titulPred + " " + prijmeni + " " + jmeno + " " + titulZa;
    }

    public Image getFoto() {
        return foto;
    }

    public void setFoto(Image foto) {
        this.foto = foto;
    }

    public int getPrava() {
        return prava;
    }

    public void setPrava(int prava) {
        this.prava = prava;
    }
    
    

    @Override
    public int compareTo(Ucitel o) {
        return prijmeni.compareTo(o.prijmeni);
    }

}
