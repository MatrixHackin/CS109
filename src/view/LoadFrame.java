package view;

import controller.Controller;
import model.Board;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoadFrame extends MyFrame{
    JButton backButton;
    JButton saveButton1;
    JFrame beginFrame;
    JLabel title;
    public LoadFrame(){
        super(498,280);

        addBackButton();
        addTitle();
        addSaveButton1();

        this.setBackground("resource/registerframe.gif");
    }
    private void addSaveButton1(){
        this.saveButton1=new JButton();
        saveButton1.setSize(220,50);
        saveButton1.setLocation(20,55);
        JLabel label1=new JLabel();
        label1.setLocation(20,106);
        label1.setSize(220,20);
        add(label1);
    }

    private void addTitle(){
        this.title=new JLabel("Load");
        title.setForeground(new Color(46,139,42));
        title.setFont(new Font("Rockwell",Font.BOLD,50));
        title.setLocation(190,0);
        title.setSize(200,50);
        add(title);
    }

    private void addBackButton() {
        this.backButton = new HomeButton("Back", 160, 210);
        backButton.addActionListener((e) -> {
            this.setVisible(false);
            beginFrame.setVisible(true);
        });
        add(backButton);
    }
}
