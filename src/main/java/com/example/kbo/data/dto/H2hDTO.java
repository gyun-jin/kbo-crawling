package com.example.kbo.data.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class H2hDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String opponent;
    private String win;
    private String draw;
    private String lose;
    private String rate;

    public H2hDTO(String opponent, String win, String draw, String lose, String rate){
        this.opponent = opponent;
        this.win = win;
        this.draw = draw;
        this.lose = lose;
        this.rate = rate;
    }

    public String getOpponent() { return opponent; }
    public String getWin() { return win; }
    public String getDraw() { return draw; }
    public String getLose() { return lose; }
    public String getRate() { return rate; }
}
