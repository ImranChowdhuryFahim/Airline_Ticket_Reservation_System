package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.awt.*;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

public class live_flight_update implements Initializable {
    int f=0;
    public int h,m,s;

    ObservableList<String> flt= FXCollections.observableArrayList("Boeing 787-9","Airbus A320-200","Airbus A321","AAL1","Biman_Bangladesh","Thai_air","Boeing 777-300ER");
    @FXML
    private javafx.scene.control.ListView<String> ListView;

    @FXML
    private Label flight_no;

    @FXML
    private Label flight;

    @FXML
    private Label seat;

    @FXML
    private Label flight_time;

    @FXML
    private Label current_time;

    int sec,min,hour;

    public void clock()
    {
        Thread clock=new Thread()
        {

            public  void run()
            {
                for(;;)
                {

                    try {
                        Calendar cal=new GregorianCalendar();
                        sec=cal.get(Calendar.SECOND);
                        min=cal.get(Calendar.MINUTE);
                        hour=cal.get(Calendar.HOUR);
                        Platform.runLater(() -> {
                            current_time.setText(String.valueOf(hour)+":" +String.valueOf(min)+":"+String.valueOf(sec));

                        });
                        if(sec==s && min==m&& hour==h)
                        {
                            f=1;
                            Platform.runLater(()->{
                                Toolkit.getDefaultToolkit().beep();
                                Alert Logout=new Alert(Alert.AlertType.WARNING);
                                Logout.setContentText("Your Boarding Time has arrived");
                                Logout.setHeaderText("ON Boarding");
                                Logout.show();

                            });

                        }
                        else if(sec%10==0 && f==0)
                        {
                            Platform.runLater(()->{
                                ListView.getItems().remove(0);

                            });
                        }
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        };
        clock.start();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Calendar t= new GregorianCalendar();
        h=t.get(Calendar.HOUR);
        m=t.get(Calendar.MINUTE)+1;
        s=t.get(Calendar.SECOND);
        flight_no.setText(SearchResult.t);
        flight.setText(SearchResult.r);
        seat.setText(seatViewController.seat.toString().replace('[',' ').replace(']',' '));
        flight_time.setText(String.valueOf(h)+":"+String.valueOf(m)+":"+String.valueOf(s));
        ListView.setItems(flt);
        clock();

    }
}
