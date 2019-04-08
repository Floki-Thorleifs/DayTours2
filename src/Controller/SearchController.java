package Controller;

import Model.DayTours;
import Model.Trip;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.text.ParseException;
import java.time.LocalDate;

public class SearchController {

    public ListView resultList;
    private DayTours dayTours = new DayTours();

    public ChoiceBox PriceChoiceBox;
    public ChoiceBox SeatChoiceBox;
    public DatePicker startDate;
    public DatePicker endDate;
    public ChoiceBox InterestsChoiceBox;
    public String nameInput;
    public ChoiceBox LocationChoiceBox;


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
        if(nameInput != null){
            dayTours.searchName(nameInput);
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
    }
}
