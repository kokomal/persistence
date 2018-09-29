package yuanjun.chen.dao.jpa.onetoonefk;

import org.springframework.data.jpa.repository.JpaRepository;
import yuanjun.chen.dao.jpa.onetoonefk.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{
}