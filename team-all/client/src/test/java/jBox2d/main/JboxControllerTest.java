/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jBox2d.main;

import org.mockito.InOrder;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import JBox2d.main.JboxController;
import model.SpriteModel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.*;


public class JboxControllerTest {

    private JboxController mock;
    public JboxControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        mock=Mockito.mock(JboxController.class);
    }
    
    @After
    public void tearDown() {
        mock=null;
    }

    /**
     * Test of init method, of class JboxController.
     */
    @Test
    public void testInit() throws Exception {
        
        GameContainer mockPara = Mockito.mock(GameContainer.class);
        
        
        doThrow(new RuntimeException()).when(mock).init(mockPara);
        
         }

    /**
     * Test of update method, of class JboxController.
     */
    @Test
    public void testUpdate() throws Exception {
       // System.out.println("update");
        GameContainer mockPara = Mockito.mock(GameContainer.class);
        int delta = 0;
       
       doCallRealMethod().when(mock).update(mockPara, delta);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of render method, of class JboxController.
     */
    @Test
    public void testRender() throws Exception {
      //  System.out.println("render");
       
        GameContainer mockGameContainer= Mockito.mock(GameContainer.class);
        Graphics mockGraphics= Mockito.mock(Graphics.class);
         doAnswer(new Answer() {
      public Object answer(InvocationOnMock invocation) {
          Object[] args = invocation.getArguments();
          return null;
      }})
  .when(mock).render(mockGameContainer, mockGraphics);
    }

    /**
     * Test of getImageFromBytes method, of class JboxController.
     */
    @Test
    public void testGetImageFromBytes()throws Exception {
         byte [] imageData=null;
       Image image=null;
       Mockito.when(mock.getImageFromBytes(imageData, "test")).thenReturn(image);
       assertEquals(image, mock.getImageFromBytes(imageData, "test"));
    }

    /**
     * Test of initActionEvents method, of class JboxController.
     */
    @Test
    public void testInitActionEvents() throws Exception {
       // System.out.println("initActionEvents");
        GameContainer mockGameContainer = Mockito.mock(GameContainer.class);
        
        doAnswer(new Answer() {
      public Object answer(InvocationOnMock invocation) {
          Object[] args = invocation.getArguments();
          return null;
      }})
  .when(mock).init(mockGameContainer);
    }

    /**
     * Test of renderSpriteImageDraw method, of class JboxController.
     */
    @Test
    public void testRenderSpriteImageDraw() {
       // System.out.println("renderSpriteImageDraw");
         JboxController first= mock(JboxController.class);
       JboxController second= mock(JboxController.class);
     
       first.renderSpriteImageDraw();
       second.renderSpriteImageDraw();
       InOrder inOrder= inOrder(first,second);
        inOrder.verify(first).renderSpriteImageDraw();
        inOrder.verify(second).renderSpriteImageDraw();
    }

    /**
     * Test of imageDraw method, of class JboxController.
     */
    //@Test
    public void testImageDraw() {
      //  System.out.println("imageDraw");
        SpriteModel sprite = null;
        JboxController instance = null;
        instance.imageDraw(sprite);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initSpriteImageMapping method, of class JboxController.
     */
    //@Test
    public void testInitSpriteImageMapping() throws Exception {
        //System.out.println("initSpriteImageMapping");
        JboxController instance = null;
        instance.initSpriteImageMapping();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateKeyEventBinding method, of class JboxController.
     */
  //  @Test
    public void testUpdateKeyEventBinding() {
       // System.out.println("updateKeyEventBinding");
        GameContainer gc = null;
        JboxController instance = null;
        instance.updateKeyEventBinding(gc);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
