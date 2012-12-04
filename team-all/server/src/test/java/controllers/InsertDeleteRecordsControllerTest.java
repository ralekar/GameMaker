package controllers;

import static org.junit.Assert.*;

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
public class InsertDeleteRecordsControllerTest {

	InsertDeleteRecordsController controller;

	@Before
	public void setUp() {
		controller = new InsertDeleteRecordsController();
	}

	@Test
	public void testDeleteHostedGameBaseRecord() {

		PowerMockito.mockStatic(DatabaseHandler.class);
		Gson gson = new Gson();

		try {
			int returnedValue = 1;
			PowerMockito.when(DatabaseHandler.class,
					"executeQuery", ((String) Matchers.any()))
					.thenReturn(returnedValue);
			String value = gson.toJson(false);
			String sqlQuery = "delete from HostedGameBases where hostname=\"m\"";
			String actualVal = controller.deleteHostedGameBaseRecord(sqlQuery);
			if (value.equals(actualVal))
				assertTrue(true);
			else
				assertTrue(false);
		} catch (Exception e) {
			Logger.getLogger(InsertDeleteRecordsControllerTest.class).debug(
					e.getMessage());
			fail(e.toString());
		}
	}
}
