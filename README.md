# KBO Crawling

KBO 경기 일정과 삼성 라이온즈 상대전적을 크롤링해 MariaDB에 저장하고, REST API로 조회하는 Spring Boot 프로젝트입니다.

## 주요 기능

- KBO 공식 사이트 경기 일정/결과 크롤링
- 경기 날짜, 시간, 원정팀, 홈팀, 점수, 구장, 비고 저장
- STATIZ 삼성 라이온즈 상대전적 크롤링
- 상대팀별 승, 무, 패, 승률 저장
- JPA Repository를 통한 MariaDB 저장

## 기술 스택

- Java 17
- Spring Boot 3.5.4
- Spring Data JPA
- MariaDB
- Selenium
- Gradle

## API

| Method | URL | 설명 |
| --- | --- | --- |
| GET | `/gameSchedule` | KBO 경기 일정을 크롤링하고 날짜별 경기 목록을 반환합니다. |
| GET | `/samsungH2h` | 삼성 라이온즈 상대전적을 크롤링하고 목록을 반환합니다. |

## 실행 방법

MariaDB 실행 후 아래 환경변수를 필요에 맞게 설정합니다.

```bash
export DB_URL="jdbc:mariadb://127.0.0.1:3306/kbo?createDatabaseIfNotExist=true&serverTimezone=UTC&characterEncoding=UTF-8"
export DB_USERNAME="root"
export DB_PASSWORD="your-password"
```

애플리케이션 실행:

```bash
./gradlew bootRun
```

## 참고

- CSV 파일은 저장소에 포함하지 않습니다.
- 크롤링 대상 사이트의 HTML 구조가 변경되면 파싱 로직 수정이 필요할 수 있습니다.
