package view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import facade.Facade;

import utility.ClockDisplay;
import utility.Helper;
import utility.Score;

public class ButtonPanel {

	private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
			.getLogger(ButtonPanel.class);
	private GameMakerView makerView;
	private JPanel buttonPanel;
	private JLabel userName;

	public ButtonPanel(GameMakerView gameMakerView) {
		
		this.makerView = gameMakerView;
		buttonPanel = new JPanel();

		JButton newGame = new JButton("Clear");
		newGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ClockDisplay.getInstance().setEnabled(true);
				LOG.trace("resetting the view");
				//Graphics g = null;
				makerView.reset();
				//ClockDisplay.getInstance().draw(g);
				
			}
		});

		JButton start = new JButton("Start");
		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Facade facade = Helper.getsharedHelper().getFacade();
				GamePanel gamePanel = Helper.getsharedHelper().getGamePanel();
				facade.startGame();
				ClockDisplay.getInstance().setEnabled(true);
				gamePanel.requestFocusInWindow();
			}
		});

		JButton stop = new JButton("Stop");
		stop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Facade facade = Helper.getsharedHelper().getFacade();
				facade.stopGame();
				ClockDisplay.getInstance().setEnabled(false);
				GameMakerView gameMakerView = Helper.getsharedHelper().getGameMakerView();
				if (gameMakerView.isShouldDisplayScore()) {
					JOptionPane.showMessageDialog(makerView.getBaseFrame(),
							"Your Score is " + Score.getInstance().getScore());
				}
			}
		});

		userName = new JLabel();

		buttonPanel.add(start);
		buttonPanel.add(stop);
		buttonPanel.add(newGame);
		buttonPanel.add(userName);
	}

	public JPanel getPanel() {
		return buttonPanel;
	}

	public JLabel getUserName() {
		return userName;
	}

	public void setUserName(JLabel userName) {
		this.userName = userName;
	}

}
