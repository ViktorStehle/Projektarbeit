package apps.sportapp;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Die Klasse UserFileUtil bietet Methoden zum Lesen und Speichern von Benutzerdaten in einer Datei
public class UserFileUtil {

    private static final String USER_DATA_FILE = "users.dat"; // Pfad zur Datei, in der die Benutzerdaten gespeichert werden

    // Methode zum Lesen der Benutzerdaten aus der Datei
    public static List<User> readUsers() throws IOException, ClassNotFoundException {
        List<User> users = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USER_DATA_FILE))) {
            users = (List<User>) ois.readObject();
        } catch (FileNotFoundException e) {
            // Datei existiert noch nicht, wir starten mit einer leeren Liste
        }
        return users; // Gibt die gelesene Benutzerliste zurück
    }

    // Methode zum Speichern der Benutzerdaten in der Datei
    public static void saveUsers(List<User> users) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USER_DATA_FILE))) {
            oos.writeObject(users); // Schreibt die Benutzerliste in die Datei
        }
    }
}
