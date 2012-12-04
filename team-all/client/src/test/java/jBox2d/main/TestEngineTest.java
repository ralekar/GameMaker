/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jBox2d.main;

import JBox2d.main.TestEngine;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.*;
import org.newdawn.slick.AppGameContainer;
import org.lwjgl.*;

/**
 *
 * @author Pranav
 */
public class TestEngineTest {
    
    public TestEngineTest() {
    }
    private static TestEngine mock;
   
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
      mock=Mockito.mock(TestEngine.class);
      
    }
    
    @After
    public void tearDown() {
        mock=null;
    }

   
    
    /**
     * Test of getApp method, of class TestEngine.
     */
    @Test
    public void testGetApp() {
      AppGameContainer expectApp=mock.getApp();
        TestEngine engine= Mockito.mock(TestEngine.class);
       engine.setApp(expectApp);
       AppGameContainer finalApp=engine.getApp();
       assertEquals(finalApp,expectApp);
       
        
    }

    /**
     * Test of setApp method, of class TestEngine.
     */
  //  @Test
    public void testSetApp() {
        AppGameContainer expectApp=mock.getApp();
        TestEngine engine= Mockito.mock(TestEngine.class);
       engine.setApp(expectApp);
       AppGameContainer finalApp=engine.getApp();
       assertEquals(finalApp,expectApp);
       
       
    }
}
