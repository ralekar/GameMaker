package action;

import static org.junit.Assert.*;

import model.SpriteModel;

import org.junit.Test;

public class ActionChangeSpeedTest {

	@Test
	public void test() {
		double xSpeed = 10;
		double ySpeed = 10;
		double initialPosX = 100;
		double initialPosY = 100;
		double width = 100;
		double height = 100;
		
		int incrementXSpeedValue = 20;
		int incrementYSpeedValue = 20;
		
		//Creating a model
		SpriteModel model = new SpriteModel(initialPosX, initialPosY, xSpeed, ySpeed, width, height, "","");
		//creating a change visibility action
		ActionChangeSpeed actionChanged = new ActionChangeSpeed(incrementXSpeedValue,incrementYSpeedValue);
		//Invoking the action on the model
		actionChanged.doAction(model);
		//Check whether the visibility has changed
		if((model.getSpeedX() == incrementXSpeedValue) &&
				(model.getSpeedY() == incrementYSpeedValue))
			 assertTrue(true);
		else
			assertTrue(false);
	}

}
