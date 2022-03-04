package services;

import domain.BookInfo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class BookInfoService {

    public Flux<BookInfo> getAllBooksInfo() {
        var allBooksInfoList = List.of(
                new BookInfo(1L,"Book One","Author One","12121212"),
                new BookInfo(2L,"Book Two","Author Two","42342343"),
                new BookInfo(3L,"Book Three","Author Three","23425444")
        );

        return Flux.fromIterable(allBooksInfoList);
    }

    public Mono<BookInfo> getBookInfoById(Long bookId) {
        var book = new BookInfo(bookId, "Book One","Author One","12121212");

        return Mono.just(book);
    }
}
