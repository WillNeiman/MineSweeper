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
### 받은 리뷰
- 인터셉터나 필터를 이용해보면 컨트롤러의 책임을 덜어낼 수 있을거같다
- RequestParam 받을땐 PrimitiveType (Interger, Long) 등을 이용하면 좋을거같다. (예컨데 boardSize, mineQuantity 에 대한 유효성을 검증 하는 방어로직이 있으면 좋음)
- System.out.print 보단 logback 설정같은것도 알아보면 좋음
- 절차지향적인데 객체지향적으로 짜봐도 좋을법하다 ~
- 예컨데 MineBoard 가 책임을 갖도록 할 수 있는 일이 많아보임
- 인터셉터나 필터는 뭐냐면 Session 을 컨트롤러에서 처리하기보다 인터셉터로 빼두면 굳이 컨트롤러에서 세션을 직접 다루지않아도 되기 떄문 
- 참고 https://madplay.github.io/post/why-constructor-injection-is-better-than-field-injection
