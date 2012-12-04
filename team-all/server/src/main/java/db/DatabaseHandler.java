package db;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


/**
 * <p>The implementation of DatabaseService Interface. The port for accessing
 * the actual Database.</p>
 */
public class DatabaseHandler {

    private static final Logger logger = Logger.getLogger(DatabaseHandler.class.getName());
    private static DatabaseHandler instance;
    private SessionFactory sessionFactory;
    private ServiceRegistry serviceRegistry;

    private DatabaseHandler(String dbConfigFile) {
        Configuration configuration = null;

        try {
            configuration = new Configuration();
        } catch (Exception e) {
            logger.error(e);
        }
        logger.info(configuration.toString());
        configuration.configure(dbConfigFile);

        serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();

        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        sessionFactory.getStatistics().setStatisticsEnabled(true);
    }

    private static DatabaseHandler getDatabaseHandler(String dbConfigFile) {
        if (instance == null) {
            instance = new DatabaseHandler(dbConfigFile);
        }
        return instance;

    }

    public static DatabaseHandler getDatabaseHandlerInstance() {
        return DatabaseHandler.getDatabaseHandler("hibernate.cfg.xml");
    }

    public synchronized void cleanUp() {
        sessionFactory.close();
    }

    public synchronized Session getHibernateSession() {
        Session session = sessionFactory.openSession();

        String log = "Distribute new Hibernate Session: " + session.hashCode();
        log += "\nPlease remember to close this Session when finish!";
        logger.info(log);

        return session;

    }
    
    public static List loginQuery(String username, String password){
    	Session session = DatabaseHandler.getDatabaseHandlerInstance().getHibernateSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("username", username));
		criteria.add(Restrictions.eq("password", password));
		List r = criteria.list();
		session.close();
		return r;
    }

    public static List listQuery(String sql) {
        DatabaseHandler db = DatabaseHandler.getDatabaseHandlerInstance();
        Session session = db.getHibernateSession();
        Query query = session.createSQLQuery(sql);
        List returnList = query.list();
        session.close();

        return returnList;
    }

    public static int executeQuery(String sql) {
        DatabaseHandler db = getDatabaseHandlerInstance();
        Session session = db.getHibernateSession();
        Query query = session.createSQLQuery(sql);
        Transaction transaction = session.beginTransaction();
        int numRecordsUpdated = query.executeUpdate();
        transaction.commit();
        session.close();

        return numRecordsUpdated;
    }
    
    public static boolean save(Object dbObject){
    	DatabaseHandler db = getDatabaseHandlerInstance();
        Session session = db.getHibernateSession();
		Transaction t = session.beginTransaction();
		session.save(dbObject);
		t.commit();

		session.close();
		return true;
    }
}
