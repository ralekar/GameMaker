package action;

import java.io.Serializable;

import model.SpriteModel;

@SuppressWarnings("serial")
public class ActionBackToLastPosition implements GameAction,Serializable {

	@Override
    public void doAction(SpriteModel object) {
        object.setPosX(object.getPreviousX());
        object.setPosY(object.getPreviousY());
    }
}
