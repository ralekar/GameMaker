package action;

import static org.junit.Assert.*;

import model.SpriteModel;

import org.junit.Test;

import action.ActionMove;

import utility.ResizeHelper;

public class ActionMoveTest {

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
		//Creating a Move action
		ActionMove actionMove = new ActionMove();
		//Invoking the action on the model
		actionMove.doAction(model);
		//Checking whether the models position is moved according to xpeed and yspeed
		if((model.getPosX()-initialPosX == xSpeed) &&
				(model.getPosY()-initialPosY == ySpeed))
			 assertTrue(true);
		else
			assertTrue(false);
			
	}

}
