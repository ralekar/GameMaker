//package controllers;
//
//import java.util.List;
//
//import javax.jms.Connection;
//import javax.jms.DeliveryMode;
//import javax.jms.MessageProducer;
//import javax.jms.Session;
//import javax.jms.TextMessage;
//import javax.jms.Topic;
//
//import org.apache.activemq.ActiveMQConnectionFactory;
//import org.junit.Before;
//import org.junit.Test;
//
//import chat.ChatUsersMonitor;
//import chat.ChatUsersReceiver;
//import chat.ChatUsersSender;
//
//public class ActiveChatUsersTest {
//	
//	private Long startTime = System.currentTimeMillis();
//	Thread senderThread;
//
//	@Before
//	public void setUp() throws Exception {
//		ChatUsersMonitor chat = ChatUsersMonitor.getInstance();
//		ChatUsersReceiver receiver = chat.getChatUsersReceiver();
//		MockChatUsersSender sender = new MockChatUsersSender(receiver);
//		senderThread = new Thread(sender);
//		senderThread.start();
//	}
//
//	@Test
//	public void testGetActiveUsers() {
//		ActiveChatUsers users = new ActiveChatUsers();
//		if(users.getActiveUsers().contains("User1") && users.getActiveUsers().contains("User2")) {
//			assert(true);
//		} else {
//			assert(false);
//		}
//	}
//
//	public class MockChatUsersSender implements Runnable {
//
//		private MessageProducer producer;
//		private Session session;
//		private ChatUsersReceiver chatUsersReceiver;
//		private final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
//				.getLogger(ChatUsersSender.class);
//		private static final String ActiveMQConnect = "tcp://129.79.247.5:61616";
//
//		public MockChatUsersSender(ChatUsersReceiver receiver) {
//			try {
//				this.chatUsersReceiver = receiver;
//				ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
//						ActiveMQConnect);
//				Connection connection = connectionFactory.createConnection();
//				connection.start();
//				session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//				Topic topic = session.createTopic("BROADCAST");
//				producer = session.createProducer(topic);
//				producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
//			} catch (Exception e) {
//				LOG.debug(e.getMessage());
//			}
//		}
//
//		@Override
//		public void run() {
//			while (true) {
//				try {
//					if(System.currentTimeMillis()-startTime>5000) {
//						senderThread.stop();
//					}
//					TextMessage textMessage = session.createTextMessage("Alive:User1:TestGame1:Hi There ! How are you");
//					producer.send(textMessage);
//					textMessage = session.createTextMessage("Alive:User2:TestGame1:Hello user1. Im fine thank you !");
//					producer.send(textMessage);
//				} catch (Exception e) {
//					LOG.debug(e.getMessage());
//				}
//			}
//
//		}
//		public MessageProducer getProducer() {
//			return producer;
//		}
//
//		public void setProducer(MessageProducer producer) {
//			this.producer = producer;
//		}
//
//		public Session getSession() {
//			return session;
//		}
//
//		public void setSession(Session session) {
//			this.session = session;
//		}
//
//		public ChatUsersReceiver getChatUsersReceiver() {
//			return chatUsersReceiver;
//		}
//
//		public void setChatUsersReceiver(ChatUsersReceiver chatUsersReceiver) {
//			this.chatUsersReceiver = chatUsersReceiver;
//		}
//	}
//}
