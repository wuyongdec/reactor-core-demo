package com.ahenteti.java;

import java.time.Duration;
import org.junit.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class MonoDemoTest {

    MonoDemo workshop = new MonoDemo();

    //========================================================================================

    @Test
    public void empty() {
        Mono<String> mono = workshop.emptyMono();
        StepVerifier.create(mono).verifyComplete();
    }

    //========================================================================================

    @Test
    public void noSignal() {
        Mono<String> mono = workshop.monoWithNoSignal();
        StepVerifier.create(mono).expectSubscription().expectNoEvent(Duration.ofSeconds(1)).thenCancel().verify();
    }

    //========================================================================================

    @Test
    public void fromValue() {
        Mono<String> mono = workshop.fooMono();
        StepVerifier.create(mono).expectNext("foo").verifyComplete();
    }

    //========================================================================================

    @Test
    public void error() {
        Mono<String> mono = workshop.errorMono();
        StepVerifier.create(mono).verifyError(IllegalStateException.class);
    }

}
