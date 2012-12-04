package chat;

import javax.jms.Connection;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.Player;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import utility.Constants;
public class AuthReceiver implements Runnable {
	private static final Logger LOG = Logger.getLogger(AuthReceiver.class);

	private MessageConsumer consumer;

	public AuthReceiver(String QueueName) {
		try {
			// Create a ConnectionFactory
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(Constants.ActiveMQConnect);

			// Create a Connection
			Connection connection = connectionFactory.createConnection();
			connection.start();


			// connection.setExceptionListener((ExceptionListener) this);

			// Create a Session
			Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);

			// Create the destination (Topic or Queue)
			//Destination destination = session.createQueue("CHAT");
			Queue queue= session.createQueue(QueueName);
			
			// Create a MessageConsumer from the Session to the Topic or Queue
			consumer = session.createConsumer(queue);
			Thread chatReceiverThread=new Thread(this);
			chatReceiverThread.start();
		} catch (Exception ex) {
			LOG.error(ex);
		}

	}

	@Override
	public void run() {
		while (true) {
			try {
				// Wait for authentication
				
				Message message = consumer.receive();
				if (message instanceof TextMessage) {
					TextMessage textMessage = (TextMessage) message;
					String text = textMessage.getText();
                                        String token="Authenticated:";
                                        if(text.startsWith(token))
                                        {
                                            Player.getInstance().setFacebookLogin(true);
                                            Player.getInstance().setUsername(text.substring(token.length(), text.length()));
                                            Player.getInstance().setAvatarURL("https://graph.facebook.com/"+Player.getInstance().getUsername()+"/picture");
                                            JFrame frame = new JFrame();
                                            JOptionPane.showMessageDialog(frame,"Login with facebook successful");
                                            break;
                                        }
				}
			} catch (Exception e) {
				LOG.error(e);
			}
		}
	}
}
