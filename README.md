# 📋 콘솔 게시판 애플리케이션

콘솔 환경에서 동작하는 Java 기반 게시글 CRUD 애플리케이션입니다.  
URL 쿼리스트링 방식의 명령어 파싱 구조를 학습 목적으로 구현하였습니다.

---

## 🛠 기술 스택

- **Language**: Java
- **Build Tool**: Maven / Gradle
- **Test**: JUnit 5, AssertJ

---

## 📁 프로젝트 구조

```
src/
├── main/java/org/example/
│   ├── Main.java                        # 진입점
│   ├── App.java                         # 앱 실행 루프 및 명령어 라우팅
│   ├── Rq.java                          # 명령어 파싱 (액션명 + 파라미터)
│   ├── Article.java                     # 게시글 도메인 모델
│   ├── domain/article/
│   │   └── ArticleController.java       # 게시글 CRUD 처리
│   └── domain/system/
│       └── SystemController.java        # 시스템 명령 처리 (종료 등)
└── test/java/org/example/
    ├── RqTest.java                       # Rq 파싱 단위 테스트
    └── domain/article/
        └── ArticleControllerTest.java   # ArticleController 단위 테스트
```

---

## ⚙️ 실행 방법

```bash
# 프로젝트 클론
git clone https://github.com/predevho/AIBE6_Project2_Team3.git
cd AIBE6_Project2_Team3

# 빌드 및 실행 (Maven 기준)
mvn compile
mvn exec:java -Dexec.mainClass="org.example.Main"
```

---

## 💻 사용법

프로그램 실행 후 아래 명령어를 입력합니다.  
명령어 형식은 `액션명?파라미터=값&파라미터=값` 형태입니다.

| 명령어 | 설명 | 예시 |
|--------|------|------|
| `write` | 게시글 작성 | `write` |
| `list` | 게시글 목록 조회 | `list` |
| `detail?id=1` | 게시글 상세 조회 | `detail?id=1` |
| `update?id=1` | 게시글 수정 | `update?id=1` |
| `delete?id=1` | 게시글 삭제 | `delete?id=1` |
| `exit` | 프로그램 종료 | `exit` |

### 실행 예시

```
명령어: write
제목 : 안녕하세요
내용 : 첫 번째 게시글입니다
=> 게시글이 등록되었습니다.

명령어: list
번호 | 제목 | 등록일
-----------------------------
1 | 안녕하세요 | 2025-04-17

명령어: detail?id=1
번호 : 1
제목 : 안녕하세요
내용 : 첫 번째 게시글입니다
등록일 : 2025-04-17
```

---

## 🧪 테스트 실행

```bash
mvn test
```

### 테스트 목록

**RqTest** - 명령어 파싱 검증
- 액션명 파싱
- 단일/복수 파라미터 파싱
- 빈 값 처리 및 기본값 반환
- 정수형 파라미터 파싱 및 예외 처리

**ArticleControllerTest** - 게시글 CRUD 검증
- 게시글 작성 시 제목, 내용, ID 저장 확인
- ID 자동 증가 확인
- ID로 게시글 조회 및 null 반환 확인
- 제목/내용 수정 확인
- 삭제 후 조회 null 반환 확인
- 등록일 null 여부 확인

---

## 📌 주요 설계 포인트

- **Rq 클래스**: HTTP 쿼리스트링과 유사한 방식으로 콘솔 명령어를 파싱하여 액션명과 파라미터를 분리
- **ArticleController**: 게시글 목록을 메모리(ArrayList)로 관리하며 CRUD 기능 제공
- **도메인 분리**: article, system 패키지로 역할 분리
