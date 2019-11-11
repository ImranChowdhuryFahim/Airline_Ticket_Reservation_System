package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class Delete {

    @FXML
    private TextField searchFlight;

    @FXML
    private TextField FlightNO;

    @FXML
    private TextField FlightName;

    @FXML
    private TextField Source;

    @FXML
    private TextField Destination;

    @FXML
    private TextField DepartTime;

    @FXML
    private TextField Arivaltime;

    @FXML
    private TextField fare;
    public String flighNoDelete;
    @FXML
    private TextField Clas;
    JSONObject deleteJson;
    @FXML
    void DeletePressed(ActionEvent event) {
        deleteJson.remove("Destination");
        deleteJson.remove("Fare");
        System.out.println("o");
    }


    @FXML
    void keyPressed(KeyEvent event) {
        String flightNo = searchFlight.getText();
        System.out.println(flightNo);
        Object obj = null;
        try {
            obj = new JSONParser().parse(new FileReader("flightinfo.json"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        int i = 0;
        JSONArray JArray = (JSONArray) obj;


        for(Object x: JArray){
            JSONObject JsonObject = (JSONObject) x;
            if( JsonObject.get("Flight no").equals(flightNo) ){
                flighNoDelete = flightNo;
                FlightNO.setText(flightNo);
                FlightName.setText(JsonObject.get("Flight Name").toString());
                Source.setText(JsonObject.get("Source").toString());
                Destination.setText(JsonObject.get("Destination").toString());
                DepartTime.setText(JsonObject.get("Departure Time").toString());
                Arivaltime.setText(JsonObject.get("Arrival Time").toString());
                fare.setText(JsonObject.get("Fare").toString());
//                Clas.setText(JsonObject.get("Class").toString());
                deleteJson = JsonObject;
            }
        }

    }

}
