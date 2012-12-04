package action;

import java.io.Serializable;

import model.SpriteModel;

@SuppressWarnings("serial")
public class ActionChangeSpeed implements GameAction,Serializable {

    private double newSpeedX, newSpeedY;

    public ActionChangeSpeed(double newSpeedX, double newSpeedY) {
        super();
        this.newSpeedX = newSpeedX;
        this.newSpeedY = newSpeedY;
    }

    @Override
    public void doAction(SpriteModel object) {
        object.setSpeedX(newSpeedX);
        object.setSpeedY(newSpeedY);
    }
}
