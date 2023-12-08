# Compare Bulk Insert With JPA and JdbcTemplate

## 두 orm 모두 Bulk Insert 를 지원 하는데, 과연 둘 중 뭐가 더 나을까?

그 해답을 찾기 위해 프로젝트를 만들었습니다.
<br/>

- 스펙

| 항목         | 버전    |
|------------|-------|
| springboot | 3.0.5 |
| java       | 21    |
| gradle     | 8.4   |

- 데이터 베이스 
  - h2
  - MySQL
  - PostgreSQL

- 실행시 고려사항
  - common.yml 에 기재된 bulk size 와 batch size 를 적절히 선택해 준다.
    - bulk size : 몇 건의 데이터를 입려할 것인가.
    - batch size : JdbcTemplate 사용시 데이터를 몇 건씩 나누어서 보낼 것인가.
- 실행 방법
  - ./gradlew clean bootJar
  - java -jar -Dspring.profiles.active={원하는 프로파일} -Dserver.port={원하는포트}  build/libs/BulkTest-0.0.1-SNAPSHOT.jar 