package view.companels;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.GameProgressSaveInfo;
import model.Player;
import utility.Constants;
import utility.Score;
import view.communication.ClientHandler;
import view.communication.protocol.GameSaveInfo;

public class GameProgressLoadPanel {

    private JComponent rootComp;
    private final String host = Constants.HOST;
    private final String path = Constants.PATH;
    private final String urlListAllGamePlays = "/listAllGamePlays";
    private final String urlLoadGameProgress = "/loadGamePlay";
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(GameProgressLoadPanel.class);

    public GameProgressLoadPanel(JComponent rootComp) {
        this.rootComp = rootComp;
    }

    public String readGameDataFromRemoteList(String gamename[]) {
        JTextField playerNameField = new JTextField();
        Object[] message = new Object[]{"Player Name:", playerNameField};
        LOG.debug(Player.getInstance().getUsername());
        if (Player.getInstance().getUsername() != null) {

            /*  int r = JOptionPane.showConfirmDialog(rootComp, message, "Load Saved In-Progress Games", JOptionPane.OK_CANCEL_OPTION);
             if (r != JOptionPane.OK_OPTION) {
             return null;
             }
	
             String playerName = playerNameField.getText();
             */
            String playerName = Player.getInstance().getUsername();

            GameSaveInfo[] gameSaves;
            try {
                gameSaves = ClientHandler.listAllGamePlays(playerName, host, path + urlListAllGamePlays);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(rootComp, ex.toString());
                return null;
            }

            String[] saveDisplay = new String[gameSaves.length];
            for (int i = 0; i < gameSaves.length; i++) {
                saveDisplay[i] = gameSaves[i].getSaveName() + " [" + gameSaves[i].getGameName() + "]";
            }

            JComboBox saveList = new JComboBox(saveDisplay);

            int r = JOptionPane.showConfirmDialog(rootComp, new Object[]{"Select Previously Saved Game by " + playerName, saveList}, "Load Game Play", JOptionPane.OK_CANCEL_OPTION);

            if (r != JOptionPane.OK_OPTION) {
                return null;
            }

            int saveIndex = saveList.getSelectedIndex();

            String gameData = null;
            try {
                gamename[0]= gameSaves[saveIndex].getGameName();
                gameData = ClientHandler.loadGamePlay(gameSaves[saveIndex].getId(), host, path + urlLoadGameProgress);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(rootComp, ex.toString());
                return null;
            }

            GameProgressSaveInfo.getInstance().setLoadedGameName(gameSaves[saveIndex].getGameName());
            GameProgressSaveInfo.getInstance().setScore((gameSaves[saveIndex].getGameScore()));
            //Loading the previously saved score into the Score instance.
            Score.getInstance().setScore(GameProgressSaveInfo.getInstance().getScore());

            return gameData;
        }
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame, "Please login");
        return null;
    }
}
