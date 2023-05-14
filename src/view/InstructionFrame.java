package view;

import javax.swing.*;
import java.awt.*;

public class InstructionFrame extends JFrame {
    JFrame beginFrame;
    JButton backButton;
    public InstructionFrame(){
        setTitle("Jungle");
        this.setSize(800, 600);

        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);

        addBackButton();

        Image image = new ImageIcon("resource/Instructions.gif").getImage();
        image = image.getScaledInstance(800, 600, Image.SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(image);
        JLabel bg = new JLabel(icon);
        bg.setSize(800, 600);
        bg.setLocation(0, 0);
        add(bg);
    }
    private void addBackButton(){
        this.backButton=new HomeButton("Back",100,200,beginFrame);
        backButton.addActionListener((e) -> {
            this.setVisible(false);
            beginFrame.setVisible(true);
        });
        add(backButton);
    }

}
