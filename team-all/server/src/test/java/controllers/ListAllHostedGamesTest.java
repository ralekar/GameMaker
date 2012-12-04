package controllers;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

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
@PrepareForTest({ DatabaseHandler.class })
public class ListAllHostedGamesTest {

	ListAllHostedGames listAllHostedGames;

	@Before
	public void setUp() {
		listAllHostedGames = new ListAllHostedGames();
	}

	@Test
	public void testLoadHostGames() {
		PowerMockito.mockStatic(DatabaseHandler.class);
		List<String> listGames = new ArrayList<String>();

		listGames.add("pacman");
		listGames.add("asteroids");

		try {

			PowerMockito.when(DatabaseHandler.class, "Query", Matchers.any()).thenReturn(listGames);
			Gson gson = new Gson();
			String json = gson.toJson(listGames);
			String actualResult = listAllHostedGames.loadHostGames();
			if (actualResult.equals(json)) {
				Assert.assertTrue(true);
			} else
				Assert.assertTrue(false);
		} catch (Exception e) {
			Logger.getLogger(ListAllHostedGamesTest.class).debug(e.getMessage());
		}
	}
}