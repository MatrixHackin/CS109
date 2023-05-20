package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SettingFrame extends MyFrame {
    BeginFrame beginFrame;
    JCheckBox checkBox;
    public SettingFrame()  {
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