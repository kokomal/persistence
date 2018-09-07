/**
 * @Title: InsertUserService.java
 * @Package: yuanjun.chen.service
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: 陈元俊
 * @date: 2018年8月20日 下午5:21:45
 * @version V1.0
 * @Copyright: 2018 All rights reserved.
 */
package yuanjun.chen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yuanjun.chen.dao.mybatis.mapper.UserMapper;
import yuanjun.chen.dao.mybatis.model.User;

/**
 * @ClassName: InsertUserService
 * @Description: 测试事务，rollbackFor是runtimeException时，如果抛出了Exception（宽于runtime），不回滚
 * @author: 陈元俊
 * @date: 2018年8月20日 下午5:21:45
 */
@Service
public class InsertUserService {
    @Autowired
    UserMapper userMapper;

    /**rollbackFor默认情况下，如果是RuntimeException或Error的话，就返回True，表示要回滚，否则不回滚。*/
    @Transactional
    public void insertUserDoomFail1(User user) throws Exception {
        userMapper.insert(user);
        throw new Exception("wild1"); // 不回滚
    }
    
    @Transactional (rollbackFor = Exception.class)
    public void insertUserDoomFail2(User user) throws Exception {
        userMapper.insert(user);
        throw new Exception("wild2"); // 回滚，因为明显指明了回滚的条件是抛出Exception
    }
    
    @Transactional
    public void insertUserDoomFail3(User user) throws Exception {
        userMapper.insert(user);
        throw new RuntimeException("wild3"); // 回滚，因为抛出RuntimeException
    }
}
