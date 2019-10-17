/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import Daty.*;
import Objekty.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import javafx.scene.image.Image;

/**
 *
 * @author maksim
 */
// Trida urcena k komunikaci applikace a databazi
// Trida pouziva pattern SINGLETON
public class DatabaseHelper {

    private static DatabaseHelper instance = null;

    private DatabaseHelper() throws SQLException, NullPointerException {
        myInit("C##ST52522", "PankiHoj");
        instance = this;
    }

    private DatabaseHelper(String login, String pswd) throws SQLException, NullPointerException {
        myInit(login, pswd);
        instance = this;
    }
    
    public static void myInit(String login, String pswd) throws SQLException, NullPointerException {
        OracleConnector.setUpConnection("fei-sql1.upceucebny.cz", 1521, "IDAS12", login, pswd);
    }

    public static DatabaseHelper getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseHelper();
        }
        return instance;
    }
    
    public static DatabaseHelper setUp(String name, String pass) throws SQLException{
        if (instance == null){
            myInit(name, pass);
        }
        return instance;
    }

    public Image getFotoFromBlob(Blob blob) throws SQLException {
        InputStream is = blob.getBinaryStream();
        return new Image(is);
    }
    //============================VYUCUJICI==========================================

    // Metoda vraci seznam vyucujicich
    public ArrayList<Ucitel> getUciteliAll() throws SQLException, NullPointerException {
        ArrayList<Ucitel> list = new ArrayList<>();
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM SP_V_VYUCUJICI_KONTAKT");
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            Ucitel ucitel = new Ucitel(rset.getString("id_vyucujici"),
                    rset.getString("jmeno"),
                    rset.getString("prijmeni"),
                    rset.getString("titulPred"),
                    rset.getString("titulZa"),
                    rset.getString("zkratka_katedry"),
                    getFotoFromBlob(rset.getBlob("obrazek")),
                    rset.getInt("prava"));
            ucitel.setKontakt(getKontaktUcitela(ucitel));
            list.add(ucitel);
        }
        stmt.close();
        rset.close();
        return list;
    }

    // Vraci vyucijiciho podle ID
    public Ucitel getUcitel(String id) throws SQLException {
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM SP_V_VYUCUJICI_KONTAKT WHERE id_vyucujici = ?");
        stmt.setString(1, id);
        ResultSet rset = stmt.executeQuery();
        Ucitel ucitel = null;
        while (rset.next()) {
            ucitel = new Ucitel(rset.getString("id_vyucujici"),
                    rset.getString("jmeno"),
                    rset.getString("prijmeni"),
                    rset.getString("titulPred"),
                    rset.getString("titulZa"),
                    rset.getString("zkratka_katedry"),
                    getFotoFromBlob(rset.getBlob("obrazek")),
                    rset.getInt("prava"));
            ucitel.setKontakt(getKontaktUcitela(ucitel));

        }
        stmt.close();
        rset.close();
        return ucitel;
    }

    // Prihlasovani uzivatela na zaklade ID a Hesla, pokud uzivatel existuje bude vraceno TRUE, v jinem pripade FALSE
    public boolean login(String id, String heslo) throws SQLException {
        Connection conn = OracleConnector.getConnection();
        CallableStatement stmt = conn.prepareCall("{ ? = call SP_FUNC_LOGIN(?,?)}");
        stmt.registerOutParameter(1, Types.INTEGER);
        stmt.setString(2, id);
        stmt.setString(3, heslo);
        stmt.execute();
        int result = stmt.getInt(1);
        stmt.close();
        return result == 1;
    }

    // Metoda vraci garantovany predmety vyucujicim
    public ArrayList<Predmet> getGarantovanyPredmetyUcitela(Ucitel ucitel) throws SQLException, NullPointerException {
        if (ucitel == null) {
            throw new NullPointerException();
        }
        ArrayList<Predmet> list = new ArrayList<>();
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM sp_v_vyucujici_garant where sp_v_vyucujici_garant.id_vyucujici = ?");
        stmt.setString(1, ucitel.getId());
        ResultSet rset = stmt.executeQuery();

        while (rset.next()) {
            list.add(new Predmet(
                    rset.getString("zkratka"),
                    rset.getString("nazev")
            ));

        }
        stmt.close();
        rset.close();
        return list;
    }

    // Metoda vraci predmety ktery prednasi vyucujici
    public ArrayList<Predmet> getPrednasPredmetyUcitela(Ucitel ucitel) throws SQLException, NullPointerException {
        if (ucitel == null) {
            throw new NullPointerException();
        }
        ArrayList<Predmet> list = new ArrayList<>();
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM sp_v_vyucujici_prednas where sp_v_vyucujici_prednas.id_vyucujici = ?");
        stmt.setString(1, ucitel.getId());
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            list.add(new Predmet(
                    rset.getString("zkratka"),
                    rset.getString("nazev")
            ));
        }
        stmt.close();
        rset.close();
        return list;
    }
    
    // Metoda vraci predmety ktery cvici vyucujici
    public ArrayList<Predmet> getCviciciPredmetyUcitela(Ucitel ucitel) throws SQLException, NullPointerException {
        if (ucitel == null) {
            throw new NullPointerException();
        }
        ArrayList<Predmet> list = new ArrayList<>();
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM sp_v_vyucujici_cvicici where sp_v_vyucujici_cvicici.id_vyucujici = ?");
        stmt.setString(1, ucitel.getId());
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            list.add(new Predmet(
                    rset.getString("zkratka"),
                    rset.getString("nazev")
            ));
        }
        stmt.close();
        rset.close();
        return list;
    }
    // Metoda vraci predmety ktery vede seminar vyucujici
    public ArrayList<Predmet> getSeminarPredmetyUcitela(Ucitel ucitel) throws SQLException, NullPointerException {
        if (ucitel == null) {
            throw new NullPointerException();
        }
        ArrayList<Predmet> list = new ArrayList<>();
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM sp_v_vyucujici_seminar where sp_v_vyucujici_seminar.id_vyucujici = ?");
        stmt.setString(1, ucitel.getId());
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            list.add(new Predmet(
                    rset.getString("zkratka"),
                    rset.getString("nazev")
            ));

        }
        stmt.close();
        rset.close();
        return list;
    }

    // Vraci kontakt vyucujiciho
    public Kontakt getKontaktUcitela(Ucitel ucitel) throws SQLException, NullPointerException {
        if (ucitel == null) {
            throw new NullPointerException();
        }
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM SP_V_VYUCUJICI_KONTAKT WHERE ID_VYUCUJICI = ?");
        stmt.setString(1, ucitel.getId());
        ResultSet rset = stmt.executeQuery();
        Kontakt kontakt = null;
        while (rset.next()) {
            kontakt = new Kontakt(
                    rset.getString("telefon"),
                    rset.getString("mobil"),
                    rset.getString("email")
            );
        }
        stmt.close();
        rset.close();
        return kontakt;
    }

    // Vkladani noveho vyucujiciho
    public void insertNovyUcitel(Ucitel ucitel, Kontakt kontakt) throws SQLException, NullPointerException {
        if (ucitel == null || kontakt == null) {
            throw new NullPointerException();
        }
        Connection conn = OracleConnector.getConnection();
        CallableStatement stmt = conn.prepareCall("{call sp_proc_insert_update_vyucujici(?,?,?,?,?,?,?,?,?,?,?,?)}");
        stmt.setString(1, null);
        stmt.setString(2, ucitel.getJmeno());
        stmt.setString(3, ucitel.getPrijmeni());
        stmt.setString(4, ucitel.getTitulPred());
        stmt.setString(5, ucitel.getTitulZa());
        stmt.setString(6, ucitel.getKatedra());
        stmt.setBinaryStream(7, null);
        stmt.setString(8, null);
        stmt.setInt(9, 0);
        stmt.setString(10, kontakt.getTelefon());
        stmt.setString(11, kontakt.getMobil());
        stmt.setString(12, kontakt.getEmail());
        stmt.execute();
        stmt.close();

    }

    // Obnovani dat vyucujiciho
    public void updateUcitel(Ucitel ucitel) throws SQLException, NullPointerException {
        if (ucitel == null) {
            throw new NullPointerException();
        }
        Connection conn;
        conn = OracleConnector.getConnection();

        CallableStatement stmt = conn.prepareCall("{call sp_proc_insert_update_vyucujici(?,?,?,?,?,?,?,?,?,?,?,?)}");
        stmt.setString(1, ucitel.getId());
        stmt.setString(2, ucitel.getJmeno());
        stmt.setString(3, ucitel.getPrijmeni());
        stmt.setString(4, ucitel.getTitulPred());
        stmt.setString(5, ucitel.getTitulZa());
        stmt.setString(6, ucitel.getKatedra());
        stmt.setBinaryStream(7, null);
        stmt.setString(8, null);
        stmt.setInt(9, ucitel.getPrava());
        stmt.setString(10, ucitel.getKontakt().getTelefon());
        stmt.setString(11, ucitel.getKontakt().getMobil());
        stmt.setString(12, ucitel.getKontakt().getEmail());
        stmt.execute();
        stmt.close();
    }

    // Smazani vyucujiciho
    public void deleteUcitel(Ucitel ucitel) throws SQLException, NullPointerException {
        if (ucitel == null) {
            throw new NullPointerException();
        }
        Connection conn;
        conn = OracleConnector.getConnection();
        CallableStatement stmt = conn.prepareCall("{call sp_proc_delete_vyucujici(?)}");
        stmt.setString(1, ucitel.getId());
        stmt.execute();
        stmt.close();
    }

    // Nastavovani hesla vyucujiciho
    public void updateHesloUcitela(Ucitel ucitel, String heslo) throws NullPointerException, SQLException {
        if (ucitel == null) {
            throw new NullPointerException();
        }
        Connection conn;
        conn = OracleConnector.getConnection();
        CallableStatement stmt = conn.prepareCall("{call sp_proc_update_heslo(?,?)}");
        stmt.setString(1, ucitel.getId());
        stmt.setString(2, heslo);
        stmt.execute();
        stmt.close();
    }

    // Nastavovani foto vyucujiciho
    public void updateFotoUcitela(Ucitel ucitel, File f) throws SQLException, FileNotFoundException {
        if (ucitel == null || f == null) {
            throw new NullPointerException();
        }
        Connection conn;
        conn = OracleConnector.getConnection();
        FileInputStream fin = new FileInputStream(f);
        System.out.println(ucitel.getId() + f.getName());
        CallableStatement stmt = conn.prepareCall("{call sp_proc_insert_update_foto(?,?,?)}");
        stmt.setString(1, null);
        stmt.setString(2, ucitel.getId());
        stmt.setBinaryStream(3, fin, (int) f.length());
        stmt.execute();
        stmt.close();
    }

//============================KATEDRY==========================================
    // Metoda vraci seznam kateder
    public ArrayList<Katedra> getKatedryAll() throws SQLException, NullPointerException {
        ArrayList<Katedra> list = new ArrayList<>();
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM SP_V_KATEDRA");
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            list.add(new Katedra(
                    rset.getString("nazev_katedry"),
                    rset.getString("zkratka_katedry"),
                    new Fakulta(rset.getString("zkratka_fakulty"),
                            rset.getString("nazev"),
                            rset.getString("dekan"))
            ));
        }
        stmt.close();
        rset.close();
        return list;
    }

    // obnovovani dat katedry
    public void updateKatedru(Katedra katedra) throws SQLException, NullPointerException {
        if (katedra == null) {
            throw new NullPointerException();
        }
        Connection conn = OracleConnector.getConnection();
        CallableStatement stmt = conn.prepareCall("{call sp_proc_insert_update_katedra(?,?,?)}");
        stmt.setString(1, katedra.getZkratka());
        stmt.setString(2, katedra.getNazev());
        stmt.setString(3, katedra.getFakulta().getZkratka());
        stmt.execute();
        stmt.close();
    }

    // vkladani nove katedry
    public void insertNovuKatedru(Katedra katedra) throws SQLException, NullPointerException {
        if (katedra == null) {
            throw new NullPointerException();
        }
        Connection conn = OracleConnector.getConnection();
        CallableStatement stmt = conn.prepareCall("{call sp_proc_insert_update_katedra(?,?,?)}");
        stmt.setString(1, katedra.getZkratka());
        stmt.setString(2, katedra.getNazev());
        stmt.setString(3, katedra.getFakulta().getZkratka());
        stmt.execute();
        stmt.close();
    }

    // smazani katedry
    public void deleteKatedru(Katedra katedra) throws SQLException, NullPointerException {
        if (katedra == null) {
            throw new NullPointerException();
        }
        Connection conn = OracleConnector.getConnection();
        CallableStatement stmt = conn.prepareCall("{call sp_proc_delete_katedra(?)}");
        stmt.setString(1, katedra.getZkratka());
        stmt.execute();
        conn.commit();
        stmt.close();
    }

    //====================PREDMETY==================================
    // Metoda vraci seznam predmetu
    public ArrayList<Predmet> getPredmetyAll() throws SQLException, NullPointerException {
        ArrayList<Predmet> list = new ArrayList<>();
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM sp_v_predmet");
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            list.add(new Predmet(
                    rset.getString("zkratka"),
                    rset.getString("nazev"),
                    rset.getString("popis"),
                    rset.getString("zkratka_katedry"),
                    rset.getString("pocet_kreditu"),
                    rset.getString("VyukaZS"),
                    rset.getString("VyukaLS"),
                    rset.getString("zpusob_zakonceni"),
                    rset.getString("doporuceny_rok"),
                    rset.getInt("prednaska_hodin"),
                    rset.getInt("cviceni_hodin"),
                    rset.getInt("seminar_hodin")
            ));
        }
        stmt.close();
        rset.close();
        return list;
    }

    // metoda vraci predmet na zaklade zkratky
    public Predmet getPredmet(String zkratka) throws SQLException {
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM sp_v_predmet WHERE (zkratka = ?)");
        stmt.setString(1, zkratka);
        ResultSet rset = stmt.executeQuery();
        Predmet predmet = null;
        while (rset.next()) {
            predmet = new Predmet(
                    rset.getString("zkratka"),
                    rset.getString("nazev"),
                    rset.getString("popis"),
                    rset.getString("zkratka_katedry"),
                    rset.getString("pocet_kreditu"),
                    rset.getString("VyukaZS"),
                    rset.getString("VyukaLS"),
                    rset.getString("zpusob_zakonceni"),
                    rset.getString("doporuceny_rok"),
                    rset.getInt("prednaska_hodin"),
                    rset.getInt("cviceni_hodin"),
                    rset.getInt("seminar_hodin")
            );
        }
        stmt.close();
        rset.close();
        return predmet;
    }

    // Vraci seznam prednasijicich
    public ArrayList<Ucitel> getSeznamPrednasicich(Predmet predmet) throws SQLException, NullPointerException {
        if (predmet == null) {
            throw new NullPointerException();
        }
        ArrayList<Ucitel> list = new ArrayList<>();
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM sp_v_vyucujici_prednas where sp_v_vyucujici_prednas.zkratka = ?");
        stmt.setString(1, predmet.getZkratka());
        System.out.println(predmet.getZkratka());
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            list.add(new Ucitel(
                    rset.getString("id_vyucujici"),
                    rset.getString("jmeno"),
                    rset.getString("prijmeni"),
                    rset.getString("titulpred"),
                    rset.getString("titulza")
            ));
        }
        stmt.close();
        rset.close();
        return list;

    }
    // Vraci seznam cvicicich
    public ArrayList<Ucitel> getSeznamCvicicich(Predmet predmet) throws SQLException, NullPointerException {
        if (predmet == null) {
            throw new NullPointerException();
        }
        ArrayList<Ucitel> list = new ArrayList<>();

        Connection conn = OracleConnector.getConnection();

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM "
                + "sp_v_vyucujici_cvicici where sp_v_vyucujici_cvicici.zkratka = ?");
        stmt.setString(1, predmet.getZkratka());
        System.out.println(predmet.getZkratka());
        ResultSet rset = stmt.executeQuery();

        while (rset.next()) {
            list.add(new Ucitel(
                    rset.getString("id_vyucujici"),
                    rset.getString("jmeno"),
                    rset.getString("prijmeni"),
                    rset.getString("titulpred"),
                    rset.getString("titulza")
            ));

        }
        stmt.close();
        rset.close();
        return list;

    }

    // Vraci seznam ucitelu ktere vede seminar
    public ArrayList<Ucitel> getSeznamSeminar(Predmet predmet) throws SQLException, NullPointerException {
        if (predmet == null) {
            throw new NullPointerException();
        }
        ArrayList<Ucitel> list = new ArrayList<>();
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM sp_v_vyucujici_seminar where sp_v_vyucujici_seminar.zkratka = ?");
        stmt.setString(1, predmet.getZkratka());
        System.out.println(predmet.getZkratka());
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            list.add(new Ucitel(
                    rset.getString("id_vyucujici"),
                    rset.getString("jmeno"),
                    rset.getString("prijmeni"),
                    rset.getString("titulpred"),
                    rset.getString("titulza")
            ));
        }
        stmt.close();
        rset.close();
        return list;

    }

    //// Vraci seznam garantu
    public ArrayList<Ucitel> getSeznamGarantu(Predmet predmet) throws SQLException, NullPointerException {
        if (predmet == null) {
            throw new NullPointerException();
        }
        ArrayList<Ucitel> list = new ArrayList<>();
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM sp_v_vyucujici_garant where sp_v_vyucujici_garant.zkratka = ?");
        stmt.setString(1, predmet.getZkratka());
        System.out.println(predmet.getZkratka());
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            list.add(new Ucitel(
                    rset.getString("id_vyucujici"),
                    rset.getString("jmeno"),
                    rset.getString("prijmeni"),
                    rset.getString("titulpred"),
                    rset.getString("titulza")
            ));
        }
        stmt.close();
        rset.close();
        return list;
    }

    // Vraci katedru predmetu
    public Katedra getKatedruPredmetu(Predmet predmet) throws SQLException, NullPointerException {
        if (predmet == null) {
            throw new NullPointerException();
        }
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM sp_v_katedra where sp_v_katedra.zkratka_katedry = ?");
        stmt.setString(1, predmet.getZkratkaKatedry());
        ResultSet rset = stmt.executeQuery();
        Katedra katedra = null;
        while (rset.next()) {
            katedra = new Katedra(
                    rset.getString("nazev_katedry"),
                    rset.getString("zkratka_katedry"),
                    new Fakulta(rset.getString("zkratka_fakulty"),
                            rset.getString("nazev"),
                            rset.getString("dekan"))
            );
        }
        stmt.close();
        rset.close();
        return katedra;
    }

    // Vkladani noveho predmetu
    public void insertNovyPredmet(Predmet predmet, Ucitel garant) throws SQLException, NullPointerException {
        if (predmet == null) {
            throw new NullPointerException();
        }
        Connection conn = OracleConnector.getConnection();
        CallableStatement stmt = conn.prepareCall("{ call sp_proc_insert_update_predmet(?,?,?,?,?,?,?,?,?,?,?,?)}");
        stmt.setString(1, predmet.getZkratka());
        stmt.setString(2, predmet.getNazev());
        stmt.setString(3, predmet.getPopis());
        stmt.setString(4, predmet.getPocetKreditu());
        stmt.setString(5, predmet.getZpusobZakonceni());
        stmt.setString(6, predmet.getSemestrZimni());
        stmt.setString(7, predmet.getSemestrLetni());
        stmt.setString(8, predmet.getZkratkaKatedry());
        stmt.setString(9, predmet.getDoporucenyRok());
        stmt.setInt(10, predmet.getPrednaskaHod());
        stmt.setInt(11, predmet.getCviceniHod());
        stmt.setInt(12, predmet.getSeminarHod());
        stmt.execute();
        if (garant != null) {
            stmt = conn.prepareCall("{ call sp_proc_insert_update_vyucujici_predmet(?,?,?)}");
            stmt.setString(1, predmet.getZkratka());
            stmt.setString(2, garant.getId());
            stmt.setString(3, "Garant");
            stmt.execute();
        }
        stmt.close();
    }

    // Smazani predmetu
    public void deletePredmet(Predmet predmet) throws SQLException, NullPointerException {
        if (predmet == null) {
            throw new NullPointerException();
        }
        Connection conn;
        conn = OracleConnector.getConnection();
        CallableStatement stmt = conn.prepareCall("{call sp_proc_delete_predmet(?)}");
        stmt.setString(1, predmet.getZkratka());
        stmt.execute();
        stmt.close();
    }

    // Obnovovani predmetu
    public void updatePredmet(Predmet predmet, Ucitel garant) throws SQLException, NullPointerException {
        if (predmet == null) {
            throw new NullPointerException();
        }
        Connection conn;
        conn = OracleConnector.getConnection();
        CallableStatement stmt = conn.prepareCall("{ call sp_proc_insert_update_predmet(?,?,?,?,?,?,?,?,?,?,?,?)}");
        stmt.setString(1, predmet.getZkratka());
        stmt.setString(2, predmet.getNazev());
        stmt.setString(3, predmet.getPopis());
        stmt.setString(4, predmet.getPocetKreditu());
        stmt.setString(5, predmet.getZpusobZakonceni());
        stmt.setString(6, predmet.getSemestrZimni());
        stmt.setString(7, predmet.getSemestrLetni());
        stmt.setString(8, predmet.getZkratkaKatedry());
        stmt.setString(9, predmet.getDoporucenyRok());
        stmt.setInt(10, predmet.getPrednaskaHod());
        stmt.setInt(11, predmet.getCviceniHod());
        stmt.setInt(12, predmet.getSeminarHod());
        stmt.execute();
        if (garant != null) {
            stmt = conn.prepareCall("{ call sp_proc_insert_update_vyucujici_predmet(?,?,?)}");
            stmt.setString(1, predmet.getZkratka());
            stmt.setString(2, garant.getId());
            stmt.setString(3, "Garant");
            stmt.execute();
        }
        stmt.close();
    }

    // Vkladani prednasijiciho
    public void insertPrednasPredmetu(Predmet predmet, Ucitel ucitel) throws SQLException, NullPointerException {
        if (predmet == null || ucitel == null) {
            throw new NullPointerException();
        }
        Connection conn;
        conn = OracleConnector.getConnection();
        CallableStatement stmt = conn.prepareCall("{ call sp_proc_insert_update_vyucujici_predmet(?,?,?)}");
        stmt.setString(1, predmet.getZkratka());
        stmt.setString(2, ucitel.getId());
        stmt.setString(3, "Přednášenící");
        stmt.execute();
        stmt.close();
    }

    //Vkladani cvicicijiho
    public void insertCviciciPredmetu(Predmet predmet, Ucitel ucitel) throws SQLException, NullPointerException {
        if (predmet == null || ucitel == null) {
            throw new NullPointerException();
        }
        Connection conn;
        conn = OracleConnector.getConnection();
        CallableStatement stmt = conn.prepareCall("{ call sp_proc_insert_update_vyucujici_predmet(?,?,?)}");
        stmt.setString(1, predmet.getZkratka());
        stmt.setString(2, ucitel.getId());
        stmt.setString(3, "Cvičící");
        stmt.execute();
        stmt.close();
    }

    //Vkladani seminarici
    public void insertSeminarPredmetu(Predmet predmet, Ucitel ucitel) throws SQLException, NullPointerException {
        if (predmet == null || ucitel == null) {
            throw new NullPointerException();
        }
        Connection conn;
        conn = OracleConnector.getConnection();
        CallableStatement stmt = conn.prepareCall("{ call sp_proc_insert_update_vyucujici_predmet(?,?,?)}");
        stmt.setString(1, predmet.getZkratka());
        stmt.setString(2, ucitel.getId());
        stmt.setString(3, "Seminář");
        stmt.execute();
        stmt.close();
    }

    // Smazani prednasijiciho
    public void deletePrednasPredmetu(Predmet predmet, Ucitel ucitel) throws SQLException, NullPointerException {
        if (predmet == null || ucitel == null) {
            throw new NullPointerException();
        }
        Connection conn;
        conn = OracleConnector.getConnection();
        CallableStatement stmt = conn.prepareCall("{call sp_proc_delete_vyucujici_s_predmetu(?,?,?)}");
        stmt.setString(1, ucitel.getId());
        stmt.setString(2, predmet.getZkratka());
        stmt.setString(3, "Přednášenící");
        stmt.execute();
        stmt.close();
    }

    // smazani cvicicijiho
    public void deleteCviciciPredmetu(Predmet predmet, Ucitel ucitel) throws SQLException, NullPointerException {
        if (predmet == null || ucitel == null) {
            throw new NullPointerException();
        }
        Connection conn;
        conn = OracleConnector.getConnection();
        CallableStatement stmt = conn.prepareCall("{call sp_proc_delete_vyucujici_s_predmetu(?,?,?)}");
        stmt.setString(1, ucitel.getId());
        stmt.setString(2, predmet.getZkratka());
        stmt.setString(3, "Cvičící");
        stmt.execute();
        stmt.close();
    }

    // smazani seminariciho
    public void deleteSeminarPredmetu(Predmet predmet, Ucitel ucitel) throws SQLException, NullPointerException {
        if (predmet == null || ucitel == null) {
            throw new NullPointerException();
        }
        Connection conn;
        conn = OracleConnector.getConnection();
        CallableStatement stmt = conn.prepareCall("{call sp_proc_delete_vyucujici_s_predmetu(?,?,?)}");
        stmt.setString(1, ucitel.getId());
        stmt.setString(2, predmet.getZkratka());
        stmt.setString(3, "Seminář");
        stmt.execute();
        stmt.close();
    }

//====================STUDENT=======================================
    // Metoda vraci seznam studentu
    public ArrayList<Student> getStudentiAll() throws SQLException, NullPointerException {
        ArrayList<Student> studenti = new ArrayList<>();
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM SP_V_STUDENT");
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            System.out.println("1");
            studenti.add(new Student(
                    rset.getString("net_id"),
                    rset.getString("jmeno"),
                    rset.getString("prijmeni"),
                    rset.getString("rocnik"),
                    getObor(rset.getString("id_oboru"), null),
                    getFotoFromBlob(rset.getBlob("obrazek"))));
        }
        stmt.close();
        rset.close();
        return studenti;
    }

    // Vkladani noveho studenta
    public void insertNovyStudent(Student student) throws SQLException, NullPointerException {
        if (student == null) {
            throw new NullPointerException();
        }
        Connection conn;
        conn = OracleConnector.getConnection();
        CallableStatement stmt = conn.prepareCall("{call sp_proc_insert_update_student(?,?,?,?,?,?)}");
        stmt.setString(1, null);
        stmt.setString(2, student.getJmeno());
        stmt.setString(3, student.getPrijmeni());
        stmt.setString(4, student.getRocnik());
        stmt.setString(5, student.getObor().getZkratka());
        stmt.setBinaryStream(6, null);
        stmt.execute();
        stmt.close();
    }

    // obnovovani studenta
    public void updateStudent(Student student) throws SQLException, NullPointerException {
        if (student == null) {
            throw new NullPointerException();
        }
        Connection conn;
        conn = OracleConnector.getConnection();
        CallableStatement stmt = conn.prepareCall("{call sp_proc_insert_update_student(?,?,?,?,?,?)}");
        stmt.setString(1, student.getNetID());
        stmt.setString(2, student.getJmeno());
        stmt.setString(3, student.getPrijmeni());
        stmt.setString(4, student.getRocnik());
        stmt.setString(5, student.getObor().getZkratka());
        stmt.setBinaryStream(6, null);
        stmt.executeQuery();
        conn.commit();
        stmt.close();
    }

    // Smazani studenta
    public void deleteStudent(Student student) throws SQLException, NullPointerException {
        if (student == null) {
            throw new NullPointerException();
        }
        Connection conn;
        conn = OracleConnector.getConnection();
        CallableStatement stmt = conn.prepareCall("{call sp_proc_delete_student(?)}");

        stmt.setString(1, student.getNetID());
        stmt.execute();
        stmt.close();
    }

    // obnovovani foto studenta
    public void updateFotoStudenta(Student student, File f) throws SQLException, FileNotFoundException {
        if (student == null || f == null) {
            throw new NullPointerException();
        }
        Connection conn;
        conn = OracleConnector.getConnection();
        FileInputStream fin = new FileInputStream(f);
        CallableStatement stmt = conn.prepareCall("{call sp_proc_insert_update_foto(?,?,?)}");
        stmt.setString(1, student.getNetID());
        stmt.setString(2, null);
        stmt.setBinaryStream(3, fin, (int) f.length());
        stmt.execute();
        stmt.close();
    }

    //===================OBOR=========================
    // Metoda vraci obory
    public ArrayList<Obor> getOboryAll() throws SQLException, NullPointerException {
        ArrayList<Obor> list = new ArrayList<>();

        Connection conn = OracleConnector.getConnection();

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM SP_V_OBOR");
        ResultSet rset = stmt.executeQuery();

        while (rset.next()) {
            list.add(new Obor(
                    rset.getString("id_oboru"),
                    rset.getString("nazev"),
                    rset.getString("zkratka"),
                    rset.getDate("akreditaceOd"),
                    rset.getDate("akreditaceDo"),
                    rset.getString("formaStudia"),
                    rset.getString("jazyk"),
                    rset.getString("typ"))
            );
        }
        stmt.close();
        rset.close();
        return list;
    }

    // Smazani oboru
    public void deleteObor(Obor obor) throws SQLException, NullPointerException {
        if (obor == null) {
            throw new NullPointerException();
        }
        Connection conn = OracleConnector.getConnection();
        CallableStatement stmt = conn.prepareCall("{call sp_proc_delete_obor(?)}");
        stmt.setString(1, obor.getId());
        stmt.execute();
        stmt.close();
    }

    // metoda vraci obor na zaklade id nebo zkratky
    public Obor getObor(String id, String zkratka) throws SQLException, NullPointerException {
        if (id != null) {
            Connection conn = OracleConnector.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM SP_V_OBOR WHERE ID_OBORU = ?");
            stmt.setString(1, id);
            ResultSet rset = stmt.executeQuery();
            while (rset.next()) {
                return new Obor(rset.getString("id_oboru"),
                        rset.getString("nazev"),
                        rset.getString("zkratka"),
                        rset.getDate("akreditaceOd"),
                        rset.getDate("akreditaceDo"),
                        rset.getString("formaStudia"),
                        rset.getString("jazyk"),
                        rset.getString("typ"));
            }
        } else {
            Connection conn = OracleConnector.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM SP_V_OBOR WHERE zkratka = ?");
            stmt.setString(1, zkratka);
            ResultSet rset = stmt.executeQuery();
            while (rset.next()) {
                return new Obor(rset.getString("id_oboru"),
                        rset.getString("nazev"),
                        rset.getString("zkratka"),
                        rset.getDate("akreditaceOd"),
                        rset.getDate("akreditaceDo"),
                        rset.getString("formaStudia"),
                        rset.getString("jazyk"),
                        rset.getString("typ"));
            }
        }

        throw new NullPointerException();
    }

    // Obnovovani oboru
    public void updateObor(Obor obor) throws SQLException, NullPointerException {
        if (obor == null) {
            throw new NullPointerException();
        }
        Connection conn = OracleConnector.getConnection();
        CallableStatement stmt = conn.prepareCall("{call sp_proc_insert_update_obor(?,?,?,?,?,?,?,?)}");
        stmt.setString(1, obor.getId());
        stmt.setString(2, obor.getNazev());
        stmt.setString(3, obor.getZkratka());
        stmt.setDate(4, obor.getAkrOd());
        stmt.setDate(5, obor.getAkrDo());
        stmt.setString(6, obor.getFormaStudia());
        stmt.setString(7, obor.getJazyk());
        stmt.setString(8, obor.getTyp());
        stmt.execute();
        stmt.close();
    }

    // Vkladani oboru
    public void insertNovyObor(Obor obor) throws SQLException, NullPointerException {
        if (obor == null) {
            throw new NullPointerException();
        }
        Connection conn = OracleConnector.getConnection();
        CallableStatement stmt = conn.prepareCall("{call sp_proc_insert_update_obor(?,?,?,?,?,?,?,?)}");
        stmt.setString(1, null);
        stmt.setString(2, obor.getNazev());
        stmt.setString(3, obor.getZkratka());
        stmt.setDate(4, obor.getAkrOd());
        stmt.setDate(5, obor.getAkrDo());
        stmt.setString(6, obor.getFormaStudia());
        stmt.setString(7, obor.getJazyk());
        stmt.setString(8, obor.getTyp());
        stmt.execute();
        stmt.close();
    }

    //===========================FAKULTA===================================
    // Metoda vraci seznam fakult
    public ArrayList<Fakulta> getFakultyAll() throws SQLException, NullPointerException {
        ArrayList<Fakulta> list = new ArrayList<>();
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM SP_V_FAKULTA_KONTAKT");
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            Fakulta fakulta = new Fakulta(rset.getString("zkratka_fakulty"),
                    rset.getString("nazev"),
                    rset.getString("dekan"));
            fakulta.setKontakt(new Kontakt(rset.getString("telefon"),
                    rset.getString("mobil"),
                    rset.getString("email")));
            list.add(fakulta);
        }
        stmt.close();
        rset.close();
        return list;
    }

    // metoda vraci fakultu na zaklade zkratky
    public Fakulta getFakulta(String zkratka) throws SQLException {
        ArrayList<Fakulta> list = new ArrayList<>();
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM SP_V_FAKULTA_KONTAKT WHERE ZKRATKA_FAKULTY = ?");
        stmt.setString(1, zkratka);
        ResultSet rset = stmt.executeQuery();
        Fakulta fakulta = null;
        while (rset.next()) {
            fakulta = new Fakulta(rset.getString("zkratka_fakulty"),
                    rset.getString("nazev"),
                    rset.getString("dekan"));
            fakulta.setKontakt(new Kontakt(rset.getString("telefon"),
                    rset.getString("mobil"),
                    rset.getString("email")));
            list.add(fakulta);
        }
        stmt.close();
        rset.close();
        return fakulta;
    }

    // vkladani nove fakulty
    public void insertNovaFakulta(Fakulta fakulta) throws SQLException, NullPointerException {
        if (fakulta == null) {
            throw new NullPointerException();
        }
        Connection conn = OracleConnector.getConnection();
        CallableStatement stmt = conn.prepareCall("{call sp_proc_insert_update_fakulta(?,?,?,?,?,?)}");
        stmt.setString(1, fakulta.getZkratka());
        stmt.setString(2, fakulta.getNazev());
        stmt.setString(3, fakulta.getDekan());;
        stmt.setString(4, fakulta.getKontakt().getTelefon());
        stmt.setString(5, fakulta.getKontakt().getMobil());
        stmt.setString(6, fakulta.getKontakt().getEmail());
        stmt.execute();
        stmt.close();
    }

    // obnovovani fakulty
    public void updateFakulta(Fakulta fakulta) throws SQLException, NullPointerException {
        if (fakulta == null) {
            throw new NullPointerException();
        }
        Connection conn = OracleConnector.getConnection();
        CallableStatement stmt = conn.prepareCall("{call sp_proc_insert_update_fakulta(?,?,?,?,?,?)}");
        stmt.setString(1, fakulta.getZkratka());
        stmt.setString(2, fakulta.getNazev());
        stmt.setString(3, fakulta.getDekan());;
        stmt.setString(4, fakulta.getKontakt().getTelefon());
        stmt.setString(5, fakulta.getKontakt().getMobil());
        stmt.setString(6, fakulta.getKontakt().getEmail());
        stmt.execute();
        stmt.close();
    }

    // smazani fakulty
    public void deleteFakultu(Fakulta fakulta) throws SQLException, NullPointerException {
        if (fakulta == null) {
            throw new NullPointerException();
        }
        Connection conn = OracleConnector.getConnection();
        CallableStatement stmt = conn.prepareCall("{call sp_proc_delete_fakulta(?)}");
        stmt.setString(1, fakulta.getZkratka());
        stmt.execute();
        stmt.close();
    }

    //=====================ROZVRH==============================
    // Vraci seznam aktivit predmetu
    public ArrayList<Akce> getAkcePredmetuAll(Predmet predmet) throws SQLException, NullPointerException {
        if (predmet == null) {
            throw new NullPointerException();
        }
        ArrayList<Akce> list = new ArrayList<>();
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM SP_V_PREDMET_ROZVRH_UCEBNA WHERE zkratka = ?");
        stmt.setString(1, predmet.getZkratka());
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            list.add(new Akce(
                    rset.getString("id_aktivity"),
                    EnumTypVyuky.valueOf(rset.getString("typ")),
                    EnumTyden.valueOf(rset.getString("tyden")),
                    EnumDen.valueOf(rset.getString("den")),
                    rset.getString("casod"),
                    rset.getString("casdo"),
                    rset.getInt("kapacita"),
                    rset.getInt("je_platna"),
                    getUcebna(rset.getString("id_ucebny")),
                    getUcitel(rset.getString("id_vyucujici")),
                    getPredmet(rset.getString("zkratka")),
                    rset.getInt("je_blokova"),
                    rset.getDate("datum")));
        }
        stmt.close();
        rset.close();
        return list;
    }

    // vraci vsechni aktivity
    public ArrayList<Akce> getAkceAll() throws SQLException, NullPointerException {
        ArrayList<Akce> list = new ArrayList<>();
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM SP_V_PREDMET_ROZVRH_UCEBNA");
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            list.add(new Akce(
                    rset.getString("id_aktivity"),
                    EnumTypVyuky.valueOf(rset.getString("typ")),
                    EnumTyden.valueOf(rset.getString("tyden")),
                    EnumDen.valueOf(rset.getString("den")),
                    rset.getString("casod"),
                    rset.getString("casdo"),
                    rset.getInt("kapacita"),
                    rset.getInt("je_platna"),
                    getUcebna(rset.getString("id_ucebny")),
                    getUcitel(rset.getString("id_vyucujici")),
                    getPredmet(rset.getString("zkratka")),
                    rset.getInt("je_blokova"),
                    rset.getDate("datum")));
        }
        stmt.close();
        rset.close();
        return list;
    }

    // Vkladani nove aktivity
    public void insertNovaAkce(Akce akce, Ucitel uzivatel) throws SQLException, NullPointerException {
        if (akce == null || uzivatel == null) {
            throw new NullPointerException();
        }
        Connection conn = OracleConnector.getConnection();
        CallableStatement stmt = conn.prepareCall("{ call SP_PROC_INSERT_UPDATE_AKCE(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
        stmt.setString(1, null);
        stmt.setString(2, akce.getTyp().toString());
        stmt.setString(3, akce.getCasOd());
        stmt.setString(4, akce.getCasDo());
        stmt.setString(5, akce.getPredmet().getZkratka());
        stmt.setString(6, akce.getDen().toString());
        stmt.setString(7, akce.getTyden().toString());
        stmt.setInt(8, akce.getPlatnaInt());
        stmt.setString(9, akce.getUcebna().getId());
        stmt.setString(10, akce.getUcitel().getId());
        stmt.setInt(11, akce.getBlokovaInt());
        stmt.setDate(12, akce.getDatum());
        stmt.setInt(13, akce.getKapacita());
        stmt.setInt(14, uzivatel.getPrava());
        stmt.executeQuery();
        conn.commit();
        stmt.close();
    }

    // obnovovani aktivity
    public void updateAkce(Akce akce, Ucitel uzivatel) throws SQLException, NullPointerException {
        if (akce == null || uzivatel == null) {
            throw new NullPointerException();
        }
        Connection conn = OracleConnector.getConnection();
        CallableStatement stmt = conn.prepareCall("{ call SP_PROC_INSERT_UPDATE_AKCE(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
        stmt.setString(1, akce.getId());
        stmt.setString(2, akce.getTyp().toString());
        stmt.setString(3, akce.getCasOd());
        stmt.setString(4, akce.getCasDo());
        stmt.setString(5, akce.getPredmet().getZkratka());
        stmt.setString(6, akce.getDen().toString());
        stmt.setString(7, akce.getTyden().toString());
        stmt.setInt(8, akce.getPlatnaInt());
        stmt.setString(9, akce.getUcebna().getId());
        stmt.setString(10, akce.getUcitel().getId());
        stmt.setInt(11, akce.getBlokovaInt());
        stmt.setDate(12, akce.getDatum());
        stmt.setInt(13, akce.getKapacita());
        stmt.setInt(14, uzivatel.getPrava());
        stmt.executeQuery();
        conn.commit();
        stmt.close();
    }

    // smazani akci
    public void deleteAkce(Akce akce) throws SQLException, NullPointerException {
        if (akce == null) {
            throw new NullPointerException();
        }
        Connection conn = OracleConnector.getConnection();
        CallableStatement stmt = conn.prepareCall("{call sp_proc_delete_akce(?)}");
        stmt.setString(1, akce.getId());
        stmt.execute();
        stmt.close();
    }

    // metoda vraci potrebnu kapacitu predmetu na zaklade predmetu a typu aktivity
    public int getPotrebnuKapacituPredmetu(Predmet predmet, EnumTypVyuky typ) throws SQLException, NullPointerException {
        if (predmet == null || typ == null) {
            throw new NullPointerException();
        }
        int kapacita = 0;
        Connection conn = OracleConnector.getConnection();
        CallableStatement stmt = conn.prepareCall("{ ? = call sp_func_get_potrebnu_kapacitu(?,?,?)}");
        stmt.registerOutParameter(1, Types.INTEGER);
        stmt.setString(2, predmet.getZkratka());
        stmt.setString(3, "A");
        stmt.setString(4, typ.toString());
        stmt.execute();
        kapacita = stmt.getInt(1);
        stmt.close();
        return kapacita;
    }

    //=====================UCEBNA==============================
    // Vraci seznam vsech uceben
    public ArrayList<Ucebna> getUcebnyAll() throws SQLException, NullPointerException {
        ArrayList<Ucebna> list = new ArrayList<>();
        Connection conn = OracleConnector.getConnection();

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM SP_V_UCEBNA");
        ResultSet rset = stmt.executeQuery();

        while (rset.next()) {
            list.add(new Ucebna(
                    rset.getString("id_ucebny"),
                    rset.getString("Cislo"),
                    rset.getInt("kapacita"),
                    EnumTypUcebny.valueOf(rset.getString("typ")),
                    EnumBudova.valueOf(rset.getString("budova"))));
        }
        stmt.close();
        rset.close();
        return list;
    }

    // vraci ucebnu na zaklade id;
    public Ucebna getUcebna(String idUcebny) throws SQLException {
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM SP_V_UCEBNA WHERE (id_ucebny = ?)");
        stmt.setString(1, idUcebny);
        ResultSet rset = stmt.executeQuery();
        Ucebna ucebna = null;
        while (rset.next()) {
            ucebna = new Ucebna(
                    rset.getString("id_ucebny"),
                    rset.getString("Cislo"),
                    rset.getInt("kapacita"),
                    EnumTypUcebny.valueOf(rset.getString("typ")),
                    EnumBudova.valueOf(rset.getString("budova")));
        }
        stmt.close();
        rset.close();
        return ucebna;
    }
    
    // vraci seznam aktivit ucebny
    public ArrayList<Akce> getUcebnaRozvrh(Ucebna ucebna) throws SQLException {
        ArrayList<Akce> list = new ArrayList<>();
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM SP_V_UCEBNA_ROZVRH WHERE (id_ucebny = ?)");
        stmt.setString(1, ucebna.getId());
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            list.add(new Akce(
                    rset.getString("id_aktivity"),
                    EnumTypVyuky.valueOf(rset.getString("typ")),
                    EnumTyden.valueOf(rset.getString("tyden")),
                    EnumDen.valueOf(rset.getString("den")),
                    rset.getString("casod"),
                    rset.getString("casdo"),
                    rset.getInt("kapacita"),
                    rset.getInt("je_platna"),
                    getUcebna(rset.getString("id_ucebny")),
                    getUcitel(rset.getString("id_vyucujici")),
                    getPredmet(rset.getString("zkratka")),
                    rset.getInt("je_blokova"),
                    rset.getDate("datum")));

        }
        stmt.close();
        rset.close();
        return list;
    }

    // vkladani nove ucebny
    public void insertNovaUcebna(Ucebna ucebna) throws SQLException, NullPointerException {
        Connection conn = OracleConnector.getConnection();

        CallableStatement stmt = conn.prepareCall("{ call sp_proc_insert_update_ucebna(?,?,?,?,?)}");
        stmt.setString(1, null);
        stmt.setString(2, ucebna.getCislo());
        stmt.setString(3, ucebna.getKapacita().toString());
        stmt.setString(4, ucebna.getTyp().toString());
        stmt.setString(5, ucebna.getBudova().toString());

        stmt.execute();
        conn.commit();
        stmt.close();
    }

    // obnovovani ucebny
    public void updateUcebna(Ucebna ucebna) throws SQLException, NullPointerException {
        Connection conn = OracleConnector.getConnection();

        CallableStatement stmt = conn.prepareCall("{ call sp_proc_insert_update_ucebna(?,?,?,?,?)}");
        stmt.setString(1, ucebna.getId());
        stmt.setString(2, ucebna.getCislo());
        stmt.setString(3, ucebna.getKapacita().toString());
        stmt.setString(4, ucebna.getTyp().toString());
        stmt.setString(5, ucebna.getBudova().toString());
        stmt.execute();
        conn.commit();
        stmt.close();
    }

    // smazani ucebny
    public void deleteUcebna(Ucebna ucebna) throws SQLException, NullPointerException {
        if (ucebna == null) {
            throw new NullPointerException();
        }
        Connection conn = OracleConnector.getConnection();
        CallableStatement stmt = conn.prepareCall("{call sp_proc_delete_ucebna(?)}");
        stmt.setString(1, ucebna.getId());
        stmt.execute();
        stmt.close();
    }

    //===================================MUJ ROZVRH=====================================
    // Vraci seznam aktivit vyucujiciho
    public ArrayList<Akce> getAkceUcitela(Ucitel ucitel) throws SQLException {
        if (ucitel == null) {
            throw new NullPointerException();
        }
        Connection conn = OracleConnector.getConnection();
        ArrayList<Akce> list = new ArrayList<>();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM SP_V_PREDMET_ROZVRH_UCEBNA WHERE id_vyucujici = ?");
        stmt.setString(1, ucitel.getId());
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            list.add(new Akce(
                    rset.getString("id_aktivity"),
                    EnumTypVyuky.valueOf(rset.getString("typ")),
                    EnumTyden.valueOf(rset.getString("tyden")),
                    EnumDen.valueOf(rset.getString("den")),
                    rset.getString("casod"),
                    rset.getString("casdo"),
                    rset.getInt("kapacita"),
                    rset.getInt("je_platna"),
                    getUcebna(rset.getString("id_ucebny")),
                    getUcitel(rset.getString("id_vyucujici")),
                    getPredmet(rset.getString("zkratka")),
                    rset.getInt("je_blokova"),
                    rset.getDate("datum")));
        }
        stmt.close();
        rset.close();
        return list;
    }

    // vraci seznam predmetu a poctu hodin kde vyucujici je garant
    public ArrayList<String> getUvazekVyucujiciGarant(Ucitel ucitel) throws SQLException {
        if (ucitel == null) {
            throw new NullPointerException();
        }
        ArrayList<String> list = new ArrayList<>();
        ArrayList<Predmet> listPredmetu = getGarantovanyPredmetyUcitela(ucitel);
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM SP_V_UVAZEK_VYUCUJICI WHERE id_vyucujici = ? AND PREDMET_ZKRATKA = ? AND NAZEV = 'Garant'");
        for (Predmet predmet : listPredmetu) {
            stmt.setString(1, ucitel.getId());
            stmt.setString(2, predmet.getZkratka());
            ResultSet rset = stmt.executeQuery();
            while (rset.next()) {
                list.add(predmet.toString() + "");
            }
        }
        return list;

    }
    
    // vraci seznam predmetu a poctu hodin kde vyucujici je prednasijici
    public ArrayList<String> getUvazekVyucujiciPrednas(Ucitel ucitel) throws SQLException {
        if (ucitel == null) {
            throw new NullPointerException();
        }
        ArrayList<String> list = new ArrayList<>();
        ArrayList<Predmet> listPredmetu = getPrednasPredmetyUcitela(ucitel);
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM SP_V_UVAZEK_VYUCUJICI WHERE id_vyucujici = ? AND PREDMET_ZKRATKA = ? AND NAZEV = 'Přednášenící'");
        for (Predmet predmet : listPredmetu) {
            stmt.setString(1, ucitel.getId());
            stmt.setString(2, predmet.getZkratka());
            ResultSet rset = stmt.executeQuery();
            while (rset.next()) {
                list.add(predmet.toString() + " Pocet hod.: " + rset.getString("POCET_HODIN"));
            }
        }
        return list;

    }
    
    // vraci seznam predmetu a poctu hodin kde vyucujici je cvicici
    public ArrayList<String> getUvazekVyucujiciCviceni(Ucitel ucitel) throws SQLException {
        if (ucitel == null) {
            throw new NullPointerException();
        }
        ArrayList<String> list = new ArrayList<>();
        ArrayList<Predmet> listPredmetu = getCviciciPredmetyUcitela(ucitel);
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM SP_V_UVAZEK_VYUCUJICI WHERE id_vyucujici = ? AND PREDMET_ZKRATKA = ? AND NAZEV = 'Cvičící'");
        for (Predmet predmet : listPredmetu) {
            stmt.setString(1, ucitel.getId());
            stmt.setString(2, predmet.getZkratka());
            ResultSet rset = stmt.executeQuery();
            while (rset.next()) {
                list.add(predmet.toString() + " Pocet hod.: " + rset.getString("POCET_HODIN"));
            }
        }
        return list;
    }

    // vraci seznam predmetu a poctu hodin kde vyucujici vede seminar   
    public ArrayList<String> getUvazekVyucujiciSeminar(Ucitel ucitel) throws SQLException {
        if (ucitel == null) {
            throw new NullPointerException();
        }
        ArrayList<String> list = new ArrayList<>();
        ArrayList<Predmet> listPredmetu = getSeminarPredmetyUcitela(ucitel);
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM SP_V_UVAZEK_VYUCUJICI WHERE id_vyucujici = ? AND PREDMET_ZKRATKA = ? AND NAZEV = 'Seminář'");
        for (Predmet predmet : listPredmetu) {
            stmt.setString(1, ucitel.getId());
            stmt.setString(2, predmet.getZkratka());
            ResultSet rset = stmt.executeQuery();
            while (rset.next()) {
                list.add(predmet.toString() + " Pocet hod.: " + rset.getString("POCET_HODIN"));
            }
        }
        return list;
    }

    //===================================NEPLATNE AKCI=====================================
    // vraci seznam neplatnych akci vyucujiciho v pripade ze vyucujici neni admin v jinem pripade vraci seznam vsech neplatnych aktivit
    public ArrayList<Akce> getNeplatneAkci(Ucitel ucitel) throws SQLException {
        if (ucitel == null) {
            throw new NullPointerException();
        }
        Connection conn = OracleConnector.getConnection();
        ArrayList<Akce> list = new ArrayList<>();
        PreparedStatement stmt;
        if (ucitel.getPrava() == 1) {
            stmt = conn.prepareStatement("SELECT * FROM SP_V_PREDMET_ROZVRH_UCEBNA WHERE je_platna = 0");
        } else {
            stmt = conn.prepareStatement("SELECT * FROM SP_V_PREDMET_ROZVRH_UCEBNA WHERE je_platna = 0 AND id_vyucujici = ?");
            stmt.setString(1, ucitel.getId());
        }
        ResultSet rset = stmt.executeQuery();

        while (rset.next()) {
            list.add(new Akce(
                    rset.getString("id_aktivity"),
                    EnumTypVyuky.valueOf(rset.getString("typ")),
                    EnumTyden.valueOf(rset.getString("tyden")),
                    EnumDen.valueOf(rset.getString("den")),
                    rset.getString("casod"),
                    rset.getString("casdo"),
                    rset.getInt("kapacita"),
                    rset.getInt("je_platna"),
                    getUcebna(rset.getString("id_ucebny")),
                    getUcitel(rset.getString("id_vyucujici")),
                    getPredmet(rset.getString("zkratka")),
                    rset.getInt("je_blokova"),
                    rset.getDate("datum")));

        }
        stmt.close();
        rset.close();
        return list;
    }

    //============================STUDIJNI PLAN=======================================
    // Vraci seznam studijnich planu
    public ArrayList<StudPlan> getStudPlanAll() throws SQLException {
        ArrayList<StudPlan> list = new ArrayList<>();
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM SP_V_STUDIJNI_PLAN");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            list.add(new StudPlan(rs.getString("id_planu"),
                    rs.getInt("pocet_studentu"),
                    rs.getString("rok"), getObor(rs.getString("ID_OBORU"), null)));
        }
        return list;
    }

    // vraci seznam stud. planu na zaklade oboru
    public ArrayList<StudPlan> getStudPlanOboru(Obor obor) throws SQLException {
        ArrayList<StudPlan> list = new ArrayList<>();
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM SP_V_STUDIJNI_PLAN WHERE ID_OBORU = ?");
        stmt.setString(1, obor.getId());
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            list.add(new StudPlan(rs.getString("id_planu"),
                    rs.getInt("pocet_studentu"),
                    rs.getString("rok"), getObor(rs.getString("ID_OBORU"), null)));
        }
        return list;
    }

    // vkladani noveho stud.planu
    public void insertNovyStudPlan(StudPlan plan) throws SQLException {
        if (plan == null) {
            throw new NullPointerException();
        }
        Connection conn = OracleConnector.getConnection();
        CallableStatement stmt = conn.prepareCall("{ call sp_proc_insert_update_stud_plan(?,?,?,?)}");
        stmt.setString(1, "-1");
        stmt.setInt(2, plan.getPocetStudentu());
        stmt.setString(3, plan.getRok());
        System.out.println(plan.getObor().getId());
        stmt.setString(4, plan.getObor().getId());
        stmt.execute();
        stmt.close();
    }

    // obnovovani stud. planu
    public void updateStudPlan(StudPlan plan) throws SQLException {
        if (plan == null) {
            throw new NullPointerException();
        }
        Connection conn = OracleConnector.getConnection();
        CallableStatement stmt = conn.prepareCall("{ call sp_proc_insert_update_stud_plan(?,?,?,?)}");
        stmt.setString(1, plan.getId());
        stmt.setInt(2, plan.getPocetStudentu());
        stmt.setString(3, plan.getRok());
        stmt.setString(4, plan.getObor().getId());
        stmt.execute();
        stmt.close();
    }

    // smazani stud.planu
    public void deleteStudPlan(StudPlan plan) throws SQLException {
        if (plan == null) {
            throw new NullPointerException();
        }
        Connection conn = OracleConnector.getConnection();
        CallableStatement stmt = conn.prepareCall("{ call sp_proc_delete_stud_plan(?)}");
        stmt.setString(1, plan.getId());
        stmt.execute();
        stmt.close();

    }

    // vkladani povineho predmetu do stud.planu
    public void insertPovinnyPredmet(StudPlan plan, Predmet predmet, String rocnik) throws SQLException, NullPointerException {
        if (plan == null || predmet == null) {
            throw new NullPointerException();
        }
        Connection conn = OracleConnector.getConnection();
        CallableStatement stmt = conn.prepareCall("{call sp_proc_insert_update_predmet_v_planu(?,?,?,?)}");
        stmt.setString(1, predmet.getZkratka());
        stmt.setString(2, plan.getId());
        stmt.setString(3, "A");
        stmt.setString(4, rocnik);
        stmt.execute();
        stmt.close();
    }
// vkladani povine-volit. predmetu do stud.planu
    public void insertPovinnyVolitPredmet(StudPlan plan, Predmet predmet, String rocnik) throws SQLException, NullPointerException {
        if (plan == null || predmet == null) {
            throw new NullPointerException();
        }
        Connection conn = OracleConnector.getConnection();
        CallableStatement stmt = conn.prepareCall("{call sp_proc_insert_update_predmet_v_planu(?,?,?,?)}");
        stmt.setString(1, predmet.getZkratka());
        stmt.setString(2, plan.getId());
        stmt.setString(3, "B");
        stmt.setString(4, rocnik);
        stmt.execute();
        stmt.close();
    }

    // vkladani volitelneho predmetu do stud.planu
    public void insertVolitelnyPredmet(StudPlan plan, Predmet predmet, String rocnik) throws SQLException, NullPointerException {
        if (plan == null || predmet == null) {
            throw new NullPointerException();
        }
        Connection conn = OracleConnector.getConnection();
        CallableStatement stmt = conn.prepareCall("{call sp_proc_insert_update_predmet_v_planu(?,?,?,?)}");
        stmt.setString(1, predmet.getZkratka());
        stmt.setString(2, plan.getId());
        stmt.setString(3, "C");
        stmt.setString(4, rocnik);
        stmt.execute();
        stmt.close();
    }

    // smazani planu v predmetu
    public void deletePredmetVPlanu(StudPlan plan, Predmet predmet) throws SQLException, NullPointerException {
        if (plan == null || predmet == null) {
            throw new NullPointerException();
        }
        Connection conn = OracleConnector.getConnection();
        CallableStatement stmt = conn.prepareCall("{call sp_proc_delete_predmet_v_planu(?,?)}");
        stmt.setString(1, plan.getId());
        stmt.setString(2, predmet.getZkratka());
        stmt.execute();
    }

    // vraci seznam povinnych predmetu stud.planu
    public ArrayList<Predmet> getPovinnePredmetyPlanu(StudPlan plan) throws SQLException, NullPointerException {
        if (plan == null) {
            throw new NullPointerException();
        }
        ArrayList<Predmet> list = new ArrayList<>();

        Connection conn = OracleConnector.getConnection();

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM SP_V_STUD_PLAN_PREDMET WHERE ID_PLANU = ? AND KATEGORIE = 'A' ");
        stmt.setString(1, plan.getId());
        ResultSet rset = stmt.executeQuery();

        while (rset.next()) {
            list.add(new Predmet(
                    rset.getString("zkratka"),
                    rset.getString("nazev")
            ));

        }
        stmt.close();
        rset.close();
        return list;
    }

    // vraci seznam povine-volit. predmetu stud.planu
    public ArrayList<Predmet> getPovinneVolitPredmetyPlanu(StudPlan plan) throws SQLException, NullPointerException {
        if (plan == null) {
            throw new NullPointerException();
        }
        ArrayList<Predmet> list = new ArrayList<>();

        Connection conn = OracleConnector.getConnection();

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM SP_V_STUD_PLAN_PREDMET WHERE ID_PLANU = ? AND KATEGORIE = 'B' ");
        stmt.setString(1, plan.getId());
        ResultSet rset = stmt.executeQuery();

        while (rset.next()) {
            list.add(new Predmet(
                    rset.getString("zkratka"),
                    rset.getString("nazev")
            ));

        }
        stmt.close();
        rset.close();
        return list;
    }

    // vraci seznam volitelnych predmetu stud.planu
    public ArrayList<Predmet> getVolitelnyPredmetyPlanu(StudPlan plan) throws SQLException, NullPointerException {
        if (plan == null) {
            throw new NullPointerException();
        }
        ArrayList<Predmet> list = new ArrayList<>();

        Connection conn = OracleConnector.getConnection();

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM SP_V_STUD_PLAN_PREDMET WHERE ID_PLANU = ? AND KATEGORIE = 'C' ");
        stmt.setString(1, plan.getId());
        ResultSet rset = stmt.executeQuery();

        while (rset.next()) {
            list.add(new Predmet(
                    rset.getString("zkratka"),
                    rset.getString("nazev")
            ));

        }
        stmt.close();
        rset.close();
        return list;
    }

}
