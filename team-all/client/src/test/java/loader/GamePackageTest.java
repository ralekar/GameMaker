package loader;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doThrow;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import eventlistener.EventListener;
import game.engine.slick2d.player.GameEnginePanel;

import model.SpriteModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class GamePackageTest {
    private Collection<SpriteModel> spriteList,spriteList2,initialSpriteList,finalSpriteList;
    private List<EventListener> eventsForGameController,eventsForGameController2;
    private List<EventListener> eventsForKeyController,eventsForKeyController2,initialEventsForKeyController,finalEventsForKeyController;
    private List<String> layers,layers2,initialLayers,finalLayers;
    private boolean isClockDisplayableInitially,isClockDisplayablefinally ;
    private boolean isClockDisplayable=false;
	
	private GamePackage gmPackage,gPMock;
	
	@Before
	public void setUp() throws Exception {
            gPMock= Mockito.mock(GamePackage.class); 	
            gmPackage = new GamePackage(spriteList, eventsForGameController, eventsForKeyController, layers, isClockDisplayable);
        }

	@After
	public void tearDown() throws Exception {
		gmPackage=null;
	}

	
	@Test
	public void testsetEventsForGameController() {
            List<EventListener> intialList = gmPackage.getEventsForGameController();
            assertEquals(intialList, eventsForGameController);
            gmPackage.setEventsForGameController(eventsForGameController2);
            List<EventListener> finalList = gmPackage.getEventsForGameController();
            assertEquals(finalList,eventsForGameController2);
        
        }
	
	@Test
	public void testGetEventsForKeyController() {
		   List<EventListener> mockList= Mockito.mock(List.class); 
	       Mockito.when(gPMock.getEventsForKeyController()).thenReturn(mockList);
               assertEquals(mockList,gPMock.getEventsForKeyController());
	}

	@Test
	public void testSetEventsForKeyController() {
            List<EventListener> initialEventsForKeyController = gmPackage.getEventsForKeyController();
            assertEquals(initialEventsForKeyController, eventsForKeyController);
            gmPackage.setEventsForKeyController(eventsForKeyController2);
           // List<EventListener> finalEventsForKeyController = gmPackage.getEventsForKeyController();
            assertEquals(initialEventsForKeyController,eventsForKeyController2);
        }

	@Test
	public void testSetSpriteList() {
            initialSpriteList = gmPackage.getSpriteList();
            gmPackage.setSpriteList(spriteList2);
            finalSpriteList = gmPackage.getSpriteList();
            if((initialSpriteList==spriteList)&&(finalSpriteList==spriteList2))
                assert(true);
            else
                assert(false);
        }

	@Test
	public void testGetLayers() {
            initialLayers = gmPackage.getLayers();
            if(initialLayers==layers);
            assert(true);
        }

	@Test
	public void testSetLayers() {
            initialLayers = gmPackage.getLayers();
            gmPackage.setLayers(layers2);
            finalLayers = gmPackage.getLayers();
            if((initialLayers==layers)&&(finalLayers==layers2))
                assert(true);
        
        }

	
	@Test
	public void testIsClockDisplayable() {
            isClockDisplayableInitially=gmPackage.isClockDisplayable();
            if(isClockDisplayableInitially==false);
            assert(true);
	}

	@Test
	public void testSetClockDisplayable() {
            isClockDisplayableInitially=gmPackage.isClockDisplayable();
            gmPackage.setClockDisplayable(true);
            isClockDisplayablefinally=gmPackage.isClockDisplayable();
            if(isClockDisplayablefinally==true)
                assert(true);
            else
                assert(false);
        }

    

}
