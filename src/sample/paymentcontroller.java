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
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import javax.security.sasl.SaslException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.Random;
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
    void mobile_no(KeyEvent event)  {
        String no=card_no.getText();
        if(no.length()==11){
            try {

                Random rand = new Random();
                String message = "TRXID: " + String.valueOf(rand.nextInt(100000000));
                String phone = no;
                String username = "Imran";
                String password = "1234";
                String address = "http://192.168.0.101";
                String port = "8093";

                URL url = new URL(
                        address + ":" + port + "/SendSMS?username=" + username + "&password=" + password +
                                "&phone=" + phone + "&message=" + URLEncoder.encode(message, "UTF-8"));

                URLConnection connection = url.openConnection();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                while ((inputLine = bufferedReader.readLine()) != null) {
                    System.out.println(inputLine);
                }
                bufferedReader.close();
            } catch (UnsupportedEncodingException e) {
                System.out.println("Couldn't sent msg");
            } catch (MalformedURLException e) {
                System.out.println("Couldn't sent msg");
            } catch (IOException e) {
                System.out.println("Couldn't sent msg");
            }


        }

    }

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
        /*amount.setText(seatViewController.seat.size() * );*/
        type.setText(User_Flight_Search_Controller.OneWayOrRound);
        if(User_Flight_Search_Controller.SeatClass!=null)
        {
            seat_class.setText(User_Flight_Search_Controller.SeatClass);
        }
        else {
            seat_class.setText(SearchResult.u);
        }
        seat_no.setText(seatViewController.seat.toString().replace('[',' ').replace(']',' '));
        amount.setText(String.valueOf(SearchResult.l*seatViewController.seat.size()));
    }
}
