package view;

import controller.Controller;
import model.Board;
import model.User;

import javax.swing.*;

public class AIFrame extends MyFrame{
    //JLabel welcomeLabel;
    LoginFrame loginFrame;
    JButton beginButton;
    JButton backButton;
    GameFrame gameFrame;
    User user;

    public AIFrame(){
        super(498,280);
        this.user= LoginFrame.user;

        addBeginButton();
        addBackButton();
        addDifficultyChooser();
        addWelcomeLabel();

        this.setBackground("resource/registerframe.gif");
    }
    private void addWelcomeLabel(){
        //String userName= LoginFrame.user.getName();
       // this.welcomeLabel=new JLabel("Welcome "+userName+" !");
    }
    private void addDifficultyChooser(){
        JComboBox<String> comboBox = new JComboBox<>(new String[]{"easy", "difficult"});
        comboBox.setLocation(100,120);
        comboBox.setSize(300,30);
        this.add(comboBox);
    }
    private void addBeginButton(){
        this.beginButton=new HomeButton("Begin",50,200);
        beginButton.addActionListener((e)->{
            GameFrame gameFrame = new GameFrame(user);
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
