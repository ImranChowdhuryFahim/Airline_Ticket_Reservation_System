package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Update {

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

    @FXML
    private TextField cls;

    @FXML
    private TextField searchFlight;
    JSONArray JArray;

    JSONObject deleteJson;
    public String flighNoDelete;

    @FXML
    void UpdatePressed(ActionEvent event) {

        JSONObject j=(JSONObject) JArray.get(JArray.indexOf(deleteJson));
        j.put("Flight no",FlightNO.getText());
        j.put("Flight Name",FlightName.getText());
        j.put("Source",Source.getText());
        j.put("Destination",Destination.getText());
        j.put("Departure Time",DepartTime.getText());
        j.put("Arrival Time",Arivaltime.getText());
        j.put("Fare",fare.getText());
        j.put("Class",cls.getText());
        JArray.remove(JArray.get(JArray.indexOf(deleteJson)));
        JArray.add(j);
        try {
            FileWriter f=new FileWriter("flightinfo.json");
            f.write(JArray.toJSONString());
            f.flush();
            Toolkit.getDefaultToolkit().beep();
            JOptionPane optionPane = new JOptionPane("Flight Info has been Updated successfully");
            JDialog dialog = optionPane.createDialog("Message");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void keyPressed(KeyEvent event) {
        String flightNo = searchFlight.getText();
        Object obj = null;
        try {
            obj = new JSONParser().parse(new FileReader("flightinfo.json"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        JArray = (JSONArray) obj;


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
                cls.setText(JsonObject.get("Class").toString());
                deleteJson = JsonObject;
                break;

            }
        }


    }

}
