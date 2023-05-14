package model;

import java.io.Serializable;

//serializable是便于将他化为字节流存储
public class Cell implements Serializable {
    // the position for chess
    public Chess chess;
    public Chess getChess() {
        return chess;
    }

    public void setChess(Chess chess) {
        this.chess = chess;
    }

    public void removeChess() {
        this.chess = null;
    }
}
