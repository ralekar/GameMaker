package multiplayer;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;

import org.newdawn.slick.util.Log;

public final class Subscribe {
	private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
			.getLogger(Subscribe.class);

	private String name;
	private MessageConsumer consumer;
	private static final Subscribe instance = new Subscribe();
	private Destination queueName;
	private boolean isMessageListenerSet = false;

	public static Subscribe getInstanceOf() {
		return instance;
	}

	private Subscribe() {

	}

	public void setQueue(String name) throws JMSException {
		this.name = name;
		this.queueName = (Destination) SessionFactory.getInstanceOf()
				.getSession().createQueue(this.name);
		Log.debug("Queue name is --------------------" + name);
		Receiver.getInstanceOf().setQueueName(queueName);

	}

	public MessageConsumer receiveData() {
		try {
			consumer = SessionFactory.getInstanceOf().getSession()
					.createConsumer(queueName);
			setMessageListener();

		} catch (JMSException e) {
			LOG.error(e);
		}
		return consumer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MessageConsumer getConsumer() {
		return consumer;
	}

	public void setConsumer(MessageConsumer consumer) {
		this.consumer = consumer;
	}

	public Destination getQueueName() {
		return queueName;
	}

	public void setQueueName(Destination queueName) {
		this.queueName = queueName;
	}

	public void setMessageListener() throws JMSException {

		if (!this.isMessageListenerSet) {
			consumer.setMessageListener(Receiver.getInstanceOf());
			this.isMessageListenerSet = true;
		}
	}
}
