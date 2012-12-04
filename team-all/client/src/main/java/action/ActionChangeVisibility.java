package action;

import java.io.Serializable;

import model.SpriteModel;

@SuppressWarnings("serial")
public class ActionChangeVisibility implements GameAction,Serializable {

    private boolean visible;

    public ActionChangeVisibility(boolean visible) {
        super();
        this.visible = visible;
    }

    @Override
    public void doAction(SpriteModel model) {
        model.setVisible(visible);
    }
}
