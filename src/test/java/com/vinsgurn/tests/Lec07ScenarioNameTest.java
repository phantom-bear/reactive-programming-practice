package com.vinsgurn.tests;

import com.vinsguru.common.Util;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

public class Lec07ScenarioNameTest {

    private Flux<Integer> getItems(){
        return Flux.range(1, 3).log();

    }


    @Test
    public void scenarioNameTest() {
        var options = StepVerifierOptions.create().scenarioName("1 to 3 items test");
        StepVerifier.create(getItems(), options)
                .expectNext(11)
                .as("first item should be 11")
                .expectNext(2, 3)
                .as("then 2 and 3")
                .expectComplete()
                .verify();
    }


}
