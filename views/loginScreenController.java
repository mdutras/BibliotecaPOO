package views;

import javafx.fxml.FXML;

// import java.net.URL;
// import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
    void getLogin(ActionEvent event) {
        String login = loginField.getText();
        String senha = passField.getText();

        System.out.println("login: " + login + " senha: "+ senha);
    }

    public void initialize() {
        categoriaMenu.getItems().addAll("Bibliotecário","Leitor");
    }
}

