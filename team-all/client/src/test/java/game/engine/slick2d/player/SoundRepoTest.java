
package game.engine.slick2d.player;

import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.newdawn.slick.Sound;

public class SoundRepoTest {
    private static Map<String, Sound> sounds = new HashMap<String, Sound>(20);
    private static SoundRepo soundRepo;
       
    @Before
    public void setUp() {
        soundRepo = new SoundRepo();
    }
    
    @After
    public void tearDown() {
        soundRepo = null;
    }

    @Test
    public void testGetSounds() {
        
        Map expResult = null;
        Map result = soundRepo.getSounds();
        if(expResult == result)
            assertTrue(false);
        
    }

    @Test
    public void testSetSounds() {
        Map<String, Sound> actualSounds = null;
        soundRepo.setSounds(sounds);
        actualSounds = soundRepo.getSounds();
        Map<String, Sound> expectedSounds = sounds;
        assertEquals(expectedSounds,actualSounds);
        
    }
}
