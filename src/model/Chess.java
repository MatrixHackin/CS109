package model;


public class Chess {
    private Player player;
    public int rank;

    public Chess(Player player, int rank) {
        this.player = player;
        this.rank = rank;
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
}
