package tic_tac_toe;

import java.util.Scanner;

public class TicTacToe {
	private static final String CPU_NAME = "CPU";

	// Main entities of the game.
	private Board board;
	private Player player1;
	private Player player2;

	// These fields represent the state of the game.
	private GameState currentState;
	private Player currentPlayer;
	private GameMode gameMode;
	private int currentRow;
	private int currentCol;

	public static void main(String[] args) {
		System.out.println("Welcome to Tic Tac Toe");
		TicTacToe ttt = new TicTacToe();
		ttt.start();
	}

	private void start() {
		System.out.print("Press Enter to continue or x to quit: ");

		try (Scanner scanner = new Scanner(System.in)) {
			String input = scanner.nextLine();

			if (input.equals("")) {
				selectGameMode(scanner);
				initPlayers(scanner);
				board = new Board();
				initGame();
				System.out.println("Let's play the game:");
				play(scanner);
			} else if (input.toLowerCase().equals("x")) {
				System.out.println("Game ended.");
			}
		}
	}

	/** Allows the player to play vs. other player or vs. CPU */
	private void selectGameMode(Scanner scanner) {
		boolean validInput = false;
		while (!validInput) {
			System.out.println("Select game mode:");
			System.out.println("1. Player VS. Player");
			System.out.println("2. Player VS. CPU");
			int mode = scanner.nextInt();
			scanner.nextLine();
			if (GameMode.isValidGameMode(mode)) {
				validInput = true;
				gameMode = GameMode.getGameModeOf(mode);
			} else {
				System.out.println("Invalid game mode! Try again...");
			}
		}
	}

	private void initPlayers(Scanner scanner) {
		switch (gameMode) {
		case PVSP:
			System.out.print("Enter Player 1 name: ");
			String player1Name = scanner.nextLine();
			player1 = new Player(player1Name, CellContent.PLAYER1, PlayerType.HUMAN);

			System.out.print("Enter Player 2 name: ");
			String player2Name = scanner.nextLine();
			player2 = new Player(player2Name, CellContent.PLAYER2, PlayerType.HUMAN);
			break;
		case PVSCPU:
			System.out.print("Enter Player name: ");
			String playerName = scanner.nextLine();
			player1 = new Player(playerName, CellContent.PLAYER1, PlayerType.HUMAN);
			player2 = new Player(CPU_NAME, CellContent.PLAYER2, PlayerType.CPU);
			break;
		}
	}

	private void initGame() {
		currentState = GameState.PLAYING;
		currentPlayer = player1;

	}

	public void play(Scanner scanner) {
		board.printBoard();
		while (currentState == GameState.PLAYING) {
			move(scanner);
			board.printBoard();
			if (board.hasWon(currentPlayer, currentRow, currentCol)) {
				System.out.println(currentPlayer.getName() + " wins the game.");
				currentState = GameState.FINISHED;
			} else if (board.isDraw()) {
				System.out.println("The game is draw.");
				currentState = GameState.FINISHED;
			} else {
				currentPlayer = currentPlayer.getNextPlayer(player1, player2);
			}
		}
	}

	/** Plays the next move */
	public void playerMove(Scanner scanner) {
		boolean validInput = false;
		while (!validInput) {
			System.out.println(currentPlayer.getName() + "'s turn.");
			System.out.print("Enter row (1-3): ");
			int row = scanner.nextInt() - 1;
			scanner.nextLine();
			System.out.print("Enter column (1-3): ");
			int col = scanner.nextInt() - 1;
			scanner.nextLine();
			System.out.println("Row: " + row + " Col: " + col);
			if (board.isValidBoardCell(row, col)) {
				currentRow = row;
				currentCol = col;
				if (board.isCellAvailabe(currentRow, currentCol)) {
					validInput = true;
					board.setCell(currentPlayer, currentRow, currentCol);
				} else {
					System.out.println("This selection is not available. Try nother field...");
				}
			} else {
				System.out.println("Invalid board position! Try again...");
			}
		}
	}

	public void cpuMove() {
		System.out.println(currentPlayer.getName() + "'s turn.");
		int[] move = board.getCPUMove();
		currentRow = move[0];
		currentCol = move[1];
		board.setCell(currentPlayer, currentRow, currentCol);
	}

	public void move(Scanner scanner) {
		switch (currentPlayer.getType()) {
		case HUMAN:
			playerMove(scanner);
			break;
		case CPU:
			cpuMove();
			break;
		}
	}
}
