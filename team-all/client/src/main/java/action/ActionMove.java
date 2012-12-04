package action;

import java.io.Serializable;



import model.SpriteModel;


@SuppressWarnings("serial")
public class ActionMove implements GameAction,Serializable {
	private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ActionMove.class);
    
	  @Override
    public void doAction(SpriteModel object) {

    	//LOG.info("In action move[[[[[[[[[[[[[[[[[[[[[[[");

        object.setPosX(object.getPosX() + object.getSpeedX());
        object.setPosY(object.getPosY() + object.getSpeedY());

       // LOG.info("Object x pos is  "+object.getPosX()+"Onject y pos is  "+object.getPosY());
}
}
