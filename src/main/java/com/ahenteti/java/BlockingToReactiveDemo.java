package com.ahenteti.java;

import com.ahenteti.java.domain.User;
import com.ahenteti.java.repository.BlockingRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class BlockingToReactiveDemo {

    /**
     * Create a Flux for reading all users from the blocking repository deferred until the flux is subscribed, and run it with an elastic scheduler
     *
     * @param repository
     *         repository
     * @return flux
     */
    Flux<User> blockingRepositoryToFlux(BlockingRepository<User> repository) {
        return Flux
                .defer(() -> Flux.fromIterable(repository.findAll()))
                .subscribeOn(Schedulers.elastic());
    }

    /**
     * Insert users contained in the Flux parameter in the blocking repository using an elastic scheduler and return a Mono<Void> that signal the end of the operation
     *
     * @param flux
     *         flux
     * @param repository
     *         repository
     * @return mono
     */
    Mono<Void> fluxToBlockingRepository(Flux<User> flux, BlockingRepository<User> repository) {
        return flux
                .publishOn(Schedulers.elastic())
                .doOnNext(repository::save)
                .then();
    }

}
