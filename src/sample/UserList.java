package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class UserList {

    @FXML
    private TextField searchbar;

    @FXML
    private TextField username;

    @FXML
    private TextField fname;

    @FXML
    private TextField lname;

    @FXML
    private TextField date;

    @FXML
    private TextField gender;

    @FXML
    private TextField mail;

    @FXML
    void userlist(KeyEvent event) {
        String usrname = searchbar.getText();
        Object obj = null;
        try {
            obj = new JSONParser().parse(new FileReader("userinfo.json"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        JSONObject o=(JSONObject) obj;
        JSONArray data=(JSONArray) o.get(usrname);
        if(data!=null){
            username.setText(searchbar.getText());
            fname.setText(data.get(0).toString());
            lname.setText(data.get(1).toString());
            date.setText(data.get(2).toString()+data.get(3).toString()+data.get(4).toString());
            mail.setText(data.get(6).toString());
            gender.setText(data.get(8).toString());

        }
        else {
            username.setText(null);
            fname.setText(null);
            lname.setText(null);
            date.setText(null);
            mail.setText(null);
            gender.setText(null);
        }

    }

}
