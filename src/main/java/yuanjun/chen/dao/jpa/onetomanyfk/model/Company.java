package yuanjun.chen.dao.jpa.onetomanyfk.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "company")
public class Company {
    private int id;
    private String name;
    private Set<Employee> employees;

    public Company() {}

    public Company(String name) {
        this.name = name;
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

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(String.format("Company[id=%d, name='%s']%n", id, name));
        if (employees != null) {
            for (Employee emp : employees) {
                result.append(String.format("Employee[id=%d, name='%s']%n", emp.getId(), emp.getName()));
            }
        }
        return result.toString();
    }
}
