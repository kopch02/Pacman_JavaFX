package menu.scores;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import entity.entity.Entity.DIRECTION;
import gameplay.GamePlayController;

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
