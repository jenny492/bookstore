package backend.bookstore.web;

import org.springframework.web.bind.annotation.RestController;
import backend.bookstore.domain.Book;
import backend.bookstore.domain.BookRepository;
import backend.bookstore.domain.CategoryRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class BookRestController {

    private final BookRepository bookRepository;
    //private final CategoryRepository categoryRepository;
    
    public BookRestController(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        //this.categoryRepository = categoryRepository;
    }    

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    @GetMapping("/books/{id}")
    public Optional<Book> getABook(@PathVariable("id") Long bookId) {
        return bookRepository.findById(bookId);
    }
    
    
}
