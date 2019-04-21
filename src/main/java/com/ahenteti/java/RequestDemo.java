package com.ahenteti.java;

import com.ahenteti.java.domain.User;
import com.ahenteti.java.repository.ReactiveRepository;
import com.ahenteti.java.repository.ReactiveUserRepository;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class RequestDemo {

    ReactiveRepository<User> repository = new ReactiveUserRepository();

    /**
     * Create a StepVerifier that initially requests all values and expect 4 values to be received
     *
     * @param flux
     *         flux
     * @return flux
     */
    StepVerifier requestAllExpectFour(Flux<User> flux) {
        return StepVerifier.create(flux)
                .expectNextCount(4)
                .expectComplete();
    }

    /**
     * Create a StepVerifier that initially requests 1 value and expects User.SKYLER then requests another value and expects User.JESSE.
     *
     * @param flux
     *         flux
     * @return flux
     */
    StepVerifier requestOneExpectSkylerThenRequestOneExpectJesse(Flux<User> flux) {
        return StepVerifier.create(flux, 1)
                .expectNext(User.SKYLER)
                .thenRequest(1)
                .expectNext(User.JESSE)
                .thenCancel();
    }

    /**
     * Return a Flux with all users stored in the repository that prints automatically logs for all Reactive Streams signals
     *
     * @return flux
     */
    Flux<User> fluxWithLog() {
        return repository
                .findAll()
                .log();
    }

    /**
     * Return a Flux with all users stored in the repository that prints "Starring:" on subscribe, "firstname lastname" for all values and "The end!" on complete
     *
     * @return flux
     */
    Flux<User> fluxWithDoOnPrintln() {
        return repository
                .findAll()
                .doOnSubscribe(s -> System.out.println("Starring:"))
                .doOnNext(u -> System.out.println(u.getFirstname() + " " + u.getLastname()))
                .doOnComplete(() -> System.out.println("The end!"));
    }

}
