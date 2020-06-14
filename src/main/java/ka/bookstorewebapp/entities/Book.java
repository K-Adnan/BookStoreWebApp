package ka.bookstorewebapp.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "BOOKS")
@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class Book implements Comparable<Book> {

    @Id
    private long isbn;
    private String title;
    private String summary;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "BOOKS_AUTHORS", joinColumns = {@JoinColumn(name = "BOOK")}, inverseJoinColumns = {@JoinColumn(name = "AUTHOR")})
    @Builder.Default
    private Set<Author> authors = new HashSet<Author>();

    private String category;
    private int numberOfPages;
    private double avgCustomerRating;
    private int numberOfReviews;
    private int quantity;
    private double price;
    private int releaseYear;
    private String imageUrl;
    private int sales;

    public Book() {
        authors = new HashSet<Author>();
    }

    public Book(long isbn) {
        this.isbn = isbn;
    }

    public Book(long isbn, String title, String category, int numberOfPages,
                int releaseYear, double price) {
        super();
        this.isbn = isbn;
        this.title = title;
        this.category = category;
        this.numberOfPages = numberOfPages;
        this.releaseYear = releaseYear;
        this.price = price;
    }

    public void addCustomerRating(int rating) {
        avgCustomerRating = (avgCustomerRating * numberOfReviews + rating) / (numberOfReviews + 1);
        numberOfReviews++;
    }

    public void setAuthor(Author author) {
        if (authors == null) {
            authors = new HashSet<Author>();
        }
        authors.add(author);
        author.addBook(this);
    }

    public int compareTo(Book other) {
        return title.compareTo(other.getTitle());
    }


}
