public abstract class Piece {
    protected int y,x;
    protected boolean isWhite;

    public Piece(int y, int x, boolean isWhite) {
        this.y = y;
        this.x = x;
        this.isWhite = isWhite;
    }

    public abstract boolean isValidMove(int y, int x, Piece[][] board);
}
