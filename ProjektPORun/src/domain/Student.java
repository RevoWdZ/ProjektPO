package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.metamodel.SetAttribute;
import org.hibernate.annotations.GenericGenerator;
import domain.*;

@Entity
public class Student implements Serializable {

    private static final long serialVersionUID = 4L;

    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Column(unique = true)
    private int student_id;
    @Column(length = 15, nullable = false,name="imie")
    private String name;
    @Column(length = 15, nullable = false,name="nazwisko")
    private String UserName;

    //n-1: Grupa
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "group_id")
    private Group group;

    //1 - n: Oceny
    @OneToMany(targetEntity = Marks.class, mappedBy = "student", cascade = CascadeType.REMOVE)
    private Set<Marks> marks;

    public Student(int student_id, String name, String UserName, Group group, Set<Marks> marks) {
        this.student_id = student_id;
        this.name = name;
        this.UserName = UserName;
        this.group = group;
        this.marks = marks;
    }

    public Student(){};

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Set<Marks> getMarks() {
        return marks;
    }

    public void setMarks(Set<Marks> marks) {
        this.marks = marks;
    }

    public int setMark(int selectedMark) {
        return selectedMark;
    }

}
