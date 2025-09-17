// rajapinta tuo paljon valmiita metodeja, jotka on valmiita sql-lauseita
package backend.bookstore.domain;

import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> { // Book ja long on id:n tietotyyppi
    Iterable<Book> findByAuthor(String author);
    //Optional<Book> findById(ID id);
}