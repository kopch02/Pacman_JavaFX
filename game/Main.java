import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

import gameplay.KeyPolling;

public class Main extends Application{
    public static void main(String[] args) {
        launch();
    }
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML(), 670, 660);
        stage.setScene(scene);
        stage.setTitle("Pacman_JavaFX");
        stage.show();
        stage.setResizable(false);
        stage.centerOnScreen();
        KeyPolling.getInstance().pollScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent e) {
                System.out.println("Shutting down");
                System.exit(0);
            }
        });
    }


    private static Parent loadFXML() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("menu/MenuView.fxml"));
        return fxmlLoader.load();
    }

}
