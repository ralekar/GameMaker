package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import chat.StatusReceiver;
import game.engine.slick2d.player.GameEnginePanel;
import java.awt.event.WindowEvent;


import org.apache.log4j.Logger;

import model.Player;
import multiplayer.SessionFactory;

import utility.Constants;
import utility.Helper;
import view.communication.ClientHandler;

public class GamePlayerView {
	private static final Logger LOG = Logger.getLogger(GamePlayerView.class);

	private static JFrame baseFrame;
	private GameEnginePanel gameenginePanel;
	private final String host = Constants.HOST;
	private final String path = Constants.PATH;
	private final String urldeleteHostedGameBaseRecord = "/deleteHostedGameBaseRecord";

	// private Facade facade;

	public GamePlayerView(int frameWidth, int frameHeight) {
		// Create a base frame to hold all the other panels
		baseFrame = Helper.getsharedHelper().createBaseFrame(frameWidth,
				frameHeight);
		baseFrame.setJMenuBar(new MenuBarPanel().getMenuBar());

		// Create a game engine panel. This is where all the game objects are
		// rendered and the game is played.
		gameenginePanel = new GameEnginePanel();

		// Create a button panel where the controls to play/pause etc the game
		// and chat window is present.
		PlayerButtonPanel playerButtonPanel = new PlayerButtonPanel();
		baseFrame.add(playerButtonPanel.getPlayerButtonPanel());
		baseFrame.add(gameenginePanel);
		baseFrame.setVisible(true);

		baseFrame.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(WindowEvent winEvt) {
				// Call for deleting the hosted games.
				if (Constants.isMultiplayer&& Constants.isHosted) {
					String playerName = Player.getInstance().getUsername();

					try {
						ClientHandler.deleteHostedGameBase(playerName, host,
								path + urldeleteHostedGameBaseRecord);
						Constants.isHosted=false;
						SessionFactory.getInstanceOf().closeSession();					
						JFrame frame = new JFrame();
						JOptionPane.showMessageDialog(frame,
								"Hosted game is exited.");
					} catch (Exception e1) {
						LOG.error(e1);
					}
				}

			}
		});

		new StatusReceiver();
	}

	/*
	 * Getter and Setter methods
	 */
	public GameEnginePanel getGameEnginePanel() {
		return gameenginePanel;
	}

	public void setGameEnginePanel(GameEnginePanel gamePanel) {
		this.gameenginePanel = gamePanel;
	}

	public static JFrame getBaseFrame() {
		return baseFrame;
	}

	public void setBaseFrame(JFrame baseFrame) {
		this.baseFrame = baseFrame;
	}

}
