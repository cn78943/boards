package com.example.board.boards;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    public Board boardDetail(Long id) {
        return boardRepository.findById(id).orElse(null);
    }

    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

    public Board updateBoard(Long id, Board boardDetails) {
        Board board = boardRepository.findById(id).orElse(null);
        if (board != null) {
            board.setTitle(boardDetails.getTitle());
            board.setContent(boardDetails.getContent());
            return boardRepository.save(board);
        }
        return null;
    }

    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }

//    public Page<Board> getAllBoards(Pageable pageable) {
//        return boardRepository.findAll(pageable);
//    }

}
