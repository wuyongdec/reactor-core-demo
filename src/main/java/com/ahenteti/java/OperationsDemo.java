package com.ahenteti.java;

import com.ahenteti.java.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class OperationsDemo {

    /**
     * Create a Flux of user from Flux of username, firstname and lastname.
     *
     * @param usernameFlux
     *         usernameFlux
     * @param firstnameFlux
     *         firstnameFlux
     * @param lastnameFlux
     *         lastnameFlux
     * @return
     */
    Flux<User> userFluxFromStringFlux(Flux<String> usernameFlux, Flux<String> firstnameFlux,
            Flux<String> lastnameFlux) {
        return Flux.zip(usernameFlux, firstnameFlux, lastnameFlux)
                .map(tuple -> new User(tuple.getT1(), tuple.getT2(), tuple.getT3()));
    }

    /**
     * Return the mono which returns its value faster
     *
     * @param mono1
     *         mono1
     * @param mono2
     *         mono2
     * @return
     */
    Mono<User> useFastestMono(Mono<User> mono1, Mono<User> mono2) {
        return Mono.first(mono1, mono2);
    }

    /**
     * Return the flux which returns the first value faster
     *
     * @param flux1
     *         flux1
     * @param flux2
     *         flux2
     * @return
     */
    Flux<User> useFastestFlux(Flux<User> flux1, Flux<User> flux2) {
        return Flux.first(flux1, flux2);
    }

    /**
     * Convert the input Flux<User> to a Mono<Void> that represents the complete signal of the flux
     *
     * @param flux
     *         flux
     * @return flux
     */
    Mono<Void> fluxCompletion(Flux<User> flux) {
        return flux.then();
    }

    /**
     * Return a valid Mono of user for null input and non null input user (hint: Reactive Streams do not accept null values)
     *
     * @param user
     *         user
     * @return mono
     */
    Mono<User> nullAwareUserToMono(User user) {
        return Mono.justOrEmpty(user);
    }

    /**
     * Return the same mono passed as input parameter, expect that it will emit User.SKYLER when empty
     *
     * @param mono
     *         mono
     * @return mono
     */
    Mono<User> emptyToSkyler(Mono<User> mono) {
        return mono.defaultIfEmpty(User.SKYLER);
    }

}
