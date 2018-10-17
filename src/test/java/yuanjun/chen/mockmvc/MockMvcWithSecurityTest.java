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

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import yuanjun.chen.PersistenceApplication;
import yuanjun.chen.dao.jpa.reading.Reader;
import yuanjun.chen.dao.jpa.reading.ReaderRepository;
import yuanjun.chen.dao.jpa.reading.ReadingBook;
import yuanjun.chen.dao.jpa.reading.ReadingListRepository;
import static org.hamcrest.Matchers.*;
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
public class MockMvcWithSecurityTest {
    @Autowired
    private WebApplicationContext webContext;

    private MockMvc mockMvc;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webContext).apply(springSecurity()).build();
    }

    @Test
    public void testReadingList() throws Exception { // 此时访问受限页面就悲剧了
        mockMvc.perform(get("/readingList")).andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "http://localhost/login"));
    }
    
    @Autowired
    private ReaderRepository readerRepository;
    @Autowired
    private ReadingListRepository readingListRepository;
    
    @Test
    @WithMockUser(username="craig", password="password", roles="READER") // 账密传入
    @WithUserDetails(value="craig", userDetailsServiceBeanName = "myUserDetailService") // 注意此处以craig用户登录
    public void testCraig() throws Exception {
        Reader exp = readerRepository.getOne("craig");
        List<ReadingBook> readingList = readingListRepository.findByReader(exp);
        System.out.println("readingList size = " + readingList.size());
        mockMvc.perform(get("/readingList"))
        .andExpect(status().isOk())
        .andExpect(view().name("readingList"))
        .andExpect(model().attribute("reader", samePropertyValuesAs(exp)))
        .andExpect(model().attribute("books", hasSize(readingList.size()))); // 目前有11条记录
    }
}
