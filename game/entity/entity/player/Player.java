package entity.entity.player;

import java.io.File;

import entity.entity.Entity;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class Player extends Entity {

    Image upImage;
    Image downImage;
    Image leftImage;
    Image rightImage;

    public static enum DIRECTION {
        up, down, left, right
    }

    public Player(Image entityImage) {
        super(entityImage);
        upImage = super.getImage();
        
        downImage = new Image(new File("other/down.gif").toURI().toString());
        leftImage = new Image(new File("other/left.gif").toURI().toString());
        rightImage = new Image(new File("other/right.gif").toURI().toString());
    }

    public void updateImage(DIRECTION value) {
        if (value == DIRECTION.up) {
            this.entityImage = upImage;
        } else if (value == DIRECTION.down) {
            this.entityImage = downImage;
        } else if (value == DIRECTION.left) {
            this.entityImage = leftImage;
        } else {
            this.entityImage = rightImage;
        }
    }

    public void moveUp() {
        updateImage(DIRECTION.up);
        Point2D curPos = this.getDrawPosition();
        this.setDrawPosition((float) curPos.getX(), (float) curPos.getY() - 1);
    }

    public void moveDown() {
        updateImage(DIRECTION.down);
        Point2D curPos = this.getDrawPosition();
        this.setDrawPosition((float) curPos.getX(), (float) curPos.getY() + 1);
    }

    public void moveLeft() {
        updateImage(DIRECTION.left);
        Point2D curPos = this.getDrawPosition();
        this.setDrawPosition((float) curPos.getX() - 1, (float) curPos.getY());
    }

    public void moveRight() {
        updateImage(DIRECTION.right);
        Point2D curPos = this.getDrawPosition();
        this.setDrawPosition((float) curPos.getX() + 1, (float) curPos.getY());
    }

}