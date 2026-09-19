package view;

import persistance.ConnectionPool;
import persistance.GameHistoryDAO;
import persistance.PlayerDAO;
import service.GameEngine;
import service.LeaderboardService;

import java.sql.SQLException;

public class AppContext {
    private final ConnectionPool connectionPool;
    private final PlayerDAO playerDAO;
    private final GameHistoryDAO gameHistoryDAO;
    private final GameEngine gameEngine;
    private final LeaderboardService leaderboardService;

    public AppContext() throws SQLException {
        this.connectionPool = ConnectionPool.getInstance();
        this.playerDAO = new PlayerDAO(connectionPool);
        this.gameHistoryDAO = new GameHistoryDAO(connectionPool);
        this.gameEngine = GameEngine.gameEngine();
        this.leaderboardService = new LeaderboardService();
    }

    public PlayerDAO getPlayerDAO() { return playerDAO; }
    public GameHistoryDAO getGameHistoryDAO() { return gameHistoryDAO; }
    public GameEngine getGameEngine() { return gameEngine; }
    public LeaderboardService getLeaderboardService() { return leaderboardService; }

}
