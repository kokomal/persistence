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

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.alibaba.fastjson.JSONObject;
import yuanjun.chen.PersistenceApplication;
import yuanjun.chen.dao.mybatis.mapper.UserMapper;
import yuanjun.chen.dao.mybatis.model.User;
import yuanjun.chen.service.InsertUserService;

/**
 * @ClassName: TestUserDao
 * @Description: mybatis增删改查操作
 * @author: 陈元俊
 * @date: 2018年8月7日 下午5:05:14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PersistenceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestUserDao {
    @Autowired
    UserMapper userMapper;

    @Test
    public void insertTest1() {
        User user = new User();
        user.setPassword("1234");
        user.setPhone("12345678900");
        user.setUserName("灭霸");
        int aff = userMapper.insert(user); // 回写需要在mapper里面设置
        System.out.println("aff=" + aff + " and userId = " + user.getUserId());
    }

    @Test
    public void insertTest2() {
        User user = new User();
        user.setPassword("4321");
        user.setPhone("7675475477");
        user.setUserName("卡魔拉");
        int aff = userMapper.insert(user); // 回写需要在mapper里面设置
        System.out.println("aff=" + aff + " and userId = " + user.getUserId());
    }

    @Test
    public void selectTest() {
        List<User> users = userMapper.selectAllUser();
        System.out.println(JSONObject.toJSONString(users));
    }

    @Test
    public void deleteTest() {
        List<User> users = userMapper.selectAllUser();
        System.out.println(JSONObject.toJSONString(users));
        for (User user : users) {
            System.out.print("deleting " + user.getUserName());
            int aff = userMapper.deleteByPrimaryKey(user.getUserId());
            if (aff > 0) {
                System.out.println(" success!");
            }
        }
    }
    
    @Autowired
    private InsertUserService insertUserService;
    
    @Test
    public void testTransaction() throws Exception {
        User user1 = getMeCaptainAmerica("美国队长1");
        try {
            insertUserService.insertUserDoomFail1(user1); // 抛异常，不回滚
        } catch (Exception e) {
            e.printStackTrace();
        }
        User user2 = getMeCaptainAmerica("美国队长2");
        try {
            insertUserService.insertUserDoomFail2(user2); // 抛异常，回滚
        } catch (Exception e) {
            e.printStackTrace();
        }
        User user3 = getMeCaptainAmerica("美国队长3");
        try {
            insertUserService.insertUserDoomFail3(user3); // 抛异常，回滚
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 最终只有1个美国队长1
    }

    private static User getMeCaptainAmerica(String str) {
        User user = new User();
        user.setPassword("9999");
        user.setPhone("123123123");
        user.setUserName(str);
        return user;
    }
}
