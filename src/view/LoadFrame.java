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
    JPanel savePanel;
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
    /*
    private void addSavePanel(){
        this.savePanel=new JPanel();
        savePanel.setSize(460,145);
        savePanel.setLocation(20,55);
        savePanel.setLayout(null);
        JButton save1=new JButton();
        JLabel timeLabel1=new JLabel();
        timeLabel1.setSize(50,20);
        timeLabel1.setLocation(0,60);
        timeLabel1.setBackground(new Color(0,0,0));
        save1.setLocation(0,0);
        save1.setSize(220,50);
        save1.addActionListener((e)->{
            if(timeLabel1.getText()==null){
                Date now=new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedDate = formatter.format(now);
                timeLabel1.setText("save 1\n"+formattedDate);
                timeLabel1.repaint();
                //传入当前board
            }
            else {
                //载入存入的board
            }
        });
        savePanel.add(save1);
        savePanel.add(timeLabel1);
        JButton save2=new JButton();
        save2.addActionListener((e)->{
            if(save2.getText()==null){
                Date now=new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedDate = formatter.format(now);
                save2.setText("save 2\n"+formattedDate);
                //传入当前board
            }
            else {
                //载入存入的board
            }
        });
        savePanel.add(save2);
        JButton save3=new JButton();
        save1.addActionListener((e)->{
            if(save3.getText()==null){
                Date now=new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedDate = formatter.format(now);
                save3.setText("save 1\n"+formattedDate);
                //传入当前board
            }
            else {
                //载入存入的board
            }
        });
        savePanel.add(save3);
        JButton save4=new JButton();
        JLabel timeLabel=new JLabel();
        save1.addActionListener((e)->{
            if(save4.getText()==null){
                Date now=new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedDate = formatter.format(now);
                save4.setText("save4");
                save4.repaint();
                save4.revalidate();
                //传入当前board
            }
            else {
                //载入存入的board
            }
        });
        savePanel.add(save4);
        add(savePanel);
    }*/
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
