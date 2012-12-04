package eventlistener;

import action.GameAction;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import model.SpriteModel;
import utility.Helper;
import utility.SpriteList;
import view.GamePanel;

public class OutOfBoundaryEventListener implements EventListener, Serializable {

    private static final long serialVersionUID = 1L;
    private String registeredObjectId;
    private String registeredGroupId;
    private GameAction action;

    @Override
    public void checkEvent(HashMap<String, Object> map) {
    	GamePanel gamePanel = Helper.getsharedHelper().getGamePanel();
        Collection<SpriteModel> allSpriteModel = SpriteList.getInstance()
                .getSpriteList();
        for (SpriteModel model : allSpriteModel) {
            if ((model.getId()
                    .equalsIgnoreCase(registeredObjectId))
                    || (model.getGroupId()
                    .equalsIgnoreCase(registeredGroupId))) {
                double posX = model.getPosX();
                double speedX = model.getSpeedX();
                double posY = model.getPosY();
                double speedY = model.getSpeedY();
                if (speedX > 0) {
                    if ((posX - model.getWidth()) > gamePanel.getWidth()) {
                        action.doAction(model);
                    }
                } else {
                    if (posX + model.getWidth() < 0) {
                        action.doAction(model);
                    }
                }
                if (speedY > 0) {
                    if ((posY - model.getHeight()) > gamePanel.getHeight()) {
                        action.doAction(model);
                    }
                } else {
                    if (posY + model.getHeight() < 0) {
                        action.doAction(model);
                    }
                }

            }
        }

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

    @Override
    public GameAction getGameAction() {
        return action;
    }
}
