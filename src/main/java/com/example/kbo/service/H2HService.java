package com.example.kbo.service;

import com.example.kbo.data.dto.H2hDTO;
import com.example.kbo.data.entity.H2h;
import com.example.kbo.data.repository.H2hRepository;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;

@Service
@RequiredArgsConstructor
public class H2HService {

    private final H2hRepository h2hRepository;

    public List<H2h> getH2h(String url) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");     // 브라우저 창 안 띄우고 실행
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");

        WebDriver driver = new ChromeDriver(options);

        List<H2h> result = new ArrayList<>();

        try {
            driver.get(url);

            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.table_type03 table")));

            List<WebElement> tables = driver.findElements(By.cssSelector("div.box_cont div.table_type03 table"));
            WebElement targetTable = null;

            for (WebElement tbl : tables) {
                List<WebElement> ths = tbl.findElements(By.tagName("th"));
                if (ths.size() >= 5 &&
                        ths.get(0).getText().equals("대결") &&
                        ths.get(1).getText().equals("승") &&
                        ths.get(2).getText().equals("무") &&
                        ths.get(3).getText().equals("패") &&
                        ths.get(4).getText().equals("승률")) {
                    targetTable = tbl;
                    break;
                }
            }

            if (targetTable == null) {
                return result; // 빈 리스트
            }

            WebElement tbody = targetTable.findElement(By.tagName("tbody"));
            List<WebElement> rows = tbody.findElements(By.tagName("tr"));

            for (WebElement row : rows) {
                List<WebElement> tds = row.findElements(By.tagName("td"));
                if (tds.size() < 5) continue;

                String opponent = tds.get(0).getText().trim();
                String win = tds.get(1).getText().trim();
                String draw = tds.get(2).getText().trim();
                String lose = tds.get(3).getText().trim();

                String rate;
                try {
                    WebElement rateDiv = tds.get(4).findElement(By.className("t_point"));
                    rate = rateDiv.getText().trim();
                } catch (Exception e) {
                    rate = tds.get(4).getText().trim();
                }

                // Entity 생성
                H2h h2h = new H2h(opponent, win, draw, lose, rate);
                h2hRepository.save(h2h);
                result.add(h2h);


            }

        } finally {
            driver.quit();
        }

        return result;
    }
}
