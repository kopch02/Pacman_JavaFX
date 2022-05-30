package gameplay;

import java.io.File;
import java.io.IOException;

import entity.entity.Entity.DIRECTION;
import entity.entity.player.Player;
import entity.entity.enemy.Ghost;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import java.util.ArrayList;
import javafx.scene.shape.Rectangle;
import javafx.scene.canvas.GraphicsContext;//под отображение
import javafx.scene.control.Label;
import entity.map.GameMap;//под отображение

public class GamePlayController {
    GameMap gamemap = new GameMap();// под отображение

    @FXML
    public AnchorPane mainRoot;

    @FXML
    public Canvas gameCanvas;

    @FXML
    static Label score;

    KeyPolling keys = KeyPolling.getInstance();

    ArrayList<Rectangle> ghostList = new ArrayList<>();

    Image upImageOrange = new Image(new File("other/ghosts/orange/up.png").toURI().toString());
    Image downImageOrange = new Image(new File("other/ghosts/orange/down.png").toURI().toString());
    Image leftImageOrange = new Image(new File("other/ghosts/orange/left.png").toURI().toString());
    Image rightImageOrange = new Image(new File("other/ghosts/orange/right.png").toURI().toString());

    Image upImageBlue = new Image(new File("other/ghosts/blue/up.png").toURI().toString());
    Image downImageBlue = new Image(new File("other/ghosts/blue/down.png").toURI().toString());
    Image leftImageBlue = new Image(new File("other/ghosts/blue/left.png").toURI().toString());
    Image rightImageBlue = new Image(new File("other/ghosts/blue/right.png").toURI().toString());

    private Player player = new Player(new Image(new File("other/up.gif").toURI().toString()));

    private Ghost orangeGhost = new Ghost(upImageOrange, downImageOrange, leftImageOrange, rightImageOrange);
    private Ghost blueGhost = new Ghost(upImageBlue, downImageBlue, leftImageBlue, rightImageBlue);

    private void initializeCanvas() {
        gameCanvas.widthProperty().bind(mainRoot.widthProperty());
        gameCanvas.heightProperty().bind(mainRoot.heightProperty());
    }

    public static String getScore() {
        return score.getText();
    }

    public void initialize() {
        mainRoot.setFocusTraversable(true);
        initializeCanvas();
        player.setDrawPosition(315, 350);
        player.setScale(1.0f);
        player.setMove(true);

        orangeGhost.setDrawPosition(15, 15);
        orangeGhost.setScale(1.0f);
        orangeGhost.setMove(true);

        blueGhost.setDrawPosition(615, 15);
        blueGhost.setScale(1.0f);
        blueGhost.setMove(true);

        Renderer renderer = new Renderer(this.gameCanvas);
        renderer.setBackground(new Image(new File("other/map2.png").toURI().toString()));
        // GraphicsContext context = gameCanvas.getGraphicsContext2D();// под
        // отображение
        gamemap.create_points(renderer);
        renderer.addEntity(player);
        renderer.addEntity(orangeGhost);
        renderer.addEntity(blueGhost);

        GameLoopTimer timer = new GameLoopTimer() {
            @Override
            public void tick(float secondsSinceLastFrame) {
                renderer.prepare();
                updatePlayerMovement();
                player.update(player.getCurDirection());
                orangeGhost.update(player.getCenter());
                blueGhost.update(player.getCenter());
                renderer.render();
                ghostList.add(orangeGhost.getSprite());
                ghostList.add(blueGhost.getSprite());
                gamemap.eatpoint(player.getSprite(),renderer);
                player.setDead(gamemap.checkLose(player.getSprite(), ghostList));
                if (isDying()) {
                    stop();
                }
                ghostList.clear();
                // gamemap.create(context);// под отображение

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

    private boolean isDying() {
        if (this.player.isDead()) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../menu/death/DeathView.fxml"));
            AnchorPane pane;
            try {
                pane = fxmlLoader.load();
                mainRoot.getChildren().setAll(pane);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

}
