
package model.dao;

import java.util.ArrayList;
import java.util.List;
import domain.Subject;

public class SubjectDAO extends DAO<Subject, Integer>{
    @Override
    public List<Subject> getAll() {
         List<Subject> lista = openSessionWithTrans().createCriteria(Subject.class).list();
         closeSessionWithTrans();
         return lista;
    }
    public SubjectDAO(){};


    
    
}