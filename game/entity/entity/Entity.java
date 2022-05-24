package entity.entity;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.shape.*;
import entity.map.GameMap;

public class Entity {
    protected Point2D position;
    float rotation;
    float scale = -1;
    double width;
    double height;
    boolean collisionDetected;
    GameMap gamemap = new GameMap();

    protected Image entityImage;

    public Entity(Image entityImage) {
        this.entityImage = entityImage;
        this.width = entityImage.getWidth();
        this.height = entityImage.getHeight();
    }

    public Rectangle getSprite(){
        Point2D pos = getDrawPosition();
        Rectangle entity_sprate = new Rectangle();
        entity_sprate.setWidth(getWidth());
        entity_sprate.setHeight(getHeight());
        entity_sprate.setLayoutX(pos.getX());
        entity_sprate.setLayoutY(pos.getY());
        return entity_sprate;
    }

    public void checAllowedToMove(String side){
        
        Point2D pos = getDrawPosition();
        collisionDetected=gamemap.checkShapeIntersection(getSprite());
        if (collisionDetected ){
            if (side == "left"){
                collisionDetected=false;
                setDrawPosition((float) pos.getX()+3, (float) pos.getY()); 
            }
            else if (side == "right"){
                collisionDetected=false;
                setDrawPosition((float) pos.getX()-3, (float) pos.getY());
            }
            else if (side == "up"){
                collisionDetected=false;
                setDrawPosition((float) pos.getX(), (float) pos.getY()+3);
            }
            else if (side == "down"){
                collisionDetected=false;
                setDrawPosition((float) pos.getX(), (float) pos.getY()-3);
            }
        }
    }

    public Point2D getDrawPosition() {
        return position;
    }

    public void setDrawPosition(float x, float y) {
        this.position = new Point2D(x, y);
    }

    public float getRotation() {
        return rotation;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public Point2D getCenter() {
        Point2D pos = getDrawPosition();
        return new Point2D(pos.getX() + width / 2, pos.getY() + height / 2);
    }

    public Image getImage() {
        return entityImage;
    }

    public double getWidth() {
        return this.width * getScale();
    }

    public double getHeight() {
        return this.height * getScale();
    }

    public double layx() {
        Point2D pos = getDrawPosition();
        return pos.getX();
    }

    public double layy() {
        Point2D pos = getDrawPosition();
        return pos.getY();
    }
}
