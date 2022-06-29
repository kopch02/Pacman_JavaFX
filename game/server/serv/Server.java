package server.serv;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import entity.entity.Entity.DIRECTION;

public class Server {

    static ServerSocket ss = null;
    static ArrayList<Socket> ez = new ArrayList<>();
    

    public static void main(String[] args) {

        try {
            ss = new ServerSocket(1111);
            while (true) {
                while(ez.size() < 2){
                System.out.println("Waiting connection...");
                System.out.println(ez.size());

                Socket clientSocket = ss.accept();
                ez.add(clientSocket);
                }
                new MyThread().start();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<Socket> getList() {
        return ez;
    }

    public static void resetList() {
        ez.clear();
    }

}

class MyThread extends Thread {
    ArrayList<Socket> ips = Server.getList();
    

    private Socket clientSocket1;
    private Socket clientSocket2;
    public MyThread() {
        System.out.println(ips.size());
        System.out.println(ips.get(0));
        System.out.println(ips.get(1));
        this.clientSocket1 = ips.get(0);
        this.clientSocket2 = ips.get(1);
        Server.resetList();
    }

    @Override
    public void run() {
        try {
            Socket localSocket1 = clientSocket1;
            Socket localSocket2 = clientSocket2;
            System.out.println(localSocket1.getRemoteSocketAddress());
            System.out.println(localSocket2.getRemoteSocketAddress());
            System.out.println("Local port1: " + localSocket1.getLocalPort());
            System.out.println("Remote port1: " + localSocket1.getPort());
            System.out.println("Local port2: " + localSocket2.getLocalPort());
            System.out.println("Remote port2: " + localSocket2.getPort());

            OutputStream o1 = localSocket1.getOutputStream();
            ObjectOutputStream out1 = new ObjectOutputStream(o1);

            InputStream is1 = localSocket1.getInputStream();
            ObjectInputStream in1 = new ObjectInputStream(is1);

            OutputStream o2 = localSocket2.getOutputStream();
            ObjectOutputStream out2 = new ObjectOutputStream(o2);

            InputStream is2 = localSocket2.getInputStream();
            ObjectInputStream in2 = new ObjectInputStream(is2);

            
            
            while (true) {
                DIRECTION player1 = (DIRECTION) in1.readObject();
                out2.writeObject(player1);
                out2.flush();
                DIRECTION player2 = (DIRECTION) in2.readObject();
                out1.writeObject(player2);
                out1.flush();
            }
           

        } catch (Exception e) {
            System.out.println("Connection reset");
            try {
                clientSocket1.close();
                clientSocket2.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

}