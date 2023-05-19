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

    @PostMapping("/start")
    public String startGame(@RequestParam(name = "boardSize") Integer boardSize,
                            @RequestParam(name = "mineQuantity") Integer mineQuantity,
                            HttpSession session) {
        if (boardSize == null || boardSize < 3 || boardSize > 15) {
            throw new IllegalArgumentException("보드 사이즈를 어디선가 이상하게 수정해서 제작자를 시험에 빠뜨리려는 속셈");
        }
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
    public String processCellClick(@RequestParam("x") Integer x,
                                   @RequestParam("y") Integer y,
                                   HttpSession session) {
        if (x == null || y == null) {
            throw new IllegalArgumentException("어딜 누르는 겁니까? 그건 지뢰의 잔상입니다만?");
        }
        System.out.println("Clicked Position: (" + y + ", " + x + ")");

        char[][] board = (char[][]) session.getAttribute("board");
        if (x < 0 || x >= board[0].length || y < 0 || y >= board.length) {
            throw new IllegalArgumentException("어이, 그 앞은 '지옥'이다. 지뢰밭으로 돌아오도록.");
        }
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
