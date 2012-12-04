package multiplayer;

import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public final class SessionFactory {
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(SessionFactory.class);

	
	private static SessionFactory instance= new SessionFactory();
	private javax.jms.Connection connection;
	private Session session; 
	
	public javax.jms.Connection getConnection() {
		return connection;
	}

	public void setConnection(javax.jms.Connection connection) {
		this.connection = connection;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public static SessionFactory getInstanceOf()
	{
		return instance;
	}
	

	public void createConnection(){
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://129.79.247.5:61616?jms.useAsyncSend=true");

        // Create a Connection
        connection=null;
		try {
			connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		} catch (JMSException e1) {
			LOG.error(e1);
		}
        
		
	}
	
	public void closeSession() throws JMSException{
//		Publish.getInstanceOf().getProducer().close();
//		Subscribe.getInstanceOf().getConsumer().close();
		session.close();
		connection.close();
	}
	
	
}
