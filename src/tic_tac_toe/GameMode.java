package tic_tac_toe;

public enum GameMode {
	PVSP(1), PVSCPU(2);

	private int mode;

	private GameMode(int mode) {
		this.mode = mode;
	}

	public int getMode() {
		return mode;
	}

	public static GameMode getGameModeOf(int mode) {
		switch (mode) {
		case 1:
			return PVSP;
		case 2:
			return PVSCPU;
		}

		return null;
	}

	/** Checks if the selected game mode is valid */
	public static boolean isValidGameMode(int mode) {
		return mode == GameMode.PVSP.getMode() || mode == GameMode.PVSCPU.getMode() ? true : false;
	}
}
