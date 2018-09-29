package yuanjun.chen.dao.jpa.onetoonefk.model;

import javax.persistence.*;

/**   
 * @ClassName: BookDetail   
 * @Description: 对应表book_detail  
 * @author: 陈元俊 
 * @date: 2018年9月29日 下午12:52:34  
 */
@Entity
@Table(name = "book_detail")
public class BookDetail {
    private Integer id;
    private Integer numberOfPages;
    private Book book;

    public BookDetail() {}

    public BookDetail(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "number_of_pages")
    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    @OneToOne(mappedBy = "bookDetail")
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
