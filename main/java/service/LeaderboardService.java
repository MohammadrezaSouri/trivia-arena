package service;

import model.GameResult;
import model.Player;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LeaderboardService {
    private static LeaderboardService leaderboardService =  new LeaderboardService();
    public static LeaderboardService getInstance() {
        return leaderboardService;
    }

    public double averageScore(List<GameResult> results) {
        return results.stream()
                .flatMap(result -> result.getPlayers().stream())
                .mapToInt(Player::getScore)
                .average()
                .orElse(0.0);
    }

    public Player toPlayer(List<GameResult> results) {
        return results.stream()
                .flatMap(result -> result.getPlayers().stream())
                .max((p1, p2) -> Integer.compare(p1.getScore(), p2.getScore()))
                .orElse(null);
    }

    public Map<String, Long> participationCount(List<GameResult> results) {
        return results.stream()
                .flatMap(result -> result.getPlayers().stream())
                .collect(Collectors.groupingBy(Player::getUserName, Collectors.counting()));
    }

}
