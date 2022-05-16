package gameplay;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class GamePlayController {
    @FXML
    private ImageView up;
    @FXML
    private ImageView down;
    @FXML
    private ImageView left;
    @FXML
    private ImageView right;

    private int pacman_x, pacman_y, pacmanD_x, pacmanD_y;

    public void initialize() throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(loadFXML());
        Image i = new Image(new File("../../up.gif").toURI().toString());
        up.setImage(i);

    }

    private static Parent loadFXML() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GamePlayController.class.getResource("gameplay/GamePlayView.fxml"));
        return fxmlLoader.load();
    }
}
