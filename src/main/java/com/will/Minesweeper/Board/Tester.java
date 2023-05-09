package com.will.Minesweeper.Board;

import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.Scanner;

@Component
public class Tester {

    public static void main(String[] args) {

        MineBoardGenerator boardGenerator = new MineBoardGenerator();
        BoardProcess boardProcess = new BoardProcess();
        MineSweeperProcessor boardProcessNext = new MineSweeperProcessor();
        Scanner scanner = new Scanner(System.in);

        boolean playAgain;
        do {
            // 지뢰판 만들기
            int n = 2; // 지뢰 개수
            int boardSize = 3 + new Random().nextInt(4); // 보드 크기 (3 ~ 30)
            String[] board = boardGenerator.generateRandomBoard(n, boardSize);

            // 지뢰판 콘솔 출력하기
            System.out.println("Generated Board:");
            for (int i = 0; i < board.length; i++) {
                System.out.println(board[i]);
            }

            boolean gameEnded = false;
            while (!gameEnded) {
                // 키보드로부터 입력 받기
                System.out.print("Enter y value: ");
                int y = scanner.nextInt();
                System.out.print("Enter x value: ");
                int x = scanner.nextInt();
                System.out.println("Clicked Position: (" + y + ", " + x + ")");

                // 생성한 board와 좌표값 y, x를 이용해 지뢰찾기 로직 실행
                String[] result = boardProcessNext.process(board, y, x);
                System.out.println("Result Board:");
                for (int i = 0; i < result.length; i++) {
                    System.out.println(result[i]);
                }

                // 승리 조건 확인
                if (boardProcessNext.isVictory()) {
                    System.out.println("Congratulations! You won!");
                    gameEnded = true;
                } else if (boardProcessNext.isGameOver(result)) {
                    System.out.println("Game over! You stepped on a mine.");
                    gameEnded = true;
                }
            }

            // 게임을 다시 플레이할지 물어보기
            System.out.print("Do you want to play again? (y/n): ");
            String userInput = scanner.next();
            playAgain = userInput.equalsIgnoreCase("y");
        } while (playAgain);

        System.out.println("Thanks for playing!");
    }
}
