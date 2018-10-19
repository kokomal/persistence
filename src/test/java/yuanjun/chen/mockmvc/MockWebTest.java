/**
 * @Title: MockWebTest.java
 * @Package: yuanjun.chen.mockmvc
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: 陈元俊
 * @date: 2018年10月18日 下午1:33:10
 * @version V1.0
 * @Copyright: 2018 All rights reserved.
 */
package yuanjun.chen.mockmvc;

import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import yuanjun.chen.PersistenceApplication;
/**
 * @ClassName: MockWebTest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author: 陈元俊
 * @date: 2018年10月18日 下午1:33:10
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PersistenceApplication.class)
@ActiveProfiles("h2")
@WebAppConfiguration
public class MockWebTest {
    @Value("${local.server.port}")
    private int port;

    @Test(expected = Exception.class)
    public void pageNotFound() {
        try {
            RestTemplate rest = new RestTemplate();
            rest.getForObject("http://localhost:{port}/free", String.class, 8080);
            fail("Should result in HTTP 404");
        } catch (Exception e) {
            // assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
            System.out.println(e);
            throw e;
        }
    }

    @Test
    public void testRestTemplate() throws Exception {
        TestRestTemplate rest = new TestRestTemplate();
        String s = rest.getForObject("http://localhost:{port}/free", String.class, 8080);
        System.out.println(s);
    }
    
}
