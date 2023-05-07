package com.will.Minesweeper.Board;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Tester {

    public static void main(String[] args) {

        BoardGenerator boardGenerator = new BoardGenerator();
        BoardProcess boardProcess = new BoardProcess();

        int n = 3; // 지뢰 개수
        int boardSize = 3 + new Random().nextInt(8); // 보드 크기 (3 ~ 30)
        String[] board = boardGenerator.generateRandomBoard(n, boardSize);

        System.out.println("Generated Board:");
        for (int i = 0; i < board.length; i++) {
            System.out.println(board[i]);
        }

        int y = new Random().nextInt(boardSize);
        int x = new Random().nextInt(boardSize);

        System.out.println("Clicked Position: (" + y + ", " + x + ")");

        String[] result = boardProcess.process(board, y, x);
        System.out.println("Result Board:");
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }
}
