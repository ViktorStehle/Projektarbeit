package apps.sportapp;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Controller {
    @FXML
    private TextField kcalPlusField;

    @FXML
    private TextField stepsField;

    @FXML
    private Label balanceLabel;

    @FXML
    private LineChart<String, Number> chart;

    @FXML
    private TextField durationField;

    @FXML
    private TextField distanceField;

    @FXML
    private Label speedLabel;

    @FXML
    private Label caloriesLabel;

    @FXML
    private ChoiceBox<String> sportChoiceBox;

    private static String currentUser;
    private User currentUserObj;

    public static void setCurrentUser(String username) {
        currentUser = username;
    }

    @FXML
    public void initialize() {
        loadCurrentUser();
        populateChartData();
        sportChoiceBox.setOnAction(e -> updateSportFields());

        // Initialize sportChoiceBox with options
        sportChoiceBox.setItems(FXCollections.observableArrayList("Joggen", "Radfahren", "Schwimmen"));
    }

    private void loadCurrentUser() {
        if (currentUser == null) {
            return;
        }
        currentUserObj = RegisterController.users.stream()
                .filter(user -> user.getName().equals(currentUser))
                .findFirst()
                .orElse(null);
        if (currentUserObj == null) {
            showAlert(Alert.AlertType.ERROR, "Fehler", "Kein Benutzer ausgewählt.");
        }
    }

    @FXML
    private void handleSaveDaily() {
        LocalDate date = LocalDate.now();
        String kcalPlusText = kcalPlusField.getText();
        String stepsText = stepsField.getText();

        if (kcalPlusText.isEmpty() || stepsText.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Fehler", "Bitte füllen Sie alle Felder aus.");
            return;
        }

        try {
            int steps = Integer.parseInt(stepsText);
            double kcalPlus = Double.parseDouble(kcalPlusText);

            if (currentUserObj != null) {
                User.DailyData dailyData = currentUserObj.getDailyData(date);
                if (dailyData == null) {
                    dailyData = new User.DailyData(steps, kcalPlus, 0);
                } else {
                    dailyData.setSteps(steps);
                    dailyData.setKcalPlus(kcalPlus);
                }
                currentUserObj.getAllDailyData().put(date, dailyData);
                updateBalanceLabel(kcalPlus, dailyData.getKcalMinus());
                populateChartData();
                showAlert(Alert.AlertType.INFORMATION, "Erfolgreich", "Daten wurden erfolgreich gespeichert.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Fehler", "Kein Benutzer ausgewählt.");
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Fehler", "Bitte geben Sie gültige Zahlenwerte ein.");
        }
    }

    private void updateBalanceLabel(double kcalPlus, double kcalMinus) {
        double balance = kcalPlus - kcalMinus;
        if (balance > 0) {
            balanceLabel.setText("Kalorienbilanz: Positive (Mehr Kalorien aufgenommen als verbrannt)");
        } else if (balance < 0) {
            balanceLabel.setText("Kalorienbilanz: Negative (Mehr Kalorien verbrannt als aufgenommen)");
        } else {
            balanceLabel.setText("Kalorienbilanz: Neutral (Gleich viel Kalorien aufgenommen wie verbrannt)");
        }
    }

    private void populateChartData() {
        if (chart == null || currentUserObj == null) {
            return;
        }

        chart.getData().clear();

        // Erstellen der Datenreihen für das Diagramm
        XYChart.Series<String, Number> seriesIncrease = new XYChart.Series<>();
        seriesIncrease.setName("Kalorienzunahme");

        XYChart.Series<String, Number> seriesDecrease = new XYChart.Series<>();
        seriesDecrease.setName("Kalorienabnahme");

        XYChart.Series<String, Number> seriesDifference = new XYChart.Series<>();
        seriesDifference.setName("Kaloriendifferenz");

// Berechnen der letzten 7 Tage
        List<LocalDate> last7Days = LocalDate.now().minusDays(6).datesUntil(LocalDate.now().plusDays(1)).collect(Collectors.toList());

        for (LocalDate date : last7Days) {
            User.DailyData dailyData = currentUserObj.getDailyData(date);
            if (dailyData != null) {
                seriesIncrease.getData().add(new XYChart.Data<>(date.toString(), dailyData.getKcalPlus()));
                seriesDecrease.getData().add(new XYChart.Data<>(date.toString(), dailyData.getKcalMinus()));
                seriesDifference.getData().add(new XYChart.Data<>(date.toString(), dailyData.getKcalPlus() - dailyData.getKcalMinus()));
            } else {
                seriesIncrease.getData().add(new XYChart.Data<>(date.toString(), 0));
                seriesDecrease.getData().add(new XYChart.Data<>(date.toString(), 0));
                seriesDifference.getData().add(new XYChart.Data<>(date.toString(), 0));
            }
        }

// Hinzufügen aller Datenreihen zum Diagramm
        chart.getData().addAll(seriesIncrease, seriesDecrease, seriesDifference);
    }

    private void updateSportFields() {
        String selectedSport = sportChoiceBox.getValue();
        if (selectedSport != null) {
            switch (selectedSport) {
                case "Joggen":
                case "Radfahren":
                case "Schwimmen":
                    // No specific field updates, common inputs should be enabled (distance and duration)
                    break;
                default:
                    showAlert(Alert.AlertType.WARNING, "Achtung", "Bitte wählen Sie eine gültige Sportart aus.");
            }
        }
    }

    @FXML
    private void calculateSport() {
        String selectedSport = sportChoiceBox.getValue();
        String durationText = durationField.getText();
        String distanceText = distanceField.getText();

        if (durationText.isEmpty() || distanceText.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Fehler", "Bitte füllen Sie alle Felder aus.");
            return;
        }

        try {
            int duration = Integer.parseInt(durationText);
            float distance = Float.parseFloat(distanceText);

            if (duration <= 0 || distance <= 0) {
                showAlert(Alert.AlertType.ERROR, "Fehler", "Bitte geben Sie positive Werte für Dauer und Distanz ein.");
                return;
            }

            Sportaktivität sport;
            switch (selectedSport) {
                case "Joggen":
                    sport = new Joggen(duration, distance);
                    break;
                case "Radfahren":
                    sport = new Radfahren(duration, distance);
                    break;
                case "Schwimmen":
                    sport = new Schwimmen(duration, distance);
                    break;
                default:
                    showAlert(Alert.AlertType.WARNING, "Achtung", "Bitte wählen Sie eine gültige Sportart aus.");
                    return;
            }

            double kcalMinus = sport.getKcal();

            if (sport instanceof Ausdauersportarten) {
                speedLabel.setText(String.format("Durchschnittsgeschwindigkeit: %.2f", ((Ausdauersportarten) sport).getGeschwindigkeit()));
            }

            if (currentUserObj != null) {
                LocalDate today = LocalDate.now();
                User.DailyData dailyData = currentUserObj.getDailyData(today);
                if (dailyData == null) {
                    dailyData = new User.DailyData(0, 0, kcalMinus);
                } else {
                    dailyData.setKcalMinus(kcalMinus);
                }
                currentUserObj.getAllDailyData().put(today, dailyData);
                updateBalanceLabel(dailyData.getKcalPlus(), kcalMinus);
                populateChartData();
                showAlert(Alert.AlertType.INFORMATION, "Erfolgreich", "Aktivität wurde erfolgreich gespeichert.");
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Fehler", "Bitte geben Sie gültige Zahlenwerte ein.");
        }
    }

    @FXML
    private void logout() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
            javafx.scene.Scene scene = new javafx.scene.Scene(fxmlLoader.load());
            Stage stage = (Stage) sportChoiceBox.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Fehler", "Fehler beim Laden des Anmeldebildschirms.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}