package yuanjun.chen.dao.jpa.manytomanyexttable.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Student {
    private int id;
    private String name;
    private Set<Teacher> teachers;

    public Student() {

    }

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, Set<Teacher> teachers) {
        this.name = name;
        this.teachers = teachers;
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

    @ManyToMany(mappedBy = "students", fetch=FetchType.EAGER)
    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }
    
    @Override
    public String toString() {
        String result = String.format("Student [id=%d, name='%s']%n", id, name);
        if (teachers != null) {
            for (Teacher tea : teachers) {
                result += String.format("Teacher[id=%d, name='%s']%n", tea.getId(), tea.getName());
            }
        }
        return result;
    }
}
