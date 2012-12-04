package db;

public class HostedGameBaseRecord implements java.io.Serializable {

	private static final long serialVersionUID = -5235915093399909479L;
	private int id;
	private String hostName;
    private String gameBaseName;
    private String saveGameBaseName;

    public HostedGameBaseRecord(String hostName, String gameBaseName, String saveGameBaseName) {
        this.hostName = hostName;
        this.gameBaseName = gameBaseName;
        this.saveGameBaseName = saveGameBaseName;
        this.setId(0);
    }

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getGameBaseName() {
		return gameBaseName;
	}

	public void setGameBaseName(String gameBaseName) {
		this.gameBaseName = gameBaseName;
	}

	public String getSaveGameBaseName() {
		return saveGameBaseName;
	}

	public void setSaveGameBaseName(String saveGameBaseName) {
		this.saveGameBaseName = saveGameBaseName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


}

