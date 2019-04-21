package com.ahenteti.java;

import reactor.core.publisher.Mono;

public class MonoDemo {

    /**
     * Return an empty Mono
     *
     * @return an empty Mono
     */
    Mono<String> emptyMono() {
        return Mono.empty();
    }

    /**
     * Return a Mono that never emits any signal
     *
     * @return a Mono that never emits any signal
     */
    Mono<String> monoWithNoSignal() {
        return Mono.never();
    }

    /**
     * Return a Mono that contains a "foo" value
     *
     * @return a Mono that contains a "foo" value
     */
    Mono<String> fooMono() {
        return Mono.just("foo");
    }

    /**
     * Create a Mono that emits an IllegalStateException
     *
     * @return a Mono that emits an IllegalStateException
     */
    Mono<String> errorMono() {
        return Mono.error(new IllegalStateException());
    }

}
