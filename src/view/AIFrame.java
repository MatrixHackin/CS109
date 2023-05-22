package view;

import controller.Controller;
import model.Board;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class AIFrame extends MyFrame{
    LoginFrame loginFrame;
    JButton beginButton;
    JButton backButton;
    GameFrame gameFrame;
    User user;

    public AIFrame(){
        super(498,280);

        addBeginButton();
        addBackButton();
        addDifficultyChooser();

    }
    public void setUser(User user){
        this.user=user;
    }
    public void addWelcomeLabel(){

        String userName= user.getName();
        JLabel welcomeLabel=new JLabel("Welcome "+userName+" !");
        welcomeLabel.setLocation(80,20);
        welcomeLabel.setSize(350,50);
        welcomeLabel.setFont(new Font("Arial",Font.BOLD,50));
        welcomeLabel.setForeground(Color.WHITE);
        add(welcomeLabel);
        this.setBackground("resource/registerframe.gif");
    }
    private void addDifficultyChooser(){
        JComboBox<String> comboBox = new JComboBox<>(new String[]{"easy", "difficult"});
        comboBox.setLocation(100,100);
        comboBox.setSize(300,30);
        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getItem().equals("easy")) {
                    // easy AI
                } else  {
                    // difficult AI
                }
            }
        });
        this.add(comboBox);
    }
    private void addBeginButton(){
        this.beginButton=new HomeButton("Begin",50,200);
        beginButton.addActionListener((e)->{
            GameFrame gameFrame = new GameFrame();
            gameFrame.setUser(user);
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
