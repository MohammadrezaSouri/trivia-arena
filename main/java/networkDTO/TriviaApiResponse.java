package networkDTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TriviaApiResponse {
    private int responseCode;
    private List<TriviaQuestionDto> results;
}
