package lookandfeel;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import org.apache.log4j.Logger;

public class ThemeHandler {

    private static final Logger LOG = Logger.getLogger(ThemeHandler.class.getName());
    public final static String THEME_ACRYL = "com.jtattoo.plaf.acryl.AcrylLookAndFeel";
    public final static String THEME_SYNTHETICA = "de.javasoft.plaf.synthetica.SyntheticaMauveMetallicLookAndFeel";
    public final static String THEME_NAP = "napkin.NapkinLookAndFeel";
    public final static String THEME_DARK = "com.jtattoo.plaf.noire.NoireLookAndFeel";
    public final static String THEME_NIMBUS = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
    public final static String THEME_WOOD = "de.javasoft.plaf.synthetica.SyntheticaClassyLookAndFeel";
    public final static String THEME_SILVER = "de.javasoft.plaf.synthetica.SyntheticaSilverMoonLookAndFeel";
    public final static String THEME_SYSTEM = UIManager.getSystemLookAndFeelClassName();

    
    public static boolean applyTheme(String themeName) {
        try {
            UIManager.setLookAndFeel(themeName);
        } catch (Exception e) {
            LOG.error(e);
            return false;
        }
        return true;
    }

    public static void showThemePanel() {
        Map<String, String> themeNames = new HashMap<String, String>(3);
        themeNames.put("Pink", THEME_SYNTHETICA);
        themeNames.put("Black", THEME_ACRYL);
        //themeNames.put("Sketch", THEME_NAP);
        themeNames.put("Dark", THEME_DARK);
        themeNames.put("System", THEME_SYSTEM);
        themeNames.put("Gray", THEME_NIMBUS);
        themeNames.put("Wood", THEME_WOOD);
        themeNames.put("Silver", THEME_SILVER);

        String chosen = (String) JOptionPane.showInputDialog(
                new JPanel(),
                "Selet a Theme you want",
                "",
                JOptionPane.PLAIN_MESSAGE,
                null, themeNames.keySet().toArray(),
                null);

        if (chosen != null) {
            ThemeHandler.applyTheme(themeNames.get(chosen));
        }
    }

    private ThemeHandler() {
    }
}
