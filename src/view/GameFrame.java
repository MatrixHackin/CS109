package view;

import javax.swing.*;
import java.awt.*;

import static model.Constant.GAMEBG_COL_SIZE;
import static model.Constant.GAMEBG_ROW_SIZE;

public class GameFrame extends JFrame {
    public BeginFrame beginFrame;
    private final int ONE_CHESS_SIZE;

    private BoardView boardView;
    LoginFrame loginFrame;
    JLabel turnLable;
    JLabel timeLabel;

    public boolean isDay;
    JLabel background;
    public final JLabel springBG;
    public final JLabel autumnBG;

    public GameFrame() {
        setTitle("Jungle");
        this.ONE_CHESS_SIZE = 80;
        this.setSize(GAMEBG_ROW_SIZE.getNum() + 15, GAMEBG_COL_SIZE.getNum() + 30);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        isDay = true;
        addChessboard();

        Image image = new ImageIcon("resource/map.png").getImage();
        image = image.getScaledInstance(GAMEBG_ROW_SIZE.getNum(), GAMEBG_COL_SIZE.getNum(), Image.SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(image);
        springBG = new JLabel(icon);
        springBG.setSize(GAMEBG_ROW_SIZE.getNum(), GAMEBG_COL_SIZE.getNum());
        springBG.setLocation(0, 0);

        image = new ImageIcon("resource/night.png").getImage();
        image = image.getScaledInstance(GAMEBG_ROW_SIZE.getNum(), GAMEBG_COL_SIZE.getNum(), Image.SCALE_DEFAULT);
        icon = new ImageIcon(image);
        autumnBG = new JLabel(icon);
        autumnBG.setSize(GAMEBG_ROW_SIZE.getNum(), GAMEBG_COL_SIZE.getNum());
        autumnBG.setLocation(0, 0);

        background = springBG;
        add(background);

    }

    public BoardView getBoardView() {
        return boardView;
    }

    public void setBoardView(BoardView boardView) {
        this.boardView = boardView;
    }

    private void addChessboard() {
        boardView = new BoardView(ONE_CHESS_SIZE, turnLable, timeLabel);
        boardView.setLocation(140, 120);
        add(boardView);
    }

    private void addStatusLabel() {
        turnLable = new JLabel("Turn 1: BLUE");
        turnLable.setLocation(HEIGHT - 40, HEIGHT / 10);
        turnLable.setSize(200, 60);
        turnLable.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(turnLable);
    }

    private void addTimeLabel() {
        timeLabel = new JLabel("Time: 45");
        timeLabel.setLocation(HEIGHT + 140, HEIGHT / 10);
        timeLabel.setSize(200, 60);
        timeLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(timeLabel);
    }
}
