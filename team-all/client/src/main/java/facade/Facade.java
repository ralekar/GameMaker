package facade;

import controller.GameController;
import controller.KeyListenerController;
import java.util.Collection;
import javax.swing.Timer;
import model.SpriteModel;
import utility.ClockDisplay;
import utility.Helper;
import view.GameMakerView;
import view.GamePanel;
import view.SpriteView;

public class Facade {

    private GamePanel gamePanel;
    private KeyListenerController keyListenerController;
    private GameController gameController;
    private Timer timer;

    public Facade(GamePanel gamePanel) {

        this.gamePanel = gamePanel;
        this.keyListenerController = new KeyListenerController();
        this.gamePanel.addKeyListener(keyListenerController);
        this.gameController = new GameController();
        this.timer = new Timer(100, gameController);
        this.gamePanel.repaint();
        this.gamePanel.requestFocusInWindow();
        this.gameController.setGamePanel(gamePanel);

    }

    public void startGame() {
        this.timer.start();
        ClockDisplay.getInstance().setEnabled(true);
        this.gamePanel.repaint();
        this.gamePanel.requestFocusInWindow();
    }

    public void stopGame() {
        this.timer.stop();
        this.gamePanel.repaint();
        this.gamePanel.requestFocusInWindow();

    }

    public void createDuplicateSpriteModel(SpriteModel model) {

        SpriteModel spriteModel = new SpriteModel(model.getPosX() + model.getWidth() / 2, model.getPosY() + model.getHeight() / 2, model.getSpeedX(), model.getSpeedY(), model.getWidth(), model.getHeight(),
                model.getImageUrlString(), model.getLayer(), model.getImageId());
        GameMakerView gameMakerView = Helper.getsharedHelper().getGameMakerView();
        gameMakerView.getActionEventPanel().updateSpriteList(spriteModel);
        gameMakerView.updateProperties();
        addSpriteModelToView(spriteModel);
        gamePanel.repaint();
    }

    public void addSpriteModelToView(SpriteModel spriteModel) {

        SpriteView spriteView = new SpriteView(spriteModel);
        gamePanel.registerDrawable(spriteView);
        this.gamePanel.repaint();
        this.gamePanel.requestFocusInWindow();
    }

    public void createViewsForModels(Collection<SpriteModel> spriteModels) {
        for (SpriteModel model : spriteModels) {
            addSpriteModelToView(model);
        }
    }

    public GameController getGameController() {
        return this.gameController;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public KeyListenerController getKeyListenerController() {
        return keyListenerController;
    }

    public void setKeyListenerController(KeyListenerController keyListenerController) {
        this.keyListenerController = keyListenerController;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public void reset() {
        getGameController().getEvents().clear();
        getKeyListenerController().getKeyEvents().clear();

    }
}
