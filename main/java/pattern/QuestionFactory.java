package pattern;

import model.MultipleChoiceQuestion;
import model.Question;
import model.TrueFalseQuestion;

import java.util.List;

public class QuestionFactory {
    private QuestionFactory() {}
    private final static QuestionFactory questionFactory = new QuestionFactory();
    public static QuestionFactory getInstance() {
        return questionFactory;
    }

    public static Question createQuestion(String type, String id, String question, Question.Difficulty difficulty, List<String> answers, int correctAnswerIndex, boolean trueFalseAnswers) {
        switch (type) {
            case "multiple_choice":
                MultipleChoiceQuestion answer = new MultipleChoiceQuestion(id, question, difficulty, answers, correctAnswerIndex);
                return answer;
            case "true_false" :
                TrueFalseQuestion trueFalseQuestion = new TrueFalseQuestion(id, question, difficulty, trueFalseAnswers);
                return trueFalseQuestion;
            default:
                throw new IllegalArgumentException("Invalid question type");
        }
    }
}
