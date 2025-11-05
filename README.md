# game-character (RTS Game Character Simulation)

이 프로젝트는 중세 시대를 배경으로 하는 RTS 게임에서 캐릭터(Unit)들의 이동 및 공격 행동을 객체지향적으로 구현한 Kotlin + Gradle 기반 프로그램입니다.

---

## 🎮 캐릭터 구성

| 캐릭터 | 이동 방식 | 공격 여부 | 공격 방식 | 비고 |
|-------|----------|-----------|-----------|------|
| Knight | 말을 타고 이동 | 가능 | 창으로 근접 공격 (공중 공격 불가) | Shuttle 탑승 가능 |
| Archer | 걸어서 이동 | 가능 | 화살 공격 (지상 / 공중 모두 공격 가능) | Shuttle 탑승 가능 |
| Griffin | 날아서 이동 | 가능 | 하늘에서 번개 공격 (공중 대상 공격 불가) | Shuttle 탑승 불가 |
| Shuttle | 날아서 이동 | 불가능 | 없음 | Knight/Archer 최대 8기 탑승 가능 |

---

## 🏛 설계 구조

- 모든 캐릭터는 `Unit` 추상 클래스를 상속
- 공격 가능한 유닛만 `Attackable` 인터페이스 구현
- Shuttle은 공격 기능이 없고, 대신 수송 기능(board / unloadAll) 구현

