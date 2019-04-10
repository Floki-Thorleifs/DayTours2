package Controller;

import Model.Booking;
import Model.Trip;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class BookingController {


        @FXML
        private TextField CustomerFirstname;

        @FXML
        private TextField CustomerNumber;

        @FXML
        private TextField CustomerEmail;

        @FXML
        private TextField CustomerLastname;

        @FXML
        private TextField CustomerSeats;

        private Trip selectedTrip;



        public Booking booking;


        public void initData(Trip trip) {
                selectedTrip = trip;
        }


        public void bookHandler(ActionEvent actionEvent) {
                
        }

        public void backHandler(ActionEvent actionEvent) {
        }
}


