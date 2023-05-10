package com.will.Minesweeper.legacy;

public class GameResult {
    private String[] board;
    private boolean gameOver;
    private boolean victory;
    // getter와 setter 메서드가 필요합니다.

    public String[] getBoard() {
        return board;
    }

    public void setBoard(String[] board) {
        this.board = board;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isVictory() {
        return victory;
    }

    public void setVictory(boolean victory) {
        this.victory = victory;
    }
}