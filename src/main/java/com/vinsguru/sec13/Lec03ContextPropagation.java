package com.vinsguru.sec13;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.context.Context;

public class Lec03ContextPropagation {

    private static final Logger log = LoggerFactory.getLogger(Lec03ContextPropagation.class);

    public static void main(String[] args) {
        getWelcomeMessage()
                .concatWith(Flux.merge(product1(), product2().contextWrite(ctx -> Context.empty())))
                .contextWrite(Context.of("user", "sam"))
                .subscribe(Util.subscriber());
        Util.sleepSeconds(2);
    }
    private static Mono<String> getWelcomeMessage(){
        return Mono.deferContextual(ctx -> {
            if (ctx.hasKey("user")){
                return Mono.just("Welcome %s".formatted(ctx.<String>get("user")));
            }
            return Mono.error(new RuntimeException("Unauthenticated"));
        });
    }

    private static Mono<String> product1(){
        return Mono.<String>deferContextual(ctx -> {
            log.info("Product 1: {}", ctx);
            return Mono.empty();
        })
                .subscribeOn(Schedulers.boundedElastic());
    }

    private static Mono<String> product2(){
        return Mono.<String>deferContextual(ctx -> {
                    log.info("Product 2: {}", ctx);
                    return Mono.empty();
                })
                .subscribeOn(Schedulers.parallel());
    }
}
