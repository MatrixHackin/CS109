package model;

public class Step {
    public BoardPoint src;
    public BoardPoint dest;
    public Boolean ismove;
    public Chess eated;
    public Chess eater;
    public Step(BoardPoint src,BoardPoint dest,Chess moved){
        ismove=true;
        this.src=src;
        this.dest=dest;
    }
    public Step(BoardPoint src,BoardPoint dest,Chess eated,Chess eater){
        ismove=false;
        this.src=src;
        this.dest=dest;
        this.eated=eated;
        this.eater=eater;
    }

}
