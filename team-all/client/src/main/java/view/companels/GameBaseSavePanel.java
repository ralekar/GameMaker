package view.companels;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Player;
import utility.Constants;
import view.communication.ClientHandler;

public class GameBaseSavePanel {

    private JComponent rootComp;
    private final String host = Constants.HOST;
    private final String path = Constants.PATH;
    private final String urlSaveGameBase = "/saveGameBase";

    public GameBaseSavePanel(JComponent rootComp) {
        this.rootComp = rootComp;
    }

    public void saveGameToRemoteServer(String gameData) {
        JTextField gameNameField = new JTextField();
        if (Player.getInstance().getUsername() != null) {
            Object[] message = new Object[]{"Game Base Name:", gameNameField};

            int r = JOptionPane.showConfirmDialog(rootComp, message, "Save Your Game Base", JOptionPane.OK_CANCEL_OPTION);
            if (r != JOptionPane.OK_OPTION) {
                return;
            }

            String gameName = gameNameField.getText();
            String authorName = Player.getInstance().getUsername();

            boolean saveOK;
            try {
                saveOK = ClientHandler.saveGameBase(gameName, authorName, gameData, Constants.isMultiplayer, host, path + urlSaveGameBase);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(rootComp, ex.toString());
                saveOK = false;
            }

            if (saveOK) {
                JOptionPane.showMessageDialog(rootComp, "Game saved to " + host + " successfully");
            } else {
                JOptionPane.showMessageDialog(rootComp, "Error, Game is not saved");
            }
        } else {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "Please login");
        }
    }

    public boolean saveGameToRemoteServerWithoutUI(String gameData, String gameName, String authorName, Boolean isMultiPlayer) throws Exception {
        return ClientHandler.saveGameBase(gameName, authorName, gameData, isMultiPlayer, host, path + urlSaveGameBase);
    }
}
