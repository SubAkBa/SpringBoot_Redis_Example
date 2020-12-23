package com.example.redis.config.board;

import com.example.redis.config.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping(path = "/save")
    public ResponseEntity<URI> save(@RequestBody Board board) {
        Long saveId = boardService.save(board);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saveId)
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping(path = "/{id}")
    public CustomResponse<Board> findOne(@PathVariable Long id) {
        return new CustomResponse<Board>(boardService.findOne(id), HttpStatus.OK.toString(), HttpStatus.OK.value());
    }

    @GetMapping(path = "/list")
    public CustomResponse<List<Board>> list() {
        return new CustomResponse<List<Board>>(boardService.findAll(), HttpStatus.OK.toString(), HttpStatus.OK.value());
    }
}
