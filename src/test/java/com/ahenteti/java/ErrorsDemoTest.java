/*
 * Copyright (c) 2011-2017 Pivotal Software Inc, All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ahenteti.java;

import com.ahenteti.java.domain.User;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class ErrorsDemoTest {

    ErrorsDemo workshop = new ErrorsDemo();

    //========================================================================================

    @Test
    public void monoWithValueInsteadOfError() {
        Mono<User> mono = workshop.betterCallSaulForBogusMono(Mono.error(new IllegalStateException()));
        StepVerifier.create(mono).expectNext(User.SAUL).verifyComplete();

        mono = workshop.betterCallSaulForBogusMono(Mono.just(User.SKYLER));
        StepVerifier.create(mono).expectNext(User.SKYLER).verifyComplete();
    }

    //========================================================================================

    @Test
    public void fluxWithValueInsteadOfError() {
        Flux<User> flux = workshop.betterCallSaulAndJesseForBogusFlux(Flux.error(new IllegalStateException()));
        StepVerifier.create(flux).expectNext(User.SAUL, User.JESSE).verifyComplete();

        flux = workshop.betterCallSaulAndJesseForBogusFlux(Flux.just(User.SKYLER, User.WALTER));
        StepVerifier.create(flux).expectNext(User.SKYLER, User.WALTER).verifyComplete();
    }

    //========================================================================================

    @Test
    public void handleCheckedExceptions() {
        Flux<User> flux = workshop.capitalizeMany(Flux.just(User.SAUL, User.JESSE));

        StepVerifier.create(flux).verifyError(ErrorsDemo.GetOutOfHereException.class);
    }

}
