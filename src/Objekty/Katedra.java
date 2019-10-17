/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objekty;

import java.util.Objects;

/**
 *
 * @author maksim
 */
public class Katedra {
    private String nazev;
    private String zkratka;
    private Fakulta fakulta;
    
    public Katedra() {
    }

    public Katedra(String nazev, String zkratka, Fakulta fakulta) {
        this.nazev = nazev;
        this.zkratka = zkratka;
        this.fakulta = fakulta;
    }

    public Katedra(String nazev, String zkratka) {
        this.nazev = nazev;
        this.zkratka = zkratka;
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


    @Override
    public String toString() {
        return zkratka + " " + nazev;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Katedra other = (Katedra) obj;
        if (!Objects.equals(this.nazev, other.nazev)) {
            return false;
        }
        if (!Objects.equals(this.zkratka, other.zkratka)) {
            return false;
        }
        return true;
    }

    public Fakulta getFakulta() {
        return fakulta;
    }

    public void setFakulta(Fakulta fakulta) {
        this.fakulta = fakulta;
    }

    
    
    
}
