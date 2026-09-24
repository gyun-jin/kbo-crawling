package com.example.kbo.service;

import com.example.kbo.data.entity.GameSchedule;
import com.example.kbo.data.repository.GameScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;
import java.util.regex.*;

@Service
@RequiredArgsConstructor
public class GameScheduleService {

    private final GameScheduleRepository gameScheduleRepository;

    // 크롤링 데이터 받기
    public Map<String, List<GameSchedule>> getGameScheduleAll(String url) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");

        WebDriver driver = new ChromeDriver(options);

        // 2차원 해쉬맵을 만들어서 날짜 별로 홈팀, 원정팀, 점수 등을 저장
        Map<String, List<GameSchedule>> scheduleMap = new HashMap<>();

        try {
            driver.get(url);


            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("tblScheduleList")));
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                    By.cssSelector("#tblScheduleList tbody tr"), 0));

            WebElement table = driver.findElement(By.id("tblScheduleList"));
            WebElement tbody = table.findElement(By.tagName("tbody"));
            List<WebElement> rows = tbody.findElements(By.tagName("tr"));

            String day = null;
            Pattern pattern = Pattern.compile("([^\\d]+)(\\d+)\\s*vs\\s*(\\d+)([^\\d]+)");

            for (WebElement tr : rows) {
                List<WebElement> tds = tr.findElements(By.tagName("td"));
                if (tds.isEmpty()) continue;

                int startIdx = 0;
                String firstClass = tds.get(0).getAttribute("class");
                if (firstClass != null && firstClass.contains("day")) {
                    day = tds.get(0).getText().trim();
                    startIdx = 1;
                }

                if (tds.size() - startIdx < 2) continue;

                String timeStr = tds.get(startIdx).getText().trim();
                String play    = tds.get(startIdx + 1).getText().trim();

                String awayTeam  = "";
                String homeTeam  = "";
                int awayScore = 0;
                int homeScore = 0;


                Matcher matcher = pattern.matcher(play);
                if (matcher.find()) {
                    awayTeam  = matcher.group(1).trim();
                    awayScore = Integer.parseInt(matcher.group(2).trim());
                    homeScore = Integer.parseInt(matcher.group(3).trim());
                    homeTeam  = matcher.group(4).trim();
                }


                String place  = (tds.size() > startIdx + 2)
                        ? tds.get(tds.size() - 2).getText().trim()
                        : "";
                String remark = (tds.size() > startIdx + 1)
                        ? tds.get(tds.size() - 1).getText().trim()
                        : "";

                GameSchedule game = new GameSchedule(
                        day, timeStr,
                        awayTeam, awayScore,
                        homeTeam, homeScore,
                        place, remark
                );

                scheduleMap.computeIfAbsent(day, k -> new ArrayList<>()).add(game);
                gameScheduleRepository.save(game);
            }
        } finally {
            driver.quit();
        }

        return scheduleMap;
    }
}
