package backend.bookstore.web;

import org.springframework.web.bind.annotation.RestController;
import backend.bookstore.domain.Book;
import backend.bookstore.domain.BookRepository;
import backend.bookstore.domain.CategoryRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
//Rest palauttaa tietoa json-muodossa tekstinä
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
    
    @PostMapping("/books")
    public Book saveBook(@RequestBody Book book) {     
        return bookRepository.save(book);
    }

    @PutMapping("/books/{id}")
    public Book editBook(@PathVariable("id") Long bookId, @RequestBody Book editedBook) { 
        return bookRepository.findById(bookId)
        .map(book -> {
            book.setAuthor(editedBook.getAuthor());
            book.setCategory(editedBook.getCategory());
            book.setIsbn(editedBook.getIsbn());
            book.setPrice(editedBook.getPrice());
            book.setPublicationYear(editedBook.getPublicationYear());
            book.setTitle(editedBook.getTitle());
            return bookRepository.save(book);
        })  
        .orElse(null);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBook(@PathVariable("id") Long bookId) {
        bookRepository.deleteById(bookId);
        //tee joku tarkistus
    }
}
