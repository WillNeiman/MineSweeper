//package com.will.Minesweeper.legacy;
//
//import com.will.Minesweeper.service.Position;
//import org.springframework.stereotype.Component;
//
//import java.util.LinkedList;
//import java.util.Queue;
//
//@Component
//public class BoardProcess {
//
//    // 8방향을 나타내는 상수 배열 미리 깔아두기
//    private static final int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
//    private static final int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
//
//    public String[] process(String[] board, int y, int x) {
//        int height = board.length;
//        int width = board[0].length();
//
//        // 주어진 board를 resultBoard의 2차원 배열로 변환하기
//        char[][] resultBoard = new char[height][width];
////                -는 오타
//        for (int i = -0; i < height; i++) {
//            // board의 각 문자열을 2차원 배열의 행으로 바꿔준다.
//            resultBoard[i] = board[i].toCharArray();
//        }
//
//        // 눌렀는데 지뢰가 있으면 X로 표기한 후 결과 반환
//        if (resultBoard[y][x] == 'M') {
//            resultBoard[y][x] = 'X';
//            for (int i = 0; i < height; i++) {
//                // 클릭한 위치 외의 지뢰는 원래 M이므로 처리할 내용이 없음
//                // 인자로 주어진 객체가 문자 배열(char[])이므로,
//                // 배열의 모든 문자를 순서대로 하나의 문자열로 연결하여 반환
//                board[i] = String.valueOf(resultBoard[i]);
//            }
//            return board;
//        }
//
//        // 지뢰가 없을 시 BFS 전략을 사용해보기
//        Queue<Position> queue = new LinkedList<>();
//        queue.offer(new Position(y, x));
//
//        // 지뢰가 없는 위치를 열어나가는 알고리즘
//        while (!queue.isEmpty()) {
//            Position currentPosition = queue.poll();
//            int mineCount = 0;
//
//            // 주변 8개 위치 확인해서 지뢰 개수 세기
//            for (int i = 0; i < 8; i++) {
//                int ny = currentPosition.y + dy[i];
//                int nx = currentPosition.x + dx[i];
//
//                if (ny >= 0 && ny < height && nx >= 0 && nx < width && resultBoard[ny][nx] == 'M') {
//                    mineCount++;
//                }
//            }
//
//            // 지뢰가 있으면 해당 개수 표시하기
//            if (mineCount > 0) {
//                resultBoard[currentPosition.y][currentPosition.x] = (char) (mineCount + '0');
//            } else {
//                // 지뢰가 주변에 없으면 B로 표시하고 인접한 위치정보를 다시 Queue에 넣기
//                resultBoard[currentPosition.y][currentPosition.x] = 'B';
//
//                // 인접한 8개 위치 확인하고 아직 열리지 않은 위치를 Queue에 추가
//                for (int i = 0; i < 8; i++) {
//                    int ny = currentPosition.y + dy[i];
//                    int nx = currentPosition.x + dx[i];
//
//                    // 인접한 위치가 지뢰밭 범위 내에 있는지 확인, 그리고 아직 열리지 않았는지 확인
//                    if (ny >= 0 && ny < height && nx >= 0 && nx < width && resultBoard[ny][nx] == 'E') {
//                        // 방문 여부를 확인하기 위해 해당 위치를 0으로 변경하기
//                        resultBoard[ny][nx] = '0';
//                        // 인접한 위치를 큐에 추가하고 BFS를 진행하기
//                        queue.offer(new Position(ny, nx));
//                    }
//                }
//            }
//        }
//
//        // 최종 결과를 문자열 배열로 변환하여 반환하기 ver2
//        for (int i = 0; i < height; i++) {
//            for (int j = 0; j < width; j++) {
//                if (resultBoard[i][j] == 'M' || resultBoard[i][j] == '0') {
//                    resultBoard[i][j] = 'E';
//                }
//            }
//            board[i] = String.valueOf(resultBoard[i]);
//        }
//
//        return board;
//    }
//}
