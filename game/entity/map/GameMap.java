package entity.map;

import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class GameMap {
    ArrayList<Rectangle> nodes = new ArrayList<>();

    public GameMap() {
        nodes.add(new Rectangle(55, 55, 80, 45));// rec1
        nodes.add(new Rectangle(175, 55, 105, 45));// rec2
        nodes.add(new Rectangle(320, 0, 30, 100));// rec3
        nodes.add(new Rectangle(390, 55, 110, 45));// rec4
        nodes.add(new Rectangle(540, 55, 75, 45));// rec5

        nodes.add(new Rectangle(55, 140, 80, 20));// rec6
        nodes.add(new Rectangle(175, 140, 30, 150));// rec7
        nodes.add(new Rectangle(205, 200, 70, 25));// rec8
        nodes.add(new Rectangle(245, 140, 175, 20));// rec9
        nodes.add(new Rectangle(320, 160, 30, 65));// rec10
        nodes.add(new Rectangle(390, 200, 75, 25));// rec11
        nodes.add(new Rectangle(465, 140, 35, 150));// rec12
        nodes.add(new Rectangle(540, 140, 75, 20));// rec13

        nodes.add(new Rectangle(175, 330, 30, 85));// rec14
        nodes.add(new Rectangle(245, 390, 175, 25));// rec15
        nodes.add(new Rectangle(465, 330, 35, 85));// rec16
        nodes.add(new Rectangle(320, 415, 30, 60));// rec17

        nodes.add(new Rectangle(15, 515, 50, 25));// rec18
        nodes.add(new Rectangle(55, 455, 80, 20));// rec19
        nodes.add(new Rectangle(105, 475, 30, 65));// rec20
        nodes.add(new Rectangle(55, 580, 225, 25));// rec21
        nodes.add(new Rectangle(175, 515, 30, 65));// rec22
        nodes.add(new Rectangle(175, 455, 105, 20));// rec23
        nodes.add(new Rectangle(245, 515, 175, 25));// rec24
        nodes.add(new Rectangle(320, 540, 30, 65));// rec25
        nodes.add(new Rectangle(390, 455, 110, 20));// rec26
        nodes.add(new Rectangle(465, 515, 35, 65));// rec27
        nodes.add(new Rectangle(390, 580, 225, 25));// rec28
        nodes.add(new Rectangle(540, 455, 75, 20));// rec29
        nodes.add(new Rectangle(540, 475, 30, 65));// rec30
        nodes.add(new Rectangle(610, 515, 45, 25));// rec31

        // границы экрана
        nodes.add(new Rectangle(0, 0, 670, 15));
        nodes.add(new Rectangle(0, 0, 15, 200));

        nodes.add(new Rectangle(0, 200, 135, 15));
        nodes.add(new Rectangle(120, 215, 15, 60));//тройка лево верх
        nodes.add(new Rectangle(0, 275, 135, 15));

        nodes.add(new Rectangle(0, 330, 135, 15));
        nodes.add(new Rectangle(120, 345, 15, 60));//тройка лево низ
        nodes.add(new Rectangle(0, 400, 135, 15));

        nodes.add(new Rectangle(0, 400, 15, 245));
        nodes.add(new Rectangle(0, 645, 670, 15));//низ
        nodes.add(new Rectangle(655, 400, 15, 245));

        nodes.add(new Rectangle(540, 330, 130, 15));
        nodes.add(new Rectangle(540, 330, 15, 75));//тройка право низ
        nodes.add(new Rectangle(540, 400, 123, 15));

        nodes.add(new Rectangle(540, 200, 130, 15));
        nodes.add(new Rectangle(540, 215, 15, 75));//тройка право верх
        nodes.add(new Rectangle(540, 275, 130, 15));

        nodes.add(new Rectangle(655, 0, 15, 200));

        // клетка в центре
        nodes.add(new Rectangle(245, 265, 72, 10));//лево верх
        nodes.add(new Rectangle(357, 265, 68, 10));//право верх
        nodes.add(new Rectangle(245, 275, 10, 65));//лево
        nodes.add(new Rectangle(415, 275, 10, 65));//право
        nodes.add(new Rectangle(245, 340, 180, 10));//низ
    }

    public void create(GraphicsContext contex) {
        for (Rectangle sprite : nodes) {
            drawRectangle(contex, sprite);
        }

    }

    private void drawRectangle(GraphicsContext gc, Rectangle rect) {
        gc.setFill(Color.WHITE);
        gc.fillRect(rect.getX(),
                rect.getY(),
                rect.getWidth(),
                rect.getHeight());

    }

    public boolean within_y(ArrayList<Double> coords, Rectangle rect) {
        double rectY2 = rect.getY() + rect.getHeight();
        if (((coords.get(1) > rect.getY()) && (coords.get(1) < rectY2)) ||
                ((coords.get(3) > rect.getY()) && (coords.get(3) < rectY2)) ||
                ((rect.getY() > coords.get(1)) && (rect.getY() < coords.get(3))) ||
                ((rectY2 > coords.get(1)) && (rectY2 < coords.get(3)))) {
            return true;
        } else {
            return false;
        }
    }

    public boolean within_x(ArrayList<Double> coords, Rectangle rect) {
        double rectX2 = rect.getX() + rect.getWidth();
        if (((coords.get(0) > rect.getX()) && (coords.get(0) < rectX2)) ||
                ((coords.get(2) > rect.getX()) && (coords.get(2) < rectX2)) ||
                ((rect.getX() > coords.get(0)) && (rect.getX() < coords.get(2))) ||
                ((rectX2 > coords.get(0)) && (rectX2 < coords.get(2)))) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkLeft(ArrayList<Double> coords) {
        for (Rectangle sprite : nodes) {
            if (within_y(coords, sprite)) {
                if ((coords.get(0) <= sprite.getX() + sprite.getWidth()) && (coords.get(0) >= sprite.getX())) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkRight(ArrayList<Double> coords) {
        for (Rectangle sprite : nodes) {
            if (within_y(coords, sprite)) {
                if ((coords.get(2) >= sprite.getX()) && (coords.get(2) <= sprite.getX() + sprite.getWidth())) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkUp(ArrayList<Double> coords) {
        for (Rectangle sprite : nodes) {
            if (within_x(coords, sprite)) {
                if ((coords.get(1) <= sprite.getY() + sprite.getHeight()) && (coords.get(1) >= sprite.getY())) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkDown(ArrayList<Double> coords) {
        for (Rectangle sprite : nodes) {
            if (within_x(coords, sprite)) {
                if ((coords.get(3) <= sprite.getY() + sprite.getHeight()) && (coords.get(3) >= sprite.getY())) {
                    return false;
                }
            }
        }
        return true;
    }
}
