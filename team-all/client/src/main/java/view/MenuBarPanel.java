package view;

import java.awt.CheckboxMenuItem;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Random;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;

import chat.AuthReceiver;

import facade.Facade;
import game.engine.slick2d.player.GameEngineController;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Queue;

import loader.GameDataPackageIO;
import loader.GamePackage;
import lookandfeel.AnimationHandler;
import lookandfeel.ThemeHandler;
import model.Player;
import model.SpriteModel;
import multiplayer.SessionFactory;
import twitter.GetScore;
import twitter.UpdateStatus;
import twitter4j.TwitterException;
import utility.ClockDisplay;
import utility.Constants;
import utility.Helper;
import utility.Layers;
import utility.Score;
import utility.SpriteList;
import view.communication.ClientHandler;
import view.companels.GameBaseLoadPanel;
import view.companels.GameBaseSavePanel;

public class MenuBarPanel implements ActionListener, ItemListener {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(MenuBarPanel.class);
    private JMenuBar menuBar = new JMenuBar();
    private JMenuItem[] modes = new JMenuItem[2];
    private final String host = Constants.HOST;
    private final String path = Constants.PATH;
    private final String urldeleteHostedGameBaseRecord = "/deleteHostedGameBaseRecord";

    public MenuBarPanel() {

        JMenu menuGame = new JMenu("Game");
        menuBar.add(menuGame);

        // Create a menu item
        if (!Constants.isGamePlayer) {
            JMenuItem loadItem = new JMenuItem("Load");
            JMenuItem saveItem = new JMenuItem("Save");
            loadItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    loadGame();
                }
            });

            saveItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    saveGame();
                }
            });

            menuGame.add(loadItem);
            menuGame.add(saveItem);
            menuGame.add(new JSeparator());
        }
        JMenuItem changeTheme = new JMenuItem("Change Theme");
        changeTheme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ThemeHandler.showThemePanel();
                GameMakerView gameMakerView = Helper.getsharedHelper().getGameMakerView();
                GamePanel gamePanel = Helper.getsharedHelper().getGamePanel();
                JFrame frame = gameMakerView.getBaseFrame();
                //frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
                SwingUtilities.updateComponentTreeUI(frame.getRootPane());
                //SwingUtilities.updateComponentTreeUI(menuBar); 
                frame.getRootPane().updateUI();
                //Update UI
                gameMakerView.getGameMakerPanel().updateUI();
                gamePanel.updateUI();
                gameMakerView.getActionEventPanel().getPanel().updateUI();

            }
        });

        JMenuItem exitItem = new JMenuItem("Exit");

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        menuGame.add(changeTheme);
        menuGame.add(new JSeparator());
        menuGame.add(exitItem);


        // Create a menu
        JMenu menu = new JMenu("Insert");
        menuBar.add(menu);

        // Create a menu item
        JCheckBoxMenuItem item = new JCheckBoxMenuItem("Clock");
        item.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                CheckboxMenuItem cbi = (CheckboxMenuItem) e.getSource();
                ClockDisplay.getInstance().setVisible(cbi.getState());
                GamePanel gamePanel = Helper.getsharedHelper().getGamePanel();
                gamePanel.repaint();
            }
        });
        menu.add(item);
        
        JMenu user = new JMenu("User");
        menuBar.add(user);
        
        JMenuItem login = new JMenuItem("Login");
        JMenuItem logout = new JMenuItem("Logout");
        JMenuItem register = new JMenuItem("Register");
        JMenuItem facebookLogin = new JMenuItem("Login with Facebook");
        JMenuItem postFacebookScore = new JMenuItem("Post Score to Facebook");
        JMenuItem twitter = new JMenuItem("Post Score to Twitter");
        JMenuItem getTwitterScore = new JMenuItem("Get Score from Twitter");
        postFacebookScore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                postFacebookScore();
            }
        });

        getTwitterScore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                GetScore getScore = new GetScore();
                try {
                    boolean result = getScore.getTwitterScores();
                    if (result) {
                        LOG.debug("receiving scores from twitter");
                    } else {
                        LOG.debug("error in receiving scores from twitter");
                    }
                } catch (TwitterException ex) {
                    Logger.getLogger(MenuBarPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(MenuBarPanel.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        twitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                try {
                    UpdateStatus updateStatus = new UpdateStatus();
                    boolean result = updateStatus.execute("Score " + Score.getInstance().getScore() + " from GameMaker #GameMakerP532");
                    if (result) {
                        JOptionPane.showMessageDialog(null, "Posted Score succesfully to Twitter", "Twitter Post Confirmation", JOptionPane.PLAIN_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Oops something went wrong try again!", "Twitter Post Confirmation", JOptionPane.PLAIN_MESSAGE);
                    }

                } catch (IOException e1) {
                	LOG.error(e1);
                }

            }
        });


        facebookLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

                try {
                    Random random = new Random();
                    Long currentTime = System.currentTimeMillis();
                    Integer randomNumber = random.nextInt();
                    String queueName = currentTime.toString() + randomNumber.toString();
                    new AuthReceiver(queueName);
                    URI uri = new URI(Constants.FacebookServer + "?q=" + queueName);
                    Desktop.getDesktop().browse(uri);
                } catch (IOException e) {
                	LOG.error(e);
                } catch (URISyntaxException e) {
                	LOG.error(e);
                }

            }
        });



        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
            	if(Player.getInstance().getUsername()==null){
                LoginFrame f = new LoginFrame();
                AnimationHandler.RotateIn(f.getLogin(), f.getLoginPanel(), 1000, 360, f.getLogin().getWidth() / 2, f.getLogin().getHeight() / 2);
            	}
            	else{
            		JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame,
							"You are already logged in.");
            	}

            }
        });
        
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
            	if(Player.getInstance().getUsername()!=null){
            		Player.getInstance().setUsername(null);
            		JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame,
							"You have logged out successfully.");
					if(Constants.isGameMaker){
						GameMakerView gameMakerView = Helper.getsharedHelper().getGameMakerView();
						gameMakerView.getBaseFrame().setVisible(false);
						OptionsFrame.getOptionFrame().setVisible(true);
					}
					else{
						GamePlayerView.getBaseFrame().setVisible(false);
						OptionsFrame.getOptionFrame().setVisible(true);
					}
            	}
            	else{
            		JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame,
							"You are not logged in to log out.");
            	}

            }
        });


        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterFrame f = new RegisterFrame();
                AnimationHandler.RotateIn(f.getLogin(), f.getLoginPanel(), 1000, -360, f.getLogin().getWidth() / 2, f.getLogin().getHeight() / 2);

            }
        });

        user.add(login);
        user.add(logout);
        user.add(register);
        user.add(facebookLogin);
        user.add(postFacebookScore);
        user.add(twitter);
        user.add(getTwitterScore);

        /*JMenu menuMultiPlayer = new JMenu("MultiPlayer");
         JMenuItem startMultiPlayer = new JMenuItem("Start"); 
         menuMultiPlayer.add(startMultiPlayer);
         menuBar.add(menuMultiPlayer);

         startMultiPlayer.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
         Constants.isMultiplayer = true;
         MultiPlayerOption p = new MultiPlayerOption(GameMakerView.getInstance().getGamePanel());
         LOG.info("in start action listener");
         p.selectOption();
         }
         });*/

        JMenu menuMode = new JMenu("Mode");
        ButtonGroup modeGroup = new ButtonGroup();

        modes[0] = new JRadioButtonMenuItem("Single Player");
        modes[1] = new JRadioButtonMenuItem("Multi Player");
        modes[0].setSelected(true);
        //modes[0].addItemListener(this);

        if (Constants.isGamePlayer) {
            modes[0].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (Constants.isMultiplayer && Constants.isHost && Constants.isHosted) {
                        Constants.isMultiplayer = false;
                        String playerName = Player.getInstance().getUsername();

                        try {
                        	
                            ClientHandler.deleteHostedGameBase(playerName, host, path + urldeleteHostedGameBaseRecord);
                            MultiPlayerOption.getInstanceOf().getJoinWaitFrame().setVisible(false);
                            SessionFactory.getInstanceOf().closeSession();
                            JFrame frame = new JFrame();
                            JOptionPane.showMessageDialog(frame, "Hosted game is exited.");
                            Constants.isHosted=false;
                            
                            
                        } catch (Exception e1) {
                        	LOG.error(e1);
                        }
                    }
                    
                }
            });
        }

        if (Constants.isGamePlayer) {
            modes[1].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(Constants.isHosted){
                    	JFrame frame = new JFrame();
                        JOptionPane.showMessageDialog(frame, "You are presently hosting a game. You cannot host more than one game");                   	
                    }
                    else{
                	
                	Constants.isMultiplayer = true;
                	GamePanel gamePanel = Helper.getsharedHelper().getGamePanel();
                    MultiPlayerOption.getInstanceOf().setRootComp(gamePanel);
                    //MultiPlayerOption p = new MultiPlayerOption(GameMakerView.getInstance().getGamePanel());
                    LOG.info("in start action listener");
                    MultiPlayerOption.getInstanceOf().selectOption();
                    //p.selectOption();
                    }
                }
            });
        }
        if (Constants.isGameMaker) {
            modes[1].addItemListener(this);
        }
        
        modeGroup.add(modes[0]);
        modeGroup.add(modes[1]);
        menuMode.add(modes[0]);
        menuMode.add(modes[1]);
        menuBar.add(menuMode);

        
        JMenu helpMode = new JMenu("Help");
        JCheckBoxMenuItem specialCheck2 = new JCheckBoxMenuItem("Show Pop-us", true);
        helpMode.add(specialCheck2);
        specialCheck2.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				AbstractButton button = (AbstractButton) e.getItem();
			      if (button.isSelected()){
			    	  Helper.getsharedHelper().setShowPopups(true);
			      }
			      else{
			    	  Helper.getsharedHelper().setShowPopups(false);  
			      }
			}
		});
        menuBar.add(helpMode);
        
    }

    /**
     * ******* Getters and Setters ************
     */
    public void setMenuBar(JMenuBar menuBar) {
        this.menuBar = menuBar;
    }

    public JMenuBar getMenuBar() {
        return this.menuBar;
    }

    public static void saveGame() {
    	GameMakerView gameMakerView = Helper.getsharedHelper().getGameMakerView();
    	Facade facade = Helper.getsharedHelper().getFacade();
        GamePackage game = new GamePackage(SpriteList.getInstance().getSpriteList(), facade.getGameController().getEvents(), facade.getKeyListenerController().getKeyEvents(), Layers.getInstance().getLayers(), ClockDisplay.getInstance().isVisible());
        String gameData = GameDataPackageIO.convertGamePackageToString(game);

        GameBaseSavePanel p = new GameBaseSavePanel(gameMakerView.getBaseFrame().getRootPane());
        p.saveGameToRemoteServer(gameData);

    }
    public static void postFacebookScore() {
        String message = "My new Score: " + Score.getInstance().getScore() + " from GameMaker";

        String name = "Game Maker Website";
        String description = "Go to game maker website";
        String caption = "Download game maker";
        URI uri = null;
        try {
            message = URLEncoder.encode(message, "UTF-8");
            name = URLEncoder.encode(name, "UTF-8");
            description = URLEncoder.encode(description, "UTF-8");
            caption = URLEncoder.encode(caption, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MenuBarPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            uri = new URI(Constants.FacebookServer + "?action=post&message=" + message + "&name=" + name + "&caption=" + caption + "&description=" + description);
        } catch (URISyntaxException ex) {
            Logger.getLogger(MenuBarPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Desktop.getDesktop().browse(uri);
        } catch (IOException ex) {
            Logger.getLogger(MenuBarPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void loadGame() {
    	GameMakerView gameMakerView = Helper.getsharedHelper().getGameMakerView();
        GameBaseLoadPanel p = new GameBaseLoadPanel(gameMakerView.getBaseFrame().getRootPane());

        String gameData = p.readGameDataFromRemoteList();
        if (gameData == null) {
            return;
        }

        LOG.debug("Game data is --------------------------------------------------------------------" + gameData);

        postProcessingAfterLoad(gameData);
    }

    public static void postProcessingAfterLoad(String gameData) {
        GamePackage game = GameDataPackageIO.loadGamePackageFromFile(gameData);
        
        LOG.debug("load done");

        Collection<SpriteModel> allSpriteModels = game.getSpriteList();
        List<String> layers = game.getLayers();
        ClockDisplay.getInstance().setVisible(game.isClockDisplayable());
        // SpriteList.getInstance().setSpriteList(allSpriteModels);
        SpriteModel m = (SpriteModel) ((Queue) allSpriteModels).peek();
        SpriteList.getInstance().setSelectedSpriteModel(m);
        for (SpriteModel model : allSpriteModels) {
            SpriteList.getInstance().addSprite(model);
            SpriteList.getInstance().setSelectedSpriteModel(model);
        }


        if (!Helper.getsharedHelper().isPlayerMode()) {
        	GameMakerView gameMakerView = Helper.getsharedHelper().getGameMakerView();
            gameMakerView.getLayerBox().removeAllItems();
            for (String layer : layers) {
                gameMakerView.getLayerBox().addItem(layer);
            }

            Facade facade = Helper.getsharedHelper().getFacade();
            facade.getGameController().setEvents(game.getEventsForGameController());
            facade.getKeyListenerController().setKeyEvents(game.getEventsForKeyController());

            facade.createViewsForModels(game.getSpriteList());

            ActionEventPanel actionEventPanel = gameMakerView.getActionEventPanel();
            for (SpriteModel model : allSpriteModels) {

                actionEventPanel.getSpriteListIndividualModel().addElement(model.getId());
                if (!actionEventPanel.getSpriteListGroupModel().contains(model.getGroupId())) {
                    actionEventPanel.getSpriteListGroupModel().addElement(model.getGroupId());
                }
                if (actionEventPanel.getSpriteListIndividualModel().size() > 0) {
                    actionEventPanel.getSpriteList().setModel(gameMakerView.getActionEventPanel().getSpriteListIndividualModel());
                }

            }
            gameMakerView.updateProperties();
        } else {
            GamePlayerView gamePlayerView = Helper.getsharedHelper().getGamePlayerView();
//			GamePanel gamePanel = gamePlayerView.getGamePanel();
//
//			Facade facade = gamePlayerView.getFacade();
//			facade.createViewsForModels(game.getSpriteList());
//			facade.getGameController().setEvents(game.getEventsForGameController());
//			facade.getKeyListenerController().setKeyEvents(game.getEventsForKeyController());			
//			gamePanel.repaint();

            GameEngineController gameEngine = gamePlayerView.getGameEnginePanel().getGame();
            gameEngine.setEventsForGameController(game.getEventsForGameController());
            gameEngine.setKeyEvents(game.getEventsForKeyController());


        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

//		if (e.getItem().equals(modes[0]))
//				{
//			LOG.info("In single player mode");
//			Constants.isMultiplayer = false;
//			GameMakerView.getInstance().getBaseFrame().validate();
//			//GameMakerView.getInstance().getControlPanel().validate();
//			GameMakerView.getInstance().getActionEventPanel().getInputKeyPanel().getComboBox().setVisible(false);
//			GameMakerView.getInstance().getActionEventPanel().getInputKeyPanel().getInputPanel().validate();
//
//				}
        if (e.getItem().equals(modes[1])) {
            LOG.info("In Multiplayer mode");
            if (!Constants.isMultiplayer) {
                Constants.isMultiplayer = true;
            } else {
                Constants.isMultiplayer = false;
            }
            GameMakerView gameMakerView = Helper.getsharedHelper().getGameMakerView();
            gameMakerView.getBaseFrame().validate();
            ActionEventPanel actionEventPanel = gameMakerView.getActionEventPanel();
            if (!actionEventPanel.getInputKeyPanel().getComboBox().isVisible()) {
            	actionEventPanel.getInputKeyPanel().getComboBox().setVisible(true);
            } else {
            	actionEventPanel.getInputKeyPanel().getComboBox().setVisible(false);
            }

            
            actionEventPanel.getInputKeyPanel().getInputPanel().validate();

		
        }

    }
}
