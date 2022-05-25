package gameplay;

import java.io.File;

import entity.entity.player.Player;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.canvas.GraphicsContext;//под отображение
import entity.map.GameMap;//под отображение

public class GamePlayController {
    GameMap gamemap = new GameMap();//под отображение

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
        player.setDrawPosition(330, 285);
        player.setScale(1.0f);

        Renderer renderer = new Renderer(this.gameCanvas);
        renderer.setBackground(new Image(new File("other/map2.png").toURI().toString()));
        GraphicsContext context = gameCanvas.getGraphicsContext2D();//под отображение
        renderer.addEntity(player);

        GameLoopTimer timer = new GameLoopTimer() {
            @Override
            public void tick(float secondsSinceLastFrame) {
                renderer.prepare();
                updatePlayerMovement(secondsSinceLastFrame);
                renderer.render();
                gamemap.create(context);//под отображение
                context.fillRect(player.layx(), player.layy(), player.getWidth(), player.getHeight());//под отображение
            }
        };
        timer.start();
    }

    private void updatePlayerMovement(float frameDuration) {
        if (keys.isDown(KeyCode.W)) {
            player.moveUp();
        } else if (keys.isDown(KeyCode.S)) {
            player.moveDown();
        } else if (keys.isDown(KeyCode.A)) {
            player.moveLeft();
        } else if (keys.isDown(KeyCode.D)) {
            player.moveRight();
        }
    }

}
