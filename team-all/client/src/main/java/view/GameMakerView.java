package view;

import facade.Facade;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


import utility.*;
import view.imagePanel.ImagePanel;
import view.PropertyPanel;
import view.imagePanel.ImageActionListener;

public class GameMakerView {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(GameMakerView.class);
    private JFrame baseFrame;					//The base frame where all other panels are placed
    private JLayeredPane layeredPane;			//Will be used for showing pop-ups
    private JPanel leftPanel;					//The panel with images
    private GamePanel gamePanel; 				//The middle panel where the game objects are rendered.
    private JPanel rightPanel;					//This contains Button, property and event action panel
    private ButtonPanel buttonPanel;			//The panel with buttons at the top right corner		
    private PropertyPanel propertyPanel;		//Panel where width, height etc are changed.
    private ActionEventPanel actionEventPanel;	//Can add action and events to game objects from this panel	
    private String userName = "";
    private Facade facade;
    private JComboBox layerBox;
    private boolean shouldDisplayScore = false;
    private InfoPanel infoPanel;
    private JPanel basePanel;

    public GameMakerView(int frameWidth, int frameHeight) {

        baseFrame = Helper.getsharedHelper().createBaseFrame(frameWidth, frameHeight);
        baseFrame.setJMenuBar(new MenuBarPanel().getMenuBar());


        ImageActionListener imageActionListener = new ImageActionListener();
        ImagePanel extendedImagePanel;
        try {
            extendedImagePanel = new ImagePanel(imageActionListener);
        } catch (Exception ex) {
            LOG.error(ex);
            return;
        }
        leftPanel = extendedImagePanel.getImagePanel();
        leftPanel.setPreferredSize(new Dimension(Constants.IMAGE_PANEL_WIDTH, Constants.BOARD_HEIGHT));

        gamePanel = new GamePanel(Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT);
        facade = new Facade(gamePanel);
        Helper.getsharedHelper().setFacade(facade);
        Helper.getsharedHelper().setGamePanel(gamePanel);

        buttonPanel = new ButtonPanel(this);
        propertyPanel = new PropertyPanel();
        actionEventPanel = new ActionEventPanel(this);

        rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.add(buttonPanel.getPanel());
        rightPanel.add(propertyPanel);
        rightPanel.add(actionEventPanel.getPanel());
        rightPanel.setPreferredSize(new Dimension(Constants.PROPERTY_PANEL_WIDTH, Constants.BOARD_HEIGHT));

        layeredPane = new JLayeredPane();
        baseFrame.getContentPane().add(layeredPane);

        basePanel = new JPanel();
        basePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        basePanel.add(leftPanel);
        basePanel.add(gamePanel);
        basePanel.add(rightPanel);

        basePanel.setBounds(0, 0, Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
        layeredPane.add(basePanel, new Integer(0));


        layerBox = new JComboBox(Layers.getInstance().getLayers().toArray());
        layerBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String layer = ((String) ((JComboBox) e.getSource()).getSelectedItem());
                gamePanel.setCurrentLayer(layer);
                gamePanel.repaint();
            }
        });

    }

    public void updateProperties() {
        propertyPanel.updateProperties();
        actionEventPanel.updateActionEvents();
    }

    public void clearAll() {
        propertyPanel.clearAll();
    }

    public void reset() {
        ClockDisplay.getInstance().reset();
        SpriteList.getInstance().getSpriteList().clear();
        gamePanel.reset();
        facade.reset();
        actionEventPanel.reset();
        updateProperties();
    }

    public void showInfoPanel(String message, int posX, int posY) {
    	if(!Helper.getsharedHelper().isShowPopups()){
    		return;
    	}
        infoPanel = new InfoPanel(message);
        infoPanel.setOpaque(false);
        Rectangle bounds = infoPanel.getBounds();

        posX -= bounds.width;
        posY -= bounds.height;

        bounds.setLocation(posX, posY);
        infoPanel.setBounds(bounds);

        layeredPane.add(infoPanel, new Integer(1));
        baseFrame.getContentPane().validate();
    }

    public void removeInfoPanel() {
        if (infoPanel != null) {
            infoPanel.setBounds(0, 0, 0, 0);
            infoPanel.removeAll();
            baseFrame.getContentPane().validate();
        }
    }

    /**
     * **************** GETTERS & SETTERS *********************************
     */
    public JFrame getBaseFrame() {
        return this.baseFrame;
    }

    public JPanel getGameMakerPanel() {
        return rightPanel;
    }

    public void setGameMakerPanel(JPanel gameMakerPanel) {
        this.rightPanel = gameMakerPanel;
    }

    public JComboBox getLayerBox() {
        return layerBox;
    }

    public ActionEventPanel getActionEventPanel() {
        return actionEventPanel;
    }

    public ButtonPanel getButtonPanel() {
        return buttonPanel;
    }

    public void setButtonPanel(ButtonPanel buttonPanel) {
        this.buttonPanel = buttonPanel;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void showGameMakerWindow(boolean visibility) {
        this.baseFrame.setVisible(true);
    }

    public boolean isShouldDisplayScore() {
        return shouldDisplayScore;
    }

    public void setShouldDisplayScore(boolean shouldDisplayScore) {
        this.shouldDisplayScore = shouldDisplayScore;
    }

    public DefaultListModel getSpriteListIndividualModel() {
        return actionEventPanel.getSpriteListIndividualModel();
    }

    public DefaultListModel getSpriteListGroupModel() {
        return actionEventPanel.getSpriteListGroupModel();
    }

    public PropertyPanel getPropertyPanel() {
        return propertyPanel;
    }

    public void setPropertyPanel(PropertyPanel propertyPanel) {
        this.propertyPanel = propertyPanel;
    }

    public JPanel getRightPanel() {
        return rightPanel;
    }
}
