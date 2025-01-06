package com.example.memo.repository;

import com.example.memo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    //우리는 spring data jpa 를 사용할거라서 interface로 첫번째 값은 member 이고 member id의 타입 Long타입으로 가져온다.

    Optional<Member> findMemberByUsername(String username);

    default Member findMemberByUsernameOrElseThrow(String username) {
        return findMemberByUsername(username).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Does not exist username = " + username));
    }

    default Member findByIdOrElseThrow(Long memberid) {
        return findById(memberid).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Does not exist id = " + memberid));
    }
    //findById를 default내부에서 사용하면 MemberRepository를 optional이 아닌 Member(Entity) 그 자체로 반환할 수 잇게 만든다.
    //이미 findById는 이미 JpaRepository에서 Optional을 반환하도록 정의되어 있으므로 추가 정의가 불필요 하기 때문이다.
}
