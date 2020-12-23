package com.example.redis.config.board;

import com.example.redis.config.exception.type.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long save(Board board) {
        Board savedBoard = boardRepository.save(board);

        return savedBoard.getId();
    }

    @Cacheable(value = "findOne", key = "#id")
    public Board findOne(Long id) {
        return boardRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("조건에 맞는 유저가 없습니다."));
    }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }
}
