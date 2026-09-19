package network;

import model.Question;
import networkDTO.TriviaQuestionDto;
import pattern.QuestionFactory;

import java.util.*;

public class QuestionMapper {
    public static Question toQuestion(TriviaQuestionDto dto){
        String id = UUID.randomUUID().toString();
        Question.Difficulty difficulty = Question.Difficulty.valueOf(dto.getDifficulty().toUpperCase());

        if (dto.getType().equalsIgnoreCase("boolean")){
            boolean correctAnswer = Boolean.parseBoolean(dto.getCorrectAnswer());
            return QuestionFactory.createQuestion("true_false",id,dto.getQuestion(),difficulty,null,0,correctAnswer);

        }
        else {
            List<String> allAnswers = new ArrayList<>(dto.getIncorrectAnswers());
            allAnswers.add(dto.getCorrectAnswer());
            Collections.shuffle(allAnswers);
            int correctAnswerIndex = allAnswers.indexOf(dto.getCorrectAnswer());

            return QuestionFactory.createQuestion("multiple_choice",id,dto.getQuestion(), difficulty,allAnswers,correctAnswerIndex,false);

        }
    }
}
