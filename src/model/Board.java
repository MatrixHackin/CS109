package model;

import java.util.ArrayList;

public class Board {
    public Cell[][] grid;
    public ArrayList<Step> steps;
    public ArrayList<Chess> blueDead;
    public ArrayList<Chess> redDead;

    Chess  BlueRat=new Chess(Player.BLUE,1);
    Chess  BlueCat=new Chess(Player.BLUE,2);
    Chess  BlueDog=new Chess(Player.BLUE,3);
    Chess  BlueWolf=new Chess(Player.BLUE,4);
    Chess  BlueLeopard=new Chess(Player.BLUE,5);
    Chess  BlueTiger=new Chess(Player.BLUE,6);
    Chess  BlueLion=new Chess(Player.BLUE,7);
    Chess  BlueElephant=new Chess(Player.BLUE,8);

    Chess  RedRat=new Chess(Player.RED,1);
    Chess  RedCat=new Chess(Player.RED,2);
    Chess  RedDog=new Chess(Player.RED,3);
    Chess  RedWolf=new Chess(Player.RED,4);
    Chess  RedLeopard=new Chess(Player.RED,5);
    Chess  RedTiger=new Chess(Player.RED,6);
    Chess  RedLion=new Chess(Player.RED,7);
    Chess  RedElephant=new Chess(Player.RED,8);
    public Board() {
        this.grid = new Cell[9][7];
        steps = new ArrayList<>();
        blueDead = new ArrayList<>();
        redDead = new ArrayList<>();

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

        grid[0][0].setChess(RedLion);
        grid[1][1].setChess(RedDog);
        grid[2][2].setChess(RedLeopard);
        grid[2][0].setChess(RedRat);
        grid[2][4].setChess(RedWolf);
        grid[1][5].setChess(RedCat);
        grid[0][6].setChess(RedTiger);
        grid[2][6].setChess(RedElephant);

        grid[6][0].setChess(BlueElephant);
        grid[8][0].setChess(BlueTiger);
        grid[7][1].setChess(BlueCat);
        grid[6][2].setChess(BlueWolf);
        grid[6][4].setChess(BlueLeopard);
        grid[7][5].setChess(BlueDog);
        grid[6][6].setChess(BlueRat);
        grid[8][6].setChess(BlueLion);
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
        if(getChessAt(src)!=null)//我还在想怎么调用Controller里的被选中的棋子，这部分我之后再改改//
        {
            if(((dest.getRow()-src.getRow())^2+(dest.getCol()-src.getCol())^2)==1){
                if(isRiver(dest)){
                    if(grid[src.getRow()][src.getCol()].chess==RedRat||grid[src.getRow()][src.getCol()].chess==BlueRat){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                else{
                    return true;
                }
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }
    private boolean canJumpRiver(BoardPoint src, BoardPoint dest){
        if(getChessAt(src)==RedLion||getChessAt(src)==RedTiger||getChessAt(src)==BlueLion||getChessAt(src)==BlueTiger){
            if(getChessAt(src).rank>=getChessAt(dest).rank&&getChessPlayer(src)!=getChessPlayer(dest)){
                BoardPoint boardPoint1=new BoardPoint(src.getRow(),src.getCol()+1);
                BoardPoint boardPoint2=new BoardPoint(dest.getRow(),dest.getCol()+1);
                BoardPoint boardPoint3=new BoardPoint(src.getRow()+1,dest.getCol());
                BoardPoint boardPoint4=new BoardPoint(dest.getRow()+1,dest.getCol());
                if(src.getRow()==dest.getRow()&&dest.getCol()-src.getCol()==2&&isRiver(boardPoint1))//这里还有一种情况要考虑：有鼠鼠在河里//
                {
                    return true;
                }
                else if(src.getRow()==dest.getRow()&&src.getCol()-dest.getCol()==2&&isRiver(boardPoint2)) {
                    return true;
                }
                else if(src.getCol()==dest.getCol()&&dest.getRow()-src.getRow()==3&&isRiver(boardPoint3)){
                    return true;
                }
                else if(src.getCol()==dest.getCol()&&src.getRow()-dest.getRow()==3&&isRiver(boardPoint4)){
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }
    private void jumpRiver(BoardPoint src,BoardPoint dest){
        if(canJumpRiver(src,dest)){
            if(getChessAt(dest)!=null){
                eat(src,dest);
                Step step=new Step(src,dest,getChessPlayer(src));
                steps.add(step);
            }
            setChess(dest,getChessAt(src));
            removeChess(src);
            Step step=new Step(src,dest,getChessPlayer(src));
            steps.add(step);
            //这边加进step的部分写的可能有点问题，之后再看看//
        }
    }

    private boolean isRiver(BoardPoint point) {
        if(((point.getRow()==3||point.getRow()==4||point.getRow()==5)&&(point.getCol()==1||point.getCol()==2||point.getCol()==4||point.getCol()==5))){
            return true;
        }
        else{
            return false;
        }
    }
    private boolean isOwnDens(BoardPoint point, Player color) {
        if((color==Player.BLUE&&point.getRow()==8&&point.getCol()==3)||(color==Player.RED&&point.getRow()==0&&point.getCol()==3)){
            return true;
        }
        else{
            return false;
        }
    }

    public void move(BoardPoint src, BoardPoint dest) {
        if(canMove(src,dest)){
            setChess(dest,getChessAt(src));
            removeChess(src);
            Step step=new Step(src,dest,getChessPlayer(src));
            steps.add(step);
        }
    }

    public boolean canEat(BoardPoint src, BoardPoint dest) {
        Chess attacker = getChessAt(src);
        Chess defender = getChessAt(dest);
        if(attacker.getPlayer().getColor()!=defender.getPlayer().getColor()){
            if(canMove(src,dest)){
                if(attacker.rank==1&&defender.rank==8&&!isRiver(src)){
                    return true;
                }
                else if(attacker.rank>=defender.rank){
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
        //空的判断，只能吃对方的，判断rank，判断可走性（比如河）//
    }
    public void eat(BoardPoint src, BoardPoint dest) {
        Chess attacker = removeChess(src);
        Chess defender = removeChess(dest);
        if(canEat(src,dest)){
            setChess(dest, attacker);
            if(defender.getPlayer()==Player.BLUE) {
                blueDead.add(defender);
            }
            else{
                redDead.add(defender);
            }
            Step step=new Step(src,dest,getChessPlayer(src),defender);
            steps.add(step);
        }
        //判断能不能吃，吃，记录死了的棋子放在list里面，记录step//
    }
    private boolean isOpponentDens(BoardPoint point, Player color) {
        if((color==Player.RED&&point.getRow()==8&&point.getCol()==3)||(color==Player.BLUE&&point.getRow()==0&&point.getCol()==3)){
            return true;
        }
        else{
            return false;
        }
    }
    private boolean isOpponentTrap(BoardPoint point, Player color) {
        if(color==Player.RED&&((point.getRow()==8&&(point.getCol()==2||point.getCol()==4))||(point.getRow()==7&&point.getCol()==3))){
            return true;
        }
        else if(color==Player.BLUE&&((point.getRow()==0&&(point.getCol()==2||point.getCol()==4))||(point.getRow()==1&&point.getCol()==3))){
            return true;
        }
        else{
            return false;
        }
    }
}
