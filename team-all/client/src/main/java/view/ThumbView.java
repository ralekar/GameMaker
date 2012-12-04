package view;

import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileView;

public class ThumbView extends FileView{
		public Icon getIcon(File f){
	  Icon icon=null;
	  if(isImageFile(f.getPath())){
	   icon=createImageIcon(f.getPath(),null);
	  }
	  return icon;
	 }
	 private ImageIcon createImageIcon(String path,String description) {
	  if (path != null) {
	   ImageIcon icon=new ImageIcon(path);
	   java.awt.Image img = icon.getImage() ; 
	   java.awt.Image newimg = img.getScaledInstance( 32, 32,  java.awt.Image.SCALE_SMOOTH ) ;
	   return new ImageIcon(newimg);
	  } else {
	   System.err.println("Couldn't find file: " + path);
	   return null;
	   }
	}
	 private boolean isImageFile(String filename){
		//return true if this is image
		return true;
	
	}
}	