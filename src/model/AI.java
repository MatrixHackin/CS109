package model;
import view.BoardView;

import java.util.Random;
public class AI {
    private Player AIplayer=Player.RED;
    public BoardPoint src;
    public BoardPoint dest;
    public Board EasyAI(Board board){
        Random random=new Random();
        int chooseChess=random.nextInt(1,8);
        int chooseStep= random.nextInt(1,4);
        while(board.getAIChess(chooseChess)==null){
            if(board.getAIChess(chooseChess)!=null){
                break;
            }
            chooseChess=random.nextInt(1,8);
        }
        BoardPoint upPoint=new BoardPoint(board.getChessPoint(board.getAIChess(chooseChess)).getRow()+1,board.getChessPoint(board.getAIChess(chooseChess)).getCol());
        BoardPoint downPoint=new BoardPoint(board.getChessPoint(board.getAIChess(chooseChess)).getRow()-1,board.getChessPoint(board.getAIChess(chooseChess)).getCol());
        BoardPoint leftPoint=new BoardPoint(board.getChessPoint(board.getAIChess(chooseChess)).getRow(),board.getChessPoint(board.getAIChess(chooseChess)).getCol()-1);
        BoardPoint rightPoint=new BoardPoint(board.getChessPoint(board.getAIChess(chooseChess)).getRow(),board.getChessPoint(board.getAIChess(chooseChess)).getCol()+1);
        if(chooseStep==1&&board.canMove(board.getChessPoint(board.getAIChess(chooseChess)),upPoint)){
            src=board.getChessPoint(board.getAIChess(chooseChess));
            dest=upPoint;
            board.move(board.getChessPoint(board.getAIChess(chooseChess)),upPoint);
        }
        else if(chooseStep==2&&board.canMove(board.getChessPoint(board.getAIChess(chooseChess)),downPoint)){
            src=board.getChessPoint(board.getAIChess(chooseChess));
            dest=downPoint;
            board.move(board.getChessPoint(board.getAIChess(chooseChess)),downPoint);
        }
        else if(chooseStep==3&&board.canMove(board.getChessPoint(board.getAIChess(chooseChess)),leftPoint)){
            src=board.getChessPoint(board.getAIChess(chooseChess));
            dest=leftPoint;
            board.move(board.getChessPoint(board.getAIChess(chooseChess)),leftPoint);
        }
        else if(chooseStep==4&&board.canMove(board.getChessPoint(board.getAIChess(chooseChess)),rightPoint)){
            src=board.getChessPoint(board.getAIChess(chooseChess));
            dest=rightPoint;
            board.move(board.getChessPoint(board.getAIChess(chooseChess)),rightPoint);
        }
        return board;
    }
}
