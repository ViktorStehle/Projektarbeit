package apps.sportapp;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String password;
    private String gender;
    private LocalDate birthDate;
    private int weight;
    private int height;
    private Map<LocalDate, DailyData> dailyData;

    // Konstruktor
    public User(String name, String password, String gender, LocalDate birthDate, int weight, int height) {
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.birthDate = birthDate;
        this.weight = weight;
        this.height = height;
        this.dailyData = new HashMap<>();
    }

    // Getter-Methoden
    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    // Methode zum Hinzufügen von Kalorienaufnahme und Kalorienverbrauch für einen bestimmten Tag
    public void addKcalToDailyData(LocalDate date, double kcalPlus, double kcalMinus) {
        DailyData data = dailyData.getOrDefault(date, new DailyData(0, 0, 0));
        data.setKcalPlus(data.getKcalPlus() + kcalPlus); // Hinzufügen der Aufnahme
        data.setKcalMinus(data.getKcalMinus() + kcalMinus); // Hinzufügen des Verbrauchs
        dailyData.put(date, data);
    }

    // Methode zum Abrufen von Tagesdaten
    public DailyData getDailyData(LocalDate date) {
        return dailyData.get(date);
    }

    // Methode zum Abrufen aller Tagesdaten
    public Map<LocalDate, DailyData> getAllDailyData() {
        return dailyData;
    }

    // Innere Klasse zur Verwaltung der täglichen Daten
    public static class DailyData implements Serializable {
        private static final long serialVersionUID = 1L;
        private int steps;
        private double kcalPlus;
        private double kcalMinus;

        public DailyData(int steps, double kcalPlus, double kcalMinus) {
            this.steps = steps;
            this.kcalPlus = kcalPlus;
            this.kcalMinus = kcalMinus;
        }

        // Setter-Methoden
        public void setSteps(int steps) {
            this.steps = steps;
        }

        public void setKcalPlus(double kcalPlus) {
            this.kcalPlus = kcalPlus;
        }

        public void setKcalMinus(double kcalMinus) {
            this.kcalMinus = kcalMinus;
        }

        // Getter-Methoden
        public int getSteps() {
            return steps;
        }

        public double getKcalPlus() {
            return kcalPlus;
        }

        public double getKcalMinus() {
            return kcalMinus;
        }
    }
}