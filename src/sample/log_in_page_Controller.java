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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javafx.scene.image.Image;

import java.awt.*;
import java.io.*;

public class log_in_page_Controller {

    @FXML
    private PasswordField password;

    @FXML
    private TextField user;

    @FXML
    void lang(MouseEvent event) {

    }
    public static JSONArray j;
    public static Image emg=null;
    public static String username=null;
    public  static  JSONObject obj=null;
    public  static  Object[] d;
    @FXML
    void login(ActionEvent event) throws ParseException, IOException ,FileNotFoundException{
        Object o = new JSONParser().parse(new FileReader("userinfo.json"));
        obj = (JSONObject) o;
        username=user.getText();
        String Checkpass=null;
        j= (JSONArray) obj.get(username);
        if(j!=null) {
            File fl = null;
            if (j.get(7) != null) {
                fl = new File((String) j.get(7));
            }
            try {
                if (fl != null) {
                    emg = new Image(new FileInputStream(fl));
                }
            } catch (FileNotFoundException e) {

            }
            Checkpass= (String) j.get(5);
        }

        if(password.getText().equals(Checkpass))
        {

            Parent n= FXMLLoader.load(getClass().getResource("user__Panel.fxml"));
            Scene n1=new Scene(n);
            Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setTitle("Profile");
            window.setScene(n1);
            window.show();


        }
        else{
            Toolkit.getDefaultToolkit().beep();
            Alert Logout=new Alert(Alert.AlertType.WARNING);
            Logout.setContentText("Incorrect Password");
            Logout.setHeaderText("Login");
            Logout.show();
        }



    }

    @FXML
    void sign_up(ActionEvent event) throws IOException, ParseException {
        Object o = new JSONParser().parse(new FileReader("userinfo.json"));
        obj = (JSONObject) o;
        d= obj.keySet().toArray();
        Parent n= FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        Scene n1=new Scene(n);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("SignUp");
        window.setScene(n1);
        window.show();

    }

    @FXML
    void adminlogin(ActionEvent event) throws IOException {

        Parent n= FXMLLoader.load(getClass().getResource("AdminLogin.fxml"));
        Scene n1=new Scene(n);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Admin Login");
        window.setScene(n1);
        window.show();

    }

}
