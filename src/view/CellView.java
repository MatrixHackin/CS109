package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class CellView extends JPanel {
    int size;
    public boolean canStep;
    public boolean mouseAt;
    public CellType type;

    public CellView(Point location, int size, CellType type) {
        setLayout(new GridLayout(1, 1));
        setLocation(location);
        setSize(size, size);
        this.size = size;
        this.type = type;
        this.setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.setColor(new Color(0,0,0,0));
        g.fillRect(1, 1, this.getWidth() - 1, this.getHeight() - 1);

        if (canStep) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(new Color(255, 253, 87, 150));
            RoundRectangle2D roundedRectangle = new RoundRectangle2D.Double(1, 1,
                    this.getWidth() - 1, this.getHeight() - 1, size / 4, size / 4);
            g2d.fill(roundedRectangle);
        }
    }

}
