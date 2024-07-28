package apps.sportapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;

// Die LoginController-Klasse verwaltet die Benutzeroberfläche und die Interaktionen im Anmeldefenster
public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    // Methode zur Verarbeitung des Login-Vorgangs
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        try {
            List<User> users = UserFileUtil.readUsers();  // Read User objects

            // Überprüfen, ob der Benutzername und das Passwort übereinstimmen
            boolean authenticated = users.stream()
                    .anyMatch(user -> user.getName().equals(username) && user.getPassword().equals(password));

            if (authenticated) {
                Controller.setCurrentUser(username); // Setzt den aktuellen Benutzer im Controller
                Stage currentStage = (Stage) usernameField.getScene().getWindow(); // Ermittelt das aktuelle Fenster
                openMainWindow(); // Öffnen des Hauptfensters
                currentStage.close(); // Schließen des Login-Fensters
            } else {
                showAlert("Login Error", "Invalid username or password.");
            }
        } catch (IOException | ClassNotFoundException e) {
            showAlert("Error", "Error reading user data.");
        }
    }

    @FXML
    // Methode zur Verarbeitung des Registrierungs-Vorgangs
    private void handleRegister() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Register.fxml"));
            Stage stage = new Stage();
            stage.setTitle("User Registration");
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.show();
            ((Stage) usernameField.getScene().getWindow()).close();
        } catch (IOException e) {
            showAlert("Error", "Unable to load registration form.");
        }
    }

    @FXML
    // Methode zur Verarbeitung des Abbrechen-Vorgangs
    private void handleCancel() {
        ((Stage) usernameField.getScene().getWindow()).close();
    }

    // Methode zur Anzeige von Fehlermeldungen
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Methode zum Öffnen des Hauptfensters der Anwendung
    private void openMainWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("SportAppMainScene.fxml"));
            Stage mainStage = new Stage();
            mainStage.setTitle("SportApp");
            mainStage.setScene(new Scene(fxmlLoader.load()));
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Print stack trace for debugging
            showAlert("Error", "Unable to load main application window.");
        }
    }
}
