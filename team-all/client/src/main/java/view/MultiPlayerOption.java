
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.jms.JMSException;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import lookandfeel.AnimationHandler;
import model.Player;
import multiplayer.Receiver;
import multiplayer.Sender;
import multiplayer.SessionFactory;
import net.miginfocom.swing.MigLayout;
import utility.Constants;
import utility.Helper;
import view.communication.ClientHandler;

public final class MultiPlayerOption {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(MultiPlayerOption.class);
    private JComponent rootComp;
    private JFrame options;
    private JButton hostButton;
    private JButton joinButton;
    private JLabel optionLabel;
    private String sendingQueueName;
    private String receivingQueueName;
    private JFrame joinWaitFrame;
    private boolean isReady=false;
    private JButton startButton;

	public boolean isReady() {
		return isReady;
	}

	public void setReady(boolean isReady) {
		this.isReady = isReady;
		this.startButton.setEnabled(true);
	}

	private final static MultiPlayerOption instance = new MultiPlayerOption();

    private MultiPlayerOption() {
    	//this.startButton = new JButton("Start Game");
        
    }

    public static MultiPlayerOption getInstanceOf() {
        return instance;
    }

    public JComponent getRootComp() {
        return rootComp;
    }

    public void setRootComp(JComponent rootComp) {
        this.rootComp = rootComp;
    }

    public String getSendingQueueName() {
        return sendingQueueName;
    }
    
    public JFrame getJoinWaitFrame() {
		return joinWaitFrame;
	}

	public void setJoinWaitFrame(JFrame joinWaitFrame) {
		this.joinWaitFrame = joinWaitFrame;
	}
    public void setSendingQueueName(String sendingQueueName) {
        if (Constants.isHost) {
            this.sendingQueueName = sendingQueueName + "#sender";
        } else {
            this.sendingQueueName = sendingQueueName + "#receiver";
        }

    }

    public String getReceivingQueueName() {
        return receivingQueueName;
    }

    public void setReceivingQueueName(String receivingQueueName) {
        if (Constants.isHost) {
            this.receivingQueueName = receivingQueueName + "#receiver";
        } else {
            this.receivingQueueName = receivingQueueName + "#sender";
        }
    }

    public void selectOption() {
        LOG.info("i m in multiplayer class");
        options = new JFrame();
        hostButton = new JButton("Host");
        joinButton = new JButton("Join");
        optionLabel = new JLabel("Would you like to:");
        options.setLayout(new MigLayout("center,center"));
        options.add(optionLabel, "wrap,wmin 80, hmin 50");
        options.add(hostButton, "wmin 30, hmin 30");
        options.add(joinButton, "wmin 30, hmin 30");
        LOG.info("In hos button pressed");
        hostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Player.getInstance().getUsername() != null) {
                	LOG.info("In hos button pressed");
                    Constants.isHost = true;
//                    HostGame p = new HostGame(rootComp);
//                    String gameName = p.displayHostedGames();
                    String queueName = JOptionPane.showInputDialog(new JFrame(), "Enter the name of the hosted game");
                    String playerName = Player.getInstance().getUsername();
                    setSendingQueueName(queueName);
                    setReceivingQueueName(queueName);
                    try {
                        ClientHandler.insertHostedGame(playerName, queueName, queueName, Constants.HOST, Constants.PATH + "/insertHostedGameBaseRecord");
                        Constants.isHosted=true;
                        SessionFactory.getInstanceOf().createConnection();
                        Receiver.getInstanceOf().subscribe(getReceivingQueueName()); 
                    } catch (Exception ex) {
                    	LOG.error(ex);
    
                    }
                    
                    joinWaitFrame();
                    options.setVisible(false);
                    
                    try {
                        SessionFactory.getInstanceOf().createConnection();
                        Receiver.getInstanceOf().subscribe(getReceivingQueueName());
                    } catch (JMSException e1) {
                        LOG.info("Receiver failed");
                    }
                    Receiver.getInstanceOf().runGame();


                } else {
                	options.setVisible(false);
                	LoginFrame f = new LoginFrame();
                    AnimationHandler.RotateIn(f.getLogin(), f.getLoginPanel(), 1000, 360, f.getLogin().getWidth() / 2, f.getLogin().getHeight() / 2);
//                    JFrame frame = new JFrame();
//                    JOptionPane.showMessageDialog(frame, "Please login");
                }

            }
        });

        joinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Player.getInstance().getUsername() != null) {
                    Constants.isHost = false;

                    GamePanel gamePanel = Helper.getsharedHelper().getGamePanel();
                    JoinGame p = new JoinGame(gamePanel);
                    String queueName = p.displayJoinGames();
                    //Should be supported with a GUI displaying a list of games available to 
                    //Below line gets replaced with the GUI as mentioned above                 
                    String playerName = Player.getInstance().getUsername();
                    setSendingQueueName(queueName);
                    setReceivingQueueName(queueName);
                    Sender sender = new Sender();
                    try {
                        sender.sendAcknowledgement(getSendingQueueName(), playerName);
                    } catch (JMSException e2) {
                    	LOG.error(e2);
                    }
                    
                    try {
                        SessionFactory.getInstanceOf().createConnection();
                        Receiver.getInstanceOf().subscribe(getReceivingQueueName());
                    } catch (JMSException e1) {
                    	LOG.error(e1);
                    }
                    options.dispose();
                    Receiver.getInstanceOf().runGame();
                } else {
//                    JFrame frame = new JFrame();
//                    JOptionPane.showMessageDialog(frame, "Please login");
                	  LoginFrame f = new LoginFrame();
                      AnimationHandler.RotateIn(f.getLogin(), f.getLoginPanel(), 1000, 360, f.getLogin().getWidth() / 2, f.getLogin().getHeight() / 2);
                }

            }
        });

        options.setSize(200, 200);
        options.setLocationRelativeTo(rootComp);
        options.setVisible(true);

    }

    public void joinWaitFrame() {
    	options.dispose();
    	LOG.info("in join");
        joinWaitFrame = new JFrame();
        joinWaitFrame.setLayout(new MigLayout("center,center"));
        joinWaitFrame.setSize(200, 200);
        JLabel label = new JLabel("Waiting for joinee...");
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setSize(30, 20);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                joinWaitFrame.dispose();
                return;

            }
        });
        joinWaitFrame.add(label, "wrap,wmin 100, hmin 50");
        joinWaitFrame.add(cancelButton, "wmin 50, hmin 30");
        LOG.info("In Join Frame");
        joinWaitFrame.setLocationRelativeTo(rootComp);
        joinWaitFrame.setVisible(true);
        joinWaitFrame.setFocusable(true);
        joinWaitFrame.requestFocus();
    }

    public void acceptUserFrame(String user, String game) {
        LOG.info("In accept user Frame");
        joinWaitFrame.dispose();
        final JFrame acceptUserFrame = new JFrame();
        acceptUserFrame.setLayout(new MigLayout("center,center"));
        acceptUserFrame.setSize(200, 200);
        JLabel label = new JLabel(user + " wants to join your game " + game);
        JButton allowButton = new JButton("Allow");
        allowButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
//				Sender sender = new Sender();
//                sender.sendAsHost(getSendingQueueName());
                acceptUserFrame.dispose();
                startGameFrame();
                
			}
        	
        });
        JButton kickButton = new JButton("Kick");
        kickButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				acceptUserFrame.dispose();
				joinWaitFrame();
				
			}
        	
        });
        acceptUserFrame.add(label, "wrap,wmin 100, hmin 30");
        acceptUserFrame.add(allowButton, "wmin 50, hmin 30");
        acceptUserFrame.add(kickButton, "wmin 50, hmin 30");
        acceptUserFrame.setLocationRelativeTo(rootComp);
        acceptUserFrame.setVisible(true);
        acceptUserFrame.setFocusable(true);
        acceptUserFrame.requestFocus();
    }
    
    public void readyGameFrame()
    {
    	final JFrame readyGameFrame = new JFrame();
    	readyGameFrame.setLayout(new MigLayout("center,center"));
    	readyGameFrame.setSize(200, 200);
        JLabel label = new JLabel("Press Ready whenever you are ready...");
        JButton readyButton = new JButton("Ready");
        readyButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Sender sender = new Sender();
				try {
					sender.readySignal();
					readyGameFrame.dispose();
				} catch (JMSException e1) {
					LOG.error(e1);
				}
				
				
			}
        	
        });
        readyGameFrame.add(label, "wrap,wmin 100, hmin 30");
        readyGameFrame.add(readyButton, "wmin 50, hmin 30");
        readyGameFrame.setLocationRelativeTo(rootComp);
        readyGameFrame.setVisible(true);
        readyGameFrame.setFocusable(true);
        readyGameFrame.requestFocus();
    }
    
    public void startGameFrame()
    {
    	final JFrame startGameFrame = new JFrame();
    	startGameFrame.setLayout(new MigLayout("center,center"));
    	startGameFrame.setSize(200, 200);
        JLabel label = new JLabel();
        this.startButton = new JButton("Start Game");
        this.startButton.setEnabled(true);
        this.startButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Sender sender = new Sender();
				try {
					sender.sendStartSignal(getSendingQueueName());
					startGameFrame.dispose();
				} catch (JMSException e1) {
					LOG.error(e1);
				}
				GamePlayerView gamePlayerView = Helper.getsharedHelper().getGamePlayerView();
				gamePlayerView.getGameEnginePanel().startGame();
				
			}
        	
        });
        startGameFrame.add(label, "wrap,wmin 100, hmin 30");
        startGameFrame.add(startButton, "wmin 50, hmin 30");
        startGameFrame.setLocationRelativeTo(rootComp);
        startGameFrame.setVisible(true);
        startGameFrame.setFocusable(true);
        startGameFrame.requestFocus();
    }
    
    
}
