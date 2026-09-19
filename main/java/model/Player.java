package model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Player implements Serializable {
    private int id;
    private String userName;
    private int score;

}
