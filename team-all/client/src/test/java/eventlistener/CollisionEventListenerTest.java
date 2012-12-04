package eventlistener;

import static org.junit.Assert.*;


import model.SpriteModel;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import utility.ResizeHelper;
import utility.SpriteList;
import view.GameMakerView;

import action.ActionChangeSpeed;
import action.GameAction;

public class CollisionEventListenerTest {
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {		
	}

   @Test
	public void testCheckEvent() {
	    SpriteList.getInstance().getSpriteList();
		ResizeHelper.getInstance().reset();
		SpriteModel selectedSpriteModel = new SpriteModel(100, 100, 10, 10, 20, 30, "","");
		SpriteModel secondarySpriteModel= new SpriteModel(100, 100, 10, 10, 100, 100, "","");
		
		selectedSpriteModel.setGroupId("Group1");
		secondarySpriteModel.setGroupId("Group2");		

		SpriteList.getInstance().addSprite(selectedSpriteModel);
		SpriteList.getInstance().addSprite(secondarySpriteModel);
		CollisionEventListener collisionListener = new CollisionEventListener();
		collisionListener.setRegisteredGroupId1(selectedSpriteModel.getGroupId());
		collisionListener.setRegisteredGroupId2(secondarySpriteModel.getGroupId());
		GameAction action= new ActionChangeSpeed(20,20);
		collisionListener.setAction(action);

		collisionListener.checkEvent(null);
		if(selectedSpriteModel.getSpeedX()==20 &&selectedSpriteModel.getSpeedY()==20)
			 assertTrue(true);
		else
			 assertTrue(false);
	}

}
