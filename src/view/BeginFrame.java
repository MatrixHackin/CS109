package view;

import controller.Controller;
import model.Board;
import javax.swing.*;
import java.awt.*;

public class BeginFrame extends JFrame {
    GameFrame gameFrame;
    LoginFrame loginFrame;
    SettingFrame settingFrame;
    InstructionFrame instructionFrame;
    JLabel titleLabel1 = new JLabel("JUNGLE  CHESS");
    JButton button;
    JButton settingButton;
    JButton instructionButton;

    public BeginFrame() {
        setTitle("Jungle");
        this.setSize(1000, 500);

        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);

        GameFrame gameFrame = new GameFrame();
        Controller controller = new Controller(gameFrame.getBoardView(), new Board());
        this.gameFrame = gameFrame;
        gameFrame.beginFrame = this;

        SettingFrame settingFrame = new SettingFrame();
        this.settingFrame = settingFrame;
        settingFrame.beginFrame = this;

        InstructionFrame instructionFrame = new InstructionFrame();
        this.instructionFrame = instructionFrame;
        instructionFrame.beginFrame = this;

        //   this.loginFrame = loginFrame;
        //  loginFrame.beginFrame = this;
        //  this.settingFrame = settingFrame;
        // settingFrame.beginFrame = this;

        addTitleLabel1();
        addBeginButton();
        addSettingButton();
        addInstructionButton();

        Image image = new ImageIcon("resource/background.gif").getImage();
        image = image.getScaledInstance(1000, 500, Image.SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(image);
        JLabel bg = new JLabel(icon);
        bg.setSize(1000, 500);
        bg.setLocation(0, 0);
        add(bg);

    }

    private void addTitleLabel1() {
        titleLabel1.setLocation(100, HEIGHT / 10);
        titleLabel1.setSize(1000, 100);
        titleLabel1.setFont(new Font("Rockwell", Font.BOLD, 100));
        titleLabel1.setForeground(new Color(139, 69, 19));
        add(titleLabel1);
    }

    private void addBeginButton() {
        this.button = new HomeButton("Begin", 100, 150, gameFrame);
        /*Timer.time = 45;
        if (Controller.timer == null) {
            Controller.timer = new Timer(gameFrame.getBoardView().controller);
            Controller.timer.start();
        }*/
        gameFrame.repaint();
        //gameFrame.timeLabel.setVisible(true);
        gameFrame.getBoardView().controller.reset();
        button.addActionListener((e) -> {
            this.setVisible(false);
            gameFrame.setVisible(true);
        });
        add(button);

    }

    private void addSettingButton() {
        this.settingButton = new HomeButton("Settings", 400, 150, settingFrame);
        settingButton.addActionListener((e) -> {
            this.setVisible(false);
            settingFrame.setVisible(true);
        });
        add(settingButton);
    }

    public void addInstructionButton() {
        this.instructionButton = new HomeButton("Instructions", 700, 150, instructionFrame);
        instructionButton.addActionListener((e) -> {
            this.setVisible(false);
            instructionFrame.setVisible(true);
        });
        add(instructionButton);
    }
}

