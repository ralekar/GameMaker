package chat;

import java.util.ArrayList;
import java.util.List;

import javax.jms.Connection;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;


public class ChatUsersReceiver implements Runnable {
	
	private List<String> activeUsers;
	private MessageConsumer consumer;
	private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ChatUsersReceiver.class);
	private static final String ActiveMQConnect = "tcp://129.79.247.5:61616";
	private static final String statusTopic = "STATUS";
	
	public ChatUsersReceiver(){
		activeUsers = new ArrayList<String>();
		try {
			// Create a ConnectionFactory
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnect);

			// Create a Connection
			Connection connection = connectionFactory.createConnection();
			connection.start();


			// connection.setExceptionListener((ExceptionListener) this);

			// Create a Session
			Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);

			// Create the destination (Topic or Queue)
			//Destination destination = session.createQueue("CHAT");
			Topic topic= session.createTopic(statusTopic);
			// Create a MessageConsumer from the Session to the Topic or Queue
			consumer = session.createConsumer(topic);
			
		} catch (Exception ex) {
			LOG.error(ex.getMessage());
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
					//when the user replies back the message should be in the format "Online:Username:Game"
					if(text.startsWith("Online")){
						String[] requiredText = text.split(":");
						activeUsers.add(requiredText[1]);
					}
				}
			} catch (Exception e) {
				LOG.error(e.getMessage());
			}
		}
		

	}


	public List<String> getActiveUsers() {
		return activeUsers;
	}
	
	public void clearActiveUsers(){
		activeUsers.clear();
	}

	public MessageConsumer getConsumer() {
		return consumer;
	}

	public void setConsumer(MessageConsumer consumer) {
		this.consumer = consumer;
	}

	public void setActiveUsers(List<String> activeUsers) {
		this.activeUsers = activeUsers;
	}
	

}
