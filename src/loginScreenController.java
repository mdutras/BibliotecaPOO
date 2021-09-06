package src;

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
import javafx.scene.Parent;
import javafx.stage.Stage;
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
    void getLogin(ActionEvent event) throws IOException {
        String login = loginField.getText();
        String senha = passField.getText();
        String categoria = categoriaMenu.getValue();
        System.out.println("login: " + login + " senha: "+ senha + " categoria: " + categoria);
        
        Stage stg = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../views/app.fxml"));
        Scene cena = new Scene(root);
        cena.getStylesheets().add("style/main.css");

        stg.setScene(cena);
        stg.show();
    }

    public void initialize() {
        categoriaMenu.getItems().addAll("Bibliotecário","Leitor");
    }
}

