package service;

import domain.Book;
import domain.Review;
import exception.BookException;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    public Mono<Book> getBookById(Long bookId) {
        var book = bookInfoService.getBookInfoById(bookId);
        var reviews = reviewService.getReviewsByBookId(bookId).collectList();

        //return book.zipWith(reviews, (b, r) -> new Book(b, r)); //alternative
        return book.zipWith(reviews, Book::new).log();
    }
}
