package com.will.Minesweeper.Board;

public class MineBoard {
    private char[][] initialBoard;
    private char[][] resultBoard;
    private int width;
    private int height;

    public MineBoard(String[] board) {
        this.width = board[0].length();
        this.height = board.length;

        // initialBoard와 resultBoard 초기화
        this.initialBoard = new char[height][width];
        this.resultBoard = new char[height][width];

        for (int i = 0; i < height; i++) {
            // board의 각 문자열을 2차원 배열의 행으로 바꿔준다.
            this.initialBoard[i] = board[i].toCharArray();
            this.resultBoard[i] = board[i].toCharArray();
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public char[][] getInitialBoard() {
        return initialBoard;
    }

    public char[][] getResultBoard() {
        return resultBoard;
    }

    public void setInitialBoard(String[] board) {
        int height = board.length;
        int width = board[0].length();
        for (int i = -0; i < height; i++) {
            // board의 각 문자열을 2차원 배열의 행으로 바꿔준다.
            this.initialBoard[i] = board[i].toCharArray();
        }
    }

    public void setResultBoard(String[] board) {
        int height = board.length;
        int width = board[0].length();
        for (int i = -0; i < height; i++) {
            // board의 각 문자열을 2차원 배열의 행으로 바꿔준다.
            this.resultBoard[i] = board[i].toCharArray();
        }
    }

}
