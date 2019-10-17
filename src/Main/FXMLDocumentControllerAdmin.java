/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Daty.EnumAnoNe;
import Daty.EnumBudova;
import Daty.EnumDen;
import Daty.EnumFormaStudia;
import Daty.EnumJazyk;
import Daty.EnumTyden;
import Daty.EnumTypImport;
import Daty.EnumTypVyuky;
import Daty.EnumTypStudia;
import Daty.EnumTypUcebny;
import Daty.EnumZpusobZakonceni;
import Objekty.Akce;
import Objekty.Fakulta;
import Objekty.Obor;
import Objekty.Predmet;
import Objekty.Student;
import Objekty.Ucitel;
import database.DatabaseHelper;
import Objekty.Katedra;
import Objekty.Kontakt;
import Objekty.StudPlan;
import Objekty.Ucebna;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Spinner;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Pair;

/**
 *
 * @author maksim
 */
public class FXMLDocumentControllerAdmin implements Initializable {

    private Ucitel uzivatel;
    private Ucitel aktUzivatel;

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
    private TextField tfUcitTitulPred;
    @FXML
    private TextField tfUcitJmeno;
    @FXML
    private TextField tfUcitTitulZa;
    @FXML
    private ChoiceBox<Katedra> cbUcitKatedra;
    @FXML
    private ComboBox<EnumAnoNe> cbUcitPrava;
    @FXML
    private ListView<Katedra> lvPracList;
    @FXML
    private TextField tfPracID;
    @FXML
    private ComboBox<Fakulta> cbPracFak;
    @FXML
    private ListView<Predmet> lvPredList;
    @FXML
    private TextField tfPredZkratka;
    @FXML
    private TextField tfPredNazev;
    @FXML
    private ComboBox<EnumZpusobZakonceni> cbPredZpusobZak;
    @FXML
    private TextField tfPredDopRocnik;
    @FXML
    private ComboBox<Ucitel> cbPredGarant;
    @FXML
    private ListView<Ucitel> lvPredListPredn;
    @FXML
    private ListView<Ucitel> lvPredListCvic;
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
    private ComboBox<Obor> cbStudObor;
    @FXML
    private ListView<Obor> lvOboryList;
    @FXML
    private TextField tfOboryID;
    @FXML
    private TextField tfOboryNazev;
    @FXML
    private TextField tfOboryZkratka;
    @FXML
    private ComboBox<EnumFormaStudia> cbOboryFormaStud;
    @FXML
    private ComboBox<EnumJazyk> cbOboryJazyk;
    @FXML
    private ListView<Predmet> lvUcitListGarant;
    @FXML
    private ListView<Predmet> lvUcitListPrednas;
    @FXML
    private ListView<Predmet> lvUcitListCvic;
    @FXML
    private TextField tfPracNazev;
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
    private ListView<Predmet> lvUcitListSem;
    @FXML
    private TextArea tfPredPopis;
    @FXML
    private ListView<Ucitel> lvPredListSem;
    @FXML
    private ComboBox<EnumAnoNe> cbPredZimSem;
    @FXML
    private ComboBox<EnumAnoNe> cbPredLetSem;
    @FXML
    private TextField tfPredPocetKred;
    @FXML
    private ComboBox<Katedra> cbPredKatedra;
    @FXML
    private ComboBox<Integer> cbPredPrednasHodin;
    @FXML
    private ComboBox<Integer> cbPredCvicHodin;
    @FXML
    private ComboBox<Integer> cbPredSemHodin;
    @FXML
    private ComboBox<EnumTypStudia> cbOborTypStudia;
    @FXML
    private DatePicker dpOboryAkrOd;
    @FXML
    private DatePicker dpOboryAkrDo;
    @FXML
    private ListView<Fakulta> lvFakList;
    @FXML
    private ListView<Predmet> lvRozvrhPredmet;
    @FXML
    private ListView<Akce> lvRozvrhAkce;
    @FXML
    private TextField tfRozvrhId;
    @FXML
    private ComboBox<EnumDen> cbRozvrhDen;
    @FXML
    private ComboBox<EnumTyden> cbRozvrhTyden;
    @FXML
    private TextField tfRozvrhKapacita;
    @FXML
    private ComboBox<EnumTypVyuky> cbRozvrhTyp;
    @FXML
    private ComboBox<String> cbRozvrhCasOd;
    @FXML
    private ComboBox<String> cbRozvrhCasDo;
    @FXML
    private ImageView imgUcitFoto;
    @FXML
    private ImageView imgStudFoto;
    @FXML
    private ComboBox<Ucebna> cbRozvrhUcebna;
    @FXML
    private ComboBox<Ucitel> cbRozvrhVyucujici;
    @FXML
    private ListView<Ucebna> lvMistSeznam;
    @FXML
    private TextField tfMistId;
    @FXML
    private TextField tfMistCislo;
    @FXML
    private TextField tfMistKapacita;
    @FXML
    private ComboBox<EnumBudova> cbMistBudova;
    @FXML
    private ComboBox<EnumTypUcebny> cbMistTyp;
    @FXML
    private ListView<Akce> lvMistObsazenost;
    @FXML
    private ComboBox<EnumAnoNe> cbRozvrhPlatny;
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
    private TabPane tabPane;
    @FXML
    private PasswordField tfVyucujiciHeslo;
    @FXML
    private PasswordField tfProfilNoveHeslo;
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
    private CheckBox chbProfilPrepnout;
    @FXML
    private ComboBox<Ucitel> cbProfilUzivatel;
    @FXML
    private ListView<Akce> lvNepAkci;
    @FXML
    private ComboBox<EnumAnoNe> cbNepAkciPlatna;
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
    private CheckBox chbRozvrhPouzePlatny;
    @FXML
    private CheckBox chbMistPouzePlatny;
    @FXML
    private Tab tabVyucuici;
    @FXML
    private Tab tabKatedra;
    @FXML
    private Tab tabPredmety;
    @FXML
    private Tab tabStudenti;
    @FXML
    private Tab tabObory;
    @FXML
    private Tab tabFakulta;
    @FXML
    private Tab tabRozvrh;
    @FXML
    private Tab tabMistnost;
    @FXML
    private Tab tabProfil;
    @FXML
    private Tab tabMujRozvrh;
    @FXML
    private Tab tabNeplAkci;
    @FXML
    private ListView<Object> lvImport;
    @FXML
    private ComboBox<EnumTypImport> cbImportTyp;
    @FXML
    ComboBox<Obor> cbStudPlanObor;
    @FXML
    private ComboBox<EnumAnoNe> cbRozvrhBlokova;
    @FXML
    private DatePicker dpRozvrhDatum;
//===========================================LISTY==========================================
    ArrayList<Katedra> listKateder;
    ArrayList<Fakulta> listFakult;
    ArrayList<Ucitel> listUcitelu;
    ArrayList<Obor> listOboru;
    ArrayList<Student> listStudentu;
    ArrayList<Predmet> listPredmetu;
    ArrayList<Ucebna> listUcebna;
    ArrayList<Akce> listAkce;
    Predmet vybranyPredmet;
    Obor vybranyObor;
    StudPlan vybrabyPlan;
    private DatabaseHelper dh;
    boolean loaded = false;

//==========================================LISTY=========================================
    @FXML
    private TextField tfStudPlanId;
    @FXML
    private TextField tfStudPlanPocet;
    @FXML
    private TextField tfStudPlanRok;
    @FXML
    private ListView<Obor> lvStudPlanObor;
    @FXML
    private ListView<StudPlan> lvStudPlanPlan;
    @FXML
    private ListView<Predmet> lvStudPlanPov;
    @FXML
    private ListView<Predmet> lvStudPlanPovVol;
    @FXML
    private ListView<Predmet> lvStudPlanVol;
    @FXML
    private ComboBox<EnumAnoNe> cbMujRozvrhBlokova;
    @FXML
    private DatePicker dpMujRozvrhDatum;
    @FXML
    private Label lRozvrhMistPrednas;
    @FXML
    private Label lRozvrhMistCvic;
    @FXML
    private Label lRozvrhMistSem;
    @FXML
    private ListView<Ucitel> lvPracVyucujici;
    @FXML
    private ListView<String> lvPracRozvrh;

    // Konstruktor
    public FXMLDocumentControllerAdmin(Ucitel uzivatel) {
        try {
            this.uzivatel = uzivatel;
            this.aktUzivatel = uzivatel;
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
            Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
            alertDialog("Chyba", ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        obnovListy();
        obnovMujRozvrh();
        cbPredLetSem.getItems().addAll(EnumAnoNe.values());
        cbPredZimSem.getItems().addAll(EnumAnoNe.values());
        cbPredZpusobZak.getItems().addAll(EnumZpusobZakonceni.values());
        cbOborTypStudia.getItems().addAll(EnumTypStudia.values());
        cbOboryJazyk.getItems().addAll(EnumJazyk.values());
        cbOboryFormaStud.getItems().addAll(EnumFormaStudia.values());
        cbRozvrhDen.getItems().addAll(EnumDen.values());
        cbRozvrhTyden.getItems().addAll(EnumTyden.values());
        cbRozvrhTyp.getItems().addAll(EnumTypVyuky.values());
        cbMistTyp.getItems().addAll(EnumTypUcebny.values());
        cbMistBudova.getItems().addAll(EnumBudova.values());
        cbRozvrhVyucujici.getItems().addAll(listUcitelu);
        cbRozvrhUcebna.getItems().addAll(listUcebna);
        cbRozvrhPlatny.getItems().addAll(EnumAnoNe.values());
        cbNepAkciPlatna.getItems().addAll(EnumAnoNe.values());
        cbMujRozvrhDen.getItems().addAll(EnumDen.values());
        cbMujRozvrhTyden.getItems().addAll(EnumTyden.values());
        cbMujRozvrhTyp.getItems().addAll(EnumTypVyuky.values());
        cbUcitPrava.getItems().addAll(EnumAnoNe.values());
        cbImportTyp.getItems().addAll(EnumTypImport.values());
        cbStudPlanObor.getItems().addAll(listOboru);
        cbRozvrhBlokova.getItems().addAll(EnumAnoNe.values());
        cbMujRozvrhBlokova.getItems().addAll(EnumAnoNe.values());
        dpMujRozvrhDatum.setValue(LocalDate.now());
        dpRozvrhDatum.setValue(LocalDate.now());
        listAkce = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            String s = i + ":00";
            cbRozvrhCasOd.getItems().add(s);
            cbRozvrhCasDo.getItems().add(s);
            cbMujRozvrhCasDo.getItems().add(s);
            cbMujRozvrhCasOd.getItems().add(s);
        }

        for (int i = 0; i < 20; i++) {
            cbPredPrednasHodin.getItems().add(i);
            cbPredCvicHodin.getItems().add(i);
            cbPredSemHodin.getItems().add(i);
        }

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
                        cbUcitKatedra.setValue(hledejKatedru(newValue.getKatedra()));
                        if (newValue.getPrava() == 1) {
                            cbUcitPrava.setValue(EnumAnoNe.ANO);
                        } else {
                            cbUcitPrava.setValue(EnumAnoNe.NE);
                        }
                        imgUcitFoto.setImage(newValue.getFoto());
                        if (dh.getKontaktUcitela(newValue) != null) {
                            newValue.setKontakt(dh.getKontaktUcitela(newValue));
                            tfUcitMobil.setText(dh.getKontaktUcitela(newValue).getMobil());
                            tfUcitTelefon.setText(dh.getKontaktUcitela(newValue).getTelefon());
                            tfUcitEmail.setText(dh.getKontaktUcitela(newValue).getEmail());
                        }
                        tfVyucujiciHeslo.clear();
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
                    cbPracFak.setValue(katedra.getFakulta());
                    lvPracVyucujici.getItems().clear();
                    for (Ucitel uc : listUcitelu){
                        if (uc.getKatedra().equals(katedra.getZkratka())){
                            lvPracVyucujici.getItems().add(uc);
                        }
                    }
                }
            }
        });
        
        // Listener pro seznam vyucujici v tabu Katedry
        lvPracVyucujici.setOnMouseClicked(e -> {
            if (lvPracVyucujici.getSelectionModel().getSelectedItem()!=null){
                try {
                    lvPracRozvrh.getItems().clear();
                    lvPracRozvrh.getItems().add("====================GARANT===================");
                    lvPracRozvrh.getItems().addAll(dh.getUvazekVyucujiciGarant(lvPracVyucujici.getSelectionModel().getSelectedItem()));
                    lvPracRozvrh.getItems().add("==================PREDNASIJICI==================");
                    lvPracRozvrh.getItems().addAll(dh.getUvazekVyucujiciPrednas(lvPracVyucujici.getSelectionModel().getSelectedItem()));
                    lvPracRozvrh.getItems().add("====================CVICICI====================");
                    lvPracRozvrh.getItems().addAll(dh.getUvazekVyucujiciCviceni(lvPracVyucujici.getSelectionModel().getSelectedItem()));
                    lvPracRozvrh.getItems().add("===================SEMINARICI==================");
                    lvPracRozvrh.getItems().addAll(dh.getUvazekVyucujiciSeminar(lvPracVyucujici.getSelectionModel().getSelectedItem()));
                    
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                    alertDialog("Chyba", ex);
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

                        cbPredKatedra.setValue(dh.getKatedruPredmetu(predmet));
                        tfPredNazev.setText(predmet.getNazev());
                        tfPredZkratka.setText(predmet.getZkratka());
                        cbPredPrednasHodin.setValue(predmet.getPrednaskaHod());
                        cbPredCvicHodin.setValue(predmet.getCviceniHod());
                        cbPredSemHodin.setValue(predmet.getSeminarHod());
                        tfPredDopRocnik.setText(predmet.getDoporucenyRok());
                        tfPredPopis.setText(predmet.getPopis());
                        tfPredPocetKred.setText(predmet.getPocetKreditu());

                        if (predmet.getZpusobZakonceni().equals("Zkouška")) {
                            cbPredZpusobZak.setValue(EnumZpusobZakonceni.Zkouška);
                        } else {
                            cbPredZpusobZak.setValue(EnumZpusobZakonceni.Zápočet);
                        }
                        if (predmet.getSemestrLetni().equals("A")) {
                            cbPredLetSem.setValue(EnumAnoNe.ANO);
                        } else {
                            cbPredLetSem.setValue(EnumAnoNe.NE);
                        }
                        if (predmet.getSemestrZimni().equals("A")) {
                            cbPredZimSem.setValue(EnumAnoNe.ANO);
                        } else {
                            cbPredZimSem.setValue(EnumAnoNe.NE);
                        }
                        if (!dh.getSeznamGarantu(predmet).isEmpty()) {
                            cbPredGarant.setValue(dh.getSeznamGarantu(predmet).get(0));
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
                    cbStudObor.setValue(student.getObor());
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
                    dpOboryAkrOd.setValue(obor.getAkrOd().toLocalDate());
                    dpOboryAkrDo.setValue(obor.getAkrDo().toLocalDate());
                    for (EnumFormaStudia fs : EnumFormaStudia.values()) {
                        if (fs.toString().equalsIgnoreCase(obor.getFormaStudia())) {
                            cbOboryFormaStud.setValue(fs);
                            break;
                        }
                    }
                    for (EnumJazyk jz : EnumJazyk.values()) {
                        if (jz.toString().equalsIgnoreCase(obor.getJazyk())) {
                            cbOboryJazyk.setValue(jz);
                            break;
                        }
                    }
                    for (EnumTypStudia ts : EnumTypStudia.values()) {
                        if (ts.toString().equalsIgnoreCase(obor.getTyp())) {
                            cbOborTypStudia.setValue(ts);
                            break;
                        }

                    }
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

        // Listener pro list predmetu v rozvrhu
        lvRozvrhPredmet.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (lvRozvrhPredmet.getSelectionModel().getSelectedItem() != null) {
                    try {
                        vybranyPredmet = lvRozvrhPredmet.getSelectionModel().getSelectedItem();
                        int predHod = dh.getPotrebnuKapacituPredmetu(vybranyPredmet, EnumTypVyuky.Přednáška);
                        if (predHod == -1) {
                            lRozvrhMistPrednas.setText("Nema vyuku");
                        }else{
                            lRozvrhMistPrednas.setText(predHod+"");
                        }
                        int predCvic = dh.getPotrebnuKapacituPredmetu(vybranyPredmet, EnumTypVyuky.Cvičení);
                        if (predCvic == -1) {
                            lRozvrhMistCvic.setText("Nema vyuku");
                        }else{
                            lRozvrhMistCvic.setText(predCvic+"");
                        }
                        int predSem = dh.getPotrebnuKapacituPredmetu(vybranyPredmet, EnumTypVyuky.Seminář);
                        if (predSem == -1) {
                            lRozvrhMistSem.setText("Nema vyuku");
                        }else{
                            lRozvrhMistSem.setText(predSem+"");
                        }
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
                    cbRozvrhDen.setValue(akce.getDen());
                    cbRozvrhTyden.setValue(akce.getTyden());
                    cbRozvrhTyp.setValue(akce.getTyp());
                    cbRozvrhCasOd.setValue(akce.getCasOd());
                    cbRozvrhCasDo.setValue(akce.getCasDo());
                    tfRozvrhKapacita.setText(akce.getKapacita() + "");
                    cbRozvrhPlatny.setValue(akce.getPlatna());
                    cbRozvrhVyucujici.setValue(akce.getUcitel());
                    cbRozvrhUcebna.setValue(akce.getUcebna());
                    cbRozvrhBlokova.setValue(akce.getBlokova());
                    dpRozvrhDatum.setValue(akce.getDatum().toLocalDate());
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
                cbMistBudova.setValue(uc.getBudova());
                cbMistTyp.setValue(uc.getTyp());
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

        //Listener pro neplatne akce
        lvNepAkci.setOnMouseClicked(e -> {
            if (lvNepAkci.getSelectionModel().getSelectedItem() != null) {
                cbNepAkciPlatna.setValue(lvNepAkci.getSelectionModel().getSelectedItem().getPlatna());
            }
        });

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

        //Listener pro seznam studijnich planu
        lvStudPlanPlan.setOnMouseClicked(e -> {
            if (lvStudPlanPlan.getSelectionModel().getSelectedItem() != null) {
                try {
                    vybrabyPlan = lvStudPlanPlan.getSelectionModel().getSelectedItem();
                    tfStudPlanId.setText(lvStudPlanPlan.getSelectionModel().getSelectedItem().getId());
                    tfStudPlanPocet.setText(lvStudPlanPlan.getSelectionModel().getSelectedItem().getPocetStudentu() + "");
                    tfStudPlanRok.setText(lvStudPlanPlan.getSelectionModel().getSelectedItem().getRok());
                    cbStudPlanObor.setValue(vybrabyPlan.getObor());
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

    //==================TAB VYUCUJICI===========================
    
    // Ukladani zmen vyucujiciho
    @FXML
    private void bUcitUloz(ActionEvent event) {
        if (lvUcitSeznam.getSelectionModel().getSelectedItem() != null) {
            try {
                Ucitel ucitel = lvUcitSeznam.getSelectionModel().getSelectedItem();
                String jmeno = "" + tfUcitJmeno.getText();
                String prijmeni = "" + tfUcitPrijmeni.getText();
                String titulPred = "" + tfUcitTitulPred.getText();
                String titulZa = "" + tfUcitTitulZa.getText();
                String email = "" + tfUcitEmail.getText();
                String mobil = "" + tfUcitMobil.getText();
                String telefon = "" + tfUcitTelefon.getText();
                ucitel.setJmeno(jmeno);
                ucitel.setPrijmeni(prijmeni);
                ucitel.setTitulPred(titulPred);
                ucitel.setTitulZa(titulZa);
                ucitel.setKatedra(cbUcitKatedra.getValue().getZkratka());
                ucitel.getKontakt().setEmail(email);
                ucitel.getKontakt().setMobil(mobil);
                ucitel.getKontakt().setTelefon(telefon);
                ucitel.setFoto(imgUcitFoto.getImage());
                if (cbUcitPrava.getValue() == EnumAnoNe.ANO) {
                    ucitel.setPrava(1);
                } else {
                    ucitel.setPrava(0);
                }
                dh.updateUcitel(ucitel);
                lvUcitSeznam.getItems().clear();
                lvUcitSeznam.getItems().addAll(listUcitelu);
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                alertDialog("Chyba", ex);
            }
        }
    }

    // Ukladani noveho vyucujiciho
    @FXML
    private void bUcitNovy(ActionEvent event) {

        try {

            String jmeno = tfUcitJmeno.getText();
            String prijmeni = tfUcitPrijmeni.getText();
            String titulPred = tfUcitTitulPred.getText();
            String titulZa = tfUcitTitulZa.getText();
            String katedra = cbUcitKatedra.getValue().getZkratka();
            Ucitel ucitel = new Ucitel("-1", jmeno, prijmeni, titulPred, titulZa, katedra);
            if (cbUcitPrava.getValue() == EnumAnoNe.ANO) {
                ucitel.setPrava(1);
            } else {
                ucitel.setPrava(0);
            }
            Kontakt kontakt = new Kontakt(tfUcitTelefon.getText(), tfUcitMobil.getText(), tfUcitEmail.getText());
            dh.insertNovyUcitel(ucitel, kontakt);
            ucitel.setKontakt(kontakt);
            listUcitelu.add(ucitel);
            lvUcitSeznam.getItems().clear();
            lvUcitSeznam.getItems().addAll(listUcitelu);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
            alertDialog("Chyba", ex);
        }

    }

    // Odebrani vyucujiciho
    @FXML
    private void bUcitOdeber(ActionEvent event) {
        if (lvUcitListCvic.getItems().isEmpty() && lvUcitListGarant.getItems().isEmpty() && lvUcitListPrednas.getItems().isEmpty() && lvUcitSeznam.getSelectionModel().getSelectedItem() != null) {
            try {
                Ucitel ucitel = lvUcitSeznam.getSelectionModel().getSelectedItem();
                dh.deleteUcitel(ucitel);
                listUcitelu.remove(ucitel);
                lvUcitSeznam.getItems().clear();
                lvUcitSeznam.getItems().addAll(listUcitelu);
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                alertDialog("Chyba", ex);
            }
        }
    }

    // Pridani noveho foto vyucujiciho
    @FXML
    private void bVyucujiciNoveFoto(ActionEvent event) {
        if (lvUcitSeznam.getSelectionModel().getSelectedItem() != null) {

            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().add(new ExtensionFilter("Obrazek", "*.jpg", "*.png", "*.jpeg"));
            File f = fc.showOpenDialog(null);
            if (f != null) {
                try {
                    dh.updateFotoUcitela(lvUcitSeznam.getSelectionModel().getSelectedItem(), f);
                    lvUcitSeznam.getSelectionModel().getSelectedItem().setFoto(new Image(f.toURI().toString()));
                    imgUcitFoto.setImage(lvUcitSeznam.getSelectionModel().getSelectedItem().getFoto());
                } catch (SQLException | FileNotFoundException ex) {
                    Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                    alertDialog("Chyba", ex);
                }
            }
        }

    }

    // Ukladani noveho hesla pro vyucujci
    @FXML
    private void bVyucujiciUlozHeslo(ActionEvent event) {
        if (lvUcitSeznam.getSelectionModel().getSelectedItem() != null) {
            try {
                dh.updateHesloUcitela(lvUcitSeznam.getSelectionModel().getSelectedItem(), tfVyucujiciHeslo.getText());
            } catch (NullPointerException | SQLException ex) {
                Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                alertDialog("Chyba", ex);
            }
        }

    }

    //===================TAB KATEDRY=========================
    // Ukladani zmen katedry
    @FXML
    private void bPracZmeny(ActionEvent event) {
        if (lvPracList.getSelectionModel().getSelectedItem() != null) {
            try {
                String zkratka = tfPracID.getText();
                String nazev = tfPracNazev.getText();
                Fakulta fakulta = cbPracFak.getValue();
                Katedra kat = lvPracList.getSelectionModel().getSelectedItem();
                kat.setFakulta(fakulta);
                kat.setZkratka(zkratka);
                kat.setNazev(nazev);
                dh.updateKatedru(kat);
                cbUcitKatedra.getItems().clear();
                cbUcitKatedra.getItems().addAll(listKateder);
                lvPracList.getItems().clear();
                lvPracList.getItems().addAll(listKateder);
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                alertDialog("Chyba", ex);
            }
        }

    }
    
    // Odebirani katedry
    @FXML
    private void bPracOdeber(ActionEvent event) {
        if (lvPracList.getSelectionModel().getSelectedItem() != null) {
            try {
                dh.deleteKatedru(lvPracList.getSelectionModel().getSelectedItem());
                listKateder.remove(lvPracList.getSelectionModel().getSelectedItem());
                cbUcitKatedra.getItems().clear();
                cbUcitKatedra.getItems().addAll(listKateder);
                lvPracList.getItems().clear();
                lvPracList.getItems().addAll(listKateder);
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                alertDialog("Chyba", ex);
            }
        }
    }

    // Vkladani nove katedry
    @FXML
    private void bPracNovy(ActionEvent event) {
        if (lvPracList.getSelectionModel().getSelectedItem() != null) {
            try {
                String zkratka = tfPracID.getText();
                String nazev = tfPracNazev.getText();
                Fakulta fakulta = cbPracFak.getValue();
                Katedra kat = new Katedra(nazev, zkratka, fakulta);
                dh.insertNovuKatedru(kat);
                listKateder.add(kat);
                cbUcitKatedra.getItems().clear();
                cbUcitKatedra.getItems().addAll(listKateder);
                lvPracList.getItems().clear();
                lvPracList.getItems().addAll(listKateder);
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                alertDialog("Chyba", ex);
            }
        }
    }

    //==================================PREDMETY==============================================================
    // Pridani noveho prednasijiciho predmetu
    @FXML
    private void bPredPridPredn(ActionEvent event) {
        ChoiceDialog<Ucitel> dialog = new ChoiceDialog<>(null, listUcitelu);
        dialog.setTitle("Choice Dialog");
        dialog.setContentText("Vyber vyucujiciho:");
        Optional<Ucitel> result = dialog.showAndWait();
        if (result.isPresent() && vybranyPredmet != null) {
            try {
                dh.insertPrednasPredmetu(vybranyPredmet, result.get());
                lvPredListPredn.getItems().clear();
                lvPredListPredn.getItems().addAll(dh.getSeznamPrednasicich(vybranyPredmet));
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                alertDialog("Chyba", ex);
            }
        }
    }

    // Odebirani prednasijiciho 
    @FXML
    private void bPredOdeberPredn(ActionEvent event) {
        Ucitel ucitel = lvPredListPredn.getSelectionModel().getSelectedItem();
        if (ucitel != null && vybranyPredmet != null) {
            try {
                dh.deletePrednasPredmetu(vybranyPredmet, ucitel);
                lvPredListCvic.getItems().clear();
                lvPredListCvic.getItems().addAll(dh.getSeznamCvicicich(vybranyPredmet));
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                alertDialog("Chyba", ex);
            }
        }
    }

    // Vkladani cvicicijiho predmetu
    @FXML
    private void bPredPridCvic(ActionEvent event) {
        ChoiceDialog<Ucitel> dialog = new ChoiceDialog<>(null, listUcitelu);
        dialog.setTitle("Choice Dialog");
        dialog.setContentText("Vyber vyucujiciho:");
        Optional<Ucitel> result = dialog.showAndWait();
        if (result.isPresent() && vybranyPredmet != null) {
            try {
                dh.insertCviciciPredmetu(vybranyPredmet, result.get());
                lvPredListCvic.getItems().clear();
                lvPredListCvic.getItems().addAll(dh.getSeznamCvicicich(vybranyPredmet));
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                alertDialog("Chyba", ex);
            }
        }
    }

    // Odebirani cvicicijiho 
    @FXML
    private void bPredOdeberCv(ActionEvent event) {
        Ucitel ucitel = lvPredListCvic.getSelectionModel().getSelectedItem();
        if (ucitel != null && vybranyPredmet != null) {
            try {
                dh.deleteCviciciPredmetu(vybranyPredmet, ucitel);
                lvPredListCvic.getItems().clear();
                lvPredListCvic.getItems().addAll(dh.getSeznamCvicicich(vybranyPredmet));
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                alertDialog("Chyba", ex);
            }
        }
    }

    // Ukladani zmen predmetu
    @FXML
    private void bPredUlozZmeny(ActionEvent event) {
        if (lvPredList.getSelectionModel().getSelectedItem() != null) {
            try {
                String zkratka = "" + tfPredZkratka.getText();
                String nazev = "" + tfPredNazev.getText();
                int prednaskaHod = cbPredPrednasHodin.getValue();
                int cviceniHod = cbPredCvicHodin.getValue();
                int seminarHod = cbPredSemHodin.getValue();
                String pocetKreditu = "" + tfPredPocetKred.getText();
                String popis = "" + tfPredPopis.getText();
                String doporucenyRok = "" + tfPredDopRocnik.getText();
                String letniSem;
                if (cbPredLetSem.getSelectionModel().getSelectedItem() == EnumAnoNe.ANO) {
                    letniSem = "A";
                } else {
                    letniSem = "N";
                }
                String zimniSem;
                if (cbPredZimSem.getSelectionModel().getSelectedItem() == EnumAnoNe.ANO) {
                    zimniSem = "A";
                } else {
                    zimniSem = "N";
                }
                Katedra katedra = cbPredKatedra.getSelectionModel().getSelectedItem();
                Predmet predmet = lvPredList.getSelectionModel().getSelectedItem();
                predmet.setZkratka(zkratka);
                predmet.setNazev(nazev);
                predmet.setPrednaskaHod(prednaskaHod);
                predmet.setCviceniHod(cviceniHod);
                predmet.setSeminarHod(seminarHod);
                predmet.setPocetKreditu(pocetKreditu);
                predmet.setPopis(popis);
                predmet.setDoporucenyRok(doporucenyRok);
                predmet.setSemestrLetni(letniSem);
                predmet.setSemestrZimni(zimniSem);
                predmet.setZkratkaKatedry(katedra.getZkratka());
                predmet.setDoporucenyRok(doporucenyRok);
                dh.updatePredmet(predmet, cbPredGarant.getSelectionModel().getSelectedItem());
                lvPredList.getItems().clear();
                lvPredList.getItems().addAll(listPredmetu);
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                alertDialog("Chyba", ex);
            }
        }

    }

    // Pridani noveho vyucujici pro seminar
    @FXML
    private void bPredPridSem(ActionEvent event) {
        ChoiceDialog<Ucitel> dialog = new ChoiceDialog<>(null, listUcitelu);
        dialog.setTitle("Choice Dialog");
        dialog.setContentText("Vyber vyucujiciho:");
        Optional<Ucitel> result = dialog.showAndWait();
        if (result.isPresent() && vybranyPredmet != null) {
            try {
                dh.insertSeminarPredmetu(vybranyPredmet, result.get());
                lvPredListSem.getItems().clear();
                lvPredListSem.getItems().addAll(dh.getSeznamSeminar(vybranyPredmet));
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                alertDialog("Chyba", ex);
            }
        }
    }

    // Odebirani vyucujiciho z seminaru
    @FXML
    private void bPredOdeberSem(ActionEvent event) {
        Ucitel ucitel = lvPredListSem.getSelectionModel().getSelectedItem();
        if (ucitel != null && vybranyPredmet != null) {
            try {
                dh.deleteSeminarPredmetu(vybranyPredmet, ucitel);
                lvPredListSem.getItems().clear();
                lvPredListSem.getItems().addAll(dh.getSeznamSeminar(vybranyPredmet));
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                alertDialog("Chyba", ex);
            }
        }
    }

    // Vkladani noveho predmetu
    @FXML
    private void bPredVloz(ActionEvent event) {
        try {
            String zkratka = tfPredZkratka.getText();
            String nazev = tfPredNazev.getText();
            int prednaskaHod = cbPredPrednasHodin.getValue();
            int cviceniHod = cbPredCvicHodin.getValue();
            int seminarHod = cbPredSemHodin.getValue();
            String pocetKreditu = tfPredPocetKred.getText();
            String popis = tfPredPopis.getText();
            String doporucenyRok = tfPredDopRocnik.getText();
            String zpusobZakonceni = cbPredZpusobZak.getValue().toString();
            String letniSem;
            if (cbPredLetSem.getSelectionModel().getSelectedItem() == EnumAnoNe.ANO) {
                letniSem = "A";
            } else {
                letniSem = "N";
            }
            String zimniSem;
            if (cbPredZimSem.getSelectionModel().getSelectedItem() == EnumAnoNe.ANO) {
                zimniSem = "A";
            } else {
                zimniSem = "N";
            }
            Katedra katedra = cbPredKatedra.getSelectionModel().getSelectedItem();
            Predmet predmet = new Predmet(zkratka, nazev, popis, katedra.getZkratka(), pocetKreditu, zimniSem, letniSem, zpusobZakonceni, doporucenyRok, prednaskaHod, cviceniHod, seminarHod);
            dh.insertNovyPredmet(predmet, cbPredGarant.getSelectionModel().getSelectedItem());
            listPredmetu.add(predmet);
            lvPredList.getItems().clear();
            lvPredList.getItems().addAll(listPredmetu);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
            alertDialog("Chyba", ex);
        }
    }

    // Odebirani predmetu
    @FXML
    private void bPredOdeber(ActionEvent event) {
        if (lvPredList.getSelectionModel().getSelectedItem() != null) {
            try {
                dh.deletePredmet(lvPredList.getSelectionModel().getSelectedItem());
                listPredmetu.remove(lvPredList.getSelectionModel().getSelectedItem());
                lvPredList.getItems().clear();
                lvPredList.getItems().addAll(listPredmetu);
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                alertDialog("Chyba", ex);
            }
        }
    }

    //=====================STUDENTY===============================
    
    // Ukladani zmen studenta
    @FXML
    private void bStudUlozZmeny(ActionEvent event) {
        if (lvStudList.getSelectionModel().getSelectedItem() != null) {
            try {
                Student student = lvStudList.getSelectionModel().getSelectedItem();
                String jmeno = tfStudJmeno.getText();
                String prijmeni = tfStudPrijmeni.getText();
                String rocnik = tfStudRocnik.getText();
                String net_id = tfStudNetID.getText();
                student.setJmeno(jmeno);
                student.setPrijmeni(prijmeni);
                student.setRocnik(rocnik);
                student.setObor(cbStudObor.getValue());
                student.setNetID(net_id);
                dh.updateStudent(student);
                lvStudList.getItems().clear();
                lvStudList.getItems().addAll(listStudentu);
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                alertDialog("Chyba", ex);
            }
        }
    }

    // Vkladani noveho studenta
    @FXML
    private void bStudNovy(ActionEvent event) {
        try {
            String jmeno = tfStudJmeno.getText();
            String prijmeni = tfStudPrijmeni.getText();
            String rocnik = tfStudRocnik.getText();
            Student student = new Student("0", jmeno, prijmeni, rocnik, cbStudObor.getValue());
            dh.insertNovyStudent(student);
            listStudentu.add(student);
            lvStudList.getItems().clear();
            lvStudList.getItems().addAll(listStudentu);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
            alertDialog("Chyba", ex);
        }

    }
// Odebirani studenta
    @FXML
    private void bStudOdeber(ActionEvent event) {
        if (lvStudList.getSelectionModel().getSelectedItem() != null) {
            try {
                dh.deleteStudent(lvStudList.getSelectionModel().getSelectedItem());
                listStudentu.remove(lvStudList.getSelectionModel().getSelectedItem());
                lvStudList.getItems().clear();
                lvStudList.getItems().addAll(listStudentu);
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                alertDialog("Chyba", ex);
            }
        }
    }

    // Vkladani noveho foto pro studenta
    @FXML
    private void bStudNoveFoto(ActionEvent event) {
        if (lvStudList.getSelectionModel().getSelectedItem() != null) {

            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().add(new ExtensionFilter("Obrazek", "*.jpg", "*.png", "*.jpeg"));
            File f = fc.showOpenDialog(null);
            if (f != null) {
                try {
                    dh.updateFotoStudenta(lvStudList.getSelectionModel().getSelectedItem(), f);
                    lvStudList.getSelectionModel().getSelectedItem().setFoto(new Image(f.toURI().toString()));
                    imgStudFoto.setImage(lvStudList.getSelectionModel().getSelectedItem().getFoto());
                } catch (SQLException | FileNotFoundException ex) {
                    Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                    alertDialog("Chyba", ex);
                }
            }
        }
    }

    //====================OBORY===================================
    // Odebirani oboru
    @FXML
    private void bOboryOdeberObor(ActionEvent event) {
        if (lvOboryList.getSelectionModel().getSelectedItem() != null) {
            try {
                dh.deleteObor(lvOboryList.getSelectionModel().getSelectedItem());
                listOboru.remove(lvOboryList.getSelectionModel().getSelectedItem());
                lvOboryList.getItems().clear();
                lvOboryList.getItems().addAll(listOboru);
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                alertDialog("Chyba", ex);
            }
        }
    }

    // Vkladani oboru
    @FXML
    private void bOboryVlozObor(ActionEvent event) {
        try {
            String nazev = tfOboryNazev.getText();
            String zkratka = tfOboryZkratka.getText();
            String id = tfOboryID.getText();
            Date akrOd = Date.valueOf(dpOboryAkrOd.getValue());
            Date akrDo = Date.valueOf(dpOboryAkrDo.getValue());
            String typStudia = cbOborTypStudia.getValue().toString();
            String jazyk = cbOboryJazyk.getValue().toString();
            String formaStudia = cbOboryFormaStud.getValue().toString();
            Obor obor = new Obor(id, nazev, zkratka, akrOd, akrDo, formaStudia, jazyk, typStudia);
            dh.insertNovyObor(obor);
            listOboru.add(obor);
            lvOboryList.getItems().clear();
            lvOboryList.getItems().addAll(listOboru);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
            alertDialog("Chyba", ex);

        }
    }

    // Ukladani zmen oboru
    @FXML
    private void bOboryUlozZmeny(ActionEvent event) {
        if (lvOboryList.getSelectionModel().getSelectedItem() != null) {
            try {
                String nazev = tfOboryNazev.getText();
                String zkratka = tfOboryZkratka.getText();
                String id = tfOboryID.getText();
                Date akrOd = Date.valueOf(dpOboryAkrOd.getValue());
                Date akrDo = Date.valueOf(dpOboryAkrDo.getValue());
                String typStudia = cbOborTypStudia.getValue().toString();
                String jazyk = cbOboryJazyk.getValue().toString();
                String formaStudia = cbOboryFormaStud.getValue().toString();
                Obor obor = lvOboryList.getSelectionModel().getSelectedItem();
                obor.setNazev(nazev);
                obor.setZkratka(zkratka);
                obor.setId(id);
                obor.setAkrOd(akrOd);
                obor.setAkrDo(akrDo);
                obor.setTyp(typStudia);
                obor.setJazyk(jazyk);
                obor.setFormaStudia(formaStudia);
                dh.updateObor(obor);
                lvOboryList.getItems().clear();
                lvOboryList.getItems().addAll(listOboru);
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                alertDialog("Chyba", ex);
            }
        }
    }

    //======================FAKULTY================================
    // Vkladani nove fakulty
    @FXML
    private void bFakNova(ActionEvent event) {
        try {
            String nazev = tfFakNazev.getText();
            String zkratka = tfFakZkratka.getText();
            String dekan = tfFakDekan.getText();
            String telefon = tfFakTelefon.getText();
            String mobil = tfFakMobil.getText();
            String email = tfFakEmail.getText();
            Kontakt kon = new Kontakt(telefon, mobil, email);
            Fakulta fak = new Fakulta(zkratka, nazev, dekan);
            fak.setKontakt(kon);
            dh.insertNovaFakulta(fak);
            listFakult.add(fak);
            lvFakList.getItems().clear();
            lvFakList.getItems().addAll(listFakult);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
            alertDialog("Chyba", ex);
        }
    }

    
    // Odebirani fakulty
    @FXML
    private void bFakZrus(ActionEvent event) {
        if (lvFakList.getSelectionModel().getSelectedItem() != null) {
            if (lvFakList.getSelectionModel().getSelectedItem() != null) {
                try {
                    dh.deleteFakultu(lvFakList.getSelectionModel().getSelectedItem());
                    listFakult.remove(lvFakList.getSelectionModel().getSelectedItem());
                    lvFakList.getItems().clear();
                    lvFakList.getItems().addAll(listFakult);
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                    alertDialog("Chyba", ex);
                }
            }
        }
    }

    // Ukladani zmen fakulty
    @FXML
    private void bFakUloz(ActionEvent event) {
        if (lvFakList.getSelectionModel().getSelectedItem() != null) {
            try {
                String nazev = tfFakNazev.getText();
                String zkratka = tfFakZkratka.getText();
                String dekan = tfFakDekan.getText();
                String telefon = tfFakTelefon.getText();
                String mobil = tfFakMobil.getText();
                String email = tfFakEmail.getText();
                Kontakt kon = new Kontakt(telefon, mobil, email);
                Fakulta fak = lvFakList.getSelectionModel().getSelectedItem();
                fak.setNazev(nazev);
                fak.setZkratka(zkratka);
                fak.setDekan(dekan);
                fak.setKontakt(kon);
                dh.updateFakulta(fak);
                lvFakList.getItems().clear();
                lvFakList.getItems().addAll(listFakult);
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                alertDialog("Chyba", ex);
            }
        }
    }

    //=====================ROZVRH===================================
    // Ukladani zmen akci
    @FXML
    private void bRozvrhUloz(ActionEvent event) {
        if (lvRozvrhAkce.getSelectionModel().getSelectedItem() != null) {

            try {
                String id = tfRozvrhId.getText();
                EnumTypVyuky typ = cbRozvrhTyp.getValue();
                String casOd = cbRozvrhCasOd.getValue();
                String casDo = cbRozvrhCasDo.getValue();
                int kapacita = Integer.parseInt(tfRozvrhKapacita.getText());
                EnumDen den = cbRozvrhDen.getValue();
                EnumTyden tyden = cbRozvrhTyden.getValue();
                EnumAnoNe jePlatna = cbRozvrhPlatny.getValue();
                Akce akce = lvRozvrhAkce.getSelectionModel().getSelectedItem();
                akce.setId(id);
                akce.setTyp(typ);
                akce.setCasDo(casDo);
                akce.setCasOd(casOd);
                akce.setKapacita(kapacita);
                akce.setDen(den);
                akce.setTyden(tyden);
                akce.setPlatna(jePlatna);
                akce.setBlokova(cbRozvrhBlokova.getValue());
                akce.setDatum(Date.valueOf(dpRozvrhDatum.getValue()));
                akce.setUcebna(cbRozvrhUcebna.getValue());
                akce.setUcitel(cbRozvrhVyucujici.getValue());
                akce.setBlokova(cbRozvrhBlokova.getValue());
                akce.setDatum(Date.valueOf(dpRozvrhDatum.getValue()));
                dh.updateAkce(akce, aktUzivatel);
                lvRozvrhAkce.getItems().clear();
                lvRozvrhAkce.getItems().addAll(listAkce);
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                alertDialog("Chyba", ex);
            }
        }

    }

    // Vkladani nove akci
    @FXML
    private void bRozvrhNovy(ActionEvent event) {
        if (vybranyPredmet != null) {
            try {
                String id = tfRozvrhId.getText();
                EnumTypVyuky typ = cbRozvrhTyp.getValue();
                String casOd = cbRozvrhCasOd.getValue();
                String casDo = cbRozvrhCasDo.getValue();
                int kapacita = Integer.parseInt(tfRozvrhKapacita.getText());
                EnumDen den = cbRozvrhDen.getValue();
                EnumTyden tyden = cbRozvrhTyden.getValue();
                int jePlatna;
                if (cbRozvrhPlatny.getValue() == EnumAnoNe.ANO) {
                    jePlatna = 1;
                } else {
                    jePlatna = 0;
                }
                int jeBlokova;
                if (cbRozvrhBlokova.getValue() == EnumAnoNe.ANO) {
                    jeBlokova = 1;
                } else {
                    jeBlokova = 0;
                }
                Date datum = Date.valueOf(dpRozvrhDatum.getValue());
                Akce akce = new Akce(id, typ, tyden, den, casOd, casDo, kapacita, jePlatna, cbRozvrhUcebna.getValue(), cbRozvrhVyucujici.getValue(), vybranyPredmet, jeBlokova, datum);
                dh.insertNovaAkce(akce, aktUzivatel);
                listAkce.add(akce);
                lvRozvrhAkce.getItems().clear();
                lvRozvrhAkce.getItems().addAll(listAkce);
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                alertDialog("Chyba", ex);
            }
        }
    }

    // Odebirani akci
    @FXML
    private void bRozvrhOdeber(ActionEvent event) {
        if (lvRozvrhAkce.getSelectionModel().getSelectedItem() != null) {
            try {
                dh.deleteAkce(lvRozvrhAkce.getSelectionModel().getSelectedItem());
                listAkce.remove(lvRozvrhAkce.getSelectionModel().getSelectedItem());
                lvRozvrhAkce.getItems().clear();
                lvRozvrhAkce.getItems().addAll(listAkce);
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                alertDialog("Chyba", ex);
            }
        }
    }

    // Zobrazeni pouze platnych akci
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

    // Listener pro typ vyuky v tabu Rozvrh
    @FXML
    private void cbRozvrhTypClick(ActionEvent event) {
        if (vybranyPredmet != null) {
            try {
                cbRozvrhVyucujici.getItems().clear();
                if (cbRozvrhTyp.getValue() == EnumTypVyuky.Cvičení) {
                    cbRozvrhVyucujici.getItems().addAll(dh.getSeznamCvicicich(vybranyPredmet));
                }
                if (cbRozvrhTyp.getValue() == EnumTypVyuky.Přednáška) {
                    cbRozvrhVyucujici.getItems().addAll(dh.getSeznamPrednasicich(vybranyPredmet));
                }
                if (cbRozvrhTyp.getValue() == EnumTypVyuky.Seminář) {
                    cbRozvrhVyucujici.getItems().addAll(dh.getSeznamSeminar(vybranyPredmet));
                }
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                alertDialog("Chyba", ex);
            }
        }

    }

    //========================MISTNOSTI====================================
    // Vkladani nove mistnosti
    @FXML
    private void bMistNovy(ActionEvent event) {
        try {
            String cislo = tfMistCislo.getText();
            int kapacita = Integer.parseInt(tfMistKapacita.getText());
            Ucebna uc = new Ucebna("", cislo, kapacita, cbMistTyp.getValue(), cbMistBudova.getValue());
            dh.insertNovaUcebna(uc);
            listUcebna.add(uc);
            lvMistSeznam.getItems().clear();
            lvMistSeznam.getItems().addAll(listUcebna);
            cbRozvrhUcebna.getItems().clear();
            cbRozvrhUcebna.getItems().addAll(dh.getUcebnyAll());
        } catch (Exception ex) {
            alertDialog("Chyba", ex);
        }

    }

    // ukladani zmen mistnosti
    @FXML
    private void bMistUloz(ActionEvent event) {
        if (lvMistSeznam.getSelectionModel().getSelectedItem() != null) {
            try {
                String cislo = tfMistCislo.getText();
                int kapacita = Integer.parseInt(tfMistKapacita.getText());
                Ucebna uc = lvMistSeznam.getSelectionModel().getSelectedItem();
                uc.setCislo(cislo);
                uc.setKapacita(kapacita);
                uc.setTyp(cbMistTyp.getValue());
                uc.setBudova(cbMistBudova.getValue());
                dh.updateUcebna(uc);
                lvMistSeznam.getItems().clear();
                lvMistSeznam.getItems().addAll(listUcebna);
                cbRozvrhUcebna.getItems().clear();
                cbRozvrhUcebna.getItems().addAll(listUcebna);
            } catch (Exception ex) {
                alertDialog("Chyba", ex);
            }
        }
    }

    // odebirani mistnosti
    @FXML
    private void bMistOdeber(ActionEvent event) {
        if (lvMistSeznam.getSelectionModel().getSelectedItem() != null) {
            try {
                dh.deleteUcebna(lvMistSeznam.getSelectionModel().getSelectedItem());
                lvMistSeznam.getItems().clear();
                lvMistSeznam.getItems().addAll(dh.getUcebnyAll());
                cbRozvrhUcebna.getItems().clear();
                cbRozvrhUcebna.getItems().addAll(dh.getUcebnyAll());
            } catch (NullPointerException | SQLException ex) {
                Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                alertDialog("Chyba", ex);
            }
        }

    }

    // zobrazeni pouze platnych akci pro ucebnu
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

    //=======================PROFIL==================================
    // ukladani zmen aktualniho uzivatela
    @FXML
    private void btProfilUloz(ActionEvent event) {
        try {
            aktUzivatel.setJmeno(tfProfilJmeno.getText());
            aktUzivatel.setPrijmeni(tfProfilPrijmeni.getText());
            aktUzivatel.setTitulPred(tfProfilTitulPred.getText());
            aktUzivatel.setTitulZa(tfProfilTitulZa.getText());
            aktUzivatel.getKontakt().setEmail(tfProfilEmail.getText());
            aktUzivatel.getKontakt().setTelefon(tfProfilTelefon.getText());
            aktUzivatel.getKontakt().setMobil(tfProfilMobil.getText());
            dh.updateUcitel(aktUzivatel);
            lvUcitSeznam.getItems().clear();
            listUcitelu = dh.getUciteliAll();
            lvUcitSeznam.getItems().addAll(listUcitelu);
            cbPredGarant.getItems().clear();
            cbPredGarant.getItems().addAll(listUcitelu);
        } catch (SQLException | NullPointerException ex) {
            Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
            alertDialog("Chyba", ex);
        }
    }

    // ukladani hesla aktualniho uzivatela
    @FXML
    private void bProfilUlozHeslo(ActionEvent event) {
        try {
            dh.updateHesloUcitela(aktUzivatel, tfProfilNoveHeslo.getText());
            tfProfilNoveHeslo.clear();
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
            alertDialog("Chyba", ex);
        }
    }

    // prepinani aktualniho uzivatela checkbox
    @FXML
    private void chbProfilPrepnoutClick(ActionEvent event) {
        if (chbProfilPrepnout.isSelected()) {
            cbProfilUzivatel.setDisable(false);
        } else {
            try {
                cbProfilUzivatel.setDisable(true);
                aktUzivatel = uzivatel;
                Ucitel uc = dh.getUcitel(aktUzivatel.getId());
                tfProfilId.setText(uc.getId());
                tfProfilTitulPred.setText(uc.getTitulPred());
                tfProfilJmeno.setText(uc.getJmeno());
                tfProfilPrijmeni.setText(uc.getPrijmeni());
                tfProfilTitulZa.setText(uc.getTitulZa());
                tfProfilKatedra.setText(uc.getKatedra());
                tfProfilEmail.setText(uc.getKontakt().getEmail());
                tfProfilTelefon.setText(uc.getKontakt().getTelefon());
                tfProfilMobil.setText(uc.getKontakt().getMobil());
                imgProfilFoto.setImage(uc.getFoto());
                cbProfilUzivatel.getItems().clear();
                cbProfilUzivatel.getItems().addAll(listUcitelu);
                lvProfilGarant.getItems().clear();
                lvProfilCvicici.getItems().clear();
                lvProfilSeminar.getItems().clear();
                lvProfilPrednas.getItems().clear();
                lvProfilGarant.getItems().addAll(dh.getUvazekVyucujiciGarant(aktUzivatel));
                lvProfilPrednas.getItems().addAll(dh.getUvazekVyucujiciPrednas(aktUzivatel));
                lvProfilCvicici.getItems().addAll(dh.getUvazekVyucujiciCviceni(aktUzivatel));
                lvProfilSeminar.getItems().addAll(dh.getUvazekVyucujiciSeminar(aktUzivatel));
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                alertDialog("Chyba", ex);
            }
        }
    }

    // prepinani aktualniho uzivatela combobox
    @FXML
    private void cbProfilUzivatelClick(ActionEvent event) {
        if (cbProfilUzivatel.getSelectionModel().getSelectedItem() != null) {
            try {
                aktUzivatel = cbProfilUzivatel.getSelectionModel().getSelectedItem();
                Ucitel uc = dh.getUcitel(aktUzivatel.getId());
                tfProfilId.setText(uc.getId());
                tfProfilTitulPred.setText(uc.getTitulPred());
                tfProfilJmeno.setText(uc.getJmeno());
                tfProfilPrijmeni.setText(uc.getPrijmeni());
                tfProfilTitulZa.setText(uc.getTitulZa());
                tfProfilKatedra.setText(uc.getKatedra());
                tfProfilEmail.setText(uc.getKontakt().getEmail());
                tfProfilTelefon.setText(uc.getKontakt().getTelefon());
                tfProfilMobil.setText(uc.getKontakt().getMobil());
                imgProfilFoto.setImage(uc.getFoto());
                lvProfilGarant.getItems().clear();
                lvProfilCvicici.getItems().clear();
                lvProfilSeminar.getItems().clear();
                lvProfilPrednas.getItems().clear();
                lvProfilGarant.getItems().addAll(dh.getUvazekVyucujiciGarant(aktUzivatel));
                lvProfilPrednas.getItems().addAll(dh.getUvazekVyucujiciPrednas(aktUzivatel));
                lvProfilCvicici.getItems().addAll(dh.getUvazekVyucujiciCviceni(aktUzivatel));
                lvProfilSeminar.getItems().addAll(dh.getUvazekVyucujiciSeminar(aktUzivatel));
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                alertDialog("Chyba", ex);
            }
        }
    }

    // Ukladani foto aktualniho uzivatela
    @FXML
    private void bMujRozvrhPridatFoto(ActionEvent event) {
        if (aktUzivatel != null) {

            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().add(new ExtensionFilter("Obrazek", "*.jpg", "*.png", "*.jpeg"));
            File f = fc.showOpenDialog(null);
            if (f != null) {
                try {
                    dh.updateFotoUcitela(aktUzivatel, f);
                    imgProfilFoto.setImage(new Image(f.toURI().toString()));
                } catch (SQLException | FileNotFoundException ex) {
                    Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                    alertDialog("Chyba", ex);
                }
            }
        }
    }

    //============================MujRozvrh====================================
    // Vkladani nove akci uzivatelem
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
            Akce akce = new Akce(null, typ, tyden, den, casOd, casDo, kapacita, 0, cbMujRozvrhMistnost.getValue(), aktUzivatel, cbMujRozvrhPredmet.getValue(), blokova, datum);
            dh.insertNovaAkce(akce, aktUzivatel);
        } catch (SQLException | NullPointerException ex) {
            Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
            alertDialog("Chyba", ex);
        }
    }

    // vyber typu vyuky
    @FXML
    private void cbMujRozvrhTypClick(ActionEvent event) {

        try {
            if (cbMujRozvrhTyp.getValue() == EnumTypVyuky.Cvičení) {
                cbMujRozvrhPredmet.getItems().clear();
                cbMujRozvrhPredmet.getItems().addAll(dh.getCviciciPredmetyUcitela(aktUzivatel));
            }
            if (cbMujRozvrhTyp.getValue() == EnumTypVyuky.Přednáška) {
                cbMujRozvrhPredmet.getItems().clear();
                cbMujRozvrhPredmet.getItems().addAll(dh.getPrednasPredmetyUcitela(aktUzivatel));
            }
            if (cbMujRozvrhTyp.getValue() == EnumTypVyuky.Seminář) {
                cbMujRozvrhPredmet.getItems().clear();
                cbMujRozvrhPredmet.getItems().addAll(dh.getSeminarPredmetyUcitela(aktUzivatel));
            }
        } catch (SQLException | NullPointerException ex) {
            Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
            alertDialog("Chyba", ex);
        }
    }

    //============================NEPLATNE AKCI=================================
    // ukladani platnosti akci
    @FXML
    private void bNepAkceUloz(ActionEvent event) {
        if (lvNepAkci.getSelectionModel().getSelectedItem() != null) {
            try {
                Akce akce = lvNepAkci.getSelectionModel().getSelectedItem();
                akce.setPlatna(cbNepAkciPlatna.getValue());
                dh.updateAkce(akce, uzivatel);
                lvNepAkci.getItems().clear();
                lvNepAkci.getItems().addAll(dh.getNeplatneAkci(aktUzivatel));
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                alertDialog("Chyba", ex);
            }
        }
        obnovMujRozvrh();
    }

    //===================TABY=======================================
    // Dal popsany metody slouzi pro obnoveni dat pri prepinani tabu
    @FXML
    private void tabVyucuiciSelect(Event event) {
        if (tabVyucuici.isSelected() && loaded) {
            try {
                lvUcitSeznam.getItems().clear();
                listUcitelu = dh.getUciteliAll();
                lvUcitSeznam.getItems().addAll(listUcitelu);
                cbPredGarant.getItems().clear();
                cbPredGarant.getItems().addAll(listUcitelu);
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                alertDialog("Chyba", ex);
            }
        }
    }

    @FXML
    private void tabKatedraSelected(Event event) {
        if (tabKatedra.isSelected() && loaded) {
            try {
                listKateder = dh.getKatedryAll();
                lvPracList.getItems().clear();
                lvPracList.getItems().addAll(listKateder);
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                alertDialog("Chyba", ex);
            }
        }
    }

    @FXML
    private void tabPredmetySelected(Event event) {
        if (tabPredmety.isSelected() && loaded) {
            try {
                listPredmetu = dh.getPredmetyAll();
                lvPredList.getItems().clear();
                lvPredList.getItems().addAll(listPredmetu);
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                alertDialog("Chyba", ex);
            }
        }
    }

    @FXML
    private void tabStudentiSelected(Event event) {
        if (tabStudenti.isSelected() && loaded) {
            try {
                listStudentu = dh.getStudentiAll();
                lvStudList.getItems().clear();
                lvStudList.getItems().addAll(listStudentu);
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                alertDialog("Chyba", ex);
            }
        }
    }

    @FXML
    private void tabOborySelected(Event event) {
        if (tabObory.isSelected() && loaded) {
            try {
                listOboru = dh.getOboryAll();
                lvOboryList.getItems().clear();
                lvOboryList.getItems().addAll(listOboru);
                cbStudObor.getItems().clear();
                cbStudObor.getItems().addAll(listOboru);
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                alertDialog("Chyba", ex);
            }
        }
    }

    @FXML
    private void tabFakultaSelected(Event event) {
        if (tabFakulta.isSelected() && loaded) {
            try {
                listFakult = dh.getFakultyAll();
                cbPracFak.getItems().clear();
                cbPracFak.getItems().addAll(listFakult);
                lvFakList.getItems().clear();
                lvFakList.getItems().addAll(listFakult);
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                alertDialog("Chyba", ex);
            }
        }
    }

    @FXML
    private void tabRozvrhSelected(Event event) {
        if (tabRozvrh.isSelected() && loaded) {
            try {
                listPredmetu = dh.getPredmetyAll();
                lvRozvrhPredmet.getItems().clear();
                lvRozvrhPredmet.getItems().addAll(listPredmetu);
                listUcebna = dh.getUcebnyAll();
                lvMistSeznam.getItems().clear();
                lvMistSeznam.getItems().addAll(listUcebna);
                cbMujRozvrhMistnost.getItems().clear();
                cbMujRozvrhMistnost.getItems().addAll(listUcebna);
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                alertDialog("Chyba", ex);
            }
        }

    }

    @FXML
    private void tabMistnostSelected(Event event) {
        if (tabMistnost.isSelected() && loaded) {
            try {
                listUcebna = dh.getUcebnyAll();
                lvMistSeznam.getItems().clear();
                lvMistSeznam.getItems().addAll(listUcebna);
                cbMujRozvrhMistnost.getItems().clear();
                cbMujRozvrhMistnost.getItems().addAll(listUcebna);
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                alertDialog("Chyba", ex);
            }
        }
    }

    @FXML
    private void tabProfilSelected(Event event) {
        if (tabProfil.isSelected() && loaded) {
            try {
                Ucitel uc = dh.getUcitel(aktUzivatel.getId());
                tfProfilId.setText(uc.getId());
                tfProfilTitulPred.setText(uc.getTitulPred());
                tfProfilJmeno.setText(uc.getJmeno());
                tfProfilPrijmeni.setText(uc.getPrijmeni());
                tfProfilTitulZa.setText(uc.getTitulZa());
                tfProfilKatedra.setText(uc.getKatedra());
                tfProfilEmail.setText(uc.getKontakt().getEmail());
                tfProfilTelefon.setText(uc.getKontakt().getTelefon());
                tfProfilMobil.setText(uc.getKontakt().getMobil());
                imgProfilFoto.setImage(uc.getFoto());
                cbProfilUzivatel.getItems().clear();
                cbProfilUzivatel.getItems().addAll(listUcitelu);
                lvProfilGarant.getItems().clear();
                lvProfilCvicici.getItems().clear();
                lvProfilSeminar.getItems().clear();
                lvProfilPrednas.getItems().clear();
                lvProfilGarant.getItems().addAll(dh.getUvazekVyucujiciGarant(aktUzivatel));
                lvProfilPrednas.getItems().addAll(dh.getUvazekVyucujiciPrednas(aktUzivatel));
                lvProfilCvicici.getItems().addAll(dh.getUvazekVyucujiciCviceni(aktUzivatel));
                lvProfilSeminar.getItems().addAll(dh.getUvazekVyucujiciSeminar(aktUzivatel));
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                alertDialog("Chyba", ex);
            }
        }
    }

    @FXML
    private void tabMujRozvrhSelected(Event event) {
        if (tabMujRozvrh.isSelected() && loaded) {
            obnovMujRozvrh();
        }
    }

    @FXML
    private void tabNeplAkciSelected(Event event) {
        if (tabNeplAkci.isSelected() && loaded) {
            try {
                lvNepAkci.getItems().clear();
                for (Akce akce : dh.getNeplatneAkci(aktUzivatel)) {
                    lvNepAkci.getItems().add(akce);
                }

            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                alertDialog("Chyba", ex);
            }
        }
    }

    @FXML
    private void bNeplAkciZrusit(ActionEvent event) {
        if (lvNepAkci.getSelectionModel().getSelectedItem() != null) {
            try {
                dh.deleteAkce(lvNepAkci.getSelectionModel().getSelectedItem());
                lvNepAkci.getItems().remove(lvNepAkci.getSelectionModel().getSelectedItem());
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                alertDialog("Chyba", ex);
            }
        }
    }

    //==============================IMPORT==============================================
    //Nacitani dat vybraneho typu z souboru csv
    @FXML
    private void bImportNacti(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new ExtensionFilter("Obrazek", "*.csv"));
        File f = fc.showOpenDialog(null);
        if (f != null && cbImportTyp.getValue() != null) {
            try {
                lvImport.getItems().clear();
                lvImport.getItems().addAll(importCSV(f, cbImportTyp.getValue()));
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                alertDialog("Chyba", ex);
            }
        }
    }

    // Import nactenych dat
    @FXML
    private void bImportUloz(ActionEvent event) {
        try {
            for (Object ob : lvImport.getItems()) {
                if (ob instanceof Student) {
                    dh.insertNovyStudent((Student) ob);
                }
                if (ob instanceof Ucitel) {
                    dh.insertNovyUcitel((Ucitel) ob, ((Ucitel) ob).getKontakt());
                }
                if (ob instanceof Predmet) {
                    dh.insertNovyPredmet((Predmet) ob, null);
                }
                if (ob instanceof Obor){
                    dh.insertNovyObor((Obor) ob);
                }
                if (ob instanceof Katedra){
                    dh.insertNovuKatedru((Katedra) ob);
                }
            }
            lvImport.getItems().clear();
            obnovListy();
        } catch (SQLException | NullPointerException ex) {
            Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
            alertDialog("Chyba", ex);
        }
    }

    //==============================VLASTNI METODY===================================
    // hledani katedry podle zkratky
    private Katedra hledejKatedru(String zkratka) {
        for (Katedra kat : listKateder) {
            if (zkratka.equalsIgnoreCase(kat.getZkratka())) {
                return kat;
            }
        }
        return null;
    }

    // hledani oboru podle id
    private Obor hledejObor(String id) {
        for (Obor obor : listOboru) {
            if (obor.getId().equalsIgnoreCase(id)) {
                return obor;
            }
        }
        return null;
    }

    // Obnoveni dat z databazi
    private void obnovListy() {
        try {
            lvUcitSeznam.getItems().clear();
            listUcitelu = dh.getUciteliAll();
            lvUcitSeznam.getItems().addAll(listUcitelu);
            cbPredGarant.getItems().addAll(listUcitelu);

            listKateder = dh.getKatedryAll();
            lvPracList.getItems().clear();
            lvPracList.getItems().addAll(listKateder);
            cbUcitKatedra.getItems().clear();
            cbUcitKatedra.getItems().addAll(listKateder);
            cbPredKatedra.getItems().clear();
            cbPredKatedra.getItems().addAll(listKateder);

            listPredmetu = dh.getPredmetyAll();
            lvPredList.getItems().clear();
            lvPredList.getItems().addAll(listPredmetu);

            listStudentu = dh.getStudentiAll();
            lvStudList.getItems().clear();
            lvStudList.getItems().addAll(listStudentu);

            listOboru = dh.getOboryAll();
            lvOboryList.getItems().clear();
            lvOboryList.getItems().addAll(listOboru);
            cbStudObor.getItems().clear();
            cbStudObor.getItems().addAll(listOboru);

            listFakult = dh.getFakultyAll();
            cbPracFak.getItems().clear();
            cbPracFak.getItems().addAll(listFakult);
            lvFakList.getItems().clear();
            lvFakList.getItems().addAll(listFakult);

            listUcebna = dh.getUcebnyAll();
            lvMistSeznam.getItems().clear();
            lvMistSeznam.getItems().addAll(listUcebna);
            cbMujRozvrhMistnost.getItems().clear();
            cbMujRozvrhMistnost.getItems().addAll(listUcebna);

            tfProfilId.setText(aktUzivatel.getId());
            tfProfilTitulPred.setText(aktUzivatel.getTitulPred());
            tfProfilJmeno.setText(aktUzivatel.getJmeno());
            tfProfilPrijmeni.setText(aktUzivatel.getPrijmeni());
            tfProfilTitulZa.setText(aktUzivatel.getTitulZa());
            tfProfilKatedra.setText(aktUzivatel.getKatedra());
            tfProfilEmail.setText(aktUzivatel.getKontakt().getEmail());
            tfProfilTelefon.setText(aktUzivatel.getKontakt().getTelefon());
            tfProfilMobil.setText(aktUzivatel.getKontakt().getMobil());
            imgProfilFoto.setImage(aktUzivatel.getFoto());
            cbProfilUzivatel.getItems().clear();
            cbProfilUzivatel.getItems().addAll(listUcitelu);
            lvProfilGarant.getItems().clear();
            lvProfilCvicici.getItems().clear();
            lvProfilSeminar.getItems().clear();
            lvProfilPrednas.getItems().clear();
            lvProfilGarant.getItems().addAll(dh.getUvazekVyucujiciGarant(aktUzivatel));
            lvProfilPrednas.getItems().addAll(dh.getUvazekVyucujiciPrednas(aktUzivatel));
            lvProfilCvicici.getItems().addAll(dh.getUvazekVyucujiciCviceni(aktUzivatel));
            lvProfilSeminar.getItems().addAll(dh.getUvazekVyucujiciSeminar(aktUzivatel));
            obnovMujRozvrh();
            lvNepAkci.getItems().clear();
            for (Akce akce : dh.getAkceAll()) {
                if (akce.getPlatna() == EnumAnoNe.NE) {
                    lvNepAkci.getItems().add(akce);
                }
            }

            lvStudPlanObor.getItems().addAll(listOboru);

        } catch (SQLException e) {
            Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, e);
            alertDialog("Chyba", e);
        }

    }

    // obnoveni rozvrhu aktualniho uzivatela
    public void obnovMujRozvrh() {
        try {
            lvMujrozvrhPondeli.getItems().clear();
            lvMujrozvrhUtery.getItems().clear();
            lvMujrozvrhStreda.getItems().clear();
            lvMujrozvrhCtvrtek.getItems().clear();
            lvMujrozvrhPatek.getItems().clear();
            lvMujRozvrhBlokova.getItems().clear();
            for (Akce akce : dh.getAkceUcitela(aktUzivatel)) {
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

    // zobrazeni dialogu pri chybe nebo vyjimke
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

    // nacitani dat z souboru
    private ArrayList<Object> importCSV(File f, EnumTypImport typ) throws IOException {

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";
        ArrayList<Object> list = new ArrayList<>();
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(f), Charset.forName("ISO-8859-2")));
            br.readLine();
            if (typ == EnumTypImport.STUDENT) {
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(cvsSplitBy);
                    for (int i = 0; i < data.length; i++) {
                        data[i] = data[i].replaceAll("\"", "");
                    }
                    list.add(new Student("NOVY STUDENT", data[1], data[2], data[15], dh.getObor(null, data[17])));

                }
            }
            if (typ == EnumTypImport.VYUCUJICI) {
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(cvsSplitBy);
                    for (int i = 0; i < data.length; i++) {
                        data[i] = data[i].replaceAll("\"", "");
                    }
                    Ucitel ucitel = new Ucitel("NOVY UCITEL", data[1], data[2], data[3], data[4], data[7]);
                    Kontakt kontakt = new Kontakt(data[11], data[10], data[9]);
                    ucitel.setKontakt(kontakt);
                    list.add(ucitel);
                }
            }
            if (typ == EnumTypImport.PREDMET) {
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(cvsSplitBy);
                    for (int i = 0; i < data.length; i++) {
                        data[i] = data[i].replaceAll("\"", "");
                    }
                    Predmet predmet = new Predmet(data[1], data[3], data[36], data[0], data[7], data[5], data[6], data[37], "1", Integer.parseInt(data[30]), Integer.parseInt(data[32]), Integer.parseInt(data[34]));
                    list.add(predmet);

                }
            }
            if (typ == EnumTypImport.KATEDRA) {
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(cvsSplitBy);
                    for (int i = 0; i < data.length; i++) {
                        data[i] = data[i].replaceAll("\"", "");
                    }
                    Katedra katedra = new Katedra(data[3], data[2], dh.getFakulta(data[5]));
                    list.add(katedra);

                }
            }
            if (typ == EnumTypImport.OBOR) {
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(cvsSplitBy);
                    for (int i = 0; i < data.length; i++) {
                        data[i] = data[i].replaceAll("\"", "");
                    }
                    Katedra katedra = new Katedra(data[3], data[2], dh.getFakulta(data[5]));
                    list.add(katedra);

                }
            }
        } catch (FileNotFoundException ex) {
            alertDialog("Chyba", ex);
        } catch (IOException | SQLException | NullPointerException ex) {
            alertDialog("Chyba", ex);
        } finally {
            if (br != null) {
                    br.close();
            }
        }

        return list;
    }

    //===============================STUDIJNI PLAN==================================================
    // Ukladni zmen studijniho planu
    @FXML
    private void bStudPlanUloz(ActionEvent event) {
        try {
            if (lvStudPlanPlan.getSelectionModel().getSelectedItem() != null) {
                StudPlan plan = lvStudPlanPlan.getSelectionModel().getSelectedItem();
                plan.setPocetStudentu(Integer.parseInt(tfStudPlanPocet.getText()));
                plan.setRok(tfStudPlanRok.getText());
                plan.setObor(cbStudPlanObor.getValue());
                dh.insertNovyStudPlan(plan);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Vkladani noveho studijniho planu
    @FXML
    private void bStudPlanNovy(ActionEvent event) throws SQLException {
        int pocet = Integer.parseInt(tfStudPlanPocet.getText());
        String rok = tfStudPlanRok.getText();
        Obor obor = cbStudPlanObor.getValue();
        StudPlan plan = new StudPlan("-1", pocet, rok, obor);
        dh.insertNovyStudPlan(plan);
        lvStudPlanPlan.getItems().add(plan);
    }

    // Odebirani studijniho planu
    @FXML
    private void bStudPlanOdeber(ActionEvent event) {
        if (lvStudPlanPlan.getSelectionModel().getSelectedItem() != null) {
            try {
                dh.deleteStudPlan(lvStudPlanPlan.getSelectionModel().getSelectedItem());
                lvStudPlanPlan.getItems().remove(lvStudPlanPlan.getSelectionModel().getSelectedItem());
                vybrabyPlan = null;
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //Vkladani povineho predmetu
    @FXML
    private void bStudPlanNovyPredPovin(ActionEvent event) {
        Dialog<Pair<Predmet, Integer>> dialog = new Dialog<>();
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        ComboBox<Predmet> cb = new ComboBox<>();
        cb.getItems().addAll(listPredmetu);
        Spinner<Integer> sp = new Spinner(1, 6, 1);
        grid.add(cb, 1, 0);
        grid.add(sp, 1, 1);
        ButtonType loginButtonType = new ButtonType("Uloz", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(cb.getValue(), sp.getValue());
            }
            return null;
        });
        Optional<Pair<Predmet, Integer>> result = dialog.showAndWait();
        if (result.isPresent()) {
            if (vybrabyPlan != null) {
                try {
                    dh.insertPovinnyPredmet(vybrabyPlan, result.get().getKey(), result.get().getValue().toString());
                    lvStudPlanPov.getItems().add(result.get().getKey());
                } catch (SQLException | NullPointerException ex) {
                    Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                    alertDialog("chyba", ex);
                }
            }

        }
    }

    //Odebirani povineho predmetu
    @FXML
    private void bStudPlanOdeberPredmetPov(ActionEvent event) {
        if (lvStudPlanPov.getSelectionModel().getSelectedItem() != null && vybrabyPlan != null) {
            try {
                dh.deletePredmetVPlanu(vybrabyPlan, lvStudPlanPov.getSelectionModel().getSelectedItem());
                lvStudPlanPov.getItems().remove(lvStudPlanPov.getSelectionModel().getSelectedItem());
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                alertDialog("chyba", ex);
            }
        }
    }

    //Vkladani povine-volitelneho predmetu
    @FXML
    private void bStudPlanNovyPredPovVol(ActionEvent event) {
        Dialog<Pair<Predmet, Integer>> dialog = new Dialog<>();
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        ComboBox<Predmet> cb = new ComboBox<>();
        cb.getItems().addAll(listPredmetu);
        Spinner<Integer> sp = new Spinner(1, 6, 1);
        grid.add(cb, 1, 0);
        grid.add(sp, 1, 1);
        ButtonType loginButtonType = new ButtonType("Uloz", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(cb.getValue(), sp.getValue());
            }
            return null;
        });
        Optional<Pair<Predmet, Integer>> result = dialog.showAndWait();
        if (result.isPresent()) {
            if (vybrabyPlan != null) {
                try {
                    dh.insertPovinnyVolitPredmet(vybrabyPlan, result.get().getKey(), result.get().getValue().toString());
                    lvStudPlanPovVol.getItems().add(result.get().getKey());
                } catch (SQLException | NullPointerException ex) {
                    Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                    alertDialog("chyba", ex);
                }
            }

        }

    }
//Odebirani povine-volitelneho predmetu
    @FXML
    private void bStudPlanOdeberPredmetPovVol(ActionEvent event) {
        if (lvStudPlanPovVol.getSelectionModel().getSelectedItem() != null && vybrabyPlan != null) {
            try {
                dh.deletePredmetVPlanu(vybrabyPlan, lvStudPlanPovVol.getSelectionModel().getSelectedItem());
                lvStudPlanPovVol.getItems().remove(lvStudPlanPov.getSelectionModel().getSelectedItem());
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                alertDialog("chyba", ex);
            }
        }
    }

    //Vkladani volitelneho predmetu
    @FXML
    private void bStudPlanNovyPredVol(ActionEvent event) {
        Dialog<Pair<Predmet, Integer>> dialog = new Dialog<>();
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        ComboBox<Predmet> cb = new ComboBox<>();
        cb.getItems().addAll(listPredmetu);
        Spinner<Integer> sp = new Spinner(1, 6, 1);
        grid.add(cb, 1, 0);
        grid.add(sp, 1, 1);
        ButtonType loginButtonType = new ButtonType("Uloz", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(cb.getValue(), sp.getValue());
            }
            return null;
        });
        Optional<Pair<Predmet, Integer>> result = dialog.showAndWait();
        if (result.isPresent()) {
            if (vybrabyPlan != null) {
                try {
                    dh.insertVolitelnyPredmet(vybrabyPlan, result.get().getKey(), result.get().getValue().toString());
                    lvStudPlanVol.getItems().add(result.get().getKey());
                } catch (SQLException | NullPointerException ex) {
                    Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                    alertDialog("chyba", ex);
                }
            }

        }

    }

    //Odebirani volitelneho predmetu
    @FXML
    private void bStudPlanOdeberPredmetVol(ActionEvent event) {
        if (lvStudPlanVol.getSelectionModel().getSelectedItem() != null && vybrabyPlan != null) {
            try {
                dh.deletePredmetVPlanu(vybrabyPlan, lvStudPlanVol.getSelectionModel().getSelectedItem());
                lvStudPlanVol.getItems().remove(lvStudPlanVol.getSelectionModel().getSelectedItem());
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(FXMLDocumentControllerAdmin.class.getName()).log(Level.SEVERE, null, ex);
                alertDialog("chyba", ex);
            }
        }
    }

}
