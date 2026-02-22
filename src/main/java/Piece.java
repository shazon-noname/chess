import javax.swing.*;
import java.io.IOException;

public abstract class Piece {
    protected int y,x;
    protected boolean isWhite;

    public Piece(int targetY, int targetX, boolean isWhite) {
        this.y = targetY;
        this.x = targetX;
        this.isWhite = isWhite;
    }

    public abstract boolean isValidMove(int targetY, int targetX, Piece[][] board);

    public abstract String getSymbol();

    public abstract Icon getIcon() throws IOException;
}
