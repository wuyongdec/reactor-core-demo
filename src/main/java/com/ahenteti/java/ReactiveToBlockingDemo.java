package com.ahenteti.java;

import com.ahenteti.java.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ReactiveToBlockingDemo {

    /**
     * Return the user contained in that Mono
     *
     * @param mono
     *         mono
     * @return user
     */
    User monoToValue(Mono<User> mono) {
        return mono.block();
    }

    /**
     * Return the users contained in that Flux
     *
     * @param flux
     *         flux
     * @return list of users
     */
    Iterable<User> fluxToValues(Flux<User> flux) {
        return flux.toIterable();
    }

}
