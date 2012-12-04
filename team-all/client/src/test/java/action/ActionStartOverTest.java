/**
 * 
 */
package action;

import static org.junit.Assert.*;

import model.SpriteModel;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author ridhima
 *
 */
public class ActionStartOverTest {

	/**
	 * @throws java.lang.Exception
	 */
	static ActionStartOver actionStartOverTest;
	static SpriteModel model;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		double xSpeed = 10;
		double ySpeed = 10;
		double initialPosX = 100;
		double initialPosY = 100;
		double width = 100;
		double height = 100;
		
		int incrementXSpeedValue = 20;
		int incrementYSpeedValue = 20;
		
		//Creating a model
		model = new SpriteModel(initialPosX, initialPosY, xSpeed, ySpeed, width, height, "","");
		
		actionStartOverTest=new ActionStartOver();
		actionStartOverTest.setStartX(1);
		actionStartOverTest.setStartY(1);
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link action.ActionStartOver#doAction(model.SpriteModel)}.
	 */
	@Test
	public void testDoAction() {
		
		actionStartOverTest.doAction(model);
		if(model.getPosX()==1&&model.getPosY()==1)
			assertTrue(true);
		else
			assertTrue(false);
		
	}

}
