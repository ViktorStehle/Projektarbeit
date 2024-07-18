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

    protected static User[] users = new User[0]; //Userverwaltung, indem alle User in einem Array gespeichert werden

    @FXML
    private void handleSubmit() {
        String gender = genderChoiceBox.getValue();
        String weight = weightField.getText();
        LocalDate birthDate = birthDatePicker.getValue();
        String height = heightField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Validate inputs
        if (username.isEmpty() || password.isEmpty() ||
                gender == null || gender.isEmpty() ||
                weight.isEmpty() || !weight.matches("\\d+(\\.\\d{1,2})?") ||
                birthDate == null ||
                height.isEmpty() || !height.matches("\\d+")) {
            showAlert(Alert.AlertType.ERROR, "Fehler", "Bitte füllen Sie alle Felder korrekt aus.");
            return;
        }

        // Save user data
        String userData = String.join(",", username, password, gender, weight, birthDate.toString(), height);

        try {
            List<String> existingUsers = UserFileUtil.readUsers();
            boolean userExists = existingUsers.stream().anyMatch(line -> line.startsWith(username + ","));

            if (userExists) {
                showAlert(Alert.AlertType.ERROR, "Fehler", "Benutzername ist bereits vergeben.");
                return;
            }

            //Abspeichern eines neuen Users im users-Array
            User newUser = new User(username, password, gender, LocalDate.parse(birthDate.toString()),Integer.parseInt(weight), Integer.parseInt(height));
            users = User.appendUser(users, newUser);
            Controller.setCurrentUser(newUser.name);
            UserFileUtil.saveUser(userData);

            showAlert(Alert.AlertType.INFORMATION, "Erfolgreich", "Registrierung erfolgreich.");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Fehler", "Fehler beim Speichern der Benutzerdaten.");
            return;
        }

        // Close registration window and open main window
        Stage stage = (Stage) genderChoiceBox.getScene().getWindow();
        stage.close();
        openMainWindow();
    }

    @FXML
    private void initialize() {
        genderChoiceBox.getItems().addAll("Weiblich", "Männlich", "Diverse");
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
}