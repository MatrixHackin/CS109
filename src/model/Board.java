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
        boolean judge=false;
        for(int i=0;i<getCanmovepoints(src).size();i++){
            if(getCanmovepoints(src).get(i).getRow()==dest.getRow()&&getCanmovepoints(src).get(i).getCol()==dest.getCol()){
                judge=true;
                break;
            }
        }
        return judge;
    }
    public void move(BoardPoint src, BoardPoint dest) {
        if(isOpponentTrap(dest,getChessPlayer(src))){
            setChess(dest, getChessAt(src));
            removeChess(src);
            getChessAt(dest).setRank(0);
        }
        else{
            setChess(dest, getChessAt(src));
            removeChess(src);
        }
            //Steps之后再写//
    }
    public ArrayList<BoardPoint> getCanmovepoints(BoardPoint src){
        ArrayList<BoardPoint> list=new ArrayList<BoardPoint>();
        if(getChessAt(src)==RedRat||getChessAt(src)==BlueRat){
            //鼠鼠的Move判断//
           if(src.getRow()!=0){
               BoardPoint dest=new BoardPoint(src.getRow()-1,src.getCol());
               if(grid[src.getRow()-1][src.getCol()].chess!=null){
                   if(!isRiver(src)){
                       if(getChessPlayer(dest)!=getChessPlayer(src)){
                           if(grid[src.getRow()-1][src.getCol()].chess.rank<=getChessAt(src).rank){
                               list.add(dest);
                           }
                           else if(grid[src.getRow()-1][src.getCol()].chess.rank==8){
                               list.add(dest);
                           }
                       }
                   }
               }
               else if(isOwnDens(dest,getChessPlayer(src))){}
               else {
                   list.add(dest);
               }
           }
            if(src.getRow()!=6){
                BoardPoint dest=new BoardPoint(src.getRow()+1,src.getCol());
                if(grid[src.getRow()+1][src.getCol()].chess!=null){
                    if(!isRiver(src)){
                        if(getChessPlayer(dest)!=getChessPlayer(src)){
                            if(grid[src.getRow()+1][src.getCol()].chess.rank<=getChessAt(src).rank){
                                list.add(dest);
                            }
                            else if(grid[src.getRow()+1][src.getCol()].chess.rank==8){
                                list.add(dest);
                            }
                        }
                    }
                }
                else if(isOwnDens(dest,getChessPlayer(src))){}
                else{
                    list.add(dest);
                }
            }
            if(src.getCol()!=0){
                BoardPoint dest=new BoardPoint(src.getRow(),src.getCol()-1);
                if(grid[src.getRow()][src.getCol()-1].chess!=null){
                    if(!isRiver(src)){
                        if(getChessPlayer(dest)!=getChessPlayer(src)){
                            if(grid[src.getRow()][src.getCol()-1].chess.rank<=getChessAt(src).rank){
                                list.add(dest);
                            }
                            else if(grid[src.getRow()][src.getCol()-1].chess.rank==8){
                                list.add(dest);
                            }
                        }
                    }
                }
                else if(isOwnDens(dest,getChessPlayer(src))){}
                else{
                    list.add(dest);
                }
            }
            if(src.getRow()!=8){
                BoardPoint dest=new BoardPoint(src.getRow(),src.getCol()+1);
                if(grid[src.getRow()][src.getCol()+1].chess!=null){
                    if(!isRiver(src)){
                        if(getChessPlayer(dest)!=getChessPlayer(src)){
                            if(grid[src.getRow()][src.getCol()+1].chess.rank<=getChessAt(src).rank){
                                list.add(dest);
                            }
                            else if(grid[src.getRow()][src.getCol()+1].chess.rank==8){
                                list.add(dest);
                            }
                        }
                    }
                }
                else if(isOwnDens(dest,getChessPlayer(src))){}
                else {
                    list.add(dest);
                }
            }
        }
        else if(getChessAt(src)==RedTiger||getChessAt(src)==RedLion||getChessAt(src)==BlueTiger||getChessAt(src)==BlueLion){
            //狮子老虎的Move判断//
            if(src.getRow()!=0){
                BoardPoint dest=new BoardPoint(src.getRow()-1,src.getCol());
                if(grid[src.getRow()-1][src.getCol()].chess!=null){
                    if(getChessPlayer(dest)!=getChessPlayer(src)){
                        if(grid[src.getRow()-1][src.getCol()].chess.rank<=getChessAt(src).rank){
                            list.add(dest);
                        }
                    }
                }
                else if(isRiver(dest)){
                    if(grid[src.getRow()-1][src.getCol()].chess!=null||grid[src.getRow()-2][src.getCol()].chess!=null){}
                    else if(grid[src.getRow()-3][src.getCol()].chess!=null&&grid[src.getRow()-3][src.getCol()].chess.rank>getChessAt(src).rank){}
                    else{
                        BoardPoint overRiver=new BoardPoint(src.getRow()-3,src.getCol());
                        list.add(overRiver);
                    }
                }
                else if(isOwnDens(dest,getChessPlayer(src))){}
                else{
                    list.add(dest);
                }
            }
            if(src.getRow()!=6){
                BoardPoint dest=new BoardPoint(src.getRow()+1,src.getCol());
                if(grid[src.getRow()+1][src.getCol()].chess!=null){
                    if(getChessPlayer(dest)!=getChessPlayer(src)){
                        if(grid[src.getRow()+1][src.getCol()].chess.rank<=getChessAt(src).rank){
                            list.add(dest);
                        }
                    }
                }
                else if(isRiver(dest)){
                    if(grid[src.getRow()+1][src.getCol()].chess!=null||grid[src.getRow()+2][src.getCol()].chess!=null){}
                    else if(grid[src.getRow()+3][src.getCol()].chess!=null&&grid[src.getRow()+3][src.getCol()].chess.rank>getChessAt(src).rank){}
                    else{
                        BoardPoint overRiver=new BoardPoint(src.getRow()+3,src.getCol());
                        list.add(overRiver);
                    }
                }
                else if(isOwnDens(dest,getChessPlayer(src))){}
                else{
                    list.add(dest);
                }
            }
            if(src.getCol()!=0){
                BoardPoint dest=new BoardPoint(src.getRow(),src.getCol()-1);
                if(grid[src.getRow()][src.getCol()-1].chess!=null){
                    if(getChessPlayer(dest)!=getChessPlayer(src)){
                        if(grid[src.getRow()][src.getCol()-1].chess.rank<=getChessAt(src).rank){
                            list.add(dest);
                        }
                    }
                }
                else if(isRiver(dest)){
                    if(grid[src.getRow()][src.getCol()-1].chess!=null||grid[src.getRow()][src.getCol()-2].chess!=null||grid[src.getRow()][src.getCol()-3].chess!=null){}
                    else if(grid[src.getRow()][src.getCol()-4].chess!=null&&grid[src.getRow()][src.getCol()-4].chess.rank>getChessAt(src).rank){}
                    else{
                        BoardPoint overRiver=new BoardPoint(src.getRow(),src.getCol()-4);
                        list.add(overRiver);
                    }
                }
                else if(isOwnDens(dest,getChessPlayer(src))){}
                else{
                    list.add(dest);
                }
            }
            if(src.getCol()!=8){
                BoardPoint dest=new BoardPoint(src.getRow(),src.getCol()+1);
                if(grid[src.getRow()][src.getCol()+1].chess!=null){
                    if(getChessPlayer(dest)!=getChessPlayer(src)){
                        if(grid[src.getRow()][src.getCol()+1].chess.rank<=getChessAt(src).rank){
                            list.add(dest);
                        }
                    }
                }
                else if(isRiver(dest)){
                    if(grid[src.getRow()][src.getCol()+1].chess!=null||grid[src.getRow()][src.getCol()+2].chess!=null||grid[src.getRow()][src.getCol()+3].chess!=null){}
                    else if(grid[src.getRow()][src.getCol()+4].chess!=null&&grid[src.getRow()][src.getCol()+4].chess.rank>getChessAt(src).rank){}
                    else{
                        BoardPoint overRiver=new BoardPoint(src.getRow(),src.getCol()+4);
                        list.add(overRiver);
                    }
                }
                else if(isOwnDens(dest,getChessPlayer(src))){}
                else{
                    list.add(dest);
                }
            }
        }
        else{
            //其他动物的Move判断//
            if(src.getRow()!=0){
                BoardPoint dest=new BoardPoint(src.getRow()-1,src.getCol());
                if(grid[src.getRow()-1][src.getCol()].chess!=null){
                    if(getChessPlayer(dest)!=getChessPlayer(src)){
                        if(grid[src.getRow()-1][src.getCol()].chess.rank==1&&getChessAt(src).rank==8){}
                        else {
                            if (grid[src.getRow() - 1][src.getCol()].chess.rank <= getChessAt(src).rank) {
                                list.add(dest);
                            }
                        }
                    }
                }
                else if(isOwnDens(dest,getChessPlayer(src))){}
                else if(!isRiver(dest)){
                    list.add(dest);
                }
            }
            if(src.getRow()!=6){
                BoardPoint dest=new BoardPoint(src.getRow()+1,src.getCol());
                if(grid[src.getRow()+1][src.getCol()].chess!=null){
                    if(getChessPlayer(dest)!=getChessPlayer(src)){
                        if(grid[src.getRow()+1][src.getCol()].chess.rank==1&&getChessAt(src).rank==8){}
                        else {
                            if (grid[src.getRow()+1][src.getCol()].chess.rank <= getChessAt(src).rank) {
                                list.add(dest);
                            }
                        }
                    }
                }
                else if(isOwnDens(dest,getChessPlayer(src))){}
                else if(!isRiver(dest)){
                    list.add(dest);
                }
            }
            if(src.getCol()!=0){
                BoardPoint dest=new BoardPoint(src.getRow(),src.getCol()-1);
                if(grid[src.getRow()][src.getCol()-1].chess!=null){
                    if(getChessPlayer(dest)!=getChessPlayer(src)){
                        if(grid[src.getRow()][src.getCol()-1].chess.rank==1&&getChessAt(src).rank==8){}
                        else {
                            if (grid[src.getRow()][src.getCol()-1].chess.rank <= getChessAt(src).rank) {
                                list.add(dest);
                            }
                        }
                    }
                }
                else if(isOwnDens(dest,getChessPlayer(src))){}
                else if(!isRiver(dest)){
                    list.add(dest);
                }
            }
            if(src.getCol()!=8){
                BoardPoint dest=new BoardPoint(src.getRow(),src.getCol()+1);
                if(grid[src.getRow()][src.getCol()+1].chess!=null){
                    if(getChessPlayer(dest)!=getChessPlayer(src)){
                        if(grid[src.getRow()][src.getCol()+1].chess.rank==1&&getChessAt(src).rank==8){}
                        else {
                            if (grid[src.getRow()][src.getCol()+1].chess.rank <= getChessAt(src).rank) {
                                list.add(dest);
                            }
                        }
                    }
                }
                else if(isOwnDens(dest,getChessPlayer(src))){}
                else if(!isRiver(dest)){
                    list.add(dest);
                }
            }
        }
        return list;
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
                    else if(attacker.rank==1&&defender.rank==8){
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
    public boolean isRiver(BoardPoint point) {
        if(((point.getRow()==1||point.getRow()==2||point.getRow()==4||point.getRow()==5)&&(point.getCol()==3||point.getCol()==4||point.getCol()==5))){
            return true;
        }
        else{
            return false;
        }
    }
    public void eat(BoardPoint src, BoardPoint dest) {
        Chess attacker = getChessAt(src);
        Chess defender = getChessAt(dest);
        if(canEat(src,dest)){
            removeChess(src);
            removeChess(dest);
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
    public boolean isOwnDens(BoardPoint point, Player color) {
        if((color==Player.BLUE&&point.getRow()==3&&point.getCol()==8)||(color==Player.RED&&point.getRow()==3&&point.getCol()==0)){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean isOpponentDens(BoardPoint point, Player color) {
        if((color==Player.RED&&point.getRow()==3&&point.getCol()==8)||(color==Player.BLUE&&point.getRow()==3&&point.getCol()==0)){
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isOpponentTrap(BoardPoint point, Player color) {
        if(color==Player.BLUE&&((point.getCol()==0&&(point.getRow()==2||point.getRow()==4))||(point.getRow()==3&&point.getCol()==1))){
            return true;
        }
        else if(color==Player.RED&&((point.getCol()==8&&(point.getRow()==2||point.getRow()==4))||(point.getCol()==7&&point.getRow()==3))){
            return true;
        }
        else{
            return false;
        }
    }
}
