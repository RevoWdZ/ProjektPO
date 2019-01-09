
package model.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.SessionFactory;

public abstract class DAO<T, ID extends Serializable>extends HibernateUtil  {

    public abstract List<T> getAll();
    
    public void update(T t){
        openSessionWithTrans();
        getCurrentLocalSession().update(t);
        closeSessionWithTrans();
    };
    public void delete(T t){
        openSessionWithTrans();
        getCurrentLocalSession().delete(t);
        closeSessionWithTrans();
    };
    public void create(T t){
        openSessionWithTrans();
        getCurrentLocalSession().save(t);
        closeSessionWithTrans();
    };
}