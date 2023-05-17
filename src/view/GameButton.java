package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameButton extends JButton {
    public GameButton(String string){
        Image image = new ImageIcon(string).getImage();
        image = image.getScaledInstance( 40,40, Image.SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(image);
        this.setIcon(icon);
        this.setOpaque(false);
        this.setBackground(Color.GREEN);
        this.setSize(50,50);


    }

}
