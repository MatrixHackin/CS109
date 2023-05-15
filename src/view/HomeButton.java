package view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HomeButton extends JButton {
    public HomeButton(String name, int x, int y) {
        this.setText(name);
        this.setLocation(x,y);
        this.setFont(new Font("Arial", Font.BOLD, 20));
        this.setBorder(new LineBorder(new Color(94,38,18)));
        this.setForeground(new Color(255,235,205));
        this.setBackground(new Color(160, 82, 45));
        this.setSize(200, 60);
        this.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                setForeground(new Color(160,82,45));
                setBackground(new Color(255, 235, 205));
            }
            public void mouseExited(MouseEvent e) {
                setForeground(new Color(255,235,205));
                setBackground(new Color(160, 82, 45));
            }
        });
    }
}
