package service;

import domain.Book;
import domain.Review;
import exception.BookException;
import lombok.extern.slf4j.Slf4j;
import reactor.core.Exceptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;
import reactor.util.retry.RetryBackoffSpec;

import java.time.Duration;
import java.util.List;

@Slf4j
public class BookService {

    private final BookInfoService bookInfoService;
    private final ReviewService reviewService;

    public BookService(BookInfoService bookInfoService, ReviewService reviewService) {
        this.bookInfoService = bookInfoService;
        this.reviewService = reviewService;
    }

    public Flux<Book> getAllBooks() {

        var allBooks = bookInfoService.getAllBooksInfo();
        return allBooks
                .flatMap(bookInfo -> {
                    Mono<List<Review>> reviews =
                            reviewService.getReviewsByBookId(bookInfo.getBookId()).collectList();
                    return reviews
                            .map(review -> new Book(bookInfo, review));
                })
                .onErrorMap(throwable -> {
                    log.error("Exception is {}", throwable);
                    return new BookException("Exception occurred while fetching Books");
                })
                .log();
    }

    public Flux<Book> getAllBooksRetry() {

        var allBooks = bookInfoService.getAllBooksInfo();
        return allBooks
                .flatMap(bookInfo -> {
                    Mono<List<Review>> reviews =
                            reviewService.getReviewsByBookId(bookInfo.getBookId()).collectList();
                    return reviews
                            .map(review -> new Book(bookInfo, review));
                })
                .onErrorMap(throwable -> {
                    log.error("Exception is {}", throwable);
                    return new BookException("Exception occurred while fetching Books");
                })
                .retry()
                .log();
    }

    public Flux<Book> getAllBooksRetryN(int n) {

        var allBooks = bookInfoService.getAllBooksInfo();
        return allBooks
                .flatMap(bookInfo -> {
                    Mono<List<Review>> reviews =
                            reviewService.getReviewsByBookId(bookInfo.getBookId()).collectList();
                    return reviews
                            .map(review -> new Book(bookInfo, review));
                })
                .onErrorMap(throwable -> {
                    log.error("Exception is {}", throwable);
                    return new BookException("Exception occurred while fetching Books");
                })
                .retry(n)
                .log();
    }

    public Flux<Book> getAllBooksRetryWhen() {

        var allBooks = bookInfoService.getAllBooksInfo();
        return allBooks
                .flatMap(bookInfo -> {
                    Mono<List<Review>> reviews =
                            reviewService.getReviewsByBookId(bookInfo.getBookId()).collectList();
                    return reviews
                            .map(review -> new Book(bookInfo, review));
                })
                .onErrorMap(throwable -> {
                    log.error("Exception is {}", throwable);
                    return new BookException("Exception occurred while fetching Books");
                })
                .retryWhen(getRetrySpecs())
                .log();
    }

    private RetryBackoffSpec getRetrySpecs() {
        return Retry.backoff(
                        3,
                        Duration.ofMillis(1000)
                ).filter(throwable -> throwable instanceof BookException)
                .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) ->
                        Exceptions.propagate(retrySignal.failure())
                );
    }

    public Mono<Book> getBookById(Long bookId) {
        var book = bookInfoService.getBookInfoById(bookId);
        var reviews = reviewService.getReviewsByBookId(bookId).collectList();

        //return book.zipWith(reviews, (b, r) -> new Book(b, r)); //alternative
        return book.zipWith(reviews, Book::new).log();
    }
}
