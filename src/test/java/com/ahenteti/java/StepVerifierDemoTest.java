/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ahenteti.java;

import java.time.Duration;
import com.ahenteti.java.domain.User;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class StepVerifierDemoTest {

    StepVerifierDemo workshop = new StepVerifierDemo();

    //========================================================================================

    @Test
    public void expectElementsThenComplete() {
        workshop.expectFooBarComplete(Flux.just("foo", "bar"));
    }

    //========================================================================================

    @Test
    public void expect2ElementsThenError() {
        workshop.expectFooBarError(Flux.just("foo", "bar").concatWith(Mono.error(new RuntimeException())));
    }

    //========================================================================================

    @Test
    public void expectElementsWithThenComplete() {
        workshop.expectSkylerJesseComplete(Flux.just(new User("swhite", null, null), new User("jpinkman", null, null)));
    }

    //========================================================================================

    @Test
    public void count() {
        workshop.expect10Elements(Flux.interval(Duration.ofSeconds(1)).take(10));
    }

    //========================================================================================

    @Test
    public void countWithVirtualTime() {
        workshop.expect3600Elements(() -> Flux.interval(Duration.ofSeconds(1)).take(3600));
    }

}
