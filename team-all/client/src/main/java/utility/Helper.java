package utility;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;

import view.GameMakerView;
import view.GamePanel;
import view.GamePlayerView;

import model.SpriteModel;
import eventlistener.CollisionEventListener;
import eventlistener.EventListener;
import eventlistener.KeyPressedEventListener;
import eventlistener.NewFrameEventListener;
import eventlistener.OutOfBoundaryEventListener;
import facade.Facade;
import action.ActionBackToLastPosition;
import action.ActionCreateSpriteModel;
import action.ActionBounce;
import action.ActionChangeSpeed;
import action.ActionChangeVisibility;
import action.ActionChangeGameStatus;
import action.ActionIncreaseScore;
import action.ActionMove;
import action.ActionPlaySound;
import action.ActionRandomChangeDirection;
import action.ActionRotate;
import action.ActionStartOver;
import action.GameAction;
import action.RemoveAction;
import view.OptionsFrame;

public class Helper {
	public static Helper sharedHelper;
	private int currentKeyCode; 
	private boolean isPlayerMode;
//	private JFrame optionsFrame;
    private OptionsFrame optionsFrame;
    private GameMakerView gameMakerView;    
	private GamePlayerView gamePlayerView;
	private int currentMessageNumber;
	private HashMap<Integer,String> messages;
	private Rectangle originalPopupRect;
	private boolean showPopups;
	
	private Facade facade;
	private GamePanel gamePanel;
	
	
	public static Helper getsharedHelper(){
		if(sharedHelper == null)
			sharedHelper = new Helper();
		return sharedHelper;
	}

	public Helper() {
		setShowPopups(true);
		messages = new HashMap<Integer, String>();
		messages.put(new Integer(1), "<html><center>You just added a sprite here.</center></html>");
		messages.put(new Integer(2), "<html><center>You can drag it around.</center></html>");
		messages.put(new Integer(3), "<html><center>Scroll the object to resize.</center></html>");
		messages.put(new Integer(4), "<html><center>Right click on the object <br> to get more <br> options.</center></html>");
		messages.put(new Integer(5), "<html><center>Change the properties <br> of the selected <br>object here.</center></html>");
		messages.put(new Integer(6), "<html><center>Add event and actions <br> to the object here.</center></html>");
	}
	
	public String getMessage(int messageNumber){
		return messages.get(messageNumber);
	}
	
	public GameAction getActionForString(String actionString, SpriteModel model){
		GameAction gameAction = null;
		if(actionString.equalsIgnoreCase("move")){
			gameAction = new ActionMove();
		}
		else if(actionString.equalsIgnoreCase("create bomb")){
			gameAction = new ActionCreateSpriteModel();
		}
		else if(actionString.equalsIgnoreCase("change visibility")){
			gameAction = new ActionChangeVisibility(!model.isVisible());
		}
		else if(actionString.equalsIgnoreCase("play sound")){
			gameAction = new ActionPlaySound(model.getSoundFile());
		}
		else if(actionString.equalsIgnoreCase("Random Movement")){
			gameAction = new ActionRandomChangeDirection(model);
		}
		else if(actionString.equalsIgnoreCase("Change Speed")){
			gameAction = new ActionChangeSpeed((int)model.getSpeedX(),(int)model.getSpeedY());
		}
		else if(actionString.equalsIgnoreCase("Bounce")){
			gameAction = new ActionBounce(false);
		}
		else if(actionString.equalsIgnoreCase("Remove")){
			gameAction = new RemoveAction();
		}
		else if(actionString.equalsIgnoreCase("Rotate Clockwise")){
			gameAction = new ActionRotate("Clockwise");
		}
		else if(actionString.equalsIgnoreCase("Rotate Anticlockwise")){
			gameAction = new ActionRotate("Anticlockwise");
		}
		else if(actionString.equalsIgnoreCase("Reposition")){
			gameAction = new ActionBackToLastPosition();
		}
		else if(actionString.equalsIgnoreCase("Start over")){
			gameAction = new ActionStartOver();
		}
		else if(actionString.equalsIgnoreCase("Game Win")){
			gameAction = new ActionChangeGameStatus(true);
		}
		else if(actionString.equalsIgnoreCase("Game Lose")){
			gameAction = new ActionChangeGameStatus(false);
		}
		
		else if(actionString.equalsIgnoreCase("Increase Score")){
		    gameAction = new ActionIncreaseScore(model.getScoreModificationValue());
		    gameMakerView.setShouldDisplayScore(true);
		}
		
		return gameAction;
	}
	


	public EventListener getEventListenerForString(String eventName,String actionName, 
			SpriteModel selectedSpriteModel, SpriteModel secondarySpriteModel) {
		
		if(eventName.equalsIgnoreCase("New Frame")){
			NewFrameEventListener newFrameEventListener = new NewFrameEventListener();
			newFrameEventListener.setRegisteredGroupId(selectedSpriteModel.getGroupId());
			newFrameEventListener.setRegisteredObjectId(selectedSpriteModel.getId());
			GameAction action = getActionForString(actionName,selectedSpriteModel);
			newFrameEventListener.setAction(action);
			return newFrameEventListener;
		}
		else if(eventName.equalsIgnoreCase("Collision")){
			CollisionEventListener collisionListener = new CollisionEventListener();
			if(selectedSpriteModel != null && selectedSpriteModel.getGroupId() != null)
				collisionListener.setRegisteredGroupId1(selectedSpriteModel.getGroupId());
			if(secondarySpriteModel!=null && secondarySpriteModel.getId()!=null)
				collisionListener.setRegisteredGroupId2(secondarySpriteModel.getGroupId());
			GameAction action = getActionForString(actionName, selectedSpriteModel);
			
			List<GameAction> list = new ArrayList<GameAction>();
			if(action!=null)
				list.add(action);
			
			collisionListener.setAction(action);
	
			return collisionListener;
			
		}
		else if(eventName.equalsIgnoreCase("Input")){
			KeyPressedEventListener keyListener = new KeyPressedEventListener();
			keyListener.setRegisteredGroupId(selectedSpriteModel.getGroupId());
			keyListener.setRegisteredObjectId(selectedSpriteModel.getId()); 
			
			keyListener.setxSpeed(selectedSpriteModel.getSpeedX());
			keyListener.setySpeed(selectedSpriteModel.getSpeedY());
			
			keyListener.setKeyRegistered(this.currentKeyCode);
			
			GameAction action = getActionForString(actionName, selectedSpriteModel);
			keyListener.setAction(action);
			
			return keyListener;
		}
		else if(eventName.equalsIgnoreCase("Out of Boundary")){
			OutOfBoundaryEventListener outOfBoundary = new OutOfBoundaryEventListener();
			outOfBoundary.setRegisteredGroupId(selectedSpriteModel.getGroupId());
			outOfBoundary.setRegisteredObjectId(selectedSpriteModel.getId()); 
			GameAction action = getActionForString(actionName,selectedSpriteModel);
			List<GameAction> list = new ArrayList<GameAction>();
			if(action!=null)
				list.add(action);
			outOfBoundary.setAction(action);
			return outOfBoundary;
		}
		return null;
		
	}
	
	public JFrame createBaseFrame(int frameWidth, int frameHeight){
		final JFrame frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				Helper.getsharedHelper().getOptionsFrame().setVisible(true);
				frame.setVisible(false);
			}
		});
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setTitle("Game Maker");
		frame.setSize(frameWidth, frameHeight);
		frame.setLayout(new GridLayout(1, 3));
		frame.setMinimumSize(new Dimension(Constants.MINIMUM_FRAMEWIDTH, Constants.MINIMUM_FRAMEHEIGHT));
		frame.setResizable(true);
		
		frame.getRootPane().addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int originalFrameWidth = Constants.FRAME_WIDTH;
				int originalFrameHeight = Constants.FRAME_HEIGHT;

				Component rootPane = e.getComponent();
				Rectangle r = rootPane.getBounds();

				double xScale = (double) r.width / originalFrameWidth;
				double yScale = (double) r.height / originalFrameHeight;

				ResizeHelper.getInstance().setxFactor(xScale);
				ResizeHelper.getInstance().setyFactor(yScale);
				
			}
		});
		return frame;
	}
	
	/*
	 * GETTERS AND SETTERS
	 */
	
	public int getCurrentKeyCode() {
		return currentKeyCode;
	}

	public void setCurrentKeyCode(int currentKeyCode) {
		this.currentKeyCode = currentKeyCode;
	}

	public OptionsFrame getOptionsFrame() {
		return optionsFrame;
	}

	public void setOptionsFrame(OptionsFrame optionsFrame) {
		this.optionsFrame = optionsFrame;
	}

	public GamePlayerView getGamePlayerView() {
		return gamePlayerView;
	}

	public void setGamePlayerView(GamePlayerView gamePlayerView) {
		this.gamePlayerView = gamePlayerView;
	}

	public boolean isPlayerMode() {
		return isPlayerMode;
	}

	public void setPlayerMode(boolean isPlayerMode) {
		this.isPlayerMode = isPlayerMode;
	}

	public int getCurrentMessageNumber() {
		return currentMessageNumber;
	}

	public void setCurrentMessageNumber(int currentMessageNumber) {
		this.currentMessageNumber = currentMessageNumber;
	}

	public HashMap<Integer,String> getMessages() {
		return messages;
	}

	public void setMessages(HashMap<Integer,String> messages) {
		this.messages = messages;
	}

	public Rectangle getOriginalPopupRect() {
		return originalPopupRect;
	}

	public void setOriginalPopupRect(Rectangle originalPopupRect) {
		this.originalPopupRect = originalPopupRect;
	}

	public boolean isShowPopups() {
		return showPopups;
	}

	public void setShowPopups(boolean showPopups) {
		this.showPopups = showPopups;
	}

	public GameMakerView getGameMakerView() {
		return gameMakerView;
	}

	public void setGameMakerView(GameMakerView gameMakerView) {
		this.gameMakerView = gameMakerView;
	}

	public Facade getFacade() {
		return facade;
	}

	public void setFacade(Facade facade) {
		this.facade = facade;
	}

	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

}
