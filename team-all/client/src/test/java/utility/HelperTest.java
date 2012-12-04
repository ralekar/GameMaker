package utility;

import static org.junit.Assert.*;

import model.SpriteModel;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HelperTest {
	private static SpriteModel selectedSpriteModel;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	selectedSpriteModel = new SpriteModel(100, 100, 10, 10, 100, 100, "","");

	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetActionForString() {
		//Helper.getsharedHelper().getActionForString("Change Speed", selectedSpriteModel).doAction(selectedSpriteModel);
		if(Helper.getsharedHelper().getActionForString("Change Speed", selectedSpriteModel).getClass().toString().equals("class action.ActionChangeSpeed"))
			 assertTrue(true);
		else
			 assertTrue(false);
		
			
	}

	@Test
	public void testGetEventListenerForString() {
		if(Helper.getsharedHelper().getEventListenerForString("New Frame","Change Speed", selectedSpriteModel,null).getClass().toString().equals("class eventlistener.NewFrameEventListener"))
			 assertTrue(true);
		else
			 assertTrue(false);
	}

	@Test
	public void testGetCurrentKeyCode() {
		Helper.getsharedHelper().setCurrentKeyCode(20);
		if(Helper.getsharedHelper().getCurrentKeyCode()==20)
			 assertTrue(true);
		else
			 assertTrue(false);	}

	@Test
	public void testSetCurrentKeyCode() {
		Helper.getsharedHelper().setCurrentKeyCode(10);
		if(Helper.getsharedHelper().getCurrentKeyCode()==10)
			 assertTrue(true);
		else
			 assertTrue(false);
	}

}
