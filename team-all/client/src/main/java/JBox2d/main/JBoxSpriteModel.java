/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JBox2d.main;

import java.io.IOException;
import model.SpriteModel;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public class JBoxSpriteModel {
    private SpriteModel spriteModel;
    private Body body;
       
      public  JBoxSpriteModel (SpriteModel sm)
     {
         this.spriteModel=sm;
         
     }    

    public SpriteModel getSpriteModel() {
        return spriteModel;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }
    
    public static JBoxSpriteModel  createJBoxSpriteModel(SpriteModel sprite,String shape,String bodyType) throws IOException
    {
        JBoxSpriteModel  model= new JBoxSpriteModel(sprite);
        if(bodyType.equalsIgnoreCase("Dynamic"))
        model.createBody("Polygon","Dynamic");
        if(bodyType.equalsIgnoreCase("Static"))
        model.createBody("Polygon","Static");
        
        return model;
    }
            
   private Body createBody(String shape,String bodyType) throws IOException
	{
		Body body=null;
		if(bodyType.equalsIgnoreCase("Dynamic"))
			body=PhysicsComponent.getInstance().getWorld().createBody(createBodyDefDynamic((float)this.spriteModel.getPosX(),(float)this.spriteModel.getPosY()));
		if (bodyType.equalsIgnoreCase("Static"))
			body=PhysicsComponent.getInstance().getWorld().createBody(createBodyDefDynamic((float)this.spriteModel.getPosX(),(float)this.spriteModel.getPosY()));
		if(body!=null)
		{
			FixtureDef bodyFixture=createFixtureDefinition(0.3f, 0.7f);
                        
			if (shape.equalsIgnoreCase("circle"))
			{
				bodyFixture.shape=createShapeCircle(0);
                                                                bodyFixture.isSensor=true;
			}
			if (shape.equalsIgnoreCase("polygon"))
			{
				bodyFixture.shape=createShapePolygon((float)this.spriteModel.getWidth(),(float)this.spriteModel.getHeight());
			}
                        
			body.createFixture(bodyFixture);
                        
                            
                        this.setBody(body);
                    
		}
                return body;
	}


	public BodyDef createBodyDefDynamic(float x,float y)
	{
		BodyDef bodyDef=new BodyDef();
		bodyDef.position.set(new Vec2(x/30,y/30));
		bodyDef.type=BodyType.DYNAMIC;
                                bodyDef.bullet=true;
                                
		return bodyDef;
	}

	public BodyDef createBodyDefStatic(float x,float y)
	{
		BodyDef bodyDef=new BodyDef();
		bodyDef.position.set(new Vec2(x/30,y/30));
		bodyDef.type=BodyType.STATIC;
                
                return bodyDef;
	}

	public PolygonShape createShapePolygon(float x,float y)
	{
		PolygonShape polygonShape=new PolygonShape();
		polygonShape.setAsBox(x/58,y/58);
                
		return polygonShape;
	}

	public CircleShape createShapeCircle(float x)
	{
		CircleShape circleShape=new CircleShape();
		circleShape.m_radius=x/58;
		return circleShape;
	}

	public FixtureDef createFixtureDefinition(float friction,float restitution)
	{
		FixtureDef fixture=new FixtureDef();
		fixture.restitution=restitution;
		fixture.friction=friction;
                               
                return fixture;
	}

    
    
     
     
     
     
    
    
}
