package entity.map;

import javafx.application.Application;

import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;


import java.util.ArrayList;
import javafx.scene.shape.*;

import java.io.IOException;

public class CollisionController extends Application {

  private ArrayList<Shape> sprites;

  @FXML
    Rectangle rectangle1=new Rectangle();
    @FXML
    Rectangle rectangle2=new Rectangle();
    @FXML
    Rectangle rectangle3=new Rectangle();
    @FXML
    Rectangle rectangle4=new Rectangle();
    @FXML
    Rectangle rectangle5=new Rectangle();
    @FXML
    Rectangle rectangle6=new Rectangle();
    @FXML
    Rectangle rectangle7=new Rectangle();
    @FXML
    Rectangle rectangle8=new Rectangle();
    @FXML
    Rectangle rectangle9=new Rectangle();
    @FXML
    Rectangle rectangle10=new Rectangle();
    @FXML
    Rectangle rectangle11=new Rectangle();
    @FXML
    Rectangle rectangle12=new Rectangle();
    @FXML
    Rectangle rectangle13=new Rectangle();
    @FXML
    Rectangle rectangle14=new Rectangle();
    @FXML
    Rectangle rectangle15=new Rectangle();
    @FXML
    Rectangle rectangle16=new Rectangle();
    @FXML
    Rectangle rectangle17=new Rectangle();
    @FXML
    Rectangle rectangle18=new Rectangle();
    @FXML
    Rectangle rectangle19=new Rectangle();
    @FXML
    Rectangle rectangle20=new Rectangle();
    @FXML
    Rectangle rectangle21=new Rectangle();
    @FXML
    Rectangle rectangle22=new Rectangle();
    @FXML
    Rectangle rectangle23=new Rectangle();
    @FXML
    Rectangle rectangle24=new Rectangle();
    @FXML
    Rectangle rectangle25=new Rectangle();
    @FXML
    Rectangle rectangle26=new Rectangle();
    @FXML
    Rectangle rectangle27=new Rectangle();
    @FXML
    Rectangle rectangle28=new Rectangle();
    @FXML
    Rectangle rectangle29=new Rectangle();
    @FXML
    Rectangle rectangle30=new Rectangle();
    @FXML
    Rectangle rectangle31=new Rectangle();


  public static void main(String[] args) { launch(args); }

  @Override public void start(Stage primaryStage) throws IOException{
    FXMLLoader fxmlLoader = new FXMLLoader(CollisionController.class.getResource("map.fxml"));
    Scene scene=new Scene(fxmlLoader.load(),660,650);
    primaryStage.setTitle("Packman???");
    Group root = new Group();

    sprites = new ArrayList<>();
    fill(sprites);
    
    
    root.getChildren().addAll(sprites);
    checkShapeIntersection(sprites.get(sprites.size() - 1));

    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private void fill(ArrayList sprites){
      sprites.add(rectangle1);
      sprites.add(rectangle2);
      sprites.add(rectangle3);
      sprites.add(rectangle4);
      sprites.add(rectangle5);
      sprites.add(rectangle6);
      sprites.add(rectangle7);
      sprites.add(rectangle8);
      sprites.add(rectangle9);
      sprites.add(rectangle10);
      sprites.add(rectangle11);
      sprites.add(rectangle12);
      sprites.add(rectangle13);
      sprites.add(rectangle14);
      sprites.add(rectangle15);
      sprites.add(rectangle16);
      sprites.add(rectangle17);
      sprites.add(rectangle18);
      sprites.add(rectangle19);
      sprites.add(rectangle20);
      sprites.add(rectangle21);
      sprites.add(rectangle22);
      sprites.add(rectangle23);
      sprites.add(rectangle24);
      sprites.add(rectangle25);
      sprites.add(rectangle26);
      sprites.add(rectangle27);
      sprites.add(rectangle28);
      sprites.add(rectangle29);
      sprites.add(rectangle30);
      sprites.add(rectangle31);

  }

  private void checkShapeIntersection(Shape block) {
    boolean collisionDetected = false;
    for (Shape player : sprites) {
      if (player != block) {
        player.setFill(Color.GREEN);

        Shape intersect = Shape.intersect(block, player);
        if (intersect.getBoundsInLocal().getWidth() != -1) {
          collisionDetected = true;
        }
      }
    }

    if (collisionDetected) {
      block.setFill(Color.BLUE);
    } else {
      block.setFill(Color.GREEN);
    }
  }

  class Delta { double x, y; }
}