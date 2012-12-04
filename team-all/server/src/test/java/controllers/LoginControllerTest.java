package controllers;

import static org.junit.Assert.*;

import java.util.ArrayList;


import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import db.DatabaseHandler;
import db.User;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DatabaseHandler.class)
public class LoginControllerTest {
	private static final Logger LOG = Logger.getLogger(LoginController.class);
	private LoginController loginController;

	@Before
	public void setUp() {
		loginController = new LoginController();
	}

	@Test
	public void testLogin() {
		PowerMockito.mockStatic(DatabaseHandler.class);

		try {
			ArrayList<User> retValue = new ArrayList<User>();
			retValue.add(new User("asdf", "asdf", "1"));
			PowerMockito.when(DatabaseHandler.class,
					"loginQuery", "asdf", "asdf").thenReturn(
					retValue);
			String ret = loginController.login("asdf", "asdf");
			assertTrue(ret.equals("true"));

			ArrayList<User> secondRetValue = new ArrayList<User>();
			PowerMockito.when(DatabaseHandler.class,
					"loginQuery", "salkdf", "sdfsdfsdf")
					.thenReturn(secondRetValue);
			ret = loginController.login("salkdf", "sdfsdfsdf");
			assertTrue(ret.equals("false"));
			
		} catch (Exception e) {
			LOG.error(e);
			fail(e.toString());
		}
	}
	
	@Test
	public void testRegister(){
		PowerMockito.mockStatic(DatabaseHandler.class);
		try {
			PowerMockito.when(DatabaseHandler.class, 
					"save", ((Object) Matchers.any())).thenReturn(true);
			String ret = loginController.register("zzsfp", "zzqfp");
			System.out.println(ret);
			assertTrue(ret.equals("true"));
		} catch(Exception e) {
			LOG.error(e);
			fail(e.toString());
		}
	}
}
