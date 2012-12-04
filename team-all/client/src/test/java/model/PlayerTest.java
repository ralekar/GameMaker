package model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
	private String testusername = "A10";
	private String testpassword = "A10";
	private String testusertype = "player";
	private Player testPlayer;
	
	@Before
	public void setUp() throws Exception {
		testPlayer = Player.getInstance();
		testPlayer.setPassword(testpassword);
		testPlayer.setUsername(testusername);
		testPlayer.setUsertype(testusertype);
	}

	@After
	public void tearDown() throws Exception {
		testPlayer = new Player();
	}

	@Test
	public void testGetInstance() {
		assertNotNull("Player Instance is being created during setup", testPlayer);
	}

	@Test
	public void testGetUsername() {
		testPlayer.setUsername("testuser");
		assertEquals("Get Username is running","testuser",testPlayer.getUsername());
	}

	@Test
	public void testGetPassword() {
		testPlayer.setPassword("testpassword");
		assertEquals("Get Password is running","testpassword",testPlayer.getPassword());
	}

	@Test
	public void testSetUsername() {
		assertEquals("Set Username is running",testusername,testPlayer.getUsername());
	}

	@Test
	public void testSetPassword() {
		assertEquals("Set Password is running",testpassword,testPlayer.getPassword());
	}

	@Test
	public void testGetUsertype() {
		testPlayer.setUsertype("gamemaker");
		assertEquals("Get UserType is running","gamemaker",testPlayer.getUsertype());
	}

	@Test
	public void testSetUsertype() {
		assertEquals("Set UserType is running",testusertype,testPlayer.getUsertype());
	}

	
	@Test
	public void testSetInstanceNull() {
		testPlayer = Player.setInstanceNull();
		assertNull(testPlayer);
	}

}
