package controller;
import java.util.Random;

import model.*;
import listener.GameListener;
import view.AIFrame;
import view.BoardView;
import view.CellView;
import view.chessView.AnimalView;

import javax.swing.*;
import java.util.ArrayList;
import view.GameFrame;

public class Controller implements GameListener {

    public Board board;
    public BoardView boardView;
    public ArrayList<BoardPoint> canStepPoints;//把可以走的点返回来给我高亮
    public Player currentPlayer;
    public Player winner;
    public BoardPoint selectedPoint;//选中的点高亮
    public boolean isPlayback;
    public boolean skip;
    public boolean AI=false;
    public AI AIplayer=new AI();
    public AnimalView eaten;

 //   public JLabel timeLabel;
    public static Timer timer;
    public ArrayList<Board> steps=new ArrayList<>();

    public Controller(BoardView boardView, Board board) {
        this.boardView = boardView;
        this.board = board;
        this.currentPlayer = Player.BLUE;
        this.winner = null;
     //   timeLabel = boardView.timeLabel;
        isPlayback = false;
        skip = false;
        boardView.setController(this);
        boardView.initiateChessComponent(board);
        boardView.repaint();
    }

   public void changePlayer() {
        currentPlayer = currentPlayer == Player.BLUE ? Player.RED : Player.BLUE;
        if (currentPlayer == Player.BLUE)
            boardView.turnLabel.setBounds(930,120,100,100);
        else
            boardView.turnLabel.setBounds(35,120,100,100);
    }

    public void checkWin(BoardPoint point) {
        if(board.blueDead.size()==8){
            winner=Player.RED;
        }
        else if(board.redDead.size()==8){
            winner=Player.BLUE;
        }
        else if(board.isOpponentDens(point,currentPlayer)){
            winner=currentPlayer;
        }
        //判断棋子全吃完了，或者到老巢了//
    }

    public void winView() {
        JOptionPane.showMessageDialog(boardView, (winner == Player.BLUE ? "BLUE" : "RED") + " Win !");
    }

    @Override
    public void clickCell(BoardPoint point, CellView component) {
        if (selectedPoint != null) {
            if(board.canMove(selectedPoint,point)){
                board.move(selectedPoint, point);
                setAllCellsCanStepFalse();
                canStepPoints = null;
                boardView.setChessViewAtCell(point, boardView.removeChessViewAtGrid(selectedPoint));
                selectedPoint=null;
                steps.add(board);
                checkWin(point);
                if(winner!=null){}
                else{
                    changePlayer();
                }
                if(AI){
                    changePlayer();
                    AIplayer.EasyAI(board);
                    if(!AIplayer.LastAction){
                        boardView.setChessViewAtCell(AIplayer.dest,boardView.removeChessViewAtGrid(AIplayer.src));
                        steps.add(board);
                    }
                    else{
                        boardView.removeChessViewAtGrid(AIplayer.dest);
                        boardView.setChessViewAtCell(AIplayer.dest, boardView.removeChessViewAtGrid(AIplayer.src));
                        steps.add(board);
                    }
                }
                boardView.repaint();
                component.revalidate();
            }
            checkWin(point);
            if (winner != null) {
                winView();
                reset();
            }
        }
    }
    @Override
    public void clickChess(BoardPoint point, AnimalView component) {
        if (selectedPoint == null) {
            if (board.getChessPlayer(point).equals(currentPlayer)) {
                canStepPoints = getCanStepPoints(point);
                selectedPoint = point;
                component.setSelected(true);
                component.revalidate();
                component.repaint();
                boardView.repaint();
                boardView.revalidate();
            }
        } else if (selectedPoint.getRow()==point.getRow()&&selectedPoint.getCol()== point.getCol()) {
            selectedPoint = null;
            canStepPoints = null;
            setAllCellsCanStepFalse();
            component.setSelected(false);
            component.repaint();
            component.revalidate();
            boardView.repaint();
            boardView.revalidate();
        } else if (board.canEat(selectedPoint, point)) {
            board.eat(selectedPoint, point);
            eaten=boardView.removeChessViewAtGrid(point);
            boardView.setChessViewAtCell(point, boardView.removeChessViewAtGrid(selectedPoint));
            selectedPoint = null;
            setAllCellsCanStepFalse();
            steps.add(board);
            checkWin(point);
            if(winner!=null){}
            else{
                changePlayer();
            }
            if(AI){
                changePlayer();
                AIplayer.EasyAI(board);
                if(!AIplayer.LastAction){
                    boardView.setChessViewAtCell(AIplayer.dest,boardView.removeChessViewAtGrid(AIplayer.src));
                    steps.add(board);
                }
                else{
                    boardView.removeChessViewAtGrid(AIplayer.dest);
                    boardView.setChessViewAtCell(AIplayer.dest, boardView.removeChessViewAtGrid(AIplayer.src));
                    steps.add(board);
                }
            }
            boardView.repaint();
            boardView.revalidate();
            component.revalidate();
            if (winner != null) {
                winView();
                reset();
            }
        }
    }
    public void setAllCellsCanStepFalse() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 9; j++) {
                boardView.gridViews[i][j].canStep = false;
            }
        }
    }
    public ArrayList<BoardPoint> getCanStepPoints(BoardPoint src){
        ArrayList<BoardPoint> list = new ArrayList<>();
        for(int i = 0; i<board.getCanmovepoints(src).size(); i++){
            boardView.gridViews[board.getCanmovepoints(src).get(i).getRow()][board.getCanmovepoints(src).get(i).getCol()].canStep = true;
            list.add(board.getCanmovepoints(src).get(i));
        }
        return list;
    }
    public void reset() {
        canStepPoints = null;
        board.initGrid();
        board.initPieces();
        boardView.removeChessComponent();
        boardView.initiateChessComponent(board);
        currentPlayer = Player.BLUE;
        boardView.turnLabel.setBounds(930,120,100,100);
        selectedPoint = null;
        setAllCellsCanStepFalse();
      //  boardView.statusLabel.setText("Turn 1: BLUE");
        steps = new ArrayList<>();
        boardView.repaint();
        boardView.revalidate();
        winner = null;

        board.redDead = new ArrayList<>();
        board.blueDead = new ArrayList<>();
        //timer.time = 45;
    }

    public void regretOneStep() {
        if(steps.size()==1){
           reset();
        }
        else{
            board.regret();
            if(board.steps.get(steps.size()-1).ismove){
                boardView.setChessViewAtCell(board.steps.get(board.steps.size()-1).src, boardView.removeChessViewAtGrid(board.steps.get(board.steps.size()-1).dest));
            }
            else{
                boardView.setChessViewAtCell(board.steps.get(board.steps.size()-1).src,boardView.removeChessViewAtGrid(board.steps.get(board.steps.size()-1).dest));
                boardView.setChessViewAtCell(board.steps.get(board.steps.size()-1).dest,eaten);
            }
            changePlayer();
            boardView.repaint();
        }
    }
}


