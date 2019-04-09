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

import java.awt.print.Book;
import java.io.IOException;

public class TripController {

    public Label TourId;
    public Label DateId;
    public Label InterestsId;
    public Label PriceId;
    public Label GuideId;
//    public TextArea DescriptionId;
    public Label LocationId;
    public Label SeatsId;
    public Label DescriptionId;
    public int tripID;


    @FXML
    public void initialize() throws IOException {


    }

    public void initData(Trip trip){
        TourId.setText(trip.getName());
        DateId.setText(trip.getDate());
        InterestsId.setText(trip.getInterests());
        PriceId.setText((trip.getPrice()) + " kr");
        GuideId.setText(trip.getTourGuide());
        LocationId.setText(trip.getLocation());
        SeatsId.setText(Integer.toString(trip.getSeatCount()));
        DescriptionId.setText(trip.getDescription());

        tripID = trip.getId();
    }

    public void bookHandler(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("./View/BookTripView.fxml"));

        Parent tableViewParent;
        try {
            tableViewParent = loader.load();
        } catch (IOException ex) {
            System.out.println("Load Failed.");
            tableViewParent = null;
        }

        Scene tableViewScene = new Scene(tableViewParent);

        BookingController controller = loader.getController();
        controller.initData(tripID);

        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();


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
