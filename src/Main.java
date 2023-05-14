//import model.BackgroundMusic;
import model.BGM;
import view.BeginFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BeginFrame beginFrame = new BeginFrame();
            beginFrame.setVisible(true);
            new BGM().playMusic("resource/BGM.wav");
        });
    }
}
