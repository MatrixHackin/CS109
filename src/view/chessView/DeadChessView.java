package view.chessView;

import javax.swing.*;
import java.awt.*;

public class DeadChessView {
    JLabel label;
    public DeadChessView(String path){
        Image image = new ImageIcon(path).getImage();
        image = image.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(image);
        this.label=new JLabel(icon);
        label.setSize(50,50);
    }
    public JLabel getLabel(){
        return label;
    }
}
