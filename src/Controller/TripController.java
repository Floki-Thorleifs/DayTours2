package Controller;

import Model.Trip;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class TripController {

    public Label TourId;
    public Label DateId;
    public Label InterestsId;
    public Label PriceId;
    public Label GuideId;
    public TextArea DescriptionId;

    @FXML
    public void initialize() throws IOException {


    }

    public void initData(Trip trip){
        TourId.setText(trip.getName());
        DateId.setText(trip.getDate());
        InterestsId.setText(trip.getInterests());
        PriceId.setText(Integer.toString(trip.getPrice()));
        GuideId.setText(trip.getTourGuide());
        DescriptionId.setText(trip.getDescription());
    }

    public void bookHandler(ActionEvent actionEvent) {
    }

    public void backHandler(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("./View/SearchTripView.fxml"));

        Parent tableViewParent;
        try {
            tableViewParent = loader.load();
        } catch (IOException ex) {
            System.out.println("Load Failed.");
            tableViewParent = null;
        }

        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }
}
