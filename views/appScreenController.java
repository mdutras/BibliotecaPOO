package views;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class appScreenController {

    @FXML
    private Label homeButton;

    @FXML
    private Label cadastroButton;

    @FXML
    private Label membrosButton;

    @FXML
    private Label eventosButton;

    @FXML
    private Label acervoButton;

    @FXML
    private Label windowTitle;

    @FXML
    void changeColorEnter(MouseEvent event) {
        Label a = (Label) event.getSource();
        a.setStyle("-fx-text-fill: #FFF8E7; -fx-background-color: #707070;");
    }

    @FXML
    void changeColorExit(MouseEvent event){
        Label a = (Label) event.getSource();
        a.setStyle("-fx-background-color: #c1c1c1; -fx-text-fill: black; -fx-background-color: #989898;");
    }

}
