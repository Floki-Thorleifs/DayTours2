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
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.setMaxWidth(800);
        primaryStage.setMaxHeight(900);
        primaryStage.show();
    }

    public void setTrip_id(int id){
        this.trip_id = id;
    }

    public int getTrip_id(){
        return this.trip_id;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
