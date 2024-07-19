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
    private ChoiceBox<String> genderChoiceBox;

    @FXML
    private TextField weightField;

    @FXML
    private DatePicker birthDatePicker;

    @FXML
    private TextField heightField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    protected static List<User> users = new ArrayList<>();  // Speichert alle Benutzer

    @FXML
    private void handleSubmit() {
        String gender = genderChoiceBox.getValue();
        String weight = weightField.getText();
        LocalDate birthDate = birthDatePicker.getValue();
        String height = heightField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Validate inputs
        if (username.isEmpty() || password.isEmpty() || gender == null || gender.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Fehler", "Bitte füllen Sie alle Felder korrekt aus.");
            return;
        }

        if (weight.isEmpty() || !weight.matches("\\d+")) {
            showAlert(Alert.AlertType.ERROR, "Fehler", "Ist das wirklich Ihr Körpergewicht? Bitte geben Sie eine positive Ganzzahl ein.");
            return;
        }

        int weightValue = Integer.parseInt(weight);
        if (weightValue <= 0) {
            showAlert(Alert.AlertType.ERROR, "Fehler", "Ist das wirklich Ihr Körpergewicht? Körpergewicht muss größer als 0 sein.");
            return;
        }

        if (height.isEmpty() || !height.matches("\\d+")) {
            showAlert(Alert.AlertType.ERROR, "Fehler", "Ist das wirklich Ihre Körpergröße? Bitte geben Sie eine positive Ganzzahl ein.");
            return;
        }

        int heightValue = Integer.parseInt(height);
        if (heightValue <= 0) {
            showAlert(Alert.AlertType.ERROR, "Fehler", "Ist das wirklich Ihre Körpergröße? Körpergröße muss größer als 0 sein.");
            return;
        }

        if (birthDate == null) {
            showAlert(Alert.AlertType.ERROR, "Fehler", "Bitte wählen Sie ein Geburtsdatum.");
            return;
        }

        if (birthDate.isAfter(LocalDate.now())) {
            showAlert(Alert.AlertType.ERROR, "Fehler", "Geburtstag befindet sich in der Zukunft.");
            return;
        }

        // Save user data
        try {
            // Check if username already exists
            boolean userExists = users.stream().anyMatch(user -> user.getName().equals(username));

            if (userExists) {
                showAlert(Alert.AlertType.ERROR, "Fehler", "Benutzername ist bereits vergeben.");
                return;
            }

            // Abspeichern eines neuen Users in der users-Liste
            User newUser = new User(username, password, gender, birthDate, weightValue, heightValue);
            users.add(newUser);

            UserFileUtil.saveUsers(users);

            showAlert(Alert.AlertType.INFORMATION, "Erfolgreich", "Registrierung erfolgreich.");

            // Set current user and open main window
            Controller.setCurrentUser(newUser.getName());
            openMainWindow();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Fehler", "Fehler beim Speichern der Benutzerdaten.");
        }
    }

    @FXML
    private void initialize() {
        genderChoiceBox.getItems().addAll("Weiblich", "Männlich", "Diverse");
        try {
            users = UserFileUtil.readUsers();  // Laden Sie Benutzer beim Initialisieren des Controllers
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void openMainWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("SportAppMainScene.fxml"));
            javafx.scene.Scene scene = new javafx.scene.Scene(fxmlLoader.load());
            Stage mainStage = new Stage();
            mainStage.setTitle("SportApp");
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setUsers(List<User> usersList) {
        users = usersList;
    }
}