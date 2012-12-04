package view.companels;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import utility.Constants;
import view.communication.ClientHandler;

public class TopScoresPanel {

    private JComponent rootComp;
    private final String host = Constants.HOST;
    private final String path = Constants.PATH;
    private final String urlListAllGameBases = "/listAllGameBases";
    private final String urlListTopScores = "/listTopScores";

    public TopScoresPanel(JComponent rootComp) {
        this.rootComp = rootComp;
    }

    public void readGameScoresFromRemoteList() {
        String[] gameNames;
        try {
            gameNames = ClientHandler.listAllGameBases(host, path + urlListAllGameBases);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootComp, ex.toString());
            return;
        }

        String chosen = (String) JOptionPane.showInputDialog(
                rootComp,
                "Select Game from " + host,
                "Select a Game First",
                JOptionPane.PLAIN_MESSAGE,
                null, gameNames,
                null);

        if (chosen == null) {
            return;
        }

        //   GameHostInfo[] gameSaves = ClientHandler.listTopScores(chosen, host, path + urlListTopScores, exceptions);
        String[] gameSaves = {"aaaaaa", "bbbbbb"};
        

        if (gameSaves.length == 0) {
            JOptionPane.showMessageDialog(rootComp, "There is no records for this game yet");
            return;
        }

        String[] columnNames = {"Rank", "Player", "Score"};

        Object[][] data = new Object[gameSaves.length][];
        for (int i = 0; i < gameSaves.length; i++) {
            // data[i] = new Object[]{gameSaves[i].getRank(), gameSaves[i].getGamePlayer(), gameSaves[i].getGameScore()};
            data[i] = new Object[]{gameSaves[i]};
        }

        JTable table = new JTable(data, columnNames);
        table.setEnabled(false);

        JScrollPane scrollPane = new JScrollPane(table);

        JOptionPane.showMessageDialog(rootComp, scrollPane, "Top Scores for " + chosen, JOptionPane.PLAIN_MESSAGE);
    }
}
