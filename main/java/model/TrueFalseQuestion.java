package model;

import annotation.Information;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class TrueFalseQuestion extends Question{

    private boolean trueFalseAnswer;
    @Information(information = "A question with multiple options to choose from", difficulty = "Varies")
    public TrueFalseQuestion(String id, String question, Difficulty difficulty, boolean correctAnswer) {
        super(id, question, difficulty);
        this.trueFalseAnswer = correctAnswer;
    }

    @Override
    public boolean checkAnswer(String answer) {
        boolean e = Boolean.parseBoolean(answer);
        return e == trueFalseAnswer;
    }
}
