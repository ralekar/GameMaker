package action;

import java.io.Serializable;

import model.SpriteModel;

@SuppressWarnings("serial")
public class ActionStartOver implements GameAction,Serializable {
	
	private double startX, startY;

	@Override
	public void doAction(SpriteModel model) {
		model.setPosX(startX);
		model.setPosY(startY);

	}

	public double getStartX() {
		return startX;
	}

	public void setStartX(double startX) {
		this.startX = startX;
	}

	public double getStartY() {
		return startY;
	}

	public void setStartY(double startY) {
		this.startY = startY;
	}


}
