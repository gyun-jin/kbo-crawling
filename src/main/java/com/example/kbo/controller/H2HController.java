package com.example.kbo.controller;

import com.example.kbo.data.dto.H2hDTO;
import com.example.kbo.data.entity.H2h;
import com.example.kbo.service.H2HService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/samsungH2h") //  상대전적
public class H2HController {
    private final H2HService h2hService;

    @Autowired
    public H2HController(H2HService h2hService) {this.h2hService = h2hService;}

    // 삼성 상대 전적 조회
    @GetMapping
    public List<H2h> getH2h() {
        String h2hurl = "https://statiz.sporki.com/team/?m=team&t_code=1001&year=2025";
        return h2hService.getH2h(h2hurl);
    }

}
