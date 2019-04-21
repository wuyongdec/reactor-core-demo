package com.ahenteti.java;

import java.time.Duration;
import java.util.Arrays;
import reactor.core.publisher.Flux;

public class FluxDemo {

    /**
     * Return an empty Flux
     */
    Flux<String> emptyFlux() {
        return Flux.empty();
    }

    /**
     * Return a Flux that contains 2 values "foo" and "bar" without using an array or a collection
     *
     * @return a Flux that contains 2 values "foo" and "bar" without using an array or a collection
     */
    Flux<String> fooBarFluxFromValues() {
        return Flux.just("foo", "bar");
    }

    /**
     * Create a Flux from a List that contains 2 values "foo" and "bar"
     *
     * @return a Flux from a List that contains 2 values "foo" and "bar"
     */
    Flux<String> fooBarFluxFromList() {
        return Flux.fromIterable(Arrays.asList("foo", "bar"));
    }

    /**
     * Create a Flux that emits an IllegalStateException
     *
     * @return a Flux that emits an IllegalStateException
     */
    Flux<String> errorFlux() {
        return Flux.error(new IllegalStateException());
    }

    /**
     * Create a Flux that emits increasing values from 0 to 9 each 100ms
     *
     * @return a Flux that emits increasing values from 0 to 9 each 100ms
     */
    Flux<Long> counter() {
        return Flux.interval(Duration.ofMillis(100)).take(10);
    }

}
