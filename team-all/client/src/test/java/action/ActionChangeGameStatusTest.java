package action;

import static org.junit.Assert.*;

import java.awt.Rectangle;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utility.Constants;
import utility.Helper;
import utility.ResizeHelper;
import utility.SpriteList;
import view.GameMakerView;

import eventlistener.CollisionEventListener;
import facade.Facade;
import gameMaker.gameMaker;

import model.SpriteModel;

public class ActionChangeGameStatusTest {
	
	   private  SpriteModel selectedSpriteModel;
	   private  SpriteModel secondarySpriteModel;
	   private  CollisionEventListener collisionListener;
	   private  ActionChangeGameStatus action;
	   private GameMakerView gameMakerView;
	   private Facade facade;

	@Before
	public void setUp() throws Exception {
		gameMakerView = new GameMakerView(Constants.FRAME_WIDTH,Constants.FRAME_HEIGHT);
		facade = Helper.getsharedHelper().getFacade();
		gameMakerView.reset();
		ResizeHelper.getInstance().reset();
      	selectedSpriteModel = new SpriteModel(190, 190, 10, 10, 100, 100, "","");
		secondarySpriteModel= new SpriteModel(100, 100, 10, 10, 100, 200, "","");
		secondarySpriteModel.setGroupId("Group1");
		SpriteList.getInstance().addSprite(selectedSpriteModel);
		SpriteList.getInstance().addSprite(secondarySpriteModel);
		collisionListener = new CollisionEventListener();
		collisionListener.setRegisteredGroupId1(selectedSpriteModel.getGroupId());
		collisionListener.setRegisteredGroupId2(secondarySpriteModel.getGroupId());
		action= new ActionChangeGameStatus(true);
		
	}
	
	@Test
	public void testDoAction() {
		facade.startGame();
		action.setShowMessage(false);
		action.doAction(selectedSpriteModel);		
       	assertTrue(!facade.getTimer().isRunning());	
       	
	}
	
	
	@After
	public void tearDown() throws Exception {
		facade.stopGame();
		gameMakerView.reset();
		
	}

	
}
