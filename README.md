![image](https://github.com/WillNeiman/MineSweeper/assets/122020165/84f0d55c-c361-4816-b262-20da91f87856)

## [지뢰밟기](https://minesweeper-willneiman.koyeb.app)

2023.5.7 - 5.10 (4일) | 플레이 가능

- BFS 알고리즘을 활용한 간단한 지뢰찾기 게임 구현
- B사 코딩테스트 문제를 풀다가 재밌어보여서 시작함

**Play** https://minesweeper-willneiman.koyeb.app

---

## **기술스택**

**Frontend** : html5, css, JavaScript

**Backend** :

- 언어: Java (버전 11)
- 프로젝트 관리 도구 : Gradle-Groovy
- 웹 프레임워크 : **Spring Boot** (버전 2.7.12)
- 패키징 형식 : Jar
- 클라우드 서비스 : **Koyeb**
- 의존성 : Spring Web, Lombok, Spring Boot DevTools, Spring AOP, Thymeleaf
- 아키텍쳐 :
    - Client → Server Side Rendering
    - API server → Spring framework
    - CI/CD → Koyeb
- VCS : Git, Github, Github Desktop

---

## 작업 영역

- Backend (Java, Spring Boot)
    - 게임 진행 로직 구현
    - Exception Handler, Aspect로 예외 핸들링
- Frontend (Java, Thymeleaf)
    - Thymeleaf Template Engine으로 뷰 구현
- Backend Server Deploy (Koyeb)
    - Koyeb 플랫폼을 사용하여 애플리케이션 실시간 통합 및 배포

---

## 기획 과정

**코딩 테스트 불합격**

- 하룻강아지 범 무서운 줄 모른다고, 알고리즘 공부 3일차에 B사 코딩테스트에 지원 → 아쉽게 불합격 ~~하루 동안 울었음~~
- 출제된 문제 중 가장 재미있었던 BFS 알고리즘에 아이디어를 얻어, 해당 알고리즘을 응용해 게임으로 만들어보자고 결심함

**실제로 작동하는 프로젝트를 배포하고 싶다**

- 로컬에서만 돌아가는 아기자기한 것들만 만들어보다가 새로운 기술에 대한 욕심이 생겼음
- Heroku가 그렇게 유명하다던데? 하지만 유료화가 됐다!
- 대안을 찾던 중 Koyeb이라는 플랫폼을 발견.
- 간단한 절차로 Github Repository 내의 원하는 Branch의 **자동 빌드, 자동 배포**를 구현할 수 있었음.

## 트러블슈팅

**만들다 보니 절차지향**

게임이 진행되는 순서와 기능의 작동 구조에만 집중하다보니 모든 클래스가 상호 의존적으로 작성됨

- 게임 보드를 만드는 로직으로부터 MineBoard(지뢰판)의 속성을 DTO로 분리
- 데이터 가공 로직을 Controller에서 분리하여 Service로 몰아 넣음

**허술한 보안처리**

개발자 도구를 통해 boardSize 및 mineQuantity를 비정상적인 값으로 설정할 수 있는 문제

같은 방식으로 x, y 좌표를 게임범위를 초과하여 설정할 수 있는 문제

- 기존에 RequestParam으로 받던 int타입 데이터를 래퍼 클래스 Integer로 받도록 변경하고 컨트롤러에서 유효성 검증
- 브라우저를 통한 어뷰징 방지
- ExceptionHandler 도입, 예외를 컨트롤러에서 분리해 별도 클래스에서 관리
- 예외 페이지서 stack trace 생략하여 소스코드 노출 방지

## [**지뢰 밟으러 가기**](https://minesweeper-willneiman.koyeb.app/) ← 플레이!
