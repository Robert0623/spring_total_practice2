# 스프링 복습을 하면서 주석을 만들고, 주석만 보고 게시판을 만들 수 있도록 훈련

## 07.19, 07.20, 07.21
### 초기세팅
- 설정파일 - pom.xml, root-context.xml, servlet-context.xml 설정
- index.jsp, menu.css 추가 및 view-controller로 등록
- Tomcat 설정 및 실행 확인
- GitHub 연동 및 .gitignore 추가, README 추가

### 홈화면
//[index.jsp]
//session을 시작하지 않도록 하고,
//loginId를 session을 시작하지 않도록 하면서 값을 구한다.
//loginId가 없으면
//	링크는 '/login/login', 있으면 '/login/logout'
//	문자는 'Login', 있으면 ID=아이디가 보이게 한다.

### 회원
//[User.java]
//1. DB를 보고 private으로 iv를 전부 작성.
//2. birth나 reg_date같이 날짜로 받는 것은 Date타입으로.
//3. 기본생성자, 생성자, getter&setter, equals&hashCode, toString을 만든다.
(equals에 reg_date를 빼고, id만 not null 체크)

//[UserDao.java]
//userMapper.xml을 보고 작성. 인터페이스 추출.

//[UserService.java]
//UserDao를 보고 작성. 인터페이스 추출.

### 회원가입 및 회원정보 출력
//[RegisterController.java]
//1. GET, POST를 처리할 메서드를 각각 만든다.
//2. POST를 처리할 메서드는 User를 받아서 유효성 검사를 한다.
//  	이 때 메세지를 보내는데 URLEncoder를 사용한다.
//  	유효성검사를 통과하지 못하면 회원가입화면으로 다시 가게 하고,
//  	유효성검사를 통과하면 회원정보를 보여주도록 한다.
//3. 검증할 객체의 바로뒤에 BindingResult를 붙인다.
//4. @InitBinder를 붙여서 WebDataBinder를 매개변수로 갖는 메서드를 만들어서 타입변환을 위해 CustomEditor를 등록한다.
//5. 컨트롤러에서 isValid메서드를 사용한 유효성체크 대신,
//	 UserValidator 클래스를 작성해서 자동으로 등록한다.(@Initbinder, @Valid) (수동 등록도 가능)
//6. error가 있으면 registerForm으로 가게 한다.
//----아래는 MyBatis로 수정----
//7. UserService를 주입받고 
//8. 검증을 통과하면 UserService의 wrtie를 사용해서, 성공하면 회원정보가 보이게 하고, 통과하지 못하면 다시 회원가입으로 가게 한다.

//[registerForm.jsp]
//form태그에 action, method를 작성하고, onsubmit에 Js로 유효성 검사를 한다.
//1. action은 c:url태그를 사용해서 작성한다.
//	- c:url태그의 기능 - 1. context root를 자동 추가, 2. session id를 자동 추가
//2. 에러메세지를 보여주는 곳은 컨트롤러에서 메세지를 받을 수 있으므로 URLDecoder를 사용한다.
//3. script태그에 Js로 함수를 작성한다.
//	1) id, pwd길이를 체크하는 formCheck함수 작성
//	2) 에러 메세지 출력과 해당 input태그를 선택하는 setMessage함수 작성
//		(font-awesome와 i태그를 사용해서 메세지를 출력하게 해놓음.)
//		(` `안의 ${ }는 HTML의 템플릿 리터럴이므로 EL로 다시 감싸줘야한다.)
//4. form:form태그를 사용해서 Validator의 에러메세지를 출력한다.

//[registerInfo.jsp]
//Model로 user를 받으므로 user를 사용해서 값을 출력한다.

### 유효성검사
//[UserValidator.java]
//Validator를 구현해서 작성한다.
//Controller에서 로컬 Validator로 등록한다.
//error_message.properties에 에러 메세지를 작성한다.

### 로그인
//[LoginController.java]
//1. GET으로 loginForm으로 이동하는 메서드 작성
//2. POST로 작성
//  1. id와 pwd를 loginCheck로 유효성 검사.
//  2-1. id와 pwd가 일치하지 않으면 loginForm으로 리다이렉트하고 에러메세지를 출력.
//  2-2. 일치하면 session에 id를 저장하고, rememberId를 확인.
//     	 rememberId가 true면 쿠키를 생성하고 홈으로 이동
//     	 rememberId가 false면 쿠키를 삭제하고 홈으로 이동
// 				toURL이 null이거나 빈문자열이면 홈으로, 아니면 toURL로 가도록 한다.       
//3. GET으로 로그아웃을 하는 메서드 작성
//	3-1 세션을 종료하고
//	3-2 홈으로 이동
//----아래는 MyBatis로 수정----
//4. UserService를 주입받고, UserService의 read로 loginCheck에서 유효성 검사.

//[loginForm.jsp]
//rememberId에 체크하고 로그인에 성공했을 때 아이디가 나오고 아이디기억이 체크되어있도록 한다
//rememberId에 체크를안하고 로그인에 성공하면 아이디칸을 비우고, 아이디기억에 체크되지않도록 한다
//input태그에 toURL을 받아서 LoginController로 넘겨준다.

### 게시판
//[BoardController.java]
[첫번째]
//1. GET으로 boardList를 보여주는 메서드 작성
//2. 로그인체크로 확인.
//  2-1. 로그인을 안했으면 toURL로 URL값을 가지고 로그인 화면으로 이동
//  2-2. 로그인 했으면 게시판 화면으로 이동
//3. 로그인체크는
//  3-1. 세션을 얻어서
//  3-2. 세션에 id가 있는지 확인. 있으면 true 없으면 false를 반환.
[두번째 - 페이징]
//page와 pageSize를 매개변수로 받는다.
//기본값을 page=1, pageSize=10을 준다.
//Map에 offset, pageSize를 저장해서 getPage를 호출 후 Model에 저장.
//PageHandler객체를 만들어서 Model에 저장.


//[boardList.jsp]
[첫번째]
//세션을 얻도록 하고,
//sessionScope로 id를 얻어서 loginId에 저장한다.
//loginId가 없으면 로그인으로 링크를, 있으면 로그아웃으로 링크를 걸고
//loginId가 없으면 Login이 보이게, 있으면 ID=아이디가 보이게 한다. 
[두번째]
//1. 목록
//HTML table을 만든다. 열의 이름은 번호, 제목, 이름, 등록일, 조회수로 한다.
//반복문으로 Model로 받은 리스트를 table에 출력한다.
//2. 페이징
//table 밑에 작성한다.
//반복문으로 a태그를 사용해서 페이지를 표시하고, 조건문으로 <와 >를 표시한다.

### DB 테이블
[board]
- bno - int, Not null, Auto inc, Primary key
- title - varchar(100), Not null
- content - text, Not null
- writer - varchar(30), Not null
- view_cnt - int, default 0
- comment_cnt - int, default 0
- reg_date - datetime, default now()
- up_date - datetime, default now()

### Mapper
[boardMapper.xml]
- sql문을 id="selectFromBoard"로 작성하고, include 하도록 한다.
- insert - 매개변수타입 BoardDto로 title, content, writer를 받는다.
- select - 매개변수타입 Integer로 bno를 받는다. reg_date, bno로 정렬한다.
- update - 매개변수타입 BoardDto로 title, content, up_date, WHERE절로 bno, writer를 받는다. 
- delete - 매개변수타입 Map으로 bno, writer를 받는다.
- count - resultType을 int로 한다.
- selectAll - 매개변수타입이 없고, resultType을 BoardDto로 한다. reg_date, bno로 정렬한다.
- deleteAll - 매개변수타입이 없다.
- increaseViewCnt - 매개변수타입 Integer로 bno를 받는다.
- selectPage - 매개변수타입 Map으로 offset, pageSize를 받고 resultType은 BoardDto로 한다. reg_date, bno로 정렬한다.

[userMapper.xml]
- insert - 매개변수타입 User로 reg_date를 제외한 나머지를 받는다. reg_date는 now()로 입력한다.
- select - 매개변수타입 String으로 id를 받는다.
- update - 매개변수타입 User로 reg_date를 제외한 나머지를 받는다. reg_date는 now()로 입력한다.
- delete - 매개변수타입 String으로 id를 받는다.
- count - resultType을 int로 한다. 
- deleteAll - 매개변수타입이 없다.

### Domain
[BoardDto.java]
- up_date를 제외하고 DB테이블을 보고 작성한다.
- 접근제어자는 private
- 생성자는 title, content, writer만 추가하고, 기본생성자를 만든다.
- getter&sertter, toString은 전체를 추가하고,
- equals&hashCode는 bno, title, content, writer만 추가한다.

### Paging
[PageHandler.java]
- iv 
1. totalCnt, pageSize
2. totalPage, naviSize, page, beginPage, endPage, showPrev, showNext 
- naviSize의 기본값을 10으로 하고, 생성자 2개를 만든다.
- 생성자는 totalCnt, page, pageSize를 매개변수로 하고, naviSize를 제외한 나머지는 계산한다.
- 추가로 생성자를 추가하는데 pageSize는 10으로 하고 위의 생성자를 사용한다.
- print()메서드, toString, getter&setter를 추가한다.
- 테스트는 페이지 네비바의 양끝을 중점으로 확인한다.
