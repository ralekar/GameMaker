package view.communication.protocol;

public class GameHostInfo {

    private String saveName, gameName, gamePlayer, gameData;
    private int gameScore, rank, id;

    public GameHostInfo(String saveName, String gameName, String gamePlayer, int id) {
        this.saveName = saveName;
        this.gameName = gameName;
        this.gamePlayer = gamePlayer;
        this.id = id;
    }
    public GameHostInfo(String saveName, String gameName, String gamePlayer, int id,int gameScore) {
        this.saveName = saveName;
        this.gameName = gameName;
        this.gamePlayer = gamePlayer;
        this.id = id;
        this.gameScore=gameScore;
    }
    public GameHostInfo(String saveName, String gameName, String gamePlayer, String gameData) {
        this.saveName = saveName;
        this.gameName = gameName;
        this.gamePlayer = gamePlayer;
        this.gameData = gameData;
    }

    public GameHostInfo(String saveName, String gameName, String gamePlayer, int gameScore, int rank, String gameData) {
        this.saveName = saveName;
        this.gameName = gameName;
        this.gamePlayer = gamePlayer;
        this.gameScore = gameScore;
        this.rank = rank;
        this.gameData = gameData;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGamePlayer() {
        return gamePlayer;
    }

    public void setGamePlayer(String gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public String getSaveName() {
        return saveName;
    }

    public void setSaveName(String saveName) {
        this.saveName = saveName;
    }

    public int getGameScore() {
        return gameScore;
    }

    public void setGameScore(int gameScore) {
        this.gameScore = gameScore;
    }

    public String getGameData() {
        return gameData;
    }

    public void setGameData(String gameData) {
        this.gameData = gameData;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
