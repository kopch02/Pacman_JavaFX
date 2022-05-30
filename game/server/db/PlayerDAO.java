package server.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PlayerDAO {

    public static ObservableList<PlayerBD> searchPlayers() throws SQLException, ClassNotFoundException {
        String select = "select p.name, s.score from players p join scores s on p.id = s.player_id";
        try {
            ResultSet rsPlayers = DBUtil.dbExecuteQuery(select);
            ObservableList<PlayerBD> playersList = getPlayersList(rsPlayers);
            playersList.sort(new Comparator<PlayerBD>() {
                @Override
                public int compare(PlayerBD p1, PlayerBD p2) {
                    if (Integer.valueOf(p1.getPlayerScore()) == Integer.valueOf(p2.getPlayerScore())) {
                        return 0;
                    } else if (Integer.valueOf(p1.getPlayerScore()) > Integer.valueOf(p2.getPlayerScore())) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            });
            return playersList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public static ObservableList<PlayerBD> getPlayersList(ResultSet rs) throws SQLException {
        ObservableList<PlayerBD> playersList = FXCollections.observableArrayList();

        while (rs.next()) {
            PlayerBD player = new PlayerBD();
            player.setPlayerName(rs.getString("name"));
            player.setPlayerScore(rs.getString("score"));
            playersList.add(player);
        }
        return playersList;
    }

    public static void insertPlayer(String name, String score) throws SQLException, ClassNotFoundException {
        String update = "insert all into players values (player_id_seq.nextval, '" + name + "')" +
                "into scores values (score_id_seq.nextval, player_id_seq.currval, '" + score + "')" +
                "select * from dual";
        try {
            DBUtil.dbExecuteUpdate(update);
            System.out.println(update);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
