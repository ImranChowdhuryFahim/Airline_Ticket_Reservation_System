package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class update_user_info implements Initializable {
    public static File fl=null;
    public static String updatedname=null;
    @FXML
    private ImageView img;

    @FXML
    private TextField lname;

    @FXML
    private TextField fname;

    @FXML
    private  TextField username;

    @FXML
    private PasswordField npass;

    @FXML
    private PasswordField ncpass;

    @FXML
    void update(ActionEvent event) throws IOException, ParseException ,FileNotFoundException{
        Object o = new JSONParser().parse(new FileReader("userinfo.json"));
        JSONObject obj = (JSONObject) o;
        JSONArray j = (JSONArray) obj.get(log_in_page_Controller.username);
        JSONArray n=new JSONArray();
        String imgloc=new String();
        if(fl==null){
            imgloc= (String) j.get(7);
        }
        else {
            imgloc=fl.toString();
        }
        n.add(0,fname.getText());
        n.add(1,lname.getText());
        n.add(2,j.get(2));
        n.add(3,j.get(3));
        n.add(4,j.get(4));
        n.add(5,npass.getText());
        n.add(6,j.get(6));
        n.add(7,imgloc);
        n.add(8,j.get(8));
        if(username.getText().equals(log_in_page_Controller.username)){
            obj.put(log_in_page_Controller.username,n);
        }
        else {
            updatedname=username.getText();
            obj.remove(log_in_page_Controller.username);
            obj.put(username.getText(),n);
        }
        System.out.println(obj);


        try (FileWriter jfw = new FileWriter("userinfo.json")) {
            jfw.write(obj.toJSONString());
            jfw.flush();
            Toolkit.getDefaultToolkit().beep();
            Alert update=new Alert(Alert.AlertType.INFORMATION);
            update.setHeaderText("UPDATE");
            update.setContentText("Information has been updated successfully");
            update.show();
        } catch (IOException e) {

        }


    }

    @FXML
    void updatepic(ActionEvent event) {

        FileChooser fc=new FileChooser();
        Button button = new Button("Select File");
        fl=fc.showOpenDialog(null);
        Image emg=null;
        try {
            if(fl!=null){
            emg = new Image(new FileInputStream(fl));}
        }
        catch (FileNotFoundException e)
        {

        }
        if(emg!=null){
        img.setImage(emg);}

    }

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
        JSONArray j = null;
        if (updatedname == null) {
            j = (JSONArray) obj.get(log_in_page_Controller.username);
        } else {
            j = (JSONArray) obj.get(updatedname);
        }
        if (j != null) {
            fname.setText((String) j.get(0));
            lname.setText((String) j.get(1));
            if (updatedname == null) {
                username.setText(log_in_page_Controller.username);
            } else {
                username.setText(updatedname);
            }

            Image emg = null;
            try {
                if (j.get(7) != null) {
                    emg = new Image(new FileInputStream(j.get(7).toString()));
                }
            } catch (FileNotFoundException e) {

            }
            if (emg != null) {
                img.setImage(emg);
            }

        }
    }
}
