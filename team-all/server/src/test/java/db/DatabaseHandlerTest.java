package db;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DatabaseHandlerTest {

	Session session;

	@Before
	public void setUp() {
		session = null;
	}

	@Test
	public void testGetHibernateSession() {
		session = DatabaseHandler.getDatabaseHandlerInstance().getHibernateSession();
		if (session != null)
			Assert.assertTrue(true);
		else
			Assert.assertTrue(false);
	}

	/** Borrowing function names from client tests */

	@After
	public void tearDown() {
		if (session != null)
			session.close();
	}
}
