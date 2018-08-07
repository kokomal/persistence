/**  
 * @Title: TestUserDao.java   
 * @Package: yuanjun.chen.dao   
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author: 陈元俊     
 * @date: 2018年8月7日 下午5:05:14   
 * @version V1.0 
 * @Copyright: 2018 All rights reserved. 
 */
package yuanjun.chen.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import yuanjun.chen.PersistenceApplication;
import yuanjun.chen.dao.mybatis.mapper.UserMapper;
import yuanjun.chen.dao.mybatis.model.User;

/**   
 * @ClassName: TestUserDao   
 * @Description: TODO(这里用一句话描述这个类的作用)   
 * @author: 陈元俊 
 * @date: 2018年8月7日 下午5:05:14  
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PersistenceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestUserDao {
    @Autowired
    UserMapper userMapper;
    
    @Test
    public void insertTest() {
        User u = new User();
        u.setPassword("1234");
        u.setPhone("12345678900");
        u.setUserName("灭霸");
        int aff = userMapper.insert(u); // 回写需要在mapper里面设置
        System.out.println("aff=" + aff + " and u.id = " + u.getUserId());
    }
}
