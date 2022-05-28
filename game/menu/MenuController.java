package menu;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import menu.scores.Net;

public class MenuController {
    @FXML
    Button startButton;
    @FXML
    Button scoresButton;

    @FXML
    TextField playerName;

    @FXML
    AnchorPane mainRoot;

    @FXML
    public void letsGo(ActionEvent actionEvent) {
        FXMLLoad("../gameplay/GamePlayView.fxml");
    }

    @FXML
    public void seeScores(ActionEvent actionEvent) {
        FXMLLoad("scores/ScoresView.fxml");
    }

    private void FXMLLoad(String path) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path));
        AnchorPane pane;
        try {
            pane = fxmlLoader.load();
            mainRoot.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
