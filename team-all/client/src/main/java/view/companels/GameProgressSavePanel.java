package view.companels;

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

public class GameProgressSavePanel {

    private JComponent rootComp;
    private final String host = Constants.HOST;
    private final String path = Constants.PATH;
    private final String urlSaveGameProgress = "/saveGameProgress";

    public GameProgressSavePanel(JComponent rootComp) {
        this.rootComp = rootComp;
    }

    public void saveGameToRemoteServer(String gameData) {
        JTextField saveNameField = new JTextField();
        if (Player.getInstance().getUsername() != null) {
            Object[] message = new Object[]{"Save Name:", saveNameField};

            int r = JOptionPane.showConfirmDialog(rootComp, message, "Save Your Game Progress", JOptionPane.OK_CANCEL_OPTION);
            if (r != JOptionPane.OK_OPTION) {
                return;
            }

            String saveName = saveNameField.getText();
            // String playerName = playerNamelField.getText();
            String playerName = Player.getInstance().getUsername();

            //Saving the current score into the GameProgressSaveInfo instance.
            GameProgressSaveInfo.getInstance().setScore(Score.getInstance().getScore());
            GameSaveInfo gameSaveInfo = new GameSaveInfo(saveName, GameProgressSaveInfo.getInstance().getLoadedGameName(), playerName, GameProgressSaveInfo.getInstance().getScore(), 0, gameData);

            boolean saveOK;
            try {
                saveOK = ClientHandler.saveGamePlay(gameSaveInfo, host, path + urlSaveGameProgress);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(rootComp, ex.toString());
                return;
            }

            if (saveOK) {
                JOptionPane.showMessageDialog(rootComp, "Game saved to " + host + " successfully");
            } else {
                JOptionPane.showMessageDialog(rootComp, "Error, Game is not saved please load game before saving");
            }
        } else {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "Please login");
        }
    }
}
