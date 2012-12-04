
package JBox2d.main;


import java.util.ArrayList;
import java.util.List;

import model.SpriteModel;
import utility.Constants;
public class CreateWall {
    
   private List<SpriteModel> listSpriteWall;
    
    public CreateWall()
    {
    
        this.listSpriteWall=new ArrayList<SpriteModel>();
        this.createBottomWall();
        this.createLeftWall();
        this.createRightWall();
        this.createTopWall();
    }
    
    public List<SpriteModel> getWallList(){
        return listSpriteWall;
    }
    
    public void createLeftWall()
    {
      SpriteModel sprite=new SpriteModel(0,Constants.BOARD_Y,0,0,0,Constants.BOARD_HEIGHT,"","");
      sprite.setId("Left Wall");
      listSpriteWall.add(sprite);
    }
    
    public void createRightWall()
    {
        SpriteModel sprite=new SpriteModel(Constants.BOARD_X,Constants.BOARD_Y,0,0,0,Constants.BOARD_HEIGHT,"","");
      sprite.setId("Right Wall");
      listSpriteWall.add(sprite);
          
    }
    
    public void createTopWall()
    {
        SpriteModel sprite=new SpriteModel(Constants.BOARD_X,0,0,0,Constants.BOARD_WIDTH,Constants.BOARD_HEIGHT,"","");
      sprite.setId("Top Wall");
      listSpriteWall.add(sprite);
    }
    
        
       
    
    public void createBottomWall()
    {
      SpriteModel sprite=new SpriteModel(Constants.BOARD_X,Constants.BOARD_Y,0,0,Constants.BOARD_WIDTH,Constants.BOARD_HEIGHT,"","");
      sprite.setId("Bottom Wall");
      listSpriteWall.add(sprite);
      
    }
}
