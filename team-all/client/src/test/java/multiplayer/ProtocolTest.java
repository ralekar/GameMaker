package multiplayer;
import java.util.HashMap;
import model.SpriteModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utility.SpriteList;
import action.ActionChangeSpeed;
import action.GameAction;

public class ProtocolTest {

	Protocol protocol;

	@Before
	public void setUp() {
		protocol = new Protocol();
	}

	@Test
	public void testSetMultiPlayerAction() {
		SpriteModel model = new SpriteModel(10.0, 10.0, 5.0, 5.0, 20, 20, "", "1", 1);
		model.setId("1");
		SpriteList.getInstance().addSprite(model);
		HashMap<String, Object> map = new HashMap<String, Object>();
		//map.put(new ActionChangeSpeed(9.0, 9.0), "1");
		map.put("action",new ActionChangeSpeed(9.0, 9.0) );
		map.put("speedX", 1.0);
		map.put("speedY", 2.0);
		map.put("id", "1");
		protocol.setMultiplayerAction(map);
		Assert.assertEquals(9.0, model.getSpeedX(), 0.0);
	}
}
