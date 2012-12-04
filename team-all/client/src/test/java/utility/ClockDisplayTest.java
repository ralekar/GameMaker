package utility;

import org.junit.Assert;
import org.junit.Test;

public class ClockDisplayTest {

	@Test
	public void testReset() {
		ClockDisplay.getInstance().setHour(4);
		ClockDisplay.getInstance().setMinute(24);
		ClockDisplay.getInstance().setSecond(56);
		Assert.assertEquals(ClockDisplay.getInstance().getHour(), 4);
		Assert.assertEquals(ClockDisplay.getInstance().getMinute(), 24);
		Assert.assertEquals(ClockDisplay.getInstance().getSecond(), 56);
		ClockDisplay.getInstance().reset();
		Assert.assertEquals(0, ClockDisplay.getInstance().getHour());
		Assert.assertEquals(0, ClockDisplay.getInstance().getMinute());
		Assert.assertEquals(0, ClockDisplay.getInstance().getSecond());
	}
}