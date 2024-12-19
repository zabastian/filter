package com.example.memo.controller;

import com.example.memo.dto.BoardResponseDto;
import com.example.memo.dto.BoardWithAgeResponseDto;
import com.example.memo.dto.CreateBoardRequestDto;
import com.example.memo.service.BoardService;
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

    @PostMapping
    public ResponseEntity<BoardResponseDto> save(@RequestBody CreateBoardRequestDto requestDto) {

        BoardResponseDto boardResponseDto =
                boardService.save(
                        requestDto.getTitle(),
                        requestDto.getContents(),
                        requestDto.getUsername(),
                        requestDto.getEmail()
                );

        return new ResponseEntity<>(boardResponseDto, HttpStatus.CREATED);
    }
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


