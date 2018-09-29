/**
 * @Title: JpaOneToOneFKTest.java
 * @Package: yuanjun.chen.dao.jpa
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: 陈元俊
 * @date: 2018年9月29日 下午12:57:28
 * @version V1.0
 * @Copyright: 2018 All rights reserved.
 */
package yuanjun.chen.dao.jpa.h2;

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
 * @Description: h2环境测试，这个环境对应虚拟db，可以虚拟建表并加载一些数据进入（
 * 配置为从src/test/resources下面的schema和data.sql）
 * @author: 陈元俊
 * @date: 2018年9月29日 下午12:57:28
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PersistenceApplication.class)
@ActiveProfiles("h2")
public class JpaOneToOneFKTestH2 {
    private static final Logger logger = LogManager.getLogger(JpaOneToOneFKTestH2.class);
    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testOneToOneFkUsingH2() {
        // save a couple of books
        List<Book> books = new ArrayList<>();
        books.add(new Book("Book P", new BookDetail(11)));
        books.add(new Book("Book Q", new BookDetail(33)));
        books.add(new Book("Book K", new BookDetail(55)));
        bookRepository.saveAll(books);

        // fetch all books
        for (Book book : bookRepository.findAll()) {
            logger.info(book.toString());
        }
    }
}
