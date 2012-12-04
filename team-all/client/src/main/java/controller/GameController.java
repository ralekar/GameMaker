package controller;

import eventlistener.CollisionEventListener;
import eventlistener.EventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import model.SpriteModel;
import utility.SpriteList;
import view.GamePanel;

public class GameController implements ActionListener {

    private GamePanel gamePanel;
    private ArrayList<EventListener> events;

    public GameController() {
    	this.events = new ArrayList<EventListener>();
	}
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
        for (EventListener event : events) {
            
            event.checkEvent(null);
        }
        
        for(SpriteModel model : SpriteList.getInstance().getToBeRemovedSpriteModels()){
        	SpriteList.getInstance().removeSprite(model);
        }
        SpriteList.getInstance().getToBeRemovedSpriteModels().clear();
        gamePanel.repaint();
        gamePanel.requestFocusInWindow();
    }



    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    
    public void registerListener(EventListener listener){
    	this.events.add(listener);
    }
    
    public void unregisterListener(EventListener listener){
    	this.events.remove(listener);
    	
    }
	
	public ArrayList<EventListener> getEvents() {
        return events;
    }
	
    public void setEvents(List<EventListener> events) {
        this.events = (ArrayList<EventListener>) events;
    }
    
    public  ArrayList<CollisionEventListener> getCollisionEvents()
    {
        ArrayList<CollisionEventListener> collisionEventListener=new ArrayList<CollisionEventListener>();
       for(EventListener e :events)
       {
           if (e   instanceof CollisionEventListener)
           {
               collisionEventListener.add((CollisionEventListener)e);
           }
       }
       return collisionEventListener;
    }
}
