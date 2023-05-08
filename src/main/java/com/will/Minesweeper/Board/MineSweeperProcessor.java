package com.will.Minesweeper.Board;

import java.util.LinkedList;
import java.util.Queue;

public class MineSweeperProcessor {

    // 8방향을 나타내는 상수 배열 미리 깔아두기
    private static final int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static final int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};

    private MineBoard board;

    public String[] process(String[] newBoard, int y, int x) {

        board = new MineBoard(newBoard);
        int height = board.getHeight();
        int width = board.getWidth();
        char[][] initialBoard = board.getInitialBoard();
        char[][] resultBoard = board.getResultBoard();

        // 눌렀는데 지뢰가 있으면 X로 표기한 후 결과 반환
        if (initialBoard[y][x] == 'M') {
            resultBoard[y][x] = 'X'; // 클릭한 위치의 지뢰를 X로 표시
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
        // 클릭한 위치를 제외한 나머지 위치 중 initialBoard의 M 위치에 해당하는 resultBoard의 좌표값들을 M으로 변경
                    if (initialBoard[i][j] == 'M' && !(i == y && j == x)) {
                        resultBoard[i][j] = 'M';
                    }
                }
        // 인자로 주어진 객체가 문자 배열(char[])이므로, 배열의 모든 문자를 순서대로 하나의 문자열로 연결하여 반환
                newBoard[i] = String.valueOf(resultBoard[i]);
            }
            return newBoard;
        }

        // 지뢰가 없을 시 BFS 전략을 사용해보기
        Queue<Position> queue = new LinkedList<>();
        queue.offer(new Position(y, x));

        // 지뢰가 없는 위치를 열어나가는 알고리즘
        while (!queue.isEmpty()) {
            Position currentPosition = queue.poll();
            int mineCount = 0;

            // 주변 8개 위치 확인해서 지뢰 개수 세기
            for (int i = 0; i < 8; i++) {
                int ny = currentPosition.y + dy[i];
                int nx = currentPosition.x + dx[i];

                if (ny >= 0 && ny < height && nx >= 0 && nx < width && resultBoard[ny][nx] == 'M') {
                    mineCount++;
                }
            }

            // 지뢰가 있으면 해당 개수 표시하기
            if (mineCount > 0) {
                resultBoard[currentPosition.y][currentPosition.x] = (char) (mineCount + '0');
            } else {
                // 지뢰가 주변에 없으면 B로 표시하고 인접한 위치정보를 다시 Queue에 넣기
                resultBoard[currentPosition.y][currentPosition.x] = 'B';

                // 인접한 8개 위치 확인하고 아직 열리지 않은 위치를 Queue에 추가
                for (int i = 0; i < 8; i++) {
                    int ny = currentPosition.y + dy[i];
                    int nx = currentPosition.x + dx[i];

                    // 인접한 위치가 지뢰밭 범위 내에 있는지 확인, 그리고 아직 열리지 않았는지 확인
                    if (ny >= 0 && ny < height && nx >= 0 && nx < width && resultBoard[ny][nx] == 'E') {
                        // 방문 여부를 확인하기 위해 해당 위치를 0으로 변경하기
                        resultBoard[ny][nx] = '0';
                        // 인접한 위치를 큐에 추가하고 BFS를 진행하기
                        queue.offer(new Position(ny, nx));
                    }
                }
            }
        }

        // 최종 결과를 문자열 배열로 변환하여 반환하기 ver2
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (resultBoard[i][j] == 'M' || resultBoard[i][j] == '0') {
                    resultBoard[i][j] = 'E';
                }
            }
            newBoard[i] = String.valueOf(resultBoard[i]);
        }

        return newBoard;
    }

    public boolean isVictory() {
        int height = board.getHeight();
        int width = board.getWidth();
        char[][] initialBoard = board.getInitialBoard();
        char[][] resultBoard = board.getResultBoard();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (initialBoard[i][j] != 'M' && resultBoard[i][j] == 'E') {
                    return false; // 지뢰가 없는 칸이 'E'인 경우가 있으면 승리 조건이 아님
                }
            }
        }
        return true; // 모든 지뢰가 없는 칸이 'E'가 아닌 경우 승리
    }

    public boolean isGameOver(String[] resultBoard) {
        for (String row : resultBoard) {
            if (row.contains("X")) {
                return true;
            }
        }
        return false;
    }
}
