package entity.entity;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import entity.map.GameMap;
import java.util.ArrayList;

public class Entity {
    protected Point2D position;
    float rotation;
    float scale = -1;
    double width;
    double height;
    boolean collisionDetected;
    boolean isMoving = false;
    GameMap gamemap = new GameMap();

    protected Image entityImage;

    public Entity(Image entityImage) {
        this.entityImage = entityImage;
        this.width = 40;
        this.height = 40;
    }

    public Rectangle getSprite() {
        Point2D pos = getDrawPosition();
        Rectangle entity_sprite = new Rectangle();
        entity_sprite.setWidth(getWidth());
        entity_sprite.setHeight(getHeight());
        entity_sprite.setLayoutX(pos.getX());
        entity_sprite.setLayoutY(pos.getY());
        return entity_sprite;
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

    public ArrayList<Double> getcoords() {
        ArrayList<Double> coords = new ArrayList<>();
        Point2D pos = getDrawPosition();
        coords.add((double) pos.getX());
        coords.add((double) pos.getY());
        coords.add((double) (pos.getX() + this.width * getScale()));
        coords.add((double) (pos.getY() + this.height * getScale()));
        return coords;
    }

    public boolean isMoving() {
        return this.isMoving;
    }

    public void setMove(boolean t) {
        this.isMoving = t;
    }

    // под отображение
    public double layx() {
        Point2D pos = getDrawPosition();
        return pos.getX();
    }

    // под отображение
    public double layy() {
        Point2D pos = getDrawPosition();
        return pos.getY();
    }
    public static double getRandom(){
        double x = (int)(Math.random()*((4-1)+1))+1;
        return x;
    }
}
