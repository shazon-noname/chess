import javax.swing.*;
import java.awt.*;

public class ChessBoard extends JFrame {
    private Piece selectedPiece = null;
    private final int SIZE = 8;
    private JButton[][] squares = new JButton[SIZE][SIZE];
    private Piece[][] board = new Piece[SIZE][SIZE];

    public ChessBoard() {
        setTitle("Java Chess");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(SIZE, SIZE));
        initializeBoard();
        setVisible(true);
    }

    private void initializeBoard() {
        board[6][0] = new Pawn(6, 0, true);
        board[1][5] = new Pawn(1, 5, false);
        for (int row = 0; row < SIZE; row++) {
            for (int color = 0; color < SIZE; color++) {
                JButton jButton = new JButton();
                jButton.setFont(new Font("Serif", Font.BOLD, 30));
                if ((row + color) % 2 == 0) {
                    jButton.setBackground(Color.LIGHT_GRAY);
                } else {
                    jButton.setBackground(Color.DARK_GRAY);
                }
                if (board[row][color] != null) {
                    jButton.setText("♙");
                }
                squares[row][color] = jButton;
                add(jButton);

                final int currentRow = row;
                final int currentCol = color;

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
                    squares[row][col].setText("♙");
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