/**
 * @Title: JpaManyToManyExtraTableTestStg.java
 * @Package: yuanjun.chen.dao.jpa.stg
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: 陈元俊
 * @date: 2018年9月29日 下午3:56:09
 * @version V1.0
 * @Copyright: 2018 All rights reserved.
 */
package yuanjun.chen.dao.jpa.stg;

import java.util.HashSet;
import java.util.Set;
import javax.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import yuanjun.chen.PersistenceApplication;
import yuanjun.chen.dao.jpa.manytomanyexttable.StudentRepository;
import yuanjun.chen.dao.jpa.manytomanyexttable.TeacherRepository;
import yuanjun.chen.dao.jpa.manytomanyexttable.model.Student;
import yuanjun.chen.dao.jpa.manytomanyexttable.model.Teacher;

/**
 * @ClassName: JpaManyToManyExtraTableTestStg
 * @Description: stg环境测试，这个环境对应实体db
 * @author: 陈元俊
 * @date: 2018年9月29日 下午3:56:09
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PersistenceApplication.class)
@ActiveProfiles("stg")
public class JpaManyToManyExtraTableTestStg {
    private static final Logger logger = LogManager.getLogger(JpaManyToManyExtraTableTestStg.class);

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void testinsert(){
        try {
            run2();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /*勿将内存里面new出来的pojo塞入而不持久化*/
    @Test
    public void testinsertTricky() {
        studentRepository.deleteAll();
        teacherRepository.deleteAll();
        try {
            run1();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testNormal() {
        studentRepository.deleteAll();
        teacherRepository.deleteAll();
        final Teacher teacherA = new Teacher("诸葛亮");
        final Student studentA = new Student("姜维");
        
        Set<Student> stu = new HashSet<>();
        stu.add(studentA);
        teacherA.setStudents(stu);
        
        teacherRepository.saveAndFlush(teacherA); // 非常和谐，师徒均进入
    }
    
    @Test
    public void testNormal2() {
        studentRepository.deleteAll();
        teacherRepository.deleteAll();
        final Teacher teacherA = new Teacher("玄奘");
        final Student studentA = new Student("孙悟空");
        
        Set<Teacher> tea = new HashSet<>();
        tea.add(teacherA);
        studentA.setTeachers(tea);
        
        studentRepository.saveAndFlush(studentA); // 如果是孙悟空发起，那么只会持久化孙悟空，玄奘不见了！！！
    }
    
    @Test
    public void testNormal3() {
        studentRepository.deleteAll();
        teacherRepository.deleteAll();
        final Teacher teacherA = new Teacher("雪斋");
        final Student studentA = new Student("元康");
        teacherRepository.saveAndFlush(teacherA); // 先持久化二者
        studentRepository.saveAndFlush(studentA); 
        
        Set<Teacher> tea = new HashSet<>(); // 再以学生角度关联
        tea.add(teacherA);
        studentA.setTeachers(tea);
        
        studentRepository.saveAndFlush(studentA); // 二者肯定都落库，但关联失败！！！
    }
    
    @Transactional
    private void run1() {
     // save a couple of Students
        final Teacher teacherA = new Teacher("贝利");
        final Teacher teacherB = new Teacher("马拉多纳");
        final Teacher teacherC = new Teacher("贝肯鲍尔");

        // 保存2个学生，但老师还没有创建
        studentRepository.saveAll(new HashSet<Student>() {
            {
                add(new Student("C罗", new HashSet<Teacher>() {
                    {
                        add(teacherA); // 假设C罗师从贝利马拉多纳
                        add(teacherB);
                    }
                }));

                add(new Student("鲁尼", new HashSet<Teacher>() {
                    {
                        add(teacherA); // 假设鲁尼师从贝利贝肯鲍尔。此时贝肯鲍尔没有在db里面！
                        add(teacherC);
                    }
                }));
            }
        });

        // fetch all Students
        for (Student stu : studentRepository.findAll()) {
            logger.info(stu.toString());
        }

        // save a couple of teachers
        final Student StudentA = new Student("C罗"); // 内存里面的C罗
        final Student StudentB = new Student("梅西"); // 内存里面的梅西

        teacherRepository.saveAll(new HashSet<Teacher>() {
            {
                add(new Teacher("克林斯曼", new HashSet<Student>() {
                    {
                        add(StudentA); // C罗会有4个老师，前面的贝利，马拉多纳和这里的克林斯曼贝肯鲍尔
                        add(StudentB); // 梅西1个老师克林斯曼
                    }
                }));

                add(new Teacher("贝肯鲍尔", new HashSet<Student>() {
                    {
                        add(StudentA); // 按道理贝肯鲍尔会记住前面的鲁尼
                    }
                }));
            }
        });

        // fetch all teachers
        for (Teacher teacher : teacherRepository.findAll()) {
            logger.info(teacher.toString());
        }
        
    }

    @Transactional
    public void run2() throws Exception {
        // save a couple of Students
        final Teacher teacherA = new Teacher("孔子");
        final Teacher teacherB = new Teacher("韩非子");
        final Teacher teacherC = new Teacher("鬼谷子");

        // 保存2个学生，但老师还没有创建
        studentRepository.saveAll(new HashSet<Student>() {
            {
                add(new Student("颜回", new HashSet<Teacher>() {
                    {
                        add(teacherA); // 假设颜回师从孔子韩非子
                        add(teacherB);
                    }
                }));

                add(new Student("宰我", new HashSet<Teacher>() {
                    {
                        add(teacherA); // 假设宰我师从孔子鬼谷子。此时鬼谷子没有在db里面！
                        add(teacherC);
                    }
                }));
            }
        });

        // fetch all Students
        for (Student stu : studentRepository.findAll()) {
            logger.info(stu.toString());
        }

        // save a couple of teachers
        final Student StudentA = new Student("子路"); // 如果是颜回，宰我，这样做是不是有毒？
        final Student StudentB = new Student("子端"); // 这个在内存中的pojo如何与db的关联？

        teacherRepository.saveAll(new HashSet<Teacher>() {
            {
                add(new Teacher("老子", new HashSet<Student>() {
                    {
                        add(StudentA); // 保存了老子荀子，但学生不在db里面
                        add(StudentB);
                    }
                }));

                add(new Teacher("荀子", new HashSet<Student>() {
                    {
                        add(StudentA);
                        add(StudentB);
                    }
                }));
            }
        });

        // fetch all teachers
        for (Teacher teacher : teacherRepository.findAll()) {
            logger.info(teacher.toString());
        }
        
        /* 此时db里面有4个学生，2个老师(老子，荀子)
         * step1，颜回，宰我持久化，但名为孔子和韩非子的老师还没有持久化，因此孔子、韩非子不会进入db
         * step2，老子，荀子持久化，此时在内存里面的子路，子端，由于Teacher是关系的拥有者，因此会顺带将
         * student进行插入，因此子路子端就这样进入db了
        */
    }
}
