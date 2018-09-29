/**  
 * @Title: JpaOneToManyFKTestStg.java   
 * @Package: yuanjun.chen.dao.jpa.stg   
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author: 陈元俊     
 * @date: 2018年9月29日 下午3:56:09   
 * @version V1.0 
 * @Copyright: 2018 All rights reserved. 
 */
package yuanjun.chen.dao.jpa.h2;

import java.util.HashSet;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import yuanjun.chen.PersistenceApplication;
import yuanjun.chen.dao.jpa.onetomanyfk.EmployeeCompanyRepository;
import yuanjun.chen.dao.jpa.onetomanyfk.model.Company;
import yuanjun.chen.dao.jpa.onetomanyfk.model.Employee;

/**   
 * @ClassName: JpaOneToManyFKTestStg   
 * @Description: stg环境测试，这个环境对应实体db   
 * @author: 陈元俊 
 * @date: 2018年9月29日 下午3:56:09  
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PersistenceApplication.class)
@ActiveProfiles("h2")
public class JpaOneToManyFKTestH2 {
    private static final Logger logger = LogManager.getLogger(JpaOneToManyFKTestH2.class);
    
    @Autowired
    private EmployeeCompanyRepository employeeCompanyRepository;
    
    @SuppressWarnings("serial")
    @Test
    public void oneToManyTestUingRealTestDb() {
     // save a couple of categories
        final Company companyA = new Company("Alibaba");
        Set<Employee> employeesA = new HashSet<Employee>(){{
            add(new Employee("马云", companyA));
            add(new Employee("张勇", companyA));
            add(new Employee("蔡崇信", companyA));
        }};
        companyA.setEmployees(employeesA);

        final Company companyB = new Company("Tencent");
        Set<Employee> employeesB = new HashSet<Employee>(){{
            add(new Employee("马化腾", companyB));
            add(new Employee("张小龙", companyB));
            add(new Employee("刘炽平", companyB));
        }};
        companyB.setEmployees(employeesB);

        employeeCompanyRepository.saveAll(new HashSet<Company>() {{
            add(companyA);
            add(companyB);
        }});

        // fetch all categories
        for (Company company : employeeCompanyRepository.findAll()) {
            logger.info(company.toString()); // 查找company顺带查出所有的雇员
        }
    }
}
