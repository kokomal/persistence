package yuanjun.chen.facade.page;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import yuanjun.chen.dao.jpa.reading.ReadingBook;
import yuanjun.chen.dao.jpa.reading.ReadingListRepository;

@Controller
@RequestMapping("/readingList")
public class ReadingListController {

    private static final String reader = "craig";
    @Autowired
    private ReadingListRepository readingListRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String readersBooks(Model model) {
        List<ReadingBook> readingList = readingListRepository.findByReader(reader);
        if (readingList != null) {
            model.addAttribute("books", readingList);
        }
        return "readingList";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addToReadingList(ReadingBook book) {
        book.setReader(reader);
        readingListRepository.save(book);
        return "redirect:/readingList";
    }

}
