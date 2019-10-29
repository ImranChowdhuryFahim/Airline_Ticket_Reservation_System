package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
    void confirm(ActionEvent event) {


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        from.setText(User_Flight_Search_Controller.FromString);
        to.setText(User_Flight_Search_Controller.DestinationString);
        date.setText(User_Flight_Search_Controller.JourneyDateString);
        type.setText(User_Flight_Search_Controller.OneWayOrRound);
        seat_class.setText(User_Flight_Search_Controller.SeatClass);
        seat_no.setText(seatViewController.seat.toString().replace('[',' ').replace(']',' '));
    }
}
