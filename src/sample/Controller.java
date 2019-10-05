//package sample;
//
//import javafx.application.Platform;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.ChoiceBox;
//import javafx.scene.control.Label;
//
//import java.net.URL;
//import java.util.Calendar;
//import java.util.GregorianCalendar;
//import java.util.ResourceBundle;
//
//public class Controller implements Initializable {
//
//    @FXML
//    private ChoiceBox<String> drpdown;
//
//    @FXML
//    private Label time;
//
//    @FXML
//    private Label date;
//
//    public void clock()
//    {
//        Thread clock=new Thread()
//        {
//
//               public  void run()
//               {
//                   for(;;)
//                   {
//
//                       try {
//                           Calendar cal=new GregorianCalendar();
//                           int sec=cal.get(Calendar.SECOND);
//                           Platform.runLater(() -> {
//                               time.setText(String.valueOf(sec));
//                           });
//                           sleep(1000);
//                       } catch (InterruptedException e) {
//                           e.printStackTrace();
//                       }
//                   }
//
//               }
//        };
//        clock.start();
//    }
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        clock();
//
//    }
//}
package sample;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller implements Initializable {

    @FXML
    private AnchorPane load;
    AnchorPane a,b;

    @FXML
    void adflight(ActionEvent event) {
        setNode(b);
    }

    @FXML
    void delete(ActionEvent event) {

    }

    @FXML
    void update(ActionEvent event) {

    }

    @FXML
    void userlist(ActionEvent event) {

    }

    private void setNode(Node node) {
        load.getChildren().clear();
        load.getChildren().add((Node) node);

//        FadeTransition ft = new FadeTransition(Duration.millis(1500));
//        ft.setNode(node);
//        ft.setFromValue(0.1);
//        ft.setToValue(1);
//        ft.setCycleCount(1);
//        ft.setAutoReverse(false);
//        ft.play();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            a = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
            b = FXMLLoader.load(getClass().getResource("log_in_page.fxml"));

            setNode(a);
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
