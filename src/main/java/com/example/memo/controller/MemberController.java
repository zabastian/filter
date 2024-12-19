package com.example.memo.controller;

import com.example.memo.dto.MemberResponseDto;
import com.example.memo.dto.SignUpRequestDto;
import com.example.memo.dto.SignUpResponseDto;
import com.example.memo.dto.UpdatePasswordRequestDto;
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

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto requestDto){
        //이 메서드는 HTTP 요청에서 전달된 JSON 데이터를 SignUpResponseDto로 변환한 후, 필요한 로직(회원가입 처리 등)을 수행한 뒤, ResponseEntity를 통해 클라이언트에게 응답을 반환한다.
        SignUpResponseDto signUpResponseDto =
                memberService.signUp( // memberService에 signUp만들라고 경고표시 나옴 그걸 requestDto로 만들어 받아옴
                        requestDto.getUsername(),
                        requestDto.getPassword(),
                        requestDto.getEmail()
                );
        return new ResponseEntity<>(signUpResponseDto, HttpStatus.CREATED);
        // 성공했을때 http상태코드를 넘겨줘야함
    }
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
