## 이 학습 테스트의 목적

### saveAll이 느린 이유에 대해서 탐구

- `saveAll()` 메서드는 기본적으로 반복문을 돌면서 하나 씩 save하는 로직으로 구성되어 있다.
- JPA의 쓰기지연 동작을 통해서 커밋 이전까지 `INSERT를` 단건씩 하지않고 모아서 처리할 수 있는 방법이 있다.
- 하지만 ID 생성 전략이 `IDENTITY인` 경우에는 이것이 동작하지 않는다.
- `IDENTITY` 전략은 오롯이 ID의 생성을 DB에 맡기기 떄문에 `persist()` 이후에 `flush()`가 일어나고 이를 다시 조회해온다.
- 따라서 ID 값이 미리 채워져있는 경우를 고려해야 Bulk Insert가 가능하다.

### IDENTITY가 아닌 UUID Generator를 적용

- `@UuidGenerator(style = UuidGenerator.Style.RANDOM)`를 ID 생성 전략으로 채택하고 `saveAll()` 테스트를 수행
- 그 결과 1000개 저장시, IDENTITY 전략의 엔티티는 2210ms, UUID Generator로 생성한 엔티티는 1194ms로 시간이 단축되었다.
- 10000개를 테스트 했을 때는 각각 12,889ms, 8,893ms가 나왔다.
- 1000개를 기준으로는 46%가 개선되었고 10000개 기준으로는 31% 개선

### JdbcTemplate을 이용한 Bulk Insert

- `JdbcTemplate.batchUpdate()`를 이용하여 Bulk Insert를 수행
    - SQL 템플릿을 전달하고 내부적으로 `PreparedStatement` 객체를 단 한 번 생성한다. 이는 동일한 SQL을 반복 실행할 때 SQL 파싱 부하를 줄인다.
    - batchUpdate에 함께 전달된 파라미터 리스트를 순회하면서 각 파라미터 세트를 `PreparedStatement`에 바인딩한다. SQL을 즉시 실행시키는 것이 아니다!!
    - 모든 파라미터 세트가 바인딩된 후, `PreparedStatement`의 `executeBatch()` 메서드를 호출하여 일괄적으로 실행한다.

### 결론
- `saveAll()` 메서드는 IDENTITY 전략을 사용하지 않는다면 JPA의 쓰기 지연 동작을 통해 Bulk Insert가 가능하다.
- 하지만 운영 중인 테이블의 전략을 바꾸는 것은 쉽지 않으므로 JdbcTemplate을 이용한 Bulk Insert가 더 유용할 수 있다.
- JdbcTemplate을 사용한 Bulk Insert 수행 시, JPA의 영속성 컨텍스트를 무시하므로 단일 작업만 하거나 영속성 컨텍스트 클리닝이 필요.