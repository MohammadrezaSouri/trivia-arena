package exception;

public class QuestionFetchException extends Exception {

    public QuestionFetchException(String message) {
        super(message);
    }
    public QuestionFetchException(String message, Throwable cause) {
        super(message, cause);

    }
}
