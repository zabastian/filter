package com.example.memo.controller;

import com.example.memo.dto.BoardResponseDto;
import com.example.memo.dto.BoardWithAgeResponseDto;
import com.example.memo.dto.CreateBoardRequestDto;
import com.example.memo.globalexceptionhandler.ApiResponse;
import com.example.memo.service.BoardService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/{boardId}")
    public ResponseEntity<BoardResponseDto> getId(@PathVariable Long boardId) {
        return new ResponseEntity<>(boardService.getId(boardId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<BoardResponseDto>> save(@Valid @RequestBody CreateBoardRequestDto requestDto, HttpSession session) {

        Long memberId = (Long) session.getAttribute("memberId");

        BoardResponseDto boardResponseDto =
                boardService.save(
                        requestDto,
                        memberId,
                        requestDto.getUsername()
                );
        ApiResponse<BoardResponseDto> apiResponse = ApiResponse.success(HttpStatus.CREATED, "created", boardResponseDto);

        return new ResponseEntity<ApiResponse<BoardResponseDto>>(apiResponse, HttpStatus.CREATED);
//        return new ResponseEntity<>(boardResponseDto.save(requestDto, userId), HttpStatus.CREATED);
    }

    /*@PostMapping
    public ResponseEntity<BoardResponseDto> save(@Valid @RequestBody CreateBoardRequestDto requestDto, HttpSession session) {

        Long memberId = (Long) session.getAttribute("memberId");

        BoardResponseDto boardResponseDto =
                boardService.save(
                        requestDto,
                        memberId,
                        requestDto.getUsername()
                );

        return new ResponseEntity<>(boardResponseDto, HttpStatus.CREATED);

    }*/ // 강결합 되있기전 코드이다.


    /*    @GetMapping("/test")
    public void testApi() {
        boardService.test();
    }

    @GetMapping("/test")
    public ResponseEntity<List<Board>> test() {
        List<Board> boardList =
                boardService.test(
                );  // boardService에서 반환된 List<Board>
        return new ResponseEntity<>(boardList, HttpStatus.OK);  // 200 OK와 함께 반환
    }*/ //테스트용 코드

    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> findAll() {
        List<BoardResponseDto> boardServiceDtoList = boardService.findAll();

        return new ResponseEntity<>(boardServiceDtoList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardWithAgeResponseDto> findById(@PathVariable Long id) {

        BoardWithAgeResponseDto boardWithAgeResponseDto = boardService.findById(id);

        return new ResponseEntity<>(boardWithAgeResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        boardService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}


