과제를 하던도중 기존에 있는 코드를 살리면서 로그인 기능을 구현할 수 없나? 라는 의문점이 들었습니다.

http://localhost:8080/members/signup 코드를 입력하고 http://localhost:8080/boards를 불렀으나 오류가 생겼습니다(쿠키는 만들어 졌지만 오류발생)

meember클래스에서 + 회원가입 + 로그인 기능을 한곳에 만드려고 하였으나 board에서 Long memberId = (Long) session.getAttribute("memberId"); memberId가 정상적으로 넣어지지가 않았습니다.

문제점을 살펴보니 회원가입까지 만들었지만 로그인이 구현되지 않아 session id를 받아올 수 없었습니다.

기존에 있었던 코드에 다 넣으니 너무 해결하기도 어렵고 한계를 느껴 어느정도 완성된 다른 코드에 globalexception/valid 기능을 넣고자 하였습니다.


