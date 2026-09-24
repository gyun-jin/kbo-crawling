package com.example.kbo.data.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "game_schedule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "game_day")
    private String day;   // 경기 날짜 (DATE 타입)

    @Column(name = "game_time")
    private String time;       // 경기 시간 (예: 18:30)

    private String awayTeam;   // 원정팀

    private int awayScore;  // 원정팀 점수

    private String homeTeam;   // 홈팀

    private int homeScore;  // 홈팀 점수

    private String stadium;    // 경기장

    private String remark;     // 비고


    public GameSchedule(String day, String time, String awayTeam, int awayScore,
                        String homeTeam, int homeScore, String stadium, String remark) {
        this.day = day;
        this.time = time;
        this.awayTeam = awayTeam;
        this.awayScore = awayScore;
        this.homeTeam = homeTeam;
        this.homeScore = homeScore;
        this.stadium = stadium;
        this.remark = remark;
    }

}
