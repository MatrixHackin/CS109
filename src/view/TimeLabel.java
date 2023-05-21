package view;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimeLabel extends JLabel {
    Timer timer;
    int counter=15;
    Controller controller;
    public TimeLabel() {
        this.timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                counter--;
                if (counter >= 0) {
                    setText(Integer.toString(counter));
                } else {
                    counter = 15;
                }
            }
        });
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
