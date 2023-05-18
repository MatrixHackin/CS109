//import model.BackgroundMusic;
import model.BGM;
import view.BeginFrame;
import view.MyFrame;

import javax.swing.*;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BeginFrame beginFrame = null;
            try {
                beginFrame = new BeginFrame();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            beginFrame.setVisible(true);
            new BGM().playMusic("resource/BGM.wav");
        });
    }
}
