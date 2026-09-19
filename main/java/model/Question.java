package model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
public abstract class Question implements Serializable {
    @Setter(AccessLevel.NONE)
    private String id;
    private String question;
    private Difficulty difficulty;

    public enum Difficulty  {
        EASY
        ,MEDIUM
        ,HARD
    }

    public abstract boolean checkAnswer(String answer);
}
