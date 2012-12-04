package model;

public class GameProgressSaveInfo {
	private static GameProgressSaveInfo sharedGameInfo = null;
	private String loadedGameName;
	private int score;

	public static GameProgressSaveInfo getInstance() {
		if (sharedGameInfo == null) {
			sharedGameInfo = new GameProgressSaveInfo();
		}
		return sharedGameInfo;
	}

	protected GameProgressSaveInfo() {
		this.loadedGameName = new String();
		this.score = 0;
	}

	public String getLoadedGameName() {
		return loadedGameName;
	}

	public void setLoadedGameName(String loadedGameName) {
		this.loadedGameName = loadedGameName;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
