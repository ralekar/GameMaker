package action;

import java.io.Serializable;
import java.util.Queue;
import utility.SpriteList;

import model.SpriteModel;

@SuppressWarnings("serial")
public class ActionBounce implements GameAction, Serializable {

    private boolean randomBounce;

    public ActionBounce(boolean randomBounce) {
        this.setRandomBounce(randomBounce);
    }

    @Override
    public void doAction(SpriteModel spriteModel) {

        double xSpeed = spriteModel.getSpeedX();
        double ySpeed = spriteModel.getSpeedY();

        Queue<SpriteModel> spriteModels = (Queue<SpriteModel>) SpriteList.getInstance().getSpriteList();

//        for (SpriteModel model : spriteModels) {
//            if (model.equals(spriteModel)) {
//                continue;
//            }
//            if (spriteModel.intersects(model.getBoundingBox())) {
//
//                /*
//                 * A9 Team 3 FATWVNINC-57
//                 * 
//                 * The code that decides the change in direction of the object,
//                 * was flawed. Commented out that code and added modified code
//                 * snippet. if((model.getPosY()<spriteModel.getPosY())
//                 * &&(model.getPosY
//                 * ()+model.getHeight()>spriteModel.getPosY()+spriteModel
//                 * .getHeight())){ spriteModel.setSpeedX(xSpeed*-1); } else
//                 * if((model.getPosX()<spriteModel.getPosX()) &&
//                 * (model.getPosX()
//                 * +model.getWidth()>spriteModel.getPosX()+spriteModel
//                 * .getWidth())){ spriteModel.setSpeedY(ySpeed*-1); }
//                 */
//                if ((model.getPosY()  < spriteModel.getPosY() + spriteModel.getHeight()) && (model.getPosY() + model.getHeight() > spriteModel.getPosY())) {
//                    // spriteModel.setSpeedX(xSpeed*-1);
//                    spriteModel.setSpeedY(ySpeed * -1);
//                } else if ((model.getPosY() > spriteModel.getPosY()) && (model.getPosY() < spriteModel.getPosY() + spriteModel.getHeight())) {
//                    // spriteModel.setSpeedX(xSpeed*-1);
//                    spriteModel.setSpeedY(ySpeed * -1);
//                }
//
//                if ((model.getPosX() < spriteModel.getPosX()) && (model.getPosX() + model.getWidth() > spriteModel.getPosX())) {
//                    // spriteModel.setSpeedY(ySpeed*-1);
//                    spriteModel.setSpeedX(xSpeed * -1);
//                } else if ((model.getPosX() > spriteModel.getPosX()) && (model.getPosX() < spriteModel.getPosX() + spriteModel.getWidth())) {
//                    // spriteModel.setSpeedY(ySpeed*-1);
//                    spriteModel.setSpeedX(xSpeed * -1);
//                }
//
//            }
//        }
//
//    }
        for(SpriteModel model : spriteModels){
			if(model.equals(spriteModel)) continue;
			if(spriteModel.intersects(model.getBoundingBox())){

				if((model.getPosY()<spriteModel.getPosY()) &&
						(model.getPosY()+model.getHeight()>spriteModel.getPosY()+spriteModel.getHeight())){
					spriteModel.setSpeedX(xSpeed*-1);
				}
				else if((model.getPosX()<spriteModel.getPosX()) &&
						(model.getPosX()+model.getWidth()>spriteModel.getPosX()+spriteModel.getWidth())){
					spriteModel.setSpeedY(ySpeed*-1);
				}
				
			}
		}
	
    }

    public boolean isRandomBounce() {
        return randomBounce;
    }

    public void setRandomBounce(boolean randomBounce) {
        this.randomBounce = randomBounce;
    }
}
