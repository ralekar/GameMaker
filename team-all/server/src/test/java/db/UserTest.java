package db;

import static org.junit.Assert.*;

import db.User;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserTest {

	private String testusername = "A10";
	private String testpassword = "A10";
	private String testusertype = "player";
	String s1 = "s1", s2 = "s2", s3 = "s3";
	private User testUser;
	
	@Before
	public void setUp() throws Exception {
		testUser = new User();
		testUser.setPassword(testpassword);
		testUser.setUsername(testusername);
		testUser.setUsertype(testusertype);
	}

	@After
	public void tearDown() throws Exception {
		testUser = new User();
	}
	
	@Test
	public void testUserStringStringString(){
		testUser = new User("username","password","usertype");
		if(testUser.getUsername() == "username" && 
				testUser.getPassword() == "password" &&
				testUser.getUsertype() == "usertype"){
			assertTrue(true);
		}
		else
			assertTrue(false);
			
	}

	@Test
	public void testGetUsername() {
		testUser.setUsername("testuser");
		assertEquals("Get Username is running","testuser",testUser.getUsername());
	}

	@Test
	public void testGetPassword() {
		testUser.setPassword("testpassword");
		assertEquals("Get Password is running","testpassword",testUser.getPassword());
	}

	@Test
	public void testSetUsername() {
		assertEquals("Set Username is running",testusername,testUser.getUsername());
	}

	@Test
	public void testSetPassword() {
		assertEquals("Set Password is running",testpassword,testUser.getPassword());
	}

	@Test
	public void testGetUsertype() {
		testUser.setUsertype("gamemaker");
		assertEquals("Get UserType is running","gamemaker",testUser.getUsertype());
	}

	@Test
	public void testSetUsertype() {
		assertEquals("Set UserType is running",testusertype,testUser.getUsertype());
	}

}
