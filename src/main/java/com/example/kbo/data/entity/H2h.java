package com.example.kbo.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "h2h")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class H2h {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "h2h_opponent")
    private String opponent;

    private String win;
    private String  draw;
    private String lose;
    private String  rate;

    public H2h(String opponent, String win, String draw, String lose, String rate) {
        this.opponent = opponent;
        this.win = win;
        this.draw = draw;
        this.lose = lose;
        this.rate = rate;
    }
}
