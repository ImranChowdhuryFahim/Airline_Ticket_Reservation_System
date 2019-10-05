package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import netscape.javascript.JSObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddFlight implements Initializable {

    @FXML
    private TextField fno;

    @FXML
    private TextField fname;

    @FXML
    private TextField src;

    @FXML
    private TextField dest;

    @FXML
    private TextField depttime;

    @FXML
    private TextField arrtime;

    @FXML
    private TextField fare;

    @FXML
    private ChoiceBox<String> choisebox;

    @FXML
    void add(ActionEvent event) throws IOException, ParseException {
        Object o = new JSONParser().parse(new FileReader("flightinfo.json"));
        JSONArray jr=(JSONArray) o;
        JSONObject j=new JSONObject();
        j.put("Flight no",fno.getText());
        j.put("Flight Name",fname.getText());
        j.put("Source",src.getText());
        j.put("Destination",dest.getText());
        j.put("Departure Time",depttime.getText());
        j.put("Arrival Time",arrtime.getText());
        j.put("Fare",fare.getText());
        j.put("Class",choisebox.getValue());
        jr.add(j);
        try {
            FileWriter f=new FileWriter("flightinfo.json");
            f.write(jr.toJSONString());
            f.flush();
            Toolkit.getDefaultToolkit().beep();
            JOptionPane optionPane = new JOptionPane("Flight Info has been added successfully");
            JDialog dialog = optionPane.createDialog("Message");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choisebox.getItems().addAll("First Class","Economy Class","Business Class");


    }
}

