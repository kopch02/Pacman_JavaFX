package entity.entity.enemy;

import java.io.File;

import entity.entity.Entity;
import javafx.scene.image.Image;
import entity.map.GameMap;
import javafx.geometry.Point2D;
import java.util.ArrayList;



public class Ghost extends Entity{
    
    Image upImage;
    Image downImage;
    Image leftImage;
    Image rightImage;
    double curDir;
    float speed = 1;
    boolean is_a_direction=false;
    Point2D ghost_center;
    double distance1;
    double distance2;


    GameMap gamemap = new GameMap();

    public static enum DIRECTION {
        up, down, left, right
    }

    public Ghost(Image entityImage) {
        super(entityImage);
        this.curDir = 1;
        upImage = super.getImage();
        downImage = new Image(new File("other/gosts/orange/down.png").toURI().toString());
        leftImage = new Image(new File("other/gosts/orange/left.png").toURI().toString());
        rightImage = new Image(new File("other/gosts/orange/right.png").toURI().toString());
    }
    public void update(Point2D player_center) {
        if (this.is_a_direction){
            moving(curDir);
        }
        else{
            ghost_center=getCenter();
            if (ghost_center.getX()<player_center.getX()){
                if (ghost_center.getY()<player_center.getY()){//левее и выше
                    distance1=Math.sqrt(Math.pow(((ghost_center.getX()+40)-player_center.getX()),2)+Math.pow(((ghost_center.getY())-player_center.getY()),2));
                    distance2=Math.sqrt(Math.pow(((ghost_center.getX())-player_center.getX()),2)+Math.pow(((ghost_center.getY()+40)-player_center.getY()),2));
                    if (distance1<distance2){
                        if (gamemap.checkRight_Ghost(getcoords())) {
                            curDir=4;
                        }
                        else if (gamemap.checkDown_Ghost(getcoords())) {
                            curDir=2;
                        }
                    }
                    else{
                        if (gamemap.checkDown_Ghost(getcoords())) {
                            curDir=2;
                        }
                        else if (gamemap.checkRight_Ghost(getcoords())) {
                            curDir=4;
                        }
                    }
                }
                else{//левее и ниже
                    distance1=Math.sqrt(Math.pow(((ghost_center.getX()+40)-player_center.getX()),2)+Math.pow(((ghost_center.getY())-player_center.getY()),2));
                    distance2=Math.sqrt(Math.pow(((ghost_center.getX())-player_center.getX()),2)+Math.pow(((ghost_center.getY()-40)-player_center.getY()),2));
                    if (distance1<distance2){
                        if (gamemap.checkRight_Ghost(getcoords())) {
                            curDir=4;
                        }
                        else if (gamemap.checkUp_Ghost(getcoords())) {
                            curDir=1;
                        }
                    }
                    else{
                        if (gamemap.checkUp_Ghost(getcoords())) {
                            curDir=1;
                        }
                        else if (gamemap.checkRight_Ghost(getcoords())) {
                            curDir=4;
                        }
                    }
                }
            }
            else{
                if (ghost_center.getY()<player_center.getY()){//гост правее и выше
                    distance1=Math.sqrt(Math.pow(((ghost_center.getX()-40)-player_center.getX()),2)+Math.pow(((ghost_center.getY())-player_center.getY()),2));
                    distance2=Math.sqrt(Math.pow(((ghost_center.getX())-player_center.getX()),2)+Math.pow(((ghost_center.getY()+40)-player_center.getY()),2));
                    if (distance1<distance2){
                        if (gamemap.checkLeft_Ghost(getcoords())) {
                            curDir=3;
                        }
                        else if (gamemap.checkDown_Ghost(getcoords())) {
                            curDir=2;
                        }
                    }
                    else{
                        if (gamemap.checkDown_Ghost(getcoords())) {
                            curDir=2;
                        }
                        else if (gamemap.checkLeft_Ghost(getcoords())) {
                            curDir=3;
                        }
                    }
                }
                else{//гост правее и ниже
                    distance1=Math.sqrt(Math.pow(((ghost_center.getX()-40)-player_center.getX()),2)+Math.pow(((ghost_center.getY())-player_center.getY()),2));
                    distance2=Math.sqrt(Math.pow(((ghost_center.getX())-player_center.getX()),2)+Math.pow(((ghost_center.getY()-40)-player_center.getY()),2));
                    if (distance1<distance2){
                        if (gamemap.checkLeft_Ghost(getcoords())) {
                            curDir=3;
                        }
                        else if (gamemap.checkUp_Ghost(getcoords())) {
                            curDir=1;
                        }
                    }
                    else {
                        if (gamemap.checkUp_Ghost(getcoords())) {
                            curDir=1;
                        }
                        else if (gamemap.checkLeft_Ghost(getcoords())) {
                            curDir=3;
                        }
                    }
                }
            }
            this.is_a_direction=true;
            moving(curDir);
        }
        
    }

    public void moving(double direction){
        if (this.isMoving()) {
            
            if (this.curDir == 1) {
                if (gamemap.checkUp(getcoords())) {
                    this.entityImage = upImage;
                    moveUp();
                }
                else{
                    this.is_a_direction=false;
                }
            } else if (this.curDir == 2) {
                if (gamemap.checkDown(getcoords())) {
                    this.entityImage = downImage;
                    moveDown();
                }
                else{
                    this.is_a_direction=false;
                }
            } else if (this.curDir == 3) {
                if (gamemap.checkLeft(getcoords())) {
                    this.entityImage = leftImage;
                    moveLeft();
                }
                else{
                    this.is_a_direction=false;
                }
            } else if (this.curDir == 4){
                if (gamemap.checkRight(getcoords())) {
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

