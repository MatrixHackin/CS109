package view;

import controller.Controller;
import model.Board;
import model.User;

import javax.swing.*;
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

        this.setBackground("resource/registerframe.gif");
    }
    public void setUser(User user){
        this.user=user;
    }
    public void addWelcomeLabel(){
        String userName= user.getName();
        JLabel welcomeLabel=new JLabel("Welcome "+userName+" !");
        welcomeLabel.setLocation(50,50);
        welcomeLabel.setSize(150,50);
        this.add(welcomeLabel);
        this.repaint();
        this.revalidate();
    }
    private void addDifficultyChooser(){
        JComboBox<String> comboBox = new JComboBox<>(new String[]{"easy", "difficult"});
        comboBox.setLocation(100,120);
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
