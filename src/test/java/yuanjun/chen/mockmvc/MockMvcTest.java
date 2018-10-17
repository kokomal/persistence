/**
 * @Title: MockMvcTest.java
 * @Package: yuanjun.chen.mockmvc
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: 陈元俊
 * @date: 2018年10月17日 下午4:47:31
 * @version V1.0
 * @Copyright: 2018 All rights reserved.
 */
package yuanjun.chen.mockmvc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import yuanjun.chen.PersistenceApplication;

/**
 * @ClassName: MockMvcTest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author: 陈元俊
 * @date: 2018年10月17日 下午4:47:31
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PersistenceApplication.class)
@ActiveProfiles("stg")
@WebAppConfiguration
public class MockMvcTest {
    @Autowired
    private WebApplicationContext webContext;

    private MockMvc mockMvc;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
    }

    @Test
    public void testFree() throws Exception { // 无密可以直接访问
        mockMvc.perform(get("/free"))
            .andExpect(status().isOk())
                .andExpect(view().name("free"))
                    .andExpect(model().attributeDoesNotExist("haha"));
    }
    
//    @Test
//    public void testReadingList() throws Exception { // 无密可以直接访问
//        mockMvc.perform(get("/readingList"))
//            .andExpect(status().is4xxClientError());
//    }
}
