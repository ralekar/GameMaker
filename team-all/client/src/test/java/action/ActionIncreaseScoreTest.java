package action;

import static org.junit.Assert.*;

import model.SpriteModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utility.Score;
import utility.SpriteList;
import view.GameMakerView;

public class ActionIncreaseScoreTest {

	@Before
	public void setUp() throws Exception {
	   SpriteList.getInstance().getSpriteList().clear();
	}

	@After
	public void tearDown() throws Exception {
		SpriteList.getInstance().getSpriteList().clear();
	}
	
	@Test
	public void testDoAction() {
		double xSpeed = 10;
		double ySpeed = 10;
		double initialPosX = 100;
		double initialPosY = 100;
		double width = 100;
		double height = 100;
		
		Score.getInstance().setScore(0);
		//Creating a model
		SpriteModel model = new SpriteModel(initialPosX, initialPosY, xSpeed, ySpeed, width, height, "","");
		ActionIncreaseScore actionIncrease = new ActionIncreaseScore(2);
		int oldScore = Score.getInstance().getScore();
		actionIncrease.doAction(model);
		int newScore = Score.getInstance().getScore();
		assertEquals(newScore - oldScore,2);
		//post processing
		Score.getInstance().setScore(0);

	}

}
