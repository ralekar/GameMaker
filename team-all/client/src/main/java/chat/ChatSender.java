package chat;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import model.Player;

import org.apache.activemq.ActiveMQConnectionFactory;

import utility.Constants;


public class ChatSender implements Runnable, Sender {

	private MessageProducer producer;
	private static boolean messagePresent = false;
	private static String message;
	private Session session;
	private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
			.getLogger(ChatSender.class);
			
	public ChatSender(String topicName) {
		try {
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(Constants.ActiveMQConnect);

			// Create a Connection
			Connection connection = connectionFactory.createConnection();
			connection.start();

			// Create a Session
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// Create the destination (Topic or Queue)
			Topic topic= session.createTopic(topicName);

			// Create a MessageProducer from the Session to the Topic or Queue
			producer = session.createProducer(topic);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			Thread chatSenderThread=new Thread(this);
			chatSenderThread.start();
		} catch (Exception e) {
			LOG.debug(e.getMessage());
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				if (messagePresent) {
					TextMessage textMessage = session.createTextMessage(message);
					producer.send(textMessage);
					messagePresent = false;

				}
				Thread.sleep(200);
			} catch (Exception e) {
				LOG.debug(e.getMessage());
			}
		}

	}

	public void sendMessage(String text) {
		message = text;
		messagePresent = true;
	}
	public static void sendChatRequest(String username) {
		message = ":"+Player.getInstance().getUsername()+":"+username;
		messagePresent = true;
	}
}
