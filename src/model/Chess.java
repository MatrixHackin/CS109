package model;


import java.io.Serial;
import java.io.Serializable;

public class Chess implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Player player;
    public int rank;
    private int FinalRank;

    public Chess(Player player, int rank) {
        this.player = player;
        this.rank = rank;
        this.FinalRank = rank;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Player getPlayer() {
        return player;
    }

    public int getFinalRank() {
        return FinalRank;
    }

}
