package view;

import game.engine.slick2d.player.GameEnginePanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import utility.Constants;
import utility.Helper;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class OptionsFrame implements ActionListener {

    private static final org.apache.log4j.Logger LOG =
            org.apache.log4j.Logger.getLogger(OptionsFrame.class);
    private JFrame optionFrame;
    private static OptionsFrame optionsFrame;
    private JButton makerButton, playerButton;
    private GameEnginePanel te;
 

    private OptionsFrame() {
        optionFrame = new JFrame();
        makerButton = new JButton("Make Game");
        makerButton.addActionListener(this);
        playerButton = new JButton("Play Game");
        playerButton.addActionListener(this);

        optionFrame.setLayout(new MigLayout("center,center"));
        optionFrame.add(makerButton, "wmin 100, hmin 100");
        optionFrame.add(playerButton, "wmin 100, hmin 100");
        
        optionFrame.setSize(300, 300);
        optionFrame.setFocusable(true);
        optionFrame.setLocationRelativeTo(null);
        optionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        optionFrame.setTitle("Option Page");
        optionFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e == null){
            return;
        }
        if (e.getSource() == makerButton) {
        	Constants.isGameMaker=true;
                Constants.isGamePlayer=false;
            //GameMakerView.getInstance();
            //GameMakerView.getInstance().getBaseFrame().setVisible(true);
             GameMakerView gameMakerView = new GameMakerView(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
             Helper.getsharedHelper().setGameMakerView(gameMakerView);
             gameMakerView.getBaseFrame().setVisible(true);

            optionFrame.setVisible(false);
            Helper.getsharedHelper().setPlayerMode(false);
        }

        if (e.getSource() == playerButton) {
        	Constants.isGamePlayer = true;
                Constants.isGameMaker = false;
        	GamePlayerView gamePlayerView = new GamePlayerView(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
        	Helper.getsharedHelper().setGamePlayerView(gamePlayerView);
        	Helper.getsharedHelper().setPlayerMode(true);
            optionFrame.setVisible(false);
        }        

    }

    public static OptionsFrame getOptionFrame() {
        return ((optionsFrame == null)? (optionsFrame = new OptionsFrame()):optionsFrame);
    }

    public void setVisible(boolean visibility){
        optionFrame.setVisible(visibility);
    }
//    public void setOptionFrame(JFrame optionFrame) {
//        this.optionFrame = optionFrame;
//    }
}
