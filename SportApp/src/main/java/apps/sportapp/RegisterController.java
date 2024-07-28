package apps.sportapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RegisterController {

    @FXML
    private ChoiceBox<String> genderChoiceBox; // Auswahlbox für das Geschlecht

    @FXML
    private TextField weightField; // Textfeld für das Gewicht

    @FXML
    private DatePicker birthDatePicker; // DatePicker für das Geburtsdatum

    @FXML
    private TextField heightField; // Textfeld für die Körpergröße

    @FXML
    private TextField usernameField; // Textfeld für den Benutzernamen

    @FXML
    private TextField passwordField; // Textfeld für das Passwort

    protected static List<User> users = new ArrayList<>(); // Liste zur Speicherung der Benutzer

    // Methode zur Verarbeitung der Registrierung
    @FXML
    private void handleSubmit() {
        String gender = genderChoiceBox.getValue();
        String weight = weightField.getText();
        LocalDate birthDate = birthDatePicker.getValue();
        String height = heightField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Überprüfen, ob die Eingaben gültig sind
        if (!validateInputs(username, password, gender, weight, height, birthDate)) {
            return;
        }

        User newUser = null;  // Deklarieren des neuen Benutzers außerhalb des try-Blocks

        try {
            // Überprüfen, ob der Benutzername bereits vergeben ist
            boolean userExists = users.stream().anyMatch(user -> user.getName().equals(username));
            if (userExists) {
                showAlert(Alert.AlertType.ERROR, "Fehler", "Benutzername ist bereits vergeben.");
                return;
            }

            // Neuen Benutzer erstellen und zur Liste hinzufügen
            newUser = new User(username, password, gender, birthDate, Integer.parseInt(weight), Integer.parseInt(height));
            users.add(newUser);
            UserFileUtil.saveUsers(users); // Benutzerliste speichern

        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Fehler", "Fehler beim Speichern der Benutzerdaten.");
            return;
        }

        showSuccessAlert(newUser);  // Übergeben Sie newUser an showSuccessAlert
    }

    //Erfolg-Meldung bei erfolgreicher Registrierung
    private void showSuccessAlert(User newUser) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Erfolgreich");
        alert.setContentText("Registrierung erfolgreich.");
        alert.showAndWait().ifPresent(response -> {
            System.out.println("Setting currentUser: " + newUser.getName());
            Controller.setCurrentUser(newUser.getName());
            openMainWindow();
            closeRegisterWindow();
        });
    }

    @FXML
    private void initialize() {
        genderChoiceBox.getItems().addAll("Weiblich", "Männlich", "Diverse");
        try {
            users = UserFileUtil.readUsers();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Methode zur Validierung der Eingaben
    private boolean validateInputs(String username, String password, String gender, String weight, String height, LocalDate birthDate) {
        if (username.isEmpty() || password.isEmpty() || gender == null || gender.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Fehler", "Bitte füllen Sie alle Felder korrekt aus.");
            return false;
        }

        if (weight.isEmpty() || !weight.matches("\\d+") || Integer.parseInt(weight) <= 0) {
            showAlert(Alert.AlertType.ERROR, "Fehler", "Ist das wirklich Ihr Körpergewicht? Bitte geben Sie eine positive Ganzzahl ein.");
            return false;
        }

        if (height.isEmpty() || !height.matches("\\d+") || Integer.parseInt(height) <= 0) {
            showAlert(Alert.AlertType.ERROR, "Fehler", "Ist das wirklich Ihre Körpergröße? Bitte geben Sie eine positive Ganzzahl ein.");
            return false;
        }

        if (birthDate == null || birthDate.isAfter(LocalDate.now())) {
            showAlert(Alert.AlertType.ERROR, "Fehler", "Geburtstag befindet sich in der Zukunft.");
            return false;
        }

        return true;
    }

    // Methode zur Anzeige von Fehlermeldungen
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Methode zum Öffnen des Hauptfensters der Anwendung
    private void openMainWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("SportAppMainScene.fxml"));
            Stage stage = new Stage();
            stage.setTitle("SportApp");
            stage.setScene(new javafx.scene.Scene(fxmlLoader.load()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Methode zum Schließen des Registrierungsfensters
    private void closeRegisterWindow() {
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.close();
    }

    // Methode zum Setzen der Benutzerliste
    public static void setUsers(List<User> usersList) {
        users = usersList;
    }
}
