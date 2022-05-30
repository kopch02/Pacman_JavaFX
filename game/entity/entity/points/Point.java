package entity.entity.points;

import java.io.File;
import entity.entity.Entity;
import javafx.scene.image.Image;
import entity.map.GameMap;

public class Point extends Entity {

    // Image point_image=new Image(new File("other/point.png").toURI().toString());
    Image pointImage = new Image(new File("other/point.png").toURI().toString());

    GameMap gamemap = new GameMap();

    public Point(Image pointImage, float x, float y) {
        super(pointImage);
        setDrawPosition(x, y);
        setScale(0.3f);
    }

}
