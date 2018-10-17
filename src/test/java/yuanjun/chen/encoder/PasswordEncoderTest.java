/**  
 * @Title: PasswordEncoderTest.java   
 * @Package: yuanjun.chen.encoder   
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author: 陈元俊     
 * @date: 2018年10月17日 上午8:17:54   
 * @version V1.0 
 * @Copyright: 2018 All rights reserved. 
 */
package yuanjun.chen.encoder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import yuanjun.chen.PersistenceApplication;
import yuanjun.chen.dao.jpa.reading.ReaderRepository;

/**   
 * @ClassName: PasswordEncoderTest   
 * @Description: 测试密码加密，和用户查询jpa的操作  
 * @author: 陈元俊 
 * @date: 2018年10月17日 上午8:17:54  
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PersistenceApplication.class)
@ActiveProfiles("stg")
public class PasswordEncoderTest {
    @Test
    public void encodePassword() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        //加密"0"
        String encode = bCryptPasswordEncoder.encode("password");
        System.out.println(encode);
    }
    
    @Autowired
    private ReaderRepository readerRepository;
    
    @Test
    public void testQueryUser() {
        UserDetails userDetails = readerRepository.getOne("craig");
        if (userDetails != null) {
            System.out.println("User is " + userDetails);
        } else {
            System.out.println("User not found");
        }
    }
}
