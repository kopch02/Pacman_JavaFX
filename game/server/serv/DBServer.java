package server.serv;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import server.db.PlayerBD;
import server.db.PlayerDAO;

public class DBServer {

    static ServerSocket ss = null;

    public static void main(String[] args) {
        try {
            ss = new ServerSocket(1112);
            while (true) {
                System.out.println("Waiting connection...");

                Socket clientSocket = ss.accept();

                new MyThreadDB(clientSocket).start();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

class MyThreadDB extends Thread {
    private Socket clientSocket;

    public MyThreadDB(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            Socket localSocket = clientSocket;
            System.out.println("Local port: " + localSocket.getLocalPort());
            System.out.println("Remote port: " + localSocket.getPort());
            DataInputStream in = new DataInputStream(localSocket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(localSocket.getOutputStream());

            if (in.readBoolean()) {
                String playerName = in.readUTF();
                String playerScore = in.readUTF();
                System.out.println(playerName);
                System.out.println(playerScore);
                PlayerDAO.insertPlayer(playerName, playerScore);
            } else {
                ObservableList<PlayerBD> playersList = PlayerDAO.searchPlayers();
                out.writeObject(new ArrayList<PlayerBD>(playersList));
                out.flush();
            }

        } catch (Exception e) {
            System.out.println("Connection reset");
            try {
                clientSocket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }


}