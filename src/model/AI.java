package model;

import java.util.Random;
public class AI {
    private Player AIplayer=Player.RED;
    public BoardPoint src;
    public BoardPoint dest;
    public boolean LastActioniseat =false;
    public void EasyAI(Board board){
        Random random=new Random();
        int chooseChess=random.nextInt(1,8);
        int chooseStep= random.nextInt(0,3);
        while(board.getAIChess(chooseChess)==null||board.getCanmovepoints(board.getChessPoint(board.getAIChess(chooseChess))).size()<=chooseStep){
            chooseChess=random.nextInt(1,8);
            chooseStep=random.nextInt(0,3);
        }
        if(chooseStep==0&&board.canMove(board.getChessPoint(board.getAIChess(chooseChess)),board.getCanmovepoints(board.getChessPoint(board.getAIChess(chooseChess))).get(0))){
            src=board.getChessPoint(board.getAIChess(chooseChess));
            dest=board.getCanmovepoints(board.getChessPoint(board.getAIChess(chooseChess))).get(0);
            if(board.canEat(src,dest)){
                board.eat(src,dest);
                LastActioniseat =true;
            }
            else{
                board.move(src,dest);
                LastActioniseat =false;
            }
        }
        else if(chooseStep==1&&board.canMove(board.getChessPoint(board.getAIChess(chooseChess)),board.getCanmovepoints(board.getChessPoint(board.getAIChess(chooseChess))).get(1))){
            src=board.getChessPoint(board.getAIChess(chooseChess));
            dest=board.getCanmovepoints(board.getChessPoint(board.getAIChess(chooseChess))).get(1);
            if(board.canEat(src,dest)){
                board.eat(src,dest);
                LastActioniseat =true;
            }
            else{
                board.move(src,dest);
                LastActioniseat =false;
            }
        }
        else if(chooseStep==2&&board.canMove(board.getChessPoint(board.getAIChess(chooseChess)),board.getCanmovepoints(board.getChessPoint(board.getAIChess(chooseChess))).get(2))){
            src=board.getChessPoint(board.getAIChess(chooseChess));
            dest=board.getCanmovepoints(board.getChessPoint(board.getAIChess(chooseChess))).get(2);
            if(board.canEat(src,dest)){
                board.eat(src,dest);
                LastActioniseat =true;
            }
            else{
                board.move(src,dest);
                LastActioniseat =false;
            }
        }
        else if(chooseStep==3&&board.canMove(board.getChessPoint(board.getAIChess(chooseChess)),board.getCanmovepoints(board.getChessPoint(board.getAIChess(chooseChess))).get(3))){
            src=board.getChessPoint(board.getAIChess(chooseChess));
            dest=board.getCanmovepoints(board.getChessPoint(board.getAIChess(chooseChess))).get(3);
            if(board.canEat(src,dest)){
                board.eat(src,dest);
                LastActioniseat =true;
            }
            else{
                board.move(src,dest);
                LastActioniseat =false;
            }
        }
    }
    public void DiffAI(Board board){
        for(int i=1;i<=8;i++){
            if(board.getCanmovepoints(board.getChessPoint(board.getAIChess(i)))!=null){
                for(int j=0;j<board.getCanmovepoints(board.getChessPoint(board.getAIChess(i))).size();j++){
                    if(board.canEat(board.getChessPoint(board.getAIChess(i)),board.getCanmovepoints(board.getChessPoint(board.getAIChess(i))).get(j))){
                        board.eat((board.getChessPoint(board.getAIChess(i))),board.getCanmovepoints(board.getChessPoint(board.getAIChess(i))).get(j));
                        break;
                    }
                }
            }
        }


    }
}
