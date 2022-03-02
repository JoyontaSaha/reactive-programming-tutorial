package services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

class FluxAndMonoServicesTest {

    FluxAndMonoServices fluxAndMonoServices = new FluxAndMonoServices();

    @Test
    @Order(1)
    @DisplayName("FruitsFlux() Method Testing")
    void testFruitsFlux() {
        var fruitsFlux = fluxAndMonoServices.fruitsFlux();

        StepVerifier.create(fruitsFlux)
                .expectNext("Mango", "Orange", "Banana")
                .verifyComplete();
    }

    @Test
    @Order(2)
    @DisplayName("FruitMono() Method Testing")
    void testFruitMono() {
         var fruitMono = fluxAndMonoServices.fruitMono();

         StepVerifier.create(fruitMono)
                 .expectNext("Apple")
                 .verifyComplete();
    }

    @Test
    @Order(3)
    @DisplayName("FruitsFluxMap() Method Testing")
    void testFruitsFluxMap() {
        var fruitsFluxMap = fluxAndMonoServices.fruitsFluxMap();

        StepVerifier.create(fruitsFluxMap)
                .expectNext("MANGO", "ORANGE", "BANANA")
                .verifyComplete();
    }

    @Test
    @Order(4)
    @DisplayName("FruitMonoMap() Method Testing")
    void testFruitMonoMap() {
        var fruitMonoMap = fluxAndMonoServices.fruitMonoMap();

        StepVerifier.create(fruitMonoMap)
                .expectNext("APPLE")
                .verifyComplete();
    }

    @Test
    @Order(5)
    @DisplayName("FruitsFluxFilter() Method Testing")
    void testFruitsFluxFilter() {
        var fruitsFluxFilter = fluxAndMonoServices.fruitsFluxFilter(5);

        StepVerifier.create(fruitsFluxFilter)
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    @Order(6)
    @DisplayName("FruitsFluxFilterMap() Method Testing")
    void testFruitsFluxFilterMap() {
        var fruitsFluxFilterMap = fluxAndMonoServices.fruitsFluxFilterMap(5);

        StepVerifier.create(fruitsFluxFilterMap)
                .expectNext("ORANGE", "BANANA")
                .verifyComplete();
    }

    @Test
    @Order(7)
    @DisplayName("FruitsFluxFlatMap() Method Testing")
    void testFruitsFluxFlatMap() {
        var fruitsFluxFlatMap = fluxAndMonoServices.fruitsFluxFlatMap();

        StepVerifier.create(fruitsFluxFlatMap)
                .expectNextCount(("Mango" + "Orange" + "Banana").length())
                .verifyComplete();
    }

    @Test
    @Order(8)
    @DisplayName("FruitsFluxFlatMapAsync() Method Testing")
    void testFruitsFluxFlatMapAsync() {
        var fruitsFluxFlatMapAsync = fluxAndMonoServices.fruitsFluxFlatMapAsync();

        StepVerifier.create(fruitsFluxFlatMapAsync)
                .expectNextCount(("Mango" + "Orange" + "Banana").length())
                .verifyComplete();
    }

    @Test
    @Order(9)
    @DisplayName("FruitMonoFlatMap() Method Testing")
    void testFruitMonoFlatMap() {
        var fruitMonoFlatMap = fluxAndMonoServices.fruitMonoFlatMap();

        StepVerifier.create(fruitMonoFlatMap)
                .expectNextCount(1)
                .verifyComplete();
    }
}