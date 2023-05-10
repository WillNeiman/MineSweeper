package com.will.Minesweeper.controller;

import com.will.Minesweeper.service.MineBoardGenerator;
import com.will.Minesweeper.service.MineSweeperProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class MineSweeperController {

    @Autowired
    private MineBoardGenerator generator;
    @Autowired
    private MineSweeperProcessor processor;

    @GetMapping("/")
    public String main(HttpSession session) {
        session.invalidate();
        return "main";
    }

    @PostMapping("/startGame")
    public String startGame(@RequestParam(name = "boardSize") int boardSize,
                            @RequestParam(name = "mineQuantity") int mineQuantity,
                            HttpSession session) {
        System.out.println("boardSize = " + boardSize);
        System.out.println("mineQuantity = " + mineQuantity);

        // 보드 생성
        char[][] board = generator.generateBoard(mineQuantity, boardSize);

        boolean isVictory = false;
        boolean isGameOver = false;
        System.out.println("isVictory = " + isVictory);
        System.out.println("isGameOver = " + isGameOver);
        session.setAttribute("board", board);
        session.setAttribute("isVictory", isVictory);
        session.setAttribute("isGameOver", isGameOver);

        // 게임 페이지로 리다이렉트
        return "game";
    }

    @PostMapping("/process")
    public String processCellClick(@RequestParam("x") int x,
                                   @RequestParam("y") int y,
                                   HttpSession session) {
        System.out.println("Clicked Position: (" + y + ", " + x + ")");

        char[][] board = (char[][]) session.getAttribute("board");

        // 게임 로직 처리
        board = processor.process(board, y, x);

        boolean isVictory = processor.isVictory();
        boolean isGameOver = processor.isGameOver(board);
        System.out.println("isVictory = " + isVictory);
        System.out.println("isGameOver = " + isGameOver);

        session.setAttribute("board", board);
        session.setAttribute("isVictory", isVictory);
        session.setAttribute("isGameOver", isGameOver);

        return "game";
    }

}
