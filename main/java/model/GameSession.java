package model;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class GameSession implements Serializable {
    private List<Question> questions;
    private List<Player> players;
    private int countOfQuestions;
    private String category;
    private int timePerQuestion;
    private Question.Difficulty  difficulty;
}
