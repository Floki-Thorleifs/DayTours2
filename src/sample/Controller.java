package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;

public class Controller {
    public Button searchButton;
    public TextField searchText;
    public ListView searchResults;

    public void searchHandler(ActionEvent actionEvent) {
        System.out.println(searchText.getText());
    }

    public void changeSearch(InputMethodEvent inputMethodEvent) {

    }
}
