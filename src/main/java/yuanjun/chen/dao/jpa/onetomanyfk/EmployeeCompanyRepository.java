package yuanjun.chen.dao.jpa.onetomanyfk;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yuanjun.chen.dao.jpa.onetomanyfk.model.Company;

@Repository
public interface EmployeeCompanyRepository extends JpaRepository<Company, Integer>{
}