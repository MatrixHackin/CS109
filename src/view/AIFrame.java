package view;

import controller.Controller;
import model.Board;

import javax.swing.*;

public class AIFrame extends MyFrame{
    JLabel welcomeLabel;
    LoginFrame loginFrame;
    JButton beginButton;
    JButton backButton;
    GameFrame gameFrame;
    JComboBox comboBox;

    public AIFrame(){
        super(498,280);

        addBeginButton();
        addBackButton();
        addDifficultyChooser();

        this.setBackground("resource/registerframe.gif");
    }
    private void addDifficultyChooser(){
        this.comboBox=new JComboBox();
        comboBox.addItem("--please choose one--");
        comboBox.addItem("easy");
        comboBox.addItem("difficult");
        add(comboBox);
    }
    private void addWelcomeLabel(){
        this.welcomeLabel=new JLabel();
    }
    private void addBeginButton(){
        this.beginButton=new HomeButton("Begin",50,200);
        beginButton.addActionListener((e)->{
            GameFrame gameFrame = new GameFrame();
            Controller controller = new Controller(gameFrame.getBoardView(), new Board());
            this.gameFrame = gameFrame;
            gameFrame.aiFrame = this;
            this.setVisible(false);
            gameFrame.getBoardView().controller.AI=true;
            gameFrame.setVisible(true);
        });
        add(beginButton);
    }
    private void addBackButton() {
        this.backButton = new HomeButton("Back", 260, 200);
        backButton.addActionListener((e) -> {
            this.setVisible(false);
            loginFrame.setVisible(true);
        });
        add(backButton);
    }

}
