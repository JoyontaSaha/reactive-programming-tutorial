package service;

import exception.BookException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
 class BookServiceMockTest {

    @Mock
    private BookInfoService bookInfoService;

    @Mock
    private ReviewService reviewService;

    @InjectMocks
    private BookService bookService;

    @Test
    void testGetAllBooks() {
        Mockito.when(bookInfoService.getAllBooksInfo())
                .thenCallRealMethod();

        Mockito.when(reviewService.getReviewsByBookId(Mockito.anyLong()))
                .thenCallRealMethod();

        var allBooks = bookService.getAllBooks();

        StepVerifier.create(allBooks)
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    void testGetAllBooksOnError() {
        Mockito.when(bookInfoService.getAllBooksInfo())
                .thenCallRealMethod();

        Mockito.when(reviewService.getReviewsByBookId(Mockito.anyLong()))
                .thenThrow(new IllegalArgumentException("Exception using test"));

        var allBooks = bookService.getAllBooks();

        StepVerifier.create(allBooks)
                .expectError(BookException.class)
                .verify();
    }

    @Test
    void testGetAllBooksOnErrorRetry() {
        Mockito.when(bookInfoService.getAllBooksInfo())
                .thenCallRealMethod();

        Mockito.when(reviewService.getReviewsByBookId(Mockito.anyLong()))
                .thenThrow(new IllegalArgumentException("Exception using test"));

        var allBooks = bookService.getAllBooksRetry();

        StepVerifier.create(allBooks)
                .expectError(BookException.class)
                .verify();
    }

    @Test
    void testGetAllBooksOnErrorRetryN() {
        Mockito.when(bookInfoService.getAllBooksInfo())
                .thenCallRealMethod();

        Mockito.when(reviewService.getReviewsByBookId(Mockito.anyLong()))
                .thenThrow(new IllegalArgumentException("Exception using test"));

        var allBooks = bookService.getAllBooksRetryN(3);

        StepVerifier.create(allBooks)
                .expectError(BookException.class)
                .verify();
    }

    @Test
    void testGetAllBooksOnErrorRetryWhen() {
        Mockito.when(bookInfoService.getAllBooksInfo())
                .thenCallRealMethod();

        Mockito.when(reviewService.getReviewsByBookId(Mockito.anyLong()))
                .thenThrow(new IllegalArgumentException("Exception using test"));

        var allBooks = bookService.getAllBooksRetryWhen();

        StepVerifier.create(allBooks)
                .expectError(BookException.class)
                .verify();
    }


}