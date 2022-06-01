package entity.entity.enemy;

import entity.entity.Entity;
import javafx.scene.image.Image;
import entity.map.GameMap;
import javafx.geometry.Point2D;

public class Ghost extends Entity {

    Image upImage;
    Image downImage;
    Image leftImage;
    Image rightImage;
    DIRECTION curDir;
    float speed = 1;
    boolean direction = false;
    Point2D ghostCenter;
    double distance1;
    double distance2;


    public Ghost(Image UpImage, Image DownImage, Image LeftImage, Image RightImage, GameMap gameMap) {
        super(UpImage, gameMap);
        this.curDir = DIRECTION.up;
        upImage = super.getImage();
        downImage = DownImage;
        leftImage = LeftImage;
        rightImage = RightImage;
    }

    

    public void moving(DIRECTION direction) {
        if (this.isMoving()) {
            System.out.println("moving");
            if (this.curDir == DIRECTION.up) {
                if (gameMap.checkUpGhost(getCoords())) {
                    this.entityImage = upImage;
                    moveUp();
                } else {
                    this.direction = false;
                }
            } else if (this.curDir == DIRECTION.down) {
                if (gameMap.checkDownGhost(getCoords())) {
                    this.entityImage = downImage;
                    moveDown();
                } else {
                    this.direction = false;
                }
            } else if (this.curDir == DIRECTION.left) {
                if (gameMap.checkLeftGhost(getCoords())) {
                    this.entityImage = leftImage;
                    moveLeft();
                } else {
                    this.direction = false;
                }
            } else if (this.curDir == DIRECTION.right) {
                if (gameMap.checkRightGhost(getCoords())) {
                    this.entityImage = rightImage;
                    moveRight();
                } else {
                    this.direction = false;
                }
            }
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
