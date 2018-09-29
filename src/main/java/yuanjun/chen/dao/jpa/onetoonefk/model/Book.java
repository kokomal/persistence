package yuanjun.chen.dao.jpa.onetoonefk.model;

import javax.persistence.*;

/**   
 * @ClassName: Book   
 * @Description: 对应表book
 * @author: 陈元俊 
 * @date: 2018年9月29日 下午12:52:57  
 */
@Entity
@Table(name = "book")
public class Book {
    private int id;
    private String name;
    private BookDetail bookDetail;

    public Book() {} // @Entity要用默认的无参构造函数，哪怕有其他带参构造函数也不可以

    public Book(String name) {
        this.name = name;
    }

    public Book(String name, BookDetail bookDetail) {
        this.name = name;
        this.bookDetail = bookDetail;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_detail_id")
    public BookDetail getBookDetail() {
        return bookDetail;
    }

    public void setBookDetail(BookDetail bookDetail) {
        this.bookDetail = bookDetail;
    }

    @Override
    public String toString() {
        return String.format("Book[id=%d, name='%s', number of pages='%d']", id, name, bookDetail.getNumberOfPages());
    }
}
