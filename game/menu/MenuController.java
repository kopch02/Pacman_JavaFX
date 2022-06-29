package menu;

import java.io.IOException;

import entity.entity.player.Player;
import gameplay.GamePlayController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import menu.scores.ScoresController;

public class MenuController {
    @FXML
    Button startButton;
    @FXML
    Button scoresButton;

    @FXML
    AnchorPane mainRoot;

    @FXML
    TextField playerNameField;

    // multi
    @FXML
    TextField ipField;
    @FXML
    TextField ipFieldScores;

    @FXML
    Button okButton;
    @FXML
    Button startMultiButton;

    @FXML
    Label ipLabel;
    @FXML
    Label ipLabelScores;

    @FXML
    public void letsGo(ActionEvent actionEvent) {
        FXMLLoad("../gameplay/GamePlayView.fxml", 1);
        Player.setName(playerNameField.getText());

    }

    @FXML
    public void initilMulti(ActionEvent actionEvent) {
        ipLabel.setVisible(true);
        ipField.setVisible(true);
        okButton.setVisible(true);
    }

    @FXML
    public void letsGoMult(ActionEvent actionEvent) {
        FXMLLoad("../gameplay/GamePlayView.fxml", 2);
        Player.setName(playerNameField.getText());

    }

    @FXML
    public void seeScores(ActionEvent actionEvent) {
        ipLabelScores.setVisible(true);
        ipFieldScores.setVisible(true);
        if (scoresButton.getText().equals("Enter")) {
            FXMLLoad("scores/ScoresView.fxml", 3);
        } else {
            scoresButton.setText("Enter");
        }
    }

    private void FXMLLoad(String path, int actionCode) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path));
        AnchorPane pane;
        try {
            pane = fxmlLoader.load();
            mainRoot.getChildren().setAll(pane);
            if (actionCode == 1) {
                GamePlayController gamePlayController = (GamePlayController) fxmlLoader.getController();
                gamePlayController.initSingle();
                gamePlayController.setIp(ipField.getText());
            } else if (actionCode == 2) {
                GamePlayController gamePlayController = (GamePlayController) fxmlLoader.getController();
                gamePlayController.setIp(ipField.getText());
                gamePlayController.initMulti();
                System.out.println(ipField.getText());
            } else if (actionCode == 3) {
                ScoresController scoresController = (ScoresController) fxmlLoader.getController();
                scoresController.setIP(ipFieldScores.getText());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
