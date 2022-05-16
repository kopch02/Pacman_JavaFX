package menu;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MenuController {
    @FXML
    Button startButton;

    @FXML
    TextField playerName;
    
    @FXML
    public void letsGo(ActionEvent actionEvent) {
        try {
            loadFXML();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Parent loadFXML() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MenuController.class.getResource("../gameplay/GamePlayView.fxml"));
        return fxmlLoader.load();
    }

}
