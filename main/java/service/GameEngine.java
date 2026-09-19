package service;

import concurrency.PlayerAnswerTask;
import exception.QuestionFetchException;
import model.Player;
import model.Question;
import network.TriviaApiClient;
import util.LocalQuestionBankLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameEngine {
    private GameEngine() {
    }

    private final static GameEngine gameEngine = new GameEngine();

    public static GameEngine gameEngine() {
        return gameEngine;
    }

    public int playSingleRound() {
        int score = 0;
        List<Question >questions = getQuestions();
        for (Question question : questions) {
            IO.println("Question: " + question.getQuestion());

            String answer = IO.readln();

            if (question.checkAnswer(answer)) {
                score++;
            }
        }
        return score;
    }

    public Map<Player, Integer> playMultiplayerRound(List<Player> players) throws InterruptedException {
        Map<Player, Integer> scores = new HashMap<>();
        List<Question> questions = getQuestions();
        for (Player player : players) {
            scores.put(player, 0);
        }

        for (Question question : questions) {
            List<PlayerAnswerTask> tasks = new ArrayList<>();
            List<Thread> threads = new ArrayList<>();


            for (Player player : players) {
                PlayerAnswerTask task = new PlayerAnswerTask();
                task.setQuestion(question);
                task.setPlayer(player);

                Thread thread = new Thread(task);
                thread.start();
                threads.add(thread);
                tasks.add(task);
            }
            for (Thread thread : threads) {
                thread.join();
            }

            for (PlayerAnswerTask task : tasks) {
                if (task.isIscorrect()) {
                    Player player = task.getPlayer();
                    scores.put(player, scores.get(player) + 1);
                }
            }
        }
        return scores;
    }
    private List<Question> getQuestions() {
        try {
            return new TriviaApiClient().getQuestions();
        } catch (QuestionFetchException e) {
            IO.println("API not available, falling back to local bank");
            try {
                return new LocalQuestionBankLoader().loadQuestions();
            } catch (IOException ex) {
                throw new RuntimeException("Both API and local bank failed", ex);
            }
        }
    }
}
