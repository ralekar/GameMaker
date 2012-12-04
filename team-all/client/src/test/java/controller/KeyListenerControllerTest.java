/**
 * 
 */
package controller;

import static org.junit.Assert.*;
import org.uispec4j.UISpecTestCase;
import org.uispec4j.Window;
import org.uispec4j.interception.MainClassAdapter;


import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.SpriteModel;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import utility.Constants;
import utility.Helper;
import utility.SpriteList;
import view.GameMakerView;
import view.GamePanel;

import action.ActionMove;
import action.GameAction;
import eventlistener.CollisionEventListener;
import eventlistener.EventListener;
import eventlistener.KeyPressedEventListener;
import gameMaker.gameMaker;

public class KeyListenerControllerTest  {

	/**
	 * @throws java.lang.Exception
	 */
	private GamePanel gamePanel;
    private ArrayList<EventListener> events;
    private static SpriteModel selectedSpriteModel;
    private static SpriteModel secondarySpriteModel;
    private KeyListenerController  keyListenerControllerTest;
    private static KeyPressedEventListener keyListener;
	private static GameAction action;
	private GameMakerView gameMakerView ;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		gameMakerView = new GameMakerView(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
		gameMakerView.reset();
        //setAdapter(new MainClassAdapter(gameMaker.class, new String[0]));
        selectedSpriteModel = new SpriteModel(100, 100, 20, 20, 100, 100, "","");
		//secondarySpriteModel= new SpriteModel(190, 190, 10, 10, 100, 100, "");
		selectedSpriteModel.setGroupId("Group1");
		SpriteList.getInstance().addSprite(selectedSpriteModel);
		//SpriteList.getInstance().addSprite(secondarySpriteModel)
		keyListener = new KeyPressedEventListener();
		keyListener.setRegisteredGroupId(selectedSpriteModel.getGroupId());
		keyListener.setRegisteredObjectId(selectedSpriteModel.getId()); 
		
		keyListener.setxSpeed(selectedSpriteModel.getSpeedX());
		keyListener.setySpeed(selectedSpriteModel.getSpeedY());
		
		keyListener.setKeyRegistered(KeyEvent.VK_SHIFT);
		
		GameAction action = new ActionMove();
		keyListener.setAction(action);

        gamePanel=new GamePanel(Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT);
		keyListenerControllerTest = new KeyListenerController();
		//keyListenerControllerTest.registerListener(keyListener);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		gameMakerView.reset();
	}

	/**
	 * Test method for {@link controller.KeyListenerController#keyPressed(java.awt.event.KeyEvent)}.
	 */
	@Test
	public void testKeyPressed() {
		keyListenerControllerTest.registerListener(keyListener);
		KeyEvent keyEvent=new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), InputEvent.CTRL_MASK, KeyEvent.VK_SHIFT);
		keyListenerControllerTest.keyPressed(keyEvent);
		if(selectedSpriteModel.getPosY()==120)
			 assertTrue(true);
		else
			 assertTrue(false);
	}

//	/**
//	 * Test method for {@link controller.KeyListenerController#registerListener(eventlistener.EventListener)}.
//	 */
	@Test
	public void testRegisterListener() {
		
			keyListenerControllerTest.registerListener(keyListener);
			List<EventListener> events =keyListenerControllerTest.getKeyEvents();
			if(events.get(0).equals(keyListener))
				assertTrue(true);
			else
				assertTrue(false);
	}

//	/**
//	 * Test method for {@link controller.KeyListenerController#unregisterListener(eventlistener.EventListener)}.
//	 */
	@Test
	public void testUnregisterListener() {
		keyListenerControllerTest.unregisterListener(keyListener);
		List<EventListener> events =keyListenerControllerTest.getKeyEvents();
		if(events.isEmpty())
			assertTrue(true);
		else
			assertTrue(false);
			}
		
	}


