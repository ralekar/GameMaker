package view.communication.protocol;

import static org.junit.Assert.*;

import org.junit.*;

public class GameSaveInfoTest {
	/**
	 * Run the GameSaveInfo(String,String,String,int) constructor test.
	 *
	 * @throws Exception
	 */
	@Test
	public void testGameSaveInfo_1()
		throws Exception {
		String saveName = "";
		String gameName = "";
		String gamePlayer = "";
		int id = 1;

		GameSaveInfo result = new GameSaveInfo(saveName, gameName, gamePlayer, id);

		assertNotNull(result);
		assertEquals(1, result.getId());
		assertEquals(0, result.getRank());
		assertEquals(0, result.getGameScore());
		assertEquals("", result.getSaveName());
		assertEquals("", result.getGameName());
		assertEquals(null, result.getGameData());
		assertEquals("", result.getGamePlayer());
	}

	/**
	 * Run the GameSaveInfo(String,String,String,String) constructor test.
	 *
	 * @throws Exception
	 */
	@Test
	public void testGameSaveInfo_2()
		throws Exception {
		String saveName = "";
		String gameName = "";
		String gamePlayer = "";
		String gameData = "";

		GameSaveInfo result = new GameSaveInfo(saveName, gameName, gamePlayer, gameData);

		assertNotNull(result);
		assertEquals(0, result.getId());
		assertEquals(0, result.getRank());
		assertEquals(0, result.getGameScore());
		assertEquals("", result.getSaveName());
		assertEquals("", result.getGameName());
		assertEquals("", result.getGameData());
		assertEquals("", result.getGamePlayer());
	}

	/**
	 * Run the GameSaveInfo(String,String,String,int,int) constructor test.
	 *
	 * @throws Exception
	 */
	@Test
	public void testGameSaveInfo_3()
		throws Exception {
		String saveName = "";
		String gameName = "";
		String gamePlayer = "";
		int id = 1;
		int gameScore = 1;

		GameSaveInfo result = new GameSaveInfo(saveName, gameName, gamePlayer, id, gameScore);

		assertNotNull(result);
		assertEquals(1, result.getId());
		assertEquals(0, result.getRank());
		assertEquals(1, result.getGameScore());
		assertEquals("", result.getSaveName());
		assertEquals("", result.getGameName());
		assertEquals(null, result.getGameData());
		assertEquals("", result.getGamePlayer());
	}

	/**
	 * Run the GameSaveInfo(String,String,String,int,int,String) constructor test.
	 *
	 * @throws Exception
	 */
	@Test
	public void testGameSaveInfo_4()
		throws Exception {
		String saveName = "";
		String gameName = "";
		String gamePlayer = "";
		int gameScore = 1;
		int rank = 1;
		String gameData = "";

		GameSaveInfo result = new GameSaveInfo(saveName, gameName, gamePlayer, gameScore, rank, gameData);

		assertNotNull(result);
		assertEquals(0, result.getId());
		assertEquals(1, result.getRank());
		assertEquals(1, result.getGameScore());
		assertEquals("", result.getSaveName());
		assertEquals("", result.getGameName());
		assertEquals("", result.getGameData());
		assertEquals("", result.getGamePlayer());
	}

	/**
	 * Run the String getGameData() method test.
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetGameData_1()
		throws Exception {
		GameSaveInfo fixture = new GameSaveInfo("", "", "", 1, 1, "");
		fixture.setId(1);

		String result = fixture.getGameData();

		assertEquals("", result);
	}

	/**
	 * Run the String getGameName() method test.
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetGameName_1()
		throws Exception {
		GameSaveInfo fixture = new GameSaveInfo("", "", "", 1, 1, "");
		fixture.setId(1);

		String result = fixture.getGameName();

		assertEquals("", result);
	}

	/**
	 * Run the String getGamePlayer() method test.
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetGamePlayer_1()
		throws Exception {
		GameSaveInfo fixture = new GameSaveInfo("", "", "", 1, 1, "");
		fixture.setId(1);

		String result = fixture.getGamePlayer();

		assertEquals("", result);
	}

	/**
	 * Run the int getGameScore() method test.
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetGameScore_1()
		throws Exception {
		GameSaveInfo fixture = new GameSaveInfo("", "", "", 1, 1, "");
		fixture.setId(1);

		int result = fixture.getGameScore();

		assertEquals(1, result);
	}

	/**
	 * Run the int getId() method test.
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetId_1()
		throws Exception {
		GameSaveInfo fixture = new GameSaveInfo("", "", "", 1, 1, "");
		fixture.setId(1);

		int result = fixture.getId();

		assertEquals(1, result);
	}

	/**
	 * Run the int getRank() method test.
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetRank_1()
		throws Exception {
		GameSaveInfo fixture = new GameSaveInfo("", "", "", 1, 1, "");
		fixture.setId(1);

		int result = fixture.getRank();

		assertEquals(1, result);
	}

	/**
	 * Run the String getSaveName() method test.
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetSaveName_1()
		throws Exception {
		GameSaveInfo fixture = new GameSaveInfo("", "", "", 1, 1, "");
		fixture.setId(1);

		String result = fixture.getSaveName();

		assertEquals("", result);
	}

	/**
	 * Run the void setGameData(String) method test.
	 *
	 * @throws Exception
	 */
	@Test
	public void testSetGameData_1()
		throws Exception {
		GameSaveInfo fixture = new GameSaveInfo("", "", "", 1, 1, "");
		fixture.setId(1);
		String gameData = "";

		fixture.setGameData(gameData);
	}

	/**
	 * Run the void setGameName(String) method test.
	 *
	 * @throws Exception
	 */
	@Test
	public void testSetGameName_1()
		throws Exception {
		GameSaveInfo fixture = new GameSaveInfo("", "", "", 1, 1, "");
		fixture.setId(1);
		String gameName = "";

		fixture.setGameName(gameName);
	}

	/**
	 * Run the void setGamePlayer(String) method test.
	 *
	 * @throws Exception
	 */
	@Test
	public void testSetGamePlayer_1()
		throws Exception {
		GameSaveInfo fixture = new GameSaveInfo("", "", "", 1, 1, "");
		fixture.setId(1);
		String gamePlayer = "";

		fixture.setGamePlayer(gamePlayer);
	}

	/**
	 * Run the void setGameScore(int) method test.
	 *
	 * @throws Exception
	 */
	@Test
	public void testSetGameScore_1()
		throws Exception {
		GameSaveInfo fixture = new GameSaveInfo("", "", "", 1, 1, "");
		fixture.setId(1);
		int gameScore = 1;

		fixture.setGameScore(gameScore);
	}

	/**
	 * Run the void setId(int) method test.
	 *
	 * @throws Exception
	 */
	@Test
	public void testSetId_1()
		throws Exception {
		GameSaveInfo fixture = new GameSaveInfo("", "", "", 1, 1, "");
		fixture.setId(1);
		int id = 1;

		fixture.setId(id);
	}

	/**
	 * Run the void setRank(int) method test.
	 *
	 * @throws Exception
	 */
	@Test
	public void testSetRank_1()
		throws Exception {
		GameSaveInfo fixture = new GameSaveInfo("", "", "", 1, 1, "");
		fixture.setId(1);
		int rank = 1;

		fixture.setRank(rank);
	}

	/**
	 * Run the void setSaveName(String) method test.
	 *
	 * @throws Exception
	 */
	@Test
	public void testSetSaveName_1()
		throws Exception {
		GameSaveInfo fixture = new GameSaveInfo("", "", "", 1, 1, "");
		fixture.setId(1);
		String saveName = "";

		fixture.setSaveName(saveName);
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 */
	@Before
	public void setUp()
		throws Exception {
		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *         if the clean-up fails for some reason
	 */
	@After
	public void tearDown()
		throws Exception {
		// Add additional tear down code here
	}

	/**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(GameSaveInfoTest.class);
	}
}