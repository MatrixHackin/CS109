package view;

import model.BGM;
import model.Board;
import model.Player;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends MyFrame {
    public BeginFrame beginFrame;
    private final int ONE_CHESS_SIZE;

    private BoardView boardView;
    JButton homeButton;
    JButton changeThemeButton;
    JButton regretButton;
    JButton musicButton;
    JButton resetButton;
    JButton saveButton;
    AIFrame aiFrame=new AIFrame();
    TurnLabel turnLabel=new TurnLabel();
    JLabel timeLabel;
    JLabel dayBG;
    JLabel nightBG;
    JPanel redDeadPanel;
    JPanel blueDeadPanel;
    public boolean isDay;
    public boolean musicOn;
    public GameFrame() {
        super(1000,700);

        this.ONE_CHESS_SIZE = 80;
        isDay = true;
        musicOn=true;

        addChessboard();
        addTurnLable();
        addHomeButton();
        addRegretButton();
        addMusicButton();
        addResetButton();
        addChangeThemeButton();
        addSaveButton();
        addRedDeadPanel();
        addBlueDeadPanel();

        boardView.redDeadPanel=this.redDeadPanel;
        boardView.blueDeadPanel=this.blueDeadPanel;

        Image image = new ImageIcon("resource/map.png").getImage();
        image = image.getScaledInstance(1000, 700, Image.SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(image);
        this.dayBG = new JLabel(icon);
        this.dayBG.setSize(1000,700);
        this.dayBG.setLocation(0,0);

        Image image1 = new ImageIcon("resource/night.png").getImage();
        image1 = image1.getScaledInstance(1000, 700, Image.SCALE_DEFAULT);
        ImageIcon icon1 = new ImageIcon(image1);
        this.nightBG = new JLabel(icon1);
        this.nightBG.setSize(1000,700);
        this.nightBG.setLocation(0,0);

        add(dayBG);
    }
    private void addBlueDeadPanel(){
        this.blueDeadPanel=new JPanel();
        blueDeadPanel.setLayout(new GridLayout(2,4));
        blueDeadPanel.setLocation(688,30);
        blueDeadPanel.setSize(200,80);
        blueDeadPanel.setOpaque(false);
        add(blueDeadPanel);
    }
    private void addRedDeadPanel(){
        this.redDeadPanel=new JPanel();
        redDeadPanel.setLayout(new GridLayout(2,4));
        redDeadPanel.setLocation(113,30);
        redDeadPanel.setSize(200,80);
        redDeadPanel.setOpaque(false);
        add(redDeadPanel);
    }
    private void addSaveButton(){
        this.saveButton=new GameButton("resource/icon/save-44.png");
        saveButton.setLocation(930,280);
        saveButton.addActionListener((e)->{

        });
        add(saveButton);
    }
    private void addChangeThemeButton(){
        this.changeThemeButton=new GameButton("resource/icon/changeTheme.png");
        changeThemeButton.setLocation(930,350);
        changeThemeButton.addActionListener((e)->{
            if(isDay){
                this.remove(dayBG);
                this.isDay=false;
                add(nightBG);
            }
            else {
                this.remove(nightBG);
                this.isDay=true;
                add(dayBG);
            }
            repaint();
            revalidate();
        });
        add(changeThemeButton);
    }
    private void addResetButton(){
        this.resetButton=new GameButton("resource/icon/reset.png");
        resetButton.setLocation(930,420);
        resetButton.addActionListener((e)->{
            boardView.controller.reset();
        });
        add(resetButton);
    }
    private void addMusicButton(){
        this.musicButton=new GameButton("resource/icon/sound-full-icon.png");
        musicButton.setLocation(930,490);
        musicButton.addActionListener((e)->{
            Image image = new ImageIcon("resource/icon/sound-off-icon.png").getImage();
            image = image.getScaledInstance( 40,40, Image.SCALE_DEFAULT);
            ImageIcon icon = new ImageIcon(image);
            Image image1 = new ImageIcon("resource/icon/sound-full-icon.png").getImage();
            image1 = image1.getScaledInstance( 40,40, Image.SCALE_DEFAULT);
            ImageIcon icon1 = new ImageIcon(image1);
            if(musicOn){
                musicButton.setIcon(icon);
                musicOn=false;
            }
            else {
                musicButton.setIcon(icon1);
                musicOn=true;
            }


        });
        add(musicButton);
    }
    private void addRegretButton(){
        this.regretButton=new GameButton("resource/icon/round-line-left-arrow-icon.png");
        regretButton.setLocation(930,560);
        regretButton.addActionListener((e)->{
            boardView.controller.regretOneStep();
        });
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
