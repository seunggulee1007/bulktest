# Compare Bulk Insert With JPA and JdbcTemplate

## 1. 두 orm 모두 Bulk Insert 를 지원 하는데, 과연 둘 중 뭐가 더 나을까?

그 해답을 찾기 위해 프로젝트를 만들었습니다.
<br/>

## 2. 개발 환경
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
- 개발 시 고려사항
  - MySql 과 Postgres의 id strategy 전략 중 identity 는 jpa 의 bulk insert 를 지원해 주지 않으므로 해당 전략은 사용하지 않는다.
  - MySql의 경우 `rewriteBatchedStatements`의 옵션에 따라 성능 차이가 있으니 해당 옵션을 `true` 혹은 `false` 로 변경하며 테스트 진행

- 실행시 고려사항
  - common.yml 에 기재된 bulk size 와 batch size 를 적절히 선택해 준다.
    - bulk size : 몇 건의 데이터를 입려할 것인가.
    - batch size : JdbcTemplate 사용시 데이터를 몇 건씩 나누어서 보낼 것인가. ( oom 방지 )
- 실행 방법
  - ./gradlew clean bootJar
  - java -jar -Dspring.profiles.active={원하는 프로파일} -Dserver.port={원하는포트}  build/libs/BulkTest-0.0.1-SNAPSHOT.jar 

## 3. 실행 결과 공유
| dbms | JdbcTemplate | JPA |
| ----- | ------------ | ------------|
| MySql | 74 | 799 |
| H2 |58 | OOM |
|PostgreSQL | 81 | 800 |

### 3-1. postgres 600만 건
![600만건 postgres](https://github.com/seunggulee1007/bulktest/assets/32692807/e1353763-88a1-469c-963c-352e3dc29480)

### 3-2. MySQL 600만 건
![600만건 MySQL](https://github.com/seunggulee1007/bulktest/assets/32692807/03692cdc-9d88-429a-b510-a3e3f520548a)


### 3-3. H2 inMemory 600만 건 
![스크린샷 2023-12-08 오후 8 30 15](https://github.com/seunggulee1007/bulktest/assets/32692807/aec63848-4081-4b6a-9e64-5b8df6069baa)

## 4. rewriteBatchedStatements 옵션 여부

### 4-1. MySQL rewriteBatchedStatements 옵션 false 일 경우 100만건
<img width="1134" alt="스크린샷 2023-12-07 오후 3 37 54" src="https://github.com/seunggulee1007/bulktest/assets/32692807/d0688844-8642-4e9c-9a3e-5da12a15a597">

### 4-2. MySQL rewriteBatchedStatements 옵션 true 일 경우 100만건
<img width="1102" alt="스크린샷 2023-12-07 오후 3 48 47" src="https://github.com/seunggulee1007/bulktest/assets/32692807/29ad5a61-48d5-42d3-a7f7-8cbc2bf51acc">

