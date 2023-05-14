package listener;

import model.BoardPoint;
import view.CellView;
import view.chessView.AnimalView;

public interface GameListener {

    void clickCell(BoardPoint point, CellView component);


    void clickChess(BoardPoint point, AnimalView component);

}
