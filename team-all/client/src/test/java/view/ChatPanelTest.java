package view;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class ChatPanelTest {

	ChatPanel chatPanel;

	@Before
	public void setUp() {
		chatPanel = Mockito.mock(ChatPanel.class);
	}

	@Test
	public void testCheckTest() {
		try {
			Assert.assertEquals(false, chatPanel.checkText("hey this chat #$#@ message should not be sent!!!"));
		} catch (Exception e) {
			Logger.getLogger(ChatPanelTest.class).debug(e.getMessage());
		}
	}
}