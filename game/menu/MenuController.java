package menu;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class MenuController {
    @FXML
    Button startButton;

    @FXML
    TextField playerName;

    @FXML
    AnchorPane mainRoot;
    
    @FXML
    public void letsGo(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../gameplay/GamePlayView.fxml"));
        AnchorPane pane = fxmlLoader.load();
        mainRoot.getChildren().setAll(pane);
    }

}
