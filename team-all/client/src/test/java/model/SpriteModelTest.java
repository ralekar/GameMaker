/**
 * 
 */
package model;

import static org.junit.Assert.*;

import java.awt.Rectangle;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import eventlistener.EventListener;

import utility.Helper;
import view.GameMakerView;



public class SpriteModelTest {

	/**
	 * @throws java.lang.Exception
	 */
	
	private SpriteModel model ;
	private SpriteModel testModel ;
	private List<SpriteModel> spriteModels;
	private List<EventListener> eventListenerList;
	private HashMap<String,Integer> stringToEventMap;
	private EventListener listener;
	

	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	/*	
		double xSpeed = 10;
		double ySpeed = 10;
		double initialPosX = 100;
		double initialPosY = 100;
		double width = 100;
		double height = 100;
		int incrementXSpeedValue = 20;
		int incrementYSpeedValue = 20;
		//Creating a model
		model = new SpriteModel(initialPosX, initialPosY, xSpeed, ySpeed, width, height, "","");
		testModel= new SpriteModel(190, 190, 10, 10, 100, 100, "","");

		listener = Helper.getsharedHelper().getEventListenerForString("Input", "Change Speed", model,null);
		
		
		
		
		spriteModels= new ArrayList();
		
		spriteModels.add(model);
		spriteModels.add(testModel);*/		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		//GameMakerView.getInstance().reset();
	}

	/**
	 * Test method for {@link model.SpriteModel#SpriteModel(double, double, double, double, double, double, java.lang.String)}.
	 */
	@Test
	public void testSpriteModel() {
		SpriteModel model = new SpriteModel(5, 5,10,10,100, 100,"","");
		
		model.setHeading(0);
		model.setPreviousX(1);
		model.setPreviousY(1);
        model.setLayer("layer1");
        String urlString = "/images/closed.png";
        model.setImageUrlString(urlString);
       
		EventListener listener = Helper.getsharedHelper().getEventListenerForString("Input", "Change Speed", model,null);

        model.setEventListenerList(new ArrayList<EventListener>());
		model.getEventListenerList().add(listener);
		model.setStringToEventMap(new HashMap<String,Integer>());
		model.getStringToEventMap().put("InputChangeSpeed", listener.getEventId());
		
		if(model.getPosX()==5 && model.getPosY()==5 && model.getSpeedX()==10 && 
				model.getSpeedY()==10 && model.getHeight()==100 && model.getWidth()==100&&
				model.getHeading()==0 &&model.getLayer().equals("layer1")&&
				model.getEventListenerList().get(0).equals(listener)&&
				model.getStringToEventMap().containsKey("InputChangeSpeed")&&
				model.getImageUrlString().equalsIgnoreCase(urlString) &&
				model.getPreviousX()==1&&model.getPreviousY()==1)
			 assertTrue(true);
				else
					assertTrue(false);
				
		
	}
//
//	/**
//	 * Test method for {@link model.SpriteModel#getBoundingBox()}.
//	 */
	@Test
	public void testGetBoundingBox() {
		SpriteModel model = new SpriteModel(5, 5,10,10,100, 100,"","");
		Rectangle spriteRectangle = model.getBoundingBox();
		if(spriteRectangle.contains(model.getPosX(), model.getPosY(),model.getWidth(), model.getHeight()))
			 assertTrue(true);
		else
			assertTrue(false);
	}
//
//	/**
//	 * Test method for {@link model.SpriteModel#intersects(java.awt.Rectangle)}.
//	 */
	@Test
	public void testIntersects() {
		double xSpeed = 10;
		double ySpeed = 10;
		double initialPosX = 100;
		double initialPosY = 100;
		double width = 100;
		double height = 100;
		int incrementXSpeedValue = 20;
		int incrementYSpeedValue = 20;
		//Creating a model
		SpriteModel model = new SpriteModel(initialPosX, initialPosY, xSpeed, ySpeed, width, height, "","");
		SpriteModel testModel= new SpriteModel(190, 190, 10, 10, 100, 100, "","");

		
		if(model.intersects(testModel.getBoundingBox()) )
			 assertTrue(true);
		else
			assertTrue(false);

	}

//	/**
//	 * Test method for {@link model.SpriteModel#intersectsAny(java.util.List, java.lang.String)}.
//	 */
	@Test
	public void testIntersectsAny() {
		
		double xSpeed = 10;
		double ySpeed = 10;
		double initialPosX = 100;
		double initialPosY = 100;
		double width = 100;
		double height = 100;
		int incrementXSpeedValue = 20;
		int incrementYSpeedValue = 20;
		//Creating a model
		SpriteModel model = new SpriteModel(initialPosX, initialPosY, xSpeed, ySpeed, width, height, "","");
		SpriteModel testModel= new SpriteModel(190, 190, 10, 10, 100, 100, "","");

		List<SpriteModel> spriteModels= new ArrayList();		
		spriteModels.add(model);
		spriteModels.add(testModel);		

		
		if(model.intersectsAny(spriteModels,"Default Group 0") )
			 assertTrue(true);
		else
			assertTrue(false);

	}

//	/**
//	 * Test method for {@link model.SpriteModel#getWidth()}.
//	 */
	@Test
	public void testGetWidth() {
		
		double xSpeed = 10;
		double ySpeed = 10;
		double initialPosX = 100;
		double initialPosY = 100;
		double width = 100;
		double height = 100;
		SpriteModel model = new SpriteModel(initialPosX, initialPosY, xSpeed, ySpeed, width, height, "","");
		
		if(model.getWidth() == 100 )
			 assertTrue(true);
		else
			assertTrue(false);
		
	}

//	/**
//	 * Test method for {@link model.SpriteModel#getHeight()}.
//	 */
	@Test
	public void testGetHeight() {
		double xSpeed = 10;
		double ySpeed = 10;
		double initialPosX = 100;
		double initialPosY = 100;
		double width = 100;
		double height = 100;
		SpriteModel model = new SpriteModel(initialPosX, initialPosY, xSpeed, ySpeed, width, height, "","");

		if(model.getHeight() == 100 )
			 assertTrue(true);
		else
			assertTrue(false);
	}

//	/**
//	 * Test method for {@link model.SpriteModel#getPosX()}.
//	 */
	@Test
	public void testGetPosX() {
		double xSpeed = 10;
		double ySpeed = 10;
		double initialPosX = 100;
		double initialPosY = 100;
		double width = 100;
		double height = 100;
		SpriteModel model = new SpriteModel(initialPosX, initialPosY, xSpeed, ySpeed, width, height, "","");

		if(model.getPosX() == 100 )
			 assertTrue(true);
		else
			assertTrue(false);
	}

//	/**
//	 * Test method for {@link model.SpriteModel#setPosX(double)}.
//	 */
	@Test
	public void testSetPosX() {
		double xSpeed = 10;
		double ySpeed = 10;
		double initialPosX = 100;
		double initialPosY = 100;
		double width = 100;
		double height = 100;
		SpriteModel model = new SpriteModel(initialPosX, initialPosY, xSpeed, ySpeed, width, height, "","");

		model.setPosX(10);
		if(model.getPosX() == 10 )
			 assertTrue(true);
		else
			assertTrue(false);
	}

//	/**
//	 * Test method for {@link model.SpriteModel#getPosY()}.
//	 */
	@Test
	public void testGetPosY() {
		double xSpeed = 10;
		double ySpeed = 10;
		double initialPosX = 100;
		double initialPosY = 100;
		double width = 100;
		double height = 100;
		SpriteModel model = new SpriteModel(initialPosX, initialPosY, xSpeed, ySpeed, width, height, "","");

		if(model.getPosY() == 100 )
			 assertTrue(true);
		else
			assertTrue(false);
	}

//	/**
//	 * Test method for {@link model.SpriteModel#setPosY(double)}.
//	 */
	@Test
	public void testSetPosY() {
		double xSpeed = 10;
		double ySpeed = 10;
		double initialPosX = 100;
		double initialPosY = 100;
		double width = 100;
		double height = 100;
		SpriteModel model = new SpriteModel(initialPosX, initialPosY, xSpeed, ySpeed, width, height, "","");

		model.setPosY(10);
		if(model.getPosY() == 10 )
			 assertTrue(true);
		else
			assertTrue(false);
	}

//	/**
//	 * Test method for {@link model.SpriteModel#getSpeedX()}.
//	 */
	@Test
	public void testGetSpeedX() {
		double xSpeed = 10;
		double ySpeed = 10;
		double initialPosX = 100;
		double initialPosY = 100;
		double width = 100;
		double height = 100;
		SpriteModel model = new SpriteModel(initialPosX, initialPosY, xSpeed, ySpeed, width, height, "","");

		
		if(model.getSpeedX() == 10 )
			 assertTrue(true);
		else
			assertTrue(false);
		
	}

//	/**
//	 * Test method for {@link model.SpriteModel#setSpeedX(double)}.
//	 */
	@Test
	public void testSetSpeedX() {
		double xSpeed = 10;
		double ySpeed = 10;
		double initialPosX = 100;
		double initialPosY = 100;
		double width = 100;
		double height = 100;
		SpriteModel model = new SpriteModel(initialPosX, initialPosY, xSpeed, ySpeed, width, height, "","");

		model.setSpeedX(3);
		if(model.getSpeedX() == 3 )
			 assertTrue(true);
		else
			assertTrue(false);
	}

	/**
	 * Test method for {@link model.SpriteModel#getSpeedY()}.
	 */
	@Test
	public void testGetSpeedY() {
		double xSpeed = 10;
		double ySpeed = 10;
		double initialPosX = 100;
		double initialPosY = 100;
		double width = 100;
		double height = 100;
		SpriteModel model = new SpriteModel(initialPosX, initialPosY, xSpeed, ySpeed, width, height, "","");
		
		
		if(model.getSpeedY() == 10 )
			 assertTrue(true);
		else
			assertTrue(false);
		
	}
//
//	/**
//	 * Test method for {@link model.SpriteModel#setSpeedY(double)}.
//	 */
	@Test
	public void testSetSpeedY() {
		double xSpeed = 10;
		double ySpeed = 10;
		double initialPosX = 100;
		double initialPosY = 100;
		double width = 100;
		double height = 100;
		SpriteModel model = new SpriteModel(initialPosX, initialPosY, xSpeed, ySpeed, width, height, "","");

		model.setSpeedY(3);
		if(model.getSpeedY() == 3 )
			 assertTrue(true);
		else
			assertTrue(false);
	}

//	/**
//	 * Test method for {@link model.SpriteModel#setWidth(double)}.
//	 */
	@Test
	public void testSetWidth() {
		double xSpeed = 10;
		double ySpeed = 10;
		double initialPosX = 100;
		double initialPosY = 100;
		double width = 100;
		double height = 100;
		SpriteModel model = new SpriteModel(initialPosX, initialPosY, xSpeed, ySpeed, width, height, "","");

		model.setWidth(5);
		if(model.getWidth() == 5 )
			 assertTrue(true);
		else
			assertTrue(false);
	}

//	/**
//	 * Test method for {@link model.SpriteModel#setHeight(double)}.
//	 */
	@Test
	public void testSetHeight() {
		double xSpeed = 10;
		double ySpeed = 10;
		double initialPosX = 100;
		double initialPosY = 100;
		double width = 100;
		double height = 100;
		SpriteModel model = new SpriteModel(initialPosX, initialPosY, xSpeed, ySpeed, width, height, "","");

		model.setHeight(5);
		if(model.getHeight() == 5 )
			 assertTrue(true);
		else
			assertTrue(false);
	}


//	/**
//	 * Test method for {@link model.SpriteModel#getImageUrlString()}.
//	 */
	@Test
	public void testGetImageUrlString() {
		double xSpeed = 10;
		double ySpeed = 10;
		double initialPosX = 100;
		double initialPosY = 100;
		double width = 100;
		double height = 100;
		SpriteModel model = new SpriteModel(initialPosX, initialPosY, xSpeed, ySpeed, width, height, "","");

		model.setImageUrlString("imagepath");
        if(model.getImageUrlString().equals("imagepath")){
        	assertTrue(true);
        }
        else
        	assertTrue(false);
	}

//	/**
//	 * Test method for {@link model.SpriteModel#setImageUrlString(java.lang.String)}.
//	 */
	@Test
	public void testSetImageUrlString() {
		double xSpeed = 10;
		double ySpeed = 10;
		double initialPosX = 100;
		double initialPosY = 100;
		double width = 100;
		double height = 100;
		SpriteModel model = new SpriteModel(initialPosX, initialPosY, xSpeed, ySpeed, width, height, "","");

		model.setImageUrlString("imagepath");
        if(model.getImageUrlString().equals("imagepath")){
        	assertTrue(true);
        }
        else
        	assertTrue(false);
	}


//	/**
//	 * Test method for {@link model.SpriteModel#getEventListenerList()}.
//	 */
//	@Test
//	public void testGetEventListenerList() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link model.SpriteModel#setEventListenerList(java.util.ArrayList)}.
//	 */
//	@Test
//	public void testSetEventListenerList() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link model.SpriteModel#getStringToEventMap()}.
//	 */
//	@Test
//	public void testGetStringToEventMap() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link model.SpriteModel#setStringToEventMap(java.util.HashMap)}.
//	 */
//	@Test
//	public void testSetStringToEventMap() {
//		fail("Not yet implemented");
//	}
	@Test
	public void isVisible() {
		double xSpeed = 10;
		double ySpeed = 10;
		double initialPosX = 100;
		double initialPosY = 100;
		double width = 100;
		double height = 100;
		SpriteModel model = new SpriteModel(initialPosX, initialPosY, xSpeed, ySpeed, width, height, "","");

		model.setVisible(true);
        if(model.isVisible()){
        	assertTrue(true);
        }
        else
        	assertTrue(false);
	}

	@Test
    public void setVisible() {
		double xSpeed = 10;
		double ySpeed = 10;
		double initialPosX = 100;
		double initialPosY = 100;
		double width = 100;
		double height = 100;
		SpriteModel model = new SpriteModel(initialPosX, initialPosY, xSpeed, ySpeed, width, height, "","");

        model.setVisible(true);
        if(model.isVisible()){
        	assertTrue(true);
        }
        else
        	assertTrue(false);
        
    }

    
    public void getGroupId() {
    	double xSpeed = 10;
		double ySpeed = 10;
		double initialPosX = 100;
		double initialPosY = 100;
		double width = 100;
		double height = 100;
		SpriteModel model = new SpriteModel(initialPosX, initialPosY, xSpeed, ySpeed, width, height, "","");

    	model.setGroupId("Group1");
        if(model.getGroupId().equals("Group1")){
        	assertTrue(true);
        }
        else
        	assertTrue(false);
    }

    
    public void setGroupId(String groupId) {
    	double xSpeed = 10;
		double ySpeed = 10;
		double initialPosX = 100;
		double initialPosY = 100;
		double width = 100;
		double height = 100;
		SpriteModel model = new SpriteModel(initialPosX, initialPosY, xSpeed, ySpeed, width, height, "","");

    	model.setVisible(true);
        if(model.isVisible()){
        	assertTrue(true);
        }
        else
        	assertTrue(false);
    }

    
    public void getId() {
    	double xSpeed = 10;
		double ySpeed = 10;
		double initialPosX = 100;
		double initialPosY = 100;
		double width = 100;
		double height = 100;
		SpriteModel model = new SpriteModel(initialPosX, initialPosY, xSpeed, ySpeed, width, height, "","");

    	model.setId("Group1");
        if(model.getId().equals("Group1")){
        	assertTrue(true);
        }
        else
        	assertTrue(false);
    }

    
    public void setId(String id) {
    	double xSpeed = 10;
		double ySpeed = 10;
		double initialPosX = 100;
		double initialPosY = 100;
		double width = 100;
		double height = 100;
		SpriteModel model = new SpriteModel(initialPosX, initialPosY, xSpeed, ySpeed, width, height, "","");

    	model.setId("Group1");
        if(model.getId().equals("Group1")){
        	assertTrue(true);
        }
        else
        	assertTrue(false);
    }

}
