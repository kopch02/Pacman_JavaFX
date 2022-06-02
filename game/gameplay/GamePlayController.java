package gameplay;

import java.io.File;
import java.io.IOException;

import entity.entity.Entity.DIRECTION;
import entity.entity.player.Player;
import entity.entity.enemy.ghosts.RedGhost;
import entity.entity.enemy.ghosts.PinkGhost;
import entity.entity.enemy.ghosts.BlueGhost;
import entity.entity.enemy.Ghost;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import java.util.ArrayList;
import javafx.scene.control.Label;
import entity.map.GameMap;

public class GamePlayController {
    GameMap gameMap = new GameMap();

    @FXML
    public AnchorPane mainRoot;

    @FXML
    public Canvas gameCanvas;

    Renderer renderer;

    @FXML
    Label scorelLabel;

    KeyPolling keys = KeyPolling.getInstance();

    ArrayList<Ghost> ghostList = new ArrayList<>();

    Image upImageRed = new Image(new File("other/ghosts/red/up.png").toURI().toString());

    Image upImagePink = new Image(new File("other/ghosts/pink/up.png").toURI().toString());

    Image upImageBlue = new Image(new File("other/ghosts/blue/up.png").toURI().toString());

    private Player player = new Player(new Image(new File("other/up.gif").toURI().toString()), gameMap);

    private RedGhost redGhost = new RedGhost(upImageRed, gameMap);
    private PinkGhost pinkGhost = new PinkGhost(upImagePink, gameMap);
    private BlueGhost blueGhost = new BlueGhost(upImageBlue, gameMap);

    static String score;

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

        renderer = new Renderer(this.gameCanvas);
        renderer.setBackground(new Image(new File("other/map2.png").toURI().toString()));
        gameMap.createPoints(renderer);
        renderer.addEntity(player);
        renderer.addEntity(redGhost);
        renderer.addEntity(pinkGhost);
        renderer.addEntity(blueGhost);
        Platform.runLater(new TimerThread());

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
            score = scorelLabel.getText();
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

    public static String getScore() {
        return score;
    }

    class TimerThread extends Thread {
        public void run() {
            GameLoopTimer timer = new GameLoopTimer() {
                @Override
                public void tick(float secondsSinceLastFrame) {
                    renderer.prepare();
                    updatePlayerMovement();
                    player.update(player.getCurDirection());
                    redGhost.update(player.getCenter());
                    pinkGhost.update(player.getCenter());
                    blueGhost.update(player.getCenter(), redGhost.getCenter());

                    renderer.render();
                    ghostList.add(redGhost);
                    ghostList.add(pinkGhost);
                    ghostList.add(blueGhost);
                    gameMap.eatpoint(player.getSprite(), renderer);
                    scorelLabel.setText(gameMap.getScore());
                    player.setDead(gameMap.checkLose(player.getSprite(), ghostList));
                    if (isDying()) {
                        stop();
                    }
                    ghostList.clear();
                }
            };
            timer.start();
        }
    }
}
