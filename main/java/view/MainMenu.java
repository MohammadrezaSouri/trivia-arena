package view;

import model.GameResult;
import model.GameSession;
import model.Player;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainMenu {
    private final AppContext ctx;

    public MainMenu(AppContext ctx) {
        this.ctx = ctx;
    }

    public void start() {
        boolean flag = true;
        while (flag) {
            IO.println("\n=== TRIVIA ARENA ===");
            IO.println("1. Single Player");
            IO.println("2. Multiplayer");
            IO.println("3. Leaderboard");
            IO.println("4. Exit");
            IO.println("Choose an option: ");

            String choice = IO.readln();

            switch (choice) {
                case "1" -> handleSinglePlayer();
                case "2" -> {
                    try {
                        handleMultiplayer();
                    } catch (InterruptedException e) {
                        IO.println("Game interrupted.");
                    }
                }
                case "3" -> handleLeaderboard();
                case "4" -> {
                    IO.println("Goodbye!");
                    flag = false;
                }
                default -> IO.println("Invalid option, try again.");
            }
        }
    }

    private void handleSinglePlayer() {
        IO.println("Enter your name: ");
        String name = IO.readln();

        Player player = new Player();
        player.setUserName(name);

        try {
            ctx.getPlayerDAO().save(player);
            Player savedPlayer = ctx.getPlayerDAO().findByUserName(name);

            int score = ctx.getGameEngine().playSingleRound();
            IO.println("Your score: " + score);

            savedPlayer.setScore(score);
            GameSession session = GameSession.builder()
                    .category("Any")
                    .difficulty(null)
                    .countOfQuestions(2)
                    .timePerQuestion(30)
                    .build();

            GameResult result = new GameResult(0, List.of(savedPlayer), List.of(), session);
            Map<Player, Integer> scores = Map.of(savedPlayer, score);
            ctx.getGameHistoryDAO().save(result, scores);

        } catch (SQLException e) {
            IO.println("Database error: " + e.getMessage());
        }
    }

    private void handleMultiplayer() throws InterruptedException {
        IO.println("Enter number of players: ");
        int count = Integer.parseInt(IO.readln());

        List<Player> players = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            IO.println("Enter name for player " + (i + 1) + ": ");
            String name = IO.readln();
            Player player = new Player();
            player.setUserName(name);
            try {
                ctx.getPlayerDAO().save(player);
                Player saved = ctx.getPlayerDAO().findByUserName(name);
                players.add(saved);
            } catch (SQLException e) {
                IO.println("Database error: " + e.getMessage());
            }
        }

        Map<Player, Integer> scores = ctx.getGameEngine().playMultiplayerRound(players);

        IO.println("\n=== RESULTS ===");
        scores.forEach((player, score) ->
                IO.println(player.getUserName() + ": " + score));

        try {
            GameSession session = GameSession.builder()
                    .category("Any")
                    .difficulty(null)
                    .countOfQuestions(2)
                    .timePerQuestion(30)
                    .build();

            GameResult result = new GameResult(0, players, List.of(), session);
            ctx.getGameHistoryDAO().save(result, scores);
        } catch (SQLException e) {
            IO.println("Database error: " + e.getMessage());
        }
    }

    private void handleLeaderboard() {
        try {
            ctx.getGameHistoryDAO().printLeaderboard();
        } catch (SQLException e) {
            IO.println("Database error: " + e.getMessage());
        }
    }

}
