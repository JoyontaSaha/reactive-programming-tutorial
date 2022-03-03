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

    @Test
    @Order(10)
    @DisplayName("FruitsFluxConcateMap() Method Testing")
    void testFruitsFluxConcateMap() {
        var fruitsFluxConcateMap = fluxAndMonoServices.fruitsFluxConcateMap();

        StepVerifier.create(fruitsFluxConcateMap)
                .expectNextCount(("Mango" + "Orange" + "Banana").length())
                .verifyComplete();
    }

    @Test
    @Order(11)
    @DisplayName("FruitMonoFlatMapMany() Method Testing")
    void testFruitMonoFlatMapMany() {
        var fruitMonoFlatMapMany = fluxAndMonoServices.fruitMonoFlatMapMany();

        StepVerifier.create(fruitMonoFlatMapMany)
                .expectNextCount(5)
                .verifyComplete();
    }

    @Test
    @Order(12)
    @DisplayName("FruitsFluxTransformFilter() Method Testing")
    void testFruitsFluxTransformFilter() {
        var fruitsFluxTransformFilter = fluxAndMonoServices.fruitsFluxTransformFilter(5);

        StepVerifier.create(fruitsFluxTransformFilter)
                .expectNext("Orange", "Banana")
                .verifyComplete();
    }

    @Test
    @Order(12)
    @DisplayName("FruitsFluxTransformFilterMap() Method Testing")
    void testFruitsFluxTransformFilterMap() {
        var fruitsFluxTransformFilterMap = fluxAndMonoServices.fruitsFluxTransformFilterMap(5);

        StepVerifier.create(fruitsFluxTransformFilterMap)
                .expectNext("ORANGE", "BANANA")
                .verifyComplete();
    }

    @Test
    @Order(13)
    @DisplayName("FruitsFluxDefaultIfEmpty() Method Testing")
    void testFruitsFluxDefaultIfEmpty() {
        var fruitsFluxDefaultIfEmpty = fluxAndMonoServices.fruitsFluxDefaultIfEmpty(8);

        StepVerifier.create(fruitsFluxDefaultIfEmpty)
                .expectNext("Jack Fruit")
                .verifyComplete();
    }

    @Test
    @Order(14)
    @DisplayName("FruitsFluxSwitchIfEmpty() Method Testing")
    void testFruitsFluxSwitchIfEmpty() {
        var fruitsFluxSwitchIfEmpty = fluxAndMonoServices.fruitsFluxSwitchIfEmpty(8);

        StepVerifier.create(fruitsFluxSwitchIfEmpty)
                .expectNext("Jack Fruit", "Pineapple")
                .verifyComplete();
    }

    @Test
    @Order(15)
    @DisplayName("FruitsVeggiesFluxConcat() Method Testing")
    void testFruitsVeggiesFluxConcat() {
        var fruitsVeggiesFluxConcat = fluxAndMonoServices.fruitsVeggiesFluxConcat();

        StepVerifier.create(fruitsVeggiesFluxConcat)
                .expectNext("Mango", "Orange", "Banana", "Tomato", "Cucumber", "Carrot")
                .verifyComplete();
    }

    @Test
    @Order(16)
    @DisplayName("FruitsVeggiesFluxConcatWith() Method Testing")
    void testFruitsVeggiesFluxConcatWith() {
        var fruitsVeggiesFluxConcatWith = fluxAndMonoServices.fruitsVeggiesFluxConcatWith();

        StepVerifier.create(fruitsVeggiesFluxConcatWith)
                .expectNext("Mango", "Orange", "Banana", "Tomato", "Cucumber", "Carrot")
                .verifyComplete();
    }

    @Test
    @Order(17)
    @DisplayName("FruitVeggieMonoConcatWith() Method Testing")
    void testFruitVeggieMonoConcatWith() {
        var fruitVeggieMonoConcatWith = fluxAndMonoServices.fruitVeggieMonoConcatWith();

        StepVerifier.create(fruitVeggieMonoConcatWith)
                .expectNext("Mango","Tomato")
                .verifyComplete();
    }

    @Test
    @Order(18)
    @DisplayName("FruitsVeggiesFluxMerge() Method Testing")
    void testFruitsVeggiesFluxMerge() {
        var fruitsVeggiesFluxMerge = fluxAndMonoServices.fruitsVeggiesFluxMerge();

        StepVerifier.create(fruitsVeggiesFluxMerge)
                .expectNext("Mango", "Tomato","Orange","Cucumber", "Banana","Carrot")
                .verifyComplete();
    }

    @Test
    @Order(19)
    @DisplayName("FruitsVeggiesFluxMergeWith() Method Testing")
    void testFruitsVeggiesFluxMergeWith() {
        var fruitsVeggiesFluxMergeWith = fluxAndMonoServices.fruitsVeggiesFluxMergeWith();

        StepVerifier.create(fruitsVeggiesFluxMergeWith)
                .expectNext("Mango", "Tomato","Orange","Cucumber", "Banana","Carrot")
                .verifyComplete();
    }

    @Test
    @Order(20)
    @DisplayName("FruitsVeggiesFluxMergeSequential() Method Testing")
    void testFruitsVeggiesFluxMergeSequential() {
        var fruitsVeggiesFluxMergeSequential = fluxAndMonoServices.fruitsVeggiesFluxMergeSequential();

        StepVerifier.create(fruitsVeggiesFluxMergeSequential)
                .expectNext("Mango", "Orange", "Banana", "Tomato", "Cucumber", "Carrot")
                .verifyComplete();
    }

    @Test
    @Order(21)
    @DisplayName("FruitsVeggiesFluxZip() Method Testing")
    void testFruitsVeggiesFluxZip() {
        var fruitsVeggiesFluxZip = fluxAndMonoServices.fruitsVeggiesFluxZip();

        StepVerifier.create(fruitsVeggiesFluxZip)
                .expectNext("MangoTomato", "OrangeCucumber", "BananaCarrot")
                .verifyComplete();
    }

    @Test
    @Order(22)
    @DisplayName("FruitsVeggiesFluxZipTuple() Method Testing")
    void testFruitsVeggiesFluxZipTuple() {
        var fruitsVeggiesFluxZipTuple = fluxAndMonoServices.fruitsVeggiesFluxZipTuple();

        StepVerifier.create(fruitsVeggiesFluxZipTuple)
                .expectNext("MangoTomatoYellow", "OrangeCucumberGreen", "BananaCarrotRed")
                .verifyComplete();
    }

    @Test
    @Order(23)
    @DisplayName("FruitsVeggiesFluxZipWith() Method Testing")
    void testFruitsVeggiesFluxZipWith() {
        var fruitsVeggiesFluxZipWith = fluxAndMonoServices.fruitsVeggiesFluxZipWith();

        StepVerifier.create(fruitsVeggiesFluxZipWith)
                .expectNext("MangoTomato", "OrangeCucumber", "BananaCarrot")
                .verifyComplete();
    }

    @Test
    @Order(24)
    @DisplayName("FruitVeggieMonoZip() Method Testing")
    void testFruitVeggieMonoZip() {
        var fruitVeggieMonoZip = fluxAndMonoServices.fruitsVeggiesMonoZip();

        StepVerifier.create(fruitVeggieMonoZip)
                .expectNext("MangoTomato")
                .verifyComplete();
    }

    @Test
    @Order(25)
    @DisplayName("FruitVeggieMonoZipWith() Method Testing")
    void testFruitVeggieMonoZipWith() {
        var fruitVeggieMonoZipWith = fluxAndMonoServices.fruitsVeggiesMonoZipWith();

        StepVerifier.create(fruitVeggieMonoZipWith)
                .expectNext("MangoTomato")
                .verifyComplete();
    }

    @Test
    @Order(26)
    @DisplayName("FruitsFluxDoOn() Method Testing")
    void testFruitsFluxDoOn() {
        var fruitsFluxDoOn = fluxAndMonoServices.fruitsFluxDoOn(5);

        StepVerifier.create(fruitsFluxDoOn)
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    @Order(27)
    @DisplayName("FruitsFluxOnErrorReturn() Method Testing")
    void testFruitsFluxOnErrorReturn() {
        var fruitsFluxOnErrorReturn = fluxAndMonoServices.fruitsFluxOnErrorReturn();

        StepVerifier.create(fruitsFluxOnErrorReturn)
                .expectNext("Mango", "Orange","Banana","Apple")
                .verifyComplete();
    }

    @Test
    @Order(28)
    @DisplayName("FruitsFluxOnErrorContinue() Method Testing")
    void testFruitsFluxOnErrorContinue() {
        var fruitsFluxOnErrorContinue = fluxAndMonoServices.fruitsFluxOnErrorContinue();

        StepVerifier.create(fruitsFluxOnErrorContinue)
                .expectNext("MANGO","BANANA")
                .verifyComplete();
    }

    @Test
    @Order(29)
    @DisplayName("FruitsFluxOnErrorMap() Method Testing")
    void testFruitsFluxOnErrorMap() {
        var fruitsFluxOnErrorMap = fluxAndMonoServices.fruitsFluxOnErrorMap();

        StepVerifier.create(fruitsFluxOnErrorMap)
                .expectNext("MANGO")
                .expectError(IllegalArgumentException.class)
                .verify();
    }

    @Test
    @Order(30)
    @DisplayName("FruitsFluxDoOnError() Method Testing")
    void testFruitsFluxDoOnError() {
        var fruitsFluxDoOnError = fluxAndMonoServices.fruitsFluxDoOnError();

        StepVerifier.create(fruitsFluxDoOnError)
                .expectNext("MANGO")
                .expectError(RuntimeException.class)
                .verify();
    }

}