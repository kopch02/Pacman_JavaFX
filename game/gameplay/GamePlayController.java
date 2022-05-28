package gameplay;

import java.io.File;

import entity.entity.player.Player;
import entity.entity.player.Player.DIRECTION;
import entity.entity.enemy.Ghost;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import java.util.ArrayList;
import javafx.scene.shape.Rectangle;
import javafx.scene.canvas.GraphicsContext;//под отображение
import entity.map.GameMap;//под отображение

public class GamePlayController {
    GameMap gamemap = new GameMap();// под отображение

    @FXML
    public AnchorPane mainRoot;

    @FXML
    public Canvas gameCanvas;

    KeyPolling keys = KeyPolling.getInstance();

    ArrayList<Rectangle> ghost_list = new ArrayList<>();

    Image upImage_orange=new Image(new File("other/gosts/orange/up.png").toURI().toString());
    Image downImage_orange=new Image(new File("other/gosts/orange/down.png").toURI().toString());
    Image leftImage_orange=new Image(new File("other/gosts/orange/left.png").toURI().toString());
    Image rightImage_orange=new Image(new File("other/gosts/orange/right.png").toURI().toString());

    Image upImage_blue=new Image(new File("other/gosts/blue/up.png").toURI().toString());
    Image downImage_blue=new Image(new File("other/gosts/blue/down.png").toURI().toString());
    Image leftImage_blue=new Image(new File("other/gosts/blue/left.png").toURI().toString());
    Image rightImage_blue=new Image(new File("other/gosts/blue/right.png").toURI().toString());

    private Player player = new Player(new Image(new File("other/up.gif").toURI().toString()));

    private Ghost orange_ghost = new Ghost(upImage_orange,downImage_orange,leftImage_orange,rightImage_orange);
    private Ghost blue_ghost = new Ghost(upImage_blue,downImage_blue,leftImage_blue,rightImage_blue);


    private void initializeCanvas() {
        gameCanvas.widthProperty().bind(mainRoot.widthProperty());
        gameCanvas.heightProperty().bind(mainRoot.heightProperty());
    }

    public void initialize() {
        mainRoot.setFocusTraversable(true);
        initializeCanvas();
        player.setDrawPosition(315, 350);
        player.setScale(1.0f);
        player.setMove(true);

        orange_ghost.setDrawPosition(15, 15);
        orange_ghost.setScale(1.0f);
        orange_ghost.setMove(true);

        blue_ghost.setDrawPosition(615, 15);
        blue_ghost.setScale(1.0f);
        blue_ghost.setMove(true);

        Renderer renderer = new Renderer(this.gameCanvas);
        renderer.setBackground(new Image(new File("other/map2.png").toURI().toString()));
        GraphicsContext context = gameCanvas.getGraphicsContext2D();// под отображение
        renderer.addEntity(player);
        renderer.addEntity(orange_ghost);
        renderer.addEntity(blue_ghost);


        GameLoopTimer timer = new GameLoopTimer() {
            @Override
            public void tick(float secondsSinceLastFrame) {
                renderer.prepare();
                updatePlayerMovement();
                player.update(player.getCurDirection());
                orange_ghost.update(player.getCenter());
                blue_ghost.update(player.getCenter());
                renderer.render();
                ghost_list.add(orange_ghost.getSprite());
                ghost_list.add(blue_ghost.getSprite());
                player.setDead(gamemap.checkLose(player.getSprite(),ghost_list));
                ghost_list.clear();
                gamemap.create(context);// под отображение
                
            }
        };
        timer.start();
    }

    private void updatePlayerMovement() {
        if (keys.isDown(KeyCode.W)) {
            player.setDirection(DIRECTION.up);
        } else if (keys.isDown(KeyCode.S)) {
            player.setDirection(DIRECTION.down);
        } else if (keys.isDown(KeyCode.A)) {
            player.setDirection(DIRECTION.left);
        } else if (keys.isDown(KeyCode.D)) {
            player.setDirection(DIRECTION.right);
        }
    }

}
