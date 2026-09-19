package persistance;

import model.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerDAO {
    private ConnectionPool pool;
    public PlayerDAO(ConnectionPool pool) {
        this.pool = pool;
    }
    public void save(Player player)throws SQLException{
        Connection connection = pool.getConnection();
        try {
            String sql = "INSERT INTO players (user_name) VALUES (?) ON CONFLICT (user_name) DO NOTHING";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,player.getUserName());
            preparedStatement.executeUpdate();
        }finally {
            pool.releaseConnection(connection);
        }
    }

    public Player findByUserName(String userName) throws SQLException {
        Connection conn = pool.getConnection();
        try {
            String sql = "SELECT * FROM players WHERE user_name = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Player player = new Player();
                player.setId(rs.getInt("id"));
                player.setUserName(rs.getString("user_name"));
                return player;
            }
            return null;
        } finally {
            pool.releaseConnection(conn);
        }
    }
}
