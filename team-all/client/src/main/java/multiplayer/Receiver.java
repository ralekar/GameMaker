package multiplayer;

import java.util.HashMap;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import utility.Helper;
import view.GamePlayerView;
import view.MultiPlayerOption;

import loader.GamePackage;

import action.GameAction;

public class Receiver implements MessageListener {

	private static Receiver receiver = new Receiver();
	private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
			.getLogger(Receiver.class);

	private boolean receiveStatus = true;

	public static Receiver getInstanceOf() {
		return receiver;
	}

	private void receiveData() throws JMSException {
		SessionFactory.getInstanceOf().createConnection();
		Subscribe.getInstanceOf().receiveData();
	}

	public void runGame() {
		try {
			receiveData();
		} catch (JMSException e) {
			LOG.error(e);
		}

	}

	public boolean isReceiveStatus() {
		return receiveStatus;
	}

	public void setReceiveStatus(boolean receiveStatus) {
		this.receiveStatus = receiveStatus;
	}

	public void onMessage(Message message) {
		ObjectMessage objectMessage = (ObjectMessage) message;
		LOG.info("object message issssssssssssssss" + message);

		try {
			Object data = objectMessage.getObject();
			if (data instanceof GamePackage) {
				GamePackage gameData = (GamePackage) data;
				Protocol protocol = new Protocol();
				protocol.setGameState(gameData);
				LOG.info("The data is GamePackage type "
						+ objectMessage.getObject());
				//MultiPlayerOption.getInstanceOf().readyGameFrame();
			} else if (data instanceof HashMap) {
				HashMap<String, Object> actionData = (HashMap<String, Object>) data;
				Protocol protocol = new Protocol();
				protocol.setMultiplayerAction(actionData);
				LOG.info("The data is HashMap type" + objectMessage.getObject());
			} else if (data instanceof String) {
				String textMessage = (String) data;
				LOG.info("The text recieved is " + textMessage);
				if (textMessage.equals("startgame__^^")) {
					GamePlayerView gamePlayerView = Helper.getsharedHelper()
							.getGamePlayerView();
					gamePlayerView.getGameEnginePanel().startGame();
				}
				else if (textMessage.equals("ready__^^")) {
					MultiPlayerOption.getInstanceOf().setReady(true);
					MultiPlayerOption.getInstanceOf().startGameFrame();
				}
				else {
					String joinee = textMessage.substring(0,
							textMessage.indexOf("+") - 1);
					String gameName = textMessage.substring(
							textMessage.indexOf("+") + 1,
							textMessage.indexOf("#") - 1);
					if (joinee != null && gameName != null) {
						MultiPlayerOption.getInstanceOf().acceptUserFrame(
								joinee, gameName);
					} else {
						LOG.info("Noise data received");
					}
				}
			}

			// SessionFactory.getInstanceOf().closeSession();

			// Come back to this.This is very important in multiplayer!!!!!

		} catch (NullPointerException ne) {
			LOG.info("No message received");
		} catch (JMSException e) {
			LOG.error(e);
		}

	}

	public void setQueueName(Destination queueName) {
	}

	public void subscribe(String receivingQueueName) throws JMSException {
		Subscribe.getInstanceOf().setQueue(receivingQueueName);
	}
}
