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

import java.util.concurrent.CompletableFuture;
import com.ahenteti.java.domain.User;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class AdaptDemo {

    /**
     * Adapt Flux to RxJava Flowable
     *
     * @param flux
     *         flux
     * @return flux
     */
    Flowable<User> fromFluxToFlowable(Flux<User> flux) {
        return Flowable.fromPublisher(flux);
    }

    /**
     * Adapt RxJava Flowable to Flux
     *
     * @param flowable
     *         flowable
     * @return flux
     */
    Flux<User> fromFlowableToFlux(Flowable<User> flowable) {
        return Flux.from(flowable);
    }

    /**
     * Adapt Flux to RxJava Observable
     *
     * @param flux
     *         flux
     * @return Observable
     */
    Observable<User> fromFluxToObservable(Flux<User> flux) {
        return Observable.fromPublisher(flux);
    }

    /**
     * Adapt RxJava Observable to Flux
     *
     * @param observable
     *         observable
     * @return flux
     */
    Flux<User> fromObservableToFlux(Observable<User> observable) {
        return Flux.from(observable.toFlowable(BackpressureStrategy.BUFFER));
    }

    /**
     * Adapt Flux to RxJava Observable
     *
     * @param mono
     *         mono
     * @return flux
     */
    Single<User> fromMonoToSingle(Mono<User> mono) {
        return Single.fromPublisher(mono);
    }

    /**
     * Adapt RxJava Observable to Flux
     *
     * @param single
     *         single
     * @return flux
     */
    Mono<User> fromSingleToMono(Single<User> single) {
        return Mono.from(single.toFlowable());
    }

    /**
     * Adapt Mono to RxJava Single
     *
     * @param mono
     *         mono
     * @return flux
     */
    CompletableFuture<User> fromMonoToCompletableFuture(Mono<User> mono) {
        return mono.toFuture();
    }

    /**
     * Adapt RxJava Single to Mono
     *
     * @param future
     *         future
     * @return flux
     */
    Mono<User> fromCompletableFutureToMono(CompletableFuture<User> future) {
        return Mono.fromFuture(future);
    }

}
