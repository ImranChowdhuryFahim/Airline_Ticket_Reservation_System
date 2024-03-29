package sample;

import com.sun.javaws.jnl.JARDesc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sun.security.krb5.internal.crypto.Des;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.*;
import java.util.jar.JarOutputStream;


public class User_Flight_Search_Controller implements Initializable {

    public  static ObservableList<String>  ResultsOfSearch = FXCollections.observableArrayList();
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

        Object obj = null;
        try {
            obj = new JSONParser().parse(new FileReader("flightinfo.json"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int i = 0;
        JSONArray JArray = (JSONArray) obj;
        String[] AllPossibleFrom = new String[JArray.size()], AllPossibleTo = new String[JArray.size()];

        for(Object x: JArray){
            JSONObject JsonObject = (JSONObject) x;
            AllPossibleFrom[i] = JsonObject.get("Source").toString();
            AllPossibleTo[i] = JsonObject.get("Destination").toString();

            i++;
        }
        Set<String> AllDestination = new HashSet<String>();
        Set<String> AllSource = new HashSet<String>();
        Collections.addAll(AllDestination, AllPossibleTo);
        Collections.addAll(AllSource, AllPossibleFrom);

        SeatClassChoisebox.getItems().addAll("Economy Class","Business Class","First Class");
        TextFields.bindAutoCompletion(From, AllSource);
        TextFields.bindAutoCompletion(To, AllDestination);
    }

    int TestTheSearchValidity(String From, String To){
        return (From.equals("") && To.equals("")) ? 3 : (From.equals("") ? 1 : (To.equals("") ? 2: 7));
    }

    public static String FromString=null;
    public static String DestinationString=null;
    public static String JourneyDateString=null;
    public static String OneWayOrRound=null;
    public static String SeatClass=null;

    @FXML
    void Seach(ActionEvent event) throws IOException, ParseException {
        warningbesidesFrom.setText("");
        warningbesidesTo.setText("");
        FromString = From.getText();
        DestinationString = To.getText();
        if(JourneyDate.getValue()!=null){
            JourneyDateString = JourneyDate.getValue().toString();
        }
        else {
            JourneyDateString=null;
        }
        OneWayOrRound = OneWay.isSelected() ? OneWay.getText() : RoundWay.getText();
        SeatClass = SeatClassChoisebox.getValue();
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
            int f=1;
            if(SeatClass==null)
            {
                f=0;
            }
            //parse fare from the string
            String Fare = JtempObject.get("Fare").toString().replace("$", "");
            // System.out.println(DestinationStringious commit + " " +  JtempObject.get("Destination") + " " + JtempObject.get("Source") + " " + FromString);
             if(f==1) {
                 if (JtempObject.get("Destination").equals(DestinationString) && JtempObject.get("Source").equals(FromString) && JtempObject.get("Class").equals(SeatClass)) {

                     if (OneWayOrRound == "y") {
                         if (JtempObject.get("Class").equals(OneWayOrRound)) {
                             System.out.println("we are **here");

                         }
                     } else {
                         //System.out.println("we are here");
                         Flight tempFligth = new Flight(JtempObject.get("Flight Name").toString(), FromString, DestinationString, Integer.valueOf(Fare), JtempObject.get("Class").toString(), JtempObject.get("Departure Time").toString());

                         PossibleFlight.add(tempFligth);

                     }
                 }
             }
             else{
                 if (JtempObject.get("Destination").equals(DestinationString) && JtempObject.get("Source").equals(FromString)) {

                     if (OneWayOrRound == "y") {
                         if (JtempObject.get("Class").equals(OneWayOrRound)) {
                             System.out.println("we are **here");

                         }
                     } else {
                         //System.out.println("we are here");
                         Flight tempFligth = new Flight(JtempObject.get("Flight Name").toString(), FromString, DestinationString, Integer.valueOf(Fare), JtempObject.get("Class").toString(), JtempObject.get("Departure Time").toString());

                         PossibleFlight.add(tempFligth);

                     }
                 }

             }


            // System.out.println(JtempObject);
        }
        int j = 0;
        Collections.sort(PossibleFlight);
        for(Flight x: PossibleFlight){
            ResultsOfSearch.add(x.getTo() + "-" + x.getFrom() + "         Flight Name: " + x.getFlightName() + "         Fare: " + x.getFare() + "$"  +"    "+"Class: "+x.getSeatClass());
            PrintStream var10000 = System.out;
        }

        Parent n= FXMLLoader.load(getClass().getResource("user_flight_search_result.fxml"));
        Scene n1=new Scene(n);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Profile");
        window.setScene(n1);
        window.show();
    }



}
