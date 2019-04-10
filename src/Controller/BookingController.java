package Controller;

import Model.Booking;
import Model.Trip;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;

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

//                DayTours trips = new DayTours();
//                Trip trip = trips.getTripById(Integer.toString(tripID));





        private boolean addBooking() {
                JSONParser parser = new JSONParser();

                try {
                        Object obj = parser.parse(new FileReader("src/JSON/bookings.json"));
                        JSONArray jsonArray = (JSONArray) obj;

                        JSONObject newJsonObject = new JSONObject();

                        newJsonObject.put("tripID", selectedTrip.getId());
                        newJsonObject.put("firstName", CustomerFirstname.getText());
                        newJsonObject.put("lastName", CustomerLastname.getText());
                        newJsonObject.put("email", CustomerEmail.getText());
                        newJsonObject.put("phonenumber", CustomerNumber.getText());
                        newJsonObject.put("seats", Integer.parseInt(CustomerSeats.getText()));

                        jsonArray.add(newJsonObject);

                        FileWriter file = new FileWriter("src/JSON/bookings.json");

                        file.write(jsonArray.toJSONString());
                        file.flush();
                        file.close();

                } catch (Exception e) {
                        System.out.println("Failed.");
                }
                return true;
        }

        public void backHandler(ActionEvent actionEvent) {

        }
}



