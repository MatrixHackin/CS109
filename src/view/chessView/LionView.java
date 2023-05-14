package view.chessView;

import model.Player;

import javax.swing.*;
import java.awt.*;

public class LionView extends AnimalView {

    public LionView(Player owner, int size) {
        this.owner = owner;
        this.selected = false;
        setSize(size/2, size/2);
        setLocation(0,0);
        setVisible(true);
        this.size = size;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        ImageIcon pic = new ImageIcon("resource\\animals\\left\\7.png");
        if (owner == Player.BLUE){
            pic = new ImageIcon("resource\\animals\\right\\7.png");
        }
        Image image = pic.getImage();
        pic = new ImageIcon(image.getScaledInstance(size,size,Image.SCALE_SMOOTH));
        JLabel label = new JLabel(pic);
        label.setSize(size, size);
        //bgLabel.setLocation(0, 0);
        add(label);
    }
}
