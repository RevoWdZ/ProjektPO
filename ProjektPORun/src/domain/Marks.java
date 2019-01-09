package domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import javafx.scene.control.ChoiceBox;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;
import domain.*;

@Entity
@Table(name = "Oceny")
public class Marks implements Serializable {
    private static final long serialVersionUID = 2L;


    
    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Column(unique = true)
    private long ocena_id;
    @Column(name = "ocena")
    private int mark;

    //n-1:Przedmiot
    @ManyToOne(cascade = CascadeType.ALL)
    @GeneratedValue
    @JoinColumn(name = "subject_id")
    private Subject subject;
    //n-1:Student
    @ManyToOne(cascade = CascadeType.ALL)
    @GeneratedValue
    @JoinColumn(name = "student_id")
    private Student student;

    public Marks(long ocena_id, int mark, Subject subject, Student student) {
        this.ocena_id = ocena_id;
        this.mark = mark;
        this.subject = subject;
        this.student = student;
    }
    public Marks(){};

    public long getOcena_id() {
        return ocena_id;
    }

    public void setOcena_id(long ocena_id) {
        this.ocena_id = ocena_id;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

   
}
