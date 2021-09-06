import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("views/loginScreen.fxml"));
        Scene cena = new Scene(root);
        stage.setTitle("Teste");
        stage.setScene(cena);
        stage.show();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        launch(args);
    }
}
