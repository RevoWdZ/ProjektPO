
package model.dao;

import java.util.ArrayList;
import java.util.List;
import domain.Group;

public class GroupDAO extends DAO<Group, Integer>{
    @Override
    public List<Group> getAll() {
         List<Group> lista = openSessionWithTrans().createCriteria(Group.class).list();
         closeSessionWithTrans();
         return lista;
    }
    
    public GroupDAO(){};


    
    
}