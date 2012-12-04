package JBox2d.main;

import action.ActionCreateSpriteModel;
import eventlistener.EventListener;
import eventlistener.KeyPressedEventListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.swing.JFrame;
import loader.GameDataPackageIO;
import loader.GamePackage;
import model.SpriteModel;
import org.apache.log4j.Logger;
import org.jbox2d.common.Vec2;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.Log;
import utility.SpriteList;
import view.companels.GameBaseLoadPanel;

public class JboxController  extends BasicGame {
    private static final Logger LOG = Logger.getLogger(JboxController.class.getName());
    public final static int LOAD_MODE_LOCAL = 1;
    public final static int LOAD_MODE_REMOTE = 2;
    public final static int LOAD_MODE_REMOTE_NO_UI = 3;
    private GamePackage game;
    private List<SpriteModel> allSpriteModels;
    private Map<String, Image> imagesOfSprites;
    private List<EventListener> eventsForGameController;
    private List<EventListener> keyEvents;
    private PhysicsComponent physicsComponent;
    private HashMap<Integer, KeyPressedEventListener> keyReg;
    private HashMap<Integer, Integer> key2key;

    public JboxController(String title, int loadMode, String[] paras) {
        super(title);
        buildKeyModel();
        buildPhysicsWorld();
        game = loadGameData(loadMode, paras);
    }

    private void buildPhysicsWorld() {
        try {
            physicsComponent = new PhysicsComponent();
        } catch (IOException ex) {
            LOG.error(ex);
        }
    }

    private void buildKeyModel() {
        keyReg = new HashMap<Integer, KeyPressedEventListener>(10);
        key2key = new HashMap<Integer, Integer>(10);
        key2key.put(37, 203);
        key2key.put(39, 205);
        key2key.put(38, 200);
        key2key.put(40, 208);
        key2key.put(32, 57);
    }

    private GamePackage loadGameData(int loadMode, String[] paras) {
        GamePackage gamePackage = null;

        if (loadMode == LOAD_MODE_LOCAL) {
            gamePackage = GameDataPackageIO.loadGamePackageFromLocalFile(paras[0]);
        } else if (loadMode == LOAD_MODE_REMOTE) {
            JFrame jf = new JFrame();
            GameBaseLoadPanel gb = new GameBaseLoadPanel(jf.getRootPane());
            String gameData = gb.readGameDataFromRemoteList();
            gamePackage = GameDataPackageIO.loadGamePackageFromFile(gameData);
        } else if (loadMode == LOAD_MODE_REMOTE_NO_UI) {
            //String gameData = ClientHandler.loadGameBase(paras[0], paras[1], paras[2], new Exception[1]);
    //        gamePackage = GameDataPackageIO.loadGamePackageFromFile(gameData);
        }

        return gamePackage;
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        
        try {
            
            initSpriteImageMapping();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(JboxController.class.getName()).log(Level.SEVERE, null, ex);
        }
        initActionEvents();

    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        
        //updateKeyEventBinding(gc);
        physicsComponent.inputLogic();

    }

    @Override
    public void render(GameContainer gc, Graphics grphcs) throws SlickException 
    {
         physicsComponent.moveLogic();
         renderSpriteImageDraw();   
    }   
    
        
    public Image getImageFromBytes(byte[] imageData, String imageName) {
        Image image = null;
        try {
            image = new Image(new ByteArrayInputStream(imageData), imageName, false);
        } catch (Exception ex) {
            LOG.error(ex);
            return null;
        }
        return image;
    }
    
    public void initActionEvents()
    {
        
        keyEvents = game.getEventsForKeyController();
        for (EventListener keyevent : keyEvents) {
            KeyPressedEventListener key = (KeyPressedEventListener) keyevent;
            int i = key.getKeyRegistered();
            keyReg.put(i, key);
            LOG.debug("read one key: " + key + " " + i);

            //hard coding for ActionCreateSpriteModel
            if (!(key.getAction() instanceof ActionCreateSpriteModel)) {
                continue;
            }

            String gid = "Bomb";
            Image entityImage = imagesOfSprites.get(key.getRegisteredObjectId());
            imagesOfSprites.put(gid, entityImage);
        }

    }
    
    public void renderSpriteImageDraw()
    {
        for (SpriteModel sprite : SpriteList.getInstance().getSpriteList()) {
            if (!sprite.isVisible()) {
                continue;
            }

            if (!imagesOfSprites.containsKey(sprite.getId())) {
                if (imagesOfSprites.containsKey(sprite.getGroupId())) {
                    imageDraw(sprite);
                    continue;
                }
            }
           
            imageDraw(sprite);
            
        }
    

    }       
    
    public void imageDraw(SpriteModel sprite)
    {
        
        Vec2 bodyPostion=physicsComponent.bodies.get(sprite.getId()).getPosition();
        sprite.setPosX((double)bodyPostion.x*30);
        sprite.setPosY((double)bodyPostion.y*30);
        imagesOfSprites.get(sprite.getId()).setRotation(physicsComponent.bodies.get(sprite.getId()).getAngle());
        imagesOfSprites.get(sprite.getId()).draw((float)bodyPostion.x*30, (float)bodyPostion.y*30, (float)sprite.getWidth(), (float) sprite.getHeight());
        Log.info("Sprite X : "+sprite.getPosX());
    }
    
  
    public void initSpriteImageMapping() throws IOException
    {
  /*      //allSpriteModels = game.getSpriteList();
        allSpriteModels=(List<SpriteModel>) game.getSpriteList();
        
        eventsForGameController = game.getEventsForGameController();
        imagesOfSprites = new HashMap<String, Image>();

        for (SpriteModel sprite : allSpriteModels) {

            SpriteList.getInstance().addSprite(sprite);

            String rid = sprite.getImageUrlString();
//            Resources r = ClientHandler.loadResource(rid, "tintin.cs.indiana.edu:8096", "/GameMakerServer/loadResource", new Exception[1]);

            byte[] imageData = r.getResource();
            Image image = getImageFromBytes(imageData, r.getResourceName());

            if (image == null) {
                continue;
            }

            imagesOfSprites.put(sprite.getId(), image);
       //     initSpriteBodyMapping(sprite);

        }
     */   
    }
    
    
  /*  public void initSpriteBodyMapping(SpriteModel sprite)
    {
        try {
            physicsComponent.bodies.put(sprite.getId().toString(),physicsComponent.createBody
                        (sprite.getId() ,"polygon","Dynamic",(float)
                        sprite.getPosX(),(float)sprite.getPreviousY(),(float)sprite.getWidth()
                        ,(float)sprite.getHeight(),0.0f));
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(JboxController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    */
    
    
    public void updateKeyEventBinding(GameContainer gc)
    {
     for (EventListener event : eventsForGameController) {
            event.checkEvent(null);
        }
        for (Integer keycode : keyReg.keySet()) {
            try {
                if (gc.getInput().isKeyDown(key2key.get(keycode.intValue()))) {
                    LOG.debug(keycode + " is Pressed");
                    HashMap<String, Object> map = new HashMap<String, Object>(5);
                    map.put("keypressed", new Integer(keycode));
                    keyReg.get(keycode).checkEvent(map);
                }
            } catch (Exception e) {
                LOG.error("don't worry, it is only temp key-mapping error, we not done yet: " + e);
            }
        }
      
    }
    
    
}
