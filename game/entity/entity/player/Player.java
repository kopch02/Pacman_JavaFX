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
    float speed = 1;


    public Player(Image entityImage) {
        super(entityImage);
        upImage = super.getImage();

        downImage = new Image(new File("other/down.gif").toURI().toString());
        leftImage = new Image(new File("other/left.gif").toURI().toString());
        rightImage = new Image(new File("other/right.gif").toURI().toString());
    }

    public void update(DIRECTION value) {
        if (this.isMoving()) {
            if (this.curDir == DIRECTION.up) {
                if (gamemap.checkUp(getCoords())) {
                    this.entityImage = upImage;
                    moveUp();
                }
            } else if (this.curDir == DIRECTION.down) {
                if (gamemap.checkDown(getCoords())) {
                    this.entityImage = downImage;
                    moveDown();
                }
            } else if (this.curDir == DIRECTION.left) {
                if (gamemap.checkLeft(getCoords())) {
                    this.entityImage = leftImage;
                    moveLeft();
                }
            } else {
                if (gamemap.checkRight(getCoords())) {
                    this.entityImage = rightImage;
                    moveRight();
                }
            }

        }
    }

    

    public void setDirection(DIRECTION dir) {
        if (dir == DIRECTION.up && gamemap.checkUp(getCoords())) {
            this.curDir = DIRECTION.up;
        } else if (dir == DIRECTION.down && gamemap.checkDown(getCoords())) {
            this.curDir = DIRECTION.down;
        } else if (dir == DIRECTION.left && gamemap.checkLeft(getCoords())) {
            this.curDir = DIRECTION.left;
        } else if (dir == DIRECTION.right && gamemap.checkRight(getCoords())) {
            this.curDir = DIRECTION.right;
        }
    }

    public void moveUp() {
        Point2D curPos = getDrawPosition();
        setDrawPosition((float) curPos.getX(), (float) curPos.getY() - speed);
    }

    public void moveDown() {
        Point2D curPos = getDrawPosition();
        setDrawPosition((float) curPos.getX(), (float) curPos.getY() + speed);
    }

    public void moveLeft() {
        Point2D curPos = getDrawPosition();
        setDrawPosition((float) curPos.getX() - speed, (float) curPos.getY());
    }

    public void moveRight() {
        Point2D curPos = getDrawPosition();
        setDrawPosition((float) curPos.getX() + speed, (float) curPos.getY());
    }

}
