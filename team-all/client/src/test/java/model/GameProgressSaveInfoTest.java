package model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GameProgressSaveInfoTest {
	private GameProgressSaveInfo testGameProgress;
	private String testLoadedGameName = "testPacm";
	private int testScore = 30;

	@Before
	public void setUp() throws Exception {
		testGameProgress = GameProgressSaveInfo.getInstance();
		testGameProgress.setLoadedGameName(testLoadedGameName);
		testGameProgress.setScore(testScore);
	}

	@After
	public void tearDown() throws Exception {
		testGameProgress = new GameProgressSaveInfo();
	}
	
	@Test
	public void testGetInstance() {
		assertNotNull("Game Instance is being created on called during setup", testGameProgress);
	}

	@Test
	public void testGameProgressSaveInfo() {
		if(testGameProgress.getLoadedGameName()=="testPacm" && testGameProgress.getScore()==30)
			assertTrue(true);
		else
			assertTrue(false);

	}

	@Test
	public void testGetLoadedGameName() {
		testGameProgress.setLoadedGameName("test");
		assertEquals("Get Loaded Game Name works", "test", testGameProgress.getLoadedGameName());
	}

	@Test
	public void testSetLoadedGameName() {
		assertEquals("Set Loaded Game Name works", "testPacm", testGameProgress.getLoadedGameName());
	}

	@Test
	public void testGetScore() {
		testGameProgress.setScore(50);
		assertEquals("Get Score works", 50, testGameProgress.getScore());
	}

	@Test
	public void testSetScore() {
		assertEquals("Set Score works", 30, testGameProgress.getScore());
	}

}
