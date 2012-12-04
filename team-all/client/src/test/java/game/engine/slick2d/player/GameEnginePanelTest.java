package game.engine.slick2d.player;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.newdawn.slick.CanvasGameContainer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doAnswer;

public class GameEnginePanelTest {

	GameEnginePanel gameEnginePanel,gameEnginePanelMock;
	CanvasGameContainer canvasGameContainer,expectedApp,actualApp;
	GameEngineController gameEngineController,expectedGameEngineController,actualGameEngineController;
	
	
	@Before
	public void setUp() throws Exception {
		gameEnginePanel= new GameEnginePanel();
		gameEnginePanelMock= mock(GameEnginePanel.class);
		
	}
   
	@After
	public void tearDown() throws Exception {
		gameEnginePanel=null;
		gameEnginePanelMock=null;
		canvasGameContainer=null;
		expectedApp=null;
		actualApp=null;
		gameEngineController=null;
		expectedGameEngineController=null;
		actualGameEngineController=null;
		
	}

	@Test
	public void testGameEnginePanel() {
		
	}

	@Test
	public void testGetGame() {
		gameEnginePanel.setGame(gameEngineController);
		actualGameEngineController= gameEnginePanel.getGame();
		expectedGameEngineController= gameEngineController;
		assertEquals(expectedGameEngineController,actualGameEngineController);
		
	}
	

	
    
	@Test
	public void testSetGame() {
		gameEnginePanel.setGame(gameEngineController);
		actualGameEngineController= gameEnginePanel.getGame();
		expectedGameEngineController= gameEngineController;
		assertEquals(expectedGameEngineController,actualGameEngineController);
		
	}
    
	
		
	
	
	
	@Test
	public void testStartGame() {
		
		doAnswer(new Answer() {
		      public Object answer(InvocationOnMock invocation) {
		          Object[] args = invocation.getArguments();
		          
	          return null;
		      }})
	   .when(gameEnginePanelMock).startGame();
              
		}
	
		
	

    @Test
	public void testNewGame() {
		doAnswer(new Answer() {
		      public Object answer(InvocationOnMock invocation) {
		          Object[] args = invocation.getArguments();
		          
		          return null;
		      }})
		  .when(gameEnginePanelMock).newGame();
			}
		
	}
	
	

