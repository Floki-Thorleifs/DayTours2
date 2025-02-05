package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public int trip_id;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View/SearchTripView.fxml"));
        primaryStage.setTitle("Day Tours");
        primaryStage.setScene(new Scene(root, 801, 830));
        primaryStage.setMaxWidth(801);
        primaryStage.setMaxHeight(830);
        primaryStage.setMinWidth(801);
        primaryStage.setMinHeight(830);
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
