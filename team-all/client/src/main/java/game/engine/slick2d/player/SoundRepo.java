package game.engine.slick2d.player;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.newdawn.slick.Sound;

public class SoundRepo {

    @SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(SoundRepo.class.getName());
    private static Map<String, Sound> sounds = new HashMap<String, Sound>(20);

    public static Map<String, Sound> getSounds() {
        return sounds;
    }

    public static void setSounds(Map<String, Sound> sounds) {
        SoundRepo.sounds = sounds;
    }
}
