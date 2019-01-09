package projektporun;

import domain.Group;
import domain.Marks;
import domain.Subject;
import domain.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
public class ProjektPORun {

    public static void main(String[] args) {
        Student student1=new Student();
        student1.setName("Adam");
        student1.setUserName("Malysz");
        Student student2=new Student();
        student2.setName("Robert");
        student2.setUserName("Kubica");
        Group grupa=new Group();
        grupa.setGroup_name("LAB1");
        Subject subject=new Subject();
        subject.setSubject_name("History");
        Marks mark=new Marks();
        mark.setMark(1);
        Marks mark1=new Marks();
        mark1.setMark(2);
        Marks mark2=new Marks();
        mark2.setMark(3);
                
        student1.setGroup(grupa);
        mark.setStudent(student1);
        mark1.setStudent(student1);
        mark.setSubject(subject);
       
        student2.setGroup(grupa);
        mark1.setStudent(student2);
        mark2.setStudent(student2);
        mark1.setSubject(subject);
        mark2.setSubject(subject);
        
        Configuration config = new Configuration().configure("META-INF\\hibernate.cfg.xml").addAnnotatedClass(Student.class).addAnnotatedClass(Group.class).addAnnotatedClass(Marks.class).addAnnotatedClass(Subject.class);
        ServiceRegistry serviceRegistry=new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
        SessionFactory factory = config.buildSessionFactory(serviceRegistry);
       
        Session session = factory.openSession();

        Transaction transaction = session.beginTransaction();
        session.save(student2);
        session.save(subject);
        session.save(mark);
        session.save(mark1);
        session.save(mark2);
        session.save(grupa);
        session.save(student1);
        transaction.commit();
        
        session.close();
        factory.close();
        StandardServiceRegistryBuilder.destroy(serviceRegistry);
            
    }

}
