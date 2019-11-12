package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import java.io.FileReader;


public class Main extends Application {
    public static JSONObject jo;



    @Override
    public void start(Stage primaryStage) throws Exception{
        Object obj = new JSONParser().parse(new FileReader("userinfo.json"));
        jo = (JSONObject) obj;
        Parent root = FXMLLoader.load(getClass().getResource("Loading.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
