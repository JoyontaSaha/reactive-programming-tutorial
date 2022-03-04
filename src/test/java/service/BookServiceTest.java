package service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    private BookInfoService bookInfoService = new BookInfoService();

    private ReviewService reviewService = new ReviewService();

    private BookService bookService = new BookService(bookInfoService, reviewService);

    @Test
    void getAllBooks() {
        var allBooks = bookService.getAllBooks();

        StepVerifier.create(allBooks)
                .assertNext(book -> {
                    assertEquals("Book One", book.getBookInfo().getTitle());
                    assertEquals(2, book.getReviews().size());
                })
                .assertNext(book -> {
                    assertEquals("Book Two", book.getBookInfo().getTitle());
                    assertEquals(2, book.getReviews().size());
                })
                .assertNext(book -> {
                    assertEquals("Book Three", book.getBookInfo().getTitle());
                    assertEquals(2, book.getReviews().size());
                })
                .verifyComplete();
    }

    @Test
    void getBookById() {
        var book = bookService.getBookById(1L);

        StepVerifier.create(book)
                .assertNext(b -> {
                    assertEquals("Book One", b.getBookInfo().getTitle());
                    assertEquals(2, b.getReviews().size());
                })
                .verifyComplete();
    }
}