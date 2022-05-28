package entity.entity.points;

import java.io.File;
import entity.entity.Entity;
import javafx.scene.image.Image;
import entity.map.GameMap;
import gameplay.Renderer;

public class Point extends Entity{

    //Image point_image=new Image(new File("other/point.png").toURI().toString());
    Image point_image=new Image(new File("other/point.png").toURI().toString());
    
    GameMap gamemap = new GameMap();

    public Point(Image point_image, float x,float y){
        super(point_image);
        setDrawPosition(x, y);
        setScale((float)0.3);
    }
    
    
}
