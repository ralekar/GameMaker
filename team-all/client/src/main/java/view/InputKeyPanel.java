package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utility.Constants;
import utility.Helper;

public class InputKeyPanel implements MouseListener,KeyListener, ActionListener{

	private static final org.apache.log4j.Logger LOG = 
			org.apache.log4j.Logger.getLogger(InputKeyPanel.class);	
	private JPanel inputPanel;
	private JComboBox comboBox;
	
	private JLabel infoLbl;
	
	public JComboBox getComboBox() {
		return comboBox;
	}

	public void setComboBox(JComboBox comboBox) {
		this.comboBox = comboBox;
	}

	public InputKeyPanel(JPanel inputPanel){
		LOG.info("Input key panel initialized");
		this.inputPanel=inputPanel;
		inputPanel.setFocusable(true);
		comboBox = new JComboBox();
		infoLbl = new JLabel();
		infoLbl.setText("Press here and type any key");
		infoLbl.setForeground(Color.BLACK);
		inputPanel.add(infoLbl);
		comboBox.addItem("Player 1");
		comboBox.addItem("Player 2");
		this.inputPanel.add(comboBox);
		comboBox.setVisible(Constants.isMultiplayer);
		comboBox.addActionListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		inputPanel.requestFocus(true);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
	LOG.debug(KeyEvent.getKeyText(e.getKeyCode()));
		String charTyped = KeyEvent.getKeyText(e.getKeyCode());
		infoLbl.setText("You have selected :"+charTyped);
		Helper.getsharedHelper().setCurrentKeyCode(e.getKeyCode());
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {
	
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
		
	}
	
	public JPanel getInputPanel() {
		return inputPanel;
	}

	public void setInputPanel(JPanel inputPanel) {
		this.inputPanel = inputPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals("Player 1"))
		{
			// Do something
		}
		if (e.getSource().equals("Player 2"))
		{
			
		}
	}

}
