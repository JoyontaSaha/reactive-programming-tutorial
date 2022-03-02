package services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class FluxAndMonoServices {
    public static void main(String[] args) {
        FluxAndMonoServices fluxAndMonoServices = new FluxAndMonoServices();

        fluxAndMonoServices.fruitsFlux()
                .subscribe(fruit -> {
                    System.out.println("Flux Fruit :: "+ fruit);
                });

        fluxAndMonoServices.fruitMono()
                .subscribe(fruit -> {
                    System.out.println("Mono Fruit :: "+ fruit);
                });

    }

    public Flux<String> fruitsFlux() {
        return Flux.fromIterable(List.of("Mango", "Orange", "Banana")).log();
    }

    public Mono<String> fruitMono() {
        return Mono.just("Apple").log();
    }
}
