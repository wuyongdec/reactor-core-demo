package com.ahenteti.java;

import com.ahenteti.java.domain.User;
import com.ahenteti.java.repository.ReactiveRepository;
import com.ahenteti.java.repository.ReactiveUserRepository;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class RequestDemoTest {

    RequestDemo workshop = new RequestDemo();
    ReactiveRepository<User> repository = new ReactiveUserRepository();

    //========================================================================================

    @Test
    public void requestAll() {
        Flux<User> flux = repository.findAll();
        StepVerifier verifier = workshop.requestAllExpectFour(flux);
        verifier.verify();
    }

    //========================================================================================

    @Test
    public void requestOneByOne() {
        Flux<User> flux = repository.findAll();
        StepVerifier verifier = workshop.requestOneExpectSkylerThenRequestOneExpectJesse(flux);
        verifier.verify();
    }

    //========================================================================================

    @Test
    public void experimentWithLog() {
        Flux<User> flux = workshop.fluxWithLog();
        StepVerifier.create(flux, 0).thenRequest(1).expectNextMatches(u -> true).thenRequest(1)
                .expectNextMatches(u -> true).thenRequest(2).expectNextMatches(u -> true).expectNextMatches(u -> true)
                .verifyComplete();
    }

    //========================================================================================

    @Test
    public void experimentWithDoOn() {
        Flux<User> flux = workshop.fluxWithDoOnPrintln();
        StepVerifier.create(flux).expectNextCount(4).verifyComplete();
    }
}
