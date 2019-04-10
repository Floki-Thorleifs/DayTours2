package Controller;

import Model.Booking;
import Model.DayTours;
import Model.Trip;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
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
        private Slider CustomerSeats;

        private int tripID;



        public Booking booking;


        public void initData(int ID) {
                tripID = ID;
                System.out.println(tripID);
        }


        public void bookHandler(ActionEvent actionEvent) {

                DayTours trips = new DayTours();
                Trip trip = trips.getTripById(Integer.toString(tripID));

        }
}


