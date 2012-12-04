package controller;
// * 
 
//package controller;
//
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;
import model.SpriteModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utility.Constants;
import utility.SpriteList;
import view.GameMakerView;
import view.GamePanel;
import action.ActionChangeSpeed;
import action.GameAction;
import eventlistener.CollisionEventListener;
import eventlistener.EventListener;
import eventlistener.KeyPressedEventListener;
import eventlistener.NewFrameEventListener;


public class GameControllerTest{

	/**
	 * @throws java.lang.Exception
	 */
	private GamePanel gamePanel;
    private ArrayList<EventListener> events;
    private static SpriteModel selectedSpriteModel;
    private static SpriteModel secondarySpriteModel;
    private Map<String, SpriteModel> gameObjectsWithId;
    private Map<String, List<SpriteModel>> gameObjectsWithGroupId;
	private GameController gameControllerTest;
	private  CollisionEventListener collisionListener;
	private  GameAction action;
	


	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	   // GameMakerView.getInstance().reset();
		GameMakerView gameMakerView = new GameMakerView(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
		SpriteList.getInstance().getSpriteList().clear();
//      setAdapter(new MainClassAdapter(gameMaker.class, new String[0]));        
        gamePanel=new GamePanel(Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT);
        selectedSpriteModel = new SpriteModel(100, 100, 10, 10, 100, 100, "","");
        secondarySpriteModel= new SpriteModel(190, 190, 10, 10, 100, 100, "","");
		secondarySpriteModel.setGroupId("Group1");
		SpriteList.getInstance().addSprite(selectedSpriteModel);
		SpriteList.getInstance().addSprite(secondarySpriteModel);
		collisionListener = new CollisionEventListener();
		collisionListener.setRegisteredGroupId1(selectedSpriteModel.getGroupId());
		collisionListener.setRegisteredGroupId2(secondarySpriteModel.getGroupId());
		action= new ActionChangeSpeed(20,20);
		collisionListener.setAction(action);
		gameControllerTest = new GameController();
		gameControllerTest.registerListener(collisionListener);
		gameControllerTest.setGamePanel(gamePanel);
		gameControllerTest.getEvents().clear();
		}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		SpriteList.getInstance().getSpriteList().clear();
		gameControllerTest.getEvents().clear();
	}


//	/**
//	 * Test method for {@link controller.GameController#registerListener(eventlistener.EventListener)}.
//	 */
	@Test
	public void testRegisterListener() {
		gameControllerTest.registerListener(collisionListener);
		List<EventListener> events =gameControllerTest.getEvents();
		if(events.get(0).equals(collisionListener))
			assertTrue(true);
		else
			assertTrue(false);
	}
//
//	/**
//	 * Test method for {@link controller.GameController#unregisterListener(eventlistener.EventListener)}.
//	 */
	@Test
	public void testUnregisterListener() {
		gameControllerTest.registerListener(collisionListener);
		gameControllerTest.unregisterListener(collisionListener);
		List<EventListener> events =gameControllerTest.getEvents();
		if(events.isEmpty())
			assertTrue(true);
		else
			assertTrue(false);
			}
///**
//* Test method for {@link controller.GameController#actionPerformed(ActionEvent arg0)}.
//*/
	@Test
	public void testActionPerformed() {
		List<SpriteModel> spriteArray = new ArrayList<SpriteModel> ();
		spriteArray.add(0, selectedSpriteModel);
		SpriteList.getInstance().setToBeRemovedSpriteModels(spriteArray);
		gameControllerTest.actionPerformed(null);
		if(SpriteList.getInstance().getToBeRemovedSpriteModels() == null){
			assertTrue(false);
		}
		else
			assertTrue(true);
		spriteArray.clear();
	}

	@Test
	public void testGetCollisionEvents() {
		EventListener e1 = new CollisionEventListener();
		EventListener e2 = new CollisionEventListener();
		EventListener e3 = new NewFrameEventListener();
		EventListener e4 = new NewFrameEventListener();
		EventListener e5 = new KeyPressedEventListener();
		gameControllerTest.registerListener(e3);
		gameControllerTest.registerListener(e2);
		gameControllerTest.registerListener(e1);
		gameControllerTest.registerListener(e5);
		gameControllerTest.registerListener(e4);
		List<CollisionEventListener> list = gameControllerTest.getCollisionEvents();
		Assert.assertEquals(2, list.size());
	}
}

