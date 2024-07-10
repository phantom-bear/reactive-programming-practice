package com.vinsgurn.tests;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;
import reactor.util.context.Context;

public class Lec08ContextTest {


    Mono<String> getWelcomeMessage(){
        return Mono.deferContextual(ctx -> {
            if (ctx.hasKey("user")){
                return Mono.just("Welcome %s".formatted(ctx.<String>get("user")));
            }
            return Mono.error(new RuntimeException("Unauthenticated"));
        });
    }
    @Test
    public void welcomeMessageTest(){
        var options = StepVerifierOptions.create().withInitialContext(Context.of("user", "sam"));
        StepVerifier.create(getWelcomeMessage(), options)
                .expectNext("Welcome sam")
                .expectComplete()
                .verify();


    }

    @Test
    public void unauthenticatedTest(){
        var options = StepVerifierOptions.create().withInitialContext(Context.empty());
        StepVerifier.create(getWelcomeMessage(), options)
                .expectErrorMessage("Unauthenticated")
                .verify();


    }
}
