package jBox2d.main;

import org.jbox2d.collision.shapes.CircleShape;
import model.SpriteModel;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import JBox2d.main.JBoxSpriteModel;
import org.mockito.Mockito;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;


public class JBoxSpriteModelTest {

	private JBoxSpriteModel model;
        private JBoxSpriteModel mock;

	@Before
	public void setUp() {
		model = new JBoxSpriteModel(new SpriteModel(10.0, 10.0, 1.0, 1.0, 20, 20, "", null, 1));
                mock=Mockito.mock(JBoxSpriteModel.class);
        }

	@Test
	public void testCreateBodyDefStatic() {
		BodyDef def = model.createBodyDefStatic(60f, 60f);
		Assert.assertEquals(BodyType.STATIC, def.type);
		Assert.assertEquals(2.0f, def.position.x, 0.0);
		Assert.assertEquals(2.0f, def.position.y, 0.0);
	}

	@Test
	public void testCreatePolygon() {
		Shape shape = model.createShapePolygon(116.0f, 116.0f);
		if (shape instanceof PolygonShape)
			Assert.assertTrue(true);
		else
			Assert.assertTrue(false);
	}
        @Test
        public void testcreatePolygonShape(){
            
		
		Shape shape = model.createShapeCircle( 116.0f);
		if (shape instanceof CircleShape)
			Assert.assertTrue(true);
		else
			Assert.assertTrue(false);
        }
}
