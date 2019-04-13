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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private double temp = Math.random()*2000000;

    private int newId = (int) temp;


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

            booking = new Booking(id, firstName, lastName, email, phonenumber, seats, date, newId);

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
            if(!validateString(CustomerFirstname.getText())){
                CustomerFirstname.clear();
                CustomerFirstname.setPromptText("First name can not be empty or include numbers or special characters");
            }
            if(!validateString(CustomerLastname.getText())){
                CustomerLastname.clear();
                CustomerLastname.setPromptText("Last name can not be empty or include numbers or special characters");
            }
            if(!validatePhone(CustomerNumber.getText())){
                CustomerNumber.clear();
                CustomerNumber.setPromptText("Phone number can not be empty or include letters or special characters");
            }
            if(!validateEmail(CustomerEmail.getText())){
                CustomerEmail.clear();
                CustomerEmail.setPromptText("Email must be a real email");
            }
            int a = validateSeats(CustomerSeats.getText());
            if(a == 1){
                CustomerSeats.clear();
                CustomerSeats.setPromptText("There are not enough seats left");
            } else if (a == 2){
                CustomerSeats.clear();
                CustomerSeats.setPromptText("Number of seats can not be empty or contain letters or special characters");
            }

        }
    }

//                DayTours trips = new DayTours();
//                Trip trip = trips.getTripById(Integer.toString(tripID));

    private boolean validateString(String string){
        Pattern p = Pattern.compile("[0-9]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(string);
        boolean b = m.find();
        if(string.length() == 0 || b){
            return false;
        }
        return true;
    }

    private boolean validateEmail(String string){
        Pattern p = Pattern.compile("[@]");
        Matcher m = p.matcher(string);
        boolean b = m.find();
        if(string.length() == 0 || !b){
            return false;
        }
        return true;
    }

    private boolean validatePhone(String string){
        Pattern p = Pattern.compile("[^a-z]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(string);
        boolean b = m.find();
        if(string.length() == 0 || !b){
            return false;
        }
        return true;
    }

    private int validateSeats(String string){
        if(!(string.length() == 0)){
            Pattern p = Pattern.compile("[^a-z]", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(string);
            boolean b = m.find();

            if(b){
                if(Integer.parseInt(string) < selectedTrip.getSeatCount()){
                    return 0;
                }
                return 1;
            }
            return 2;
        }
        return 2;

    }

    private boolean addBooking() {
        JSONParser parser = new JSONParser();

        if(
                validateString(CustomerFirstname.getText()) ||
                validateString(CustomerLastname.getText()) ||
                validateEmail(CustomerEmail.getText()) ||
                validatePhone(CustomerNumber.getText()) ||
                validateSeats(CustomerSeats.getText()) == 0
        ) {


            try {
                Object obj = parser.parse(new FileReader("src/JSON/bookings.json"));
                JSONArray jsonArray = (JSONArray) obj;

                JSONObject newJsonObject = new JSONObject();


                newJsonObject.put("id", newId);
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
        return false;
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



