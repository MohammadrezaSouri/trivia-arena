package model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class GameResult {
    @Setter(AccessLevel.NONE)
    private int id;
    private List<Player> players;
    private List<Player> winners;
    private GameSession gameSession;
}
