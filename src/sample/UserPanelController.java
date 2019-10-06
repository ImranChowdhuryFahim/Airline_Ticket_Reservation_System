package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserPanelController implements Initializable {

    @FXML
    private AnchorPane container;

    AnchorPane pay,updateprofile,flightsearch,userinfo;

    @FXML
    void bookflight(ActionEvent event) throws IOException {
        flightsearch = FXMLLoader.load(getClass().getResource("user_flight_search.fxml"));
        setNode(flightsearch);

    }

    @FXML
    void crnstatus(ActionEvent event) throws IOException {
        userinfo = FXMLLoader.load(getClass().getResource("userInfo.fxml"));
        setNode(userinfo);

    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        Parent n= FXMLLoader.load(getClass().getResource("log_in_page.fxml"));
        Scene n1=new Scene(n);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Login");
        window.setScene(n1);
        window.show();


    }

    @FXML
    void pay(ActionEvent event) throws IOException {
        pay = FXMLLoader.load(getClass().getResource("payment.fxml"));
        setNode(pay);

    }

    @FXML
    void updateprofile(ActionEvent event) throws IOException {
        updateprofile = FXMLLoader.load(getClass().getResource("update_user_info.fxml"));
        setNode(updateprofile);

    }

    private void setNode(Node node) {
        container.getChildren().clear();
        container.getChildren().add((Node) node);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            pay = FXMLLoader.load(getClass().getResource("payment.fxml"));
            updateprofile = FXMLLoader.load(getClass().getResource("update_user_info.fxml"));
            flightsearch = FXMLLoader.load(getClass().getResource("user_flight_search.fxml"));
            userinfo = FXMLLoader.load(getClass().getResource("userInfo.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(UserPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }

        setNode(userinfo);

    }
}


