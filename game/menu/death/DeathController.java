package menu.death;

import java.io.IOException;

import entity.entity.player.Player;
import gameplay.GamePlayController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import menu.scores.NetDB;

public class DeathController {
    @FXML
    Button seeScoresButton;
    @FXML
    Button backToMenuButton;

    @FXML
    AnchorPane mainRoot;

    @FXML
    Label score;

    @FXML
    Label titleLabel;

    @FXML
    Label yourScoreLabel;

    NetDB netDB;

    String ip;

    public void setIP(String ip) {
        this.ip = ip;
    }

    @FXML
    public void initialize() {

    }

    public void initSingle() {
        score.setText(GamePlayController.getScore());
        Platform.runLater(new Runnable() {
            public void run() {
                netDB = new NetDB(ip);
                netDB.sendToServer(Player.getName(), GamePlayController.getScore());
            }
        });
    }

    public void initMulti() {

        titleLabel.setText("YOU WON!");
        score.setVisible(false);
        score.setDisable(true);
        yourScoreLabel.setVisible(false);
        yourScoreLabel.setDisable(true);
        seeScoresButton.setVisible(false);
        seeScoresButton.setDisable(true);

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
