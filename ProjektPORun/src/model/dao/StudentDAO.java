
package model.dao;

import java.util.ArrayList;
import java.util.List;
import domain.Student;
import org.hibernate.Query;
import org.hibernate.Session;

public class StudentDAO extends DAO<Student, Integer>{
    @Override
    public List<Student> getAll() {
         List<Student> lista = openSessionWithTrans().createCriteria(Student.class).list();
         closeSessionWithTrans();
         return lista;
    }

    public StudentDAO(){};


    
    
}