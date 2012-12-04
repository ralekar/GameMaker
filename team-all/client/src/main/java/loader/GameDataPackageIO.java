package loader;

import action.GameAction;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import controller.GameController;
import controller.KeyListenerController;
import eventlistener.EventListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import model.SpriteModel;
import org.apache.log4j.Logger;

public class GameDataPackageIO {

    private static final Logger log = Logger.getLogger(GameDataPackageIO.class.getName());

    public static void saveGamePackageToFile(File file, GamePackage game) throws IOException {
        log.debug("saving game to: " + file.getName());

        XStream xstream = new XStream(new StaxDriver());
        xstream.alias("gameObject", SpriteModel.class);
        xstream.alias("gameAction", GameAction.class);
        xstream.alias("gameEventListener", EventListener.class);
        xstream.alias("game", GamePackage.class);
        xstream.alias("gameController", GameController.class);
        xstream.alias("keyController", KeyListenerController.class);

        FileWriter writer = new FileWriter(file);
        xstream.toXML(game, writer);
        log.debug("saving done");
    }

    public static String convertGamePackageToString(GamePackage game) {
        log.debug("saving game to String");

        XStream xstream = new XStream(new StaxDriver());
        xstream.alias("gameObject", SpriteModel.class);
        xstream.alias("gameAction", GameAction.class);
        xstream.alias("gameEventListener", EventListener.class);
        xstream.alias("game", GamePackage.class);
        xstream.alias("gameController", GameController.class);
        xstream.alias("keyController", KeyListenerController.class);

        return xstream.toXML(game);
    }

    public static GamePackage loadGamePackageFromFile(String file) {
        log.debug("loading game from: " + file);
        XStream xstream = new XStream(new StaxDriver());
        xstream.alias("gameObject", SpriteModel.class);
        xstream.alias("gameAction", GameAction.class);
        xstream.alias("gameEventListener", EventListener.class);
        xstream.alias("game", GamePackage.class);
        xstream.alias("gameController", GameController.class);
        xstream.alias("keyController", KeyListenerController.class);


        GamePackage gameCopy = (GamePackage) xstream.fromXML(file);
        log.debug("loading done");
        return gameCopy;
    }

    public static GamePackage loadGamePackageFromLocalFile(String file) {
        log.debug("loading game from: " + file);
        XStream xstream = new XStream(new StaxDriver());
        xstream.alias("gameObject", SpriteModel.class);
        xstream.alias("gameAction", GameAction.class);
        xstream.alias("gameEventListener", EventListener.class);
        xstream.alias("game", GamePackage.class);
        xstream.alias("gameController", GameController.class);
        xstream.alias("keyController", KeyListenerController.class);


        GamePackage gameCopy = (GamePackage) xstream.fromXML(GameDataPackageIO.class.getResourceAsStream(file));
        log.debug("loading done");
        return gameCopy;
    }

    private GameDataPackageIO() {
    }
}
