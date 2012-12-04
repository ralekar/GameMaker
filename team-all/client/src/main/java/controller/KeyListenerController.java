package controller;

import eventlistener.EventListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class KeyListenerController implements KeyListener {

    private List<EventListener> keyEvents;

    public KeyListenerController() {
    	this.keyEvents = new ArrayList<EventListener>();
    }
    
    
    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int pressed = ke.getKeyCode();
        for (EventListener event : keyEvents) {
        	HashMap<String,Object> map = new HashMap<String,Object>();
        	map.put("keypressed", new Integer(pressed));
        	event.checkEvent(map);
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }
    
    public void registerListener(EventListener listener){
    	this.keyEvents.add(listener);
    }
    
    public void unregisterListener(EventListener listener){
    	this.keyEvents.remove(listener);
    }
	public List<EventListener> getKeyEvents() {
        return keyEvents;
    }
	
    public void setKeyEvents(List<EventListener> keyEvents) {
        this.keyEvents = keyEvents;
    }
}
