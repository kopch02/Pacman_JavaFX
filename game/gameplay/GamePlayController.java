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
import javafx.scene.canvas.GraphicsContext;//под отображение
import entity.map.GameMap;//под отображение

public class GamePlayController {
    GameMap gamemap = new GameMap();// под отображение

    @FXML
    public AnchorPane mainRoot;

    @FXML
    public Canvas gameCanvas;

    KeyPolling keys = KeyPolling.getInstance();

    private Player player = new Player(new Image(new File("other/up.gif").toURI().toString()));

    private Ghost orange_ghost = new Ghost(new Image(new File("other/gosts/orange/up.png").toURI().toString()));


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

        Renderer renderer = new Renderer(this.gameCanvas);
        renderer.setBackground(new Image(new File("other/map2.png").toURI().toString()));
        GraphicsContext context = gameCanvas.getGraphicsContext2D();// под отображение
        renderer.addEntity(player);
        renderer.addEntity(orange_ghost);


        GameLoopTimer timer = new GameLoopTimer() {
            @Override
            public void tick(float secondsSinceLastFrame) {
                renderer.prepare();
                updatePlayerMovement();
                player.update(player.getCurDirection());
                orange_ghost.update(player.getCenter());
                renderer.render();
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
