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
    protected float speed = 2.5f;
    boolean direction = false;
    Point2D ghostCenter;
    double distance1;
    double distance2;
    boolean goSpawn = false;

    public Ghost(Image UpImage, GameMap gameMap) {
        super(UpImage, gameMap);
        this.curDir = DIRECTION.up;
        upImage = super.getImage();
    }


    public void switchGoSpawn() {
        this.goSpawn = !this.goSpawn;
    }

    public boolean getGoSpawn() {
        return this.goSpawn;
    }

    public void goSpawn() {
    }
}
