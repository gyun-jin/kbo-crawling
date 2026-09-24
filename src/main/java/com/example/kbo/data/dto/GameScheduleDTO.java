package com.example.kbo.data.dto;

public class GameScheduleDTO {
    private String date;
    private String time;
    private String awayTeam;
    private String homeTeam;
    private String awayScore;
    private String homeScore;
    private String stadium;
    private String note;

    public GameScheduleDTO(String date, String time, String awayTeam, String awayScore,
                        String homeTeam, String homeScore, String stadium, String note) {
        this.date = date;
        this.time = time;
        this.awayTeam = awayTeam;
        this.awayScore = awayScore;
        this.homeTeam = homeTeam;
        this.homeScore = homeScore;
        this.stadium = stadium;
        this.note = note;
    }

    // JSON 변환을 위한 getter
    public String getDate() { return date; }
    public String getTime() { return time; }
    public String getAwayTeam() { return awayTeam; }
    public String getHomeTeam() { return homeTeam; }
    public String getAwayScore() { return awayScore; }
    public String getHomeScore() { return homeScore; }
    public String getStadium() { return stadium; }
    public String getNote() { return note; }
}
