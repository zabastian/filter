package com.example.memo.service;

import com.example.memo.dto.BoardResponseDto;
import com.example.memo.dto.BoardWithAgeResponseDto;
import com.example.memo.entity.Board;
import com.example.memo.entity.Member;
import com.example.memo.repository.BoardRepository;
import com.example.memo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService{

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    public BoardResponseDto save(String title, String contents, String username, String email) {

        Member findMember = memberRepository.findMemberByUsernameOrElseThrow(username);

        Board board = new Board(title, contents, email);
        board.setMember(findMember);

        boardRepository.save(board); //simplejparepository에서 이미 @transactional 명시가 되었기때문에 없어도 동작한다

        return new BoardResponseDto(board.getId(), board.getTitle(), board.getContents(),board.getEmail());

    }

    public List<BoardResponseDto> findAll() {

//        List<Board> findAllBoard=  boardRepository.findAll();
//        return null;

//        아래형태는 BoardResponseDto의 리스트형태로 반환받을 수 있게 해준다.
        //즉 이것때문에 게시글이 전체 조회할수 있게 되는것이다.

        return boardRepository.findAll()
                .stream()
                .map(BoardResponseDto::toDto)
                .toList();
    }

    public BoardWithAgeResponseDto findById(Long id) {
        Board findBoard = boardRepository.findByIdOrElseThrow(id);
        Member writer = findBoard.getMember();

        return new BoardWithAgeResponseDto(findBoard.getTitle(), findBoard.getContents(), writer.getEmail());
    }

    public void delete(Long id) {
        Board findBoard = boardRepository.findByIdOrElseThrow(id);

        boardRepository.delete(findBoard);
    }
}