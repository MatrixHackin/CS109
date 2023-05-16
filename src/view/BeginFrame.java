package view;

import controller.Controller;
import model.Board;

import javax.swing.*;
import java.awt.*;

public class BeginFrame extends MyFrame{
    GameFrame gameFrame;
    LoginFrame loginFrame;
    SettingFrame settingFrame;
    InstructionFrame instructionFrame;
    BoardFrame boardFrame;
    JLabel titleLabel1 = new JLabel("JUNGLE  CHESS");
    JButton button ;
    JButton settingButton;
    JButton instructionButton;
    JButton loginButton;
    JButton boardButton;

    public BeginFrame() {
        super(1000,500);

        GameFrame gameFrame = new GameFrame();
        Controller controller = new Controller(gameFrame.getBoardView(), new Board());
        this.gameFrame = gameFrame;
        gameFrame.beginFrame = this;

        BoardFrame boardFrame=new BoardFrame();
        this.boardFrame=boardFrame;
        boardFrame.beginFrame=this;

        SettingFrame settingFrame=new SettingFrame();
        this.settingFrame=settingFrame;
        settingFrame.beginFrame=this;

        InstructionFrame instructionFrame=new InstructionFrame();
        this.instructionFrame=instructionFrame;
        instructionFrame.beginFrame=this;

        LoginFrame loginFrame=new LoginFrame();
        this.loginFrame = loginFrame;
        loginFrame.beginFrame = this;

        addTitleLabel1();
        addBeginButton();
        addSettingButton();
        addLoginButton();
        addInstructionButton();
        addBoardButton();
        this.setBackground("resource/background.gif");
    }
    private void addBoardButton(){
        this.boardButton=new HomeButton("Board",400,250);
        boardButton.addActionListener((e)->{
            this.setVisible(false);
            boardFrame.setVisible(true);
        });
        add(boardButton);
    }

    private void addTitleLabel1() {
        titleLabel1.setLocation(100, HEIGHT / 10);
        titleLabel1.setSize(1000, 100);
        titleLabel1.setFont(new Font("Rockwell", Font.BOLD, 100));
        titleLabel1.setForeground(new Color(139, 69, 19));
        add(titleLabel1);
    }
    private void addLoginButton(){
        this.loginButton=new HomeButton("Login",100,250);
        loginButton.addActionListener((e) ->{
                    this.setVisible(false);
                    loginFrame.setVisible(true);
        });
        add(loginButton);
    }

    private void addBeginButton() {
        this.button= new HomeButton("Begin",100,150);
           /* Timer.time = 45;
            if (Controller.timer == null){
                Controller.timer = new Timer(gameFrame.getBoardView().controller);
                Controller.timer.start();
            }*/

            // gameFrame.statusLabel.setLocation(770, 81);
            // gameFrame.repaint();
            //gameFrame.timeLabel.setVisible(true);
            //   gameFrame.getBoardView().controller.reset();
            //  gameFrame.getBoardView().controller.AIPlaying = false;
            //   gameFrame.getBoardView().controller.AIDiff = Difficulty.NONE;
        button.addActionListener((e) -> {
            this.setVisible(false);
            gameFrame.setVisible(true);
        });
        add(button);

    }
    private void addSettingButton() {
        this.settingButton = new HomeButton("Settings", 400, 150);
        settingButton.addActionListener((e) -> {
            this.setVisible(false);
            settingFrame.setVisible(true);
        });
        add(settingButton);
    }
    public void addInstructionButton(){
        this.instructionButton=new HomeButton("Instructions",700,150);
        instructionButton.addActionListener((e) -> {
            this.setVisible(false);
            instructionFrame.setVisible(true);
        });
        add(instructionButton);
    }
}

