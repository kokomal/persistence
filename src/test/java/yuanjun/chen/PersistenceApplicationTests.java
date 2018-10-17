package yuanjun.chen;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import yuanjun.chen.dao.jpa.reading.Reader;
import yuanjun.chen.dao.jpa.reading.ReadingBook;
import yuanjun.chen.dao.jpa.reading.ReadingListRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersistenceApplicationTests {
    @Autowired
    private ReadingListRepository readingListRepository;
    
	@Test
	public void contextLoads() {
        Reader reader = new Reader();
        reader.setUsername("craig");
        System.out.println("haha input");
        List<ReadingBook> readingList = readingListRepository.findByReader(reader);
        System.out.println(readingList.get(0).getReader().getFullname());
	}

}
