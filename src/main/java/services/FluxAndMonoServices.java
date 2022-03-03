package services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class FluxAndMonoServices {
    public static void main(String[] args) {
        FluxAndMonoServices fluxAndMonoServices = new FluxAndMonoServices();

        fluxAndMonoServices.fruitsFlux()
                .subscribe(s -> {
                    System.out.println("Flux Fruit :: "+ s);
                });

        fluxAndMonoServices.fruitMono()
                .subscribe(s -> {
                    System.out.println("Mono Fruit :: "+ s);
                });

        fluxAndMonoServices.fruitMonoMap()
                .subscribe(s -> {
                    System.out.println("Map Mono Fruit :: "+ s);
                });

    }

    public Flux<String> fruitsFlux() {
        return Flux.fromIterable(List.of("Mango", "Orange", "Banana")).log();
    }

    public Mono<String> fruitMono() {
        return Mono.just("Apple").log();
    }

    public Flux<String> fruitsFluxMap() {
        return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
                .map(String :: toUpperCase)
                .log();
    }

    public Mono<String> fruitMonoMap() {
        return Mono.just("Apple")
                .map(String :: toUpperCase)
                .log();
    }

    public Flux<String> fruitsFluxFilter(int limit) {
        return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
                .filter(s -> s.length() > limit)
                .log();
    }

    public Flux<String> fruitsFluxFilterMap(int limit) {
        return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
                .filter(s -> s.length() > limit)
                .map(String :: toUpperCase)
                .log();
    }

    public Flux<String> fruitsFluxFlatMap() {
        return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
                .flatMap(s -> Flux.just(s.split("")))
                .log();
    }

    public Flux<String> fruitsFluxFlatMapAsync() {
        return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
                .flatMap(s -> Flux.just(s.split(""))
                .delayElements(Duration.ofMillis(
                        new Random().nextInt(1000)
                )))
                .log();
    }

    public Mono<List<String>> fruitMonoFlatMap() {
        return Mono.just("Apple")
                .flatMap(s -> Mono.just(List.of(s.split(""))))
                .log();
    }

    public Flux<String> fruitsFluxConcateMap() {
        return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
                .concatMap(s -> Flux.just(s.split(""))
                        .delayElements(Duration.ofMillis(
                                new Random().nextInt(1000)
                        )))
                .log();
    }

    public Flux<String> fruitMonoFlatMapMany() {
        return Mono.just("Apple")
                .flatMapMany(s -> Flux.just(s.split("")))
                .log();
    }
}
