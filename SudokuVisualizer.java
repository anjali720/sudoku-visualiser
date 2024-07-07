import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SudokuVisualizer {

    private static final int GRID_SIZE = 9;  
    private static final int CELL_SIZE = 60; 
    private static final int GRID_WIDTH = CELL_SIZE * GRID_SIZE;
    private static final int GRID_HEIGHT = CELL_SIZE * GRID_SIZE;

    private static JFrame frame;
    private static JPanel sudokuPanel;
    private static JTextField[][] textFieldGrid;
    private static JButton solveButton;
    private static JButton clearButton;

   
    private static int[][] board = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
    };

    public static void visualize() {
        frame = new JFrame("Sudoku Visualizer");
        frame.setSize(GRID_WIDTH + 200, GRID_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        sudokuPanel = new JPanel();
        sudokuPanel.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));
        frame.add(sudokuPanel, BorderLayout.CENTER);

        textFieldGrid = new JTextField[GRID_SIZE][GRID_SIZE];
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                int value = board[row][col];
                JTextField textField = new JTextField(value == 0 ? "" : String.valueOf(value));
                textField.setHorizontalAlignment(JTextField.CENTER);
                textField.setFont(new Font("SansSerif", Font.PLAIN, 20));
                textField.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
                sudokuPanel.add(textField);
                textFieldGrid[row][col] = textField;
            }
        }

        JPanel buttonPanel = new JPanel();
        solveButton = new JButton("Solve");
        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solveSudoku();
            }
        });
        buttonPanel.add(solveButton);

        clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearBoard();
            }
        });
        buttonPanel.add(clearButton);

        frame.add(buttonPanel, BorderLayout.EAST);

        frame.setVisible(true);
    }

    private static boolean solveSudoku() {
        int[][] inputBoard = new int[GRID_SIZE][GRID_SIZE];

   
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                String text = textFieldGrid[row][col].getText().trim();
                if (text.isEmpty()) {
                    inputBoard[row][col] = 0;  
                } else {
                    try {
                        int value = Integer.parseInt(text);
                        if (value < 1 || value > 9) {
                            JOptionPane.showMessageDialog(frame, "Invalid input: Enter numbers from 1 to 9 or leave empty for blank cells.");
                            return false;
                        }
                        inputBoard[row][col] = value;
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Invalid input: Enter numbers from 1 to 9 or leave empty for blank cells.");
                        return false;
                    }
                }
            }
        }

      
        if (solveSudokuRecursive(inputBoard)) {
            
            for (int row = 0; row < GRID_SIZE; row++) {
                for (int col = 0; col < GRID_SIZE; col++) {
                    board[row][col] = inputBoard[row][col];
                }
            }
            updateGUI();
            return true;
        } else {
            JOptionPane.showMessageDialog(frame, "No solution exists for this Sudoku puzzle.");
            return false;
        }
    }

    private static boolean solveSudokuRecursive(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (board[row][col] == 0) { 
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;
                            if (solveSudokuRecursive(board)) {
                                return true;
                            } else {
                                board[row][col] = 0;  
                            }
                        }
                    }
                    return false;  
                }
            }
        }
        return true;  
    }

    private static boolean isValid(int[][] board, int row, int col, int num) {
        
        for (int c = 0; c < GRID_SIZE; c++) {
            if (board[row][c] == num) {
                return false;
            }
        }
       
        for (int r = 0; r < GRID_SIZE; r++) {
            if (board[r][col] == num) {
                return false;
            }
        }
       
        int boxStartRow = row - row % 3;
        int boxStartCol = col - col % 3;
        for (int r = boxStartRow; r < boxStartRow + 3; r++) {
            for (int c = boxStartCol; c < boxStartCol + 3; c++) {
                if (board[r][c] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void clearBoard() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                board[row][col] = 0;
                textFieldGrid[row][col].setText("");
            }
        }
    }

    private static void updateGUI() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                int value = board[row][col];
                textFieldGrid[row][col].setText(value == 0 ? "" : String.valueOf(value));
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                visualize();
            }
        });
    }
}
