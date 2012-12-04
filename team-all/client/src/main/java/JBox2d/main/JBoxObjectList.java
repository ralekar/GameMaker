/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JBox2d.main;


import java.util.LinkedHashMap;

import java.util.Map;

import org.jbox2d.dynamics.Body;

public class JBoxObjectList {

    public static Map<String,Body>mapJBoxSpriteModel;

    public JBoxObjectList() 
    {
    
        mapJBoxSpriteModel=new LinkedHashMap<String,Body>();
        
    }
    
    public void registerSpriteModel(JBoxSpriteModel sprite)
    {
        mapJBoxSpriteModel.put(sprite.getSpriteModel().getId(), sprite.getBody());
    }

    public static Map<String,Body> getListJBoxSpriteModel() {
        return mapJBoxSpriteModel;
    }

    public void setListJBoxSpriteModel(Map mapJBoxSpriteModel)
    {
        this.mapJBoxSpriteModel=mapJBoxSpriteModel;
    }
    
    
    
}

