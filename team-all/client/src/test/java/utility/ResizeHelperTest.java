package utility;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ResizeHelperTest {

	private  ResizeHelper rHelper;
	
	@Before
	public void setUp() throws Exception {
		rHelper = ResizeHelper.getInstance();		
	}

	@After
	public void tearDown() throws Exception {
		rHelper.reset();
	}

	@Test
	public void testGetxFactor() {
		rHelper.setxFactor(1.5);
		assertEquals(1.5,rHelper.getxFactor(),0);
		assertEquals(ResizeHelper.getInstance().getxFactor(),rHelper.getxFactor(),0);
	}

	@Test
	public void testGetyFactor() {
		rHelper.setyFactor(3.5);
		assertEquals(3.5,rHelper.getyFactor(),0);
		assertEquals(ResizeHelper.getInstance().getyFactor(),rHelper.getyFactor(),0);
	}

}
