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
                .expectComplete();
    }

    @Test
    @Order(2)
    @DisplayName("FruitMono() Method Testing")
    void testFruitMono() {
         var fruitMono = fluxAndMonoServices.fruitMono();

         StepVerifier.create(fruitMono)
                 .expectNext("Apple")
                 .expectComplete();
    }
}