package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

// import java.net.URL;
// import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.stage.Stage;
import src.JsonManager;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;

public class loginScreenController {
    @FXML
    private TextField loginField;

    @FXML
    private Button enterBut;

    @FXML
    private PasswordField passField;

    @FXML
    private ChoiceBox<String> categoriaMenu;

    @FXML
    private Label warningLabel;

    Boolean validateLogin(String login, String senha, String categoria){
        Boolean allowed = false;
        if(JsonManager.membroExists(login)){
            if(JsonManager.getSenhaMembro(login).equals(senha) && JsonManager.getMembro(login).categoria.equals(categoria)){
                allowed = true;
            }
        }
        return allowed;
    }

    @FXML
    void getLogin(ActionEvent event) throws IOException {
        System.out.println("Entrou");
        String login = loginField.getText();
        String senha = passField.getText();
        String categoria = categoriaMenu.getValue();
        System.out.println("login: " + login + " senha: "+ senha + " categoria: " + categoria);
        if(!(login.equals("") || senha.equals("") || categoria == null) & validateLogin(login, senha, categoria)){
            warningLabel.setText("");
            Parent root;
            Stage stg = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stg.setResizable(false);
            FXMLLoader loader;
            if(categoria.equals("Leitor")){
                loader = new FXMLLoader(getClass().getResource("../views/client.fxml"));
                root = loader.load();
                clientScreenController scene2Controller = loader.getController();
                scene2Controller.setLogin(loginField.getText());
                
            }else{ 
                loader = new FXMLLoader(getClass().getResource("../views/app.fxml"));
                root = loader.load();
                adminScreenController scene2Controller = loader.getController();
                scene2Controller.setLogin(loginField.getText());
            }
            Scene cena = new Scene(root);
            cena.getStylesheets().add("style/main.css");

            stg.setScene(cena);
            stg.show();
        }else{
            warningLabel.setText("Informações de login incorretas");
        }
    }

    public void initialize() {
        categoriaMenu.getItems().addAll("Bibliotecário","Leitor");
    }
}

