package chat;

import javax.jms.Connection;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import model.Player;

import org.apache.activemq.ActiveMQConnectionFactory;
import view.ChatPanel;
import view.ChatViewPanel;
import utility.Constants;

public class ChatReceiver implements Runnable {

	private MessageConsumer consumer;
	private ChatPanel chatPanel;
	private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
			.getLogger(ChatReceiver.class);

	public ChatReceiver(ChatPanel chatPanel) {
		try {
			// Create a ConnectionFactory
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
					Constants.ActiveMQConnect);

			// Create a Connection
			Connection connection = connectionFactory.createConnection();
			connection.start();

			// connection.setExceptionListener((ExceptionListener) this);

			// Create a Session
			Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);

			// Create the destination (Topic or Queue)
			// Destination destination = session.createQueue("CHAT");
			Topic topic = session.createTopic("CHAT");
			// Create a MessageConsumer from the Session to the Topic or Queue
			consumer = session.createConsumer(topic);
			Thread chatReceiverThread = new Thread(this);
			this.chatPanel = chatPanel;
			chatReceiverThread.start();
		} catch (Exception ex) {
			LOG.debug(ex.getMessage());
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
					if (text.charAt(0) == ':') {
						String requestor = text
								.substring(text.lastIndexOf(':') + 1);
						if (!requestor.equals(Player.getInstance()
								.getUsername())) {
							String topic = Player.getInstance().getUsername()
									+ ":" + requestor;
							ChatViewPanel.createChatTab(topic);
						}

					} else
						chatPanel.updateChatWindow(text);

				} else {
					chatPanel.updateChatWindow(message.toString());
				}
			} catch (Exception e) {
				LOG.debug(e.getMessage());
			}
		}
	}
}
