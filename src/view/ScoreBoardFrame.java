package view;

import model.User;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ScoreBoardFrame extends MyFrame{
    BeginFrame beginFrame;
    ArrayList<User> users;
    JLabel title;
    JScrollPane scoreBoard;

    public ScoreBoardFrame() throws FileNotFoundException {
        super(800,600);

        //读入用户数据
        this.users= new ArrayList<>();
        File file=new File("user/users");
        Scanner scanner=new Scanner(file);
        while (scanner.hasNextLine()){
            String line= scanner.nextLine();
            String[] parts =line.split(" ");
            String name=parts[0];
            String password=parts[1];
            String score=parts[2];
            int scores=Integer.parseInt(score);
            User user1=new User(name,password,scores);
            users.add(user1);
        }
        scanner.close();
        addScoreBoard();
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
        addTitle();
        addScoreBoard();

    }
    private void addScoreBoard(){
        String[] columnNames={"ID","Name","Score"};
        Object[][] data=new Object[users.size()][3];
        int i=0;
        for(User e:users){
            data[i][0]=i+1;
            data[i][1]=e.getName();
            data[i][2]=e.getScore();
            i++;
        }
        JTable table=new JTable(data,columnNames);
        this.scoreBoard=new JScrollPane(table);
        scoreBoard.setSize(714,350);
        scoreBoard.setLocation(50,100);
        scoreBoard.setBackground(new Color(56,94,15,200));
        scoreBoard.getViewport().setOpaque(false);
        add(scoreBoard);
    }
    private void addTitle(){
        this.title=new JLabel("Score Board");
        title.setForeground(new Color(46,139,42));
        title.setFont(new Font("Rockwell",Font.BOLD,50));
        title.setLocation(275,0);
        title.setSize(300,100);
        add(title);
    }
}
