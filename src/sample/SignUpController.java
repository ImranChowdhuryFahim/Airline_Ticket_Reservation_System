package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class SignUpController {
    @FXML
    private CheckBox f;

    @FXML
    private CheckBox agr;


    @FXML
    private ImageView userphoto;

    @FXML
    private CheckBox m;

    @FXML
    private File fl;

    String gender = "";

    @FXML
    private TextField fname;

    @FXML
    private TextField lname;


    @FXML
    private TextField username;

    @FXML
    private TextField email;

    @FXML
    private PasswordField pass;

    @FXML
    private PasswordField cpass;


    @FXML
    private DatePicker date;

    @FXML
    void female(MouseEvent event) {

    }

    @FXML
    void sign_in(ActionEvent event) throws IOException {
        Parent n= FXMLLoader.load(getClass().getResource("log_in_page.fxml"));
        Scene n1=new Scene(n);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Login");
        window.setScene(n1);
        window.show();

    }

    @FXML
    void upload(ActionEvent event) throws FileNotFoundException {
        FileChooser fc=new FileChooser();
        fl=fc.showOpenDialog(null);
        Image img=null;
        try {
            img = new Image(new FileInputStream(fl));
        }
        catch (FileNotFoundException e)
        {

        }
        userphoto.setImage(img);


    }

    @FXML
    void sign_up(ActionEvent event) throws IOException, ParseException {
        if(agr.isSelected()) {
            Object o = new JSONParser().parse(new FileReader("userinfo.json"));
            JSONObject obj = (JSONObject) o;
            JSONArray j = new JSONArray();
            j.add(0, fname.getText());
            j.add(1, lname.getText());
            j.add(2, date.getValue());

            j.add(3, pass.getText());
            j.add(4, email.getText());
            j.add(5, fl.toString());
            if (m.isSelected()) j.add(6, m.getText());
            else if (f.isSelected()) j.add(6, f.getText());


            obj.put(username.getText(), j);
            System.out.println(obj);

            try (FileWriter jfw = new FileWriter("userinfo.json")) {
                jfw.write(obj.toJSONString());
                jfw.flush();
            } catch (IOException e) {

            }
        }

    }

}
