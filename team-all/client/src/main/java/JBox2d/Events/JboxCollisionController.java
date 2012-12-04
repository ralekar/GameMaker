
package JBox2d.Events;

import JBox2d.Actions.JBoxGameAction;
import JBox2d.main.JBoxSpriteModel;
import JBox2d.main.PhysicsComponent;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.contacts.Contact;
import org.newdawn.slick.util.Log;
import controller.*;
import eventlistener.CollisionEventListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class JboxCollisionController implements ContactListener{

    
    private GameController gameController;
    private JBoxGameAction jboxAction;
    
    
    public JboxCollisionController(GameController gameController)
    {
        PhysicsComponent.getWorld().setContactListener(this);
        this.gameController=gameController;
        
    }
    
    
    
    public void handleContact(Body bodyA,Body bodyB) throws IOException
     {
          Log.debug("Fresh Contact Handle");
          ArrayList<CollisionEventListener>  collisionList =  gameController.getCollisionEvents();
          LinkedHashMap<String,Body> mapJboxSprite=(LinkedHashMap)PhysicsComponent.getInstance().getJboxObjectList().getListJBoxSpriteModel();
          for(CollisionEventListener cEvent:collisionList)
         {
             if (mapJboxSprite.containsKey(cEvent.getRegisteredObjectId1()) && 
                     mapJboxSprite.containsKey(cEvent.getRegisteredObjectId2()))
             {
                 if(!cEvent.getRegisteredGroupId1().equals(cEvent.getRegisteredObjectId2()))
                 {
                   
                      jboxAction.doAction(mapJboxSprite.get(cEvent.getRegisteredObjectId1()));
                      jboxAction.doAction(mapJboxSprite.get(cEvent.getRegisteredObjectId2()));
                     
                 }
             }
         }
        
          
             
         }
     
     
    
    @Override
    public void beginContact(Contact cntct) {
        try {
            handleContact(cntct.getFixtureA().getBody(), cntct.getFixtureB().getBody());
        } catch (IOException ex) {
            Logger.getLogger(JboxCollisionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void endContact(Contact cntct) {
        
    }

    @Override
    public void preSolve(Contact cntct, Manifold mnfld) {
        
    }

    @Override
    public void postSolve(Contact cntct, ContactImpulse ci) {
       
    }

    
    
}
