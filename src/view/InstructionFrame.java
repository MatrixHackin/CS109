package view;

import javax.swing.*;
import java.awt.*;

public class InstructionFrame extends MyFrame {
    JFrame beginFrame;
    JButton backButton;
    JLabel ruleTitle;
    JScrollPane instructionText;
    public InstructionFrame(){
        super(800,600);

        addBackButton();
        addInstructionText();
        addRuleTitle();

        this.setBackground("resource/Instructions.gif");
    }
    private void addBackButton(){
        this.backButton=new HomeButton("Back",300,500);
        backButton.addActionListener((e) -> {
            this.setVisible(false);
            beginFrame.setVisible(true);
        });
        add(backButton);
    }
    private void addInstructionText(){
        JTextArea textArea=new JTextArea();
        textArea.setEditable(false);
        textArea.setOpaque(false);
        textArea.setLineWrap(true);
        textArea.setLineWrap(true);
        textArea.setFont(new Font("Arial",Font.BOLD,30));
        textArea.setForeground(new Color(255,235,205));
        textArea.setText("Rules\n" +
                "1. Game Initialization: At the beginning, all 16 pieces are put into the chessboard as the initial" +
                "state. The initial state is shown in Figure 6.\n" +
                "2. Game Progress: The player with blue moves first. Two players take the turn alternatively until " +
                "the game is finished. When a player takes turn, he/she can select one of his pieces and do " +
                "one of the following:\n" +
                "\uF06C Moving one square horizontally or vertically. For lion, tiger and rat, they also have special " +
                "movements related to the river squares, which have been introduced previously.\n" +
                "\uF06C Capturing an opposing piece. In all captures, the captured pieces is removed from the board " +
                "and the square is occupied by the capturing piece. A piece can capture any enemy piece " +
                "following the rules introduced in \"Pieces\".\n" +
                "3. Game Termination: A player loses the game if one of the following happens:\n" +
                "\uF06C The den is entered by his/her opponents.\n" +
                "\uF06C All of his/her pieces are captured and he/her is unable to do any movement.");

        this.instructionText=new JScrollPane(textArea);
        instructionText.setSize(714,350);
        instructionText.setLocation(50,100);
        instructionText.setBackground(new Color(56,94,15,200));
        instructionText.getViewport().setOpaque(false);
        add(instructionText);
    }
    private void addRuleTitle(){
        this.ruleTitle=new JLabel("Rule");
        ruleTitle.setForeground(new Color(46,139,42));
        ruleTitle.setFont(new Font("Rockwell",Font.BOLD,100));
        ruleTitle.setLocation(275,0);
        ruleTitle.setSize(300,100);
        add(ruleTitle);
    }
}
