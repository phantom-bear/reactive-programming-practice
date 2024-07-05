package com.vinsguru.sec05;

import com.vinsguru.common.Util;
import com.vinsguru.sec05.assignment.EnableHttpClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Lec11Assignment {
    public static void main(String[] args) {

        for(int i = 0; i < 5; i++){
            getProductHelper(i).subscribe(Util.subscriber());
        }

        Util.sleepSeconds(3);


    }

    public static Mono<String> getProductHelper(int productId){
        EnableHttpClient enableHttpClient = new EnableHttpClient();

        return enableHttpClient.getProductName(productId)
                .timeout(Duration.ofSeconds(2), enableHttpClient.getTimeOutFallback(productId))
                .switchIfEmpty(enableHttpClient.getEmptyFallback(productId));
    }
}
