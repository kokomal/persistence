package yuanjun.chen.dao.jpa.onetomanyfk.model;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {
    private int id;
    private String name;
    private Company company;

    public Employee() {

    }

    public Employee(String name) {
        this.name = name;
    }

    public Employee(String name, Company company) {
        this.name = name;
        this.company = company;
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

    @ManyToOne
    @JoinColumn(name = "company_id")
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
