package service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

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

    public Flux<String> fruitsFluxTransformFilter(int limit) {
        Function<Flux<String>, Flux<String>> filterData =
                data -> data.filter(s -> s.length() > limit);

        return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
                .transform(filterData)
                .log();
    }
    public Flux<String> fruitsFluxTransformFilterMap(int limit) {
        Function<Flux<String>, Flux<String>> filterData =
                data -> data.filter(s -> s.length() > limit)
                        .map(String :: toUpperCase);

        return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
                .transform(filterData)
                .log();
    }

    public Flux<String> fruitsFluxDefaultIfEmpty(int limit) {
        Function<Flux<String>, Flux<String>> filterData =
                data -> data.filter(s -> s.length() > limit);

        return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
                .transform(filterData)
                .defaultIfEmpty("Jack Fruit")
                .log();
    }

    public Flux<String> fruitsFluxSwitchIfEmpty(int limit) {
        Function<Flux<String>, Flux<String>> filterData =
                data -> data.filter(s -> s.length() > limit);

        return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
                .transform(filterData)
                .switchIfEmpty(Flux.just("Jack Fruit", "Guava", "Pineapple"))
                .transform(filterData)
                .log();
    }

    public Flux<String> fruitsVeggiesFluxConcat() {
        Flux<String> fruits = Flux.just("Mango", "Orange", "Banana");
        Flux<String> veggies = Flux.just("Tomato", "Cucumber", "Carrot");

        return Flux.concat(fruits, veggies).log();
    }

    public Flux<String> fruitsVeggiesFluxConcatWith() {
        Flux<String> fruits = Flux.just("Mango", "Orange", "Banana");
        Flux<String> veggies = Flux.just("Tomato", "Cucumber", "Carrot");

        return fruits.concatWith(veggies).log();
    }

    public Flux<String> fruitVeggieMonoConcatWith() {
        Mono<String> fruit = Mono.just("Mango");
        Mono<String> veggie = Mono.just("Tomato");

        return fruit.concatWith(veggie).log();
    }

    public Flux<String> fruitsVeggiesFluxMerge() {
        Flux<String> fruits = Flux.just("Mango", "Orange", "Banana")
                .delayElements(Duration.ofMillis(45));
        Flux<String> veggies = Flux.just("Tomato", "Cucumber", "Carrot")
                .delayElements(Duration.ofMillis(55));

        return Flux.merge(fruits, veggies).log();
    }

    public Flux<String> fruitsVeggiesFluxMergeWith() {
        Flux<String> fruits = Flux.just("Mango", "Orange", "Banana")
                .delayElements(Duration.ofMillis(45));
        Flux<String> veggies = Flux.just("Tomato", "Cucumber", "Carrot")
                .delayElements(Duration.ofMillis(55));

        return fruits.mergeWith(veggies).log();
    }

    public Flux<String> fruitsVeggiesFluxMergeSequential() {
        Flux<String> fruits = Flux.just("Mango", "Orange", "Banana")
                .delayElements(Duration.ofMillis(45));
        Flux<String> veggies = Flux.just("Tomato", "Cucumber", "Carrot")
                .delayElements(Duration.ofMillis(55));

        return Flux.mergeSequential(fruits, veggies).log();
    }

    public Flux<String> fruitsVeggiesFluxZip() {
        Flux<String> fruits = Flux.just("Mango", "Orange", "Banana");
        Flux<String> veggies = Flux.just("Tomato", "Cucumber", "Carrot");

        return Flux.zip(fruits, veggies, (first, second) -> first + second).log();
    }

    public Flux<String> fruitsVeggiesFluxZipTuple() {
        Flux<String> fruits = Flux.just("Mango", "Orange", "Banana");
        Flux<String> veggies = Flux.just("Tomato", "Cucumber", "Carrot");
        Flux<String> colors = Flux.just("Yellow", "Green", "Red");

        return Flux.zip(fruits, veggies, colors)
                .map(Object -> Object.getT1() + Object.getT2() + Object.getT3()).log();
    }

    public Flux<String> fruitsVeggiesFluxZipWith() {
        Flux<String> fruits = Flux.just("Mango", "Orange", "Banana");
        Flux<String> veggies = Flux.just("Tomato", "Cucumber", "Carrot");

        return fruits.zipWith(veggies, (first, second) -> first + second).log();
    }


    public Mono<String> fruitsVeggiesMonoZip() {
        Mono<String> fruit = Mono.just("Mango");
        Mono<String> veggie = Mono.just("Tomato");

        return Mono.zip(fruit, veggie, (first, second) -> first + second).log();
    }

    public Mono<String> fruitsVeggiesMonoZipWith() {
        Mono<String> fruit = Mono.just("Mango");
        Mono<String> veggie = Mono.just("Tomato");

        return fruit.zipWith(veggie, (first, second) -> first + second).log();
    }

    public Flux<String> fruitsFluxDoOn(int limit) {
        return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
                .filter(s -> s.length() > limit)
                .doOnSubscribe(subscription -> {
                    System.out.println("subscription :: " + subscription.toString());
                })
                .doOnNext(s -> {
                    System.out.println("next :: " + s);
                })
                .doOnComplete(() -> System.out.println("Completed"))
                .log();
    }

    public Flux<String> fruitsFluxOnErrorReturn() {
        return Flux.just("Mango", "Orange", "Banana")
                .concatWith(Flux.error(new RuntimeException("Exception Occurred")))
                .onErrorReturn("Apple").log();
    }

    public Flux<String> fruitsFluxOnErrorContinue() {
        return Flux.just("Mango", "Orange", "Banana")
                .map(s -> {
                    if(s.equals("Orange"))
                        throw new RuntimeException("Exception Occurred");
                    return s.toUpperCase();
                })
                .onErrorContinue((e,f) -> {
                    System.out.println("e :: " + e);
                    System.out.println("f :: " + f);
                }).log();
    }

    public Flux<String> fruitsFluxOnErrorMap() {
        return Flux.just("Mango", "Orange", "Banana")
                .checkpoint("Error CheckPoint 1")
                .map(s -> {
                    if(s.equals("Orange"))
                        throw new RuntimeException("Runtime Exception Occurred");
                    return s.toUpperCase();
                })
                .checkpoint("Error CheckPoint 2")
                .onErrorMap(throwable -> {
                    System.out.println("Throwable :: " + throwable);
                    return new IllegalArgumentException("Illegal Argument Exception Occurred");
                }).log();
    }

    public Flux<String> fruitsFluxDoOnError() {
        return Flux.just("Mango", "Orange", "Banana")
                .map(s -> {
                    if(s.equals("Orange"))
                        throw new RuntimeException("Runtime Exception Occurred");
                    return s.toUpperCase();
                })
                .doOnError(throwable -> {
                    System.out.println("Throwable :: " + throwable);
                }).log();
    }

}
