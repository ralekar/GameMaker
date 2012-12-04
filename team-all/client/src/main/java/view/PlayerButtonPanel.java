package view;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import loader.GameDataPackageIO;
import loader.GamePackage;
import net.miginfocom.swing.MigLayout;
import utility.Helper;
import utility.SpriteList;
import view.companels.GameProgressLoadPanel;
import view.companels.GameProgressSavePanel;

public class PlayerButtonPanel implements ActionListener {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(PlayerButtonPanel.class);
    private JPanel playerButtonPanel, buttonPanel;
    private JLabel userName;
    private JButton startButton;
    private JButton newButton;
    private JButton loadButton;
    private JButton saveButton;
    private JPanel chatViewPanel;
    private JButton share;

    public PlayerButtonPanel() {


//		userColor=Color.BLUE;
//		textColor=Color.BLACK;
//		playerAvatarUrl="http://www.mayurmasrani.com/default_user.jpg"; //to be changed later according to users avatar
        startButton = new JButton();//start
        startButton.setSize(30, 30);
        ImageIcon starticon = new ImageIcon(this.getClass().getClassLoader().getResource("start.png"));
        Image startimage = starticon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        starticon.setImage(startimage);
        startButton.setIcon(starticon);
        startButton.setToolTipText("Start Game");
        
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GamePlayerView gamePlayerView = Helper.getsharedHelper().getGamePlayerView();
                gamePlayerView.getGameEnginePanel().startGame();
            }
        });

        newButton = new JButton();//new
        newButton.setSize(30, 30);
        ImageIcon newicon = new ImageIcon(this.getClass().getClassLoader().getResource("new.png"));
        Image newimage = newicon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        newicon.setImage(newimage);
        newButton.setIcon(newicon);
        newButton.setToolTipText("New Game");

        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GamePlayerView gamePlayerView = Helper.getsharedHelper().getGamePlayerView();
                gamePlayerView.getGameEnginePanel().newGame();
            }
        });

        loadButton = new JButton();//load
        loadButton.setSize(30, 30);
        ImageIcon loadicon = new ImageIcon(this.getClass().getClassLoader().getResource("load.png"));
        Image loadimage = loadicon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        loadicon.setImage(loadimage);
        loadButton.setIcon(loadicon);

        loadButton.setToolTipText("Load Game");

        
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	GamePanel gamePanel = Helper.getsharedHelper().getGamePanel();
                GameProgressLoadPanel p = new GameProgressLoadPanel(gamePanel);
                GamePlayerView gamePlayerView = (GamePlayerView) Helper.getsharedHelper().getGamePlayerView();
                String gamename[] = new String[1];
                String gameData = p.readGameDataFromRemoteList(gamename);

                if (gameData == null) {
                    return;
                }

                GamePackage game = GameDataPackageIO.loadGamePackageFromFile(gameData);
                
                gamePlayerView.getGameEnginePanel().newGame(game);
                
                LOG.debug("load done");

            }
        });


        share = new JButton();//share
        share.setSize(30, 30);
        ImageIcon shareicon = new ImageIcon(this.getClass().getClassLoader().getResource("share.png"));
        Image shareimage = shareicon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        shareicon.setImage(shareimage);
        share.setIcon(shareicon);
        share.setToolTipText("Share Game");
        
        share.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 MenuBarPanel.postFacebookScore(); 
            }
        });



        saveButton = new JButton();//save
        saveButton.setSize(30, 30);
        ImageIcon saveicon = new ImageIcon(this.getClass().getClassLoader().getResource("save.png"));
        Image saveimage = saveicon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        saveicon.setImage(saveimage);
        saveButton.setIcon(saveicon);
        saveButton.setToolTipText("Save Game");
        
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 GamePlayerView gamePlayerView = (GamePlayerView) Helper.getsharedHelper().getGamePlayerView();
                GamePackage game = new GamePackage(SpriteList.getInstance().getSpriteList(), gamePlayerView.getGameEnginePanel().getGame().getEventsForGameController(), gamePlayerView.getGameEnginePanel().getGame().getKeyEvents(), null, false);
                String gameData = GameDataPackageIO.convertGamePackageToString(game);
                GamePanel gamePanel = Helper.getsharedHelper().getGamePanel();
                GameProgressSavePanel p = new GameProgressSavePanel(gamePanel);

                p.saveGameToRemoteServer(gameData);

            }
        });

        chatViewPanel = new ChatViewPanel().getChatViewPanel();

        playerButtonPanel = new JPanel(new MigLayout("center,center"));
        buttonPanel = new JPanel();
        
        buttonPanel.add(startButton);
        buttonPanel.add(newButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(share);

        playerButtonPanel.add(buttonPanel, "wrap,wmin 400, gapy 10px 80px");
        playerButtonPanel.add(chatViewPanel, "wrap,wmin 400, hmin 300");



    }

    public JPanel getPlayerButtonPanel() {
        return playerButtonPanel;
    }

    public void setPlayerButtonPanel(JPanel playerButtonPanel) {
        this.playerButtonPanel = playerButtonPanel;
    }

    public JLabel getUserName() {
        return userName;
    }

    public void setUserName(JLabel userName) {
        this.userName = userName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
