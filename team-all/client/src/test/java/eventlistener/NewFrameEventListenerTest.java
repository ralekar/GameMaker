/**
 * 
 */
package eventlistener;

import static org.junit.Assert.*;

import model.SpriteModel;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import utility.SpriteList;
import view.GameMakerView;

import action.ActionBounce;
import action.ActionMove;
import action.GameAction;


public class NewFrameEventListenerTest {

	/**
	 * @throws java.lang.Exception
	 */

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
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link eventlistener.NewFrameEventListener#checkEvent(java.util.HashMap)}.
	 */
	@Test
	public void testCheckEvent() {
		
		SpriteModel selectedSpriteModel = new SpriteModel(100, 100, 20, 20, 100, 100, "","");
		SpriteModel selectedSpriteModelTest = new SpriteModel(190, 190, 20, 20, 100, 100, "","");

		SpriteList.getInstance().addSprite(selectedSpriteModel);
		SpriteList.getInstance().addSprite(selectedSpriteModelTest);

		NewFrameEventListener newFrameListener = new NewFrameEventListener();
		newFrameListener.setRegisteredGroupId(selectedSpriteModel.getGroupId());
		newFrameListener.setRegisteredObjectId(selectedSpriteModel.getId()); 
		GameAction action = new ActionMove();
		newFrameListener.setAction(action);

		
		double previousX = selectedSpriteModel.getPosX();
		double previousY = selectedSpriteModel.getPosY();
		
		newFrameListener.checkEvent(null);
		
		if(selectedSpriteModel.getPosX()-previousX==20&&selectedSpriteModel.getPosY()-previousY==20)
			 assertTrue(true);
		else
			 assertTrue(false);
	}

}
