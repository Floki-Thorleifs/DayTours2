package Controller;

import Model.DayTours;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;

import java.text.ParseException;
import java.time.LocalDate;

public class SearchController {

    public DayTours dayTours = new DayTours();
    public ChoiceBox PriceChoiceBox;
    public ChoiceBox SeatChoiceBox;
    public DatePicker startDate;
    public DatePicker endDate;
    public ChoiceBox InterestsChoiceBox;
    public String nameInput;

    public void searchHandler(ActionEvent actionEvent) throws ParseException {
        LocalDate inputStartDate = startDate.getValue();
        LocalDate inputEndDate = endDate.getValue();
        dayTours.searchDates(inputStartDate, inputEndDate);
    }
}
