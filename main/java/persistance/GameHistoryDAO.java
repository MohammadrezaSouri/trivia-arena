package persistance;

import model.GameResult;
import model.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class GameHistoryDAO {
    private final ConnectionPool pool;

    public GameHistoryDAO(ConnectionPool pool) {
        this.pool = pool;
    }

    public void save(GameResult result, Map<Player, Integer> scores) throws SQLException {
        Connection conn = pool.getConnection();
        try {
            String sql = "INSERT INTO game_history (player_id, score, category, difficulty, played_at) " +
                    "VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            for (Map.Entry<Player, Integer> entry : scores.entrySet()) {
                Player player = entry.getKey();
                int score = entry.getValue();

                stmt.setInt(1, player.getId());
                stmt.setInt(2, score);
                stmt.setString(3, result.getGameSession().getCategory());
                stmt.setString(4, result.getGameSession().getDifficulty().name());
                stmt.addBatch();
            }
            stmt.executeBatch();
        } finally {
            pool.releaseConnection(conn);
        }
    }

    public void printLeaderboard() throws SQLException {
        Connection conn = pool.getConnection();
        try {
            String sql = "SELECT p.user_name, SUM(gh.score) as total_score " +
                    "FROM game_history gh " +
                    "JOIN players p ON gh.player_id = p.id " +
                    "GROUP BY p.user_name " +
                    "ORDER BY total_score DESC";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            IO.println("=== LEADERBOARD ===");
            while (rs.next()) {
                IO.println(rs.getString("user_name") + ": " + rs.getInt("total_score"));
            }
        } finally {
            pool.releaseConnection(conn);
        }
    }
}
