package com.example.memo.repository;


import com.example.memo.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    default Board findByIdOrElseThrow(Long boardId) {
        return findById(boardId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + boardId));
    }
}

//    List<Board> findByTitleIgnoreCase(String titlename)