package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import org.controlsfx.control.textfield.TextFields;
import org.json.simple.JSONObject;
import javafx.scene.image.ImageView;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    @FXML
    private TextField user;

    @FXML
    private ImageView img;

    @FXML
    void login(ActionEvent event) throws FileNotFoundException {

        FileChooser fc=new FileChooser();
        Image emg = null;
        File file=fc.showOpenDialog(null);

        try {
            emg = new Image(new FileInputStream(file));
        }
        catch (FileNotFoundException e)
        {

        }
        img.setImage(emg);

//       System.out.println(selected);



    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TextFields.bindAutoCompletion(user,Main.jo.keySet());

    }
}
