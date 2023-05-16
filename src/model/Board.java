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
        this.grid = new Cell[7][9];
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
        grid[0][2].setChess(RedRat);
        grid[4][2].setChess(RedWolf);
        grid[5][1].setChess(RedCat);
        grid[6][0].setChess(RedTiger);
        grid[6][2].setChess(RedElephant);

        grid[0][6].setChess(BlueElephant);
        grid[0][8].setChess(BlueTiger);
        grid[1][7].setChess(BlueCat);
        grid[2][6].setChess(BlueWolf);
        grid[4][6].setChess(BlueLeopard);
        grid[5][7].setChess(BlueDog);
        grid[6][6].setChess(BlueRat);
        grid[6][8].setChess(BlueLion);
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
        if(getCanmovepoints(src).contains(dest)){
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
            //Steps之后再写//
        }
    }
    public ArrayList<BoardPoint> getCanmovepoints(BoardPoint src){
        ArrayList<BoardPoint> list=new ArrayList<BoardPoint>();
        if(getChessAt(src)==RedRat||getChessAt(src)==BlueRat){//鼠鼠的Move判断//
           if(src.getRow()!=0){
               BoardPoint dest=new BoardPoint(src.getRow()-1,src.getCol());
               if(grid[src.getRow()-1][src.getCol()].chess!=null){
                    if(grid[src.getRow()-1][src.getCol()].chess.rank<=getChessAt(src).rank){
                        list.add(dest);
                    }
               }
               else{
                   list.add(dest);
               }
           }
            if(src.getRow()!=6){
                BoardPoint dest=new BoardPoint(src.getRow()+1,src.getCol());
                if(grid[src.getRow()+1][src.getCol()].chess!=null){
                    if(grid[src.getRow()+1][src.getCol()].chess.rank<=getChessAt(src).rank){
                        list.add(dest);
                    }
                }
                else{
                    list.add(dest);
                }
            }
            if(src.getCol()!=0){
                BoardPoint dest=new BoardPoint(src.getRow(),src.getCol()-1);
                if(grid[src.getRow()][src.getCol()-1].chess!=null){
                    if(grid[src.getRow()][src.getCol()-1].chess.rank<=getChessAt(src).rank){
                        list.add(dest);
                    }
                }
                else{
                    list.add(dest);
                }
            }
            if(src.getRow()!=8){
                BoardPoint dest=new BoardPoint(src.getRow(),src.getCol()+1);
                if(grid[src.getRow()][src.getCol()+1].chess!=null){
                    if(grid[src.getRow()][src.getCol()+1].chess.rank<=getChessAt(src).rank){
                        list.add(dest);
                    }
                }
                else{
                    list.add(dest);
                }
            }
        }
        else if(getChessAt(src)==RedTiger||getChessAt(src)==RedLion||getChessAt(src)==BlueTiger||getChessAt(src)==BlueLion){//狮子老虎的Move判断//
            if(src.getRow()!=0){
                BoardPoint dest=new BoardPoint(src.getRow()-1,src.getCol());
                if(grid[src.getRow()-1][src.getCol()].chess!=null){
                    if(grid[src.getRow()-1][src.getCol()].chess.rank<=getChessAt(src).rank){
                        list.add(dest);
                    }
                }
                else if(isRiver(dest)){
                    if(grid[src.getRow()-1][src.getCol()].chess!=null||grid[src.getRow()-2][src.getCol()].chess!=null){}
                    else{
                        BoardPoint overRiver=new BoardPoint(src.getRow()-3,src.getCol());
                    }
                }
                else{
                    list.add(dest);
                }
            }
            if(src.getRow()!=6){
                BoardPoint dest=new BoardPoint(src.getRow()+1,src.getCol());
                if(grid[src.getRow()+1][src.getCol()].chess!=null){
                    if(grid[src.getRow()+1][src.getCol()].chess.rank<=getChessAt(src).rank){
                        list.add(dest);
                    }
                }
                else if(isRiver(dest)){
                    if(grid[src.getRow()+1][src.getCol()].chess!=null||grid[src.getRow()+2][src.getCol()].chess!=null){}
                    else{
                        BoardPoint overRiver=new BoardPoint(src.getRow()+3,src.getCol());
                    }
                }
                else{
                    list.add(dest);
                }
            }
            if(src.getCol()!=0){
                BoardPoint dest=new BoardPoint(src.getRow(),src.getCol()-1);
                if(grid[src.getRow()][src.getCol()-1].chess!=null){
                    if(grid[src.getRow()][src.getCol()-1].chess.rank<=getChessAt(src).rank){
                        list.add(dest);
                    }
                }
                else if(isRiver(dest)){
                    if(grid[src.getRow()][src.getCol()-1].chess!=null||grid[src.getRow()][src.getCol()-2].chess!=null||grid[src.getRow()][src.getCol()-3].chess!=null){}
                    else{
                        BoardPoint overRiver=new BoardPoint(src.getRow(),src.getCol()-4);
                    }
                }
                else{
                    list.add(dest);
                }
            }
            if(src.getCol()!=8){
                BoardPoint dest=new BoardPoint(src.getRow(),src.getCol()+1);
                if(grid[src.getRow()][src.getCol()+1].chess!=null){
                    if(grid[src.getRow()][src.getCol()+1].chess.rank<=getChessAt(src).rank){
                        list.add(dest);
                    }
                }
                else if(isRiver(dest)){
                    if(grid[src.getRow()][src.getCol()+1].chess!=null||grid[src.getRow()][src.getCol()+2].chess!=null||grid[src.getRow()][src.getCol()+3].chess!=null){}
                    else{
                        BoardPoint overRiver=new BoardPoint(src.getRow(),src.getCol()+4);
                    }
                }
                else{
                    list.add(dest);
                }
            }
        }
        else{//其他动物的Move判断//
            if(src.getRow()!=0){
                BoardPoint dest=new BoardPoint(src.getRow()-1,src.getCol());
                if(grid[src.getRow()-1][src.getCol()].chess!=null){
                    if(grid[src.getRow()-1][src.getCol()].chess.rank<=getChessAt(src).rank){
                        list.add(dest);
                    }
                }
                else if(!isRiver(dest)){
                    list.add(dest);
                }
            }
            if(src.getRow()!=6){
                BoardPoint dest=new BoardPoint(src.getRow()+1,src.getCol());
                if(grid[src.getRow()+1][src.getCol()].chess!=null){
                    if(grid[src.getRow()+1][src.getCol()].chess.rank<=getChessAt(src).rank){
                        list.add(dest);
                    }
                }
                else if(!isRiver(dest)){
                    list.add(dest);
                }
            }
            if(src.getCol()!=0){
                BoardPoint dest=new BoardPoint(src.getRow(),src.getCol()-1);
                if(grid[src.getRow()][src.getCol()-1].chess!=null){
                    if(grid[src.getRow()][src.getCol()-1].chess.rank<=getChessAt(src).rank){
                        list.add(dest);
                    }
                }
                else if(!isRiver(dest)){
                    list.add(dest);
                }
            }
            if(src.getCol()!=8){
                BoardPoint dest=new BoardPoint(src.getRow(),src.getCol()+1);
                if(grid[src.getRow()][src.getCol()+1].chess!=null){
                    if(grid[src.getRow()][src.getCol()+1].chess.rank<=getChessAt(src).rank){
                        list.add(dest);
                    }
                }
                else if(!isRiver(dest)){
                    list.add(dest);
                }
            }
        }
        return list;
    }

    private boolean canJumpRiver(BoardPoint src, BoardPoint dest) {
        if(getChessAt(src)==RedLion||getChessAt(src)==RedTiger||getChessAt(src)==BlueLion||getChessAt(src)==BlueTiger){
            BoardPoint boardPoint1=new BoardPoint(src.getRow(),src.getCol()+1);
            BoardPoint boardPoint2=new BoardPoint(dest.getRow(),dest.getCol()+1);
            BoardPoint boardPoint3=new BoardPoint(src.getRow()+1,dest.getCol());
            BoardPoint boardPoint4=new BoardPoint(dest.getRow()+1,dest.getCol());
            if(src.getRow()==dest.getRow()&&dest.getCol()-src.getCol()==2&&isRiver(boardPoint1)) {
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

    public boolean canEat(BoardPoint src, BoardPoint dest) {
        Chess attacker = getChessAt(src);
        if(getChessAt(dest)!=null){
            Chess defender = getChessAt(dest);
            if(attacker.getPlayer().getColor()!=defender.getPlayer().getColor()){
                if(canMove(src,dest)){
                    if(attacker.rank>=defender.rank){
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
        else{
            return false;
        }
        //空的判断，只能吃对方的，判断rank，判断可走性（比如河）//
    }
    private void jumpRiver(BoardPoint src,BoardPoint dest){
        if(canJumpRiver(src,dest)){
            setChess(dest,getChessAt(src));
            removeChess(src);
            Step step=new Step(src,dest,getChessPlayer(src));
            steps.add(step);
            //这边加进step的部分写的可能有点问题，之后再看看//
        }
    }
    private boolean isRiver(BoardPoint point) {
        if(((point.getRow()==1||point.getRow()==2||point.getRow()==4||point.getRow()==5)&&(point.getCol()==3||point.getCol()==4||point.getCol()==5))){
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
          //Steps//
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
