/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objekty;

import Daty.EnumBudova;
import Daty.EnumTypUcebny;

/**
 *
 * @author maksim
 */
public class Ucebna {

    private String id;
    private String cislo;
    private Integer kapacita;
    private EnumTypUcebny typ;
    private EnumBudova budova;

    public Ucebna(String id, String cislo, Integer kapacita, EnumTypUcebny typ, EnumBudova budova) {
        this.id = id;
        this.cislo = cislo;
        this.kapacita = kapacita;
        this.typ = typ;
        this.budova = budova;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCislo() {
        return cislo;
    }

    public void setCislo(String cislo) {
        this.cislo = cislo;
    }

    public Integer getKapacita() {
        return kapacita;
    }

    public void setKapacita(Integer kapacita) {
        this.kapacita = kapacita;
    }

    public EnumTypUcebny getTyp() {
        return typ;
    }

    public void setTyp(EnumTypUcebny typ) {
        this.typ = typ;
    }

    public EnumBudova getBudova() {
        return budova;
    }

    public void setBudova(EnumBudova budova) {
        this.budova = budova;
    }

    @Override
    public String toString() {
        return "ID: " + id + " Cislo: " + cislo + " Kapacita: " + kapacita + " Typ: " + typ + " Budova: " + budova;
    }

    

    
}
