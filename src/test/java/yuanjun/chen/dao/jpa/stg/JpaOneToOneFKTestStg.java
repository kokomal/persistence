/**  
 * @Title: JpaOneToOneFKTest.java   
 * @Package: yuanjun.chen.dao.jpa   
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author: 陈元俊     
 * @date: 2018年9月29日 下午12:57:28   
 * @version V1.0 
 * @Copyright: 2018 All rights reserved. 
 */
package yuanjun.chen.dao.jpa.stg;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import yuanjun.chen.PersistenceApplication;
import yuanjun.chen.dao.jpa.onetoonefk.BookRepository;
import yuanjun.chen.dao.jpa.onetoonefk.model.Book;
import yuanjun.chen.dao.jpa.onetoonefk.model.BookDetail;

/**   
 * @ClassName: JpaOneToOneFKTest 
 * @Description: stg环境测试，这个环境对应实体db 
 * @author: 陈元俊 
 * @date: 2018年9月29日 下午12:57:28  
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PersistenceApplication.class)
@ActiveProfiles("stg")
public class JpaOneToOneFKTestStg {
    private static final Logger logger = LogManager.getLogger(JpaOneToOneFKTestStg.class);
    @Autowired
    private BookRepository bookRepository;
    
    @Test
    public void testOneToOneFkUsingRealTestDb() {
        // save a couple of books
        List<Book> books = new ArrayList<>();
        books.add(new Book("Book X", new BookDetail(49)));
        books.add(new Book("Book Y", new BookDetail(59)));
        books.add(new Book("Book Z", new BookDetail(69)));
        bookRepository.saveAll(books);

        // fetch all books
        for (Book book : bookRepository.findAll()) {
            logger.info(book.toString());
        }
    }
}
