package eventlistener;

import static org.junit.Assert.*;

import model.SpriteModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import action.ActionChangeSpeed;
import action.ActionMove;
import action.GameAction;

import utility.Constants;
import utility.SpriteList;
import view.GameMakerView;
import java.lang.Math;

public class OutOfBoundaryEventListenerTest {
    
	@Before
	public void setUp() throws Exception {
		GameMakerView gameMakerView = new GameMakerView(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
		SpriteList.getInstance().getSpriteList().clear();    		
	}

	@After
	public void tearDown() throws Exception {
	
	}

	@Test
	public void testRightVerticleWall() {
		double width = 20;
		double height = 100;
		double xspeed = 20;
		double yspeed = 20;
		// to remove the dependency on the GameMakerView - removing it <---OutOfBoundaryEventListener still uses GameMakerView -Matt
		//double posX = GameMakerView.getInstance().getGamePanel().getWidth() + width +10;
		double posX = 800;
		double posY = 100;
		SpriteModel selectedSpriteModel = new SpriteModel( posX, posY, xspeed, yspeed, width, height, "","");
		SpriteList.getInstance().addSprite(selectedSpriteModel);
		OutOfBoundaryEventListener outOfBoundaryEventListener = new OutOfBoundaryEventListener();
		outOfBoundaryEventListener.setRegisteredGroupId(selectedSpriteModel.getGroupId());
		outOfBoundaryEventListener.setRegisteredObjectId(selectedSpriteModel.getId()); 
		GameAction action = new ActionChangeSpeed(-xspeed, yspeed);
		outOfBoundaryEventListener.setAction(action);
		outOfBoundaryEventListener.checkEvent(null);
		//accounting for floating point errors comparison errors
		assertTrue(Math.abs(selectedSpriteModel.getSpeedX()+xspeed) < 10e-12);	
		
	}
	
	@Test
	public void testLeftVerticleWall() {
		double width = 20;
		double height = 100;
		double xspeed = -20;
		double yspeed = 20;
		double posX = -width-10;
		double posY = 100;
		SpriteModel selectedSpriteModel = new SpriteModel( posX, posY, xspeed, yspeed, width, height, "","");
		SpriteList.getInstance().addSprite(selectedSpriteModel);
		OutOfBoundaryEventListener outOfBoundaryEventListener = new OutOfBoundaryEventListener();
		outOfBoundaryEventListener.setRegisteredGroupId(selectedSpriteModel.getGroupId());
		outOfBoundaryEventListener.setRegisteredObjectId(selectedSpriteModel.getId()); 
		GameAction action = new ActionChangeSpeed(-xspeed, yspeed);
		outOfBoundaryEventListener.setAction(action);
		outOfBoundaryEventListener.checkEvent(null);
		//accounting for floating point errors comparison errors
		assertTrue(Math.abs(selectedSpriteModel.getSpeedX()+xspeed) < 10e-12);		
	}
	
	@Test
	public void testBottomHorizontalFloor() {
		double width = 20;
		double height = 100;
		double xspeed = 20;
		double yspeed = 20;
		double posX = 100;
		//to remove the dependency on GameMakerView
		//double posY = GameMakerView.getInstance().getGamePanel().getHeight() + height + 10;
		double posY = 800;
		SpriteModel selectedSpriteModel = new SpriteModel( posX, posY, xspeed, yspeed, width, height, "","");
		SpriteList.getInstance().addSprite(selectedSpriteModel);
		OutOfBoundaryEventListener outOfBoundaryEventListener = new OutOfBoundaryEventListener();
		outOfBoundaryEventListener.setRegisteredGroupId(selectedSpriteModel.getGroupId());
		outOfBoundaryEventListener.setRegisteredObjectId(selectedSpriteModel.getId());
		GameAction action = new ActionChangeSpeed(xspeed, -yspeed);
		outOfBoundaryEventListener.setAction(action);
		outOfBoundaryEventListener.checkEvent(null);
		//accounting for floating point errors comparison errors
		assertTrue(Math.abs(selectedSpriteModel.getSpeedY()+yspeed) < 10e-12);		
	}
	
	@Test
	public void testTopHorizontalRoof() {
		double width = 20;
		double height = 100;
		double xspeed = 20;
		double yspeed = -20;
		double posX = 100;
		double posY = -height -10;
		SpriteModel selectedSpriteModel = new SpriteModel( posX, posY, xspeed, yspeed, width, height, "","");
		SpriteList.getInstance().addSprite(selectedSpriteModel);
		OutOfBoundaryEventListener outOfBounaryEventListener = new OutOfBoundaryEventListener();
		outOfBounaryEventListener.setRegisteredGroupId(selectedSpriteModel.getGroupId());
		outOfBounaryEventListener.setRegisteredObjectId(selectedSpriteModel.getId()); 
		GameAction action = new ActionChangeSpeed(xspeed, -yspeed);
		outOfBounaryEventListener.setAction(action);
		outOfBounaryEventListener.checkEvent(null);
		//accounting for floating point errors comparison errors
		assertTrue(Math.abs(selectedSpriteModel.getSpeedY()+yspeed) < 10e-12);		
	}
}
