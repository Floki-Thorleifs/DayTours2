package Controller;

import Model.DayTours;
import Model.Trip;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Iterator;

public class SearchController {

    private DayTours dayTours;

    public ListView resultList;
    public ChoiceBox PriceChoiceBox;
    public ChoiceBox SeatChoiceBox;
    public DatePicker startDate;
    public DatePicker endDate;
    public ChoiceBox InterestsChoiceBox;
    public TextField nameInput;
    public ChoiceBox LocationChoiceBox;

    @FXML
    public void initialize(){
        dayTours = new DayTours();
        JSONParser parser = new JSONParser();

        try {
            System.out.println("Byrja");
//            Object obj = parser.parse(new FileReader("../JSON/gogn.json"));
            Object obj = parser.parse(new FileReader("/Users/egill/Dropbox/University/Þroun/DayTours2/src/JSON/gogn.json"));
            JSONArray jsonArray = (JSONArray) obj;

            JSONObject gamli = (JSONObject) jsonArray.get(2);
            String name = (String) gamli.get("Name");
//            System.out.println(name);

            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject feitt = (JSONObject) jsonArray.get(i);
                String nname = (String) feitt.get("Name");
                System.out.println(nname);
            }

            System.out.println("gekk upp");

        } catch (Exception e) {
            System.out.println("failed");
            e.printStackTrace();
        }
        ObservableList<Trip> results = FXCollections.observableArrayList(dayTours.getTrips());
        resultList.setItems(results);
    }


    public void searchHandler() throws ParseException {
        dayTours = new DayTours();
        LocalDate inputStartDate = startDate.getValue();
        LocalDate inputEndDate = endDate.getValue();

        if(LocationChoiceBox.getValue() != null){
            String location = LocationChoiceBox.getValue().toString();
            dayTours.searchLocations(location);
        }
        if(inputStartDate != null){
            dayTours.searchDates(inputStartDate, inputEndDate);
        }
        if(nameInput.getText() != null){
            dayTours.searchName(nameInput.getText());
        }
        if(PriceChoiceBox.getValue() != null){
            switch (PriceChoiceBox.getValue().toString()){
                case "0 - 5000 kr":
                    dayTours.searchPrice(0, 5000);
                    break;
                case "5000 - 10000 kr":
                    dayTours.searchPrice(5000, 10000);
                    break;
                case "10000 - 15000 kr":
                    dayTours.searchPrice(10000, 15000);
                    break;
                default:
                    break;
            }

        }

        if(InterestsChoiceBox.getValue() != null){
            System.out.println(InterestsChoiceBox.getValue());
            dayTours.searchInterests(InterestsChoiceBox.getValue().toString());
        }

        if(SeatChoiceBox.getValue() != null) {
            dayTours.searchSeats(Integer.parseInt(SeatChoiceBox.getValue().toString()));
        }

        ObservableList<Trip> results = FXCollections.observableArrayList(dayTours.getTrips());
        resultList.setItems(results);

        


//        LocalDate inputStartDate = startDate.getValue();
//        LocalDate inputEndDate = endDate.getValue();
//        dayTours.searchDates(inputStartDate, inputEndDate);
    }
}
