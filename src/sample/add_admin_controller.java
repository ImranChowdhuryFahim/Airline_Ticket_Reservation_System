package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

public class add_admin_controller {

    @FXML
    private TextField adname;

    @FXML
    private TextField apass;

    @FXML
    void Add(ActionEvent event) throws IOException, ParseException {
        Object o = new JSONParser().parse(new FileReader("add_admin.json"));
        JSONObject obj = (JSONObject) o;
        JSONArray j = new JSONArray();
        j.add(0, adname.getText());
        j.add(1, apass.getText());
        obj.put(adname.getText(), j);
        try (FileWriter jfw = new FileWriter("add_admin.json")) {
            jfw.write(obj.toJSONString());
            jfw.flush();
            Toolkit.getDefaultToolkit().beep();
            Alert SignUp=new Alert(Alert.AlertType.INFORMATION);
            SignUp.setHeaderText("Add Admin");
            SignUp.setContentText("New Admin has been Added  successfully");
            SignUp.show();

        } catch (IOException e) {

        }

    }

}
