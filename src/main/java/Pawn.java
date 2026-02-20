public class Pawn extends Piece {
    public Pawn(int y, int x, boolean isWhite) {
        super(y, x, isWhite);
    }

    @Override
    public boolean isValidMove(int targetY, int targetX, Piece[][] board) {
        if (isWhite) {
            if (targetY == this.y - 1 && targetX == this.x) {
                return board[targetY][targetX] == null;
            }
        } else {
            if (targetY == this.y + 1 && targetX == this.x) {
                return board[targetY][targetX] == null;
            }
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return isWhite ? "♙" : "♟";
    }
}
