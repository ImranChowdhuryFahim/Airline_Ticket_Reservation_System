package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class userpanelController implements Initializable {

    @FXML
    private ImageView img;

    @FXML
    private Label name;

    @FXML
    private Label username;

    @FXML
    private Label email;

    @FXML
    void button(ActionEvent event) throws FileNotFoundException {
       name.setText((String) log_in_page_Controller.j.get(0));
//        File fl=new File((String) log_in_page_Controller.j.get(7));
//        Image emg= null;
//        try {
//            emg=new Image(new FileInputStream(fl));
//        }
//        catch (FileNotFoundException e)
//        {
//
//        }
//        img.setImage(emg);


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name.setText((String) log_in_page_Controller.j.get(0));
        email.setText((String) log_in_page_Controller.j.get(6));
        username.setText(log_in_page_Controller.username);
        img.setImage(log_in_page_Controller.emg);

    }
}
