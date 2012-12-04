package action;

import static org.junit.Assert.*;

import model.SpriteModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utility.Constants;
import utility.Helper;
import utility.ResizeHelper;
import utility.SpriteList;
import view.GameMakerView;
import eventlistener.CollisionEventListener;

public class ActionRandomChangeDirectionTest {
	  private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ActionRandomChangeDirectionTest.class);

	   private  SpriteModel selectedSpriteModel;
	   private  SpriteModel secondarySpriteModel;
	   private  CollisionEventListener collisionListener;
	   private  GameAction action;
	   private GameMakerView gameMakerView ;
	   
	@Before
	public void setUp() throws Exception {
		gameMakerView = new GameMakerView(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
		gameMakerView.reset();
		ResizeHelper.getInstance().reset();
      	selectedSpriteModel = new SpriteModel(100, 195, 10, -10, 100, 100, "","");
		secondarySpriteModel= new SpriteModel(100, 100, 10, 10, 100, 100, "","");
		secondarySpriteModel.setGroupId("Group1");
		SpriteList.getInstance().addSprite(selectedSpriteModel);
		SpriteList.getInstance().addSprite(secondarySpriteModel);
		collisionListener = new CollisionEventListener();
		collisionListener.setRegisteredGroupId1(selectedSpriteModel.getGroupId());
		collisionListener.setRegisteredGroupId2(secondarySpriteModel.getGroupId());
		action= new ActionRandomChangeDirection(selectedSpriteModel);
	}

	@After
	public void tearDown() throws Exception {
		gameMakerView.reset();
	}

	@Test
	public void testDoAction() {
		//fail("Not yet implemented");
		double previousSpeedX=selectedSpriteModel.getSpeedX();
		double previousSpeedY=selectedSpriteModel.getSpeedY();
		action.doAction(selectedSpriteModel);
		double newSpeedX=selectedSpriteModel.getSpeedX();
		double newSpeedY=selectedSpriteModel.getSpeedY();
		if(newSpeedX ==0 || newSpeedY == 0)			
		    assertTrue(true);
		else
			assertTrue(false);
	}

}
