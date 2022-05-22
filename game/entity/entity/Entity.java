package entity.entity;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class Entity {
    protected Point2D position;
    float rotation;
    float scale = -1;
    double width;
    double height;

    protected Image entityImage;

    public Entity(Image entityImage) {
        this.entityImage = entityImage;
        this.width = entityImage.getWidth();
        this.height = entityImage.getHeight();
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

}
