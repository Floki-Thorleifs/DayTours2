package Controller;

import Model.Booking;
import Model.Trip;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BookingController {


    @FXML
    private TextField CustomerFirstname;
    @FXML
    private TextField CustomerNumber;

    public TextField CustomerEmail;

    public TextField CustomerLastname;

    @FXML
    private TextField CustomerSeats;

    private Trip selectedTrip;

    private Booking booking;


    public void initData(Trip trip) {
        System.out.println(trip.getName());
        selectedTrip = trip;
    }


    public void bookHandler(ActionEvent actionEvent) {
        Boolean booked = addBooking();

        if (booked) {

            int id = selectedTrip.getId();
            String firstName = CustomerFirstname.getText();
            String lastName = CustomerLastname.getText();
            String email = CustomerEmail.getText();
            String phonenumber = CustomerNumber.getText();
            int seats = Integer.parseInt(CustomerSeats.getText());
            String date = selectedTrip.getDate();

            booking = new Booking(id, firstName, lastName, email, phonenumber, seats, date);

            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getClassLoader().getResource("./View/Thanks.fxml"));
                Parent tableViewParent = loader.load();

                Scene tableViewScene = new Scene(tableViewParent);

                Thanks thankyou = loader.getController();

                thankyou.initData(selectedTrip, booking);

                Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

                window.setScene(tableViewScene);
                window.show();
            } catch (IOException ex) {
                System.out.println("Json villa");
            }

        } else {
            System.out.println("TODO gera villusidu");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("./View/Errors.fxml"));

            Parent tableViewParent;
            try {
                tableViewParent = loader.load();
            } catch (IOException ex) {
                System.out.println("Load Failed.");
                tableViewParent = null;
            }

            Scene tableViewScene = new Scene(tableViewParent);

            Thanks thanks = loader.getController();
            thanks.errorData(selectedTrip);

            Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();

        }
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
            newJsonObject.put("date", selectedTrip.getDate());

            jsonArray.add(newJsonObject);

            FileWriter file = new FileWriter("src/JSON/bookings.json");

            file.write(jsonArray.toJSONString());
            file.flush();
            file.close();

        } catch (Exception e) {
            System.out.println("Failed.");
            return false;
        }
        return true;
    }

    public void backHandler(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("./View/TripView.fxml"));

        Parent tableViewParent;
        try {
            tableViewParent = loader.load();
        } catch (IOException ex) {
            System.out.println("Load Failed.");
            tableViewParent = null;
        }

        Scene tableViewScene = new Scene(tableViewParent);

        TripController controller = loader.getController();
        controller.initData(selectedTrip);


        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

    }
}



