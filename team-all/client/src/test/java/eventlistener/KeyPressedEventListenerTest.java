/**
 * 
 */
package eventlistener;

import static org.junit.Assert.*;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

import model.SpriteModel;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import utility.SpriteList;
import view.GameMakerView;

import action.ActionMove;
import action.GameAction;
import controller.KeyListenerController;


public class KeyPressedEventListenerTest {

	/**
	 * @throws java.lang.Exception
	 */


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
		SpriteList.getInstance().getSpriteList().clear();
			}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		SpriteList.getInstance().getSpriteList().clear();
	}

	/**
	 * Test method for {@link eventlistener.KeyPressedEventListener#checkEvent(java.util.HashMap)}.
	 */
	@Test
	public void testCheckEvent() {
		SpriteModel selectedSpriteModel = new SpriteModel(100, 100, 20, 20, 100, 100, "","");
		 selectedSpriteModel.setGroupId("Group1");
		SpriteList.getInstance().addSprite(selectedSpriteModel);
		KeyPressedEventListener keyListener = new KeyPressedEventListener();
		keyListener.setRegisteredGroupId(selectedSpriteModel.getGroupId());
		keyListener.setRegisteredObjectId(selectedSpriteModel.getId()); 
		
		keyListener.setxSpeed(selectedSpriteModel.getSpeedX());
		keyListener.setySpeed(selectedSpriteModel.getSpeedY());
		
		keyListener.setKeyRegistered(KeyEvent.VK_SHIFT);
		
		GameAction action = new ActionMove();
		keyListener.setAction(action);
		
		int pressed = KeyEvent.VK_SHIFT;
		HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("keypressed", new Integer(pressed));
		keyListener.checkEvent(map);
		if(selectedSpriteModel.getPosY()==120&&selectedSpriteModel.getPosX()==120)
			 assertTrue(true);
		else
			 assertTrue(false);
		
	
	}

}
