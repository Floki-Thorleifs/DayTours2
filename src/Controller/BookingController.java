package Controller;

import Model.Booking;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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


}


