package view.companels;

import javax.swing.JFrame;

/**
 *
 * @author han
 */
public class TestPopupPanels {

    public static void main(String[] args) {
        JFrame f = new JFrame();

        GameBaseLoadPanel p1 = new GameBaseLoadPanel(f.getRootPane());
        p1.readGameDataFromRemoteList();

        GameBaseSavePanel p2 = new GameBaseSavePanel(f.getRootPane());
        p2.saveGameToRemoteServer("<game></game>");
        
      }
}
