package JBox2d.main;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class TestEngine {
    
    private static final org.apache.log4j.Logger LOG = 
			org.apache.log4j.Logger.getLogger(TestEngine.class);
    public static AppGameContainer app;
    

    
    public static void main (String [] args){
        
        LOG.debug("This is Game Engine Test");
        //System.setProperty("org.lwjgl.librarypath", System.getProperty("user.dir") + "/target/natives/");
        //System.setProperty("net.java.games.input.librarypath", System.getProperty("org.lwjgl.librarypath"));

        JboxController game = new JboxController("test", JboxController.LOAD_MODE_REMOTE, null);
        //this.app = app;

        try {
            app = new AppGameContainer(game);
            app.setTargetFrameRate(100);
            app.setDisplayMode(800, 600, false);
            app.setAlwaysRender(true);
            app.setForceExit(false);
            app.setClearEachFrame(true);
        

            app.start();
        } catch (SlickException ex) {
            Logger.getLogger(TestEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }

    public AppGameContainer getApp() {
        return app;
    }

    public void setApp(AppGameContainer app) {
        this.app = app;
    }
    
}
