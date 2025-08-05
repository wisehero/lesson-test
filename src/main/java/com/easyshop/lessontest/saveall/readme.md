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