package gameplay;

import java.io.File;

import entity.entity.player.Player;
import entity.entity.player.Player.DIRECTION;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

public class GamePlayController {

    @FXML
    public AnchorPane mainRoot;

    @FXML
    public Canvas gameCanvas;

    KeyPolling keys = KeyPolling.getInstance();

    private Player player = new Player(new Image(new File("other/up.gif").toURI().toString()));

    private void initializeCanvas() {
        gameCanvas.widthProperty().bind(mainRoot.widthProperty());
        gameCanvas.heightProperty().bind(mainRoot.heightProperty());
    }

    public void initialize() {
        mainRoot.setFocusTraversable(true);
        initializeCanvas();
        player.setDrawPosition(350, 285);
        player.setScale(1.4f);
        player.setMove(true);

        Renderer renderer = new Renderer(this.gameCanvas);
        renderer.addEntity(player);
        renderer.setBackground(new Image(new File("other/map.png").toURI().toString()));

        GameLoopTimer timer = new GameLoopTimer() {
            @Override
            public void tick(float secondsSinceLastFrame) {
                renderer.prepare();
                updatePlayerMovement();
                player.update(player.getCurDirection());
                renderer.render();
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
