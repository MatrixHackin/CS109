package model;


import java.awt.*;

public enum Player {
    BLUE(Color.BLUE), RED(Color.RED);

    private final Color color;

    Player(Color color) {
        this.color = color;
    }


    public Color getColor() {
        return color;
    }


}
