-- 필수 과제를 하던 도중 실습과 유사해 정보를 수정하는 형태로 작업을 했습니다

-- age의 변수명을 조건에 맞게 email로 바꿨고 실습에서는 전체 조회를 한것과 선택 조회를 한것에 데이터가 다르게 들어가게 되어있어 email은 둘 다 들어가는게 맞다고 생각을 해 전체,선택 조회시에도 get을 통해 이메일 정보를 저장하게 만들었습니다.(스크린샷으로 사진 넣어놓았습니다)

-- 마지막으로 cookie,session을 활용해 필터를 등록하는 과정을 넣었습니다.

가장 흥미로운 점은  /findById를 default내부에서 사용하면 MemberRepository를 optional이 아닌 Member(Entity) 그 자체로 반환할 수 잇게 만든다는 점이 였고

코드 중간중간에 실습때 배운내용과 내가 생각하고 정리한 내용을 합쳐서 주석처리로 적어놓았다.

진행과정

member entity를 생성 => membercontroller를 통해 @RequestMapping으로 경로를 설정해주었고 가장먼저 @RequestMapping "/members" @PostMapping으로 "/signup"경로를 설정해주었다 
(이 과정에서 @RequiredArgsConstructor 사용이되었는데 

((Spring Context에서 MemberService 타입의 Bean을 찾음.
해당 Bean을 MemberController의 생성자에 주입.
MemberService가 final로 선언되어 있기 때문에 반드시 주입되어야 하며, 주입되지 않으면 컴파일 오류가 발생          ==> 이 내용이 굉장히 흥미로웠다 생성자 주입을 통한 의존성을 주입받는 형식이 좀 신기한 경험이었다.
→ 이로 인해 필수 의존성을 강제할 수 있습니다.))


그 후에 ResponseEntity로 SignUpResponseDto를 받아와 signUp(@RequestBody SignUpRequestDto requestDto로 매핑해준다.
(requestDto는 @RequestBody를 통해 클라이언트에서 전달받은 JSON 데이터를 담는 매개변수이고 @RequestBody 덕분에 json데이터를 객체로 바꿔준다.)

