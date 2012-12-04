package view.companels;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import model.GameProgressSaveInfo;
import utility.Constants;
import view.communication.ClientHandler;

public class GameBaseLoadPanel {

    private JComponent rootComp;
    private final String host = Constants.HOST;
    private final String path = Constants.PATH;
    private final String urlListAllGameBases = "/listAllGameBases";
    private final String urlLoadGameBase = "/loadGameBase";

    public GameBaseLoadPanel(JComponent rootComp) {
        this.rootComp = rootComp;
    }

    public String readGameDataFromRemoteList() {
        String[] gameNames = null;
        try {
            gameNames = ClientHandler.listAllGameBases(host, path + urlListAllGameBases);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootComp, ex.toString());
            return null;
        }

        String chosen = (String) JOptionPane.showInputDialog(
                rootComp,
                "Select Game Base from " + host,
                "Load Game Base",
                JOptionPane.PLAIN_MESSAGE,
                null, gameNames,
                null);

        if (chosen == null) {
            return null;
        }

        String gameData;
        try {
            gameData = ClientHandler.loadGameBase(chosen, host, path + urlLoadGameBase);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootComp, ex.toString());
            return null;
        }

        GameProgressSaveInfo.getInstance().setLoadedGameName(chosen);

        return gameData;
    }
}
