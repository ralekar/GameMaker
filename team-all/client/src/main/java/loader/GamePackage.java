package loader;

import eventlistener.EventListener;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import model.SpriteModel;

public class GamePackage implements Serializable {

    private static final long serialVersionUID = 1L;
    private Collection<SpriteModel> spriteList;
    private List<EventListener> eventsForGameController;
    private List<EventListener> eventsForKeyController;
    private List<String> layers;
    private boolean isClockDisplayable;

    public GamePackage(Collection<SpriteModel> spriteList, List<EventListener> eventsForGameController, List<EventListener> eventsForKeyController, List<String> layers, boolean isDisplayable) {
        this.spriteList = spriteList;
        this.eventsForGameController = eventsForGameController;
        this.eventsForKeyController = eventsForKeyController;
        this.layers = layers;
        this.isClockDisplayable = isDisplayable;
    }

    public List<EventListener> getEventsForGameController() {
        return eventsForGameController;
    }

    public void setEventsForGameController(List<EventListener> eventsForGameController) {
        this.eventsForGameController = eventsForGameController;
    }

    public List<EventListener> getEventsForKeyController() {
        return eventsForKeyController;
    }

    public void setEventsForKeyController(List<EventListener> eventsForKeyController) {
        this.eventsForKeyController = eventsForKeyController;
    }

    public Collection<SpriteModel> getSpriteList() {
        return spriteList;
    }

    public void setSpriteList(Collection<SpriteModel> spriteList) {
        this.spriteList = spriteList;
    }

    public List<String> getLayers() {
        return layers;
    }

    public void setLayers(List<String> layers) {
        this.layers = layers;
    }

    public boolean isClockDisplayable() {
        return isClockDisplayable;
    }

    public void setClockDisplayable(boolean isClockDisplayable) {
        this.isClockDisplayable = isClockDisplayable;
    }
}