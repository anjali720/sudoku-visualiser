 A Java program that displays the Sudoku puzzle through Swing components. It does simple things like let you enter numbers into a grid, try to solve it using the backtracking algorithsm solver that I came up with on my own and gives buttons for solving or clear the puzzle.

Constants and Variables:

is: GRID_SIZE - it generates a Sudoku grid of size (GRID)_SIZE x SIZE commonly 9x9)

CELL_SIZE: This just determines the size of each cell in our grid so when we visualize it.

GRID_WIDTH, GRID_HEIGHT: The overall dimensions of the Sudoku grid (based on GRID_SIZE and CELL_SIZE)

board -> it is a 2D array which represents the original Sudoku puzzle. Where the Zeros are seeing represents that we need to fill in all those cells.

Swing Components:
JTextField[][] textFieldGrid: stores 2D array of JTextField objects. The text fields correspond to a (single) cell in the Sudoku grid that should accept user input.

JButton solveButton, clearButton: Buttons to call the code that solves Sudoku and clears board respectively.

visualize() Method:

Initialize the main JFrame (frame) and JPanel (sudokuPanel) for Sudoku grid

Creates new JTextField objects for each cell in the grid based on an inputed board array.

Puts solveButton and clearButton in a separate panel (buttonPanel) to the right of the frame.

Registers ActionListener implementations for solveButton using 1 of the following strategy and clearButton, which should handle solving Sudoku game or clearing board.

solveSudoku() Method:

Current state of Sudoku grid from textFieldGrid into inputBoard.

Receive and verify the users input (stored as string) into an int array known as inputBoard

It calls solveSudokuRecursive that will recursively try to solve the sudoku puzzle.

search if the puzzle has a solution, update board array with solved puzzle when we get a 0 or no cell is empty and updates GUI by calling an other function show through this statement: -.

Prints the desired result suitable to this case e. g displays an appropriate message in a dialog, if invalid input or no solution is knownINTERNAL_END

solveSudokuRecursive Method -

Solves the sudoku puzzle recursively using a backtracking algorithm

It takes an empty cell (given as board[row][col] == 0) and tries all numbers from 1 to 9. Validity of tried number is checked using isValid().

If it is, it puts that number there and then recursively tries to solve the rest.

If it can find a valid number, then move to next level of recursion and the call itselfDFS(x+1,y); after return stop (since we have solution from this path). If no val operNum is found backtrack by resetting cell x*y.

Checks if placing num at board[row][col] is valid according to Sudoku rules:
No duplicate numbers in the same row, column, or 3x3 sub-grid (box).
Utilizes nested loops to check each condition separately.
clearBoard() Method:

Clears both the board array and the textFieldGrid by setting all values to 0 and clearing text fields.
updateGUI() Method:

Updates the text fields in textFieldGrid to reflect the current state of the board array.
main() Method:

Starts the Swing GUI on the Event Dispatch Thread using SwingUtilities.invokeLater(), ensuring thread safety.
Usage:
To use this Sudoku visualizer:

Compile the Java file.
Run the compiled program.
Input numbers into the grid and click "Solve" to attempt solving the puzzle.
Click "Clear" to reset the puzzle.

This program provides a basic implementation of a Sudoku solver and visualizer using Java and Swing, demonstrating GUI creation, event handling, and basic algorithmic logic for solving Sudoku puzzles.
