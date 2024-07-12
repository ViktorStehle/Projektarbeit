package apps.sportapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static apps.sportapp.BackEnd.deleteString;
import static apps.sportapp.BackEnd.saveString;


public class Controller implements Initializable {

    @FXML
    private ChoiceBox<String> sportart;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ListView<String> listOfEvents;

    @FXML
    private Text schritteOut;

    @FXML
    private TextField schritteIn;

    @FXML
    private TextField verbrauchIn;

    @FXML
    private Text verbrauchOut;

    @FXML
    private TextField zunahmeIn;

    @FXML
    private Text zunahmeOut;

    @FXML
    void löscheData(MouseEvent event) {
        int selectedID = listOfEvents.getSelectionModel().getSelectedIndex();
        listOfEvents.getItems().remove(selectedID);
        deleteString(selectedID);
    }

    @FXML
    void saveData(MouseEvent event) {
        LocalDate time = datePicker.getValue();
        String sport = sportart.getSelectionModel().getSelectedItem();
        listOfEvents.getItems().add(zunahmeIn.getText()+", " + verbrauchIn.getText()+ ", " + schritteIn.getText() + ", " + sport + "," + time);
        saveString("|" + zunahmeIn.getText()+", " + verbrauchIn.getText()+ ", " + schritteIn.getText() + ", " + sport + "," + time + "|");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sportart.getItems().addAll("Ausdauersport", "Kraftsport", "Kampfsport", "Bettsport", "Kopfsport");
    }

}

