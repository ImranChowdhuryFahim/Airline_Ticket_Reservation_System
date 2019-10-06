package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Set;
import java.util.regex.Pattern;

public class SignUpController {
    @FXML
    private CheckBox f;

    @FXML
    private CheckBox isAgreed;


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
    private Label passvalid;

    @FXML
    private Label cpassvalid;

    @FXML
    private Label emailvallid;

    @FXML
    private Label usrvalid;

    @FXML
    private ImageView emaildone;

    @FXML
    private ImageView cpassdone;

    @FXML
    private ImageView passdone;

    @FXML
    private ImageView usrdone;


    @FXML
    void sign_in(ActionEvent event) throws IOException {
        Parent n= FXMLLoader.load(getClass().getResource("log_in_page.fxml"));
        Scene n1=new Scene(n);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Login");
        window.setScene(n1);
        window.show();

    }

    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    @FXML
    void emailvalidation(KeyEvent event) {
        if(email.getText().isEmpty())
        {
            emailvallid.setText("");
            emaildone.setImage(null);
        }
        else if(isValid(email.getText()))
        {
            emailvallid.setText("");
            Image img=new Image("sample/tick1.png");
            emaildone.setImage(img);

        }
        else {
            emaildone.setImage(null);
            emailvallid.setText("Invalid Email");
            emailvallid.setTextFill(Paint.valueOf("crimson"));
        }

    }

    @FXML
    void passvalidation(KeyEvent event) {

        String Password = pass.getText().toString();
        String UpperCaseCharacters = "(.*[A-Z].*)";
        String lowerCaseCharacters = "(.*[a-z].*)";
        String SpecialCharacters = "(.*[,~,!,@,#,$,%,^,&,*,(,),-,_,=,+,[,{,],},|,;,:,<,>,/,?].*)";
        String Numbers = "(.*[1, 2, 3, 4, 5, 6, 7, 8, 9, 0].*)";
        if(Password.isEmpty()){
            passvalid.setText("");
            passdone.setImage(null);
        }
        else if(!Password.matches(Numbers)){
            passvalid.setText("Password must contain at least one number");
            passvalid.setTextFill(Paint.valueOf("crimson"));
            passdone.setImage(null);
        }
        else if(!Password.matches(lowerCaseCharacters)){
            passvalid.setText("Password must contain at least one lower case letter");
            passvalid.setTextFill(Paint.valueOf("crimson"));
            passdone.setImage(null);
        }
        else if(!Password.matches(UpperCaseCharacters)){
            passvalid.setText("Password must contain at least one Upper-Case character");
            passvalid.setTextFill(Paint.valueOf("crimson"));
            passdone.setImage(null);
        }
        else if(!Password.matches(SpecialCharacters)){
            passvalid.setText("Password must contain at least one Special Character");
            passvalid.setTextFill(Paint.valueOf("crimson"));
            passdone.setImage(null);
        }
        else if(Password.length() != 8){
            passvalid.setText("Password must be of 8 characters");
            passvalid.setTextFill(Paint.valueOf("crimson"));
            passdone.setImage(null);
        }
        else if(Password.length()==8){
            passvalid.setText("");
            Image img = new Image("sample/tick1.png");
            passdone.setImage(img);
        }

    }

    @FXML
    void usrvalidation(KeyEvent event) {
        if(username.getText().isEmpty()){
            usrdone.setImage(null);
            usrvalid.setText("");

        }
        else if(Arrays.asList(log_in_page_Controller.d).contains(username.getText()))
        {
            usrdone.setImage(null);
            usrvalid.setText("Username already exists");
            usrvalid.setTextFill(Paint.valueOf("crimson"));
        }
        else{
            usrvalid.setText("");
            Image img=new Image("sample/tick1.png");
            usrdone.setImage(img);
        }

    }

    @FXML
    void cpassvalidation(KeyEvent event) {
        if(cpass.getText().isEmpty())
        {
            cpassvalid.setText("");
            cpassdone.setImage(null);
        }
        else if(pass.getText().equals(cpass.getText()))
        {
            cpassvalid.setText("");
            Image img=new Image("sample/tick1.png");
            cpassdone.setImage(img);
        }
        else {
            cpassdone.setImage(null);
            cpassvalid.setText("Not Matched");
            cpassvalid.setTextFill(Paint.valueOf("crimson"));
        }

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
        if(isAgreed.isSelected()) {
            Object o = new JSONParser().parse(new FileReader("userinfo.json"));
            JSONObject obj = (JSONObject) o;
            JSONArray j = new JSONArray();
            j.add(0, fname.getText());
            j.add(1, lname.getText());
            j.add(2, date.getValue());

            j.add(3, pass.getText());
            j.add(4, email.getText());
            if(fl==null){
                j.add(5,null);

            }
            else
            {j.add(5, fl.toString());}
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
