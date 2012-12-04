
package view;

import java.awt.Component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import facade.Facade;
import model.SpriteModel;

import utility.Helper;
import utility.SpriteList;

public class PopupMenus {
	public enum Type {
		Sprite,
		Game,	
	}

	private JPopupMenu popupMenu;
	
	public PopupMenus(Component comp,int x, int y, PopupMenus.Type type )
	{			 
		popupMenu = new JPopupMenu("Controls");
		if(type == Type.Sprite)
		{
		    createSpriteMenu();
		}
		else if(type == Type.Game)
		{
			 createGameMenu();

		}
	    popupMenu.show(comp, x, y);
    }
	
	private void createSpriteMenu()
	{
		JMenuItem deleteMenuItem = new JMenuItem("Delete");
		popupMenu.add(deleteMenuItem);
		deleteMenuItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					GameMakerView gameMakerView = Helper.getsharedHelper().getGameMakerView();
					GamePanel gamePanel = Helper.getsharedHelper().getGamePanel();
					java.util.List<SpriteModel> smList = SpriteList.getInstance().getSelectedSpriteModels();
					for(SpriteModel sm: smList)
					{
						if(sm.getId().equals("background")) continue;
						SpriteList.getInstance().removeSprite(sm);
						gameMakerView.getActionEventPanel().removeSpriteModelFromList(sm);
					}
					gamePanel.repaint();
					gameMakerView.updateProperties();
				}
			});
	
		   JMenuItem duplicateMenuItem=new JMenuItem("Duplicate");
		   popupMenu.add(duplicateMenuItem);
		   duplicateMenuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					java.util.List<SpriteModel> smList = SpriteList.getInstance().getSelectedSpriteModels();
					Facade facade = Helper.getsharedHelper().getFacade();
					facade.createDuplicateSpriteModel(smList.get(smList.size()-1));
				}
			});

	}
	private void createGameMenu()
	{
		Facade facade = Helper.getsharedHelper().getFacade();
		
		if(facade.getTimer().isRunning())
		{
		    JMenuItem stopItem = new JMenuItem("Stop");
		    popupMenu.add(stopItem);
		    stopItem.addActionListener(new ActionListener() {

				@Override
			public void actionPerformed(ActionEvent e) {
					Facade facade = Helper.getsharedHelper().getFacade();
					facade.stopGame();					
				}
			});
		}else	{
			
			JMenuItem startItem = new JMenuItem("Start");
			popupMenu.add(startItem);
			startItem.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						Facade facade = Helper.getsharedHelper().getFacade();
						facade.startGame();					
					}
				});
		}
		
		JMenuItem saveItem = new JMenuItem("Save");
		popupMenu.add(saveItem);
		saveItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					MenuBarPanel.saveGame();
				}
			});
		JMenuItem loadItem = new JMenuItem("Load");
		popupMenu.add(loadItem);
		loadItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					MenuBarPanel.loadGame();					
				}
			});
		
	}
}
