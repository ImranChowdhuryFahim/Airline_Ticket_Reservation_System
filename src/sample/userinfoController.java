package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class userinfoController  implements Initializable {

    @FXML
    private ImageView img;

    @FXML
    private Label name;

    @FXML
    private Label usrname;

    @FXML
    private Label mail;

    @FXML
    private Label pst;

    @FXML
    private Label upflight;

    @FXML
    private Label deptime;
//
//    @FXML
//    void b(ActionEvent event) {
//        System.out.println("hello");
//
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Object o = null;
        try {
            o = new JSONParser().parse(new FileReader("userinfo.json"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONObject obj = (JSONObject) o;
        JSONArray j = (JSONArray) obj.get(log_in_page_Controller.username);


        name.setText((String) j.get(0)+" "+(String)j.get(1));
        if(update_user_info.updatedname==null){
            usrname.setText((log_in_page_Controller.username));}
        else {
            usrname.setText((update_user_info.updatedname));
        }
        mail.setText((String) j.get(6));
        Image emg=null;
        try {
            emg = new Image(new FileInputStream(j.get(7).toString()));
        }
        catch (FileNotFoundException e)
        {

        }
        img.setImage(emg);
        upflight.setText("Null");
        deptime.setText("Null");
        pst.setText("Null");

    }
}
