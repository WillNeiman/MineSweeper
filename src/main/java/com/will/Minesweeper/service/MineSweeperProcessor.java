package com.will.Minesweeper.service;

import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;

@Component
public class MineSweeperProcessor {

    // 8방향을 나타내는 상수 배열 미리 깔아두기
    private static final int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static final int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};

    private MineBoard board;

    public char[][] process(char[][] newBoard, int y, int x) {
        // 매 회마다 보드의 상태를 갱신해가며 진행되므로 위해 이전 클릭의 결과를 바탕으로 새로운 객체를 생성함
        this.board = new MineBoard(newBoard);
        int height = board.getHeight();
        int width = board.getWidth();
        char[][] initialBoard = board.getInitialBoard();
        System.out.println("initialBoard[0][0] = " + initialBoard[0][0]);
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
            }
            return resultBoard;
        }

        // 지뢰가 없을 시 BFS 전략을 사용해보기
        Queue<Position> queue = new LinkedList<>();
        queue.offer(new Position(y, x));

        // 큐가 빌 때까지 지뢰가 없는 위치를 열어나가는 알고리즘
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
        System.out.println("Result Board:");
        for (int i = 0; i < resultBoard.length; i++) {
            for (int j = 0; j < resultBoard[0].length; j++){
                System.out.print(resultBoard[i][j] + " ");
            }
            System.out.println();
        }

        // 최종 결과 반환
        return resultBoard;
    }

    public boolean isVictory() {
        int height = board.getHeight();
        int width = board.getWidth();
        char[][] initialBoard = board.getInitialBoard();
        char[][] resultBoard = board.getResultBoard();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                //"지뢰가 아닌 칸 중에서 아직 탐색하지 않은 칸이 존재하는 경우"
                // 아직 게임이 끝나지 않았다는 것을 의미, false 반환
                if (initialBoard[i][j] != 'M' && resultBoard[i][j] == 'E') {
                    return false;
                }
            }
        }
        // 모든 칸을 순회했음에도 이런 조건을 만족하는 칸이 하나도 없다면,
        // 즉 "지뢰가 아닌 모든 칸을 찾았다면", 게임에서 승리했다는 것을 의미하므로 true를 반환
        return true;
    }

    public boolean isGameOver(char[][] resultBoard) {
        for(int i = 0; i < resultBoard.length; i ++){
            for(int j = 0; j < resultBoard[i].length; j++){
                // process의 결과 'X'로 변환된 칸, 즉 "지뢰가 있는 자리를 탐색한" 경우 패배처리
                if(resultBoard[i][j] == 'X'){
                    return true;
                }
            }
        }
        return false;
    }
}
