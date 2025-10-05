// rajapinta tuo paljon valmiita metodeja, jotka on valmiita sql-lauseita
package backend.bookstore.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> { // Book ja long on id:n tietotyyppi
    //kun Bookista puuttui jsonIgnoreProperties, niin bookRestControllerissa .findAll() ei toiminut -> vaihto JpaRepositoryyn 
    //"korjasi" ongelman ja .findAll() toimi taas, mutta kirjojen listaus toimi vähän hassusti
    //kun Bookissa ja Categoryssa on JsonIgnoreProperties, niin CrudRepo toimii

    Iterable<Book> findByAuthor(String author);
    List<Book> findByTitle(String title);
}