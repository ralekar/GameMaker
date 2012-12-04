package action;

import static org.junit.Assert.*;

import org.junit.*;

public class ActionPlaySoundTest {
	
	@Test
	public void testSoundFile()throws Exception{
		String soundFile = "sound_file";
		
		ActionPlaySound actionPlaySound = new ActionPlaySound(soundFile);
		String result = actionPlaySound.getSoundFile();
		assertEquals(soundFile, result);
	}
}
