package entity.map;

import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;



public class GameMap {
    ArrayList<Rectangle> nodes= new ArrayList<>();

    public GameMap(){
        nodes.add(new Rectangle(47, 52, 73, 40));//rec1
        nodes.add(new Rectangle(168, 52, 97, 40));//rec2
        nodes.add(new Rectangle(312, 0, 25, 92));//rec3
        nodes.add(new Rectangle(384, 52, 97, 40));//rec4
        nodes.add(new Rectangle(529, 52, 73, 40));//rec5
        nodes.add(new Rectangle(46, 139, 73, 15));//rec6
        nodes.add(new Rectangle(167, 139, 25, 140));//rec7
        nodes.add(new Rectangle(192, 198, 73, 22));//rec8
        nodes.add(new Rectangle(241, 136, 168, 22));//rec9
        nodes.add( new Rectangle(312, 154, 25, 61));//rec10
        nodes.add( new Rectangle(385, 198, 73, 22));//rec11
        nodes.add( new Rectangle(456, 139, 25, 140));//rec12
        nodes.add( new Rectangle(529, 139, 73, 15));//rec13
        nodes.add( new Rectangle(167, 329, 25, 76));//rec14
        nodes.add( new Rectangle(241, 386, 168, 22));//rec15
        nodes.add( new Rectangle(456, 329, 25, 76));//rec16
        nodes.add( new Rectangle(312, 408, 25, 61));//rec17
        nodes.add( new Rectangle(-6, 513, 52, 22));//rec18
        nodes.add( new Rectangle(46, 451, 73, 22));//rec19
        nodes.add( new Rectangle(94, 472, 25, 61));//rec20
        nodes.add( new Rectangle(46, 578, 216, 22));//rec21
        nodes.add( new Rectangle(167, 517, 25, 61));//rec22
        nodes.add( new Rectangle(167, 454, 97, 15));//rec23
        nodes.add( new Rectangle(240, 513, 168, 22));//rec24
        nodes.add( new Rectangle(312, 533, 25, 61));//rec25
        nodes.add( new Rectangle(384, 454, 97, 15));//rec26
        nodes.add( new Rectangle(456, 517, 25, 61));//rec27
        nodes.add( new Rectangle(385, 578, 216, 22));//rec28
        nodes.add( new Rectangle(529, 451, 73, 22));//rec29
        nodes.add( new Rectangle(529, 472, 25, 61));//rec30
        nodes.add( new Rectangle(601, 513, 52, 22));//rec31

        //границы экрана
        nodes.add( new Rectangle(0, 0, 662, 12));
        nodes.add( new Rectangle(0, 0, 10, 203));

        nodes.add( new Rectangle(0, 203, 123, 10));
        nodes.add( new Rectangle(113, 203, 10, 72));
        nodes.add( new Rectangle(0, 275, 123, 10));

        nodes.add( new Rectangle(0, 327, 123, 10));
        nodes.add( new Rectangle(113, 327, 10, 72));
        nodes.add( new Rectangle(0, 399, 123, 10));

        nodes.add( new Rectangle(0, 399, 10, 245));
        nodes.add( new Rectangle(0, 640, 662, 10));
        nodes.add( new Rectangle(650, 399, 10, 245));

        nodes.add( new Rectangle(530, 327, 130, 10));
        nodes.add( new Rectangle(530, 327, 10, 72));
        nodes.add( new Rectangle(530, 399, 123, 10));

        nodes.add( new Rectangle(530, 203, 130, 10));
        nodes.add( new Rectangle(530, 203, 10, 72));
        nodes.add( new Rectangle(530, 275, 130, 10));

        nodes.add( new Rectangle(650, 0, 10, 203));
        
        //клетка в центре
        nodes.add( new Rectangle(237, 261, 61, 10));
        nodes.add( new Rectangle(347, 261, 56, 10));
        nodes.add( new Rectangle(237, 261, 10, 80));
        nodes.add( new Rectangle(400, 261, 10, 80));
        nodes.add( new Rectangle(237, 337, 172, 10));
    }

    public void create(GraphicsContext contex){
        for (Rectangle sprite : nodes) {
            drawRectangle(contex, sprite);
        }
        
    }

    private void drawRectangle(GraphicsContext gc,Rectangle rect){
        gc.setFill(Color.WHITE);
        gc.fillRect(rect.getX(),      
                    rect.getY(), 
                    rect.getWidth(), 
                    rect.getHeight());
        
     }
    
    public boolean checkShapeIntersection(Shape player_sprite) {
       boolean collisionDetected = false;
       for (Shape sprite : nodes) {
         if (sprite != player_sprite) {
           sprite.setFill(Color.GREEN);

           Shape intersect = Shape.intersect(player_sprite, sprite);
           if (intersect.getBoundsInLocal().getWidth() != -1) {
             collisionDetected = true;
             
           }
         }
       }
       if (collisionDetected){
        return true;
       }

      return false;
     }

}
