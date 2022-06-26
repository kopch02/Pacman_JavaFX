package menu.scores;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import entity.entity.Entity.DIRECTION;
import gameplay.GamePlayController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import server.db.PlayerBD;

public class Net {
    private ObjectInputStream in;
    private ObjectOutputStream out;
    Socket s;
    String ip;

    public Net(String ip) {
        try {
            this.ip = GamePlayController.ipS;
            System.out.println("Net init");
            System.out.println("NET IP::::" + this.ip);
            s = new Socket(this.ip, 1111);
            System.out.println("Local port: " + s.getLocalPort());
            System.out.println("Remote port: " + s.getPort());

            out = new ObjectOutputStream(s.getOutputStream());
            in = new ObjectInputStream(s.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            /*
             * try {
             * if (out != null) {
             * out.close();
             * }
             * if (in != null) {
             * in.close();
             * }
             * } catch (IOException e) {
             * 
             * }
             */
        }
    }

    public ObservableList<PlayerBD> receivePLFromServer() throws ClassNotFoundException, IOException { // PL - players
                                                                                                       // list
        try {
            out.writeBoolean(false);
            out.flush();
            ArrayList<PlayerBD> playerList = (ArrayList<PlayerBD>) in.readObject();
            ObservableList<PlayerBD> oPlayerList = FXCollections.observableArrayList(playerList);

            return oPlayerList;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void sendToServer(String name, String score) {
        try {
            out.writeBoolean(true);
            out.writeUTF(name);
            out.writeUTF(score);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendDir(DIRECTION dir) {
        try {
            out.flush();
            out.writeObject(dir);
            out.flush();
        } catch (IOException e) {

        }
    }

    public DIRECTION getDir() {
        try {
            return (DIRECTION) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        return DIRECTION.up;
    }
}
