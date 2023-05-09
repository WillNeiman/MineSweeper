package com.will.Minesweeper.service;

import org.springframework.stereotype.Component;

// y, x 좌표 저장
class Position {
    int y;
    int x;

    Position(int y, int x){
        this.y = y;
        this.x = x;
    }
}
