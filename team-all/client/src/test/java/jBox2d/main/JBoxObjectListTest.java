/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jBox2d.main;

import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.mockito.InOrder;
import org.mockito.Mockito;

import JBox2d.main.JBoxObjectList;
import JBox2d.main.JBoxSpriteModel;
import org.jbox2d.dynamics.Body;

public class JBoxObjectListTest {
	private  JBoxObjectList mock;

	public JBoxObjectListTest() {
	}

	@BeforeClass
	public static void setUpClass() throws Exception {

	}

	@AfterClass
	public static void tearDownClass() throws Exception {

	}

	@Before
	public void setUp() {
		mock = new JBoxObjectList();
                mock.mapJBoxSpriteModel=new LinkedHashMap<String,Body>();
	}

	@After
	public void tearDown() {
		mock = null;
	}

	/** Test of registerSpriteModel method, of class JBoxObjectList. */
	@Test
	public void testRegisterSpriteModel() {
                JBoxObjectList mock1 = Mockito.mock(JBoxObjectList.class);
		JBoxObjectList mock2 = Mockito.mock(JBoxObjectList.class);
		JBoxSpriteModel sprite1 = Mockito.mock(JBoxSpriteModel.class);
		JBoxSpriteModel sprite2 = Mockito.mock(JBoxSpriteModel.class);
		mock1.registerSpriteModel(sprite1);
		mock2.registerSpriteModel(sprite2);
		InOrder inOrder = inOrder(mock1, mock2);
		inOrder.verify(mock1).registerSpriteModel(sprite1);
		inOrder.verify(mock2).registerSpriteModel(sprite2);
	}

	/** Test of getListJBoxSpriteModel method, of class JBoxObjectList. */
	@Test
	public void testSetListJBoxSpriteModel() throws Exception {
		Map<String,Body> actual=mock.getListJBoxSpriteModel();
                Map<String,Body> expected= new LinkedHashMap<String,Body>();
                mock.setListJBoxSpriteModel(expected);
                Map<String,Body> fin=mock.getListJBoxSpriteModel();
                assertEquals(fin,expected);
		
	}
}