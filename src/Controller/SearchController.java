package Controller;

import Model.DayTours;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Iterator;

public class SearchController {

    public DayTours dayTours = new DayTours();
    public ChoiceBox PriceChoiceBox;
    public ChoiceBox SeatChoiceBox;
    public DatePicker startDate;
    public DatePicker endDate;
    public ChoiceBox InterestsChoiceBox;
    public String nameInput;


    public void searchHandler(ActionEvent actionEvent) throws ParseException {

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


//        LocalDate inputStartDate = startDate.getValue();
//        LocalDate inputEndDate = endDate.getValue();
//        dayTours.searchDates(inputStartDate, inputEndDate);
    }
}
