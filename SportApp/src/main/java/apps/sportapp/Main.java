package apps.sportapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class Main extends Application {

    public static final String USER_FILE_PATH = Paths.get("").toAbsolutePath().toString() + "/users.dat";

    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        List<User> users = UserFileUtil.readUsers();
        RegisterController.setUsers(users);

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("SportApp - Anmeldung");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws IOException {
        UserFileUtil.saveUsers(RegisterController.users);
    }

    public static void main(String[] args) {
        launch();
    }
}