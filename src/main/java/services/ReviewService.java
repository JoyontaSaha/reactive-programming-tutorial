package services;

import domain.Review;
import reactor.core.publisher.Flux;

import java.util.List;

public class ReviewService {

    public Flux<Review> getReviewsByBookId(Long bookId) {
        var reviews = List.of(
                new Review(1L ,bookId,9.1,"Good Book"),
                new Review(2L ,bookId,8.6,"Worth Reading")
        );

        return Flux.fromIterable(reviews);
    }
}
