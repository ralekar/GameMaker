package chat;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;


public class ChatUsersSender implements Runnable {

	private MessageProducer producer;
	private Session session;
	private ChatUsersReceiver chatUsersReceiver;
	private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
			.getLogger(ChatUsersSender.class);
	private static final String ActiveMQConnect = "tcp://129.79.247.5:61616";
	private static final String statusTopic = "STATUS";

	public ChatUsersSender(ChatUsersReceiver receiver) {
		try {
			this.chatUsersReceiver = receiver;
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
					ActiveMQConnect);

			// Create a Connection
			Connection connection = connectionFactory.createConnection();
			connection.start();

			// Create a Session
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// Create the destination (Topic or Queue)
			Topic topic = session.createTopic(statusTopic);

			// Create a MessageProducer from the Session to the Topic or Queue
			producer = session.createProducer(topic);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		} catch (Exception e) {
			LOG.debug(e.getMessage());
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				TextMessage textMessage = session.createTextMessage("ALIVE?");
				chatUsersReceiver.clearActiveUsers();
				producer.send(textMessage);
				Thread.sleep(5000);
			} catch (Exception e) {
				LOG.debug(e.getMessage());
			}
		}

	}

	public MessageProducer getProducer() {
		return producer;
	}

	public void setProducer(MessageProducer producer) {
		this.producer = producer;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public ChatUsersReceiver getChatUsersReceiver() {
		return chatUsersReceiver;
	}

	public void setChatUsersReceiver(ChatUsersReceiver chatUsersReceiver) {
		this.chatUsersReceiver = chatUsersReceiver;
	}

}
