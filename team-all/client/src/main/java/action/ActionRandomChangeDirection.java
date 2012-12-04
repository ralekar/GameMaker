package action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

import model.SpriteModel;
import utility.SpriteList;

@SuppressWarnings("serial")
public class ActionRandomChangeDirection implements GameAction, Serializable {

    public ActionRandomChangeDirection(SpriteModel object) {
    }

    private boolean isColliding(SpriteModel spriteModel) {
    	ConcurrentLinkedQueue<SpriteModel> spriteModels = (ConcurrentLinkedQueue<SpriteModel>) SpriteList.getInstance().getSpriteList();
        for (SpriteModel model : spriteModels) {
            if (model.equals(spriteModel)) {
                continue;
            }
            if (spriteModel.intersects(model.getBoundingBox())) {
                return true;
            }
        }
        return false;
    }

    private void findBlock(SpriteModel spriteModel, double option1SpeedX, double option1SpeedY, ArrayList<Integer> availableOptions, int option) {

        spriteModel.setPosX(spriteModel.getPosX() + option1SpeedX);
        spriteModel.setPosY(spriteModel.getPosY() + option1SpeedY);
        if (isColliding(spriteModel)) {

            spriteModel.setPosX(spriteModel.getPosX() - option1SpeedX);
            spriteModel.setPosY(spriteModel.getPosY() - option1SpeedY);

        } else {
            availableOptions.add(new Integer(option));
        }
    }

    @Override
    public void doAction(SpriteModel spriteModel) {

        //Get the x and y position before trying to move the object.
        //If suppose the object collides with any other object, we will use this to reposition 
        //the object to the previous position before colliding
        double previousX = spriteModel.getPosX();
        double previousY = spriteModel.getPosY();

        //Get the x and y velocity in which it is moving
        double previousSpeedX = spriteModel.getSpeedX();
        double previousSpeedY = spriteModel.getSpeedY();

        double tempSpeedX = 0;
        double tempSpeedY = 0;

        //store the x and y velocity in a temp variable to be used in the logic
        //where we find which direction to move on
        if (previousSpeedX != 0) {
            tempSpeedX = previousSpeedX;
            tempSpeedY = previousSpeedX;
        } else if (previousSpeedY != 0) {
            tempSpeedX = previousSpeedY;
            tempSpeedY = previousSpeedY;
        }

        //Its time to check whether the object collides.
        //So, move the object based on the x and y velocity
        spriteModel.setPosX(spriteModel.getPosX() + spriteModel.getSpeedX());
        spriteModel.setPosY(spriteModel.getPosY() + spriteModel.getSpeedY());

        //Check whether the object is colliding in the given direction
        if (isColliding(spriteModel)) {
            //Collision has happened. Move the object to its previous position.
            spriteModel.setPosX(previousX);
            spriteModel.setPosY(previousY);
        } else {
            //No obstacles in the path in which the object is moving.
            return;
        }




        ArrayList<Integer> availableOptions = new ArrayList<Integer>();

        //Find in what sides the block is present.
        findBlock(spriteModel, 0, -tempSpeedY, availableOptions, 1);
        findBlock(spriteModel, tempSpeedX, 0, availableOptions, 2);
        findBlock(spriteModel, 0, tempSpeedY, availableOptions, 3);
        findBlock(spriteModel, -tempSpeedX, 0, availableOptions, 4);

        if (availableOptions.size() <= 0) {
            return;
        }


        //Choose one of the available options randomly
        Random rand = new Random();
        int randomNumber = rand.nextInt(availableOptions.size());
        int option = availableOptions.get(randomNumber);

        if (option == 1) {
            previousSpeedX = 0;
            previousSpeedY = -tempSpeedY;
        } else if (option == 2) {
            previousSpeedX = tempSpeedX;
            previousSpeedY = 0;
        } else if (option == 3) {
            previousSpeedX = 0;
            previousSpeedY = tempSpeedY;
        } else if (option == 4) {
            previousSpeedX = -tempSpeedX;
            previousSpeedY = 0;
        }

        //Set the values to the model object
        spriteModel.setPosX(spriteModel.getPosX() + previousSpeedX);
        spriteModel.setPosY(spriteModel.getPosY() + previousSpeedY);
        spriteModel.setSpeedX(previousSpeedX);
        spriteModel.setSpeedY(previousSpeedY);
    }
}
