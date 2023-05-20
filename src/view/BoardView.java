package view;


import model.BoardPoint;
import controller.Controller;
import model.*;
import view.chessView.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;

import static model.Constant.CHESSBOARD_COL_SIZE;
import static model.Constant.CHESSBOARD_ROW_SIZE;

public class BoardView extends JPanel {
    public CellView[][] gridViews = new CellView[CHESSBOARD_ROW_SIZE.getNum()][CHESSBOARD_COL_SIZE.getNum()];
    private int CHESS_SIZE;
    private Set<BoardPoint> riverCell = new HashSet<>();
    private Set<BoardPoint> trapCell = new HashSet<>();
    private Set<BoardPoint> denCell = new HashSet<>();

    public Controller controller;
    public TurnLabel turnLabel;
    public JLabel timeLabel;
    public BoardView(int chessSize,TurnLabel turnLabel, JLabel timeLabel) {
        this.timeLabel = timeLabel;
        this.turnLabel=turnLabel;
        CHESS_SIZE = chessSize;
        int width = CHESS_SIZE * 9;
        int height = CHESS_SIZE * 7;
        this.setOpaque(false);
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        setLayout(null);
        setSize(width, height);
        initiateGridComponents();

    }

    public void initiateChessComponent(Board board) {
        Cell[][] grid = board.getGrid();
        for (int i = 0; i < CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < CHESSBOARD_COL_SIZE.getNum(); j++) {
                if (grid[i][j].getChess() != null) {
                    Chess chess = grid[i][j].getChess();
                    //System.out.println(chess.getOwner());
                    if (chess.getRank() == 8) {
                        gridViews[i][j].add(new ElephantView(chess.getPlayer(), CHESS_SIZE));
                    }
                    if (chess.getRank() == 7) {
                        gridViews[i][j].add(new LionView(chess.getPlayer(), CHESS_SIZE));
                    }
                    if (chess.getRank() == 6) {
                        gridViews[i][j].add(new TigerView(chess.getPlayer(), CHESS_SIZE));
                    }
                    if (chess.getRank() == 5) {
                        gridViews[i][j].add(new LeopardView(chess.getPlayer(), CHESS_SIZE));
                    }
                    if (chess.getRank() == 4) {
                        gridViews[i][j].add(new WolfView(chess.getPlayer(), CHESS_SIZE));
                    }
                    if (chess.getRank() == 3) {
                        gridViews[i][j].add(new DogView(chess.getPlayer(), CHESS_SIZE));
                    }
                    if (chess.getRank() == 2) {
                        gridViews[i][j].add(new CatView(chess.getPlayer(), CHESS_SIZE));
                    }
                    if (chess.getRank() == 1) {
                        gridViews[i][j].add(new RatView(chess.getPlayer(), CHESS_SIZE));
                    }

                }
            }
        }
    }

    public void removeChessComponent() {
        for (int i = 0; i < CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < CHESSBOARD_COL_SIZE.getNum(); j++) {
                try {
                    gridViews[i][j].remove(0);
                } catch (Exception e) {
                }
            }
        }

    }


    public void initiateGridComponents() {

        riverCell.add(new BoardPoint(1, 3));
        riverCell.add(new BoardPoint(2, 3));
        riverCell.add(new BoardPoint(1, 4));
        riverCell.add(new BoardPoint(2, 4));
        riverCell.add(new BoardPoint(1, 5));
        riverCell.add(new BoardPoint(2, 5));

        riverCell.add(new BoardPoint(4, 3));
        riverCell.add(new BoardPoint(5, 3));
        riverCell.add(new BoardPoint(4, 4));
        riverCell.add(new BoardPoint(5, 4));
        riverCell.add(new BoardPoint(4, 5));
        riverCell.add(new BoardPoint(5, 5));

        trapCell.add(new BoardPoint(2, 8));
        trapCell.add(new BoardPoint(4, 8));
        trapCell.add(new BoardPoint(3, 7));

        trapCell.add(new BoardPoint(2, 0));
        trapCell.add(new BoardPoint(4, 0));
        trapCell.add(new BoardPoint(3, 1));

        denCell.add(new BoardPoint(3, 8));
        denCell.add(new BoardPoint(3, 0));

        for (int i = 0; i < CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < CHESSBOARD_COL_SIZE.getNum(); j++) {
                BoardPoint temp = new BoardPoint(i, j);
                CellView cell;
                if (riverCell.contains(temp)) {
                    cell = new CellView(calculatePoint(i, j), CHESS_SIZE, CellType.RIVER);
                    this.add(cell);
                } else if (trapCell.contains(temp)) {
                    cell = new CellView(calculatePoint(i, j), CHESS_SIZE, CellType.TRAP);
                    this.add(cell);
                } else if (denCell.contains(temp)) {
                    cell = new CellView(calculatePoint(i, j), CHESS_SIZE, CellType.DEN);
                    this.add(cell);
                } else {
                    cell = new CellView(calculatePoint(i, j), CHESS_SIZE, CellType.GRASS);
                    this.add(cell);
                }
                gridViews[i][j] = cell;
            }
        }
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setChessViewAtCell(BoardPoint point, AnimalView chess) {
        getCellViewAt(point).add(chess);
    }

    public AnimalView removeChessViewAtGrid(BoardPoint point) {
        AnimalView chess =(AnimalView) getCellViewAt(point).getComponents()[0];
        getCellViewAt(point).removeAll();
        getCellViewAt(point).revalidate();
        chess.setSelected(false);
        return chess;
    }

    private CellView getCellViewAt(BoardPoint point) {
        return gridViews[point.getRow()][point.getCol()];
    }

    private BoardPoint getBoardPoint(Point point) {
        return new BoardPoint(point.y / CHESS_SIZE, point.x / CHESS_SIZE);
    }

    private Point calculatePoint(int row, int col) {
        return new Point(col * CHESS_SIZE, row * CHESS_SIZE);
    }

    //使用抗锯齿效果使图像渲染更加平滑
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    @Override
    public void processMouseEvent(MouseEvent e) {
        if (e.getID() == MouseEvent.MOUSE_PRESSED) {
            JComponent clickedComponent = (JComponent) getComponentAt(e.getX(), e.getY());
            if (clickedComponent.getComponentCount() == 0) {
                controller.clickCell(getBoardPoint(e.getPoint()), (CellView) clickedComponent);
            } else {
                controller.clickChess(getBoardPoint(e.getPoint()), (AnimalView) clickedComponent.getComponents()[0]);
            }
        }
    }
    @Override
    public void processMouseMotionEvent(MouseEvent e) {
        if (e.getID() == MouseEvent.MOUSE_PRESSED) {
            CellView component = (CellView) getComponentAt(e.getX(), e.getY());
            setMouseAtFalse();
            component.mouseAt = true;
            component.repaint();
            component.revalidate();
            repaint();
            revalidate();
        }
    }

    private void setMouseAtFalse() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 7; j++) {
                gridViews[i][j].mouseAt = false;
            }
        }
    }
}
