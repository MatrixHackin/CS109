package model;

import java.util.ArrayList;

public class Board {
    public Cell[][] grid;
    public ArrayList<Step> steps;
    public ArrayList<Chess> blueDead;
    public ArrayList<Chess> redDead;

    public Board() {
        this.grid = new Cell[7][9];
        //  steps = new ArrayList<>();
        //  blueDead = new ArrayList<>();
        //  redDead = new ArrayList<>();

        initGrid();
        initPieces();
    }
    public void initGrid() {
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                grid[i][j] = new Cell();
            }
        }
    }

    public void initPieces() {
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                grid[i][j].removeChess();
            }
        }

        //用grid.setpiece在grid的规定位置放chess
    }
    public Cell[][] getGrid() {
        return grid;
    }

    public Player getChessPlayer(BoardPoint point) {
        return getCellAt(point).getChess().getPlayer();
    }
    public Cell getCellAt(BoardPoint point) {
        return grid[point.getRow()][point.getCol()];
    }

    public Chess getChessAt(BoardPoint point) {
        return getCellAt(point).getChess();
    }
    private void setChess(BoardPoint point, Chess chess) {
        getCellAt(point).setChess(chess);
        if (isOpponentTrap(point, chess.getPlayer())) {
            chess.rank = 0;
        }
    }
    private Chess removeChess(BoardPoint point) {
        Chess chess = getChessAt(point);
        getCellAt(point).removeChess();
        return chess;
    }
    public boolean canMove(BoardPoint src, BoardPoint dest) {
        //原本未选中棋子不能move,move距离大于1不能move,dest是河判断棋子类型//
    }
    private boolean canJumpRiver(BoardPoint src, BoardPoint dest){

    }

    public boolean canEat(BoardPoint src, BoardPoint dest) {

        Chess attacker = getChessAt(src);
        Chess defender = getChessAt(dest);
        //空的判断，只能吃对方的，判断rank，判断可走性（比如河）//
    }

    private boolean isRiver(BoardPoint point) {

    }

    private boolean isOwnDens(BoardPoint point, Player color) {

    }

    public void move(BoardPoint src, BoardPoint dest) {
        //判断可走性，放子，移子，加到step//
    }

    public void eat(BoardPoint src, BoardPoint dest) {
        Chess attacker = removeChess(src);
        Chess defender = removeChess(dest);
        setChess(dest, attacker);
        //判断能不能吃，吃，记录死了的棋子放在list里面，记录step//
    }
    private boolean isOpponentDens(BoardPoint point, Player color) {

    }

    private boolean isOpponentTrap(BoardPoint point, Player color) {

    }
}
