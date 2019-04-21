package com.ahenteti.java;

import com.ahenteti.java.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MergeDemo {

    /**
     * Merge flux1 and flux2 values with interleave
     *
     * @param flux1
     *         flux1
     * @param flux2
     *         flux2
     * @return flux
     */
    Flux<User> mergeFluxWithInterleave(Flux<User> flux1, Flux<User> flux2) {
        return flux1.mergeWith(flux2);
    }

    /**
     * Merge flux1 and flux2 values with no interleave (flux1 values and then flux2 values)
     *
     * @param flux1
     *         flux1
     * @param flux2
     *         flux2
     * @return flux
     */
    Flux<User> mergeFluxWithNoInterleave(Flux<User> flux1, Flux<User> flux2) {
        return flux1.concatWith(flux2);
    }

    /**
     * Create a Flux containing the value of mono1 then the value of mono2
     *
     * @param mono1
     *         mono1
     * @param mono2
     *         mono2
     * @return flux
     */
    Flux<User> createFluxFromMultipleMono(Mono<User> mono1, Mono<User> mono2) {
        return Flux.concat(mono1, mono2);
    }

}
