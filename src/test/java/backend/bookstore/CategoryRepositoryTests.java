package backend.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import backend.bookstore.domain.Category;
import backend.bookstore.domain.CategoryRepository;

//@DataJpaTest
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoryRepositoryTests {

    @Autowired
    private CategoryRepository cRepository;

    @Test
    public void createNewCategory() {
        Category category = new Category("Test Category");
        cRepository.save(category);
        assertThat(category.getId()).isNotNull();
    }

    @Test
    public void deleteCategory() {
        Category category = new Category("Test Category");
        cRepository.save(category);
        cRepository.delete(category);

        assertThat(cRepository.findById(category.getId())).isEmpty();
    }

    @Test
    public void searchCategoryByName() {
        Category category = new Category("Test Category");
        cRepository.save(category);
        List<Category> categories = cRepository.findByName("Test Category");
        assertThat(categories.get(0).getName()).isEqualTo("Test Category");
    }
}
