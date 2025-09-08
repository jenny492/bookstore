package backend.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import backend.bookstore.domain.Book;
import backend.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
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
