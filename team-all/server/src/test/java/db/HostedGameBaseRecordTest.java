package db;

import static org.junit.Assert.*;

import org.junit.*;

public class HostedGameBaseRecordTest {
	/**
	 * Run the HostedGameBaseRecord(String,String,String) constructor test.
	 *
	 * @throws Exception
	 */
	@Test
	public void testHostedGameBaseRecord_1()
		throws Exception {
		String hostName = "";
		String gameBaseName = "";
		String saveGameBaseName = "";

		HostedGameBaseRecord result = new HostedGameBaseRecord(hostName, gameBaseName, saveGameBaseName);

		assertNotNull(result);
		assertEquals(0, result.getId());
		assertEquals("", result.getHostName());
		assertEquals("", result.getSaveGameBaseName());
		assertEquals("", result.getGameBaseName());
	}

	/**
	 * Run the String getGameBaseName() method test.
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetGameBaseName_1()
		throws Exception {
		HostedGameBaseRecord fixture = new HostedGameBaseRecord("", "", "");
		fixture.setId(1);

		String result = fixture.getGameBaseName();

		assertEquals("", result);
	}

	/**
	 * Run the String getHostName() method test.
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetHostName_1()
		throws Exception {
		HostedGameBaseRecord fixture = new HostedGameBaseRecord("", "", "");
		fixture.setId(1);

		String result = fixture.getHostName();

		assertEquals("", result);
	}

	/**
	 * Run the int getId() method test.
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetId_1()
		throws Exception {
		HostedGameBaseRecord fixture = new HostedGameBaseRecord("", "", "");
		fixture.setId(1);

		int result = fixture.getId();

		assertEquals(1, result);
	}

	/**
	 * Run the String getSaveGameBaseName() method test.
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetSaveGameBaseName_1()
		throws Exception {
		HostedGameBaseRecord fixture = new HostedGameBaseRecord("", "", "");
		fixture.setId(1);

		String result = fixture.getSaveGameBaseName();

		assertEquals("", result);
	}

	/**
	 * Run the void setGameBaseName(String) method test.
	 *
	 * @throws Exception
	 */
	@Test
	public void testSetGameBaseName_1()
		throws Exception {
		HostedGameBaseRecord fixture = new HostedGameBaseRecord("", "", "");
		fixture.setId(1);
		String gameBaseName = "";

		fixture.setGameBaseName(gameBaseName);
	}

	/**
	 * Run the void setHostName(String) method test.
	 *
	 * @throws Exception
	 */
	@Test
	public void testSetHostName_1()
		throws Exception {
		HostedGameBaseRecord fixture = new HostedGameBaseRecord("", "", "");
		fixture.setId(1);
		String hostName = "";

		fixture.setHostName(hostName);
	}

	/**
	 * Run the void setId(int) method test.
	 *
	 * @throws Exception
	 */
	@Test
	public void testSetId_1()
		throws Exception {
		HostedGameBaseRecord fixture = new HostedGameBaseRecord("", "", "");
		fixture.setId(1);
		int id = 1;

		fixture.setId(id);
	}

	/**
	 * Run the void setSaveGameBaseName(String) method test.
	 *
	 * @throws Exception
	 */
	@Test
	public void testSetSaveGameBaseName_1()
		throws Exception {
		HostedGameBaseRecord fixture = new HostedGameBaseRecord("", "", "");
		fixture.setId(1);
		String saveGameBaseName = "";

		fixture.setSaveGameBaseName(saveGameBaseName);
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
		new org.junit.runner.JUnitCore().run(HostedGameBaseRecordTest.class);
	}
}