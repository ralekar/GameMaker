package protocol;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import protocol.GameSaveInfo;

public class GameSaveInfoTest {

	private String saveName = "save1";
	private String saveName2 = "save2";
	private String saveName3 = "save3";
	private String expectedSaveName, actualSaveName;
	private String gameName = "Game";
	private String expectedGameName, actualGameName ; 
	private String gamePlayer = "Player";
	private String gamePlayer2 = "Player2";
	private String expectedGamePlayer, actualGamePlayer;
	private String gameData = "Data";
	private String gameData2 = "Data2";
	private String expectedGameData, actualGameData;
    private int gameScore = 100;
    private int gameScore2 = 200;
    private int expectedGameScore, actualGameScore;
    private int rank = 2; 
    private int expectedRank, actualRank; 
    private int id = 011;
    private int expectedId, actualId;
    private GameSaveInfo game1;
    private GameSaveInfo game2;
    private GameSaveInfo game3;
    
	@Before
	public void setUp() throws Exception {
		game1 = new GameSaveInfo(saveName, gameName, gamePlayer, id);
		game2 = new GameSaveInfo(saveName2, gameName, gamePlayer2, gameData);
		game3 = new GameSaveInfo(saveName3, gameName, gamePlayer, gameScore, rank);
		
		
	}

	@After
	public void tearDown() throws Exception {
		game1 = game2 = game3 = null;
		
	}

	@Test
	public void testGetGameName() {
		actualGameName = game1.getGameName();
		expectedGameName = "Game";
		assertEquals(actualGameName,expectedGameName);
	}

	@Test
	public void testGetGamePlayer() {
		actualGamePlayer = game1.getGamePlayer();
		expectedGamePlayer = gamePlayer;
		assertEquals(expectedGamePlayer,actualGamePlayer);
		actualGamePlayer = game2.getGamePlayer();
		assertNotSame(expectedGamePlayer, actualGamePlayer);
		expectedGamePlayer = gamePlayer2;
		assertEquals(expectedGamePlayer,actualGamePlayer);
		
	}

	@Test
	public void testGetSaveName() {
		actualSaveName = game1.getSaveName();
		expectedSaveName = saveName;
		assertEquals(expectedSaveName, actualSaveName);
		actualSaveName = game2.getSaveName();
		assertNotSame(expectedSaveName, actualSaveName);
		game2.setSaveName(game3.getSaveName());
		actualSaveName = game2.getSaveName();
		expectedSaveName = saveName3;
		assertEquals(expectedSaveName, actualSaveName);
		
	}

	@Test
	public void testGetGameScore() {
		game3.setGameScore(gameScore2);
		actualGameScore = game3.getGameScore();
		expectedGameScore = 200;
		assertEquals(expectedGameScore,actualGameScore);
	}

	@Test
	public void testGetGameData() {
		String initialGameData = game2.getGameData();
		game2.setGameData(gameData2);
		String finalGameData = game2.getGameData();
		assertNotSame(initialGameData,finalGameData);
	}

	@Test
	public void testGetRank() {
		int initialRank = game3.getRank();
		game3.setRank(1);
		int finalRank = game3.getRank();
		assertFalse(initialRank == finalRank);
		
	}

	@Test
	public void testGetId() {
		game3.setId(game1.getId());
		actualId = game3.getId();
		expectedId = 011;
		assertTrue(expectedId == actualId);
		
	}

}
