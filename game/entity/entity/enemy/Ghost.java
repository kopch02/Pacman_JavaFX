package entity.entity.enemy;

import java.io.File;

import entity.entity.Entity;
import javafx.scene.image.Image;

public class Ghost extends Entity{
    
    Image upImage;
    Image downImage;
    Image leftImage;
    Image rightImage;

    public Ghost(Image entityImage) {
        super(entityImage);
        upImage = super.getImage();
        downImage = new Image(new File("other/down.gif").toURI().toString());
        leftImage = new Image(new File("other/left.gif").toURI().toString());
        rightImage = new Image(new File("other/right.gif").toURI().toString());
    }
}
