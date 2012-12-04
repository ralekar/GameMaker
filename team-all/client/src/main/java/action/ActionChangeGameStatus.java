package action;

import java.awt.Rectangle;
import java.io.Serializable;
import java.util.Collection;
import javax.swing.JOptionPane;

import facade.Facade;
import model.SpriteModel;
import utility.Helper;
import utility.SpriteList;

@SuppressWarnings("serial")
public class ActionChangeGameStatus implements GameAction,Serializable {

    private final boolean YES = true;
    private final boolean NO = false;

    private boolean isGameStopCommandIssued = NO;
    
    // This is added to suppress the message for unit testing.
    private boolean showMessage = true;


	String message = "";

    public ActionChangeGameStatus(boolean isWin) {
	if (isWin)
	    message = "You Win";
	else
	    message = "You Lose";
    }

    @Override
    public void doAction(SpriteModel model) {
	double xSpeed = model.getSpeedX();
	double ySpeed = model.getSpeedY();

	Rectangle xReversed = model.getBoundingBox();
	Rectangle yReversed = model.getBoundingBox();
	xReversed.x -= xSpeed;
	yReversed.y -= ySpeed;
	Collection<SpriteModel> spriteModels = SpriteList.getInstance().getSpriteList();
	for (SpriteModel obj : spriteModels) {
		if(obj.getClass().equals(spriteModels.getClass()))
		continue;
	    if (model.intersects(obj.getBoundingBox())) {
		/*
		 * used a boolean isGameStopCommandIssued so that the
		 * showMessageDialog is not executed again for this for loop.
		 */
		if (!isGameStopCommandIssued  ) {
			Facade facade = Helper.getsharedHelper().getFacade();
		    if (!xReversed.intersects(obj.getBoundingBox())) {
		        if(showMessage == true)		    	
			      JOptionPane.showMessageDialog(null, message);
		    
		        facade.stopGame();
			    isGameStopCommandIssued = YES;
		    }
		    if (!yReversed.intersects(obj.getBoundingBox())) {
		    	if(showMessage == true)	
			        JOptionPane.showMessageDialog(null, message);
		    	facade.stopGame();
			    isGameStopCommandIssued = YES;
		    } else if (yReversed.intersects(obj.getBoundingBox()) && xReversed.intersects(obj.getBoundingBox())) {
		        if(showMessage == true)	
			        JOptionPane.showMessageDialog(null, message);
		        facade.stopGame();
			    isGameStopCommandIssued = YES;
		    }
		}

	    }
	}
    }
    

    public boolean isShowMessage() {
		return showMessage;
	}

	public void setShowMessage(boolean showMessage) {
		this.showMessage = showMessage;
	}

}
