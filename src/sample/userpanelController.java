package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class userpanelController implements Initializable {

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

    @FXML
    void bookflight(ActionEvent event) {

    }

    @FXML
    void logout(ActionEvent event) {

    }

    @FXML
    void updatepic(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name.setText((String) log_in_page_Controller.j.get(0)+" "+(String)log_in_page_Controller.j.get(1));
        usrname.setText((log_in_page_Controller.username));
        mail.setText((String) log_in_page_Controller.j.get(6));
        img.setImage(log_in_page_Controller.emg);

    }
}
