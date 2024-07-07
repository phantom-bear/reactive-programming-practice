package com.vinsguru.sec13;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class Lec02ContextAppendUpdate {

    private static final Logger log = LoggerFactory.getLogger(Lec02ContextAppendUpdate.class);

    public static void main(String[] args) {
        getWelcomeMessage()
//                .contextWrite(ctx -> Context.empty())
//                .contextWrite(ctx -> Context.of("user", "mike"))
//                .contextWrite(ctx -> ctx.delete("c"))
                .contextWrite(ctx -> ctx.put("user", ctx.get("user").toString().toUpperCase()))
                .contextWrite(Context.of("a", "b").put("c", "d").put("e", "f"))
                .contextWrite(Context.of("user", "sam"))
                .subscribe(Util.subscriber());
    }

    private static void append(){
        getWelcomeMessage()
                .contextWrite(Context.of("a", "b").put("c", "d").put("e", "f"))
                .contextWrite(Context.of("user", "sam"))
                .subscribe(Util.subscriber());
    }
    private static Mono<String> getWelcomeMessage(){
        return Mono.deferContextual(ctx -> {
            log.info("{}", ctx);
            if (ctx.hasKey("user")){
                return Mono.just("Welcome %s".formatted(ctx.<String>get("user")));
            }
            return Mono.error(new RuntimeException("Unauthenticated"));
        });
    }
}
