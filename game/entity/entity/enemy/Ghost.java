package entity.entity.enemy;

import java.io.File;

import entity.entity.Entity;
import javafx.scene.image.Image;
import entity.map.GameMap;
import javafx.geometry.Point2D;


public class Ghost extends Entity{
    
    Image upImage;
    Image downImage;
    Image leftImage;
    Image rightImage;
    DIRECTION curDir;
    float speed = 1;
    boolean is_a_direction=false;
    Point2D ghost_center;
    double distance1;
    double distance2;


    GameMap gamemap = new GameMap();

    public static enum DIRECTION {
        up, down, left, right
    }

    public Ghost(Image UpImage,Image DownImage,Image LeftImage,Image RightImage) {
        super(UpImage);
        this.curDir = DIRECTION.up;
        upImage = super.getImage();
        downImage = DownImage;
        leftImage = LeftImage;
        rightImage = RightImage;
    }
    public void update(Point2D player_center) {
        ghost_center=getCenter();
        if (this.is_a_direction){
            moving(curDir);
        }
        else{
            
            if (ghost_center.getX()<player_center.getX()){
                if (ghost_center.getY()<player_center.getY()){//левее и выше
                    distance1=Math.sqrt(Math.pow(((ghost_center.getX()+40)-player_center.getX()),2)+Math.pow(((ghost_center.getY())-player_center.getY()),2));
                    distance2=Math.sqrt(Math.pow(((ghost_center.getX())-player_center.getX()),2)+Math.pow(((ghost_center.getY()+40)-player_center.getY()),2));
                    if (distance1<distance2){
                        if (gamemap.checkRight(getcoords())) {
                            curDir=DIRECTION.right;
                            moveRight();
                        }
                        else if (gamemap.checkDown(getcoords())) {
                            curDir=DIRECTION.down;
                            moveDown();
                        }
                        else{
                            curDir=DIRECTION.left;
                            moveLeft();
                        }
                    }
                    else{
                        if (gamemap.checkDown(getcoords())) {
                            curDir=DIRECTION.down;
                            moveDown();
                        }
                        else if (gamemap.checkRight(getcoords())) {
                            curDir=DIRECTION.right;
                            moveRight();
                        }
                        else{
                            curDir=DIRECTION.left;
                            moveLeft();
                        }
                    }
                }
                else{//левее и ниже
                    distance1=Math.sqrt(Math.pow(((ghost_center.getX()+40)-player_center.getX()),2)+Math.pow(((ghost_center.getY())-player_center.getY()),2));
                    distance2=Math.sqrt(Math.pow(((ghost_center.getX())-player_center.getX()),2)+Math.pow(((ghost_center.getY()-40)-player_center.getY()),2));
                    if (distance1<distance2){
                        if (gamemap.checkRight(getcoords())) {
                            curDir=DIRECTION.right;
                            moveRight();
                        }
                        else if (gamemap.checkUp(getcoords())) {
                            curDir=DIRECTION.up;
                            moveUp();
                        }
                        else{
                            curDir=DIRECTION.down;
                            moveDown();
                        }
                    }
                    else{
                        if (gamemap.checkUp(getcoords())) {
                            curDir=DIRECTION.up;
                            moveUp();
                        }
                        else if (gamemap.checkRight(getcoords())) {
                            curDir=DIRECTION.right;
                            moveRight();
                        }
                        else{
                            curDir=DIRECTION.down;
                            moveDown();
                        }
                    }
                }
            }
            else{
                if (ghost_center.getY()<player_center.getY()){//гост правее и выше
                    distance1=Math.sqrt(Math.pow(((ghost_center.getX()-40)-player_center.getX()),2)+Math.pow(((ghost_center.getY())-player_center.getY()),2));
                    distance2=Math.sqrt(Math.pow(((ghost_center.getX())-player_center.getX()),2)+Math.pow(((ghost_center.getY()+40)-player_center.getY()),2));
                    if (distance1<distance2){
                        if (gamemap.checkLeft(getcoords())) {
                            curDir=DIRECTION.left;
                            moveLeft();
                        }
                        else if (gamemap.checkDown(getcoords())) {
                            curDir=DIRECTION.down;
                            moveDown();
                        }
                        else{
                            curDir=DIRECTION.up;
                            moveUp();
                        }
                    }
                    else{
                        if (gamemap.checkDown(getcoords())) {
                            curDir=DIRECTION.down;
                            moveDown();
                        }
                        else if (gamemap.checkLeft(getcoords())) {
                            curDir=DIRECTION.left;
                            moveLeft();
                        }
                        else{
                            curDir=DIRECTION.up;
                            moveUp();
                        }
                    }
                }
                else{//гост правее и ниже
                    distance1=Math.sqrt(Math.pow(((ghost_center.getX()-40)-player_center.getX()),2)+Math.pow(((ghost_center.getY())-player_center.getY()),2));
                    distance2=Math.sqrt(Math.pow(((ghost_center.getX())-player_center.getX()),2)+Math.pow(((ghost_center.getY()-40)-player_center.getY()),2));
                    if (distance1<distance2){
                        if (gamemap.checkLeft(getcoords())) {
                            curDir=DIRECTION.left;
                            moveLeft();
                        }
                        else if (gamemap.checkUp(getcoords())) {
                            curDir=DIRECTION.up;
                            moveUp();
                        }
                        else{
                            curDir=DIRECTION.right;
                            moveRight();
                        }
                    }
                    else {
                        if (gamemap.checkUp(getcoords())) {
                            curDir=DIRECTION.up;
                            moveUp();
                        }
                        else if (gamemap.checkLeft(getcoords())) {
                            curDir=DIRECTION.left;
                            moveLeft();
                        }
                        else{
                            curDir=DIRECTION.right;
                            moveRight();
                        }
                    }
                }
            }
            this.is_a_direction=true;
            moving(curDir);
        }
        
    }

    public void moving(DIRECTION direction){
        if (this.isMoving()) {
            
            if (this.curDir == DIRECTION.up) {
                if (gamemap.checkUp_Ghost(getcoords())) {
                    this.entityImage = upImage;
                    moveUp();
                }
                else{
                    this.is_a_direction=false;
                }
            } else if (this.curDir == DIRECTION.down) {
                if (gamemap.checkDown_Ghost(getcoords())) {
                    this.entityImage = downImage;
                    moveDown();
                }
                else{
                    this.is_a_direction=false;
                }
            } else if (this.curDir == DIRECTION.left) {
                if (gamemap.checkLeft_Ghost(getcoords())) {
                    this.entityImage = leftImage;
                    moveLeft();
                }
                else{
                    this.is_a_direction=false;
                }
            } else if (this.curDir == DIRECTION.right){
                if (gamemap.checkRight_Ghost(getcoords())) {
                    this.entityImage = rightImage;
                    moveRight();
                }
                else{
                    this.is_a_direction=false;
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

