package view;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    int backgroundWidth;
    int backgroundHeight;
    JLabel background;
    public MyFrame(int backgroundWidth,int backgroundHeight) {
        this.backgroundWidth=backgroundWidth;
        this.backgroundHeight=backgroundHeight;
        setTitle("Jungle");
        this.setSize(backgroundWidth + 15, backgroundHeight + 36);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

    }

    public void setBackground(String backgroundPath) {
        Image image = new ImageIcon(backgroundPath).getImage();
        image = image.getScaledInstance(backgroundWidth, backgroundHeight, Image.SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(image);
        this.background = new JLabel(icon);
        background.setSize(backgroundWidth, backgroundHeight);
        background.setLocation(0, 0);
        add(background);
    }
    public void removeBackground(){
        remove(background);
    }
}
