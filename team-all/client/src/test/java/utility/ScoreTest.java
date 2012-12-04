package utility;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ScoreTest {
	
	private Score score = Score.getInstance();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testIncreament() {
		score.setScore(0);
		score.increament();
		if(score.getScore()==1)
			assertTrue(true);
		else
			assertTrue(false);
	}

   @Test
   public void testDecrease() {
	   score.setScore(1);
		score.decrease();
		if(score.getScore()==0)
			assertTrue(true);
		else
			assertTrue(false);
	}

	@Test
	public void testModifyBy() {
		score.setScore(0);
		score.modifyBy(1);
		if(score.getScore()==1)
			assertTrue(true);
		else
			assertTrue(false);
	}
	

	@Test
	public void testResetScore() {
		
		score.resetScore();
		if(score.getScore()==0)
			assertTrue(true);
		else
			assertTrue(false);
	}

}
