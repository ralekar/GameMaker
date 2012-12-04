package action;

import game.engine.slick2d.player.SoundRepo;
import java.io.Serializable;
import model.SpriteModel;
import org.newdawn.slick.Sound;

@SuppressWarnings("serial")
public class ActionPlaySound implements GameAction, Serializable {

    private String soundFile;
    @SuppressWarnings("unused")
	private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
            .getLogger(ActionPlaySound.class);

    public ActionPlaySound(String soundFile) {
        super();
        this.soundFile = soundFile;
    }

    @Override
    public void doAction(SpriteModel model) {
        Sound sound = SoundRepo.getSounds().get(soundFile);
        sound.play();
    }

    public String getSoundFile() {
        return soundFile;
    }
}
