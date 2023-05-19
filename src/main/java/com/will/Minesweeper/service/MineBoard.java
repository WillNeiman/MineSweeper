package com.will.Minesweeper.service;

public class MineBoard {
    private char[][] initialBoard;
    private char[][] resultBoard;
    private int width;
    private int height;

    public MineBoard(char[][] board) {
        this.width = board[0].length;
        this.height = board.length;

        // initialBoard와 resultBoard 초기화
        this.initialBoard = board;
        this.resultBoard = board;
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

}
