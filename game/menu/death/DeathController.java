package menu.death;

import java.io.IOException;

import entity.entity.player.Player;
import gameplay.GamePlayController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import menu.scores.Net;

public class DeathController {
    @FXML
    Button seeScoresButton;
    @FXML
    Button backToMenuButton;

    @FXML
    AnchorPane mainRoot;

    Net net;

    @FXML
    public void initialize() {
        net = new Net();
        net.sendToServer(Player.getName(), GamePlayController.getScore());
    }

    @FXML
    public void seeScores() {
        FXMLLoad("../scores/ScoresView.fxml");
    }

    @FXML
    public void backToMenu() {
        FXMLLoad("../MenuView.fxml");
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
