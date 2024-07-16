package apps.sportapp;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class UserFileUtil {

    private static final String FILE_PATH = Main.USER_FILE_PATH;

    public static void saveUser(String data) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(data);
            writer.newLine();
        }
    }

    public static List<String> readUsers() throws IOException {
        if (Files.exists(Paths.get(FILE_PATH))) {
            return Files.readAllLines(Paths.get(FILE_PATH));
        } else {
            return List.of();
        }
    }
}