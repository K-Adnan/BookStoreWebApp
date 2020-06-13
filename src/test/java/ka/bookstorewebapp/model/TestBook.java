package ka.bookstorewebapp.model;

import ka.bookstorewebapp.entities.Author;
import ka.bookstorewebapp.entities.Book;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestBook {

    Book book;

    @Before
    public void setUp() {
        book = new Book();
    }

    @Test
    public void test_AddingACustomerRatingOfFive_ToABookWithNoRatings_ChangesAvgCustomerRatingToFive() {
        book.addCustomerRating(5);

        assertEquals(5, book.getAvgCustomerRating(), 0.01);
    }

    @Test
    public void test_AddingCustomerRatingOfFive_ToBookWithAvgCustomerRatingFour_ChangesAvgCustomerRatingToFourPointFive() {
        book.addCustomerRating(4);
        book.addCustomerRating(5);

        assertEquals(4.5, book.getAvgCustomerRating(), 0.01);

    }

    @Test
    public void test_CallingTheConstructor_SetsAllVaiables() {
        Book book = new Book(12345l, "Title", "Category", 100, 2017, 9.99);
        assertEquals(book.getIsbn(), 12345l);
        assertEquals(book.getTitle(), "Title");
        assertEquals(book.getCategory(), "Category");
        assertEquals(book.getNumberOfPages(), 100);
        assertEquals(book.getReleaseYear(), 2017);
        assertEquals(book.getPrice(), 9.99, 0.01);
    }

    @Test
    public void test_CallingTheToStringMethodOnBook_ReturnsBookAsString() {
        Book book = new Book(12345l, "Title", "Category", 100, 2017, 9.99);
        String toString = "[isbn=" + 12345l + ", title=" + "Title" + ", author=[]" + ", price=" + 9.99 + ", pages=" + 100 + "]";
        assertEquals(book.toString(), toString);
    }

    @Test
    public void test_SetAuthorOnBook_AddsAuthorToSetOfAuthors() {
        Author author = new Author();
        book.setAuthor(author);

        assertEquals(1, book.getAuthors().size());
    }

    @Test
    public void test_SetAuthorOnBook_CreatesNewHashSet_WhenSetIsNull() {
        book.setAuthors(null);
        book.setAuthor(new Author());
    }

}
