/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objekty;

/**
 *
 * @author Spike
 */
public class StudPlan {
    private String id;
    private int pocetStudentu;
    private String rok;
    private Obor obor;

    public StudPlan(String id, int pocetStudentu, String rok, Obor obor) {
        this.id = id;
        this.pocetStudentu = pocetStudentu;
        this.rok = rok;
        this.obor = obor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPocetStudentu() {
        return pocetStudentu;
    }

    public void setPocetStudentu(int pocetStudentu) {
        this.pocetStudentu = pocetStudentu;
    }

    public String getRok() {
        return rok;
    }

    public void setRok(String rok) {
        this.rok = rok;
    }

    public Obor getObor() {
        return obor;
    }

    public void setObor(Obor obor) {
        this.obor = obor;
    }

    @Override
    public String toString() {
        return "ID: " + id + " Pocet studentu: " + pocetStudentu + " rok: " + rok + " Obor: " + obor.getZkratka();
    }
    
    
}
