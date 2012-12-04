package multiplayer;

import java.awt.Toolkit;

import javax.jms.JMSException;


import action.GameAction;

public class Sender {
	
	Toolkit toolkit;
	
	public void sendAsHost(String topic)
	{
		SessionFactory.getInstanceOf().createConnection();
		Publish.getInstanceOf().setTopic(topic);

		Publish.getInstanceOf().sendState();
		
	}
	
	public synchronized void sendAsClient(GameAction action, String spriteID)
	{
		SessionFactory.getInstanceOf().createConnection();
		Publish.getInstanceOf().sendGameAction(action, spriteID);
	
	}
	public void sendAcknowledgement(String queue, String playerName) throws JMSException
	{
		SessionFactory.getInstanceOf().createConnection();
		Publish.getInstanceOf().setTopic(queue);
		Publish.getInstanceOf().sendAcknowledgement(playerName);
	}

	public void sendStartSignal(String topic) throws JMSException {
		SessionFactory.getInstanceOf().createConnection();
		Publish.getInstanceOf().setTopic(topic);
		Publish.getInstanceOf().sendStartSignal();
		
	}

	public void readySignal() throws JMSException {
		SessionFactory.getInstanceOf().createConnection();
		Publish.getInstanceOf().sendReadySignal();
		
	}

}