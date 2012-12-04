package gameMaker;

import lookandfeel.ThemeHandler;
import utility.Helper;
import view.OptionsFrame;

public class gameMaker {

	
    public static void main(String[] args) throws Exception {
    	//Default theme has been set
    	//TODO -create a new XML file to store the default properties
        ThemeHandler.applyTheme(ThemeHandler.THEME_SILVER);
//        System.setProperty("org.lwjgl.librarypath", System.getProperty("user.dir") + "/target/natives/");
//        System.setProperty("net.java.games.input.librarypath", System.getProperty("org.lwjgl.librarypath"));
//        OptionsFrame optionFrame = new OptionsFrame();
        Helper.getsharedHelper().setOptionsFrame(OptionsFrame.getOptionFrame());
    }
}
