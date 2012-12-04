package multiplayer;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import action.GameAction;

public final class Publish {

	private MessageProducer producer;
	public MessageProducer getProducer() {
		return producer;
	}

	public void setProducer(MessageProducer producer) {
		this.producer = producer;
	}

	private ObjectMessage objectMessage;
	private String topic;
	private final static Publish instance = new Publish();
	private Destination topic1;

	private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
			.getLogger(Publish.class);

	public static Publish getInstanceOf() {
		return instance;
	}

	private Publish() {

	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;

		LOG.info("The TOPIC is-------------- "+topic);
		try {
			topic1 = (Destination) SessionFactory.getInstanceOf().getSession()
					.createQueue(topic);
			LOG.info("sending queue name is"+topic);
			producer = SessionFactory.getInstanceOf().getSession()
					.createProducer(topic1);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

		} catch (JMSException e1) {
			LOG.error(e1);
		}

	}

	public void sendState() {
		try {
			Protocol protocol = new Protocol();
			objectMessage = protocol.createDataAsHost();
			// Tell the producer to send the message
			LOG.debug("S is -------------------" + objectMessage.getObject());
			producer.send(objectMessage);
			// SessionFactory.getInstanceOf().closeSession();
		} catch (JMSException e) {
			LOG.error(e);
		}
	}

	public void sendAcknowledgement(String playerName) throws JMSException {
		Protocol protocol = new Protocol();
		String data = playerName + "+" + this.getTopic();
		objectMessage = protocol.createAcknowledgement(data);
		producer.send(objectMessage);
	}

	public void sendGameAction(GameAction action, String spriteID) {
		try {
			Protocol protocol = new Protocol();

			objectMessage = protocol.createData(action, spriteID);
			producer.send(objectMessage);

		} catch (JMSException e) {
			LOG.error(e);
		}
	}

	public void sendStartSignal() throws JMSException {
		Protocol protocol = new Protocol();
		String data = "startgame__^^";
		objectMessage = protocol.createStartSignal(data);
		producer.send(objectMessage);
	}

	public void sendReadySignal() throws JMSException {
		Protocol protocol = new Protocol();
		String data = "ready__^^";
		objectMessage = protocol.createStartSignal(data);
		producer.send(objectMessage);
		
	}
}
