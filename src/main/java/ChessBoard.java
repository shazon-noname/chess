import javax.swing.*;
import java.awt.*;

public class ChessBoard extends JFrame {
    private Piece selectedPiece = null;
    private final int SIZE = 8;
    private JButton[][] squares = new JButton[SIZE][SIZE];
    private Piece[][] board = new Piece[SIZE][SIZE];

    public ChessBoard() {
        setTitle("Java Chess");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(SIZE, SIZE));
        initializeBoard();
        setVisible(true);
    }

    private void initializeBoard() {
        board[6][0] = new Pawn(6, 0, true);
        board[6][1] = new Pawn(6, 1, true);
        board[6][2] = new Pawn(6, 2, true);
        board[6][3] = new Pawn(6, 3, true);
        board[6][4] = new Pawn(6, 4, true);
        board[6][5] = new Pawn(6, 5, true);
        board[6][6] = new Pawn(6, 6, true);
        board[6][7] = new Pawn(6, 7, true);

        board[1][0] = new Pawn(1, 0, false);
        board[1][1] = new Pawn(1, 1, false);
        board[1][2] = new Pawn(1, 2, false);
        board[1][3] = new Pawn(1, 3, false);
        board[1][4] = new Pawn(1, 4, false);
        board[1][5] = new Pawn(1, 5, false);
        board[1][6] = new Pawn(1, 6, false);
        board[1][7] = new Pawn(1, 7, false);

        board[7][0] = new Rook(7, 0, true);
        board[7][7] = new Rook(7, 7, true);
        board[0][0] = new Rook(0, 0, false);
        board[0][7] = new Rook(0, 7, false);


        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                JButton jButton = new JButton();
                jButton.setFont(new Font("Serif", Font.BOLD, 30));
                if ((row + col) % 2 == 0) {
                    jButton.setBackground(Color.LIGHT_GRAY);
                } else {
                    jButton.setBackground(Color.DARK_GRAY);
                }
                if (board[row][col] != null) {
                    jButton.setText(board[row][col].getSymbol());
                }
                squares[row][col] = jButton;
                add(jButton);

                final int currentRow = row;
                final int currentCol = col;

                jButton.addActionListener(e -> {

                    if (selectedPiece == null) {
                        selectedPiece = board[currentRow][currentCol];
                    } else {
                        if (selectedPiece.isValidMove(currentRow, currentCol, board)) {
                            board[selectedPiece.y][selectedPiece.x] = null;

                            selectedPiece.y = currentRow;
                            selectedPiece.x = currentCol;

                            board[currentRow][currentCol] = selectedPiece;
                        } else {
                            System.out.println("Invalid move");
                        }
                        selectedPiece = null;
                        refreshBoard();
                    }

                });
            }
        }
    }

    private void refreshBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] != null) {
                    squares[row][col].setText(board[row][col].getSymbol());
                } else {
                    squares[row][col].setText("");
                }
            }
        }
    }

    public static void main(String[] args) {
        new ChessBoard();
    }
}