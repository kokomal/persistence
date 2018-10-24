package yuanjun.chen.dao.jpa.manytomanyexttable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yuanjun.chen.dao.jpa.manytomanyexttable.model.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{
}