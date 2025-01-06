package com.example.memo.controller;

import com.example.memo.dto.*;
import com.example.memo.globalexceptionhandler.ApiResponse;
import com.example.memo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor // 생성자 주입 받으려고 사용

public class MemberController {
    private final MemberService memberService;

//    @PostMapping("/login")
//    public ResponseEntity<ApiResponse<String>> login(@RequestBody LoginRequestDto loginRequestDto) {
//        try {
//            String sessionKey = memberService.login(loginRequestDto.getUsername(), loginRequestDto.getPassword());
//            ApiResponse<String> apiResponse = ApiResponse.success(HttpStatus.OK, "Login successful", sessionKey);
//            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
//        } catch (Exception e) {
//            ApiResponse<String> errorResponse = ApiResponse.error(HttpStatus.UNAUTHORIZED, "Invalid credentials");
//            return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
//        }
//    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<SignUpResponseDto>> signUp(@RequestBody SignUpRequestDto requestDto) {
        //이 메서드는 HTTP 요청에서 전달된 JSON 데이터를 SignUpRequestDto로 변환한 후, 필요한 로직(회원가입 처리 등)을 수행한 뒤, ResponseEntity를 통해 클라이언트에게 응답을 반환한다.
        SignUpResponseDto signUpResponseDto =
                memberService.signUp( // memberService에 signUp만들라고 경고표시 나옴 그걸 requestDto로 만들어 받아옴
                        requestDto.getUsername(),
                        requestDto.getPassword(),
                        requestDto.getEmail(),
                        requestDto.getKim()
                );
        ApiResponse<SignUpResponseDto> apiResponse = ApiResponse.success(HttpStatus.CREATED, "created", signUpResponseDto);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
        // 성공했을때 http상태코드를 넘겨줘야함
    }

    //public class MemberController {
//    private final MemberService memberService;
//
//    @PostMapping("/signup")
//    public ResponseEntity<ApiResponse<SignUpResponseDto>> signUp(@RequestBody SignUpRequestDto requestDto) {
//        //이 메서드는 HTTP 요청에서 전달된 JSON 데이터를 SignUpRequestDto로 변환한 후, 필요한 로직(회원가입 처리 등)을 수행한 뒤, ResponseEntity를 통해 클라이언트에게 응답을 반환한다.
//        try {
//            SignUpResponseDto signUpResponseDto =
//                    memberService.signUp( // memberService에 signUp만들라고 경고표시 나옴 그걸 requestDto로 만들어 받아옴
//                            requestDto.getUsername(),
//                            requestDto.getPassword(),
//                            requestDto.getEmail(),
//                            requestDto.getKim()
//                    );
//            ApiResponse<SignUpResponseDto> apiResponse = ApiResponse.success(HttpStatus.CREATED, "created", signUpResponseDto);
//            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
//        } catch (Exception e) {
//            SignUpResponseDto errorResponseDto = new SignUpResponseDto(null, "Error", e.getMessage());
//            ApiResponse<SignUpResponseDto> errorResponse = ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "error occurred", errorResponseDto);
//            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//
//        }
    // 성공했을때 http상태코드를 넘겨줘야함
//}
    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> findById(@PathVariable Long id){

        MemberResponseDto memberResponseDto = memberService.findById(id);

        return new ResponseEntity<>(memberResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(
            @PathVariable Long id,
            @RequestBody UpdatePasswordRequestDto requestDto
    ) {

        memberService.updatePassword(id, requestDto.getOldPassword(), requestDto.getNewPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
