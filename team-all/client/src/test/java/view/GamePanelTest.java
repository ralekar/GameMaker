package view;

import interfaces.Drawable;

import java.util.List;

import junit.framework.Assert;
import model.SpriteModel;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.mockito.Mockito;

public class GamePanelTest {

	GamePanel gamePanel = Mockito.mock(GamePanel.class);

	@Test
	public void testUnregisterModel() {

		try {
			SpriteModel model1 = new SpriteModel(10.0, 10.0, 1.0, 1.0, 10.0, 10.0, "", "");
			SpriteView view1 = Mockito.mock(SpriteView.class);
			view1.setModel(model1);
			SpriteModel model2 = new SpriteModel(15.0, 15.0, 2.0, 2.0, 20.0, 20.0, "", "");
			SpriteView view2 = Mockito.mock(SpriteView.class);
			view2.setModel(model2);
			gamePanel.registerDrawable(view1);
			gamePanel.registerDrawable(view2);
			gamePanel.unregisterModel(model1); // testing

			List<Drawable> drawables = gamePanel.getDrawables();
			boolean result = false;

			for (Drawable d : drawables)
				if (d.equals(view1))
					Assert.assertTrue(false);

			Assert.assertTrue(true);
		} catch (Exception e) {
			Logger.getLogger(GamePanelTest.class).debug(e.getMessage());
		}
	}
}
