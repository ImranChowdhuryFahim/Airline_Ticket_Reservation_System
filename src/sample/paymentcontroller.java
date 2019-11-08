package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class paymentcontroller implements Initializable {

    @FXML
    private Label from;

    @FXML
    private Label to;

    @FXML
    private Label type;

    @FXML
    private Label date;

    @FXML
    private Label seat_class;

    @FXML
    private Label amount;

    @FXML
    private TextField trxid;

    @FXML
    private TextField card_no;

    @FXML
    private Label seat_no;


    @FXML
    void confirm(ActionEvent event) throws IOException {

        Parent n= FXMLLoader.load(getClass().getResource("live_flight_update.fxml"));
        Scene n1=new Scene(n);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("SignUp");
        window.setScene(n1);
        window.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        from.setText(User_Flight_Search_Controller.FromString);
        to.setText(User_Flight_Search_Controller.DestinationString);
        date.setText(User_Flight_Search_Controller.JourneyDateString);
        type.setText(User_Flight_Search_Controller.OneWayOrRound);
        seat_class.setText(User_Flight_Search_Controller.SeatClass);
        seat_no.setText(seatViewController.seat.toString().replace('[',' ').replace(']',' '));
        amount.setText(String.valueOf(SearchResult.l*seatViewController.seat.size()));
    }
}
