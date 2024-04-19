package com.example.board;

import com.example.board.boards.Board;
import com.example.board.boards.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {


    private final BoardService boardService;

    @Autowired
    public MainController(BoardService boardService) {
        this.boardService = boardService;
    }


    @GetMapping("List")
    public String getAllBoards(Model model) {
        List<Board> boards = boardService.getAllBoards();
        model.addAttribute("boards", boards);
        return "List";
    }

    @GetMapping("/board/{id}")
    public String getBoardDetail(@PathVariable("id") Long id, Model model) {
        Board board = boardService.boardDetail(id);
        model.addAttribute("board", board);
        return "BoardDetail";
    }

    @GetMapping("/create")
    public String writeForm(Model model) {
        model.addAttribute("board", new Board());
        return "CreateBoard";
    }

    @PostMapping("/save")
    public String saveBoard(@ModelAttribute("board") Board board) {
        boardService.createBoard(board);
        return "redirect:/List";
    }

    @PostMapping("/delete/{id}")
    public String deleteBoard(@PathVariable("id") Long id) {
        boardService.deleteBoard(id);
        return "redirect:/List";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Board board = boardService.boardDetail(id);
        if (board == null) {
            return "redirect:/List";
        }
        model.addAttribute("board", board);
        return "UpdateBoard";
    }

    @PostMapping("/update/{id}")
    public String updateBoard(@PathVariable("id") Long id, @ModelAttribute("board") Board updatedBoard) {
        boardService.updateBoard(id, updatedBoard);
        return "redirect:/List";
    }

}
