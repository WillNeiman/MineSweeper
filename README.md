# 지뢰밟기(MineSweeper)
### 프로젝트 개요
- BFS 알고리즘을 활용한 간단한 지뢰찾기 게임 구현
- B사 코딩테스트 문제를 풀다가 재밌어보여서 시작함
### 프로젝트 기간
- 2023/05/07 ~ 2023/05/10
### 프로젝트 멤버
- Will Neiman
### 프로젝트 구성
- IntelliJ IDEA
- Java 11
- Spring Boot 2.7.11
- - SpringWeb
- - Thymeleaf
- HTML / CSS / JavaScript
---
# 플레이
https://minesweeper-willneiman.koyeb.app/
---
### 트러블슈팅
1. 개발자 도구를 통해 boardSize 및 mineQuantity를 비정상적인 값으로 설정할 수 있는 문제
2. 같은 방식으로 x, y 좌표를 게임범위를 초과하여 설정할 수 있는 문제
- 기존에 RequestParam으로 받던 int타입 데이터를 래퍼 타입 Integer로 받도록 변경
- - 컨트롤러에서 유효성 검사가 가능해짐
- - 브라우저를 통한 어뷰징 방지
- ExceptionHandler 도입
- - 예외를 컨트롤러에서 분리해 별도 클래스에서 관리
- - 예외 관리 범위를 전역으로 확대함
- - 예외 페이지서 stack trace 생략하여 소스코드 숨김
