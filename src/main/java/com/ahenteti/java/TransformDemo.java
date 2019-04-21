package com.ahenteti.java;

import com.ahenteti.java.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class TransformDemo {

    /**
     * Capitalize the user username, firstname and lastname
     *
     * @param mono
     *         mono
     * @return mono
     */
    Mono<User> capitalizeOne(Mono<User> mono) {
        return mono.map(u -> new User(
                u.getUsername().toUpperCase(), 
                u.getFirstname().toUpperCase(),
                u.getLastname().toUpperCase()
        ));
    }

    /**
     * Capitalize the users username, firstName and lastName
     *
     * @param flux
     *         flux
     * @return flux
     */
    Flux<User> capitalizeMany(Flux<User> flux) {
        return flux.map(u -> new User(
                u.getUsername().toUpperCase(),
                u.getFirstname().toUpperCase(),
                u.getLastname().toUpperCase()
        ));
    }

    /**
     * Capitalize the users username, firstName and lastName using #asyncCapitalizeUser
     *
     * @param flux
     *         flux
     * @return flux
     */
    Flux<User> asyncCapitalizeMany(Flux<User> flux) {
        return flux.flatMap(u -> asyncCapitalizeUser(u));
    }

    Mono<User> asyncCapitalizeUser(User u) {
        return Mono.just(new User(u.getUsername().toUpperCase(), u.getFirstname().toUpperCase(),
                u.getLastname().toUpperCase()));
    }

}
