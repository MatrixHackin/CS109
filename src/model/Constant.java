package model;

public enum Constant {
    CHESSBOARD_ROW_SIZE(7),CHESSBOARD_COL_SIZE(9), GAMEBG_ROW_SIZE(1000),GAMEBG_COL_SIZE(700);

    private final int num;
    Constant(int num){
        this.num = num;
    }

    public int getNum() {
        return num;
    }
}
