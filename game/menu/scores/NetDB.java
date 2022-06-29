package menu.scores;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import server.db.PlayerBD;

public class NetDB {
    private ObjectInputStream in;
    private DataOutputStream out;
    Socket s;
    String ip;

    public NetDB(String ip) {
        try {
            this.ip = ScoresController.ipS;
            System.out.println("Net init");
            System.out.println("NET IP::::" + this.ip);
            s = new Socket(this.ip, 1112);
            System.out.println("Local port: " + s.getLocalPort());
            System.out.println("Remote port: " + s.getPort());

            out = new DataOutputStream(s.getOutputStream());
            in = new ObjectInputStream(s.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
          
        }
    }
    public void closeCon() {
        try{
        if (this.in != null && this.out != null) {
            in.close();
            out.close();
        }
        }catch(Exception e){
            
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
