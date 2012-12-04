package view;

import facade.Facade;
import interfaces.Drawable;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JPanel;

import model.SpriteModel;

import utility.Constants;
import utility.Helper;
import utility.SpriteList;


public class GamePanel extends JPanel implements KeyListener{

	private static final org.apache.log4j.Logger LOG = 
			org.apache.log4j.Logger.getLogger(GamePanel.class);
    private static final long serialVersionUID = 1L;
    private Image image;
    private Graphics graphic;
    private List<Drawable> drawables;
    private String currentLayer;

   
    BindMouseMove movingAdapt = new BindMouseMove();
	ResizeHandler gameobjectResize = new ResizeHandler();

    public GamePanel(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.drawables = new ArrayList<Drawable>(10);
        this.currentLayer=Constants.ALL_LAYERS;
        addMouseMotionListener(movingAdapt);
        addMouseListener(movingAdapt);
        addMouseWheelListener(gameobjectResize);
        addKeyListener(this);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getSize().width, this.getSize().height);
        
        
        
        if(currentLayer.equalsIgnoreCase(Constants.ALL_LAYERS))
        {
        	for (Drawable d : drawables) {
                if (d.isVisible() ) {
                    d.draw(g);
                }
            }
        }
        else{
	        for (Drawable d : drawables) {
	            if (d.isVisible() && d.getLayer().equalsIgnoreCase(getCurrentLayer())) {
	                d.draw(g);
	            }
	        }
        }
    }

    @Override
    public void update(Graphics g) {
        if (image == null) {
            image = createImage(this.getSize().width, this.getSize().height);
            graphic = image.getGraphics();
        }

        graphic.setColor(this.getBackground());
        graphic.fillRect(0, 0, this.getSize().width, this.getSize().height);
        graphic.setColor(this.getForeground());

        paint(graphic);
        g.drawImage(image, 0, 0, this);
    }
    
    public void unregisterModel(SpriteModel model) {
    	ArrayList<Drawable> toBeRemoved = new ArrayList<Drawable>();
    	for(Drawable drawable : drawables){
    		if(drawable instanceof SpriteView){
    			SpriteView view = (SpriteView) drawable;
        		if(view.getModel().equals(model))
        			toBeRemoved.add(drawable);
    		}
    	}
    		
    	for(Drawable drawable : toBeRemoved)
    		unregisterDrawable(drawable);
    }

    public void registerDrawable(Drawable d) {
        this.drawables.add(d);
    }

    public void unregisterDrawable(Drawable d) {
        this.drawables.remove(d);
    }

    public void removeAllDrawables() {
        this.drawables.clear();
    }
    

    class BindMouseMove extends MouseAdapter {
	    private int x;
	    private int y;
	    @Override
	    public void mousePressed(MouseEvent event) {

	    	x = event.getX();
	    	y = event.getY();

	    	boolean foundObject = false;
	    	Collection<SpriteModel> spriteModels = SpriteList.getInstance().getSpriteList();
	    	List<SpriteModel> selectedSpriteModels = new ArrayList<SpriteModel>();
	    	SpriteModel selectedSpriteModel = null;
	    	
	    	GameMakerView gameMakerView = Helper.getsharedHelper().getGameMakerView();

	    	for(SpriteModel model:spriteModels){
	    		if (model.getBoundingBox().contains(x, y)){
	    			selectedSpriteModel = model;
	    			selectedSpriteModels.add(model);
	    			foundObject = true;
	    		}
	    		else{
	    			if(!foundObject)
	    				gameMakerView.clearAll();
	    		}
	    		gameMakerView.updateProperties();
	    	}
	    	

	    	SpriteList.getInstance().setSelectedSpriteModel(selectedSpriteModel);
	    	SpriteList.getInstance().setSelectedSpriteModels(selectedSpriteModels);

	    	Facade facade = Helper.getsharedHelper().getFacade();
	    	int clickCount = event.getClickCount();
	    	if(event.getButton() == MouseEvent.BUTTON3)
	    	{
	    		GamePanel gamePanel = Helper.getsharedHelper().getGamePanel();
	    		LOG.debug("Click Caught");
	    		if(selectedSpriteModels.size() == 0 || 
	    				(selectedSpriteModels.size()==1 && selectedSpriteModels.get(0).getId().equals("background")))
	    		{
	    			new PopupMenus(gamePanel, event.getX(),event.getY(),PopupMenus.Type.Game);    			
	    		    
	    		}	    		
	    		else
	    		{
	    			new PopupMenus(gamePanel, event.getX(),event.getY(),PopupMenus.Type.Sprite);
	    		}
	    	}  
	    	else if(clickCount == 2){
	    		if(foundObject){
	    			facade.createDuplicateSpriteModel(selectedSpriteModel);
	    		}
	    			
	    	}
	
	    	requestFocus();
	    }
	    
	    
	    	@Override
	    	public void mouseDragged(MouseEvent event) {
	    		int dx = event.getX() - x;
	    		int dy = event.getY() - y;
	    		double tempx;
	    		double tempy;
	    		
	    		SpriteModel selectedGameObject = SpriteList.getInstance().getSelectedSpriteModel();
	    		if(selectedGameObject == null)
	    			return;

	    		tempx = selectedGameObject.getPosX();
				tempx += dx;
				selectedGameObject.setPosX(tempx);
				
				tempy = selectedGameObject.getPosY();
				tempy += dy;
				selectedGameObject.setPosY(tempy);
				
				LOG.info("In Mouse Dragged if condition");
				repaint();
				
	    		x += dx;
	    		y += dy;
	    		GameMakerView gameMakerView = Helper.getsharedHelper().getGameMakerView();
	    		gameMakerView.updateProperties();
	    		gameMakerView.removeInfoPanel();
	    		
	    	}
	}
	
	class ResizeHandler implements MouseWheelListener {
		@Override
	    public void mouseWheelMoved(MouseWheelEvent e) 
		{
	        int x = e.getX();
	        int y = e.getY();
	        double tempheight;
	        double tempwidth;
	        if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) 
	        {
	        	Collection<SpriteModel> gameObjects = SpriteList.getInstance().getSpriteList();
	        	SpriteModel topSpriteModel = null;
	    		for(SpriteModel gameObject:gameObjects){
	    			if (gameObject.getBoundingBox().contains(x, y)) {
	    				topSpriteModel = gameObject;
	    			}
	    		}
	    		
	    		if(topSpriteModel != null){
	    			float amount = e.getWheelRotation() * 5f;
					tempwidth = topSpriteModel.getWidth();
					tempwidth += amount;
					if(tempwidth <= 5)
						tempwidth = 5;
					topSpriteModel.setWidth(tempwidth);
					
					tempheight = topSpriteModel.getHeight();
					tempheight += amount;
					if(tempheight <= 5)
						tempheight = 5;
					topSpriteModel.setHeight(tempheight);
					
					repaint();
				
					GameMakerView gameMakerView = Helper.getsharedHelper().getGameMakerView();
					gameMakerView.updateProperties();
	    		}
	        }
	    }
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
	
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}
	
	@Override
	public void keyTyped(KeyEvent arg0) {
		GameMakerView gameMakerView = Helper.getsharedHelper().getGameMakerView();
		GamePanel gamePanel = Helper.getsharedHelper().getGamePanel();
		if(arg0.getKeyChar() == KeyEvent.VK_DELETE){
			gameMakerView.getActionEventPanel().removeSpriteModelFromList(SpriteList.getInstance().getSelectedSpriteModel());
			SpriteList.getInstance().removeSprite(SpriteList.getInstance().getSelectedSpriteModel());
			gamePanel.repaint();

		}
		
	}

	public List<Drawable> getDrawables() {
		return drawables;
	}

	public void setDrawables(List<Drawable> drawables) {
		this.drawables = drawables;
	}

	public String getCurrentLayer() {
		return this.currentLayer;
	}

	public void setCurrentLayer(String currentLayer) {
		this.currentLayer = currentLayer;
	}

	public void reset() {
		Collection<SpriteModel> allSpriteModels = SpriteList.getInstance().getSpriteList();
		for (SpriteModel model : allSpriteModels) {
			unregisterModel(model);
		}
		removeAllDrawables();
		repaint();
		//ClockDisplay.getInstance().setEnabled(false);
	}
}
