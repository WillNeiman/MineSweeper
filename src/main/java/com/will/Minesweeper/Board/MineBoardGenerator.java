package com.will.Minesweeper.Board;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MineBoardGenerator {
    // 테스트케이스 랜덤 생성 메소드
    public String[] generateRandomBoard(int n, int boardSize) {
        // 지뢰의 수가 boardSize의 제곱보다 클 경우 예외처리
        if(n > Math.pow(boardSize,2)){
            n = (int)Math.pow(boardSize,2);
            System.out.println("n = " + n);
        }

        Random rand = new Random();
        String[] board = new String[boardSize];
        System.out.println("board.length = " + board.length);

        int totalMinesAdded = 0;

        // 보드를 'E'로 초기화
        for (int i = 0; i < boardSize; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < boardSize; j++) {
                sb.append('E');
            }
            board[i] = sb.toString();
        }

        // 전체 행과 열 중 랜덤하게 n개의 지뢰를 추가하기
        while (totalMinesAdded < n) {
            int rowIndex = rand.nextInt(boardSize);
            int colIndex = rand.nextInt(boardSize);

            StringBuilder sb = new StringBuilder(board[rowIndex]);
            if (sb.charAt(colIndex) == 'E') {
                // 해당 위치에 아직 지뢰가 없는 경우
                sb.setCharAt(colIndex, 'M');
                totalMinesAdded++;
                board[rowIndex] = sb.toString();
            } else {
                // 해당 위치에 이미 지뢰가 있는 경우
                continue;
            }
        }

        // 남은 곳은 모두 지뢰가 없는 빈 칸으로 채우기
        for (int i = 0; i < boardSize; i++) {
            if (board[i] == null) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < boardSize; j++) {
                    sb.append('E');
                }
                board[i] = sb.toString();
            }
        }

        return board;
    }
}
