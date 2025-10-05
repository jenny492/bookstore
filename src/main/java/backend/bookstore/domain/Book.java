package backend.bookstore.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "books")
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    //alemmille muuttujille @NotEmpty checkit
    
    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "pub_year")
    private int pubYear;

    @Column(name = "isbn")
    private String isbn;

    @Min(value = 0)
    @Column(name = "price")
    private double price;

    @JsonIgnoreProperties("books")
    @ManyToOne
    @JoinColumn(name = "category_id") // categoryid on tietokannassa oleva fk
    private Category category;

    public Book() {
    }

    public Book(String title, String author, String isbn, double price, int publicationYear, Category category) {
        this.title = title;
        this.author = author;
        this.pubYear = publicationYear;
        this.isbn = isbn;
        this.price = price;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPubYear() {
        return pubYear;
    }

    public void setPubYear(int publicationYear) {
        this.pubYear = publicationYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
