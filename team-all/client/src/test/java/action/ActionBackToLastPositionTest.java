package action;

import static org.junit.Assert.*;

import model.SpriteModel;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ActionBackToLastPositionTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDoAction() {
		double xSpeed = 10;
		double ySpeed = 10;
		double initialPosX = 100;
		double initialPosY = 100;
		double width = 100;
		double height = 100;
		
		//Creating a model
		SpriteModel model = new SpriteModel(initialPosX, initialPosY, xSpeed, ySpeed, width, height, "","");
		model.setPreviousX(90);
		model.setPreviousY(90);
		//Creating a Move action
		ActionBackToLastPosition actionBackToLastPositionTest = new ActionBackToLastPosition();
		//Invoking the action on the model
		actionBackToLastPositionTest.doAction(model);
		//Checking whether the models position is moved according to xpeed and yspeed
		if(model.getPosX() == 90&&model.getPosX() == 90) 				
			 assertTrue(true);
		else
			assertTrue(false);
	}

}
