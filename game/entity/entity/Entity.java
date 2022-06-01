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
    protected DIRECTION curDir;
    protected GameMap gameMap;

    protected Image entityImage;

    public Entity(Image entityImage, GameMap gameMap) {
        this.entityImage = entityImage;
        this.gameMap = gameMap;
        this.width = 40;
        this.height = 40;
    }

    public static enum DIRECTION {
        up, down, left, right
    }

    public DIRECTION getCurDirection() {
        return this.curDir;
    }

    public Rectangle getSprite() {
        Point2D pos = getDrawPosition();
        Rectangle entitySprite = new Rectangle();
        entitySprite.setWidth(getWidth());
        entitySprite.setHeight(getHeight());
        entitySprite.setLayoutX(pos.getX());
        entitySprite.setLayoutY(pos.getY());
        return entitySprite;
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

    public ArrayList<Double> getCoords() {
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

    public static double getRandom() {
        double x = (int) (Math.random() * ((4 - 1) + 1)) + 1;
        return x;
    }
}
