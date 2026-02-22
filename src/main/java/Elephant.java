import javax.swing.*;
import java.io.IOException;

public class Elephant extends Piece {
    public Elephant(int targetY, int targetX, boolean isWhite) {
        super(targetY, targetX, isWhite);
    }

    @Override
    public boolean isValidMove(int targetY, int targetX, Piece[][] board) {
        if (!(targetY != this.y && targetX != this.x)) {
            return false;
        }

        int stepY = Integer.compare(targetY, this.y);
        int stepX = Integer.compare(targetX, this.x);

        int currentY = this.y + stepY;
        int currentX = this.x + stepX;

        while (currentY != targetY || currentX != targetX) {
            if (board[currentY][currentX] != null) {
                return false;
            }
            currentY += stepY;
            currentX += stepX;
        }

        Piece targetPiece = board[targetY][targetX];

        return targetPiece == null || targetPiece.isWhite != this.isWhite;

    }

    @Override
    public String getSymbol() {
        return isWhite ? "♗" : "♝"
                ;
    }

    @Override
    public Icon getIcon() throws IOException {
        return PieceIcons.elephant(isWhite, 50);
    }
}
