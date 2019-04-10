package Controller;

import Model.Booking;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class BookingController {

        @FXML
        private TextField CustomerFirstname;
        @FXML
        private TextField CustomerNumber;

        public TextField CustomerEmail;

        public TextField CustomerLastname;

        @FXML
        private Slider CustomerSeats;

        public Booking booking;

        @FXML
        public void initialize() {

            CustomerSeats.setShowTickLabels(true);
        }

    }


