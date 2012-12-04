package view.imagePanel;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import facade.Facade;

import model.SpriteModel;
import utility.Constants;
import utility.Helper;
import utility.Layers;
import utility.enums.PropertyField;
import view.GameMakerView;
import view.GamePanel;
import view.PropertyPanel;

public class ImageActionListener implements ActionListener {
	
	 private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ImageActionListener.class);

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton btn = (JButton) arg0.getSource();
		
		GameMakerView gameMakerView = Helper.getsharedHelper().getGameMakerView();
		PropertyPanel propertyPanel = gameMakerView.getPropertyPanel();
		Facade facade = Helper.getsharedHelper().getFacade();
		GamePanel gamePanel = Helper.getsharedHelper().getGamePanel();

        double sizeX;
        double sizeY;
        double speedX;
        double speedY;
        String layer = null;
        try {
            sizeX = Double.valueOf(propertyPanel.getValueForProperty(PropertyField.WIDTH.toString()));
            sizeY = Double.valueOf(propertyPanel.getValueForProperty(PropertyField.HEIGHT.toString()));
            LOG.debug("got size values for customObject x:" + sizeX + " y:" + sizeY);

        } catch (Exception exception) {
            LOG.error("did not read in size values ... using defaults");
            sizeX = 30;
            sizeY = 30;
        }
        try {
            speedX = Double.valueOf(propertyPanel.getValueForProperty(PropertyField.VELOCITY_X.toString()));
            speedY = Double.valueOf(propertyPanel.getValueForProperty(PropertyField.VELOCITY_Y.toString()));
            LOG.debug("got speed values for customObject x:" + speedX + " y:" + speedY);
        } catch (Exception exception) {
            LOG.error("did not read in speed values ... using defaults");
            speedX = 1;
            speedY = 1;
        }
        try {
            layer = gameMakerView.getLayerBox().getSelectedItem().toString();
            if (layer.equalsIgnoreCase(Constants.ALL_LAYERS)) {
                if (Layers.getInstance().getLayers().size() == 1) {
                    Layers.getInstance().addNewLayer();
                }
                List<String> layers = new ArrayList<String>();
                layers = Layers.getInstance().getLayers();
                layer = layers.get(1);
            }
        } catch (Exception exception) {
            LOG.error("layer value not set", exception);
        }
        if (btn != null) {
            int imageId = 0;
			try{
				imageId = Integer.parseInt(btn.getName());
			}catch (NumberFormatException ex){
				LOG.error(ex);
			}
			
            SpriteModel spriteModel = new SpriteModel(Constants.SPRITE_X, Constants.SPRITE_Y, speedX, speedY, sizeX, sizeY, btn.getName(), layer, imageId);
            gameMakerView.getActionEventPanel().updateSpriteList(spriteModel);
            gameMakerView.updateProperties();
            facade.addSpriteModelToView(spriteModel);
            gamePanel.repaint();
            
            gameMakerView.removeInfoPanel();
            
            Helper.getsharedHelper().setCurrentMessageNumber(1);
            
            Rectangle rect = gamePanel.getBounds();
            int x = (int)spriteModel.getPosX()+rect.x;
            int y = (int)spriteModel.getPosY()+rect.y;
            
            gameMakerView.showInfoPanel(Helper.getsharedHelper().getMessage(1),x,y);
        }

	}

	

}
