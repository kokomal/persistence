package yuanjun.chen.facade.page;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import yuanjun.chen.dao.jpa.reading.AmazonProperties;
import yuanjun.chen.dao.jpa.reading.Reader;
import yuanjun.chen.dao.jpa.reading.ReadingBook;
import yuanjun.chen.dao.jpa.reading.ReadingListRepository;

/**
 * @ClassName: ReadingListController
 * @Description: 如果在pom里面配置security，那么page会重定向到默认login，弹出对话框提示输入user和cmd里面生成的密码
 * @author: 陈元俊
 * @date: 2018年10月16日 下午3:49:40
 */
@Controller
@RequestMapping("/")
public class ReadingListController {

    @Autowired
    private ReadingListRepository readingListRepository;
    @Autowired
    private AmazonProperties amazonConfig;

    @RequestMapping(method = RequestMethod.GET, value = "/fail")
    public void fail() {
        throw new RuntimeException();
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(value = HttpStatus.BANDWIDTH_LIMIT_EXCEEDED)
    public String error() {
        return "error";
    }

    // 这个页面无需auth
    @RequestMapping(value = "free", method = RequestMethod.GET)
    public String free(Model model) {
        return "free";
    }
    
    @RequestMapping(value = "readingList", method = RequestMethod.GET)
    public String readersBooks(Reader reader, Model model) {
        List<ReadingBook> readingList = readingListRepository.findByReader(reader);
        if (readingList != null) {
            model.addAttribute("books", readingList);
            model.addAttribute("reader", reader);
            model.addAttribute("amazonID", amazonConfig.getAssociateId());
        }
        return "readingList";
    }

    @RequestMapping(value = "readingList", method = RequestMethod.POST)
    public String addToReadingList(Reader reader, ReadingBook book) {
        book.setReader(reader);
        readingListRepository.save(book);
        return "redirect:/readingList";
    }

}
