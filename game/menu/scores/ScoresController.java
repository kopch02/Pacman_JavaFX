package menu.scores;

import java.io.IOException;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import server.db.PlayerBD;

public class ScoresController {

    @FXML
    Button goBackButton;

    @FXML
    AnchorPane mainRoot;

    @FXML
    TableView<PlayerBD> playersTable;
    @FXML
    static TableView<PlayerBD> playersTableCopy;

    @FXML
    TableColumn<PlayerBD, String> playersNamesCol;
    @FXML
    TableColumn<PlayerBD, String> playersScoresCol;

    private NetDB netDB;

    String ip;
    static String ipS;

    public void setIP(String ip) {
        this.ip = ip;
        ipS = this.ip;
        System.out.println("SCC SET IP:" + this.ip);
        System.out.println("SCC STAT IP:" + ipS);
    }

    @FXML
    void initialize() {
        playersNamesCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPlayerName()));
        playersScoresCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPlayerScore()));

        try {
            refresh();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goBack(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../MenuView.fxml"));
        AnchorPane pane;
        try {
            pane = fxmlLoader.load();
            mainRoot.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void populatePlayers(ObservableList<PlayerBD> playersData) throws ClassNotFoundException {
        playersTable.setItems(playersData);
    }

    public void refresh() throws ClassNotFoundException, IOException {
        Platform.runLater(new MyThread(ip));
    }

    class MyThread extends Thread {
        String ip;
        public MyThread(String ip) {
            this.ip = ip;
        }

        @Override
        public void run() {
            try {
                netDB = new NetDB(this.ip);
                ObservableList<PlayerBD> playersData = netDB.receivePLFromServer();
                populatePlayers(playersData);
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
