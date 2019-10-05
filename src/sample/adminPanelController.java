package sample;

import com.sun.xml.internal.bind.v2.TODO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.SwipeEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.sun.javafx.scene.control.skin.Utils.getResource;

public class adminPanelController implements Initializable {

    @FXML
    private AnchorPane adminMainContent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Parent pane = null;
        try {
            pane = (Parent) FXMLLoader.load(getClass().getResource("addFlight.fxml"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        adminMainContent.getChildren().add(pane);
    }


    @FXML
    void addFlightButtonAction(ActionEvent event) throws IOException {
        Parent pane = (Parent) FXMLLoader.load(getClass().getResource("addFlight.fxml"));
        adminMainContent.getChildren().add(pane);
    }

    @FXML
    void deleteButtonAction(ActionEvent event) throws IOException {
        Parent pane = (Parent) FXMLLoader.load(getClass().getResource("Delete.fxml"));
        adminMainContent.getChildren().add(pane);
    }

    @FXML
    void updateButtonAction(ActionEvent event) throws IOException {
        Parent pane = (Parent) FXMLLoader.load(getClass().getResource("update.fxml"));
        adminMainContent.getChildren().add(pane);
    }

    @FXML
    void userListButtonAction(ActionEvent event) throws IOException {
        Parent pane = (Parent) FXMLLoader.load(getClass().getResource("UserList.fxml"));
        adminMainContent.getChildren().add(pane);
    }

}
