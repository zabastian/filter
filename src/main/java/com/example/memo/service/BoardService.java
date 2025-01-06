package com.example.memo.service;

import com.example.memo.dto.BoardResponseDto;
import com.example.memo.dto.BoardWithAgeResponseDto;
import com.example.memo.dto.CreateBoardRequestDto;
import com.example.memo.entity.Board;
import com.example.memo.entity.Member;
import com.example.memo.globalexceptionhandler.ValidateException;
import com.example.memo.repository.BoardRepository;
import com.example.memo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService{

    private final MemberRepository memberRepository;

    private final BoardRepository boardRepository;

    public BoardResponseDto getId(long boardId) {
        Board board = findBoardById(boardId);
        return convertToDto(board);
    }  //id를 가지고와 convertToDto에 넣어준다.

    private Board findBoardById(long boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(() -> new ValidateException("존재하지 않는 게시글", HttpStatus.NOT_FOUND));
    }

    public BoardResponseDto save(CreateBoardRequestDto request, long memberId, String username) {

        Member findMember = memberRepository.findMemberByUsernameOrElseThrow(username);
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new ValidateException(("존재하지 않는유저"), HttpStatus.NOT_FOUND));
        Board board = Board.of(request, member);
        boardRepository.save(board); //simplejparepository에서 이미 @transactional 명시가 되었기때문에 없어도 동작한다
        return convertToDto(board);

    }
    /*public BoardResponseDto save(String title, String contents, String username, String email) {

        Member findMember = memberRepository.findMemberByUsernameOrElseThrow(username);

        Board board = new Board(title, contents, email);
        board.setMember(findMember);

        boardRepository.save(board); //simplejparepository에서 이미 @transactional 명시가 되었기때문에 없어도 동작한다

        return new BoardResponseDto(board.getId(), board.getTitle(), board.getContents(),board.getEmail());

    }*/  //주석을 열면 위의 코드와 다르게 그냥 받아와서 출력하는 코드가 열린다


    private BoardResponseDto convertToDto(Board board) {
        return BoardResponseDto.toDto(board);
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

    public BoardWithAgeResponseDto findById(Long boardid) {
        Board findBoard = boardRepository.findByIdOrElseThrow(boardid);
        Member writer = findBoard.getMember();

        return new BoardWithAgeResponseDto(findBoard.getTitle(), findBoard.getContents(), writer.getEmail());
    }

    public void delete(Long boardid) {
        Board findBoard = boardRepository.findByIdOrElseThrow(boardid);

        boardRepository.delete(findBoard);
    }


    //    public List<Board> test() {
//        List<Board> titlename = boardRepository.findByTitleIgnoreCase("titlename");
//        for (Board board : titlename) {
//            log.info("아이디값 {}", board.getTitle());
//        }
//        return new ArrayList<>(titlename);
//    }


}