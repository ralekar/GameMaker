package action;

import eventlistener.CollisionEventListener;
import model.SpriteModel;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import utility.ResizeHelper;
import utility.SpriteList;
import view.GameMakerView;

public class ActionBounceTest {

    private SpriteModel selectedSpriteModel;
    private SpriteModel secondarySpriteModel;
    private CollisionEventListener collisionListener;
    private GameAction action;

    @Before
    public void setUp() throws Exception {
    	SpriteList.getInstance().getSpriteList().clear();
        ResizeHelper.getInstance().reset();
        selectedSpriteModel = new SpriteModel(190, 190, 10, 10, 100, 100, "", "");
        secondarySpriteModel = new SpriteModel(100, 100, 10, 10, 100, 200, "", "");
        secondarySpriteModel.setGroupId("Group1");
        SpriteList.getInstance().addSprite(selectedSpriteModel);
        SpriteList.getInstance().addSprite(secondarySpriteModel);
        collisionListener = new CollisionEventListener();
        collisionListener.setRegisteredGroupId1(selectedSpriteModel.getGroupId());
        collisionListener.setRegisteredGroupId2(secondarySpriteModel.getGroupId());
        action = new ActionBounce(true);

    }

    @Test
    public void testDoAction() {
        double previousSpeedX = selectedSpriteModel.getSpeedX();
        double previousSpeedY = selectedSpriteModel.getSpeedY();
        action.doAction(selectedSpriteModel);
        if (selectedSpriteModel.getSpeedX() == -previousSpeedX || selectedSpriteModel.getSpeedY() == -previousSpeedY) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }

    }

    @After
    public void tearDown() throws Exception {
    	SpriteList.getInstance().getSpriteList().clear();

    }
}
