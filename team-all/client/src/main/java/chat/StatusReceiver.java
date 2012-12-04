package chat;

import javax.jms.Connection;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import model.Player;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import utility.Constants;


public class StatusReceiver implements Runnable {
	private static final Logger LOG = Logger.getLogger(StatusReceiver.class);

	private MessageConsumer consumer;
	private MessageProducer producer;
	private Session session;

	public StatusReceiver() {
		try {
			// Create a ConnectionFactory
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(Constants.ActiveMQConnect);

			// Create a Connection
			Connection connection = connectionFactory.createConnection();
			connection.start();


			// connection.setExceptionListener((ExceptionListener) this);

			// Create a Session
			session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);

			// Create the destination (Topic or Queue)
			//Destination destination = session.createQueue("CHAT");
			Topic topic= session.createTopic("STATUS");
			// Create a MessageConsumer from the Session to the Topic or Queue
			consumer = session.createConsumer(topic);
			producer = session.createProducer(topic);
			Thread ststusReceiverThread=new Thread(this);
			ststusReceiverThread.start();
		} catch (Exception ex) {
			LOG.error(ex);
		}

	}

	@Override
	public void run() {
		while (true) {
			try {
				// Wait for a message
				Message message = consumer.receive();
				if (message instanceof TextMessage) {
					TextMessage textMessage = (TextMessage) message;

					String text = textMessage.getText();
					
					if(text.equals("ALIVE?")){
						if(!(Player.getInstance().getUsername()==null)) {
							String messageUsername="Online:"+Player.getInstance().getUsername();
							TextMessage textMessageUsername = session.createTextMessage(messageUsername);
							producer.send(textMessageUsername);
							
						}
					}
				}
			} catch (Exception e) {
				LOG.error(e);
			}
		}
	}
}
