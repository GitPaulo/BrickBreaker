package game;

public class Player {
	private final String gamertag;
	private final boolean saveStats;
	private final GameDifficulty difficulty;
	private int score;

	public Player(String gamertag, boolean saveStats, GameDifficulty difficulty) {
		this.gamertag = gamertag;
		this.score = 0;
		this.saveStats = saveStats;
		this.difficulty = difficulty;
	}

	public String getGamertag() {
		return gamertag;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isSaveStats() {
		return saveStats;
	}

	public GameDifficulty getDifficulty() {
		return difficulty;
	}
}
