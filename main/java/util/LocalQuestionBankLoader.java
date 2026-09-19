package util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Question;
import network.QuestionMapper;
import networkDTO.TriviaQuestionDto;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class LocalQuestionBankLoader {
    public List<Question> loadQuestions() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/local-questions.json");
        ObjectMapper objectMapper = new ObjectMapper();
        List<TriviaQuestionDto> dtos = objectMapper.readValue(
                inputStream,
                new TypeReference<List<TriviaQuestionDto>>() {}
        );
        List<Question> questions = new ArrayList<>();
        for (TriviaQuestionDto dto : dtos) {
            questions.add(QuestionMapper.toQuestion(dto));
        }
        return questions;
    }
}
