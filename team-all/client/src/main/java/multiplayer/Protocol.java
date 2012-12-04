package multiplayer;

import action.GameAction;
import game.engine.slick2d.player.GameEngineController;
import java.util.Collection;
import java.util.HashMap;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import loader.GamePackage;
import model.SpriteModel;
import utility.Helper;
import utility.SpriteList;
import view.GamePlayerView;

public class Protocol {

    private ObjectMessage msg;
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(Protocol.class);

    public ObjectMessage createDataAsHost() {
        GamePlayerView gamePlayerView = (GamePlayerView) Helper.getsharedHelper().getGamePlayerView();
        GameEngineController gameEngine = gamePlayerView.getGameEnginePanel().getGame();
        if (gameEngine != null) {
            LOG.info("Game engine is not nul =================================");
        }
        if (SpriteList.getInstance().getSpriteList().isEmpty()) {
            LOG.info("Sprite list is empty here -----------------");
        }
        GamePackage game = new GamePackage(SpriteList.getInstance().getSpriteList(), gameEngine.getEventsForGameController(), gameEngine.getKeyEvents(), null, false);
        if (game != null) {
            LOG.info("Game  is not nul =================================");
        }
        try {
            msg = SessionFactory.getInstanceOf().getSession().createObjectMessage();
            msg.setObject(game);
            msg.setJMSType("Sending as Host");
        } catch (JMSException e) {
            LOG.info("sending falied as host");
        }
        //}
        return msg;

    }

    public ObjectMessage createData(GameAction action, String spriteID) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("action", action);
        map.put("id", spriteID);
        //map.put(action, spriteID);
        double speedX;
        double speedY;
        for (SpriteModel sprite: SpriteList.getInstance().getSpriteList())
        {
        if (sprite.getId().equals(spriteID))
        {
        	 speedX = sprite.getSpeedX();
        	 speedY = sprite.getSpeedY();
        	 map.put("speedx", speedX);
             map.put("speedy", speedY);
        }
        }
        
        try {


            msg = SessionFactory.getInstanceOf().getSession().createObjectMessage();
            msg.setObject(map);
            msg.setJMSType("Sending Actions");
        } catch (JMSException e) {
            LOG.info("sending Actions");
        }
        return msg;
    }

    public ObjectMessage createAcknowledgement(String data) {
        try {
            msg = SessionFactory.getInstanceOf().getSession().createObjectMessage(data);
        } catch (JMSException e) {
            LOG.info("creating Acknowledgement failed");
        }
        return msg;
    }

    public void setGameState(GamePackage game) {
        GamePlayerView gamePlayerView = (GamePlayerView) Helper.getsharedHelper().getGamePlayerView();

        LOG.debug("load done");

        gamePlayerView.getGameEnginePanel().newGame(game);

    }

    public void setMultiplayerAction(HashMap<String, Object> map) {
        String id = null;
        GameAction action = null;
        double speedX=0.0;
        double speedY=0.0;
        for (String key : map.keySet()) {
            if (key.equals("action"))
            {
            	action = (GameAction)map.get(key);
            	
            }
            else if (key.equals("speedx"))
            {
            	speedX = (Double) map.get(key);
            	
            }
            else if (key.equals("speedy"))
            {
            	speedY = (Double) map.get(key);
            	
            }
            else if (key.equals("id"))
            {
            	id = (String) map.get(key);
            	
            }
        }
        // Helper.getsharedHelper().getGamePlayerView().getGameEnginePanel().repaint();

        Collection<SpriteModel> collection = SpriteList.getInstance().getSpriteList();
        SpriteModel actionModel = null;
        for (SpriteModel sprite : collection) {
            if (sprite.getId().equals(id)) {
                actionModel = sprite;
                break;
            }
        }
        actionModel.setSpeedX(speedX);
        actionModel.setSpeedY(speedY);
        action.doAction(actionModel);
        LOG.info("Setting multiplayer action");
    }

    public ObjectMessage createStartSignal(String data) {
        try {
            msg = SessionFactory.getInstanceOf().getSession().createObjectMessage(data);
        } catch (JMSException e) {
            LOG.info("creating start signal failed");
        }
        return msg;
    }
}
