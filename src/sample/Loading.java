package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Loading implements Initializable {

    @FXML
    private AnchorPane LoadingAP;

    private AnchorPane root, twt;


    @FXML
    private ProgressBar prgBar;

    public void load ()
    {

        Thread load = new Thread(() -> {
            for(int i = 1; i <= 25; i++)
            {


                try {
                    int finalI = i;
                    int finalI1 = i;
                    Platform.runLater(() -> {

                        if(finalI <= 10){
                            double pr = finalI / 10.0;
                            prgBar.setProgress(pr);
                        }

//                        System.out.println(finalI + "%");
                        else if(finalI1 >= 15){

                            LoadingAP.getChildren().clear();
                            LoadingAP.getChildren().setAll(root);

                        }

                        else if(finalI >= 10){
                            LoadingAP.getChildren().clear();
                            LoadingAP.getChildren().setAll(twt);
                        }
//                        else if(finalI == 150){
//                            parAP.getChildren().clear();
//                            parAP.getChildren().setAll(twoS);
//                        }

                    });

                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
        load.start();
        return;

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            twt = FXMLLoader.load(getClass().getResource("tagwithtitle.fxml"));
            root = FXMLLoader.load(getClass().getResource("log_in_page.fxml"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        load();

    }
}
