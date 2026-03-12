package logic;

import pieces.King;
import pieces.Piece;

public class GameLogic {
    public boolean isKingSafe(Piece[][] board, boolean isWhiteKing) {
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

        for (Piece[] pieces : board) {
            for (int x = 0; x < board.length; x++) {
                if (pieces[x] != null && pieces[x].isWhite() != isWhiteKing) {
                    if (pieces[x].isValidMove(kingY, kingX, board)) {
                        return false; // King is in check, so not safe
                    }
                }
            }
        }
        return true;
    }

    public boolean isCheckMate(Piece[][] board, boolean isWhiteKing) {
        if (isKingSafe(board, isWhiteKing)) {
            return false;
        }
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board.length; x++) {
                if (hasAnyValidMove(board[y][x], y, x, board)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean hasAnyValidMove(Piece piece, int fromY, int fromX, Piece[][] board) {
        if (piece == null) {
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

        tempBoard[toY][toX] = board[fromY][fromX];
        tempBoard[fromY][fromX] = null;

        boolean isWhite = tempBoard[toY][toX].isWhite();

        return isKingSafe(tempBoard, isWhite);

    }

    private Piece[][] copyBoard(Piece[][] board) {
        Piece[][] result = new Piece[board.length][board.length];
        for (int y = 0; y < board.length; y++) {
            System.arraycopy(board[y], 0, result[y], 0, board.length);
        }
        return result;
    }
}
