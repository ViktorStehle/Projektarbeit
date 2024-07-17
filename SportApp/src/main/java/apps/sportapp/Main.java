package apps.sportapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main extends Application {

    public static final String USER_FILE_PATH = Paths.get("").toAbsolutePath().toString() + "/users.txt";

    @Override
    public void start(Stage stage) throws IOException {
        // Load the login window on start
        showLoginWindow(stage);
    }

    private void showLoginWindow(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("SportApp - Anmeldung");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}