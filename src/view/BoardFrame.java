package view;

import javax.swing.*;

public class BoardFrame extends MyFrame{
    BeginFrame beginFrame;
    public BoardFrame(){
        super(800,600);

        addBackButton();

        this.setBackground("resource/Instructions.gif");
    }
    private void addBackButton(){
        JButton backButton=new HomeButton("Back",300,500);
        backButton.addActionListener((e) -> {
            this.setVisible(false);
            beginFrame.setVisible(true);
        });
        add(backButton);
    }
}
