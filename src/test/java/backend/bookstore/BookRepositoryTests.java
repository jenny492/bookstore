package backend.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import backend.bookstore.domain.Book;
import backend.bookstore.domain.BookRepository;

@DataJpaTest
public class BookRepositoryTests {

    @Autowired
    private BookRepository bRepository;

    private Book createTestBook() {
        return new Book("Test title", "Test Author", "12345", 10.0, "1234", null);
    }

    @Test
    public void createNewBook() {
        Book book = createTestBook();
        bRepository.save(book);
        assertThat(book.getId()).isNotNull();
    }

    @Test
    public void searchBookByTitle() {
        Book book = createTestBook();
        bRepository.save(book);
        List<Book> books = bRepository.findByTitle("Test title");
        assertThat(books.get(0).getTitle()).isEqualTo("Test title");
    }

    @Test
    public void deleteBook() {
        Book book = createTestBook();
        bRepository.save(book);
        bRepository.delete(book);
        List<Book> books = bRepository.findByTitle("Test title");
        assertThat(books).hasSize(0);
    }

}
