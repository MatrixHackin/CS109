package view;

import javax.swing.*;
import java.awt.*;

public class TurnLabel extends JPanel {

    public void paint(Graphics g){
        int[] x={0,50,25};
        int[] y = {50, 50, 0};
        int n = 3;
        g.fillPolygon(x,y,n);
    }
}
