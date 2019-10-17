/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Daty.EnumAnoNe;
import Daty.EnumDen;
import Daty.EnumTyden;
import Daty.EnumTypVyuky;
import Objekty.*;
import database.DatabaseHelper;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Spike
 */
public class FXMLDocumentVyucujiciController implements Initializable {

    @FXML
    private TabPane tabPane;
    @FXML
    private Tab tabVyucuici;
    @FXML
    private ListView<Ucitel> lvUcitSeznam;
    @FXML
    private TextField tfUcitID;
    @FXML
    private TextField tfUcitPrijmeni;
    @FXML
    private TextField tfUcitTelefon;
    @FXML
    private TextField tfUcitEmail;
    @FXML
    private TextField tfUcitMobil;
    @FXML
    private ListView<Predmet> lvUcitListGarant;
    @FXML
    private TextField tfUcitTitulPred;
    @FXML
    private TextField tfUcitJmeno;
    @FXML
    private TextField tfUcitTitulZa;
    @FXML
    private ListView<Predmet> lvUcitListPrednas;
    @FXML
    private ListView<Predmet> lvUcitListCvic;
    @FXML
    private ListView<Predmet> lvUcitListSem;
    @FXML
    private ImageView imgUcitFoto;
    @FXML
    private Tab tabKatedra;
    @FXML
    private ListView<Katedra> lvPracList;
    @FXML
    private TextField tfPracID;
    @FXML
    private TextField tfPracNazev;
    @FXML
    private Tab tabPredmety;
    @FXML
    private ListView<Predmet> lvPredList;
    @FXML
    private TextField tfPredZkratka;
    @FXML
    private TextField tfPredNazev;
    @FXML
    private TextField tfPredDopRocnik;
    @FXML
    private ListView<Ucitel> lvPredListPredn;
    @FXML
    private ListView<Ucitel> lvPredListCvic;
    @FXML
    private TextArea tfPredPopis;
    @FXML
    private ListView<Ucitel> lvPredListSem;
    @FXML
    private TextField tfPredPocetKred;
    @FXML
    private Tab tabStudenti;
    @FXML
    private ListView<Student> lvStudList;
    @FXML
    private TextField tfStudNetID;
    @FXML
    private TextField tfStudJmeno;
    @FXML
    private TextField tfStudPrijmeni;
    @FXML
    private TextField tfStudRocnik;
    @FXML
    private ImageView imgStudFoto;
    @FXML
    private Tab tabObory;
    @FXML
    private ListView<Obor> lvOboryList;
    @FXML
    private TextField tfOboryID;
    @FXML
    private TextField tfOboryNazev;
    @FXML
    private TextField tfOboryZkratka;
    @FXML
    private ListView<Obor> lvStudPlanObor;
    @FXML
    private ListView<StudPlan> lvStudPlanPlan;
    @FXML
    private TextField tfStudPlanId;
    @FXML
    private TextField tfStudPlanPocet;
    @FXML
    private TextField tfStudPlanRok;
    @FXML
    private ListView<Predmet> lvStudPlanPov;
    @FXML
    private ListView<Predmet> lvStudPlanPovVol;
    @FXML
    private ListView<Predmet> lvStudPlanVol;
    @FXML
    private Tab tabFakulta;
    @FXML
    private ListView<Fakulta> lvFakList;
    @FXML
    private TextField tfFakZkratka;
    @FXML
    private TextField tfFakNazev;
    @FXML
    private TextField tfFakDekan;
    @FXML
    private TextField tfFakMobil;
    @FXML
    private TextField tfFakEmail;
    @FXML
    private TextField tfFakTelefon;
    @FXML
    private Tab tabRozvrh;
    @FXML
    private ListView<Predmet> lvRozvrhPredmet;
    @FXML
    private ListView<Akce> lvRozvrhAkce;
    @FXML
    private TextField tfRozvrhId;
    @FXML
    private TextField tfRozvrhKapacita;
    @FXML
    private CheckBox chbRozvrhPouzePlatny;
    @FXML
    private Tab tabMistnost;
    @FXML
    private ListView<Ucebna> lvMistSeznam;
    @FXML
    private TextField tfMistId;
    @FXML
    private TextField tfMistCislo;
    @FXML
    private TextField tfMistKapacita;
    @FXML
    private ListView<Akce> lvMistObsazenost;
    @FXML
    private CheckBox chbMistPouzePlatny;
    @FXML
    private Tab tabProfil;
    @FXML
    private TextField tfProfilId;
    @FXML
    private TextField tfProfilTitulPred;
    @FXML
    private TextField tfProfilJmeno;
    @FXML
    private TextField tfProfilPrijmeni;
    @FXML
    private TextField tfProfilKatedra;
    @FXML
    private TextField tfProfilTelefon;
    @FXML
    private TextField tfProfilEmail;
    @FXML
    private TextField tfProfilTitulZa;
    @FXML
    private TextField tfProfilMobil;
    @FXML
    private ImageView imgProfilFoto;
    @FXML
    private ListView<String> lvProfilGarant;
    @FXML
    private ListView<String> lvProfilPrednas;
    @FXML
    private ListView<String> lvProfilCvicici;
    @FXML
    private ListView<String> lvProfilSeminar;
    @FXML
    private PasswordField tfProfilNoveHeslo;
    @FXML
    private Tab tabMujRozvrh;
    @FXML
    private ListView<String> lvMujrozvrhPondeli;
    @FXML
    private ListView<String> lvMujrozvrhStreda;
    @FXML
    private ListView<String> lvMujrozvrhUtery;
    @FXML
    private ListView<String> lvMujrozvrhCtvrtek;
    @FXML
    private ListView<String> lvMujrozvrhPatek;
    @FXML
    private ListView<String> lvMujRozvrhBlokova;
    @FXML
    private ComboBox<Predmet> cbMujRozvrhPredmet;
    @FXML
    private ComboBox<String> cbMujRozvrhCasOd;
    @FXML
    private ComboBox<String> cbMujRozvrhCasDo;
    @FXML
    private ComboBox<EnumDen> cbMujRozvrhDen;
    @FXML
    private ComboBox<EnumTyden> cbMujRozvrhTyden;
    @FXML
    private TextField tfMujRozvrhKapacita;
    @FXML
    private ComboBox<Ucebna> cbMujRozvrhMistnost;
    @FXML
    private ComboBox<EnumTypVyuky> cbMujRozvrhTyp;
    @FXML
    private ComboBox<EnumAnoNe> cbMujRozvrhBlokova;
    @FXML
    private DatePicker dpMujRozvrhDatum;
    @FXML
    private Tab tabNeplAkci;
    @FXML
    private ListView<Akce> lvNepAkci;
    @FXML
    private TextField tfUcitKatedra;
    @FXML
    private TextField tfUcitAdministrator;
    @FXML
    private TextField tfPracFakulta;
    @FXML
    private TextField tfPredKatedra;
    @FXML
    private TextField tfPredPrednas;
    @FXML
    private TextField tfPredCvic;
    @FXML
    private TextField tfPredSem;
    @FXML
    private TextField tfPredZimSem;
    @FXML
    private TextField tfPredLetSem;
    @FXML
    private TextField tfPredZpusZak;
    @FXML
    private TextField tfPredGarant;
    @FXML
    private TextField tfStudObor;
    @FXML
    private TextField tfOboryAkrOd;
    @FXML
    private TextField tfOboryAkrDo;
    @FXML
    private TextField tfOboryForma;
    @FXML
    private TextField tfOboryJazyk;
    @FXML
    private TextField tfOboryTypStud;
    @FXML
    private TextField tfStudPlanObor;
    @FXML
    private TextField tfRozvrhTyp;
    @FXML
    private TextField tfRozvrhCasOd;
    @FXML
    private TextField tfRozvrhCasDo;
    @FXML
    private TextField tfRozvrhDen;
    @FXML
    private TextField tfRozvrhTyden;
    @FXML
    private TextField tfRozvrhMistn;
    @FXML
    private TextField tfRozvrhVyucujici;
    @FXML
    private TextField tfRozvrhBlokova;
    @FXML
    private TextField tfRozvrhDatum;
    @FXML
    private TextField tfRozvrhIPlatna;
    @FXML
    private TextField tfMistBudova;
    @FXML
    private TextField tfMistTyp;

    //===========================================LISTY==========================================
    private Ucitel uzivatel;
    private ArrayList<Katedra> listKateder;
    private ArrayList<Fakulta> listFakult;
    private ArrayList<Ucitel> listUcitelu;
    private ArrayList<Obor> listOboru;
    private ArrayList<Student> listStudentu;
    private ArrayList<Predmet> listPredmetu;
    private ArrayList<Ucebna> listUcebna;
    private ArrayList<Akce> listAkce;
    private Predmet vybranyPredmet;
    private Obor vybranyObor;
    private StudPlan vybrabyPlan;
    private DatabaseHelper dh;
    boolean loaded = false;

//==========================================LISTY=========================================
    // Konstruktor
    public FXMLDocumentVyucujiciController(Ucitel uzivatel) {
        try {
            this.uzivatel = uzivatel;
            dh = DatabaseHelper.getInstance();
            listKateder = new ArrayList<>();
            listFakult = new ArrayList<>();
            listUcitelu = new ArrayList<>();
            listOboru = new ArrayList<>();
            listStudentu = new ArrayList<>();
            listPredmetu = new ArrayList<>();
            listUcebna = new ArrayList<>();
            listAkce = new ArrayList<>();
        } catch (SQLException ex) {

            Logger.getLogger(FXMLDocumentVyucujiciController.class.getName()).log(Level.SEVERE, null, ex);
            alertDialog("Chyba", ex);
        }
    }
    // Volani chyb a vyjimek
    private void alertDialog(String message, Exception ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Chyba");
        alert.setHeaderText("Pozor, chyba!");
        alert.setContentText(message);
        if (ex != null) {
            TextArea tA = new TextArea(ex.getLocalizedMessage());
            alert.getDialogPane().setExpandableContent(tA);
        }

        alert.showAndWait();
    }

    // Obnoveni localnich listu z databazi
    private void obnovListy() {
        try {
            lvUcitSeznam.getItems().clear();
            listUcitelu = dh.getUciteliAll();
            lvUcitSeznam.getItems().addAll(listUcitelu);

            listKateder = dh.getKatedryAll();
            lvPracList.getItems().clear();
            lvPracList.getItems().addAll(listKateder);

            listPredmetu = dh.getPredmetyAll();
            lvPredList.getItems().clear();
            lvPredList.getItems().addAll(listPredmetu);
            lvRozvrhPredmet.getItems().clear();
            lvRozvrhPredmet.getItems().addAll(listPredmetu);

            listStudentu = dh.getStudentiAll();
            lvStudList.getItems().clear();
            lvStudList.getItems().addAll(listStudentu);

            listOboru = dh.getOboryAll();
            lvOboryList.getItems().clear();
            lvOboryList.getItems().addAll(listOboru);

            listFakult = dh.getFakultyAll();
            lvFakList.getItems().clear();
            lvFakList.getItems().addAll(listFakult);

            listUcebna = dh.getUcebnyAll();
            lvMistSeznam.getItems().clear();
            lvMistSeznam.getItems().addAll(listUcebna);
            cbMujRozvrhMistnost.getItems().clear();
            cbMujRozvrhMistnost.getItems().addAll(listUcebna);

            tfProfilId.setText(uzivatel.getId());
            tfProfilTitulPred.setText(uzivatel.getTitulPred());
            tfProfilJmeno.setText(uzivatel.getJmeno());
            tfProfilPrijmeni.setText(uzivatel.getPrijmeni());
            tfProfilTitulZa.setText(uzivatel.getTitulZa());
            tfProfilKatedra.setText(uzivatel.getKatedra());
            tfProfilEmail.setText(uzivatel.getKontakt().getEmail());
            tfProfilTelefon.setText(uzivatel.getKontakt().getTelefon());
            tfProfilMobil.setText(uzivatel.getKontakt().getMobil());
            imgProfilFoto.setImage(uzivatel.getFoto());
            lvProfilGarant.getItems().clear();
            lvProfilCvicici.getItems().clear();
            lvProfilSeminar.getItems().clear();
            lvProfilPrednas.getItems().clear();
            lvProfilGarant.getItems().addAll(dh.getUvazekVyucujiciGarant(uzivatel));
            lvProfilPrednas.getItems().addAll(dh.getUvazekVyucujiciPrednas(uzivatel));
            lvProfilCvicici.getItems().addAll(dh.getUvazekVyucujiciCviceni(uzivatel));
            lvProfilSeminar.getItems().addAll(dh.getUvazekVyucujiciSeminar(uzivatel));
            lvStudPlanObor.getItems().addAll(listOboru);

            lvNepAkci.getItems().clear();
            for (Akce akce : dh.getAkceUcitela(uzivatel)) {
                if (akce.getPlatna() == EnumAnoNe.NE) {
                    lvNepAkci.getItems().add(akce);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentVyucujiciController.class.getName()).log(Level.SEVERE, null, ex);
            alertDialog("Chyba", ex);
        }
    }

    // Obnoveni rozvrhu pro aktualniho uzivatela
    private void obnovMujRozvrh() {
        try {
            lvMujrozvrhPondeli.getItems().clear();
            lvMujrozvrhUtery.getItems().clear();
            lvMujrozvrhStreda.getItems().clear();
            lvMujrozvrhCtvrtek.getItems().clear();
            lvMujrozvrhPatek.getItems().clear();
            lvMujRozvrhBlokova.getItems().clear();
            for (Akce akce : dh.getAkceUcitela(uzivatel)) {
                if (akce.getBlokova() == EnumAnoNe.NE && akce.getPlatna() == EnumAnoNe.ANO) {
                    if (akce.getDen() == EnumDen.Pondělí) {
                        lvMujrozvrhPondeli.getItems().add(akce.toString());
                        continue;
                    }
                    if (akce.getDen() == EnumDen.Úterý) {
                        lvMujrozvrhUtery.getItems().add(akce.toString());
                        continue;
                    }
                    if (akce.getDen() == EnumDen.Středa) {
                        lvMujrozvrhStreda.getItems().add(akce.toString());
                        continue;
                    }
                    if (akce.getDen() == EnumDen.Čtvrtek) {
                        lvMujrozvrhCtvrtek.getItems().add(akce.toString());
                        continue;
                    }
                    if (akce.getDen() == EnumDen.Pátek) {
                        lvMujrozvrhPatek.getItems().add(akce.toString());
                        continue;
                    }
                }
                if (akce.getBlokova() == EnumAnoNe.ANO && akce.getPlatna() == EnumAnoNe.ANO) {
                    lvMujRozvrhBlokova.getItems().add(akce.toString());
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
            alertDialog("chyba", ex);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        obnovListy();
        obnovMujRozvrh();
        for (int i = 0; i < 24; i++) {
            String s = i + ":00";
            cbMujRozvrhCasDo.getItems().add(s);
            cbMujRozvrhCasOd.getItems().add(s);
        }

        dpMujRozvrhDatum.setValue(LocalDate.now());
        cbMujRozvrhBlokova.getItems().addAll(EnumAnoNe.values());
        cbMujRozvrhDen.getItems().addAll(EnumDen.values());
        cbMujRozvrhTyden.getItems().addAll(EnumTyden.values());
        cbMujRozvrhTyp.getItems().addAll(EnumTypVyuky.values());

        //======================================LISTENERY=========================================================
        //Listener pro seznam ucitelu
        lvUcitSeznam.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (lvUcitSeznam.getSelectionModel().getSelectedItem() != null) {
                    try {
                        Ucitel newValue = lvUcitSeznam.getSelectionModel().getSelectedItem();
                        lvUcitListGarant.getItems().clear();
                        lvUcitListGarant.getItems().addAll(dh.getGarantovanyPredmetyUcitela(newValue));

                        lvUcitListPrednas.getItems().clear();
                        lvUcitListPrednas.getItems().addAll(dh.getPrednasPredmetyUcitela(newValue));

                        lvUcitListCvic.getItems().clear();
                        lvUcitListCvic.getItems().addAll(dh.getCviciciPredmetyUcitela(newValue));

                        lvUcitListSem.getItems().clear();
                        lvUcitListSem.getItems().addAll(dh.getSeminarPredmetyUcitela(newValue));

                        tfUcitID.setText(newValue.getId());
                        tfUcitJmeno.setText(newValue.getJmeno());
                        tfUcitPrijmeni.setText(newValue.getPrijmeni());
                        tfUcitTitulPred.setText(newValue.getTitulPred());
                        tfUcitTitulZa.setText(newValue.getTitulZa());
                        tfUcitKatedra.setText(newValue.getKatedra());
                        if (newValue.getPrava() == 1) {
                            tfUcitAdministrator.setText("ANO");
                        } else {
                            tfUcitAdministrator.setText("NE");
                        }
                        imgUcitFoto.setImage(newValue.getFoto());
                        if (dh.getKontaktUcitela(newValue) != null) {
                            newValue.setKontakt(dh.getKontaktUcitela(newValue));
                            tfUcitMobil.setText(dh.getKontaktUcitela(newValue).getMobil());
                            tfUcitTelefon.setText(dh.getKontaktUcitela(newValue).getTelefon());
                            tfUcitEmail.setText(dh.getKontaktUcitela(newValue).getEmail());
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                        alertDialog("Chyba", ex);
                    }
                }
            }
        });
        
        // Listener pro seznam kateder
        lvPracList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (lvPracList.getSelectionModel().getSelectedItem() != null) {
                    Katedra katedra = lvPracList.getSelectionModel().getSelectedItem();
                    tfPracID.setText(katedra.getZkratka());
                    tfPracNazev.setText(katedra.getNazev());
                    tfPracFakulta.setText(katedra.getFakulta().getNazev());
                }
            }

        });

        // Listener pro seznam predmetu
        lvPredList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (lvPredList.getSelectionModel().getSelectedItem() != null) {
                    try {
                        Predmet predmet = lvPredList.getSelectionModel().getSelectedItem();
                        vybranyPredmet = predmet;
                        lvPredListPredn.getItems().clear();
                        lvPredListPredn.getItems().addAll(dh.getSeznamPrednasicich(predmet));

                        lvPredListCvic.getItems().clear();
                        lvPredListCvic.getItems().addAll(dh.getSeznamCvicicich(predmet));

                        lvPredListSem.getItems().clear();
                        lvPredListSem.getItems().addAll(dh.getSeznamSeminar(predmet));

                        tfPredKatedra.setText(predmet.getZkratkaKatedry());
                        tfPredNazev.setText(predmet.getNazev());
                        tfPredZkratka.setText(predmet.getZkratka());
                        tfPredPrednas.setText(predmet.getPrednaskaHod() + "");
                        tfPredCvic.setText(predmet.getCviceniHod() + "");
                        tfPredPrednas.setText(predmet.getSeminarHod() + "");
                        tfPredDopRocnik.setText(predmet.getDoporucenyRok());
                        tfPredPopis.setText(predmet.getPopis());
                        tfPredPocetKred.setText(predmet.getPocetKreditu());
                        tfPredZpusZak.setText(predmet.getZpusobZakonceni());
                        if (predmet.getSemestrLetni().equals("A")) {
                            tfPredZimSem.setText("ANO");
                        } else {
                            tfPredZimSem.setText("NE");
                        }
                        if (predmet.getSemestrZimni().equals("A")) {
                            tfPredLetSem.setText("ANO");
                        } else {
                            tfPredLetSem.setText("NE");
                        }
                        if (!dh.getSeznamGarantu(predmet).isEmpty()) {
                            tfPredGarant.setText(dh.getSeznamGarantu(predmet).get(0).toString());
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                        alertDialog("Chyba", ex);
                    }
                }
            }
        });

        // listener pro list studentu
        lvStudList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (lvStudList.getSelectionModel().getSelectedItem() != null) {
                    Student student = lvStudList.getSelectionModel().getSelectedItem();
                    tfStudNetID.setText(student.getNetID());
                    tfStudJmeno.setText(student.getJmeno());
                    tfStudPrijmeni.setText(student.getPrijmeni());
                    tfStudRocnik.setText(student.getRocnik());
                    tfStudObor.setText(student.getObor().toString());
                    imgStudFoto.setImage(student.getFoto());
                }

            }
        });

        // Listener pro list oboru
        lvOboryList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (lvOboryList.getSelectionModel().getSelectedItem() != null) {
                    Obor obor = lvOboryList.getSelectionModel().getSelectedItem();
                    vybranyObor = obor;
                    tfOboryID.setText(obor.getId());
                    tfOboryNazev.setText(obor.getNazev());
                    tfOboryZkratka.setText(obor.getZkratka());
                    tfOboryAkrOd.setText(obor.getAkrOd().toString());
                    tfOboryAkrDo.setText(obor.getAkrDo().toString());
                    tfOboryForma.setText(obor.getFormaStudia());
                    tfOboryJazyk.setText(obor.getJazyk());
                    tfOboryTypStud.setText(obor.getTyp());
                }
            }
        });

        // Listener pro list fakult
        lvFakList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (lvFakList.getSelectionModel().getSelectedItem() != null) {
                    Fakulta fakulta = lvFakList.getSelectionModel().getSelectedItem();
                    tfFakNazev.setText(fakulta.getNazev());
                    tfFakZkratka.setText(fakulta.getZkratka());
                    tfFakDekan.setText(fakulta.getDekan());
                    tfFakEmail.setText(fakulta.getKontakt().getEmail());
                    tfFakMobil.setText(fakulta.getKontakt().getMobil());
                    tfFakTelefon.setText(fakulta.getKontakt().getTelefon());
                }
            }
        });

        //Listener rozvrhu
        lvRozvrhPredmet.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (lvRozvrhPredmet.getSelectionModel().getSelectedItem() != null) {
                    try {

                        listAkce = dh.getAkcePredmetuAll(lvRozvrhPredmet.getSelectionModel().getSelectedItem());
                        lvRozvrhAkce.getItems().clear();
                        if (chbRozvrhPouzePlatny.isSelected()) {
                            for (Akce akce : listAkce) {
                                if (akce.getPlatna() == EnumAnoNe.ANO) {
                                    lvRozvrhAkce.getItems().add(akce);
                                }
                            }
                        } else {
                            lvRozvrhAkce.getItems().addAll(listAkce);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                        alertDialog("Chyba", ex);
                    }
                }
            }

        });

        // Listener pro rozvrhovu akce
        lvRozvrhAkce.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (lvRozvrhAkce.getSelectionModel().getSelectedItem() != null) {
                    Akce akce = lvRozvrhAkce.getSelectionModel().getSelectedItem();
                    tfRozvrhId.setText(akce.getId());
                    tfRozvrhDen.setText(akce.getDen().toString());
                    tfRozvrhTyden.setText(akce.getTyden().toString());
                    tfRozvrhTyp.setText(akce.getTyp().toString());
                    tfRozvrhCasOd.setText(akce.getCasOd());
                    tfRozvrhCasDo.setText(akce.getCasDo());
                    tfRozvrhKapacita.setText(akce.getKapacita() + "");
                    tfRozvrhIPlatna.setText(akce.getPlatna().toString());
                    tfRozvrhVyucujici.setText(akce.getUcitel().toString());
                    tfRozvrhMistn.setText(akce.getUcebna().toString());
                    tfRozvrhBlokova.setText(akce.getBlokova().toString());
                    tfRozvrhDatum.setText(akce.getDatum().toString() + "");
                }
            }

        });

        // Listener pro ucebny
        lvMistSeznam.setOnMouseClicked(e -> {
            if (lvMistSeznam.getSelectionModel().getSelectedItem() != null) {
                Ucebna uc = lvMistSeznam.getSelectionModel().getSelectedItem();
                tfMistId.setText(uc.getId());
                tfMistCislo.setText(uc.getCislo());
                tfMistKapacita.setText(uc.getKapacita().toString());
                tfMistBudova.setText(uc.getBudova().toString());
                tfMistTyp.setText(uc.getTyp().toString());
                lvMistObsazenost.getItems().clear();
                try {
                    if (chbMistPouzePlatny.isSelected()) {
                        for (Akce akce : dh.getUcebnaRozvrh(lvMistSeznam.getSelectionModel().getSelectedItem())) {
                            if (akce.getPlatna() == EnumAnoNe.ANO) {
                                lvMistObsazenost.getItems().add(akce);
                            }
                        }
                    } else {
                        lvMistObsazenost.getItems().addAll(dh.getUcebnaRozvrh(lvMistSeznam.getSelectionModel().getSelectedItem()));
                    }
                } catch (SQLException | NullPointerException ex) {
                    Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                    alertDialog("Chyba", ex);
                }
            }
        });

        // Listener pro seznam oboru v tabu Studijni plan
        lvStudPlanObor.setOnMouseClicked(e -> {
            if (lvStudPlanObor.getSelectionModel().getSelectedItem() != null) {
                try {
                    lvStudPlanPlan.getItems().clear();
                    lvStudPlanPlan.getItems().addAll(dh.getStudPlanOboru(lvStudPlanObor.getSelectionModel().getSelectedItem()));
                } catch (SQLException ex) {
                    alertDialog("Chyba", ex);
                    Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        // Listener pro seznam studijnich planu
        lvStudPlanPlan.setOnMouseClicked(e -> {
            if (lvStudPlanPlan.getSelectionModel().getSelectedItem() != null) {
                try {
                    vybrabyPlan = lvStudPlanPlan.getSelectionModel().getSelectedItem();
                    tfStudPlanId.setText(lvStudPlanPlan.getSelectionModel().getSelectedItem().getId());
                    tfStudPlanPocet.setText(lvStudPlanPlan.getSelectionModel().getSelectedItem().getPocetStudentu() + "");
                    tfStudPlanRok.setText(lvStudPlanPlan.getSelectionModel().getSelectedItem().getRok());
                    tfStudPlanObor.setText(vybrabyPlan.getObor().toString());
                    lvStudPlanPov.getItems().clear();
                    lvStudPlanPov.getItems().addAll(dh.getPovinnePredmetyPlanu(lvStudPlanPlan.getSelectionModel().getSelectedItem()));
                    lvStudPlanPovVol.getItems().clear();
                    lvStudPlanPovVol.getItems().addAll(dh.getPovinneVolitPredmetyPlanu(lvStudPlanPlan.getSelectionModel().getSelectedItem()));
                    lvStudPlanVol.getItems().clear();
                    lvStudPlanVol.getItems().addAll(dh.getVolitelnyPredmetyPlanu(lvStudPlanPlan.getSelectionModel().getSelectedItem()));
                } catch (SQLException | NullPointerException ex) {
                    Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        loaded = true;
    }

    @FXML
    private void tabVyucuiciSelect(Event event) {
    }

    @FXML
    private void tabKatedraSelected(Event event) {
    }

    @FXML
    private void tabPredmetySelected(Event event) {
    }

    @FXML
    private void tabStudentiSelected(Event event) {
    }

    @FXML
    private void tabOborySelected(Event event) {
    }

    @FXML
    private void tabFakultaSelected(Event event) {
    }

    @FXML
    private void tabRozvrhSelected(Event event) {
    }

    @FXML
    private void tabMistnostSelected(Event event) {
    }

    @FXML
    private void tabProfilSelected(Event event) {
    }

    @FXML
    private void tabMujRozvrhSelected(Event event) {
    }

    // Obnoveni tabu neplatnych akci
    @FXML
    private void tabNeplAkciSelected(Event event) {
        if (tabNeplAkci.isSelected() && loaded) {
            try {
                lvNepAkci.getItems().clear();
                for (Akce akce : dh.getNeplatneAkci(uzivatel)) {
                    lvNepAkci.getItems().add(akce);
                }

            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                alertDialog("Chyba", ex);
            }
        }
    }

    // Zobrazeni pouze platnych akci v tabu Rozvrh
    @FXML
    private void chbRozvrhPouzePlatnyClick(ActionEvent event) {
        lvRozvrhAkce.getItems().clear();
        if (chbRozvrhPouzePlatny.isSelected()) {
            for (Akce akce : listAkce) {
                if (akce.getPlatna() == EnumAnoNe.ANO) {
                    lvRozvrhAkce.getItems().add(akce);
                }
            }
        } else {
            lvRozvrhAkce.getItems().addAll(listAkce);
        }
    }

    // Zobrazeni pouze platnych akci v tabu Mistnosti
    @FXML
    private void chbMistPouzePlatnyClick(ActionEvent event) {
        if (lvMistSeznam.getSelectionModel().getSelectedItem() != null) {
            try {
                lvMistObsazenost.getItems().clear();
                if (chbMistPouzePlatny.isSelected()) {
                    for (Akce akce : dh.getUcebnaRozvrh(lvMistSeznam.getSelectionModel().getSelectedItem())) {
                        if (akce.getPlatna() == EnumAnoNe.ANO) {
                            lvMistObsazenost.getItems().add(akce);
                        }
                    }
                } else {
                    lvMistObsazenost.getItems().addAll(dh.getUcebnaRozvrh(lvMistSeznam.getSelectionModel().getSelectedItem()));
                }
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                alertDialog("Chyba", ex);
            }
        }
    }

    // Ukladani zmen pro aktualniho uzivatela
    @FXML
    private void btProfilUloz(ActionEvent event) {
        try {
            uzivatel.setJmeno(tfProfilJmeno.getText());
            uzivatel.setPrijmeni(tfProfilPrijmeni.getText());
            uzivatel.setTitulPred(tfProfilTitulPred.getText());
            uzivatel.setTitulZa(tfProfilTitulZa.getText());
            uzivatel.getKontakt().setEmail(tfProfilEmail.getText());
            uzivatel.getKontakt().setTelefon(tfProfilTelefon.getText());
            uzivatel.getKontakt().setMobil(tfProfilMobil.getText());
            dh.updateUcitel(uzivatel);
        } catch (SQLException | NullPointerException ex) {
            Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
            alertDialog("Chyba", ex);
        }
    }

    // Ukladani hesla aktualniho uzivatela
    @FXML
    private void bProfilUlozHeslo(ActionEvent event) {
        try {
            dh.updateHesloUcitela(uzivatel, tfProfilNoveHeslo.getText());
            tfProfilNoveHeslo.clear();
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
            alertDialog("Chyba", ex);
        }
    }

    // Ukaladni noveho foto uzivatela
    @FXML
    private void bMujRozvrhPridatFoto(ActionEvent event) {
        if (uzivatel != null) {

            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Obrazek", "*.jpg", "*.png", "*.jpeg"));
            File f = fc.showOpenDialog(null);
            if (f != null) {
                try {
                    dh.updateFotoUcitela(uzivatel, f);
                    imgProfilFoto.setImage(new Image(f.toURI().toString()));
                } catch (SQLException | FileNotFoundException ex) {
                    Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                    alertDialog("Chyba", ex);
                }
            }
        }
    }

    // Ukladani nove akci uzivatela
    @FXML
    private void bMujRozvrhUloz(ActionEvent event) {
        try {
            EnumTypVyuky typ = cbMujRozvrhTyp.getValue();
            String casOd = cbMujRozvrhCasOd.getValue();
            String casDo = cbMujRozvrhCasDo.getValue();
            int kapacita = Integer.parseInt(tfMujRozvrhKapacita.getText());
            EnumDen den = cbMujRozvrhDen.getValue();
            EnumTyden tyden = cbMujRozvrhTyden.getValue();
            int blokova;
            if (cbMujRozvrhBlokova.getValue() == EnumAnoNe.ANO) {
                blokova = 1;
            } else {
                blokova = 0;
            }
            Date datum = Date.valueOf(dpMujRozvrhDatum.getValue());
            Akce akce = new Akce(null, typ, tyden, den, casOd, casDo, kapacita, 0, cbMujRozvrhMistnost.getValue(), uzivatel, cbMujRozvrhPredmet.getValue(), blokova, datum);
            dh.insertNovaAkce(akce, uzivatel);
        } catch (SQLException | NullPointerException ex) {
            Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
            alertDialog("Chyba", ex);
        }
    }

    // Listener comboboxu vyberu typu akci
    @FXML
    private void cbMujRozvrhTypClick(ActionEvent event) {
        try {
            if (cbMujRozvrhTyp.getValue() == EnumTypVyuky.Cvičení) {
                cbMujRozvrhPredmet.getItems().clear();
                cbMujRozvrhPredmet.getItems().addAll(dh.getCviciciPredmetyUcitela(uzivatel));
            }
            if (cbMujRozvrhTyp.getValue() == EnumTypVyuky.Přednáška) {
                cbMujRozvrhPredmet.getItems().clear();
                cbMujRozvrhPredmet.getItems().addAll(dh.getPrednasPredmetyUcitela(uzivatel));
            }
            if (cbMujRozvrhTyp.getValue() == EnumTypVyuky.Seminář) {
                cbMujRozvrhPredmet.getItems().clear();
                cbMujRozvrhPredmet.getItems().addAll(dh.getSeminarPredmetyUcitela(uzivatel));
            }
        } catch (SQLException | NullPointerException ex) {
            Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
            alertDialog("Chyba", ex);
        }
    }

}
