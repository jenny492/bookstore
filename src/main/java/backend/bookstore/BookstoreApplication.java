package backend.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookstoreApplication {

	//private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	// tarvii ehkä myöhemmin täällä:
	// private final BookRepository bookRepository;
    // public BookstoreApplication(BookRepository bookRepository) {
    //     this.bookRepository = bookRepository;
    // }

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	//seuraavan voi laittaa myös service-kansioon erikseen
/* 	@Bean
	public CommandLineRunner demo(BookRepository brepository, CategoryRepository crepository, AppUserRepository urepository) {
		return (args) -> {
			Category fiction = new Category("Fiction");
			Category classic = new Category("Classic");
			crepository.save(fiction);
			crepository.save(classic);
			// luodaan muutama kirja
			Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", "9780743273565", 10.99, "1925", fiction);
			Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", "9780061120084", 7.99, "1960", classic);
			Book book3 = new Book("1984", "George Orwell", "9780451524935", 9.99, "1949", classic);
			// tallennetaan kirjat h2-tietokantaan
			brepository.save(book1);
			brepository.save(book2);
			brepository.save(book3);

			AppUser user1 = new AppUser("user", "$2a$10$tAmc6.J5OZGgW89pWr8EFuQaguv1mnkKMMeawWxXbkL/whVkE.ioO", "USER");
			AppUser admin1 = new AppUser("admin", "$2a$10$HAS4hoxt575J/95NCefPTuRgQl6WqEMlMM8waonVsXez3tk.pOwby", "ADMIN");
			urepository.save(user1);
			urepository.save(admin1);


			log.info("fetch all books ");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());
			}
			// log.info("haetaan kaikki Naamiot");
			// for (Book book : bookRepository.findByLastName("Naamio")) {
			// 	log.info(book.toString());
			// }
		};
	} */
}
