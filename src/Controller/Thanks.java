package Controller;

import Model.Booking;
import Model.Trip;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class Thanks {

    public Label seatsBooked;
    public Label dateBooked;
    public Label tripBooked;
    public Label locationBooked;
    public Label buyerBooked;
    public Trip bookedTrip;
    public boolean isBooked = false;
    public Label bookingNumber;

    public void initData(Trip trip, Booking booking){

        seatsBooked.setText(Integer.toString(booking.getSeats()));
        dateBooked.setText(trip.getDate());
        tripBooked.setText(trip.getName());
        locationBooked.setText(trip.getLocation());
        buyerBooked.setText(booking.getFullName());
        bookedTrip = trip;
        isBooked = true;
        bookingNumber.setText(Integer.toString(booking.getrealID()));
    }

    public void errorData(Trip trip){
        bookedTrip = trip;
    }


    public void backHandler(ActionEvent actionEvent) {

        if(!isBooked) {
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
            controller.initData(bookedTrip);

            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();
        } else {
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
}
