package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import java.net.URL;
import java.util.ResourceBundle;

public class user_flight_Controller implements Initializable {

    @FXML
    private ChoiceBox<String > choisebox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choisebox.getItems().addAll("Economy","Business","First Class");


    }
}
