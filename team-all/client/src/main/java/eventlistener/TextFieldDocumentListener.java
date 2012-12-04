package eventlistener;

import facade.Facade;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.apache.log4j.Logger;

import model.SpriteModel;
import utility.Helper;
import utility.SpriteList;
import utility.enums.PropertyField;
import view.GameMakerView;

public class TextFieldDocumentListener implements DocumentListener{
	private static final Logger LOG = Logger.getLogger(TextFieldDocumentListener.class);

	@Override
	public void changedUpdate(DocumentEvent e) {
		fieldModified(e);
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		fieldModified(e);
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		fieldModified(e);
	}
	
	private void fieldModified(DocumentEvent e){
		//get the owner of this document
        String owner = (String) e.getDocument().getProperty("owner");
        JTextField textField = (JTextField) e.getDocument().getProperty("parent");
        

		Facade facade = Helper.getsharedHelper().getFacade();
		GameMakerView gameMakerView = Helper.getsharedHelper().getGameMakerView();
        
        String textString = textField.getText();
        Double doubleValue = 0.0;
        
        if((!owner.equalsIgnoreCase(PropertyField.SPRITE_NAME.toString())) &&
        		(!owner.equalsIgnoreCase(PropertyField.GROUP_NAME.toString()))){
        	 try {
             	doubleValue = Double.parseDouble(textString);
     		} catch (Exception e2) {
     			LOG.error(e2);
     			return;
     		}
        }
       
        DefaultListModel spriteListIndividualModel = gameMakerView.getSpriteListIndividualModel();
        DefaultListModel spriteListGroupModel = gameMakerView.getSpriteListGroupModel();
        
        if(owner.equalsIgnoreCase(PropertyField.SPRITE_NAME.toString())){
			String previousName = SpriteList.getInstance().getSelectedSpriteModel().getId();
			if(textString != null && !textString.equalsIgnoreCase("")){
				SpriteList.getInstance().getSelectedSpriteModel().setId(textString);
				
				for(int i=0; i<spriteListIndividualModel.size(); i++){
					String tempString = (String) spriteListIndividualModel.get(i);
					if(tempString.equalsIgnoreCase(previousName)){
						spriteListIndividualModel.set(i, textString);
					}
				}
			}
        }
        else if(owner.equalsIgnoreCase(PropertyField.GROUP_NAME.toString())){
			String previousGroupId = SpriteList.getInstance().getSelectedSpriteModel().getGroupId();
			if(textString != null && !textString.equalsIgnoreCase("")){
				SpriteModel spriteModel = SpriteList.getInstance().getSelectedSpriteModel();
				spriteModel.setGroupId(textString);

				for(int i=0; i<spriteListGroupModel.size(); i++){
					String tempString = (String) spriteListGroupModel.get(i);
					if(tempString.equalsIgnoreCase(previousGroupId)){
						spriteListGroupModel.set(i, textString);		
					}
				}
				for(int i=0; i<spriteListGroupModel.size(); i++){
					String tempString = (String) spriteListGroupModel.get(i);
					if(tempString.equalsIgnoreCase(textString))
						spriteListGroupModel.remove(i);
				}
				if(!spriteListGroupModel.contains(spriteModel.getGroupId()))
					spriteListGroupModel.addElement(spriteModel.getGroupId());
			}
			if(textString != null && !textString.equalsIgnoreCase(""))
				SpriteList.getInstance().getSelectedSpriteModel().setGroupId(textString);
        }
        else if(owner.equalsIgnoreCase(PropertyField.VELOCITY_X.toString())){
    		if(textString!= null && !textString.equalsIgnoreCase(""))
				SpriteList.getInstance().getSelectedSpriteModel().setSpeedX(doubleValue);	
		
        }
        else if(owner.equalsIgnoreCase(PropertyField.VELOCITY_Y.toString())){
			if(textString != null && !textString.equalsIgnoreCase(""))
				SpriteList.getInstance().getSelectedSpriteModel().setSpeedY(doubleValue);
		
        }
        else if(owner.equalsIgnoreCase(PropertyField.WIDTH.toString())){
			if(textString != null && !textString.equalsIgnoreCase("")){
				SpriteList.getInstance().getSelectedSpriteModel().setWidth(doubleValue);
				facade.getGamePanel().repaint();
			}
        }
        else if(owner.equalsIgnoreCase(PropertyField.HEIGHT.toString())){
			if(textString != null && !textString.equalsIgnoreCase("")){
				SpriteList.getInstance().getSelectedSpriteModel().setHeight(doubleValue);
				facade.getGamePanel().repaint();
			}
        }
        
		
	}

}
