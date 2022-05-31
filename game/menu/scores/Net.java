package menu.scores;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import server.db.PlayerBD;

public class Net {
    private ObjectInputStream in;
    private DataOutputStream out;
    Socket s;


    public Net() {
        try {
            System.out.println("Net init");
            s = new Socket("127.0.0.1", 1111);
            System.out.println("Local port: " + s.getLocalPort());
            System.out.println("Remote port: " + s.getPort());

            in = new ObjectInputStream(s.getInputStream());
            out = new DataOutputStream(s.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            
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
}
