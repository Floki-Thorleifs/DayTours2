package Controller;

import Model.DayTours;
import Model.Trip;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchController {

    public TableView<ObservableList<String>> resultList = new TableView<>();
    public AnchorPane rootPane;
    public Button bookingButton;
    private DayTours dayTours;

    public ChoiceBox PriceChoiceBox;
    public TextField SeatChoiceBox;
    public DatePicker startDate;
    public DatePicker endDate;
    public ChoiceBox InterestsChoiceBox;
    public TextField nameInput;
    public ChoiceBox LocationChoiceBox;

    private boolean anyBookings = false;

    public TableColumn<ObservableList<String>, String> column;


    @FXML
    public void initialize(){
        dayTours = new DayTours();
        ArrayList<ArrayList<String>> info = dayTours.getInfo();
        ObservableList<String> interests = FXCollections.observableArrayList(info.get(0));
        InterestsChoiceBox.setItems(interests);
        ObservableList<String> places = FXCollections.observableArrayList(info.get(1));
        LocationChoiceBox.setItems(places);


        List<String> columnNames = Arrays.asList("Name","Available Seats","Duration","Date","Price"
        ,"ID");
        for(int i = 0; i < columnNames.size(); i++) {
            final int finalIdx = i;
            column = new TableColumn<>(columnNames.get(i));
            column.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(finalIdx)));
            if(i == columnNames.size() - 1){
                column.setMaxWidth(0);
            } else if (i == 0) {
                column.setMinWidth(200);
            } else {
                column.setMinWidth((739-200)/4);
            }
            resultList.getColumns().add(column);
        }

        getTrips();

    }

    private void getTrips(){
        resultList.getItems().remove(0, resultList.getItems().size());
        ObservableList<Trip> results = FXCollections.observableArrayList(dayTours.getTrips());
        for(int j = 0; j < results.size(); j++){
            String name = results.get(j).getName();
            String price = Integer.toString(results.get(j).getPrice());
            String date = results.get(j).getDate();
            String seats = Integer.toString(results.get(j).getSeatCount());
            String duration = results.get(j).getDuration();
            String id = Integer.toString(results.get(j).getId());
            ObservableList<String> plep = FXCollections.observableArrayList();
            plep.add(name);
            plep.add(seats);
            plep.add(duration);
            plep.add(date);
            plep.add(price);
            plep.add(id);
            resultList.getItems().add(plep);
        }
    }




    public void searchHandler() throws ParseException {
        dayTours.freshStart();
        LocalDate inputStartDate = startDate.getValue();
        LocalDate inputEndDate = endDate.getValue();

        if(LocationChoiceBox.getValue() != null){
            String location = LocationChoiceBox.getValue().toString();
            dayTours.searchLocations(location);
        }

        if(inputEndDate != null && inputStartDate != null){
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
            dayTours.searchInterests(InterestsChoiceBox.getValue().toString());
        }
        if(SeatChoiceBox.getText().length() != 0){
            dayTours.searchSeats(Integer.parseInt(SeatChoiceBox.getText()));
        }
        getTrips();
    }


    @FXML
    public void onClick(MouseEvent mouseEvent) throws IOException {
        if(mouseEvent.getClickCount() == 2){

            String id = resultList.getSelectionModel().getSelectedItem().get(5);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("./View/TripView.fxml"));
            Parent tableViewParent = loader.load();

            Scene tableViewScene = new Scene(tableViewParent);

            TripController controller = loader.getController();
            Trip trip = dayTours.getTripById(id);
            controller.initData(trip);

            Stage window = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();

        }
    }

    public void bookingsHandler(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("./View/MyBookings.fxml"));
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }
}
