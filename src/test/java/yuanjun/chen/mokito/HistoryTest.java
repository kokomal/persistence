/**  
 * @Title: MockitoTest.java   
 * @Package: yuanjun.chen.mokito   
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author: 陈元俊     
 * @date: 2018年10月18日 下午4:58:17   
 * @version V1.0 
 * @Copyright: 2018 All rights reserved. 
 */
package yuanjun.chen.mokito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import yuanjun.chen.service.History;
import yuanjun.chen.service.QinDynasty;

/**   
 * @ClassName: MockitoTest   
 * @Description: 指鹿为马  
 * @author: 陈元俊 
 * @date: 2018年10月18日 下午4:58:17  
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HistoryTest {
    @Autowired
    private QinDynasty qinDynasty;
    @Autowired
    private History history;
    
    @Test
    public void testYinHuhai() {
        System.out.println(history.tellStoryOfQin()); // DEER
    }
    
    @Test
    public void testZhaogao() {
        System.out.println(qinDynasty.getDeer()); // DEER
    }
}
