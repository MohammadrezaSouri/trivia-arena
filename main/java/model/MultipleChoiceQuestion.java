package model;

import annotation.Information;
import lombok.*;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class MultipleChoiceQuestion extends Question{
    private List<String> answers = new ArrayList<>();
    private int correctAnswer;
    @Information(information = "A question with multiple options to choose from",difficulty = "Varies")
    public MultipleChoiceQuestion(String id, String question, Difficulty difficulty, List<String> answer, int correctAnswer) {
        super(id, question, difficulty);
        this.answers =  answer;
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean checkAnswer(String answer) {
        int a =  Integer.parseInt(answer);
        return a == correctAnswer;
    }
}
