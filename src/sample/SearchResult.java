package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

public class SearchResult implements Initializable {

    public static Integer l=0;
    public static String t=null;
    public  static  String r=null;
    @FXML
    private javafx.scene.control.ListView<String> ListView;
//    ObservableList<String> Answer = FXCollections.observableArrayList("Hello", "sello", "mello");
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ListView.setItems(User_Flight_Search_Controller.ResultsOfSearch);
        ListView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount() == 2) {
                   String[] s= ListView.getSelectionModel().getSelectedItem().split(" ");
                   l=Integer.valueOf(s[s.length-1].replace("$",""));
                  t=s[11]+" "+s[12];
                  r=s[0]+"-"+s[1].replace("-","");
                    Parent n= null;
                    try {
                        n = FXMLLoader.load(getClass().getResource("seatView.fxml"));
                        Scene n1=new Scene(n);
                        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                        window.setTitle("Seat Selection");
                        window.setScene(n1);
                        window.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }

            }
        });
    }
}
