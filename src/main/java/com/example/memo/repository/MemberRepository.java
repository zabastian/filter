package com.example.memo.repository;

import com.example.memo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface MemberRepository extends JpaRepository<Member, Long> {
    //우리는 spring data jpa 를 사용할거라서 interface로 첫번째 값은 member 이고 member id의 타입 Long타입으로 가져온다.
    default Member findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Does not exist id = " + id));}
    //findById를 default내부에서 사용하면 MemberRepository를 optional이 아닌 Member(Entity) 그 자체로 반환할 수 잇게 만든다.
}
