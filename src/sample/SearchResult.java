package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

public class SearchResult implements Initializable {


    @FXML
    private javafx.scene.control.ListView<String> ListView;
//    ObservableList<String> Answer = FXCollections.observableArrayList("Hello", "sello", "mello");
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ListView.setItems(User_Flight_Search_Controller.ResultsOfSearch);
        ListView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                System.out.println(ListView.getSelectionModel().getSelectedItem());
            }
        });
    }
}
