package eventlistener;

import action.GameAction;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.newdawn.slick.util.Log;

import model.SpriteModel;
import multiplayer.Sender;
import utility.Constants;
import utility.SpriteList;
import utility.enums.playerModes;

public class KeyPressedEventListener implements EventListener, Serializable {

    private static final long serialVersionUID = 1L;
    private int keyRegistered;
    private double xSpeed;
    private double ySpeed;
    private String registeredObjectId;
    private String registeredGroupId;
    private GameAction action;

    @Override
    public void checkEvent(HashMap<String, Object> map) {

        Integer keyPressed = (Integer) map.get("keypressed");
        if (keyRegistered != keyPressed.intValue() || keyRegistered == 0) {
            return;
        }

        Collection<SpriteModel> allSpriteModel = SpriteList.getInstance().getSpriteList();
        for (SpriteModel sprite : allSpriteModel) {

            if ((sprite.getId().equalsIgnoreCase(registeredObjectId))
                    || (sprite.getGroupId().equalsIgnoreCase(registeredGroupId))) {
                sprite.setSpeedX(getxSpeed());
                sprite.setSpeedY(getySpeed());
                Log.info("The flag is ==================== "+Constants.isMultiplayer+ " "+Constants.isHost+" "+sprite.getMode());
                Log.info("In key pressed event");
                if (!Constants.isMultiplayer)
                	action.doAction(sprite);
                Log.info("The flag is "+((Constants.isMultiplayer && Constants.isHost && sprite.getMode().equals(playerModes.PLAYER1)) || (Constants.isMultiplayer && !Constants.isHost && sprite.getMode().equals(playerModes.PLAYER2))));
               if ((Constants.isMultiplayer && Constants.isHost && sprite.getMode().equals(playerModes.PLAYER1)) || (Constants.isMultiplayer && !Constants.isHost && sprite.getMode().equals(playerModes.PLAYER2))) {
               // if (!Constants.isMultiplayer || (Constants.isMultiplayer && Constants.isHost ) || (Constants.isMultiplayer && !Constants.isHost && sprite.getMode().equals(playerModes.PLAYER2))) {
            	   action.doAction(sprite);
            	   Log.info("action ======================== "+action);
                   Log.info("model =========================="+sprite.getEventListenerList().get(0));
                    Log.debug("Before sending :action = "+ action.toString()+"sprite is" + sprite.getId());
                    final String id = sprite.getId();
                    ThreadPoolExecutor pool;
                    pool = new ThreadPoolExecutor(10, 100, 30, TimeUnit.SECONDS, new PriorityBlockingQueue<Runnable>());

                    pool.execute(new Runnable(){

						@Override
						public void run() {
							   Sender sender = new Sender();
			                   sender.sendAsClient(action, id);
			                   Log.info("In key pressed event listener");
							
						}
                    	
                    });
                }
            }
        }
    }

    public int getKeyRegistered() {
        return keyRegistered;
    }

    public void setKeyRegistered(int keyRegistered) {
        this.keyRegistered = keyRegistered;
    }

    public String getRegisteredGroupId() {
        return registeredGroupId;
    }

    public void setRegisteredGroupId(String registeredGroupId) {
        this.registeredGroupId = registeredGroupId;
    }

    public String getRegisteredObjectId() {
        return registeredObjectId;
    }

    public void setRegisteredObjectId(String registeredObjectId) {
        this.registeredObjectId = registeredObjectId;
    }

    @Override
    public int getEventId() {
        return this.hashCode();
    }

    public GameAction getAction() {
        return action;
    }

    public void setAction(GameAction action) {
        this.action = action;
    }

    public double getySpeed() {
        return ySpeed;
    }

    public void setySpeed(double ySpeed) {
        this.ySpeed = ySpeed;
    }

    public double getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(double xSpeed) {
        this.xSpeed = xSpeed;
    }

    @Override
    public GameAction getGameAction() {
        return action;
    }
}
