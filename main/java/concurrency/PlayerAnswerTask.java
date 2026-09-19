package concurrency;

import lombok.Getter;
import lombok.Setter;
import model.Player;
import model.Question;

@Getter
@Setter
public class PlayerAnswerTask implements Runnable {

    private Question question;
    private Player player;
    private boolean iscorrect;

    @Override
    public void run() {
        String answer = IO.readln();
        iscorrect = question.checkAnswer(answer);
    }

}
