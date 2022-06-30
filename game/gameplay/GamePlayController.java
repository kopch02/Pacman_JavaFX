package gameplay;

import java.io.File;
import java.io.IOException;

import entity.entity.Entity.DIRECTION;
import entity.entity.player.Player;
import entity.entity.enemy.ghosts.RedGhost;
import entity.entity.enemy.ghosts.PinkGhost;
import entity.entity.enemy.Ghost;
import entity.entity.enemy.ghosts.BlueGhost;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import menu.death.DeathController;
import menu.scores.Net;

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

    private Player player1;
    private Player player2;
    GraphicsContext context;

    private RedGhost redGhost = new RedGhost(upImageRed, gameMap);
    private PinkGhost pinkGhost = new PinkGhost(upImagePink, gameMap);
    private BlueGhost blueGhost = new BlueGhost(upImageBlue, gameMap);

    static String score;
    String loc = "localhost";
    String loc2 = "127.0.0.1";
    String ip = new String();
    public static String ipS;
    boolean multi;

    private void initializeCanvas() {
        gameCanvas.widthProperty().bind(mainRoot.widthProperty());
        gameCanvas.heightProperty().bind(mainRoot.heightProperty());
    }

    public void initialize() {
        mainRoot.setFocusTraversable(true);
        initializeCanvas();
        renderer = new Renderer(this.gameCanvas);
        renderer.setBackground(new Image(new File("other/map2.png").toURI().toString()));
        gameMap.createPoints(renderer);
        
    }

    public void initSingle() {
        context = gameCanvas.getGraphicsContext2D();
        multi = false;
        player1 = new Player(new Image(new File("other/up.gif").toURI().toString()), gameMap, false);
        player1.setDrawPosition(315, 350);
        player1.setScale(1.0f);
        player1.setMove(true);
        renderer.addEntity(player1);
        renderer.addEntity(redGhost);
        renderer.addEntity(pinkGhost);
        renderer.addEntity(blueGhost);
        Platform.runLater(new TimerThreadSingle());
    }

    public void setIp(String IP) {
        System.out.println("GMPC SET IP");
        System.out.println(IP);
        this.ip = IP;
        System.out.println(this.ip);
        ipS = this.ip;
        System.out.println(ipS);
    }

    public void initMulti() {
        System.out.println(ip);
        multi = true;
        if ((ip.equalsIgnoreCase(loc)) || ip.equalsIgnoreCase(loc2)) {
            System.out.println("EQUALS");
            player1 = new Player(new Image(new File("other/up.gif").toURI().toString()), gameMap, false);
            player2 = new Player(new Image(new File("other/ghosts/red/up.png").toURI().toString()), gameMap, true);
            player1.setDrawPosition(205, 350);
            player1.setDirection(DIRECTION.right);
            player2.setDrawPosition(425, 225);
            player2.setDirection(DIRECTION.left);
        } else {
            System.out.println("NNOOOOOOOT EQUALS");
            player1 = new Player(new Image(new File("other/ghosts/red/up.png").toURI().toString()), gameMap, true);
            player2 = new Player(new Image(new File("other/up.gif").toURI().toString()), gameMap, false);
            player1.setDrawPosition(425, 225);
            player1.setDirection(DIRECTION.left);
            player2.setDrawPosition(205, 350);
            player2.setDirection(DIRECTION.right);
        }

        player1.setScale(1.0f);
        player1.setMove(true);

        player2.setScale(1.0f);
        player2.setMove(true);
        renderer.addEntity(player1);
        renderer.addEntity(player2);
        renderer.addEntity(pinkGhost);
        renderer.addEntity(blueGhost);
        
        Platform.runLater(new TimerThreadMulti(player1, player2, ip));
    }

    private void updatePlayerMovement() {
        if (keys.isDown(KeyCode.W)) {
            player1.setDirection(DIRECTION.up);
        } else if (keys.isDown(KeyCode.S)) {
            player1.setDirection(DIRECTION.down);
        } else if (keys.isDown(KeyCode.A)) {
            player1.setDirection(DIRECTION.left);
        } else if (keys.isDown(KeyCode.D)) {
            player1.setDirection(DIRECTION.right);
        }
    }

    private boolean isDying() {
        if (multi) {
            if (this.player1.isDeadByGhost() || this.player1.isDeadByPlayer() || this.player2.isDeadByGhost() || this.player2.isDeadByPlayer()) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../menu/death/DeathView.fxml"));
                AnchorPane pane;
                try {
                    pane = fxmlLoader.load();
                    mainRoot.getChildren().setAll(pane);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (!player1.getIsGhost()) {
                    score = scorelLabel.getText();
                    DeathController deathController = (DeathController) fxmlLoader.getController();
                    deathController.initSingle();
                } else {
                    DeathController deathController = (DeathController) fxmlLoader.getController();
                    deathController.initMulti();
                }
                return true;

            }
            return false;
        } else {
            if (this.player1.isDeadByGhost()) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../menu/death/DeathView.fxml"));
                AnchorPane pane;
                try {
                    pane = fxmlLoader.load();
                    mainRoot.getChildren().setAll(pane);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (!player1.getIsGhost()) {
                    score = scorelLabel.getText();
                    DeathController deathController = (DeathController) fxmlLoader.getController();
                    deathController.initSingle();
                } else {
                    DeathController deathController = (DeathController) fxmlLoader.getController();
                    deathController.initMulti();
                }
                return true;

            }
            return false;
        }
    }

    public static String getScore() {
        return score;
    }

    class TimerThreadMulti extends Thread {
        Player player1;
        Player player2;
        String ip;

        public TimerThreadMulti(Player player11, Player player22, String ip) {
            this.player1 = player11;
            this.player2 = player22;
            this.ip = ip;
        }

        Net net = new Net(this.ip);

        public void run() {
            GameLoopTimer timer = new GameLoopTimer() {
                @Override
                public void tick(float secondsSinceLastFrame) {
                    renderer.prepare();
                    updatePlayerMovement();
                    player1.update(player1.getCurDirection());
                    net.sendDir(player1.getCurDirection());
                    DIRECTION d = net.getDir();
                    if (d instanceof DIRECTION) {
                        player2.setDirection(d);
                        player2.update(player2.getCurDirection());
                    }
                    renderer.render();
                    ghostList.add(pinkGhost);
                    ghostList.add(blueGhost);
                    if (!player1.getIsGhost()) {
                        gameMap.eatpoint(player1.getSprite(), renderer);
                        pinkGhost.update(player1.getCenter());
                        blueGhost.update(player1.getCenter(), redGhost.getCenter());
                        player1.setDeadByPlayer(gameMap.checkLoseMulti(player1, player2));
                        player1.setDeadByGhost(gameMap.checkLose(player1.getSprite(), ghostList));
                    } else {
                        gameMap.eatpoint(player2.getSprite(), renderer);
                        pinkGhost.update(player2.getCenter());
                        blueGhost.update(player2.getCenter(), redGhost.getCenter());
                        player2.setDeadByPlayer(gameMap.checkLoseMulti(player1, player2));
                        player2.setDeadByGhost(gameMap.checkLose(player2.getSprite(), ghostList));
                    }
                    scorelLabel.setText(gameMap.getScore());
                    if (isDying()) {
                        net.closeCon();
                        stop();
                    }
                    ghostList.clear();
                }
            };
            timer.start();
        }
    }

    class TimerThreadSingle extends Thread {
        public void run() {
            GameLoopTimer timer = new GameLoopTimer() {
                @Override
                public void tick(float secondsSinceLastFrame) {
                    renderer.prepare();
                    updatePlayerMovement();
                    player1.update(player1.getCurDirection());
                    

                    redGhost.update(player1.getCenter());
                    pinkGhost.update(player1.getCenter());
                    blueGhost.update(player1.getCenter(), redGhost.getCenter());

                    renderer.render();
                    ghostList.add(redGhost);
                    ghostList.add(pinkGhost);
                    ghostList.add(blueGhost);
                    gameMap.eatpoint(player1.getSprite(), renderer);
                    scorelLabel.setText(gameMap.getScore());
                    player1.setDeadByGhost(gameMap.checkLose(player1.getSprite(), ghostList));
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
