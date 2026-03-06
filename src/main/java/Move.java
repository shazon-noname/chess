import pieces.Piece;

public class Move {
    Piece piece;
    int fromX, fromY, toY, toX;
    Piece capturedPiece;

    public Move(Piece piece, int fromX, int fromY, int toY, int toX, Piece capturedPiece) {
        this.piece = piece;
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
        this.capturedPiece = capturedPiece;
    }

    public String getDetailInfo() {
        String color = piece.isWhite() ? "White" : "Black";
        String pieceType = piece.getClass().getSimpleName();
        String fromSquare = (char) ('a' + fromX) + "" + (8 - fromY);
        String toSquare = (char) ('a' + toX) + "" + (8 - toY);

        String captureInfo = "";
        if (capturedPiece != null) {
            captureInfo = " (captures" + capturedPiece.getClass().getSimpleName() + ")";
        }

        return color + " " + " " + pieceType + " " + fromSquare + " " + toSquare + " " + captureInfo;
    }

    @Override
    public String toString() {
        return getDetailInfo();
    }
}
