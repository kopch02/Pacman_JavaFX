package entity.entity.points;

import java.io.File;
import entity.entity.Entity;
import javafx.scene.image.Image;
import entity.map.GameMap;

public class Point extends Entity {

    Image pointImage = new Image(new File("other/point.png").toURI().toString());

    public Point(Image pointImage, float x, float y, GameMap gameMap) {
        super(pointImage, gameMap);
        setDrawPosition(x, y);
        setScale(0.3f);
    }

}
