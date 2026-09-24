package com.example.kbo.data.dao;

public interface GameScheduleDAO {

    // 원하는 날, 팀의 경기 일정
    GameScheduleDAO selectGameSchedule(String day, String home_team);

}
