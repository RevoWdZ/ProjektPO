
package model.dao;

import domain.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import static org.hibernate.boot.registry.StandardServiceRegistryBuilder.destroy;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    static SessionFactory sessionFactory;
    static Session session;
    private static ServiceRegistry registry;
    private static Transaction transact;
    private static Configuration config;
    
    
    public static void StartSessionFactory(){
        try {
            config = new Configuration().configure("META-INF\\hibernate.cfg.xml");
                          config.addAnnotatedClass(Group.class);
                          config.addAnnotatedClass(Marks.class);
                          config.addAnnotatedClass(Subject.class);
                          config.addAnnotatedClass(Student.class);

            registry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();

            sessionFactory = config.buildSessionFactory(registry);
        }   
        catch (HibernateException ex) {
             
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
  
    public Session openSessionWithoutTrans(){
          session = getSessionFactory().openSession();
          return session;
    }
    
    public Session openSessionWithTrans(){
           session = getSessionFactory().openSession();
           transact = session.beginTransaction();
           return session;
    }
    
    public void closeSessionWithoutTrans(){
            getCurrentLocalSession().close();
    }
    
    public void closeSessionWithTrans(){
            getCurrentLocalTransac().commit();
            getCurrentLocalSession().close();
    }
    
    public Session getCurrentLocalSession(){
        return session;
    }
    
    public Transaction getCurrentLocalTransac(){
        return transact;
    }
    
    public static void CloseConnection(){
        sessionFactory.close();
        destroy(registry);
    }
    
    public static void OpenConnection(){
        StartSessionFactory();
    }
}