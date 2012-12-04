/**
 * 
 */
package action;

import java.io.Serializable;

import utility.Score;
import model.SpriteModel;


@SuppressWarnings("serial")
public class ActionIncreaseScore implements GameAction,Serializable {

    /* (non-Javadoc)
     * @see action.GameAction#doAction(model.SpriteModel)
     */
    int scoreModificationValue = 1;
    
    public ActionIncreaseScore(int scoreModificationValue){
	this.scoreModificationValue = scoreModificationValue;
    }
    
    @Override
    public void doAction(SpriteModel model) {
	Score.getInstance().modifyBy(scoreModificationValue);
    }

}
