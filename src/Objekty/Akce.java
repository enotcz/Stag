/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objekty;

import Daty.EnumAnoNe;
import Daty.EnumDen;
import Daty.EnumTyden;
import Daty.EnumTypVyuky;
import java.sql.Date;

/**
 *
 * @author maksim
 */
public class Akce {

    private String id;
    private EnumTypVyuky typ;
    private EnumTyden tyden;
    private EnumDen den;
    private String CasOd;
    private String CasDo;
    private EnumAnoNe platna;
    private int kapacita;
    private Ucebna ucebna;
    private Ucitel ucitel;
    private Predmet predmet;
    private EnumAnoNe blokova;
    private Date datum;

    public Akce(String id, EnumTypVyuky typ, EnumTyden tyden, EnumDen den, String CasOd, String CasDo, int kapacita, int platna, Ucebna ucebna, Ucitel ucitel, Predmet predmet, int blokova, Date datum) {
        this.id = id;
        this.typ = typ;
        this.tyden = tyden;
        this.den = den;
        this.CasOd = CasOd;
        this.CasDo = CasDo;
        if (platna == 0) {
            this.platna = EnumAnoNe.NE;
        } else {
            this.platna = EnumAnoNe.ANO;
        }
        this.kapacita = kapacita;
        this.ucebna = ucebna;
        this.ucitel = ucitel;
        this.predmet = predmet;
        if (blokova == 0) {
            this.blokova = EnumAnoNe.NE;
        } else {
            this.blokova = EnumAnoNe.ANO;
        }
        this.datum = datum;
    }

    public EnumAnoNe getBlokova() {
        return blokova;
    }

    public int getBlokovaInt() {
        if (blokova == EnumAnoNe.ANO) {
            return 1;
        } else {
            return 0;
        }
    }

    public void setBlokova(EnumAnoNe blokova) {
        this.blokova = blokova;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EnumTypVyuky getTyp() {
        return typ;
    }

    public void setTyp(EnumTypVyuky typ) {
        this.typ = typ;
    }

    public EnumTyden getTyden() {
        return tyden;
    }

    public void setTyden(EnumTyden tyden) {
        this.tyden = tyden;
    }

    public EnumDen getDen() {
        return den;
    }

    public void setDen(EnumDen den) {
        this.den = den;
    }

    public String getCasOd() {
        return CasOd;
    }

    public void setCasOd(String CasOd) {
        this.CasOd = CasOd;
    }

    public String getCasDo() {
        return CasDo;
    }

    public void setCasDo(String CasDo) {
        this.CasDo = CasDo;
    }

    public EnumAnoNe getPlatna() {
        return platna;
    }

    public void setPlatna(EnumAnoNe platna) {
        this.platna = platna;
    }

    public int getPlatnaInt() {
        if (this.platna == EnumAnoNe.ANO) {
            return 1;
        } else {
            return 0;
        }
    }

    public void setPlatnaInt(int jePlatna) {
        if (jePlatna == 1) {
            this.platna = EnumAnoNe.ANO;
        } else {
            this.platna = EnumAnoNe.NE;
        }
    }

    public int getKapacita() {
        return kapacita;
    }

    public Ucebna getUcebna() {
        return ucebna;
    }

    public Ucitel getUcitel() {
        return ucitel;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setKapacita(int kapacita) {
        this.kapacita = kapacita;
    }

    public void setUcebna(Ucebna ucebna) {
        this.ucebna = ucebna;
    }

    public void setUcitel(Ucitel ucitel) {
        this.ucitel = ucitel;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    @Override
    public String toString() {
        if (blokova == EnumAnoNe.NE){
            return predmet.getZkratka() + " " + typ + " " + den + " " + " " + tyden + " " + CasOd + "-" + CasDo + " " + ucebna.getCislo() + " " + ucitel.getJmeno() + " " + ucitel.getPrijmeni() + " Platna: " + platna;
        }
        else {
            return predmet.getZkratka() + " " + typ + " " + datum + " " + CasOd + "-" + CasDo + " " + ucebna.getCislo() + " " + ucitel.getJmeno() + " " + ucitel.getPrijmeni() + " Platna: " + platna;
        }
    }

    public String toStringAkceNeBlokova() {
        return getPredmet().getZkratka() + " " + getCasOd() + "-" + getCasDo() + " " + getTyden() + " " + getUcebna().getCislo() + " " + getUcebna().getBudova();
    }

    public String toStringAkceBlokova() {
        return getPredmet().getZkratka() + " " + getCasOd() + "-" + getCasDo() + " " + getDatum() + " " + getUcebna().getCislo() + " " + getUcebna().getBudova();
    }

}
