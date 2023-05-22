package controller;

import model.*;
import listener.GameListener;
import view.BoardView;
import view.CellView;
import view.chessView.AnimalView;
import view.chessView.DeadChessView;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Controller implements GameListener {

    public Board board;
    public BoardView boardView;
    public ArrayList<BoardPoint> canStepPoints;//把可以走的点返回来给我高亮
    public Player currentPlayer;
    public Player winner;
    public BoardPoint selectedPoint;//选中的点高亮
    public boolean isPlayback;
    public boolean skip;
    public boolean AI = false;
    public AI AIplayer = new AI();
    public ArrayList<AnimalView> eaten=new ArrayList<>();
    public ArrayList<Board> boards;
    ObjectInputStream in;


    public Controller(BoardView boardView, Board board) {
        this.boardView = boardView;
        this.board = board;
        this.currentPlayer = Player.BLUE;
        this.winner = null;
        isPlayback = false;
        skip = false;
        this.boards = new ArrayList<>();
        boardView.setController(this);
        boardView.initiateChessComponent(board);
        boardView.repaint();
    }
    public Board load(){
        Board loadedBoard=new Board();
        try {
            ObjectInputStream in = boardView.in;
             loadedBoard= (Board) in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.board=loadedBoard;
        return loadedBoard;
    }

    public void save(String path){
        Board board=this.boards.get(boardView.controller.boards.size()-1);
        try {
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(board);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changePlayer() {
        currentPlayer = currentPlayer == Player.BLUE ? Player.RED : Player.BLUE;
        if (currentPlayer == Player.BLUE)
            boardView.turnLabel.setBounds(930, 120, 100, 100);
        else
            boardView.turnLabel.setBounds(35, 120, 100, 100);
    }

    public void checkWin(BoardPoint point) {
        if (board.blueDead.size() == 8) {
            winner = Player.RED;
            if(boardView.getUser()!=null){
                boardView.getUser().setScore(boardView.getUser().getScore()-10);
            }
        } else if (board.redDead.size() == 8) {
            winner = Player.BLUE;
            if(boardView.getUser()!=null){
                boardView.getUser().setScore(boardView.getUser().getScore()+10);
            }
        } else if (board.isOpponentDens(point, currentPlayer)) {
            winner = currentPlayer;
        }

        //判断棋子全吃完了，或者到老巢了//
    }

    public void winView() {
        JOptionPane.showMessageDialog(boardView, (winner == Player.BLUE ? "BLUE" : "RED") + " Win !");
    }

    @Override
    public void clickCell(BoardPoint point, CellView component) {
        if (selectedPoint != null) {
            if (board.canMove(selectedPoint, point)) {
                board.move(selectedPoint, point);
                setAllCellsCanStepFalse();
                canStepPoints = null;
                boardView.setChessViewAtCell(point, boardView.removeChessViewAtGrid(selectedPoint));
                selectedPoint = null;
                boards.add(board);
                checkWin(point);
                if (winner != null) {
                    winView();
                    reset();
                } else {
                    boardView.timer.stop();
                    boardView.count=20;
                    boardView.timeLabel.setText(Integer.toString(boardView.count));
                    boardView.timer.start();
                    if(boardView.controller.currentPlayer==Player.BLUE){
                        boardView.timeLabel.setLocation(30,25);
                    }
                    else {
                        boardView.timeLabel.setLocation(920,25);
                    }
                    boardView.controller.changePlayer();
                }
                if (AI) {
                    changePlayer();
                    AIplayer.EasyAI(board);
                    if (!AIplayer.LastActioniseat) {
                        boardView.setChessViewAtCell(AIplayer.dest, boardView.removeChessViewAtGrid(AIplayer.src));
                        boards.add(board);
                    } else {
                        boardView.removeChessViewAtGrid(AIplayer.dest);
                        boardView.setChessViewAtCell(AIplayer.dest, boardView.removeChessViewAtGrid(AIplayer.src));
                        boards.add(board);
                        addDeadView();
                    }
                    changePlayer();
                }
                boardView.repaint();
                component.revalidate();
            }
            checkWin(point);
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
        } else if (selectedPoint.getCol() == point.getCol() && selectedPoint.getRow() == point.getRow()) {
            selectedPoint = null;
            canStepPoints = null;
            setAllCellsCanStepFalse();
            component.setSelected(false);
            boardView.repaint();
            boardView.revalidate();
            component.repaint();
            component.revalidate();
            //放下棋子
        } else if (board.canEat(selectedPoint, point)) {
            board.eat(selectedPoint, point);
            eaten.add(boardView.removeChessViewAtGrid(point));
            boardView.setChessViewAtCell(point, boardView.removeChessViewAtGrid(selectedPoint));
            selectedPoint = null;
            setAllCellsCanStepFalse();
            boards.add(board);
            checkWin(point);
            if (winner != null) {
            } else {
                changePlayer();
                boardView.timer.stop();
                boardView.count=20;
                changePlayer();
                boardView.timeLabel.setText(Integer.toString(boardView.count));
                boardView.timer.start();
                if(boardView.controller.currentPlayer==Player.BLUE){
                    boardView.timeLabel.setLocation(30,25);
                }
                else {
                    boardView.timeLabel.setLocation(920,25);
                }
                boardView.controller.changePlayer();
            }
            if (AI) {
                changePlayer();
                AIplayer.EasyAI(board);
                if (!AIplayer.LastActioniseat) {
                    boardView.setChessViewAtCell(AIplayer.dest, boardView.removeChessViewAtGrid(AIplayer.src));
                    boards.add(board);
                } else {
                    boardView.removeChessViewAtGrid(AIplayer.dest);
                    boardView.setChessViewAtCell(AIplayer.dest, boardView.removeChessViewAtGrid(AIplayer.src));
                    boards.add(board);
                    addDeadView();
                }
                changePlayer();
            }
            addDeadView();
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

    public ArrayList<BoardPoint> getCanStepPoints(BoardPoint src) {
        ArrayList<BoardPoint> list = new ArrayList<>();
        for (int i = 0; i < board.getCanmovepoints(src).size(); i++) {
            boardView.gridViews[board.getCanmovepoints(src).get(i).getRow()][board.getCanmovepoints(src).get(i).getCol()].canStep = true;
            list.add(board.getCanmovepoints(src).get(i));
        }
        return list;
    }

    public void reset() {
        canStepPoints = null;
        board.initGrid();
        board.initPieces();
        boardView.removeAllChess();
        boardView.initiateChessComponent(board);
        currentPlayer = Player.BLUE;
        boardView.turnLabel.setBounds(930, 120, 100, 100);
        selectedPoint = null;
        setAllCellsCanStepFalse();
        boards.clear();
        boardView.timer.stop();
        boardView.count=20;
        boardView.timeLabel.setText(Integer.toString(boardView.count));
        boardView.timer.start();
        boardView.timeLabel.setLocation(920,25);
        boardView.repaint();
        boardView.revalidate();
        winner = null;
        boardView.redDeadPanel.removeAll();
        boardView.blueDeadPanel.removeAll();
        board.redDead = new ArrayList<>();
        board.blueDead = new ArrayList<>();
        boardView.redDeadPanel.repaint();
        boardView.blueDeadPanel.repaint();
    }

    public void regretOneStep() {
        //AI模式关闭
        if (!AI) {
            if (boards.size() == 1) {
                reset();
            } else {
                board.regret();
                if (board.steps.get(boards.size() - 1).ismove) {
                    boardView.setChessViewAtCell(board.steps.get(board.steps.size() - 1).src, boardView.removeChessViewAtGrid(board.steps.get(board.steps.size() - 1).dest));
                    boards.remove(board.steps.size()-1);
                    board.steps.remove(board.steps.size()-1);
                }
                else {
                    boardView.setChessViewAtCell(board.steps.get(board.steps.size() - 1).src, boardView.removeChessViewAtGrid(board.steps.get(board.steps.size() - 1).dest));
                    boardView.setChessViewAtCell(board.steps.get(board.steps.size() - 1).dest, eaten.get(eaten.size()-1));
                    eaten.remove(eaten.size()-1);
                    boards.remove(board.steps.size()-1);
                    if(currentPlayer==Player.BLUE){
                        boardView.redDeadPanel.remove(board.blueDead.size());
                        boardView.redDeadPanel.repaint();
                    }
                    else{
                        boardView.blueDeadPanel.remove(board.redDead.size());
                        boardView.blueDeadPanel.repaint();
                    }
                    board.steps.remove(board.steps.size()-1);
                }
                changePlayer();
            }
        }
        //AI模式开启
        else {
            board.regret();
            changePlayer();
            if (board.steps.get(boards.size() - 1).ismove) {
                boardView.setChessViewAtCell(board.steps.get(board.steps.size() - 1).src, boardView.removeChessViewAtGrid(board.steps.get(board.steps.size() - 1).dest));
            }
            else {
                boardView.setChessViewAtCell(board.steps.get(board.steps.size() - 1).src, boardView.removeChessViewAtGrid(board.steps.get(board.steps.size() - 1).dest));
                boardView.setChessViewAtCell(board.steps.get(board.steps.size() - 1).dest, eaten.get(eaten.size()-1));
                eaten.remove(eaten.size()-1);
                boardView.redDeadPanel.remove(board.blueDead.size());
                boardView.redDeadPanel.repaint();
            }
            board.regret(AI);
            changePlayer();
            if (board.steps.get(boards.size() - 2).ismove) {
                boardView.setChessViewAtCell(board.steps.get(board.steps.size() - 2).src, boardView.removeChessViewAtGrid(board.steps.get(board.steps.size() - 2).dest));
            } else {
                boardView.setChessViewAtCell(board.steps.get(board.steps.size() - 2).src, boardView.removeChessViewAtGrid(board.steps.get(board.steps.size() - 2).dest));
                boardView.setChessViewAtCell(board.steps.get(board.steps.size() - 2).dest, eaten.get(eaten.size()-1));
                eaten.remove(eaten.size()-1);
                boardView.blueDeadPanel.remove(board.redDead.size()-1);
                boardView.blueDeadPanel.repaint();
            }
        }
    }
    public void addDeadView(){
        if(!AI){
            if (currentPlayer == Player.RED) {
                switch (board.redDead.get(board.redDead.size() - 1).getRank()) {
                    case 1 -> {
                        DeadChessView r1 = new DeadChessView("resource/animals/right/grey/1.png");
                        boardView.blueDeadPanel.add(r1.getLabel());
                    }
                    case 2 -> {
                        DeadChessView r2 = new DeadChessView("resource/animals/right/grey/2.png");
                        boardView.blueDeadPanel.add(r2.getLabel());
                    }
                    case 3 -> {
                        DeadChessView r3 = new DeadChessView("resource/animals/right/grey/3.png");
                        boardView.blueDeadPanel.add(r3.getLabel());
                    }
                    case 4 -> {
                        DeadChessView r4 = new DeadChessView("resource/animals/right/grey/4.png");
                        boardView.blueDeadPanel.add(r4.getLabel());
                    }
                    case 5 -> {
                        DeadChessView r5 = new DeadChessView("resource/animals/right/grey/5.png");
                        boardView.blueDeadPanel.add(r5.getLabel());
                    }
                    case 6 -> {
                        DeadChessView r6 = new DeadChessView("resource/animals/right/grey/6.png");
                        boardView.blueDeadPanel.add(r6.getLabel());
                    }
                    case 7 -> {
                        DeadChessView r7 = new DeadChessView("resource/animals/right/grey/7.png");
                        boardView.blueDeadPanel.add(r7.getLabel());
                    }
                    case 8 -> {
                        DeadChessView r8 = new DeadChessView("resource/animals/right/grey/8.png");
                        boardView.blueDeadPanel.add(r8.getLabel());
                    }
                }
            }
            if (currentPlayer == Player.BLUE) {
                switch (board.blueDead.get(board.blueDead.size() - 1).getRank()) {
                    case 1 -> {
                        DeadChessView r1 = new DeadChessView("resource/animals/left/grey/1.png");
                        boardView.redDeadPanel.add(r1.getLabel());
                    }
                    case 2 -> {
                        DeadChessView r2 = new DeadChessView("resource/animals/left/grey/2.png");
                        boardView.redDeadPanel.add(r2.getLabel());
                    }
                    case 3 -> {
                        DeadChessView r3 = new DeadChessView("resource/animals/left/grey/3.png");
                        boardView.redDeadPanel.add(r3.getLabel());
                    }
                    case 4 -> {
                        DeadChessView r4 = new DeadChessView("resource/animals/left/grey/4.png");
                        boardView.redDeadPanel.add(r4.getLabel());
                    }
                    case 5 -> {
                        DeadChessView r5 = new DeadChessView("resource/animals/left/grey/5.png");
                        boardView.redDeadPanel.add(r5.getLabel());
                    }
                    case 6 -> {
                        DeadChessView r6 = new DeadChessView("resource/animals/left/grey/6.png");
                        boardView.redDeadPanel.add(r6.getLabel());
                    }
                    case 7 -> {
                        DeadChessView r7 = new DeadChessView("resource/animals/left/grey/7.png");
                        boardView.redDeadPanel.add(r7.getLabel());
                    }
                    case 8 -> {
                        DeadChessView r8 = new DeadChessView("resource/animals/left/grey/8.png");
                        boardView.redDeadPanel.add(r8.getLabel());
                    }
                }
            }
        }
        else{
            if (currentPlayer == Player.RED) {
                switch (board.blueDead.get(board.blueDead.size() - 1).getRank()) {
                    case 1 -> {
                        DeadChessView r1 = new DeadChessView("resource/animals/left/grey/1.png");
                        boardView.redDeadPanel.add(r1.getLabel());
                    }
                    case 2 -> {
                        DeadChessView r2 = new DeadChessView("resource/animals/left/grey/2.png");
                        boardView.redDeadPanel.add(r2.getLabel());
                    }
                    case 3 -> {
                        DeadChessView r3 = new DeadChessView("resource/animals/left/grey/3.png");
                        boardView.redDeadPanel.add(r3.getLabel());
                    }
                    case 4 -> {
                        DeadChessView r4 = new DeadChessView("resource/animals/left/grey/4.png");
                        boardView.redDeadPanel.add(r4.getLabel());
                    }
                    case 5 -> {
                        DeadChessView r5 = new DeadChessView("resource/animals/left/grey/5.png");
                        boardView.redDeadPanel.add(r5.getLabel());
                    }
                    case 6 -> {
                        DeadChessView r6 = new DeadChessView("resource/animals/left/grey/6.png");
                        boardView.redDeadPanel.add(r6.getLabel());
                    }
                    case 7 -> {
                        DeadChessView r7 = new DeadChessView("resource/animals/left/grey/7.png");
                        boardView.redDeadPanel.add(r7.getLabel());
                    }
                    case 8 -> {
                        DeadChessView r8 = new DeadChessView("resource/animals/left/grey/8.png");
                        boardView.redDeadPanel.add(r8.getLabel());
                    }
                }
            }
            if (currentPlayer == Player.BLUE) {
                switch (board.redDead.get(board.redDead.size() - 1).getRank()) {
                    case 1 -> {
                        DeadChessView r1 = new DeadChessView("resource/animals/right/grey/1.png");
                        boardView.blueDeadPanel.add(r1.getLabel());
                    }
                    case 2 -> {
                        DeadChessView r2 = new DeadChessView("resource/animals/right/grey/2.png");
                        boardView.blueDeadPanel.add(r2.getLabel());
                    }
                    case 3 -> {
                        DeadChessView r3 = new DeadChessView("resource/animals/right/grey/3.png");
                        boardView.blueDeadPanel.add(r3.getLabel());
                    }
                    case 4 -> {
                        DeadChessView r4 = new DeadChessView("resource/animals/right/grey/4.png");
                        boardView.blueDeadPanel.add(r4.getLabel());
                    }
                    case 5 -> {
                        DeadChessView r5 = new DeadChessView("resource/animals/right/grey/5.png");
                        boardView.blueDeadPanel.add(r5.getLabel());
                    }
                    case 6 -> {
                        DeadChessView r6 = new DeadChessView("resource/animals/right/grey/6.png");
                        boardView.blueDeadPanel.add(r6.getLabel());
                    }
                    case 7 -> {
                        DeadChessView r7 = new DeadChessView("resource/animals/right/grey/7.png");
                        boardView.blueDeadPanel.add(r7.getLabel());
                    }
                    case 8 -> {
                        DeadChessView r8 = new DeadChessView("resource/animals/right/grey/8.png");
                        boardView.blueDeadPanel.add(r8.getLabel());
                    }
                }
            }
        }
    }
}


