package action;

import java.io.Serializable;

import utility.Constants;
import model.SpriteModel;

@SuppressWarnings("serial")
public class ActionRotate implements GameAction,Serializable{

	private String direction;
	
	public ActionRotate(String direction) {
		this.direction = direction;
	}
	
	@Override
	public void doAction(SpriteModel model) {
		if(direction.equalsIgnoreCase("Clockwise"))
			model.setHeading(model.getHeading()+Constants.HEADING_AMOUNT);
		else if(direction.equalsIgnoreCase("Anticlockwise"))
			model.setHeading(model.getHeading()-Constants.HEADING_AMOUNT);
	}

}
