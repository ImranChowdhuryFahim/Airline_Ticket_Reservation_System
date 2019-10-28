package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.awt.*;

public class seatViewController {

    @FXML
    private ImageView F12;

    @FXML
    private ImageView F15;

    @FXML
    private ImageView F11;

    @FXML
    private TextField F13;

    @FXML
    private ImageView F21;

    @FXML
    void F11(MouseEvent event) {
        Image img=new Image("sample/icons8-theatre-seat-80_black.png");
        F11.setImage(img);

    }

    @FXML
    void F12(MouseEvent event) {

    }

    @FXML
    void F13(MouseEvent event) {

    }

    @FXML
    void F14(MouseEvent event) {

    }


    @FXML
    void F21(MouseEvent event) {
        Toolkit.getDefaultToolkit().beep();
        Alert Logout=new Alert(Alert.AlertType.WARNING);
        Logout.setContentText("Seat Not Available");
        Logout.setHeaderText("Seat Info");
        Logout.show();

    }

    @FXML
    void F23(MouseEvent event) {

    }

    @FXML
    void F24(MouseEvent event) {

    }

}
