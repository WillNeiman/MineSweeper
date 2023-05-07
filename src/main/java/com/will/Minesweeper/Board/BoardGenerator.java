package com.will.Minesweeper.Board;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class BoardGenerator {
    // 테스트케이스 랜덤 생성 메소드
    public String[] generateRandomBoard(int n, int boardSize) {
        Random rand = new Random();
        String[] board = new String[boardSize];
        System.out.println("board.length = " + board.length);

        int totalMinesAdded = 0;

        // 전체 행 중 랜덤하게 n개의 지뢰를 추가하기
        while (totalMinesAdded < n) {
            int rowIndex = rand.nextInt(boardSize);
            String row = board[rowIndex];
            if (row == null) {
                // 아직 해당 행에 아무 지뢰도 추가되지 않은 경우
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < boardSize; i++) {
                    if (i == rowIndex) {
                        sb.append('M');
                        totalMinesAdded++;
                        System.out.println("totalMinesAdded = " + totalMinesAdded);
                    } else {
                        sb.append('E');
                    }
                }
                board[rowIndex] = sb.toString();
            } else {
                // 해당 행에 이미 지뢰가 추가된 경우
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
