package tic_tac_toe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {

	// The game board is 3x3.
	private static final int ROWS = 3;
	private static final int COLS = 3;

	private char[][] board;

	public Board() {
		board = new char[ROWS][COLS];
		initBoard();
	}

	private void initBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = CellContent.EMPTY.getSymbol();
			}
		}
	}

	private char[][] getDisplayBoard(char[][] board) {
		return new char[][] { { board[0][0], '|', board[0][1], '|', board[0][2] }, { '-', '+', '-', '+', '-' },
				{ board[1][0], '|', board[1][1], '|', board[1][2] }, { '-', '+', '-', '+', '-' },
				{ board[2][0], '|', board[2][1], '|', board[2][2] } };
	}

	public void printBoard() {
		char[][] displayBoard = getDisplayBoard(board);
		for (char[] row : displayBoard) {
			for (char ch : row) {
				System.out.print(ch);
			}
			System.out.println();
		}
	}

	/** Checks if the selected position is valid. */
	public boolean isValidBoardCell(int row, int col) {
		if (row >= 0 && row < ROWS && col >= 0 && col < COLS) {
			return true;
		}

		return false;
	}

	/** Checks if the current board cell is not already filled */
	public boolean isCellAvailabe(int row, int col) {
		return board[row][col] == CellContent.EMPTY.getSymbol() ? true : false;
	}

	public void setCell(Player player, int row, int col) {
		board[row][col] = player.getCellContent().getSymbol();
	}

	/** Checks if any field is still EMPTY. */
	public boolean isDraw() {
		for (char[] row : board) {
			for (char ch : row) {
				if (ch == CellContent.EMPTY.getSymbol()) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * Checks if the current player has won the game in horizontal, vertical or
	 * diagonal
	 */
	public boolean hasWon(Player player, int currentRow, int currentCol) {
		char symbol = player.getCellContent().getSymbol();
		return (board[currentRow][0] == symbol // checks the row
				&& board[currentRow][1] == symbol && board[currentRow][2] == symbol)
				|| (board[0][currentCol] == symbol // checks the column
						&& board[1][currentCol] == symbol && board[2][currentCol] == symbol)
				|| (currentRow == currentCol // checks left diagonal
						&& board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol)
				|| (currentRow + currentCol == 2 // checks right diagonal
						&& board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol);
	}
	
	private List<int[]> getAvailableCells() {
		List<int[]> availableCells = new ArrayList<>(); 
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				if (board[i][j] == CellContent.EMPTY.getSymbol()) {
					availableCells.add(new int[] {i, j});
				}
			}
		}
		
		return availableCells;
	}
	
	public int[] getCPUMove() {
		Random random = new Random();
		List<int[]> availableCells = getAvailableCells();
		int randIndex = random.nextInt(availableCells.size());
		return availableCells.get(randIndex);
	}
}
