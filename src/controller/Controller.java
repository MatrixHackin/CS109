package controller;

import model.*;
import listener.GameListener;
import view.BoardView;
import view.CellView;
import view.GameFrame;
import view.chessView.AnimalView;
import view.chessView.DeadChessView;

import javax.swing.*;
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
    public AnimalView eaten;
    public ArrayList<Board> steps;
    public GameFrame gameFrame;

    //   public JLabel timeLabel;
    public static Timer timer;

    public Controller(BoardView boardView, Board board) {
        this.boardView = boardView;
        this.board = board;
        this.currentPlayer = Player.BLUE;
        this.winner = null;
        //   timeLabel = boardView.timeLabel;
        isPlayback = false;
        skip = false;
        this.steps = new ArrayList<>();

        boardView.setController(this);
        boardView.initiateChessComponent(board);
        boardView.repaint();
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
        } else if (board.redDead.size() == 8) {
            winner = Player.BLUE;
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
                steps.add(board);
                checkWin(point);
                if (winner != null) {
                } else {
                    changePlayer();
                }
                if (AI) {
                    changePlayer();
                    AIplayer.EasyAI(board);
                    if (!AIplayer.LastAction) {
                        boardView.setChessViewAtCell(AIplayer.dest, boardView.removeChessViewAtGrid(AIplayer.src));
                        steps.add(board);
                    } else {
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
            eaten = boardView.removeChessViewAtGrid(point);
            boardView.setChessViewAtCell(point, boardView.removeChessViewAtGrid(selectedPoint));
            selectedPoint = null;
            setAllCellsCanStepFalse();
            steps.add(board);
            checkWin(point);
            if (winner != null) {
            } else {
                changePlayer();
            }
            if (AI) {
                changePlayer();
                AIplayer.EasyAI(board);
                if (!AIplayer.LastAction) {
                    boardView.setChessViewAtCell(AIplayer.dest, boardView.removeChessViewAtGrid(AIplayer.src));
                    steps.add(board);
                } else {
                    boardView.removeChessViewAtGrid(AIplayer.dest);
                    boardView.setChessViewAtCell(AIplayer.dest, boardView.removeChessViewAtGrid(AIplayer.src));
                    steps.add(board);
                }
            }


            if (currentPlayer == Player.RED) {
                switch (board.redDead.get(board.redDead.size() - 1).getRank()) {
                    case 1 -> {
                        DeadChessView r1 = new DeadChessView("resource/animals/left/grey/1.png");
                        boardView.redDeadPanel.add(r1.getLabel());
                    }
                    case 2 -> {
                        DeadChessView r2 = new DeadChessView("resource/animals/left/grey/2.png");
                        boardView.redDeadPanel.add(r2.getLabel());
                    }
                    case 3 -> {
                        DeadChessView r3 = new DeadChessView("resource/animals/left/grey/4.png");
                        boardView.redDeadPanel.add(r3.getLabel());
                    }
                    case 4 -> {
                        DeadChessView r4 = new DeadChessView("resource/animals/left/grey/3.png");
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
                switch (board.blueDead.get(board.blueDead.size() - 1).getRank()) {
                    case 1 -> {
                        DeadChessView r1 = new DeadChessView("resource/animals/right/grey/1.png");
                        boardView.blueDeadPanel.add(r1.getLabel());
                    }
                    case 2 -> {
                        DeadChessView r2 = new DeadChessView("resource/animals/right/grey/2.png");
                        boardView.blueDeadPanel.add(r2.getLabel());
                    }
                    case 3 -> {
                        DeadChessView r3 = new DeadChessView("resource/animals/right/grey/4.png");
                        boardView.blueDeadPanel.add(r3.getLabel());
                    }
                    case 4 -> {
                        DeadChessView r4 = new DeadChessView("resource/animals/right/grey/3.png");
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
        steps.clear();
        boardView.repaint();
        boardView.revalidate();
        winner = null;
        boardView.redDeadPanel.removeAll();
        boardView.blueDeadPanel.removeAll();
        board.redDead = new ArrayList<>();
        board.blueDead = new ArrayList<>();
        boardView.redDeadPanel.repaint();
        boardView.blueDeadPanel.repaint();
        //timer.time = 45;
    }

    public void regretOneStep() {
        if (!AI) {
            if (steps.size() == 1) {
                reset();
            } else {
                board.regret();
                changePlayer();
                if (board.steps.get(steps.size() - 1).ismove) {
                    boardView.setChessViewAtCell(board.steps.get(board.steps.size() - 1).src, boardView.removeChessViewAtGrid(board.steps.get(board.steps.size() - 1).dest));
                } else {
                    boardView.setChessViewAtCell(board.steps.get(board.steps.size() - 1).src, boardView.removeChessViewAtGrid(board.steps.get(board.steps.size() - 1).dest));
                    boardView.setChessViewAtCell(board.steps.get(board.steps.size() - 1).dest, eaten);
                }
            }
        } else {
            board.regret();
            changePlayer();
            if (board.steps.get(steps.size() - 1).ismove) {
                boardView.setChessViewAtCell(board.steps.get(board.steps.size() - 1).src, boardView.removeChessViewAtGrid(board.steps.get(board.steps.size() - 1).dest));
            } else {
                boardView.setChessViewAtCell(board.steps.get(board.steps.size() - 1).src, boardView.removeChessViewAtGrid(board.steps.get(board.steps.size() - 1).dest));
                boardView.setChessViewAtCell(board.steps.get(board.steps.size() - 1).dest, eaten);
            }
            board.regret(AI);
            changePlayer();
            if (board.steps.get(steps.size() - 2).ismove) {
                boardView.setChessViewAtCell(board.steps.get(board.steps.size() - 2).src, boardView.removeChessViewAtGrid(board.steps.get(board.steps.size() - 2).dest));
            } else {
                boardView.setChessViewAtCell(board.steps.get(board.steps.size() - 2).src, boardView.removeChessViewAtGrid(board.steps.get(board.steps.size() - 2).dest));
                boardView.setChessViewAtCell(board.steps.get(board.steps.size() - 2).dest, eaten);
            }
        }
    }
}


