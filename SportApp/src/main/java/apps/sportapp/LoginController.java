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
            List<User> users = UserFileUtil.readUsers();  // Read User objects

            boolean authenticated = users.stream()
                    .anyMatch(user -> user.getName().equals(username) && user.getPassword().equals(password));

            if (authenticated) {
                Controller.setCurrentUser(username);
                Stage currentStage = (Stage) usernameField.getScene().getWindow(); // Get current stage
                openMainWindow(); // Open the main window
                currentStage.close(); // Close the login window
            } else {
                showAlert("Login Error", "Invalid username or password.");
            }
        } catch (IOException | ClassNotFoundException e) {
            showAlert("Error", "Error reading user data.");
        }
    }

    @FXML
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
    private void handleCancel() {
        ((Stage) usernameField.getScene().getWindow()).close();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

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