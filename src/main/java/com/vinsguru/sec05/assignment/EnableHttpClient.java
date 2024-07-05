package com.vinsguru.sec05.assignment;

import com.vinsguru.common.AbstractHttpClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class EnableHttpClient extends AbstractHttpClient {

    public Mono<String> getEmptyFallback(int productId){
        return this.httpClient.get()
                .uri("/demo03/empty-fallback/product/"+productId)
                .responseContent()
                .asString()
                .next();
    }

    public Mono<String> getProductName(int productId){
        return this.httpClient.get()
                .uri("/demo03/product/"+ productId)
                .responseContent()
                .asString()
                .next();
    }

    public Mono<String> getTimeOutFallback(int productId){
        return this.httpClient.get()
                .uri("/demo03/timeout-fallback/product/"+ productId)
                .responseContent()
                .asString()
                .next();
    }
}
