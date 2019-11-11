package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class AdminLoginController {

    @FXML
    private PasswordField apass;

    @FXML
    private TextField aname;

    @FXML
    void login(ActionEvent event) throws IOException, ParseException {
        Object o = new JSONParser().parse(new FileReader("add_admin.json"));
        JSONObject obj = (JSONObject) o;
        JSONArray j= (JSONArray) obj.get(aname.getText());
        System.out.println(j.get(0));
        if(j!=null)
        {
            if(aname.getText().equals(j.get(0)) && apass.getText().equals(j.get(1))){
                Parent n= FXMLLoader.load(getClass().getResource("adminPanel.fxml"));
                Scene n1=new Scene(n);
                Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setTitle("Admin Panel");
                window.setScene(n1);
                window.show();
            }
            else {
                Toolkit.getDefaultToolkit().beep();
                Alert Logout=new Alert(Alert.AlertType.WARNING);
                Logout.setContentText("Incorrect Password");
                Logout.setHeaderText("Login");
                Logout.show();
            }

        }
        else {
            Toolkit.getDefaultToolkit().beep();
            Alert Logout=new Alert(Alert.AlertType.WARNING);
            Logout.setContentText("Invalid Username");
            Logout.setHeaderText("Login");
            Logout.show();
        }

    }

}
