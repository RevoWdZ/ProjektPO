
package model.dao;

import java.util.ArrayList;
import java.util.List;
import domain.Marks;

public class MarksDAO extends DAO<Marks, Integer>{
    @Override
    public List<Marks> getAll() {
         List<Marks> lista = openSessionWithTrans().createCriteria(Marks.class).list();
         closeSessionWithTrans();
         return lista;
    }
    public MarksDAO(){};


    
    
}