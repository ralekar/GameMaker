package db;

import static org.junit.Assert.*;

import db.Resources;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ResourcesTest {

	private Resources testerResources;
	private int testResourceNumber = 25;
    private String testresourceName = "A10";
    private String testresourceType = "image";
    private byte[] testresource = null;
    private String testusername = "A10user";

	@Before
	public void setUp() throws Exception {
		testerResources = new Resources();
		testerResources.setResourceNumber(testResourceNumber);
		testerResources.setResource(testresource);
		testerResources.setResourceName(testresourceName);
		testerResources.setResourceType(testresourceType);
		testerResources.setUsername(testusername);
	}

	@After
	public void tearDown() throws Exception {
		testerResources = new Resources();
	}

	@Test
	public void testResources() {
		testerResources = new Resources("pac","image",null,"testA110");
		if(testerResources.getResource() == null && testerResources.getResourceName() == "pac" 
				&& testerResources.getResourceType()=="image" && testerResources.getUsername() == "testA110"){
			assertTrue(true);
		}
		else
			assertTrue(false);
	}

	@Test
	public void testGetReourceNumber() {
		int testNumber = 45;
		testerResources.setResourceNumber(testNumber);
		assertEquals("Get Resource number is working",testNumber,testerResources.getResourceNumber());
	}

	@Test
	public void testSetReourceNumber() {
		assertEquals("Set Resource number is working",testResourceNumber,testerResources.getResourceNumber());
	}

	@Test
	public void testGetResourceName() {
		testerResources.setResourceName("asteroids");
		assertEquals("Get Resource Name is working","asteroids",testerResources.getResourceName());
	}

	@Test
	public void testSetResourceName() {
		assertEquals("Set Resource Name is working",testresourceName,testerResources.getResourceName());
	}

	@Test
	public void testGetResourceType() {
		testerResources.setResourceType("audio");
		assertEquals("Get Resource Type is working","audio",testerResources.getResourceType());
	}

	@Test
	public void testSetResourceType() {
		assertEquals("Set Resource Type is working",testresourceType,testerResources.getResourceType());
	}

	@Test
	public void testGetResource() {
		testerResources.setResource(null);
		assertEquals("Get Resource is working",null,testerResources.getResource());
	}

	@Test
	public void testSetResource() {
		assertEquals("Set Resource is working",testresource,testerResources.getResource());
	}

	@Test
	public void testGetUsername() {
		testerResources.setUsername("player");
		assertEquals("Get username is working","player",testerResources.getUsername());
	}

	@Test
	public void testSetUsername() {
		assertEquals("Set username is working",testusername,testerResources.getUsername());
	}

}
