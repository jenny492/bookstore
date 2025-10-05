package backend.bookstore;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import backend.bookstore.domain.Book;
import backend.bookstore.web.BookRestController;

@SpringBootTest
@AutoConfigureMockMvc
public class BookRestTests {

	@Autowired
	private BookRestController brcontroller;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

	@Test
	public void bookRepositoryCcontrollerContextLoads() throws Exception {
		assertThat(brcontroller).isNotNull();
	}

    // testataan että http status ok
    @Test
    public void testGetAllBooks() throws Exception {
        mockMvc.perform(get("/books")).andExpect(status().isOk());
    }

    // luodaan kirja ja katsotaan, että admin pystyy lähettämään pyynnön
    @Test
    @WithMockUser(username = "admin", authorities = { "ADMIN"}) 
    public void testAddBookAsAdmin() throws Exception {
        Book book = new Book("Test title", "Test Author", "12345", 10.0, 1234, null);

        mockMvc.perform
        (post("/books")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(book)))
        .andExpect(status().isOk());
    }

    // Testataan, että user ei pysty luomaan kirjaa. Vastaus on tällöin 403 Forbidden
    @Test
    @WithMockUser(username = "user", authorities = { "USER"}) 
    public void testAddBookAsUser() throws Exception {
        Book book = new Book("Test title", "Test Author", "12345", 10.0, 1234, null);

        mockMvc.perform
        (post("/books")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(book)))
        .andExpect(status().isForbidden());
    }


}
