package com.example.kbo.controller;

import com.example.kbo.data.entity.GameSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.kbo.service.GameScheduleService;

import java.util.*;

@RestController
@RequestMapping("/gameSchedule")
public class GameScheduleController {

    private final GameScheduleService gameScheduleService;

    @Autowired
    public GameScheduleController(GameScheduleService gameScheduleService) {this.gameScheduleService = gameScheduleService;}

    // 전체 일정 조회
    @GetMapping
    public Map<String, List<GameSchedule>> getGameSchedule() {
        String gameScheduleUrl =  "https://www.koreabaseball.com/Schedule/Schedule.aspx";

        return gameScheduleService.getGameScheduleAll(gameScheduleUrl);
    };
}
