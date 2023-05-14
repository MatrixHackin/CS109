package model;
//方便写悔棋来记录一下step
public class Step {
    public BoardPoint src;
    public BoardPoint dest;
    public Player color;
    public Chess captured;

    public Step(BoardPoint src, BoardPoint dest, Player color) {
        this.src = src;
        this.dest = dest;
        this.color = color;
        captured = null;
    }

    public Step(BoardPoint src, BoardPoint dest, Player color, Chess captured) {
        this.src = src;
        this.dest = dest;
        this.color = color;
        this.captured = captured;
    }
}
