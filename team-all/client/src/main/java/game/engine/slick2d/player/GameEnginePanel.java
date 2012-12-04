package game.engine.slick2d.player;

import java.awt.Dimension;
import javax.swing.JPanel;
import loader.GamePackage;
import org.newdawn.slick.CanvasGameContainer;
import org.newdawn.slick.SlickException;
import utility.Constants;
import utility.Helper;
import view.GamePlayerView;

@SuppressWarnings("serial")
public class GameEnginePanel extends JPanel {

    private static final org.apache.log4j.Logger LOG =
            org.apache.log4j.Logger.getLogger(GameEnginePanel.class);
    private CanvasGameContainer canvas;
    private GameEngineController gameController;

    public GameEnginePanel() {
        setPreferredSize(new Dimension(Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT));
        setVisible(true);
    }

    private void addGame(CanvasGameContainer app) {
        canvas = app;
        canvas.getContainer().setTargetFrameRate(120);
        canvas.setPreferredSize(new Dimension(Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT));
        canvas.getContainer().setForceExit(true);
        add(canvas);
    }

    public void newGame(GamePackage p) {
        try {
            if (gameController != null) {
                gameController.reset(p);
                canvas.requestFocusInWindow();

                return;
            }

            initStart();
        } catch (Exception ex) {
            LOG.error(ex);
        }
    }

    public void newGame() {
        try {
            if (gameController != null) {
                GameEngineController temp = new GameEngineController("test", GameEngineController.LOAD_MODE_REMOTE, new String[]{"/game/engine/slick2d/player/testing_game.xml"});
                GamePackage p = temp.getGame();
                gameController.reset(p);

                canvas.requestFocusInWindow();
                return;
            }

            initStart();

        } catch (Exception ex) {
            LOG.error(ex);
        }
    }

    private void initStart() throws SlickException {
        gameController = new GameEngineController("test", GameEngineController.LOAD_MODE_REMOTE, new String[]{"/game/engine/slick2d/player/testing_game.xml"});

        //test
        gameController.setPauseReporter(new TestingReporter());

        CanvasGameContainer app = new CanvasGameContainer(gameController);
        GamePlayerView gamePlayerView = Helper.getsharedHelper().getGamePlayerView();
        gamePlayerView.getGameEnginePanel().addGame(app);
        gamePlayerView.getBaseFrame().pack();


        canvas.start();
        canvas.requestFocusInWindow();
    }

    public void startGame() {
        canvas.requestFocusInWindow();
        gameController.startGame();
    }

    public GameEngineController getGame() {
        return gameController;
    }

    public void setGame(GameEngineController game) {
        this.gameController = game;
    }
}
