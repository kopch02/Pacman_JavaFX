package gameplay;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;


public class GamePlayController {
    @FXML
    private ImageView pacman;

    @FXML
    private AnchorPane mainRoot;
    

    private int pacman_x = 8, pacman_y = 8;
    public Image upImage, downImage, leftImage, rightImage;

    public void initialize() throws IOException {
        upImage = new Image(new File("/../../other/up.gif").toURI().toString());
        downImage = new Image(new File("/../../other/down.gif").toURI().toString());
        leftImage = new Image(new File("/../../other/left.gif").toURI().toString());
        rightImage = new Image(new File("/../../other/right.gif").toURI().toString());
        pacman.setImage(upImage);
        mainRoot.setFocusTraversable(true);

    }

    @FXML
    public void kek(ActionEvent actionEvent) {
        this.pacman.setImage(leftImage);
    }

    public void getKeyboardInput(KeyEvent e) {
        switch (e.getCode()) {
            case A:
                moveLeft();
                break;
            case D:
                moveRight();
                break;
            case S:
                moveDown();
                break;
            case W:
                moveUp();
                break;
        }
    }

    public void moveUp() {
        this.pacman.setImage(upImage);
        this.pacman.setTranslateY(this.pacman.getTranslateY() - pacman_y);
    }

    public void moveDown() {
        this.pacman.setImage(downImage);
        this.pacman.setTranslateY(this.pacman.getTranslateY() + pacman_y);
    }

    public void moveLeft() {
        this.pacman.setImage(leftImage);
        this.pacman.setTranslateX(this.pacman.getTranslateX() - pacman_x);
    }

    public void moveRight() {
        this.pacman.setImage(rightImage);
        this.pacman.setTranslateX(this.pacman.getTranslateX() + pacman_x);
    }

}
