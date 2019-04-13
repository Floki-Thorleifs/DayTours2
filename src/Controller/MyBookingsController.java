package Controller;

import Model.Booking;
import Model.DayTours;
import Model.Trip;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyBookingsController {

    public TableView<ObservableList<String>> resultList =  new TableView<>();
    public Label buyerBooked;
    public Label locationBooked;
    public Label tripBooked;
    public Label dateBooked;
    public Label seatsBooked;
    public TextField numberInput;
    public Button aboutButton;
    public Label totalPrice;
    private DayTours dayTours;
    public TableColumn<ObservableList<String>, String> column;
    private Trip trip;


    @FXML
    public void initialize(){
        dayTours = new DayTours();
        aboutButton.setDisable(true);
        /*List<String> columnNames = Arrays.asList("Booking number","Trip name","Buyer","Date","Seats", "id");
        for(int i = 0; i < columnNames.size(); i++) {
            final int finalIdx = i;
            column = new TableColumn<>(columnNames.get(i));
            column.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(finalIdx)));
            if (i == 1) {
                column.setMinWidth(200);
            } else if(i == columnNames.size() -1 ){
                column.setMaxWidth(0);
            }else {
                column.setMinWidth(539/4);
            }
            resultList.getColumns().add(column);
        }
        ArrayList<Trip> books = dayTours.getBookings();
        resultList.getItems().remove(0, resultList.getItems().size());
        for(int i = 0; i < books.size(); i++){
            for(int j = 0; j < books.get(i).getBookings().size(); j++){
                ArrayList<Booking> matches = books.get(i).getBookings();
                Trip temp2 = dayTours.getTripById(Integer.toString(matches.get(j).getID()));
                System.out.println(temp2.getName());
                ObservableList<String> temp = FXCollections.observableArrayList();
                temp.add(Integer.toString(matches.get(j).getrealID()));
                temp.add(temp2.getName());
                temp.add(matches.get(j).getFullName());
                temp.add(matches.get(j).getDate());
                temp.add(Integer.toString(matches.get(j).getSeats()));
                temp.add(Integer.toString(matches.get(j).getID()));
                resultList.getItems().add(temp);
            }
        }*/

    }




    public void onClick(ActionEvent actionEvent) throws IOException {

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getClassLoader().getResource("./View/TripView.fxml"));
                Parent tableViewParent = loader.load();

                Scene tableViewScene = new Scene(tableViewParent);

                TripController controller = loader.getController();
                controller.initData(trip);

                Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

                window.setScene(tableViewScene);
                window.show();
        }

    public void backHandler(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("./View/SearchTripView.fxml"));

        Parent tableViewParent;
        try {
            tableViewParent = loader.load();
        } catch (IOException ex) {
            System.out.println("Load Failed.");
            tableViewParent = null;
        }

        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    public void getInfo(ActionEvent actionEvent) {
        Pattern p = Pattern.compile("[a-z]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(numberInput.getText());

        if(!m.find()){
            Booking book = dayTours.getBookingById(Integer.parseInt(numberInput.getText()));
            System.out.println(book == null);
            if(book == null){
                numberInput.clear();
                numberInput.setPromptText("No booking exists with that booking number");
            } else {
                trip = dayTours.getTripById(Integer.toString(book.getID()));
                locationBooked.setText(trip.getLocation());
                tripBooked.setText(trip.getName());
                seatsBooked.setText(Integer.toString(book.getSeats()));
                dateBooked.setText(book.getDate());
                buyerBooked.setText(book.getFullName());
                totalPrice.setText(trip.getPrice() * book.getSeats() + "kr.");
                aboutButton.setDisable(false);

            }
        } else if (numberInput.getText().length() != 0) {
            numberInput.clear();
            numberInput.setPromptText("Booking number can not be empty");
        }




    }
}
