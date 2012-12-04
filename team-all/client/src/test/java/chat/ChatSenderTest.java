/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import org.mockito.InOrder;
import javax.jms.TextMessage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.never;



import static org.junit.Assert.*;

public class ChatSenderTest {
    
    public ChatSenderTest() {
    }
    private ChatSender chatSender;
    private ChatSender mock;
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        chatSender=new ChatSender("test");
         mock= mock(ChatSender.class);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of run method, of class ChatSender.
     */
    @Test
    public void testRun() {
        System.out.println("run");
      
       doCallRealMethod().when(mock).run();
//       reset(mock);
       verify(mock,never()).run();
       
       verifyZeroInteractions(mock);
       verifyNoMoreInteractions(mock);
        }

    /**
     * Test of sendMessage method, of class ChatSender.
     */
      @Test
    public void testSendMessage() {
      //  System.out.println("sendMessage");
        String text = "test";
        mock.sendMessage("test1");
        mock.sendMessage("test2");
        InOrder inOrder=inOrder(mock);
         
        
        
        // TODO review the generated test code and remove the default call to fail.
      //  fail("The test case is a prototype.");
    }

    /**
     * Test of sendChatRequest method, of class ChatSender.
     */
  //  @Test
    public void testSendChatRequest() {
        ChatSender firstMock=mock(ChatSender.class);
        ChatSender secondMock=mock(ChatSender.class);
        firstMock.sendChatRequest("request1");
        secondMock.sendChatRequest("request2");
        InOrder inOrder=inOrder(firstMock,secondMock);
    }
}
