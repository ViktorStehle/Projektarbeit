package apps.sportapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

// Die Main-Klasse ist der Einstiegspunkt der SportApp-Anwendung
public class Main extends Application {

    // Konstante zur Speicherung des Pfades zur Benutzerdatei
    public static final String USER_FILE_PATH = Paths.get("").toAbsolutePath().toString() + "/users.dat";

    // Die Startmethode wird aufgerufen, wenn die Anwendung gestartet wird
    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        // Liest die Benutzerinformationen aus der Datei und setzt sie im RegisterController
        List<User> users = UserFileUtil.readUsers();
        RegisterController.setUsers(users);

        // Lädt die Login-Szene und zeigt sie an
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("SportApp - Anmeldung");
        stage.setScene(scene);
        stage.show();
    }

    // Die Stoppmethode wird aufgerufen, wenn die Anwendung beendet wird
    @Override
    public void stop() throws IOException {
        UserFileUtil.saveUsers(RegisterController.users);
    }

    public static void main(String[] args) {
        launch();
    }
}
