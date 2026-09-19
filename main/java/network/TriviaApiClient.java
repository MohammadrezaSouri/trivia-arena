package network;

import com.fasterxml.jackson.databind.ObjectMapper;
import exception.QuestionFetchException;
import model.Question;
import networkDTO.TriviaApiResponse;
import networkDTO.TriviaQuestionDto;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class TriviaApiClient {
    private final static String API = "https://opentdb.com/api.php?amount=2&encode=url3986";
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public TriviaApiResponse fetchQuestions() throws QuestionFetchException {
        try {

            HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(API)).GET().build();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if (httpResponse.statusCode() != 200) {
                throw new QuestionFetchException("API returned status code " + httpResponse.statusCode());
            }
            String jbody = httpResponse.body();
            return objectMapper.readValue(jbody, TriviaApiResponse.class);
        }
        catch (IOException | InterruptedException e) {
        throw new QuestionFetchException("can't connect to API", e);
        }
    }
    public List<Question> getQuestions() throws QuestionFetchException {
        TriviaApiResponse triviaApiResponse = fetchQuestions();
        List<Question> questions = new ArrayList<>();
        for (TriviaQuestionDto dto : triviaApiResponse.getResults()){
            questions.add(QuestionMapper.toQuestion(dto));
        }
        return questions;
    }
}
