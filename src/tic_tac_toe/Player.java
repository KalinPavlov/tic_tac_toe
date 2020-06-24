package tic_tac_toe;

public class Player {
	private String name;
	private CellContent cellContent;
	private PlayerType type;
	
	public Player(String name, CellContent cellContent, PlayerType type) {
		this.name = name;
		this.cellContent = cellContent;
		this.type = type;
	}

	public String getName() {
		return name;
	}
	
	public PlayerType getType() {
		return type;
	}

	public CellContent getCellContent() {
		return cellContent;
	}

	/** Takes the current player and returns the other player. */
	public Player getNextPlayer(Player player1, Player player2) {
		return this == player1 ? player2 : player1;
	}
}
