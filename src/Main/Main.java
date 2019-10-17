/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Objekty.Ucitel;
import com.sun.deploy.util.FXLoader;
import database.DatabaseHelper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Pair;

/**
 *
 * @author maksim
 */
public class Main extends Application {

    DatabaseHelper dh;

    @Override
    public void start(Stage stage) throws Exception {
        loginDialog();
    }

    private void loginDialog() {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Login Dialog");

        ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
        ButtonType anonymButton = new ButtonType("Anonym", ButtonBar.ButtonData.OTHER);
        ButtonType configmButton = new ButtonType("Config", ButtonBar.ButtonData.HELP);
        ButtonType cancelButton = new ButtonType("Exit", ButtonBar.ButtonData.NO);
        dialog.getDialogPane().getButtonTypes().addAll(configmButton, anonymButton, loginButtonType, cancelButton);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField username = new TextField();
        username.setPromptText("ID");
        PasswordField password = new PasswordField();
        password.setPromptText("Heslo");

        grid.add(new Label("ID:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Heslo:"), 0, 1);
        grid.add(password, 1, 1);
        dialog.getDialogPane().setContent(grid);

        Platform.runLater(() -> username.requestFocus());

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(username.getText(), password.getText());
            }
            if (dialogButton == anonymButton) {
                return new Pair<>("anonym", "anonym");
            }
            if (dialogButton == cancelButton) {
                System.exit(0);
            }
            if (dialogButton == configmButton) {
                configDialog();
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(usernamePassword -> {
            Ucitel uzivatel = null;
            try {

                    Scanner sc = new Scanner(new File("cfg.txt"));
                    String name = sc.nextLine();
                    String pass = sc.nextLine();
                    sc.close();
                    DatabaseHelper.setUp(name, pass);

                dh = DatabaseHelper.getInstance();
                if ("anonym".equals(usernamePassword.getKey()) && "anonym".equals(usernamePassword.getValue())) {
                    loginAsAnonym();
                } else {
                    if (dh.login(usernamePassword.getKey(), usernamePassword.getValue())) {
                        uzivatel = dh.getUcitel(usernamePassword.getKey());
                        if (uzivatel == null) {
                            alertDialog("Prihlaseni selhalo. Zkuste znovu", null);
                            loginDialog();
                        } else {
                            if (uzivatel.getPrava() == 1) {
                                System.out.println("Login as admin");
                                loginAsAdmin(uzivatel);
                            } else {
                                System.out.println("login as vyucijici");
                                loginAsVyucujici(uzivatel);
                            }
                        }

                    } else {
                        alertDialog("Prihlaseni selhalo. Zkuste znovu", null);
                        loginDialog();
                    }
                }

            } catch (SQLException ex) {
                alertDialog("Chyba", ex);
                System.exit(1);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                alertDialog("Chyba", ex);
            }
        });
    }

    private void loginAsAdmin(Ucitel ucitel) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocumentAdmin.fxml"));
            FXMLDocumentControllerAdmin contr = new FXMLDocumentControllerAdmin(ucitel);

            loader.setController(contr);
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(loader.load()));
            stage.setTitle(ucitel.toString());
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            alertDialog("Chyba", ex);
        }
    }

    private void loginAsVyucujici(Ucitel ucitel) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocumentVyucujici.fxml"));
            FXMLDocumentVyucujiciController contr = new FXMLDocumentVyucujiciController(ucitel);

            loader.setController(contr);
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(loader.load()));
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            alertDialog("Chyba", ex);
        }
    }

    private void loginAsAnonym() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocumentAnonym.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(loader.load()));
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            alertDialog("Chyba", ex);
        }
    }

    private void configDialog() {
        Dialog dialog = new Dialog<>();
        dialog.setTitle("Configurace databazi");

        ButtonType okButtonType = new ButtonType("Uloz", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Zavrit", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(okButtonType,cancelButton);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField username = new TextField();
        username.setPromptText("Jmeno uzivatela");
        PasswordField password = new PasswordField();
        password.setPromptText("Heslo");

        grid.add(new Label("Jmeno:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Heslo:"), 0, 1);
        grid.add(password, 1, 1);
        dialog.getDialogPane().setContent(grid);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.get() == okButtonType){
            FileWriter fw = null;
            try {
                System.out.println("OK");
                fw = new FileWriter(new File("cfg.txt"));
                fw.write(username.getText()+"\n");
                fw.write(password.getText());
                fw.flush();
                fw.close();
                loginDialog();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fw.close();
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else{
            dialog.close();
            loginDialog();
        }
    }

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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
