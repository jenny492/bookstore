package backend.bookstore.web;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import backend.bookstore.domain.Book;
import backend.bookstore.domain.BookRepository;

@Controller
public class BookController {

    //@Autowired
    private BookRepository bookRepository;

    // construktorin injektointi
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/index")
    public @ResponseBody String indexMethod() {
        return "Bookstore";
    }

    // tuo kaikki kirjat
    @GetMapping("/booklist")
    public String bookList(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "booklist";
    }

    @GetMapping("/add")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "addbook";
    }

    @PostMapping("/save")
    public String save(Book book) {
        bookRepository.save(book);
        return "redirect:booklist";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long studentId, Model model) {
        bookRepository.deleteById(studentId);
        return "redirect:../booklist";
    }

    @GetMapping("path")
    public String edit(Model model) {
        model.addAttribute("book", model);
        return new String();
    }

    @Bean
    public CommandLineRunner demo(BookRepository bookRepository) {
        return (args) -> {
            // luodaan muutama kirja
            Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", "9780743273565", 10.99, "1925");
            Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", "9780061120084", 7.99, "1960");
            Book book3 = new Book("1984", "George Orwell", "9780451524935", 9.99, "1949");
            // tallennetaan kirjat h2-tietokantaan
            bookRepository.save(book1);
            bookRepository.save(book2);
            bookRepository.save(book3);
        };
    }
}
