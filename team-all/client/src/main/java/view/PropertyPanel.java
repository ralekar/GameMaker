package view;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.Document;

import model.SpriteModel;

import eventlistener.TextFieldDocumentListener;

import utility.SpriteList;
import utility.enums.PropertyField;

public class PropertyPanel extends JPanel{

	private TextFieldDocumentListener documentListener;
	
	public PropertyPanel() {
		documentListener = new TextFieldDocumentListener();
		createPropertyPanel();			
	}
	

	private JLabel createLabel(String title){
		JLabel label = new JLabel(title);
		return label;
	}
	
	private JTextField createTextField(int length,String name){
		JTextField textField = new JTextField(length);
		Document document = textField.getDocument();
		document.addDocumentListener(documentListener);
		document.putProperty("owner", name);
		document.putProperty("parent", textField);
		return textField;
	}
	
	private void addToPanel(JComponent component,int gridx, int gridy, GridBagConstraints constraints){
		constraints.gridx = gridx;
		constraints.gridy = gridy;
		add(component,constraints);
	}
	
	private void createPropertyPanel(){
	
		setLayout(new GridBagLayout());
		
		String spriteFieldName = PropertyField.SPRITE_NAME.toString();
		String groupFieldName = PropertyField.GROUP_NAME.toString();
		String velocityXFieldName = PropertyField.VELOCITY_X.toString();
		String velocityYFieldName = PropertyField.VELOCITY_Y.toString();
		String widthFieldName = PropertyField.WIDTH.toString();
		String heightFieldName = PropertyField.HEIGHT.toString();
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill  = GridBagConstraints.HORIZONTAL;
		
		addToPanel(createLabel(spriteFieldName), 0, 0, constraints);
		addToPanel(createTextField(20,spriteFieldName), 1, 0, constraints);
		addToPanel(createLabel(groupFieldName), 0, 1, constraints);
		addToPanel(createTextField(20,groupFieldName), 1, 1, constraints);
		addToPanel(createLabel(velocityXFieldName), 0, 2, constraints);
		addToPanel(createTextField(10,velocityXFieldName), 1, 2, constraints);
		addToPanel(createLabel(velocityYFieldName), 0, 3, constraints);
		addToPanel(createTextField(10,velocityYFieldName), 1, 3, constraints);
		addToPanel(createLabel(widthFieldName), 0, 4, constraints);
		addToPanel(createTextField(10,widthFieldName), 1, 4, constraints);
		addToPanel(createLabel(heightFieldName), 0, 5, constraints);
		addToPanel(createTextField(10,heightFieldName), 1, 5, constraints);
	}
	
	public void updateProperties(){
		SpriteModel selectedSpriteModel = SpriteList.getInstance().getSelectedSpriteModel();
		if(selectedSpriteModel != null){
			Component[] components = getComponents();
			for(Component component : components){
				if(component instanceof JTextField){
					JTextField textField = (JTextField) component;
					String owner = (String) textField.getDocument().getProperty("owner");
					
					if(owner.equalsIgnoreCase(PropertyField.SPRITE_NAME.toString()))
						textField.setText(selectedSpriteModel.getId());
					else if(owner.equalsIgnoreCase(PropertyField.GROUP_NAME.toString()))
						textField.setText(selectedSpriteModel.getGroupId());
					else if(owner.equalsIgnoreCase(PropertyField.VELOCITY_X.toString()))
						textField.setText(Double.toString(selectedSpriteModel.getSpeedX()));
					else if(owner.equalsIgnoreCase(PropertyField.VELOCITY_Y.toString()))
						textField.setText(Double.toString(selectedSpriteModel.getSpeedY()));
					else if(owner.equalsIgnoreCase(PropertyField.WIDTH.toString()))
						textField.setText(Double.toString(selectedSpriteModel.getWidth()));
					else if(owner.equalsIgnoreCase(PropertyField.HEIGHT.toString()))
						textField.setText(Double.toString(selectedSpriteModel.getHeight()));
				}
			}
		}
	}
	
	public String getValueForProperty(String property){
		Component[] components = getComponents();
		JTextField requiredTextField = null;
		for(Component component : components){
			if(component instanceof JTextField){
				JTextField textField = (JTextField) component;
				
				String owner = (String) textField.getDocument().getProperty("owner");
				
				if(owner.equalsIgnoreCase(PropertyField.SPRITE_NAME.toString()))
					requiredTextField = textField;
				else if(owner.equalsIgnoreCase(PropertyField.GROUP_NAME.toString()))
					requiredTextField = textField;
				else if(owner.equalsIgnoreCase(PropertyField.VELOCITY_X.toString()))
					requiredTextField = textField;
				else if(owner.equalsIgnoreCase(PropertyField.VELOCITY_Y.toString()))
					requiredTextField = textField;
				else if(owner.equalsIgnoreCase(PropertyField.WIDTH.toString()))
					requiredTextField = textField;
				else if(owner.equalsIgnoreCase(PropertyField.HEIGHT.toString()))
					requiredTextField = textField;
			}
		}
		return requiredTextField.getText();
	}

	public void clearAll() {
		Component[] components = getComponents();
		for(Component component : components){
			if(component instanceof JTextField){
				JTextField textField = (JTextField) component;
				textField.setText(null);
			}
		}
		
	}
}
