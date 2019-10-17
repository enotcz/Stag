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
public class Kontakt {
    private String telefon;
    private String mobil;
    private String email;

    public Kontakt(){
        this.telefon = "";
        this.mobil = "";
        this.email = "";
    }
    
    public Kontakt(String telefon, String mobil, String email) {
        if (telefon == null || telefon.equalsIgnoreCase("null")){
            this.telefon = "";
        }else {
            this.telefon = telefon;
        }
        if (mobil == null || mobil.equalsIgnoreCase("null")){
            this.mobil = "";
        }else {
            this.mobil = mobil;
        }
        if (email == null || email.equalsIgnoreCase("null")){
            this.email = "";
        }else {
            this.email = email;
        }
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getMobil() {
        return mobil;
    }

    public void setMobil(String mobil) {
        this.mobil = mobil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "tel:" + telefon + " mob:" + mobil + ", email:" + email;
    }
    
    
    
    
    
    
}
