package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;
import domain.*;
import javax.persistence.CascadeType;
import javax.persistence.Table;
@Entity
@Table(name="Przedmiot")
public class Subject implements Serializable {
    private static final long serialVersionUID = 3L;
    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Column(unique = true)
    private int subject_id;
    @Column(name="nazwa_przedmiotu",length = 20)
    private String subject_name;

    //1-n:Oceny
    @OneToMany(cascade = CascadeType.ALL,targetEntity = Marks.class, mappedBy = "subject")
    private List<Marks> marks;

    public Subject(int subject_id, String subject_name, List<Marks> marks) {
        this.subject_id = subject_id;
        this.subject_name = subject_name;
        this.marks = marks;
    }
    public Subject(){};

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public List<Marks> getMarks() {
        return marks;
    }

    public void setMarks(List<Marks> marks) {
        this.marks = marks;
    }
    
 

}
