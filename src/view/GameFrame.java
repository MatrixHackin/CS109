package view;

import javax.swing.*;
import java.awt.*;

import static model.Constant.GAMEBG_COL_SIZE;
import static model.Constant.GAMEBG_ROW_SIZE;

public class GameFrame extends MyFrame {
    public BeginFrame beginFrame;
    private final int ONE_CHESS_SIZE;

    private BoardView boardView;
    AIFrame aiFrame;
    TurnLabel turnLabel=new TurnLabel();
    JLabel timeLabel;

    public boolean isDay;


    public GameFrame() {
        super(1000,700);

        this.ONE_CHESS_SIZE = 80;
        isDay = true;

        addChessboard();
        addTurnLable();

        this.setBackground("resource/map.png");
    }
    private void addTurnLable(){
        turnLabel.setBounds(930,120,100,100);
        turnLabel.setForeground(new Color(255,215,0));
        add(turnLabel);
    }

    public BoardView getBoardView() {
        return boardView;
    }

    public void setBoardView(BoardView boardView) {
        this.boardView = boardView;
    }

    private void addChessboard() {
        boardView = new BoardView(ONE_CHESS_SIZE, turnLabel,timeLabel);
        boardView.setLocation(140, 120);
        add(boardView);
    }
}
