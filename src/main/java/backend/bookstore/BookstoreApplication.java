package backend.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import backend.bookstore.domain.Book;
import backend.bookstore.domain.BookRepository;
import backend.bookstore.domain.Category;
import backend.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	// tarvii ehkä myöhemmin täällä:
	// private final BookRepository bookRepository;
    // public BookstoreApplication(BookRepository bookRepository) {
    //     this.bookRepository = bookRepository;
    // }

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	//seuraavan voi laittaa myös service-kansioon erikseen
	// lisää tänne myös kategorian lisäys
	@Bean
	public CommandLineRunner demo(BookRepository bookRepository, CategoryRepository categoryRepository) {
		return (args) -> {
			Category fiction = new Category("Fiction");
			Category classic = new Category("Classic");
			categoryRepository.save(fiction);
			categoryRepository.save(classic);
			// luodaan muutama kirja
			Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", "9780743273565", 10.99, "1925", fiction);
			Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", "9780061120084", 7.99, "1960", classic);
			Book book3 = new Book("1984", "George Orwell", "9780451524935", 9.99, "1949", classic);
			// tallennetaan kirjat h2-tietokantaan
			bookRepository.save(book1);
			bookRepository.save(book2);
			bookRepository.save(book3);

			log.info("fetch all books ");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}
			// log.info("haetaan kaikki Naamiot");
			// for (Book book : bookRepository.findByLastName("Naamio")) {
			// 	log.info(book.toString());
			// }
		};
	}
}
