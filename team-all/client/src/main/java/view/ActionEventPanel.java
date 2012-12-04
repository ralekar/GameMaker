package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.SpriteModel;

import org.apache.commons.lang3.StringUtils;

import utility.Constants;
import utility.Helper;
import utility.SpriteList;
import utility.enums.playerModes;
import action.ActionStartOver;
import action.GameAction;
import controller.GameController;
import eventlistener.EventListener;
import eventlistener.KeyPressedEventListener;
import eventlistener.OutOfBoundaryEventListener;
import facade.Facade;
import java.util.Collection;


public class ActionEventPanel {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ActionEventPanel.class);
    private JPanel inputPanel;
    private InputKeyPanel inputKeyPanel;
    private JList spriteList;
    private JScrollPane spriteListScrollPane;
    private JComboBox actionBox;
    private JComboBox eventBox;
    private JList eventActionList;
    private JButton addEventActionButton;
    private JButton removeEventActionButton;
    private JComboBox collisionSpriteBox;
    private DefaultListModel eventActionListModel;
    private DefaultComboBoxModel collisionSpriteModel;
    private JTextField scoreModificationField;
    private String[] sample = {"Sprite List"};
    private String[] actionTypes = {"Change Visibility", "Move", "Create Bomb", "Reposition", "Remove", "Play Sound", "Bounce", "Change Speed", "Random Movement", "Rotate Clockwise", "Rotate Anticlockwise", "Change Image", "Start Over", "Game Win", "Game Lose", "Increase Score"};
    private GamePanel gamePanel; // Right view in game maker
    private String[] eventTypes = {"Collision", "Input", "New Frame", "Time Change", "Out of Boundary"};
    private double startX, startY;
    private Facade facade;
    private GameMakerView design;
    private JPanel actionEventPanel;
    private DefaultListModel spriteListIndividualModel;
    private DefaultListModel spriteListGroupModel;
    private JLabel spriteName;
    private JLabel eventActionName;

    public ActionEventPanel(GameMakerView designArg) {

        this.design = designArg;
        this.facade = Helper.getsharedHelper().getFacade();

        collisionSpriteModel = new DefaultComboBoxModel();
        collisionSpriteModel.addElement("SpriteList");
        eventActionListModel = new DefaultListModel();
        spriteListIndividualModel = new DefaultListModel();
        spriteListGroupModel = new DefaultListModel();
        actionEventPanel = new JPanel();
        actionEventPanel.setLayout(new GridBagLayout());
        inputPanel = new JPanel();
        inputKeyPanel = new InputKeyPanel(inputPanel);
        // inputPanel.setSize(80,200);
        inputPanel.setVisible(false);
        inputPanel.addMouseListener(inputKeyPanel);
        inputPanel.addKeyListener(inputKeyPanel);

        // spriteListTypeBox=new JComboBox(spriteListTypes);
        spriteList = new JList();
        spriteList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        spriteList.setListData(sample);
        spriteListScrollPane = new JScrollPane(spriteList);
        spriteListScrollPane.setSize(100, 100);
        spriteName = new JLabel("Sprite Name");
        eventActionName = new JLabel("Event Action List");

        scoreModificationField = new JTextField(10);
        scoreModificationField.setVisible(false);

        // groupSpriteList=new JList(sampleGroupList);
        // groupSpriteList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        // groupSpriteList.setEnabled(false);
        actionBox = new JComboBox(actionTypes);
        actionBox.setToolTipText("Select action");
        actionBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String actionName = (String) actionBox.getSelectedItem();
                if (actionName.equalsIgnoreCase("Play Sound")) {
                    URL jar = this.getClass().getClassLoader().getResource("resource.jar");
                    List<String> list = new ArrayList<String>();

                    ZipInputStream zip;
                    try {
                        zip = new ZipInputStream(jar.openStream());
                        ZipEntry ze = null;

                        while ((ze = zip.getNextEntry()) != null) {
                            String entryName = ze.getName();
                            if (entryName.endsWith(".au") || entryName.endsWith(".wav")) {
                                list.add(entryName);
                            }
                        }
                    } catch (IOException e1) {
                        LOG.error(e1);
                    }

                    String filename = "";
                    filename = (String) JOptionPane.showInputDialog(null, "Choose Sound File", "Sound Selection", JOptionPane.PLAIN_MESSAGE, null, list.toArray(), null);

                    try {
                        SpriteModel selectedSpriteModel = SpriteList.getInstance().getSelectedSpriteModel();
                        selectedSpriteModel.setSoundFile(filename);
                    } catch (NullPointerException e) {
                        LOG.info("File not selected");
                    }
                }

                if (actionName.equalsIgnoreCase("Increase Score")) {
                    scoreModificationField.setVisible(true);
                } else {
                    scoreModificationField.setVisible(false);
                }

                if (actionName.equalsIgnoreCase("Start Over")) {
                    String[] combo = {"Start of frame", "End of frame", "Present value"};
                    JComboBox startXCombo = new JComboBox(combo);
                    startXCombo.setEditable(true);
                    JComboBox startYCombo = new JComboBox(combo);
                    startYCombo.setEditable(true);
                    final JComponent[] inputs = new JComponent[]{new JLabel("Start X position"), startXCombo, new JLabel("Start Y position"), startYCombo,};
                    JOptionPane.showMessageDialog(null, inputs, "Start positions", JOptionPane.PLAIN_MESSAGE);
                    String xval = (String) startXCombo.getSelectedItem();
                    if (xval.equalsIgnoreCase("Start of frame")) {
                        startX = 0;
                    } else if (xval.equalsIgnoreCase("End of frame")) {
                        startX = gamePanel.getWidth();
                    } else if (xval.equalsIgnoreCase("Present value")) {
                        SpriteModel selectedSpriteModel = SpriteList.getInstance().getSelectedSpriteModel();
                        startX = selectedSpriteModel.getPosX();
                    } else {
                        startX = Integer.parseInt(xval);
                    }
                    String yval = (String) startYCombo.getSelectedItem();
                    if (yval.equalsIgnoreCase("Start of frame")) {
                        startY = 0;
                    } else if (yval.equalsIgnoreCase("End of frame")) {
                        startY = gamePanel.getHeight();
                    } else if (yval.equalsIgnoreCase("Present value")) {
                        SpriteModel selectedSpriteModel = SpriteList.getInstance().getSelectedSpriteModel();
                        startY = selectedSpriteModel.getPosY();
                    } else {
                        startY = Integer.parseInt(yval);
                    }
                }
            }
        });

        eventBox = new JComboBox(eventTypes);
        eventBox.setToolTipText("Select Event");
        // eventActionListModel.addElement("Event-Action List");
        eventActionList = new JList();
        if (eventActionListModel != null) {
            eventActionList.setModel(eventActionListModel);
        }
        JScrollPane scrollPane = new JScrollPane(eventActionList);
        scrollPane.setEnabled(false);

        collisionSpriteBox = new JComboBox();
        if (collisionSpriteModel != null) {
            collisionSpriteBox.setModel(collisionSpriteModel);
        }
        collisionSpriteBox.setVisible(false);
        collisionSpriteBox.setToolTipText("Collision against");

        addEventActionButton = new JButton("Add");
        addEventActionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                eventActionList.setEnabled(true);


                // String spriteName = (String)spriteList.getSelectedValue();
                String eventName = (String) eventBox.getSelectedItem();
                String actionName = (String) actionBox.getSelectedItem();
                SpriteModel selectedSpriteModel = SpriteList.getInstance().getSelectedSpriteModel();

                // gameController.getGameObjectsWithId().put(selectedSpriteModel.getId(),
                // selectedSpriteModel);
                // List<SpriteModel> list =
                // gameController.getGameObjectsWithGroupId().get(selectedSpriteModel.getGroupId());
                // if(list==null)
                // list = new ArrayList<SpriteModel>();
                // if(!list.contains(selectedSpriteModel))
                // list.add(selectedSpriteModel);
                if (!Constants.isMultiplayer) {
                    if (selectedSpriteModel != null) {

                        int scoreModificationValue = 1;

                        if (scoreModificationField.isVisible()) {
                            if (StringUtils.isNumeric(scoreModificationField.getText())) {
                                scoreModificationValue = Integer.parseInt(scoreModificationField.getText());
                            } else {
                                JOptionPane.showMessageDialog(design.getBaseFrame(), "Integer expected in score modification field. currently set to " + scoreModificationValue + ". Resize window to view modification field", "Input Error", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                        selectedSpriteModel.setScoreModificationValue(scoreModificationValue);
                        String eventActionString = eventName + "+" + actionName;
                        eventActionListModel.addElement(eventActionString);
                        SpriteModel secondaryModel = null;
                        String itemName = (String) collisionSpriteBox.getSelectedItem();
                        Collection<SpriteModel> spriteList = SpriteList.getInstance().getSpriteList();
                        for (SpriteModel model : spriteList) {
                            if (model.getId().equalsIgnoreCase(itemName)) {
                                secondaryModel = model;
                            }
                        }
                        EventListener listener = Helper.getsharedHelper().getEventListenerForString(eventName, actionName, selectedSpriteModel, secondaryModel);
                        if (listener instanceof KeyPressedEventListener) {
                            facade.getKeyListenerController().registerListener(listener);
                        } else {
                            facade.getGameController().registerListener(listener);
                        }
                        if (listener instanceof OutOfBoundaryEventListener) {
                            OutOfBoundaryEventListener outOfBoundary = (OutOfBoundaryEventListener) listener;
                            GameAction action = outOfBoundary.getAction();
                            if (action instanceof ActionStartOver) {
                                ActionStartOver startOver = (ActionStartOver) action;
                                startOver.setStartX(startX);
                                startOver.setStartY(startY);
                            }
                        }
                        // gameController.getGameObjectsWithGroupId().put(selectedSpriteModel.getGroupId(),
                        // list);
                        selectedSpriteModel.getStringToEventMap().put(eventActionString, listener.getEventId());
                        selectedSpriteModel.getEventListenerList().add(listener);
                    } else {
                        JOptionPane.showMessageDialog(design.getBaseFrame(), "You haven't selected any objects", "Input Error", JOptionPane.ERROR_MESSAGE);
                    }

                } else {
                    LOG.info("In multiplayer key control assignment");
                    GameMakerView gameMakerView = Helper.getsharedHelper().getGameMakerView();
                    String mode = (String) gameMakerView.getActionEventPanel().getInputKeyPanel().getComboBox().getSelectedItem();
                    if (mode.equals("Player 1")) {
                        LOG.info("setting the control to player 1");
                        selectedSpriteModel.setMode(playerModes.PLAYER1);
                    } else if (mode.equals("Player 2")) {
                        LOG.info("setting the control to player 2");
                        selectedSpriteModel.setMode(playerModes.PLAYER2);
                    }
                    if (selectedSpriteModel != null) {

                        int scoreModificationValue = 1;

                        if (scoreModificationField.isVisible()) {
                            if (StringUtils.isNumeric(scoreModificationField.getText())) {
                                scoreModificationValue = Integer.parseInt(scoreModificationField.getText());
                            } else {
                                JOptionPane.showMessageDialog(design.getBaseFrame(), "Integer expected in score modification field. currently set to " + scoreModificationValue + ". Resize window to view modification field", "Input Error", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                        selectedSpriteModel.setScoreModificationValue(scoreModificationValue);
                        String eventActionString = eventName + "+" + actionName;
                        eventActionListModel.addElement(eventActionString);
                        SpriteModel secondaryModel = null;
                        String itemName = (String) collisionSpriteBox.getSelectedItem();
                        Collection<SpriteModel> spriteList = SpriteList.getInstance().getSpriteList();
                        for (SpriteModel model : spriteList) {
                            if (model.getId().equalsIgnoreCase(itemName)) {
                                secondaryModel = model;
                            }
                        }
                        EventListener listener = Helper.getsharedHelper().getEventListenerForString(eventName, actionName, selectedSpriteModel, secondaryModel);
                        if (listener instanceof KeyPressedEventListener) {
                            facade.getKeyListenerController().registerListener(listener);
                        } else {
                            facade.getGameController().registerListener(listener);
                        }
                        if (listener instanceof OutOfBoundaryEventListener) {
                            OutOfBoundaryEventListener outOfBoundary = (OutOfBoundaryEventListener) listener;
                            GameAction action = outOfBoundary.getAction();
                            if (action instanceof ActionStartOver) {
                                ActionStartOver startOver = (ActionStartOver) action;
                                startOver.setStartX(startX);
                                startOver.setStartY(startY);
                            }
                        }
                        // gameController.getGameObjectsWithGroupId().put(selectedSpriteModel.getGroupId(),
                        // list);
                        selectedSpriteModel.getStringToEventMap().put(eventActionString, listener.getEventId());
                        selectedSpriteModel.getEventListenerList().add(listener);
                    } else {
                        JOptionPane.showMessageDialog(design.getBaseFrame(), "You haven't selected any objects", "Input Error", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });
        removeEventActionButton = new JButton("Remove");
        removeEventActionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String eventActionPair = (String) eventActionList.getSelectedValue();
                if (eventActionPair == null || eventActionPair.equalsIgnoreCase("")) {
                    return;
                }
                eventActionListModel.removeElement(eventActionPair);

                SpriteModel spriteModel = SpriteList.getInstance().getSelectedSpriteModel();
                int hashId = spriteModel.getStringToEventMap().get(eventActionPair);

                ArrayList<EventListener> toRemoveListeners = new ArrayList<EventListener>();
                for (EventListener listener : spriteModel.getEventListenerList()) {
                    if (listener.getEventId() == hashId) {
                        toRemoveListeners.add(listener);
                    }
                }

                GameController gameController = facade.getGameController();

                for (EventListener listener : toRemoveListeners) {
                    spriteModel.getEventListenerList().remove(listener);
                    gameController.unregisterListener(listener);
                }

            }
        });

        spriteList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {

                eventActionListModel.clear();
                collisionSpriteModel.removeAllElements();
                for (SpriteModel sprite : SpriteList.getInstance().getSpriteList()) {
                    if (sprite.getId().equals(spriteList.getSelectedValue())) {
                        SpriteList.getInstance().setSelectedSpriteModel(sprite);
                        design.updateProperties();

                        Set<String> s = SpriteList.getInstance().getSelectedSpriteModel().getStringToEventMap().keySet();
                        Iterator<String> it = s.iterator();
                        while (it.hasNext()) {
                            eventActionListModel.addElement(it.next());
                        }

                    }
                }

            }
        });

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(10, 5, 0, 0);
        gridBagConstraints.weightx = 0.25;
        //gridBagConstraints.weighty=1;

        // gridBagConstraints.gridx = 0;
        // gridBagConstraints.gridy = 0;
        // actionEventPanel.add(spriteListTypeBox,gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;

        actionEventPanel.add(spriteName, gridBagConstraints);

        gridBagConstraints.gridx = 1;

        actionEventPanel.add(eventActionName, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipady = 100;
        actionEventPanel.add(spriteListScrollPane, gridBagConstraints);


        gridBagConstraints.gridx = 1;
        actionEventPanel.add(scrollPane, gridBagConstraints);


        gridBagConstraints.ipady = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        actionEventPanel.add(eventBox, gridBagConstraints);


        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        actionEventPanel.add(actionBox, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        actionEventPanel.add(addEventActionButton, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        actionEventPanel.add(removeEventActionButton, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        actionEventPanel.add(collisionSpriteBox, gridBagConstraints);
        actionEventPanel.add(inputPanel, gridBagConstraints);

        // Score modification.
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        actionEventPanel.add(scoreModificationField, gridBagConstraints);
        // actionEventPanel.add(inputPanel, gridBagConstraints);

        // spriteListTypeBox.addActionListener(new ActionListener(){
        // @Override
        // public void actionPerformed(ActionEvent arg0) {
        // if(spriteListTypeBox.getSelectedIndex()==0){
        // spriteList.setEnabled(true);
        // groupSpriteList.setEnabled(false);
        // }
        // if(spriteListTypeBox.getSelectedIndex()==1){
        // spriteList.setEnabled(false);
        // groupSpriteList.setEnabled(true);
        // }
        // }
        // });

        eventBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = (String) eventBox.getSelectedItem();

                if (text.equalsIgnoreCase("Collision")) {
                    collisionSpriteBox.setVisible(true);

                    for (int i = 0; i < SpriteList.getInstance().getSpriteList().size(); i++) {
                        collisionSpriteModel.removeAllElements();
                    }

                    for (SpriteModel sprite : SpriteList.getInstance().getSpriteList()) {
                        if (sprite.getId().equals(spriteList.getSelectedValue())) {
                        } else {
                            collisionSpriteModel.addElement(sprite.getId());
                        }
                    }

                }

                if (text.equalsIgnoreCase("Input")) {
                    collisionSpriteBox.setVisible(false);
                    inputPanel.setVisible(true);
                    inputPanel.requestFocusInWindow();
                }
                if (text.equalsIgnoreCase("New Frame")) {
                    collisionSpriteBox.setVisible(false);
                    inputPanel.setVisible(false);
                }
                if (text.equalsIgnoreCase("Time Change")) {
                    collisionSpriteBox.setVisible(false);
                    inputPanel.setVisible(false);
                }

            }
        });

    }

    public void updateActionEvents() {
        SpriteModel selectedSpriteModel = SpriteList.getInstance().getSelectedSpriteModel();
        if (selectedSpriteModel != null) {
            int selectedItem = 0;
            DefaultListModel listModel = getSpriteListIndividualModel();
            for (int i = 0; i < listModel.size(); i++) {
                String element = (String) listModel.get(i);
                if (element.equalsIgnoreCase(selectedSpriteModel.getId())) {
                    selectedItem = i;
                }
            }
            getSpriteList().setSelectedIndex(selectedItem);
        }
    }

    public void removeSpriteModelFromList(SpriteModel selectedSpriteModel) {
        int selectedItem = 0;
        DefaultListModel listModel = getSpriteListIndividualModel();
        for (int i = 0; i < listModel.size(); i++) {
            String element = (String) listModel.get(i);
            if (element.equalsIgnoreCase(selectedSpriteModel.getId())) {
                selectedItem = i;
            }
        }
        listModel.remove(selectedItem);
        getSpriteList().setModel(listModel);
        GameMakerView gameMakerView = Helper.getsharedHelper().getGameMakerView();
        gameMakerView.removeInfoPanel();
    }

    public void reset() {
        getSpriteListIndividualModel().removeAllElements();
        getSpriteList().setModel(getSpriteListIndividualModel());

    }

    public void updateSpriteList(SpriteModel spriteModel) {
        SpriteList.getInstance().addSprite(spriteModel);
        SpriteList.getInstance().setSelectedSpriteModel(spriteModel);

        getSpriteListIndividualModel().addElement(spriteModel.getId());
        if (!getSpriteListGroupModel().contains(spriteModel.getGroupId())) {
            getSpriteListGroupModel().addElement(spriteModel.getGroupId());
        }
        if (getSpriteListIndividualModel().size() > 0) {
            getSpriteList().setModel(getSpriteListIndividualModel());
        }
    }

    public JPanel getInputPanel() {
        return inputPanel;
    }

    public void setInputPanel(JPanel inputPanel) {
        this.inputPanel = inputPanel;
    }

    public InputKeyPanel getInputKeyPanel() {
        return inputKeyPanel;
    }

    public void setInputKeyPanel(InputKeyPanel inputKeyPanel) {
        this.inputKeyPanel = inputKeyPanel;
    }

    JPanel getPanel() {
        return actionEventPanel;
    }

    public DefaultListModel getSpriteListIndividualModel() {
        return spriteListIndividualModel;
    }

    public void setSpriteListIndividualModel(DefaultListModel spriteListIndividualModel) {
        this.spriteListIndividualModel = spriteListIndividualModel;
    }

    public JList getSpriteList() {
        return spriteList;
    }

    public void setSpriteList(JList spriteList) {
        this.spriteList = spriteList;
    }

    public DefaultListModel getSpriteListGroupModel() {
        return spriteListGroupModel;
    }

    public void setSpriteListGroupModel(DefaultListModel spriteListGroupModel) {
        this.spriteListGroupModel = spriteListGroupModel;
    }
}
