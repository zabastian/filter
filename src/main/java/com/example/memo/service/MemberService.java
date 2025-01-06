package com.example.memo.service;

import com.example.memo.dto.MemberResponseDto;
import com.example.memo.dto.SignUpResponseDto;
import com.example.memo.entity.Member;
import com.example.memo.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RequiredArgsConstructor
@Service

public class MemberService {

    private final MemberRepository memberRepository;

    public SignUpResponseDto signUp(String username, String password, String email, String kim) {

        Member member = new Member(username, password, email, kim);
        // 이렇게 request,member값을 같게 가져와야 하는 이유는 dto와 service를
        // 나눠논것이기 때문에 같은 인수를 가지고 와야한다.
        // public SignUpResponseDto signUp(String username, String password, String email, String kim) {
        // 까지는 requestDto로 요청한 데이터이고 savedMember부터가 Member가 memberRepository를 통해 가져온 db이다.
        //그 후에 id,username,email요청에 따라 값을 넣어서 보내주는것이다

        Member savedMember = memberRepository.save(member);

//        Member foundCourse = memberRepository.findById(member.getId())
//                .orElseThrow(() -> new MemberNotFoundException("course not found"));

        return new SignUpResponseDto(savedMember.getMemberid(), savedMember.getUsername(), savedMember.getEmail());
    }

    public MemberResponseDto findById(Long memberid) {
        Optional<Member> optionalMember = memberRepository.findById(memberid);

        // NPE 방지
        if (optionalMember.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + memberid);
        }

        Member findMember = optionalMember.get();

        return new MemberResponseDto(findMember.getUsername(),findMember.getEmail());

    }

    @Transactional // 트랜잭션으로 묶어서 모두 성공 or 모두 실패되야함
    public void updatePassword(Long memberid, String oldPassword, String newPassword) {
        Member findMember = memberRepository.findByIdOrElseThrow(memberid);  //findByIdOrElseThrow를 통해 optional없이 사용가능하게 됨

        if (!findMember.getPassword().equals(oldPassword)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"비밀번호 불일치");
        }
        findMember.updatePassword(newPassword);
    }
}
