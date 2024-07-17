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

import static apps.sportapp.BackEnd.*;


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

    private static int currentUser;

    @FXML
    void löscheData(MouseEvent event) {
        int selectedID = listOfEvents.getSelectionModel().getSelectedIndex();
        listOfEvents.getItems().remove(selectedID);

        String selected = stringList.get(selectedID);
        String[] delete = selected.split(", ");
        LocalDate date = LocalDate.parse(delete[4]);
        RegisterController.users[currentUser].deleteFromDays(date, Integer.parseInt(delete[2]), Integer.parseInt(delete[0]), Integer.parseInt(delete[1]));
        System.out.println("Änderungen (deleteData) vorgenommen am User: "+RegisterController.users[currentUser].name);

        deleteString(selectedID);
    }

    @FXML
    void saveData(MouseEvent event) {
        LocalDate time = datePicker.getValue();
        String sport = sportart.getSelectionModel().getSelectedItem();
        listOfEvents.getItems().add(zunahmeIn.getText() + ", " + verbrauchIn.getText() + ", " + schritteIn.getText() + ", " + sport + "," + time);
        saveString(zunahmeIn.getText() + ", " + verbrauchIn.getText() + ", " + schritteIn.getText() + ", " + sport + ", " + time);

        RegisterController.users[currentUser].setDays(time, Integer.parseInt(schritteIn.getText()), Integer.parseInt(zunahmeIn.getText()), Integer.parseInt(verbrauchIn.getText()));
        System.out.println("Änderungen (saveData) vorgenommen am User: "+RegisterController.users[currentUser].name);
    }

        @Override
        public void initialize (URL url, ResourceBundle resourceBundle){
            sportart.getItems().addAll("       ", "Ausdauersport", "Kraftsport", "Kampfsport", "Bettsport", "Kopfsport");
        }

        public static void setCurrentUser(String name){
            int id = 0;
            for(int i =0; i < RegisterController.users.length; i++){
                if(RegisterController.users[i].name.equals(name)){
                    id = i;
                }
            }
            currentUser = id;
            System.out.println("Angemeldeter User:  "+RegisterController.users[id].name);
        }

}
