package server.db;

import java.io.Serializable;

public class PlayerBD implements Serializable {
    private int id;
    private String name;
    private String score;

    public void setPlayerID(int playerID) {
        this.id = playerID;
    }

    public int getPlayerID() {
        return this.id;
    }

    public String getPlayerName() {
        return this.name;
    }

    public void setPlayerName(String playerName) {
        this.name = playerName;
    }

    public String getPlayerScore() {
        return this.score;
    }

    public void setPlayerScore(String score) {
        this.score = score;
    }

}
