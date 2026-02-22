import javax.swing.*;
import java.io.IOException;

public class Pawn extends Piece {
    public Pawn(int y, int x, boolean isWhite) {
        super(y, x, isWhite);
    }

    @Override
    public boolean isValidMove(int targetY, int targetX, Piece[][] board) {
        if (isWhite) {
            if (this.y == 6 && targetY == this.y - 2) {
                return board[targetY][targetX] == null;
            } else if (targetY == this.y - 1 && targetX == this.x) {
                return board[targetY][targetX] == null;
            }
        } else {
            if (this.y == 1 && targetY == this.y + 2) {
                return board[targetY][targetX] == null;
            } else if (targetY == this.y + 1 && targetX == this.x) {
                return board[targetY][targetX] == null;
            }
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return isWhite ? "♙" : "♟";
    }

    @Override
    public Icon getIcon() throws IOException {
        return PieceIcons.pawn(isWhite, 50);
    }

}
