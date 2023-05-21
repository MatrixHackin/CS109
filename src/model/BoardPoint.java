package model;

import java.io.Serializable;

public class BoardPoint implements Serializable {
    private final int row;
    private final int col;

    public BoardPoint(int row, int col) {
        this.row = row;
        this.col = col;
    }


    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

}
