package logic;

import pieces.King;
import pieces.Piece;

public class GameLogic {
    public boolean isKingInCheck(Piece[][] board, boolean isWhiteKing) {
        int kingX = -1, kingY = -1;
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board.length; x++) {
                if (board[y][x] instanceof King && board[y][x].isWhite() == isWhiteKing) {
                    kingX = x;
                    kingY = y;
                    break;
                }
            }
        }

        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board.length; x++) {
                if (board[y][x] != null && board[y][x].isWhite() != isWhiteKing) {
                    if (board[y][x].isValidMove(kingY, kingX, board)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isCheckMate(Piece[][] board, boolean isWhiteKing) {
        if (!isKingInCheck(board, isWhiteKing)) {
            return false;
        }
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board.length; x++) {
                if (hasAnyValidMove(board[y][x], y, x, board, isWhiteKing)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean hasAnyValidMove(Piece piece, int fromY, int fromX, Piece[][] board, boolean isWhiteKing) {
        if (piece == null || piece.isWhite() != isWhiteKing) {
            return false;
        }
        for (int toY = 0; toY < board.length; toY++) {
            for (int toX = 0; toX < board.length; toX++) {
                if (piece.isValidMove(toY, toX, board)) {
                    if (wouldMoveBeLegal(fromY, fromX, toY, toX, board)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean wouldMoveBeLegal(int fromY, int fromX, int toY, int toX, Piece[][] board) {
        Piece[][] tempBoard = copyBoard(board);
        Piece movingPiece = tempBoard[fromY][fromX];

        int originalX = movingPiece.getX();
        int originalY = movingPiece.getY();

        tempBoard[toY][toX] = movingPiece;
        tempBoard[fromY][fromX] = null;
        movingPiece.setX(toX);
        movingPiece.setY(toY);

        boolean isWhite = movingPiece.isWhite();
        boolean legal = !isKingInCheck(tempBoard, isWhite);

        movingPiece.setX(originalX);
        movingPiece.setY(originalY);

        return legal;
    }

    private Piece[][] copyBoard(Piece[][] board) {
        Piece[][] result = new Piece[board.length][board.length];
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board.length; x++) {
                if (board[y][x] != null) {
                    result[y][x] = board[y][x].copy();
                }
            }
        }
        return result;
    }
}
