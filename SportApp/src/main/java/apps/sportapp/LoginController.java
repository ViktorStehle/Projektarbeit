package apps.sportapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        try {
            List<String> users = UserFileUtil.readUsers();

            //durch das Einlesen wird hier das users-Array aus RegisterController mit den Werten aus users.txt gesetzt
            String[] arr = new String[users.size()];
            users.toArray(arr);
            for(int i = 0; i < arr.length;i++){
                String[] userNow = arr[i].split(",");
                String name = userNow[0];
                String passwort = userNow[1];
                String geschlecht = userNow[2];
                LocalDate gbdate = LocalDate.parse(userNow[4]);
                double gewicht = Integer.parseInt(userNow[3]);
                double groesse = Integer.parseInt(userNow[5]);
                User newUser = new User(name, passwort, geschlecht, gbdate, gewicht, groesse);
                RegisterController.users = User.appendUser(RegisterController.users, newUser);
            }

            boolean authenticated = users.stream()
                    .anyMatch(line -> {
                        String[] parts = line.split(",");
                        return parts[0].equals(username) && parts[1].equals(password);
                    });

            if (authenticated) {
                Stage stage = (Stage) usernameField.getScene().getWindow();
                stage.close();
                openMainWindow();

                for(int i = 0; i < RegisterController.users.length; i++){
                    if(RegisterController.users[i].name.equals(username)){
                        Controller.setCurrentUser(username);
                    }
                }

            } else {
                showAlert(Alert.AlertType.ERROR, "Anmeldefehler", "Ungültiger Benutzername oder Passwort");
            }
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Fehler", "Fehler beim Lesen der Benutzerdaten.");
        }
    }

    @FXML
    private void handleRegister() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Register.fxml"));
            javafx.scene.Scene scene = new javafx.scene.Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Angaben zum Benutzer");
            stage.setScene(scene);
            stage.show();

            ((Stage) usernameField.getScene().getWindow()).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCancel() {
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.close();
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