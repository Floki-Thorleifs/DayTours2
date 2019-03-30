package Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;

import java.util.Date;

public class Controller {
    public Button searchButton;
    public TextField searchText;
    public ListView searchResults;
    public Date date = new Date();

    public void searchHandler(ActionEvent actionEvent) {
        System.out.println(date.toString());
    }

    public void changeSearch(InputMethodEvent inputMethodEvent) {

    }
}
