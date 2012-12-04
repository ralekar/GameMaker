package eventlistener;

import action.GameAction;
import java.util.HashMap;

public interface EventListener {

    public abstract void checkEvent(HashMap<String, Object> map);

    public int getEventId();

    public GameAction getGameAction();
}
