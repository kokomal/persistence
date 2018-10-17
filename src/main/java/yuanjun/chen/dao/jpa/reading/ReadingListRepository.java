package yuanjun.chen.dao.jpa.reading;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadingListRepository extends JpaRepository<ReadingBook, Long> {
	
	List<ReadingBook> findByReader(Reader reader);

}
