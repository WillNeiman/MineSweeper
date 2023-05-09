package com.will.Minesweeper.controller;

import com.will.Minesweeper.service.GameResult;
import com.will.Minesweeper.service.MineBoardGenerator;
import com.will.Minesweeper.service.MineSweeperProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Arrays;

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
        String[] board = generator.generateRandomBoard(mineQuantity, boardSize);
        System.out.println("Generated Board:");
        for (int i = 0; i < board.length; i++) {
            System.out.println(board[i]);
        }

        // 2차원 문자 배열로 변환
        char[][] boardArray = new char[board.length][];
        for (int i = 0; i < board.length; i++) {
            boardArray[i] = board[i].toCharArray();
        }

        session.setAttribute("board", boardArray);

        // 게임 페이지로 리다이렉트
        return "game";
    }

    @PostMapping("/process")
    public String processCellClick(@RequestParam("x") int x,
                                   @RequestParam("y") int y,
                                   HttpSession session) {
        System.out.println("Clicked Position: (" + y + ", " + x + ")");

        char[][] board = (char[][]) session.getAttribute("board");
        String[] result = new String[board.length];

        for (int i = 0; i < board.length; i++) {
            result[i] = String.valueOf(board[i]);
        }
        System.out.println("Current Board:");
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }

        // 게임 로직 처리
        result = processor.process(result, y, x);
        System.out.println("Result Board:");
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }

        for (int i = 0; i < board.length; i++) {
            board[i] = result[i].toCharArray();
        }
        boolean isVictory = processor.isVictory();
        boolean isGameOver = processor.isGameOver(result);
        System.out.println("isVictory = " + isVictory);
        System.out.println("isGameOver = " + isGameOver);

        session.setAttribute("board", board);
        session.setAttribute("isVictory", isVictory);
        session.setAttribute("isGameOver", isGameOver);

        return "game";
    }

}
