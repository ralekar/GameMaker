package action;

import static org.junit.Assert.*;

import model.SpriteModel;

import org.junit.Test;

import utility.Constants;

public class ActionRotateTest {

	@Test
	public void test() {
		double xSpeed = 10;
		double ySpeed = 10;
		double initialPosX = 100;
		double initialPosY = 100;
		double width = 100;
		double height = 100;
		
		//Creating a model
		SpriteModel model = new SpriteModel(initialPosX, initialPosY, xSpeed, ySpeed, width, height, "","");
		double initialHeading = model.getHeading();
		//Creating a Move action
		ActionRotate actionRotate = new ActionRotate("Anticlockwise");
		//Invoking the action on the model
		actionRotate.doAction(model);
		//Checking whether the models position is moved according to xpeed and yspeed
		if(Math.abs(model.getHeading() - initialHeading) == Constants.HEADING_AMOUNT)
			 assertTrue(true);
		else
			assertTrue(false);
	}

}
