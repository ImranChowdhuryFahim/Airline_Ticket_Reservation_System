package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sun.security.krb5.internal.crypto.Des;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;


public class User_Flight_Search_Controller implements Initializable {

    @FXML
    private Text warningbesidesFrom;

    @FXML
    private Text warningbesidesTo;

    @FXML
    private CheckBox OneWay;

    @FXML
    private CheckBox RoundWay;

    @FXML
    private ChoiceBox<String> SeatClassChoisebox;

    @FXML
    private DatePicker JourneyDate;

    @FXML
    private TextField From;

    @FXML
    private TextField To;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SeatClassChoisebox.getItems().addAll("Economy","Business","First Class");


    }

    int TestTheSearchValidity(String From, String To){
        return (From.equals("") && To.equals("")) ? 3 : (From.equals("") ? 1 : (To.equals("") ? 2: 7));
    }

    @FXML
    void Seach(ActionEvent event) throws IOException, ParseException {
        warningbesidesFrom.setText("");
        warningbesidesTo.setText("");
        String FromString = From.getText();
        String DestinationString = To.getText();
        String JourneyDateString = JourneyDate.toString();
        String OneWayOrRound = OneWay.isSelected() ? OneWay.getText() : RoundWay.getText();
        String SeatClass = SeatClassChoisebox.getValue();
        int validity = TestTheSearchValidity(FromString, DestinationString);
        if(validity != 7){
            String Warning = "You must fill this field";
            if(validity == 3){
                warningbesidesTo.setText(Warning);
                warningbesidesFrom.setText(Warning);
            }
            else if(validity == 1){
                warningbesidesFrom.setText(Warning);
            }
            else if(validity == 2){
                warningbesidesTo.setText(Warning);
            }

            return;
        }

        //flight data has been stored in json arrays, let's retrieve the data we need

        int cnt = 0;
        List<Flight> PossibleFlight = new ArrayList<Flight>();

        Object obj = new JSONParser().parse(new FileReader("flightinfo.json"));
        JSONArray JArray = (JSONArray) obj;
        for(Object tmpObject: JArray){
            JSONObject JtempObject = (JSONObject) tmpObject;

            //parse fare from the string
            String Fare = JtempObject.get("Fare").toString().replace("$", "");
          // System.out.println(DestinationString + " " +  JtempObject.get("Destination") + " " + JtempObject.get("Source") + " " + FromString);
            if(JtempObject.get("Destination").equals(DestinationString) && JtempObject.get("Source").equals(FromString)){

                if(OneWayOrRound == "y"){
                    if(JtempObject.get("Class").equals(OneWayOrRound)){
                        System.out.println("we are **here");

                    }
                }
                else{
                    //System.out.println("we are here");
                    Flight tempFligth = new Flight (JtempObject.get("Flight Name").toString(), FromString, DestinationString, Integer.valueOf(Fare) , JtempObject.get("Class").toString(), JtempObject.get("Departure Time").toString());

                    PossibleFlight.add(tempFligth);

                }
            }

           // System.out.println(JtempObject);
        }

        Collections.sort(PossibleFlight);
        for(Flight x: PossibleFlight){
            System.out.println( ++cnt + "." + x.getFlightName() + "         " + "Fare: " + x .getFare());
        }

    }


}
