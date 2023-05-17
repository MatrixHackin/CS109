package view;

import model.Board;

import javax.swing.*;
import java.awt.*;

import static model.Constant.GAMEBG_COL_SIZE;
import static model.Constant.GAMEBG_ROW_SIZE;

public class GameFrame extends MyFrame {
    public BeginFrame beginFrame;
    private final int ONE_CHESS_SIZE;

    private BoardView boardView;
    JButton homeButton;
    JButton changeThemeButton;
    JButton regretButton;
    JButton musicButton;
    JButton resetButton;
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
        addHomeButton();
        addRegretButton();
        addMusicButton();
        addResetButton();
        addChangeThemeButton();

        this.setBackground("resource/map.png");
    }
    private void addChangeThemeButton(){
        this.changeThemeButton=new GameButton("resource/icon/changeTheme.png");
        changeThemeButton.setLocation(930,350);
        changeThemeButton.addActionListener((e)->{
            if(isDay){
                this.setBackground("resource/night.png");
                this.isDay=false;
                this.repaint();
                this.validate();
            }
            else {
                this.setBackground("resource/map.png");
                this.validate();
            }
        });
        add(changeThemeButton);
    }
    private void addResetButton(){
        this.resetButton=new GameButton("resource/icon/reset.png");
        resetButton.setLocation(930,420);
        resetButton.addActionListener((e)->{

        });
        add(resetButton);
    }
    private void addMusicButton(){
        this.musicButton=new GameButton("resource/icon/sound-full-icon.png");
        musicButton.setLocation(930,490);
        musicButton.addActionListener((e)->{

        });
        add(musicButton);
    }
    private void addRegretButton(){
        this.regretButton=new GameButton("resource/icon/round-line-left-arrow-icon.png");
        regretButton.setLocation(930,560);
        add(regretButton);
    }
    private void addHomeButton(){
        this.homeButton=new GameButton("resource/icon/home-button-icon.png");
        homeButton.setLocation(930,630);
        homeButton.addActionListener((e)->{
            this.setVisible(false);
            beginFrame.setVisible(true);
        });
        add(homeButton);
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
