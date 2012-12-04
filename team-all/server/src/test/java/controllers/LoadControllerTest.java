package controllers;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.google.gson.Gson;

import db.DatabaseHandler;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DatabaseHandler.class)
public class LoadControllerTest {

	private LoadController loadController;

	@Before
	public void setUp() {
		loadController = new LoadController();
	}

	@Test
	public void testLoadGameBase() {
		List<String> gameDataList = new ArrayList<String>();
		String dataItem1 = "assume that this is lots of data!!!";
		gameDataList.add(dataItem1);
		PowerMockito.mockStatic(DatabaseHandler.class);
		try {

			PowerMockito.when(DatabaseHandler.class,
					"listQuery", ((String) Matchers.any()))
					.thenReturn(gameDataList);
			String result = loadController.listAllGameBases();
			String substring = result.substring(2, result.length() - 2);
			assertTrue(substring.equals(dataItem1));
		} catch (Exception e) {
			fail(e.toString());
			Logger.getLogger(LoadControllerTest.class).debug(e.getMessage());
		}
	}

	@Test
	public void testListAllGameBases() {
		List<String> gameNames = new ArrayList<String>();
		gameNames.add("pacman");
		gameNames.add("asteroids");
		Gson gson = new Gson();
		String json = gson.toJson(gameNames);
		PowerMockito.mockStatic(DatabaseHandler.class);
		try {
			PowerMockito.when(DatabaseHandler.class,
					"listQuery", ((String) Matchers.any()))
					.thenReturn(gameNames);
			String jsonResult = loadController.listAllGameBases();
			if (json.equals(jsonResult))
				assertTrue(true);
			else
				assertTrue(false);
		} catch (Exception e) {
			fail(e.toString());
			Logger.getLogger(LoadControllerTest.class).debug(e.getMessage());
		}
	}

	@Test
	public void testListMultiPlayerGameBases() {
		List<String> gameNames = new ArrayList<String>();
		gameNames.add("test multiplayer game 1");
		gameNames.add("test multiplayer game 2");
		Gson gson = new Gson();
		String json = gson.toJson(gameNames);
		PowerMockito.mockStatic(DatabaseHandler.class);
		try {
			PowerMockito.when(DatabaseHandler.class,
					"listQuery", ((String) Matchers.any()))
					.thenReturn(gameNames);
			String jsonResult = loadController.listMultiPlayerGameBases();
			if (json.equals(jsonResult))
				assertTrue(true);
			else
				assertTrue(false);
		} catch (Exception e) {
			fail(e.toString());
			Logger.getLogger(LoadControllerTest.class).debug(e.getMessage());
		}
	}

	@Test
	public void testLoadGamePlay() {
		String game1_data = "<data> this is the test game1 data </data>";
		String game2_data = "<data> this is the test game2 data </data>";
		List<String> gameData = new ArrayList<String>();
		gameData.add(game1_data);
		gameData.add(game2_data);
		PowerMockito.mockStatic(DatabaseHandler.class);
		try {
			PowerMockito.when(DatabaseHandler.class,
					"listQuery", ((String) Matchers.any()))
					.thenReturn(gameData);
			if (gameData.get(0).equals(
					loadController.loadGamePlay("test_game_id")))
				assertTrue(true);
			else
				assertTrue(false);
		} catch (Exception e) {
			fail(e.toString());
			Logger.getLogger(LoadControllerTest.class).debug(e.getMessage());
		}
	}
}