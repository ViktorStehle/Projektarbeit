module apps.sportapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens apps.sportapp to javafx.fxml;
    exports apps.sportapp;
}