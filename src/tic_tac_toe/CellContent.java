package tic_tac_toe;

public enum CellContent {
	EMPTY(' '), PLAYER1('X'), PLAYER2('O');

	private char symbol;

	private CellContent(char symbol) {
		this.symbol = symbol;
	}

	public char getSymbol() {
		return symbol;
	}
}
