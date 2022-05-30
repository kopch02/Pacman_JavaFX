package entity.entity.enemy.ghosts;

import entity.entity.enemy.Ghost;
import javafx.scene.image.Image;
import javafx.geometry.Point2D;
import entity.map.GameMap;


public class PinkGhost extends Ghost{

    Image upImage;
    Image downImage;
    Image leftImage;
    Image rightImage;
    float speed = 1;
    boolean is_a_direction = false;
    Point2D ghost_center;
    double distance1;
    double distance2;
    DIRECTION curDir;
    Integer xcur;
    Integer ycur;

    GameMap gamemap = new GameMap();

    public PinkGhost(Image UpImage, Image DownImage, Image LeftImage, Image RightImage){
        super(UpImage, DownImage, LeftImage, RightImage);
        this.curDir = DIRECTION.down;
        upImage = super.getImage();
        downImage = DownImage;
        leftImage = LeftImage;
        rightImage = RightImage;

    }

    public void update(Point2D player_center) {
        ghost_center = getCenter();
        if (this.is_a_direction) {
            moving(this.curDir);
        } else {
            if (this.curDir==DIRECTION.up){
                xcur=0;
                ycur=-160;
            }
            else if (this.curDir==DIRECTION.down){
                xcur=0;
                ycur=160;
            }
            else if (this.curDir==DIRECTION.left){
                xcur=-160;
                ycur=0;
            }
            else {
                xcur=160;
                ycur=0;
            }
            if (ghost_center.getX() < player_center.getX()) {
                if (ghost_center.getY() < player_center.getY()) {// левее и выше
                    this.distance1 = Math.sqrt(Math.pow(((ghost_center.getX() + 40) - player_center.getX()+xcur), 2)+ Math.pow(((ghost_center.getY()) - player_center.getY()+ycur), 2));
                    this.distance2 = Math.sqrt(Math.pow(((ghost_center.getX()) - player_center.getX()+xcur), 2)+ Math.pow(((ghost_center.getY() + 40) - player_center.getY()+ycur), 2));
                    if (this.distance1 < this.distance2) {
                        if (gamemap.checkRight(getCoords())) {
                            this.curDir = DIRECTION.right;
                            moveRight();
                        } else if (gamemap.checkDown(getCoords())) {
                            this.curDir = DIRECTION.down;
                            moveDown();
                        } else {
                            this.curDir = DIRECTION.left;
                            moveLeft();
                        }
                    } else {
                        if (gamemap.checkDown(getCoords())) {
                            this.curDir = DIRECTION.down;
                            moveDown();
                        } else if (gamemap.checkRight(getCoords())) {
                            this.curDir = DIRECTION.right;
                            moveRight();
                        } else {
                            this.curDir = DIRECTION.left;
                            moveLeft();
                        }
                    }
                } else {// левее и ниже
                    this.distance1 = Math.sqrt(Math.pow(((ghost_center.getX() + 40) - player_center.getX()+xcur), 2)+ Math.pow(((ghost_center.getY()) - player_center.getY()+ycur), 2));
                    this.distance2 = Math.sqrt(Math.pow(((ghost_center.getX()) - player_center.getX()+xcur), 2)+ Math.pow(((ghost_center.getY() - 40) - player_center.getY()+ycur), 2));
                    if (this.distance1 < this.distance2) {
                        if (gamemap.checkRight(getCoords())) {
                            this.curDir = DIRECTION.right;
                            moveRight();
                        } else if (gamemap.checkUp(getCoords())) {
                            this.curDir = DIRECTION.up;
                            moveUp();
                        } else {
                            this.curDir = DIRECTION.down;
                            moveDown();
                        }
                    } else {
                        if (gamemap.checkUp(getCoords())) {
                            this.curDir = DIRECTION.up;
                            moveUp();
                        } else if (gamemap.checkRight(getCoords())) {
                            this.curDir = DIRECTION.right;
                            moveRight();
                        } else {
                            this.curDir = DIRECTION.down;
                            moveDown();
                        }
                    }
                }
            } else {
                if (ghost_center.getY() < player_center.getY()) {// гост правее и выше
                    this.distance1 = Math.sqrt(Math.pow(((ghost_center.getX() - 40) - player_center.getX()+xcur), 2)+ Math.pow(((ghost_center.getY()) - player_center.getY()+ycur), 2));
                    this.distance2 = Math.sqrt(Math.pow(((ghost_center.getX()) - player_center.getX()+xcur), 2)+ Math.pow(((ghost_center.getY() + 40) - player_center.getY()+ycur), 2));
                    if (this.distance1 < this.distance2) {
                        if (gamemap.checkLeft(getCoords())) {
                            this.curDir = DIRECTION.left;
                            moveLeft();
                        } else if (gamemap.checkDown(getCoords())) {
                            this.curDir = DIRECTION.down;
                            moveDown();
                        } else {
                            this.curDir = DIRECTION.up;
                            moveUp();
                        }
                    } else {
                        if (gamemap.checkDown(getCoords())) {
                            this.curDir = DIRECTION.down;
                            moveDown();
                        } else if (gamemap.checkLeft(getCoords())) {
                            this.curDir = DIRECTION.left;
                            moveLeft();
                        } else {
                            this.curDir = DIRECTION.up;
                            moveUp();
                        }
                    }
                } else {// гост правее и ниже
                    this.distance1 = Math.sqrt(Math.pow(((ghost_center.getX() - 40) - player_center.getX()+xcur), 2)+ Math.pow(((ghost_center.getY()) - player_center.getY()+ycur), 2));
                    this.distance2 = Math.sqrt(Math.pow(((ghost_center.getX()) - player_center.getX()+xcur), 2)+ Math.pow(((ghost_center.getY() - 40) - player_center.getY()+ycur), 2));
                    if (this.distance1 < this.distance2) {
                        if (gamemap.checkLeft(getCoords())) {
                            this.curDir = DIRECTION.left;
                            moveLeft();
                        } else if (gamemap.checkUp(getCoords())) {
                            this.curDir = DIRECTION.up;
                            moveUp();
                        } else {
                            this.curDir = DIRECTION.right;
                            moveRight();
                        }
                    } else {
                        if (gamemap.checkUp(getCoords())) {
                            this.curDir = DIRECTION.up;
                            moveUp();
                        } else if (gamemap.checkLeft(getCoords())) {
                            this.curDir = DIRECTION.left;
                            moveLeft();
                        } else {
                            this.curDir = DIRECTION.right;
                            moveRight();
                        }
                    }
                }
            }
            this.is_a_direction = true;
            moving(this.curDir);
        }
    }

    public void moving(DIRECTION direction) {
        if (this.isMoving()) {
            if (this.curDir == DIRECTION.up) {
                if (gamemap.checkUp_Ghost(getCoords())) {
                    this.entityImage = upImage;
                    moveUp();
                } else {
                    this.is_a_direction = false;
                }
            } else if (this.curDir == DIRECTION.down) {
                if (gamemap.checkDown_Ghost(getCoords())) {
                    this.entityImage = downImage;
                    moveDown();
                } else {
                    this.is_a_direction = false;
                }
            } else if (this.curDir == DIRECTION.left) {
                if (gamemap.checkLeft_Ghost(getCoords())) {
                    this.entityImage = leftImage;
                    moveLeft();
                } else {
                    this.is_a_direction = false;
                }
            } else if (this.curDir == DIRECTION.right) {
                if (gamemap.checkRight_Ghost(getCoords())) {
                    this.entityImage = rightImage;
                    moveRight();
                } else {
                    this.is_a_direction = false;
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
