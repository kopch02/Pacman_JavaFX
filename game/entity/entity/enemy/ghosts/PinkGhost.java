package entity.entity.enemy.ghosts;

import entity.entity.enemy.Ghost;
import javafx.scene.image.Image;
import javafx.geometry.Point2D;
import entity.map.GameMap;
import java.io.File;

public class PinkGhost extends Ghost {

    Image upImage;
    Image downImage;
    Image leftImage;
    Image rightImage;

    Image upImageFear;
    Image downImageFear;
    Image leftImageFear;
    Image rightImageFear;

    boolean direction = false;
    Point2D ghostCenter;
    double distance1;
    double distance2;
    DIRECTION curDir;
    Integer xcur;
    Integer ycur;

    public PinkGhost(Image UpImage, GameMap gameMap) {
        super(UpImage, gameMap);
        this.curDir = DIRECTION.down;
        upImage = super.getImage();
        downImage = new Image(new File("other/ghosts/pink/down.png").toURI().toString());
        leftImage = new Image(new File("other/ghosts/pink/left.png").toURI().toString());
        rightImage = new Image(new File("other/ghosts/pink/right.png").toURI().toString());

        upImageFear = new Image(new File("other/ghosts/pink/upFear.png").toURI().toString());
        downImageFear = new Image(new File("other/ghosts/pink/downFear.png").toURI().toString());
        leftImageFear = new Image(new File("other/ghosts/pink/leftFear.png").toURI().toString());
        rightImageFear = new Image(new File("other/ghosts/pink/rightFear.png").toURI().toString());

        setMove(false);
        setScale(1.0f);
        setDrawPosition(270, 290);

    }

    public void update(Point2D playerCenter) {
        if (Integer.valueOf(gameMap.getScore()) >= 100) {
            setMove(true);
        }
        ghostCenter = getCenter();
        if (this.direction) {
            moving(this.curDir);
        } else {
            if (this.curDir == DIRECTION.up) {
                xcur = 0;
                ycur = -160;
            } else if (this.curDir == DIRECTION.down) {
                xcur = 0;
                ycur = 160;
            } else if (this.curDir == DIRECTION.left) {
                xcur = -160;
                ycur = 0;
            } else {
                xcur = 160;
                ycur = 0;
            }
            if (ghostCenter.getX() < playerCenter.getX()) {
                if (ghostCenter.getY() < playerCenter.getY()) {// левее и выше
                    this.distance1 = Math.sqrt(Math.pow(((ghostCenter.getX() + 40) - playerCenter.getX() + xcur), 2)
                            + Math.pow(((ghostCenter.getY()) - playerCenter.getY() + ycur), 2));
                    this.distance2 = Math.sqrt(Math.pow(((ghostCenter.getX()) - playerCenter.getX() + xcur), 2)
                            + Math.pow(((ghostCenter.getY() + 40) - playerCenter.getY() + ycur), 2));
                    if (this.distance1 < this.distance2) {
                        if (gameMap.checkRight(getCoords())) {
                            this.curDir = DIRECTION.right;
                            moveRight();
                        } else if (gameMap.checkDown(getCoords())) {
                            this.curDir = DIRECTION.down;
                            moveDown();
                        } else {
                            this.curDir = DIRECTION.left;
                            moveLeft();
                        }
                    } else {
                        if (gameMap.checkDown(getCoords())) {
                            this.curDir = DIRECTION.down;
                            moveDown();
                        } else if (gameMap.checkRight(getCoords())) {
                            this.curDir = DIRECTION.right;
                            moveRight();
                        } else {
                            this.curDir = DIRECTION.left;
                            moveLeft();
                        }
                    }
                } else {// левее и ниже
                    this.distance1 = Math.sqrt(Math.pow(((ghostCenter.getX() + 40) - playerCenter.getX() + xcur), 2)
                            + Math.pow(((ghostCenter.getY()) - playerCenter.getY() + ycur), 2));
                    this.distance2 = Math.sqrt(Math.pow(((ghostCenter.getX()) - playerCenter.getX() + xcur), 2)
                            + Math.pow(((ghostCenter.getY() - 40) - playerCenter.getY() + ycur), 2));
                    if (this.distance1 < this.distance2) {
                        if (gameMap.checkRight(getCoords())) {
                            this.curDir = DIRECTION.right;
                            moveRight();
                        } else if (gameMap.checkUp(getCoords())) {
                            this.curDir = DIRECTION.up;
                            moveUp();
                        } else {
                            this.curDir = DIRECTION.down;
                            moveDown();
                        }
                    } else {
                        if (gameMap.checkUp(getCoords())) {
                            this.curDir = DIRECTION.up;
                            moveUp();
                        } else if (gameMap.checkRight(getCoords())) {
                            this.curDir = DIRECTION.right;
                            moveRight();
                        } else {
                            this.curDir = DIRECTION.down;
                            moveDown();
                        }
                    }
                }
            } else {
                if (ghostCenter.getY() < playerCenter.getY()) {// гост правее и выше
                    this.distance1 = Math.sqrt(Math.pow(((ghostCenter.getX() - 40) - playerCenter.getX() + xcur), 2)
                            + Math.pow(((ghostCenter.getY()) - playerCenter.getY() + ycur), 2));
                    this.distance2 = Math.sqrt(Math.pow(((ghostCenter.getX()) - playerCenter.getX() + xcur), 2)
                            + Math.pow(((ghostCenter.getY() + 40) - playerCenter.getY() + ycur), 2));
                    if (this.distance1 < this.distance2) {
                        if (gameMap.checkLeft(getCoords())) {
                            this.curDir = DIRECTION.left;
                            moveLeft();
                        } else if (gameMap.checkDown(getCoords())) {
                            this.curDir = DIRECTION.down;
                            moveDown();
                        } else {
                            this.curDir = DIRECTION.up;
                            moveUp();
                        }
                    } else {
                        if (gameMap.checkDown(getCoords())) {
                            this.curDir = DIRECTION.down;
                            moveDown();
                        } else if (gameMap.checkLeft(getCoords())) {
                            this.curDir = DIRECTION.left;
                            moveLeft();
                        } else {
                            this.curDir = DIRECTION.up;
                            moveUp();
                        }
                    }
                } else {// гост правее и ниже
                    this.distance1 = Math.sqrt(Math.pow(((ghostCenter.getX() - 40) - playerCenter.getX() + xcur), 2)
                            + Math.pow(((ghostCenter.getY()) - playerCenter.getY() + ycur), 2));
                    this.distance2 = Math.sqrt(Math.pow(((ghostCenter.getX()) - playerCenter.getX() + xcur), 2)
                            + Math.pow(((ghostCenter.getY() - 40) - playerCenter.getY() + ycur), 2));
                    if (this.distance1 < this.distance2) {
                        if (gameMap.checkLeft(getCoords())) {
                            this.curDir = DIRECTION.left;
                            moveLeft();
                        } else if (gameMap.checkUp(getCoords())) {
                            this.curDir = DIRECTION.up;
                            moveUp();
                        } else {
                            this.curDir = DIRECTION.right;
                            moveRight();
                        }
                    } else {
                        if (gameMap.checkUp(getCoords())) {
                            this.curDir = DIRECTION.up;
                            moveUp();
                        } else if (gameMap.checkLeft(getCoords())) {
                            this.curDir = DIRECTION.left;
                            moveLeft();
                        } else {
                            this.curDir = DIRECTION.right;
                            moveRight();
                        }
                    }
                }
            }
            this.direction = true;
            moving(this.curDir);
        }
    }

    public void moving(DIRECTION direction) {
        if (this.isMoving()) {
            if (this.curDir == DIRECTION.up) {
                if (gameMap.checkUpGhost(getCoords())) {
                    if (gameMap.getAngry()) {
                        this.entityImage = upImageFear;
                    } else {
                        this.entityImage = upImage;
                    }
                    moveUp();
                } else {
                    this.direction = false;
                }
            } else if (this.curDir == DIRECTION.down) {
                if (gameMap.checkDownGhost(getCoords())) {
                    if (gameMap.getAngry()) {
                        this.entityImage = downImageFear;
                    } else {
                        this.entityImage = downImage;
                    }
                    moveDown();
                } else {
                    this.direction = false;
                }
            } else if (this.curDir == DIRECTION.left) {
                if (gameMap.checkLeftGhost(getCoords())) {
                    if (gameMap.getAngry()) {
                        this.entityImage = leftImageFear;
                    } else {
                        this.entityImage = leftImage;
                    }
                    moveLeft();
                } else {
                    this.direction = false;
                }
            } else if (this.curDir == DIRECTION.right) {
                if (gameMap.checkRightGhost(getCoords())) {
                    if (gameMap.getAngry()) {
                        this.entityImage = rightImageFear;
                    } else {
                        this.entityImage = rightImage;
                    }
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

    public void goSpawn() {
        setDrawPosition(340, 225);
    }
}
