//package com.will.Minesweeper.legacy;
//
//import com.will.Minesweeper.service.MineBoardGenerator;
//import com.will.Minesweeper.service.MineSweeperProcessor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import javax.servlet.http.HttpSession;
//
////@Controller
//public class OldMineSweeperController {
//
//    @Autowired
//    private MineBoardGenerator generator;
//    @Autowired
//    private MineSweeperProcessor processor;
//
////    @GetMapping("/")
//    public String main(HttpSession session) {
//        session.invalidate();
//        return "main";
//    }
//
////    @PostMapping("/startGame")
//    public String startGame(@RequestParam(name = "boardSize") int boardSize,
//                            @RequestParam(name = "mineQuantity") int mineQuantity,
//                            HttpSession session) {
//        System.out.println("boardSize = " + boardSize);
//        System.out.println("mineQuantity = " + mineQuantity);
//
//        // 보드 생성
////        String[] board = generator.generateBoard(mineQuantity, boardSize);
//        char[][] board = generator.generateBoard(mineQuantity, boardSize);
//        System.out.println("Generated Board:");
////        for (int i = 0; i < board.length; i++) {
////            System.out.println(board[i]);
////        }
//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < board[0].length; j++){
//                System.out.print(board[i][j] + " ");
//            }
//            System.out.println();
//        }
//
//        // 2차원 문자 배열로 변환
////        char[][] boardArray = new char[board.length][];
////        for (int i = 0; i < board.length; i++) {
////            boardArray[i] = board[i].toCharArray();
////        }
//
////        session.setAttribute("board", boardArray);
//        session.setAttribute("board", board);
//
//        // 게임 페이지로 리다이렉트
//        return "game";
//    }
//
////    @PostMapping("/process")
//    public String processCellClick(@RequestParam("x") int x,
//                                   @RequestParam("y") int y,
//                                   HttpSession session) {
//        System.out.println("Clicked Position: (" + y + ", " + x + ")");
//
//        char[][] board = (char[][]) session.getAttribute("board");
////        String[] result = new String[board.length];
////
////        for (int i = 0; i < board.length; i++) {
////            result[i] = String.valueOf(board[i]);
////        }
//        System.out.println("Current Board:");
////        for (int i = 0; i < result.length; i++) {
////            System.out.println(result[i]);
////        }
//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < board[0].length; j++){
//                System.out.print(board[i][j] + " ");
//            }
//            System.out.println();
//        }
//
//        // 게임 로직 처리
//        board = processor.process(board, y, x);
//        System.out.println("Result Board:");
////        for (int i = 0; i < result.length; i++) {
////            System.out.println(result[i]);
////        }
//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < board[0].length; j++){
//                System.out.print(board[i][j] + " ");
//            }
//            System.out.println();
//        }
//
////        for (int i = 0; i < board.length; i++) {
////            board[i] = result[i].toCharArray();
////        }
//        boolean isVictory = processor.isVictory();
//        boolean isGameOver = processor.isGameOver(board);
//        System.out.println("isVictory = " + isVictory);
//        System.out.println("isGameOver = " + isGameOver);
//
//        session.setAttribute("board", board);
//        session.setAttribute("isVictory", isVictory);
//        session.setAttribute("isGameOver", isGameOver);
//
//        return "game";
//    }
//
//}
