package action;

import static org.junit.Assert.*;

import model.SpriteModel;

import org.junit.Test;

public class ActionChangeVisibilityTest {

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
		//creating a change visibility action
		ActionChangeVisibility actionChangeVisibility = new ActionChangeVisibility(false);
		//Invoking the action on the model
		actionChangeVisibility.doAction(model);
		//Check whether the visibility has changed
		if(!model.isVisible())
			 assertTrue(true);
		else
			assertTrue(false);
	}

}
