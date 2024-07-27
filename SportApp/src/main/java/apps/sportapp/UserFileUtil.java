package apps.sportapp;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserFileUtil {

    private static final String USER_DATA_FILE = "users.dat";

    public static List<User> readUsers() throws IOException, ClassNotFoundException {
        List<User> users = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USER_DATA_FILE))) {
            users = (List<User>) ois.readObject();
        } catch (FileNotFoundException e) {
            // Datei existiert noch nicht, wir starten mit einer leeren Liste
        }
        return users;
    }

    public static void saveUsers(List<User> users) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USER_DATA_FILE))) {
            oos.writeObject(users);
        }
    }
}