# 스프링 복습을 하면서 주석을 만들고, 주석만 보고 게시판을 만들 수 있도록 훈련

## 07.19
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

### 회원정보
//[User.java]
//1. DB를 보고 private으로 iv를 작성.
//2. getter&setter, toString을 만든다.

### 회원가입 및 회원정보
//[RegisterController.java]
//1. GET, POST를 처리할 메서드를 각각 만든다.
//2. POST를 처리할 메서드는 User를 받아서 유효성 검사를 한다.
//  	이 때 메세지를 보내는데 URLEncoder를 사용한다.
//  	유효성검사를 통과하지 못하면 회원가입화면으로 다시 가게 하고,
//  	유효성검사를 통과하면 회원정보를 보여주도록 한다.

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

//[registerInfo.jsp]
//Model로 user를 받으므로 user를 사용해서 값을 출력한다.

### 로그인
//[LoginController.java]
//1. GET으로 loginForm으로 이동하는 메서드 작성
//2. POST로 작성
//  1. id와 pwd를 loginCheck메서드로 확인.
//  2-1. id와 pwd가 일치하지 않으면 loginForm으로 리다이렉트하고 에러메세지를 출력.
//  2-2. 일치하면 session에 id를 저장하고, rememberId를 확인.
//     	 rememberId가 true면 쿠키를 생성하고 홈으로 이동
//     	 rememberId가 false면 쿠키를 삭제하고 홈으로 이동
// 				toURL이 null이거나 빈문자열이면 홈으로, 아니면 toURL로 가도록 한다.       
//3. GET으로 로그아웃을 하는 메서드 작성
//	3-1 세션을 종료하고
//	3-2 홈으로 이동

//[loginForm.jsp]
//rememberId에 체크하고 로그인에 성공했을 때 아이디가 나오고 아이디기억이 체크되어있도록 한다
//rememberId에 체크를안하고 로그인에 성공하면 아이디칸을 비우고, 아이디기억에 체크되지않도록 한다
//input태그에 toURL을 받아서 LoginController로 넘겨준다.

### 게시판
//[BoardController.java]
//1. GET으로 boardList를 보여주는 메서드 작성
//2. 로그인체크로 확인.
//  2-1. 로그인을 안했으면 toURL로 URL값을 가지고 로그인 화면으로 이동
//  2-2. 로그인 했으면 게시판 화면으로 이동
//3. 로그인체크는
//  3-1. 세션을 얻어서
//  3-2. 세션에 id가 있는지 확인. 있으면 true 없으면 false를 반환.

//[boardList.jsp]
//세션을 얻도록 하고,
//sessionScope로 id를 얻어서 loginId에 저장한다.
//loginId가 없으면 로그인으로 링크를, 있으면 로그아웃으로 링크를 걸고
//loginId가 없으면 Login이 보이게, 있으면 ID=아이디가 보이게 한다. 
