package yuanjun.chen.dao.jpa.manytomanyexttable.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Teacher {
    private int id;
    private String name;
    private Set<Student> students;

    public Teacher() {

    }

    public Teacher(String name) {
        this.name = name;
    }

    public Teacher(String name, Set<Student> students) {
        this.name = name;
        this.students = students;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinTable(name = "student_teacher", joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id", referencedColumnName = "id"))
    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        String result = String.format("Teacher [id=%d, name='%s']%n", id, name);
        if (students != null) {
            for (Student student : students) {
                result += String.format("Student [id=%d, name='%s']%n", student.getId(), student.getName());
            }
        }
        return result;
    }
}
