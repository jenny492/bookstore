package backend.bookstore.web;
import backend.bookstore.domain.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import backend.bookstore.domain.Book;
import backend.bookstore.domain.BookRepository;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class BookController {
// spring.io spring beans and dependency
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // construktorin injektointi
    public BookController(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/index")
    public @ResponseBody String indexMethod() {
        return "Bookstore";
    }

    // tuo kaikki kirjat
    @GetMapping("/booklist")
    public String bookList(Model model) {
        model.addAttribute("books", bookRepository.findAll()); // hakee kaikki kirjat select-komennolla ja listaa ne book-muuttujaan
        return "booklist";
    }

    @GetMapping("/add")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute(("categories"), categoryRepository.findAll());
        return "addbook";
    }

    @PostMapping("/save")
    public String save(Book book) {
        bookRepository.save(book);
        return "redirect:booklist";
    }

    //jotta saa vitosen kurssista, niin olisi hyvä toteuttaa jotakin tarkistusta, esimerkiksi tänne
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
        bookRepository.deleteById(bookId);
        return "redirect:../booklist";
    }

    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable("id") Long bookId, Model model) {
        model.addAttribute("book", bookRepository.findById(bookId));
        model.addAttribute(("categories"), categoryRepository.findAll());
        return "editbook";
    }

    @PostMapping("/saveEditedBook")
    public String saveEditedBook(@RequestBody String entity) {
        //TODO: process POST request
        
        return "redirect:/booklist";
    }
    
}
