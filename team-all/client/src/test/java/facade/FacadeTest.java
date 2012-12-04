package facade;

import controller.GameController;
import controller.KeyListenerController;
import javax.swing.Timer;
import static org.junit.Assert.assertTrue;
import model.SpriteModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utility.Constants;
import utility.SpriteList;
import view.GameMakerView;
import view.GamePanel;

public class FacadeTest   {
	private  Facade facade;
	private GamePanel gamePanel,gamePanel2,finalGamePanel;
        private KeyListenerController keyListenerController,keyListenerController2,finalkeyListenerController;
        private GameController gameController,gameController2,finalGameController;
        private Timer timer,timer2,finalTimer;
        
	@Before
	public void setUp() throws Exception {
		//setAdapter(new MainClassAdapter(gameMaker.class, new String[0]));        
        gamePanel=new GamePanel(Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT);
		facade=new Facade(gamePanel);
	}
	
	@After
	public void tearDown() throws Exception {
		
	}

@Test
	public void testStartGame() {
		facade.startGame();
		if(facade.getTimer().isRunning())
			assertTrue(true);
		else
			assertTrue(false);
		facade.stopGame();
		
	}

	@Test
	public void testStopGame() {
	facade.stopGame();
		if(!facade.getTimer().isRunning())
			assertTrue(true);
		else
			assertTrue(false);
	}

	@Test
	public void testCreateViewsForModels() {
		//removing all the drawables and spritemodels.
		facade.getGamePanel().getDrawables().clear();
		SpriteList.getInstance().getSpriteList().clear();

		SpriteModel selectedSpriteModel = new SpriteModel(190, 190, 10, 10, 100, 100, "","");
		selectedSpriteModel.setImageId(12);
		SpriteModel secondarySpriteModel= new SpriteModel(100, 100, 10, 10, 100, 200, "","");
		secondarySpriteModel.setImageId(10);
		secondarySpriteModel.setGroupId("Group1");
		SpriteList.getInstance().addSprite(selectedSpriteModel);
		SpriteList.getInstance().addSprite(secondarySpriteModel);
		facade.createViewsForModels(SpriteList.getInstance().getSpriteList());
		
		if(facade.getGamePanel().getDrawables().size()==2)
			assertTrue(true);
		else
			assertTrue(false);
		//post processing needed only for this test
		facade.getGamePanel().getDrawables().clear();
		SpriteList.getInstance().getSpriteList().clear();
		
				
	}	
	
        @Test
        public void testSetTimer() {
            facade.setTimer(timer2);
            finalTimer=facade.getTimer();
            if(finalTimer == timer2)
                assertTrue(true);
        }

	@Test
        public void testSetGamePanel() {
            facade.setGamePanel(gamePanel2);
            finalGamePanel=facade.getGamePanel();
            if(finalGamePanel == gamePanel2)
                assertTrue(true);
        }
        
        
        @Test
        public void testSetKeyListenerController() {
            facade.setKeyListenerController(keyListenerController2);
            finalkeyListenerController=facade.getKeyListenerController();
            if(finalkeyListenerController == keyListenerController2)
                assertTrue(true);  
        }
        
        @Test
        public void testSetGameController() {
            facade.setGameController(gameController2);
            finalGameController=facade.getGameController();
            if(finalGameController == gameController2)
                assertTrue(true);  
        }
        
}
