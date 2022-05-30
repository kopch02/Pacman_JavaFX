package entity.entity.player;

import java.io.File;

import entity.entity.Entity;
import entity.map.GameMap;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class Player extends Entity {

    Image upImage;
    Image downImage;
    Image leftImage;
    Image rightImage;
    float speed = 1;
    boolean Dead = false;
    static String name;

    public Player(Image entityImage, GameMap gameMap) {
        super(entityImage, gameMap);
        upImage = super.getImage();

        downImage = new Image(new File("other/down.gif").toURI().toString());
        leftImage = new Image(new File("other/left.gif").toURI().toString());
        rightImage = new Image(new File("other/right.gif").toURI().toString());
    }

    public static void setName(String name) {
        Player.name = name;
    }

    public static String getName() {
        return name;
    }

    public void update(DIRECTION value) {
        if (this.isMoving()) {
            if (this.curDir == DIRECTION.up) {
                if (gameMap.checkUp(getCoords())) {
                    this.entityImage = upImage;
                    moveUp();
                }
            } else if (this.curDir == DIRECTION.down) {
                if (gameMap.checkDown(getCoords())) {
                    this.entityImage = downImage;
                    moveDown();
                }
            } else if (this.curDir == DIRECTION.left) {
                if (gameMap.checkLeft(getCoords())) {
                    this.entityImage = leftImage;
                    moveLeft();
                }
            } else {
                if (gameMap.checkRight(getCoords())) {
                    this.entityImage = rightImage;
                    moveRight();
                }
            }

        }
    }

    public void setDirection(DIRECTION dir) {
        if (dir == DIRECTION.up && gameMap.checkUp(getCoords())) {
            this.curDir = DIRECTION.up;
        } else if (dir == DIRECTION.down && gameMap.checkDown(getCoords())) {
            this.curDir = DIRECTION.down;
        } else if (dir == DIRECTION.left && gameMap.checkLeft(getCoords())) {
            this.curDir = DIRECTION.left;
        } else if (dir == DIRECTION.right && gameMap.checkRight(getCoords())) {
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

    public void setDead(boolean t) {
        Dead = t;
    }

    public boolean isDead() {
        return Dead;
    }

}
