package pieces;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class PieceIcons {
    private static final int ROWS = 2;
    private static final int COLS = 6;
    private static final String spritePath = "/pieces/pieces.png";
    private static BufferedImage sprite;


    public static BufferedImage sprite() throws IOException {
        if (sprite != null) {
            return sprite;
        }

        URL resource = PieceIcons.class.getResource(spritePath);
        if (resource == null) {
            return null;
        }

        BufferedImage read = ImageIO.read(resource);
        if (read == null) {
            return null;
        } else {
            sprite = read;
            return sprite;
        }
    }

    public static Icon getIcon(int col, boolean isWhite, int sizePx) throws IOException {
        BufferedImage sheet = sprite();
        if (sheet == null) {
            return null;
        }

        int row = isWhite ? 0 : 1;
        int cellH = sheet.getHeight() / ROWS;
        int cellW = sheet.getWidth() / COLS;

        if (col < 0 || col >= COLS) {
            return null;
        }

        BufferedImage sub = sheet.getSubimage(col * cellW, row * cellH, cellW, cellH);
        Image scaledInstance = sub.getScaledInstance(sizePx, sizePx, Image.SCALE_SMOOTH);

        return new ImageIcon(scaledInstance);
    }

    public static Icon rook(boolean isWhite, int sizePx) throws IOException {
        return getIcon(4, isWhite, sizePx);
    }
    public static Icon elephant(boolean isWhite, int sizePx) throws IOException {
        return getIcon(2, isWhite, sizePx);
    }
    public static Icon pawn(boolean isWhite, int sizePx) throws IOException {
        return getIcon(5, isWhite, sizePx);
    }
}
